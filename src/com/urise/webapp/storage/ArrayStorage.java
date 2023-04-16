package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {
    private final Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == size) {
            System.out.println("Резюме нет в списке");
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == size) {
            storage[size] = r;
            size++;
        } else if (index == storage.length) {
            System.out.println("Хранилище заполнено");
        } else {
            System.out.println("Данное резюме уже есть");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < size) {
            return storage[index];
        } else {
            System.out.println("Данного резюме нет в списке");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < size) {
            for (int i = index; i <= size; i++) {
                storage[i] = storage[i + 1];
            }
            size--;
            storage[size] = null;
        } else {
            System.out.println("Данного резюме нет");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return size;
    }
}
