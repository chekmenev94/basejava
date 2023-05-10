package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> storageResume = new HashMap<>();

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(storageResume.values());
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storageResume.get(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storageResume.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        storageResume.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object searchKey, String uuid) {
        storageResume.remove(uuid, (Resume) searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    public void clear() {
        storageResume.clear();
    }

    @Override
    public int size() {
        return storageResume.size();
    }
}
