package com.company.demo.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api/doc")
public class DocController {
    private DocService docService;

    @Autowired
    public DocController(DocService docService) {
        this.docService = docService;
    }

    @PostMapping("/upload")
    public void uploadDoc(@RequestParam("file")MultipartFile multipartFile){
        docService.save(multipartFile);
    }

    @GetMapping("/{hashId}")
    public ResponseEntity getDocByHashId(@PathVariable String hashId) throws MalformedURLException {
        Doc doc = docService.getDocByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; fileName=\"" +
                                URLEncoder.encode(doc.getName()))
                .contentType(MediaType.parseMediaType(doc.getContentType()))
                .contentLength(doc.getSize())
                .body(new FileUrlResource(docService.uploadFolder + doc.getName()));
    }

    @DeleteMapping("/delete/{hashId}")
    public ResponseEntity delete(@PathVariable String hashId){
        docService.deleteByHashId(hashId);
        return ResponseEntity.ok("Succesfully deleted!");
    }

}
