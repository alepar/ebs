package ru.alepar.ebs;

import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.util.Iterator;
import java.util.Map;

public class TsvTupleReader implements Iterable<Tuple> {

    private final BufferedReader reader;
    private final String[] header;

    public TsvTupleReader(BufferedReader reader) {
        this.reader = reader;

        try {
            final String headerString = reader.readLine();
            if (headerString == null) {
                throw new RuntimeException("empty reader: " + reader);
            }
            header = headerString.split("\t");
        } catch (Exception e) {
            throw new RuntimeException("failed to read header", e);
        }
    }

    @Override
    public Iterator<Tuple> iterator() {
        try {
            return new TupleIterator();
        } catch (Exception e) {
            throw new RuntimeException("failed to create iterator", e);
        }
    }

    private class TupleIterator implements Iterator<Tuple> {

        private boolean readCurrent;
        private String current;

        @Override
        public boolean hasNext() {
            return current() != null;
        }

        @Override
        public Tuple next() {
            final String current = current();
            advance();
            final String[] values = current.split("\t");
            final Map<String, String> entries = Maps.newHashMap();

            if (values.length != header.length) {
                throw new RuntimeException("string had invalid number of columns (!= header.length): " + current);
            }

            for (int i = 0; i < header.length; i++) {
                entries.put(header[i], values[i]);
            }

            return new Tuple(entries);
        }

        private void advance() {
            readCurrent = false;
        }

        private String current() {
            try {
                if(!readCurrent) {
                    current = reader.readLine();
                    readCurrent = true;
                }
                return current;
            } catch (Exception e) {
                throw new RuntimeException("failed to read next line", e);
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
