package com.urise.webapp.model;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Company {
    private final String name;
    private final String website;
    private final List<Period> period;

    public Company(String name, String website, Period...args) {
        this(name, website, Arrays.asList(args));
    }

    public Company(String name, String website, List<Period> period) {
        this.name = name;
        this.website = website;
        this.period = period;
    }

    public String getName() {
        return name;
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

        if (!Objects.equals(name, company.name)) return false;
        if (!Objects.equals(website, company.website)) return false;
        return Objects.equals(period, company.period);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", period=" + period +
                '}';
    }
}
