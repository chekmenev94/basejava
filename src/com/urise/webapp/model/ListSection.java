package com.urise.webapp.model;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;

public class ListSection extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<String> list;

    public ListSection(String... args) {
        this(Arrays.asList(args));
    }

    public ListSection(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "list=" + list +
                '}';
    }
}

