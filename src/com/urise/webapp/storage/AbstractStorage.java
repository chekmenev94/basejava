package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;


public abstract class AbstractStorage implements Storage {
    public final void save(Resume r) {
        Object searchKey = getNotExistedKey(r.getUuid());
        doSave(searchKey, r);
    }

    public final void update(Resume r) {
        Object searchKey = getExistedKey(r.getUuid());
        doUpdate(searchKey, r);
    }

    public final void delete(String uuid) {
        Object searchKey = getExistedKey(uuid);
        doDelete(searchKey, uuid);
    }

    public final Resume get(String uuid) {
        Object searchKey = getExistedKey(uuid);
        return doGet(searchKey);

    }

    private Object getExistedKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isFound(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isFound(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Object getSearchKey(String uuid);
    protected abstract boolean isFound(Object searchKey);
    protected abstract void doUpdate(Object searchKey, Resume r);
    protected abstract void doSave(Object searchKey, Resume r);
    protected abstract void doDelete(Object searchKey, String uuid);
    protected abstract Resume doGet(Object searchKey);
}
