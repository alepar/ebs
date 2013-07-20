package ru.alepar.ebs;

import com.google.common.collect.Maps;

import java.util.Map;

public class KeyGrouper<K extends GroupKey, I, O> implements Grouper<K, I, O> {

    private final Map<K, Group<I, O>> result = Maps.newHashMap();

    private final GroupKeyFactory<K, I> groupKeyFactory;
    private final GroupFactory<I, O> groupFactory;

    public KeyGrouper(GroupKeyFactory<K, I> groupKeyFactory, GroupFactory<I, O> groupFactory) {
        this.groupKeyFactory = groupKeyFactory;
        this.groupFactory = groupFactory;
    }

    @Override
    public Map<K, Group<I, O>> group(Iterable<I> inputs) {
        for (I input : inputs) {
            final K key = groupKeyFactory.create(input);

            Group<I, O> group = result.get(key);
            if (group == null) {
                group = groupFactory.create();
                result.put(key, group);
            }

            group.add(input);
        }
        return result;
    }
}
