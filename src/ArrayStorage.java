import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);

    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        Resume get = null;
        for (Resume resume : storage) {
            if (resume.uuid.equals(uuid)) {
                get = resume;
                break;
            }
        }
        return get;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                break;
            }
        }
    }

    Resume[] getAll() {
        int length = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                length++;
            }
        }
        Resume[] copyStorage = new Resume[length];
        int indexCopy = 0;
        int indexStart = 0;
        while (indexCopy < storage.length) {
            int indexSum = 0;
            if (storage[indexCopy] == null) {
                indexCopy++;
            } else {
                while (storage[indexCopy] != null) {
                    indexSum++;
                    indexCopy++;
                }
                indexCopy -= indexSum;
                System.arraycopy(storage, indexCopy, copyStorage, indexStart, indexSum);
                indexStart += indexSum;
                indexCopy += indexSum;
            }
        }
        return copyStorage;
    }

    int size() {
        int length = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                length++;
            }
        }
        return length;
    }
}
