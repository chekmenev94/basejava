package com.urise.webapp.model;

import java.io.Serial;
import java.util.Objects;

public class TextSection extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;

    private String information;

    public TextSection() {
    }

    public TextSection(String information) {
        this.information = information;
    }

    public String getInformation() {
        return information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;

        return Objects.equals(information, that.information);
    }

    @Override
    public int hashCode() {
        return information != null ? information.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "StringItem{" +
                "information='" + information + '\'' +
                '}';
    }
}
