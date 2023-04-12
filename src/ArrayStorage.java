import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }


    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
        size++;
    }

    Resume get(String uuid) {
        int i = 0;
        while (!storage[i].uuid.equals(uuid)) {
            i++;
        }
        return storage[i];
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                break;
            }
        }
        for (int i = 0; i < size; i++) {
            if (storage[i] != null) {
                storage[i - 1] = storage[i];
                storage[i] = null;
            }
        }
        size--;
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
