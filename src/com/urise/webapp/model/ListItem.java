package com.urise.webapp.model;

import java.util.List;

public class ListItem extends AbstractItem {
    private final List<String> list;

    public ListItem(List<String> list) {
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

