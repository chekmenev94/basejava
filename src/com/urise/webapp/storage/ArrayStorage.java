package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(r)) {
                storage[i] = r;
                System.out.println("Резюме " + storage[i] + " обновлено");
                break;
            }
        }
    }

    public void save(Resume r) {
        if (size == 0) {
            storage[size] = r;
        } else {
            int index = search(r.getUuid());
            if (index < size) {
                System.out.println("ERROR");
            } else {
                storage[size] = r;
            }
        }
        if (size < storage.length) {
            size++;
        } else {
            System.out.println("Хранилище заполнено");
        }
    }

    public Resume get(String uuid) {
        return storage[search(uuid)];
    }

    public void delete(String uuid) {
        int index = search(uuid);
        if (index < size) {
            for (int i = index; i <= size; i++) {
                storage[i] = storage[i + 1];
            }
            size--;
            storage[size] = null;
        } else {
            System.out.println("ERROR");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int search(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return size;
    }
}
