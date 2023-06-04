package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.ObjectStreamStorage;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(storageDir, new ObjectStreamStorage()));
    }
}