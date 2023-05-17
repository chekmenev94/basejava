package com.urise.webapp.model;

public enum Information {
    NUMBER("Телефон"),
    SKYPE("Skype"),
    EMAIL("Почта"),
    PROFILE("Профиль LinkedIn"),
    PROFILE2("Профиль GitHub"),
    PROFILE3("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private final String title;

    Information(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
