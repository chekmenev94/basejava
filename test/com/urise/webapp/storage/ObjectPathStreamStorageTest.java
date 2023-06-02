package com.urise.webapp.storage;

import java.io.File;

public class ObjectPathStreamStorageTest extends AbstractStorageTest {

    public ObjectPathStreamStorageTest() {
        super(new ObjectPathStreamStorage(storageDir.getAbsolutePath()));
    }
}