package com.urise.webapp.model;

public enum AllNameItem {
    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достиженя"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private final String title;

    AllNameItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
