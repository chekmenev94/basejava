package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        storage.save(new Resume("uuid4"));
        assertEquals(4, storage.size());
        assertSame("uuid4", storage.get("uuid4").toString());
    }

    @Test
    public void update() {
        storage.update(storage.get("uuid1"));
    }

    @Test
    public void delete() {
        storage.delete("uuid1");
        assertEquals(2, storage.size());
        getAllResume(storage.getAll());
    }

    @Test
    public void get() {
        storage.get("uuid1");
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        assertEquals(3, storage.getAll().length);
        storage.delete("uuid1");
        assertEquals(2, storage.getAll().length);
    }

    public void getAllResume(Resume[] r) {
        for (Resume resume : r) {
            System.out.println(resume);
        }
    }
}