package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;


public abstract class AbstractPathStorage extends AbstractStorage<Path> {

    private final Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory");
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        Path[] Paths = directory.listPaths();
        if (Paths == null) {
            throw new StorageException("Directory error", null);
        }
        List<Resume> list = new ArrayList<>(Objects.requireNonNull(Paths).length);
        for (Path Path : Paths) {
            list.add(doGet(Path));
        }
        return list;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return new Path(directory, uuid);
    }

    @Override
    protected boolean isExist(Path Path) {
        return Path.exists();
    }

    @Override
    protected void doUpdate(Path Path, Resume r) {
        try {
            doWrite(r, new BufferedOutputStream(new PathOutputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("Write Path error", Path.getName(), e);
        }
    }

    @Override
    protected void doSave(Path Path, Resume r) {
        try {
            Path.createNewPath();
            doWrite(r, new BufferedOutputStream(new PathOutputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("Path save error", Path.getName(), e);
        }
    }

    @Override
    protected void doDelete(Path Path, String uuid) {
        if (!Path.delete()) {
            throw new StorageException("Path delete error", Path.getName());
        }
    }

    @Override
    protected Resume doGet(Path Path) {
        try {
            return doRead(new BufferedInputStream(new PathInputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", Path.getName(), e);
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        Path[] Paths = directory.listPaths();
        if (Paths == null) {
            throw new StorageException("Directory error", null);
        }
        return Paths.length;
    }

    protected abstract Resume doRead(InputStream is) throws IOException;

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;
}
