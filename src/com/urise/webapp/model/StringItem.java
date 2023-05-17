package com.urise.webapp.model;

public class StringItem extends AbstractItem {
    private final String information;

    public StringItem(String information) {
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
