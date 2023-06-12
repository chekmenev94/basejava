package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<String> list;

    public ListSection() {
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "list=" + list +
                '}';
    }
}

