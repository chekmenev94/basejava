package com.urise.webapp.storage;


import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

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
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
