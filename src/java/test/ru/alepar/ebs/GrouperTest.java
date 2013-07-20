package ru.alepar.ebs;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class GrouperTest {

    private static final Key<String> KEY_ASSIGNEE = new StringKey("assignee");
    private static final Key<String> KEY_TYPE = new StringKey("type");
    private static final Key<Double> KEY_ESTIMATE = new DoubleKey("estimate");
    private static final Key<Double> KEY_ACTUAL = new DoubleKey("actual");

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(GrouperTest.class.getResourceAsStream(
            "/ru/alepar/ebs/GrouperTest.1.tsv"
    )));
    private final Iterable<Tuple> tupleReader = new TsvTupleReader(reader);

    @Test
    public void properlyGroups() throws Exception {
        final Grouper<StringArrayKey, Tuple, Tuple> grouper = new KeyGrouper<>(new StringArrayKeyFactory(KEY_ASSIGNEE, KEY_TYPE), new TupleGroupFactory());
        final Map<StringArrayKey, Group<Tuple, Tuple>> groups = grouper.group(tupleReader);

        assertThat(groups.get(new StringArrayKey("AP", "dev")).getValues(), hasSize(3));
        assertThat(groups.get(new StringArrayKey("AP", "test")).getValues(), hasSize(2));
        assertThat(groups.get(new StringArrayKey("VS", "dev")).getValues(), hasSize(1));
    }
}
