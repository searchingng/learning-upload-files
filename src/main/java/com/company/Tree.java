package com.company;

public class Tree {
    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 3, 1, 3, 4, 3};
        System.out.println(haveThree(nums));
    }

    public static boolean haveThree(int[] nums) {
        boolean b = false;
        int c = 0;
        for (int i = 0; i < nums.length; i++){
            if (!b && nums[i] == 3){
                b = true;
                c++;
            }
            if (nums[i] != 3)
                b = false;
        }
        return (c == 3);
    }

}
