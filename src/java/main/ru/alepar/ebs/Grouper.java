package ru.alepar.ebs;

import java.util.Map;

public interface Grouper<K extends GroupKey, I, O> {
    Map<K, Group<I, O>> group(Iterable<I> tupleReader);
}
