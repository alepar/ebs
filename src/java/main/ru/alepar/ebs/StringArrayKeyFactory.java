package ru.alepar.ebs;

public class StringArrayKeyFactory implements GroupKeyFactory<StringArrayKey, Tuple> {

    private final Key<?>[] keys;

    public StringArrayKeyFactory(Key<?>... keys) {
        this.keys = keys;
    }

    @Override
    public StringArrayKey create(Tuple tuple) {
        final String values[] = new String[keys.length];

        for (int i = 0; i < keys.length; i++) {
            final Key<?> key = keys[i];
            values[i] = tuple.getRaw(key);
        }

        return new StringArrayKey(values);
    }
}
