package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(storageDir.getAbsolutePath(), new XmlStreamSerializer()));
    }
}