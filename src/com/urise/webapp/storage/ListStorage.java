package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> storageList = new ArrayList<>();

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(storageList);
    }

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
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume r) {
        storageList.set(searchKey, r);
    }

    @Override
    protected void doSave(Integer searchKey, Resume r) {
        if (storageList.size() == 10000) {
            throw new StorageException("List overflow", r.getUuid() );
        } else {
            storageList.add(r);
        }
    }

    @Override
    protected void doDelete(Integer searchKey, String uuid) {
        storageList.remove((searchKey).intValue());
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storageList.get(searchKey);
    }

    public void clear() {
        storageList.clear();
    }

    @Override
    public int size() {
        return storageList.size();
    }

}
