package ru.alepar.ebs;

import java.util.Set;

public interface Group<I, O> {
    void add(I input);
    Set<O> getValues();
}
