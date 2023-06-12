package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializer implements StreamStrategy {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contact = r.getContact();
            writeCollect(dos, contact.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            Map<SectionType, AbstractSection> section = r.getSection();
            writeCollect(dos, section.entrySet(), entry -> {
                SectionType type = entry.getKey();
                AbstractSection abstractSection = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) abstractSection).getInformation());
                    case ACHIEVEMENT, QUALIFICATIONS -> writeCollect(dos, (((ListSection) abstractSection).getList()),
                            dos::writeUTF);
                    case EXPERIENCE, EDUCATION -> writeCollect(dos, (((CompanySection) abstractSection).getCompanies()),
                            companies -> {
                                dos.writeUTF(companies.getTitle());
                                dos.writeUTF(companies.getWebsite());
                                writeCollect(dos, companies.getPeriod(), period -> {
                                    dos.writeUTF(period.getTitle());
                                    LocalDate(dos, period.getStartDate());
                                    LocalDate(dos, period.getEndDate());
                                    dos.writeUTF(period.getDescription());
                                });

                            });
                }
            });
        }
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

    private <T> void writeCollect(DataOutputStream dos, Collection<T> collection, WriteSection<T> writeSection) throws IOException {
        Objects.requireNonNull(collection);
        dos.writeInt(collection.size());
        for (T t : collection) {
            writeSection.write(t);
        }
    }

    private interface WriteSection<T> {
        void write(T t) throws IOException;
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private void LocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonth().getValue());
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL, OBJECTIVE -> {
                return new TextSection(dis.readUTF());
            }
            case ACHIEVEMENT, QUALIFICATIONS -> {
                return new ListSection(readCollect(dis, dis::readUTF));
            }
            case EXPERIENCE, EDUCATION -> {
                return new CompanySection(readCollect(dis, () -> new Company(
                        dis.readUTF(), dis.readUTF(), readCollect(dis, () -> new Period(dis.readUTF(), readLocalDate(dis),
                        readLocalDate(dis), dis.readUTF()))
                )));
            }
            default -> throw new IllegalStateException();
        }
    }

    private interface ReadSection<T> {
        T read() throws IOException;
    }

    private <T> List<T> readCollect(DataInputStream dis, ReadSection<T> readSection) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(readSection.read());
        }
        return list;
    }

}
