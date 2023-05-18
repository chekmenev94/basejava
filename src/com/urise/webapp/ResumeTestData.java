package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume resume = new Resume("Grigory Kislin", "uuid1");
        System.out.println(resume);

        resume.setContactInformation(Information.NUMBER, "8-800-555-88-55");
        resume.setContactInformation(Information.SKYPE, "skype:grigory.kislin");
        resume.setContactInformation(Information.EMAIL, "gkislin@yandex.ru");

        for (Information info : Information.values()) {
            System.out.print(info.getTitle() + " : ");
            System.out.println(resume.getContactInformation(info));
        }

        TextSection stringItem = new TextSection("Ведущий стажировок и корпоративного обучения по Java");
        TextSection stringItem1 = new TextSection("Аналитический склад ума, сильная логика, креативность");

        List<String> list = new ArrayList<>();
        list.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков");
        list.add("Реализация двухфакторной аутентификации для онлайн платформы");
        list.add("Реализация c нуля Rich Internet Application приложения");
        ListSection listItem = new ListSection(list);

        List<String> stringList = List.of("Создание, организация и проведение Java онлайн проектов и стажировок.");
//        CompanySection functionalItem = new CompanySection("Java Online Projects", "Автор проекта", stringList);

        resume.setTypeItem(AllNameItem.OBJECTIVE, stringItem);
        resume.setTypeItem(AllNameItem.PERSONAL, stringItem1);

        resume.setTypeItem(AllNameItem.ACHIEVEMENT, listItem);
//        resume.setTypeItem(AllNameItem.EXPERIENCE, functionalItem);

        for (AllNameItem item : AllNameItem.values()) {
            System.out.print(item.getTitle() + " : ");
            System.out.println(resume.getTypeItem(item));
        }
    }
}
