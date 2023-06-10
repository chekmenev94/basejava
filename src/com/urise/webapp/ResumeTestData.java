package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume r1 = new Resume("Grigory Kislin", "uuid1");
        System.out.println(r1);
        resumeCompletion(r1);

//        r1.setContact(ContactType.NUMBER, "8-800-555-88-55");
//        r1.setContact(ContactType.SKYPE, "skype:grigory.kislin");
//        r1.setContact(ContactType.EMAIL, "gkislin@yandex.ru");

        for (ContactType info : ContactType.values()) {
            if (r1.getContact(info) != null) {
                System.out.print(info.getTitle() + " : ");
                System.out.println(r1.getContact(info));
            }
        }

//        r1.setSection(SectionType.OBJECTIVE,
//                new TextSection("Ведущий стажировок и корпоративного обучения по Java"));
//        r1.setSection(SectionType.PERSONAL,
//                new TextSection("Аналитический склад ума, сильная логика, креативность"));
//
//        r1.setSection(SectionType.ACHIEVEMENT,
//                new ListSection(Arrays.asList("Организация команды и успешная реализация" +
//                        " Java проектов для сторонних заказчиков", "Реализация двухфакторной аутентификации " +
//                        "для онлайн платформы", "Реализация c нуля Rich Internet Application приложения")));
//
//        r1.setSection(SectionType.QUALIFICATIONS,
//                new ListSection(Arrays.asList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
//                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce")));
//
//        r1.setSection(SectionType.EXPERIENCE,
//                new CompanySection(Arrays.asList(new Company("Java Online Projects", "https://javaops.ru",
//                                List.of(new Period("Автор проекта", LocalDate.of(2013, 10, 1),
//                                        LocalDate.now(), "Создание, организация и проведение Java онлайн проектов и стажировок"))),
//                        new Company("Wrike", "https://www.wrike.com", List.of(
//                                new Period("Старший разработчик (backend)", LocalDate.of(2014, Month.OCTOBER, 1),
//                                        LocalDate.of(2016, 1, 1), "Проектирование и " +
//                                        "разработка онлайн"))))));
//
//        r1.setSection(SectionType.EDUCATION, new CompanySection(List.of(new Company("Санкт-Петербургский " +
//                "национальный исследовательский университет информационных технологий, механики и оптики", "https://itmo.ru",
//                Arrays.asList(new Period("Инженер (программист Fortran, C)", LocalDate.of(1987, 9, 1),
//                        LocalDate.of(1993, 7, 1), null), new Period("Аспирантура (программист С, С++)",
//                        LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1),
//                        null))))));

        for (SectionType item : SectionType.values()) {
            System.out.print(item.getTitle() + " : ");
            System.out.println(r1.getSection(item));
        }

    }

    public static void resumeCompletion(Resume r) {
        r.addContact(ContactType.NUMBER, "11111");
        r.addContact(ContactType.SKYPE, "skype.skype");
        r.addContact(ContactType.EMAIL, r.getUuid() + "@yandex.ru");
        r.addContact(ContactType.PROFILE, r.getUuid() + "LinkedId");
        r.addContact(ContactType.PROFILE2, r.getUuid() + "GitHub");
        r.addContact(ContactType.HOMEPAGE, r.getFullName());

        r.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        r.addSection(SectionType.PERSONAL, new TextSection("Personal1"));
        r.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievement1", "Achievement2", "Achievement3"));
        r.addSection(SectionType.QUALIFICATIONS, new ListSection("Qualifications1", "Qualifications2"));
        r.addSection(SectionType.EXPERIENCE, new CompanySection(new Company("Company1",
                "https://company1.ru", new Period("Period1", 2000, Month.OCTOBER, 2002,
                Month.APRIL, "Java programming1")), new Company("Company2",
                "https://company2.ru", new Period("Period2", 2002, Month.APRIL, 20023,
                Month.MARCH, "Java programming2"))));
        r.addSection(SectionType.EDUCATION, new CompanySection(new Company("Education",
                "https://education.ru", new Period("Period11", 1995, Month.SEPTEMBER, 2000,
                Month.MARCH, "null"))));

    }
}
