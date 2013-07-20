package ru.alepar.ebs;

import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.Set;

public class SimpleGroup<T> implements Group<T, T> {

    private final Set<T> values = Sets.newHashSet();

    @Override
    public void add(T input) {
        values.add(input);
    }

    @Override
    public Set<T> getValues() {
        return Collections.unmodifiableSet(values);
    }
}

