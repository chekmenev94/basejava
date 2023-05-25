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

    private final Map<ContactType, String> contactInformation = new EnumMap<>(ContactType.class);
    private final Map<SectionType, AbstractSection> typeSection = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid mast be not null");
        Objects.requireNonNull(fullName, "fullName mast be not null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public void setContact(ContactType info, String str) {
        contactInformation.put(info, str);
    }

    public void setSection(SectionType type, AbstractSection instance) {
        typeSection.put(type, instance);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContact(ContactType info) {
        return contactInformation.get(info);
    }
    public AbstractSection getSection(SectionType info) {
        return typeSection.get(info);
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
