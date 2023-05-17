package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private static final Resume r1 = new Resume(UUID_1, "Oleg");
    private static final Resume r2 = new Resume(UUID_2, "Dasha");
    private static final Resume r3 = new Resume(UUID_3, "Dima");

    public static final String UUID_NOT_EXIST = "dummy";
    private static final Resume r4 = new Resume(UUID_NOT_EXIST, "Dummy");

    public AbstractStorageTest(Storage storage) {
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
        assertArrayEquals(expected, storage.getAllSorted().toArray());
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


    @Test
    public void update() {
        Resume newResume = new Resume("uuid2", "Dasha2");
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
    public void getAllSorted() {
        List<Resume> actual = Arrays.asList(r1, r2, r3);
        actual.sort(AbstractStorage.RESUME_COMPARATOR);
        assertArrayEquals(actual.toArray(), storage.getAllSorted().toArray());
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Resume expected = storage.get(resume.getUuid());
        assertEquals(expected, resume);
    }
}