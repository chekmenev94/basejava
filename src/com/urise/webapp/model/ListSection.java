package com.urise.webapp.model;

import java.util.List;

public class ListSection extends AbstractSection {
    private final List<String> list;

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

