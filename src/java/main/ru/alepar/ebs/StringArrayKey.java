package ru.alepar.ebs;

import java.util.Arrays;

public class StringArrayKey implements GroupKey{

    private final String[] strings;

    public StringArrayKey(String... strings) {
        this.strings = strings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final StringArrayKey that = (StringArrayKey) o;

        return Arrays.equals(strings, that.strings);
    }

    @Override
    public int hashCode() {
        return strings != null ? Arrays.hashCode(strings) : 0;
    }
}
