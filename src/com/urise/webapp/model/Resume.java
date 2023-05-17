package com.urise.webapp.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {
    private final String uuid;
    private final String fullName;

    private final Map<Information, String> contactInformation = new EnumMap<>(Information.class);
    private final Map<AllNameItem, AbstractItem> typeItem = new EnumMap<>(AllNameItem.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid mast be not null");
        Objects.requireNonNull(fullName, "fullName mast be not null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public void setContactInformation(Information info, String str) {
        contactInformation.put(info, str);
    }

    public void setTypeItem(AllNameItem type, AbstractItem instance) {
        typeItem.put(type, instance);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContactInformation(Information info) {
        return contactInformation.get(info);
    }
    public AbstractItem getTypeItem(AllNameItem info) {
        return typeItem.get(info);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Id = " + uuid + ", Full name = " + fullName;
    }
}
