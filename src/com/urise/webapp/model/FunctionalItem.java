package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FunctionalItem extends AbstractItem {
    private String place;
    private String post;
    private final List<String> information;


    public FunctionalItem(String place, String post, List<String> list) {
        this.place = place;
        this.post = post;
        this.information = list;
    }

    public void setInformationItem(String info) {
        information.add(info);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FunctionalItem that = (FunctionalItem) o;

        if (!Objects.equals(place, that.place)) return false;
        if (!Objects.equals(post, that.post)) return false;
        return Objects.equals(information, that.information);
    }

    @Override
    public int hashCode() {
        int result = place != null ? place.hashCode() : 0;
        result = 31 * result + (post != null ? post.hashCode() : 0);
        result = 31 * result + (information != null ? information.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FunctionalItem{" +
                "place='" + place + '\'' +
                ", post='" + post + '\'' +
                ", information=" + information +
                '}';
    }
}
