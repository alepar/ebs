package ru.alepar.ebs;

import java.util.Map;

public class Tuple {

    private final Map<String, String> entries;

    public Tuple(Map<String, String> entries) {
        this.entries = entries;
    }

    public <T> T get(Key<T> key) {
        final String val = getRaw(key);
        if (val == null) {
            return null;
        }

        return key.parse(val);
    }

    public String getRaw(Key<?> key) {
        return entries.get(key.name);
    }
}
