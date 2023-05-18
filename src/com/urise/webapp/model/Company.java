package com.urise.webapp.model;

import java.time.Period;
import java.util.List;
import java.util.Objects;

public class Company {
    private final String title;
    private final String website;
    private final List<Period> period;
    private final String description;

    public Company(String name, String web, List<Period> period, String description) {
        this.title = name;
        this.website = web;
        this.period = period;
        this.description = description;
    }

    public String getName() {
        return title;
    }

    public String getWebsite() {
        return website;
    }

    public List<Period> getPeriod() {
        return period;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (!Objects.equals(title, company.title)) return false;
        if (!Objects.equals(website, company.website)) return false;
        if (!Objects.equals(period, company.period)) return false;
        return Objects.equals(description, company.description);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "title='" + title + '\'' +
                ", website='" + website + '\'' +
                ", period=" + period +
                ", description='" + description + '\'' +
                '}';
    }
}
