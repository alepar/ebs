package ru.alepar.ebs;

public interface GroupFactory<I, O> {
    Group<I, O> create();
}
