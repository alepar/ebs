package ru.alepar.ebs;

public class TupleGroupFactory implements GroupFactory<Tuple, Tuple> {
    @Override
    public Group<Tuple, Tuple> create() {
        return new SimpleGroup<>();
    }
}
