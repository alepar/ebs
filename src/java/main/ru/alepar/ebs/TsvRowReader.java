package ru.alepar.ebs;

import java.io.Reader;

public class TsvRowReader implements RowReader {
    public TsvRowReader(Reader reader) {
    }

    @Override
    public Iterable<Tuple> getTuples() {
        throw new RuntimeException("parfale, implement me!");
    }
}
