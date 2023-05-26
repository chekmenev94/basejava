package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {

        String filePath = ".\\.gitignore";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("C:\\Java\\BaseJava\\basejava\\src");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try(FileInputStream fis = new FileInputStream(filePath)) {
            byte[] bytes = new byte[1024];
            int len = fis.read(bytes);
            System.out.print(new String(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getPrintFile(dir);



    }
    public static void getPrintFile(File dir) {
        if (dir.exists()) {
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    System.out.println("Directory : " + file.getName());
                    getPrintFile(file);
                } else {
                    System.out.println("- File : " +  file.getName());
                }
            }
        }

    }
}
