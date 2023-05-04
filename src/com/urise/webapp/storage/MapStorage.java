package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        for (Map.Entry<String, Resume> search : mapStorage.entrySet()) {
            String uuidSearch = search.getValue().getUuid();
            if (uuidSearch.equals(uuid)) {
                return search.getKey();
            }
        }
        return null;
    }

    @Override
    protected boolean isFound(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        mapStorage.replace((String) searchKey, r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        if (mapStorage.size() == 10000) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            mapStorage.put(r.getUuid(), r);
        }
    }

    @Override
    protected void doDelete(Object searchKey, String uuid) {
        mapStorage.remove((String) searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return mapStorage.get((String) searchKey);
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }


    @Override
    public Resume[] getAll() {
        return mapStorage.values().toArray(new Resume[mapStorage.size()]);
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
