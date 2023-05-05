package com.urise.webapp.storage;


import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;


public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMITED = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMITED];
    protected int size;

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage[(Integer) searchKey] = r;
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        if (size == STORAGE_LIMITED) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveResume(r, (Integer) searchKey);
            size++;
        }
    }

    @Override
    protected void doDelete(Object searchKey, String uuid) {
        deleteResume((Integer) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }


    protected abstract void deleteResume(int index);

    protected abstract void saveResume(Resume r, int i);

    protected abstract Integer getSearchKey(String uuid);
}
