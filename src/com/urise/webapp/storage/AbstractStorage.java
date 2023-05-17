package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


public abstract class AbstractStorage<T> implements Storage {
    public static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).
            thenComparing(Resume::getUuid);

    private static final Logger LOGGER = Logger.getLogger(AbstractStorage.class.getName());


    public final void save(Resume r) {
        LOGGER.info("Save " + r);
        T searchKey = getNotExistedKey(r.getUuid());
        doSave(searchKey, r);
    }

    public final void update(Resume r) {
        LOGGER.info("Update " + r);
        T searchKey = getExistedKey(r.getUuid());
        doUpdate(searchKey, r);
    }

    public final void delete(String uuid) {
        LOGGER.info("Delete " + uuid);
        T searchKey = getExistedKey(uuid);
        doDelete(searchKey, uuid);
    }

    public final Resume get(String uuid) {
        LOGGER.info("Get " + uuid);
        T searchKey = getExistedKey(uuid);
        return doGet(searchKey);

    }

    private T getExistedKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOGGER.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private T getNotExistedKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOGGER.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOGGER.info("getAllSorted");
        List<Resume> list = doGetAll();
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    protected abstract List<Resume> doGetAll();

    protected abstract T getSearchKey(String uuid);
    protected abstract boolean isExist(T searchKey);
    protected abstract void doUpdate(T searchKey, Resume r);
    protected abstract void doSave(T searchKey, Resume r);
    protected abstract void doDelete(T searchKey, String uuid);
    protected abstract Resume doGet(T searchKey);
}
