package com.urise.webapp.storage;


import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> comparator = Comparator.comparing(Resume::getUuid);
    @Override
    protected List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    protected void deleteResume(int index) {
        int numElements = size - index - 1;
        System.arraycopy(storage, index + 1, storage, index, numElements);
    }

    protected void saveResume(Resume r, int i) {
        int index = -i - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }


    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "noName");
        return Arrays.binarySearch(storage, 0, size, searchKey, comparator);
    }
}
