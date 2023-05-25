package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    protected final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private static final Resume r1 = new Resume(UUID_1, "Oleg");
    private static final Resume r2 = new Resume(UUID_2, "Dasha");
    private static final Resume r3 = new Resume(UUID_3, "Dima");

    public static final String UUID_NOT_EXIST = "dummy";
    private static final Resume r4 = new Resume(UUID_NOT_EXIST, "Dummy");

    static {
        r1.setContact(ContactType.NUMBER, "8-800-555-88-55");
        r1.setContact(ContactType.SKYPE, "skype:grigory.kislin");
        r1.setContact(ContactType.EMAIL, "gkislin@yandex.ru");
        r1.setSection(SectionType.OBJECTIVE,
                new TextSection("Ведущий стажировок и корпоративного обучения по Java"));
        r1.setSection(SectionType.PERSONAL,
                new TextSection("Аналитический склад ума, сильная логика, креативность"));

        r1.setSection(SectionType.ACHIEVEMENT,
                new ListSection(Arrays.asList("Организация команды и успешная реализация" +
                        " Java проектов для сторонних заказчиков", "Реализация двухфакторной аутентификации " +
                        "для онлайн платформы", "Реализация c нуля Rich Internet Application приложения")));

        r1.setSection(SectionType.QUALIFICATIONS,
                new ListSection(Arrays.asList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce")));

        r1.setSection(SectionType.EXPERIENCE,
                new CompanySection(Arrays.asList(new Company("Java Online Projects", "https://javaops.ru",
                                List.of(new Period("Автор проекта", LocalDate.of(2013, 10, 1),
                                        LocalDate.now(), "Создание, организация и проведение Java онлайн проектов и стажировок"))),
                        new Company("Wrike", "https://www.wrike.com", List.of(
                                new Period("Старший разработчик (backend)", LocalDate.of(2014, Month.OCTOBER, 1),
                                        LocalDate.of(2016, 1, 1), "Проектирование и " +
                                        "разработка онлайн"))))));

        r1.setSection(SectionType.EDUCATION, new CompanySection(List.of(new Company("Санкт-Петербургский " +
                "национальный исследовательский университет информационных технологий, механики и оптики", "https://itmo.ru",
                Arrays.asList(new Period("Инженер (программист Fortran, C)", LocalDate.of(1987, 9, 1),
                        LocalDate.of(1993, 7, 1), null), new Period("Аспирантура (программист С, С++)",
                        LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1),
                        null))))));
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Resume[] expected = new Resume[0];
        assertArrayEquals(expected, storage.getAllSorted().toArray());
    }

    @Test
    public void save() {
        storage.save(r4);
        assertGet(r4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(r1);
    }


    @Test
    public void update() {
        Resume newResume = new Resume("uuid2", "Dasha2");
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(r4);
    }

    @Test
    public void delete() {
        assertSize(3);
        storage.delete(UUID_1);
        assertSize(2);
    }
    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
    }

    @Test
    public void get() {
        assertGet(r1);
        assertGet(r2);
        assertGet(r3);

    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void getAllSorted() {
        List<Resume> actual = Arrays.asList(r1, r2, r3);
        actual.sort(AbstractStorage.RESUME_COMPARATOR);
        assertArrayEquals(actual.toArray(), storage.getAllSorted().toArray());
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Resume expected = storage.get(resume.getUuid());
        assertEquals(expected, resume);
    }
}