package com.urise.webapp.model;

import java.io.Serial;

public class TextSection extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String information;

    public TextSection(String information) {
        this.information = information;
    }

    public String getInformation() {
        return information;
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
        return "StringItem{" +
                "information='" + information + '\'' +
                '}';
    }
}
