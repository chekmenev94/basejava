package com.urise.webapp.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String title;
    private String website;
    private List<Period> period;

    public Company() {
    }

    public Company(String name, String website, Period...args) {
        this(name, website, Arrays.asList(args));
    }

    public Company(String name, String website, List<Period> period) {
        this.title = name;
        this.website = website;
        this.period = period;
    }

    public String getTitle() {
        return title;
    }

    public String getWebsite() {
        return website;
    }

    public List<Period> getPeriod() {
        return period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (!Objects.equals(title, company.title)) return false;
        if (!Objects.equals(website, company.website)) return false;
        return Objects.equals(period, company.period);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + title + '\'' +
                ", website='" + website + '\'' +
                ", period=" + period +
                '}';
    }
}
