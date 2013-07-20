package ru.alepar.ebs;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TsvTupleReaderTest {

    private static final String FIXTURE_FILE_1 = "/ru/alepar/ebs/TsvTupleReaderTest.1.tsv";
    private static final String FIXTURE_FILE_2 = "/ru/alepar/ebs/TsvTupleReaderTest.2.tsv";

    private static final Key<String> KEY_1 = new StringKey("col1");
    private static final Key<Double> KEY_3 = new DoubleKey("col3");

    @Test
    public void keysReturnValuesFromMatchingColumns() throws Exception {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(TsvTupleReaderTest.class.getResourceAsStream(FIXTURE_FILE_1)));

        Tuple tuple;
        final Iterable<Tuple> tuples = new TsvTupleReader(reader);
        final Iterator<Tuple> it = tuples.iterator();

        assertThat(it.hasNext(), equalTo(true));
        tuple = it.next();
        assertThat(tuple.get(KEY_1), equalTo("val11"));
        assertThat(tuple.get(KEY_3), equalTo(13.0));

        assertThat(it.hasNext(), equalTo(true));
        tuple = it.next();
        assertThat(tuple.get(KEY_1), equalTo("val21"));
        assertThat(tuple.get(KEY_3), equalTo(23.0));

        assertThat(it.hasNext(), equalTo(false));
    }

    @Test
    public void fileWithDifferentColumnOrderingWorksToo() throws Exception {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(TsvTupleReaderTest.class.getResourceAsStream(FIXTURE_FILE_2)));

        Tuple tuple;
        final Iterable<Tuple> tuples = new TsvTupleReader(reader);
        final Iterator<Tuple> it = tuples.iterator();

        assertThat(it.hasNext(), equalTo(true));
        tuple = it.next();
        assertThat(tuple.get(KEY_1), equalTo("val11"));
        assertThat(tuple.get(KEY_3), equalTo(13.0));

        assertThat(it.hasNext(), equalTo(true));
        tuple = it.next();
        assertThat(tuple.get(KEY_1), equalTo("val21"));
        assertThat(tuple.get(KEY_3), equalTo(23.0));

        assertThat(it.hasNext(), equalTo(false));
    }
}
