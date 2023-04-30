package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private static final Resume r1 = new Resume(UUID_1);
    private static final Resume r2 = new Resume(UUID_2);
    private static final Resume r3 = new Resume(UUID_3);

    public static final String UUID_NOT_EXIST = "dummy";
    private static final Resume r4 = new Resume(UUID_NOT_EXIST);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Resume[] expected = new Resume[0];
        assertArrayEquals(expected, storage.getAll());
    }

    @Test
    public void save() {
        storage.save(r4);
        assertGet(r4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(r1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMITED; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail();
        }
        storage.save(new Resume());
    }

    @Test
    public void update() {
        Resume newResume = new Resume("uuid2");
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(r4);
    }

    @Test
    public void delete() {
        assertSize(3);
        storage.delete(UUID_1);
        assertSize(2);
    }
    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
    }

    @Test
    public void get() {
        assertGet(r1);
        assertGet(r2);
        assertGet(r3);

    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void getAll() {
        Resume[] actual = new Resume[] {r1, r2, r3};
        assertArrayEquals(actual, storage.getAll());
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Resume expected = null;
        for (Resume resume1 : storage.getAll()) {
            if (resume.equals(resume1)) {
                expected = resume1;
                break;
            }
        }
        assertEquals(expected, resume);
    }

}