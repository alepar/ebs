package ru.alepar.ebs;

public class DoubleKey extends Key<Double> {
    public DoubleKey(String name) {
        super(name);
    }

    @Override
    public Double parse(String val) {
        return Double.valueOf(val);
    }
}
