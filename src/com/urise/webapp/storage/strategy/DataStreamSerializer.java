package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamStrategy{
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {

            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            Map<ContactType, String> contact = r.getContact();
            dos.writeInt(contact.size());
            for (Map.Entry<ContactType, String> entry : contact.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, AbstractSection> section = r.getSection();
            dos.writeInt(section.size());
            for (Map.Entry<SectionType, AbstractSection> entry : section.entrySet()) {
                SectionType type = entry.getKey();
                AbstractSection abstractSection = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) abstractSection).getInformation());
                    case ACHIEVEMENT, QUALIFICATIONS -> setCollection(dos, (((ListSection) abstractSection).getList()));
                    case EXPERIENCE, EDUCATION -> {
                        List<Company> companyList = (((CompanySection) abstractSection).getCompanies());
                        dos.writeInt(companyList.size());
                        for (int i = 0; i < companyList.size(); i++) {
                            dos.writeUTF(companyList.get(i).getTitle());
                            dos.writeUTF(companyList.get(i).getWebsite());
                            List<Period> periodList = companyList.get(i).getPeriod();
                            dos.writeInt(periodList.size());
                            for (int j = 0; j < periodList.size(); j++) {
                                dos.writeUTF(periodList.get(j).getTitle());
                                LocalDate(dos, periodList.get(j).getStartDate());
                                LocalDate(dos, periodList.get(j).getEndDate());
                                dos.writeUTF(periodList.get(j).getDescription());
                            }
                        }
                    }
                }
            }

        }
    }

    private <T> void setCollection(DataOutputStream dos, List<T> list) throws IOException {
        dos.writeInt(list.size());
        for (T elem : list) {
            dos.writeUTF((String) elem);
        }
    }

    private void LocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonth().getValue());
    }
    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String name = dis.readUTF();
            Resume resume = new Resume(uuid, name);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sizeSection = dis.readInt();
            for (int i = 0; i < sizeSection; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                AbstractSection abstractSection = readSection(dis, sectionType);
                resume.addSection(sectionType, abstractSection);
            }
            return resume;
        }
    }



    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL, OBJECTIVE -> {
                return new TextSection(dis.readUTF());
            }
            case ACHIEVEMENT, QUALIFICATIONS -> {
                int lisSize = dis.readInt();
                List<String> list = new ArrayList<>(lisSize);
                for (int i = 0; i < lisSize; i++) {
                    list.add(dis.readUTF());
                }
                return new ListSection(list);
            }
            case EXPERIENCE, EDUCATION -> {
                int companySize = dis.readInt();
                List<Company> companyList = new ArrayList<>(companySize);
                for (int i = 0; i < companySize; i++) {
                    String title = dis.readUTF();
                    String webSite = dis.readUTF();
                    int periodSize = dis.readInt();
                    List<Period> periodList = new ArrayList<>(periodSize);
                    for (int j = 0; j < periodSize; j++) {
                        String title1 = dis.readUTF();
                        LocalDate ldStart = readLocalDate(dis);
                        LocalDate ldEnd = readLocalDate(dis);
                        String description = dis.readUTF();
                        periodList.add(new Period(title1, ldStart, ldEnd, description));
                    }
                    companyList.add(new Company(title, webSite, periodList));
                }
                return new CompanySection(companyList);
            }
            default -> throw new IllegalStateException();
        }
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

}
