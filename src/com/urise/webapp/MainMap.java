package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.MapStorage;

public class MainMap {
    public static void main(String[] args) {
        MapStorage storage = new MapStorage();

        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");

        storage.save(r1);
        storage.save(r2);
        storage.save(r3);

        System.out.println(storage.size());
    }
}
