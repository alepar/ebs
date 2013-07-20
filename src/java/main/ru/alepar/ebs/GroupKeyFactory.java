package ru.alepar.ebs;

public interface GroupKeyFactory<K, V> {
    K create(V value);
}
