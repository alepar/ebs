package ru.alepar.ebs;

public abstract class Key<T> {
    public final String name;

    public Key(String name) {
        this.name = name;
    }

    public abstract T parse(String val);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key that = (Key) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
