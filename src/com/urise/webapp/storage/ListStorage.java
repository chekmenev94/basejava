package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage {
    private List<Resume> storageList = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storageList.size(); i++) {
            if (storageList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;

    }

    @Override
    protected boolean isFound(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storageList.set((Integer) searchKey, r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        if (storageList.size() == 10000) {
            throw new StorageException("List overflow", r.getUuid() );
        } else {
            storageList.add(r);
        }
    }

    @Override
    protected void doDelete(Object searchKey, String uuid) {
        storageList.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storageList.get((Integer) searchKey);
    }

    public void clear() {
        storageList.clear();
    }

    public Resume[] getAll() {
        Resume[] resumes = new Resume[storageList.size()];

        for (int i = 0; i < storageList.size(); i++) {
            resumes[i] = storageList.get(i);
        }
        return resumes;
    }

    @Override
    public int size() {
        return storageList.size();
    }

}
