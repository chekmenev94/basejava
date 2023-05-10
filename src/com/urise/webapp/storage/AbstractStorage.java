package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;


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
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doGetAll();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> doGetAll();

    protected abstract Object getSearchKey(String uuid);
    protected abstract boolean isExist(Object searchKey);
    protected abstract void doUpdate(Object searchKey, Resume r);
    protected abstract void doSave(Object searchKey, Resume r);
    protected abstract void doDelete(Object searchKey, String uuid);
    protected abstract Resume doGet(Object searchKey);
}
