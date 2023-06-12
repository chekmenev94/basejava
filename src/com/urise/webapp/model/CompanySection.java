package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySection extends AbstractSection {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Company> companies;

    public CompanySection() {
    }

    public CompanySection(Company... args) {
        this(Arrays.asList(args));
    }

    public CompanySection(List<Company> list) {
        this.companies = list;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanySection that = (CompanySection) o;

        return Objects.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return companies != null ? companies.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "companies=" + companies +
                '}';
    }
}
