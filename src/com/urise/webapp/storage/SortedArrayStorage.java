package com.urise.webapp.storage;


import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    protected void sortedDelete(int i) {
        int numElements = size - i - 1;
        System.arraycopy(storage, i + 1, storage, i, numElements);
    }

    protected void sortedSave(Resume r, int i) {
        int index = -i - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
