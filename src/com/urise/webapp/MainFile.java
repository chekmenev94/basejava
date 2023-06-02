package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

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
        getPrintFile(dir, "");



    }
    public static void getPrintFile(File dir, String indent) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println(indent + "Directory: " + file.getName());
                    getPrintFile(file, indent + "   ");
                } else {
                    System.out.println(indent + "File: " + file.getName());
                }
            }
        }

    }
}
