package ru.alepar.ebs;

import org.junit.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class TsvRowReaderTest {

    private static final String FIXTURE_FILE_1 = "/ru/alepar/ebs/1.tsv";

    private static final Key<String> KEY_1 = new Key<>("col1");
    private static final Key<Double> KEY_3 = new Key<>("col3");

    @Test
    public void readsFileInAccordanceWithConfig() throws Exception {
        final Reader reader = new InputStreamReader(TsvRowReaderTest.class.getResourceAsStream(FIXTURE_FILE_1));
        final RowReader rowReader = new TsvRowReader(reader);

        Tuple tuple;
        final Iterable<Tuple> tuples = rowReader.getTuples();
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
