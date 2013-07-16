package ru.alepar.ebs;

public class StringKey extends Key<String> {
    public StringKey(String name) {
        super(name);
    }

    @Override
    public String parse(String val) {
        return val;
    }
}
