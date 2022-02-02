package com.company.demo.file;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;

@Service
public class DocService {
    private DocRepository docRepository;

    @Value("${upload.folder}")
    public String uploadFolder;

    private final Hashids hashid;

    @Autowired
    public DocService(DocRepository docRepository) {
        this.docRepository = docRepository;
        this.hashid = new Hashids(getClass().getName(), 6);
    }

    public Doc getDocByHashId(String hashId){
        return docRepository.findByHashId(hashId);
    }

    public void save(MultipartFile multipartFile){
        Doc doc = new Doc();
        doc.setName(multipartFile.getOriginalFilename());
        doc.setExtension(getExtension(doc.getName()));
        doc.setSize(multipartFile.getSize());
        doc.setContentType(multipartFile.getContentType());
        doc.setStatus(DocStatus.DRAFT);
        doc.setPath(uploadFolder);

        docRepository.save(doc);
        doc.setHashId(hashid.encode(doc.getId()));

        docRepository.save(doc);
        try {
            multipartFile.transferTo(new File(doc.getPath() + doc.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getExtension(String name){
        if (name != null && !name.isEmpty() && name.contains(".")){
            int dot = name.lastIndexOf(".");
            return name.substring(dot + 1);
        }
        return null;
    }

    public void deleteByHashId(String hashId){
        Doc doc = docRepository.findByHashId(hashId);
        docRepository.delete(doc);

        File file = new File(uploadFolder + doc.getName());
        if (file.exists()) {
            file.delete();
        }
    }
}
