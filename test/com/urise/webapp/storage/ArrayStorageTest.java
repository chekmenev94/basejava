package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayStorageTest extends AbstractArrayStorageTest {


    public ArrayStorageTest() {
        super(new ArrayStorage());
    }
}