package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.DataStreamSerializer;


public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(storageDir.getAbsolutePath(), new DataStreamSerializer()));
    }
}