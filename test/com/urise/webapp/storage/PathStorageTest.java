package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.ObjectStreamStorage;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(storageDir.getAbsolutePath(), new ObjectStreamStorage()));
    }
}