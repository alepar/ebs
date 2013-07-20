package ru.alepar.ebs;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class LinearyInterpolatedProbabilityTest {

    @Test
    public void allPointsWithTheSameValueGivesHunderdPercentCertainty() throws Exception {
        final Probability probability = new LinearyInterpolatedProbability(0.5, 0.5, 0.5);

        for (int i=0; i<=10; i++) {
            assertThat(probability.valueProbableAtLeastWith(i / 10.0), equalTo(0.5));
        }
    }

    @Test
    public void twoDifferentPointsGiveFiftyPercentCertainty() throws Exception {
        final Probability probability = new LinearyInterpolatedProbability(0.1, 0.9);

        for (int i=0; i<=5; i++) {
            assertThat("at " + i / 10.0, probability.valueProbableAtLeastWith(i / 10.0), equalTo(0.9));
        }

        for (int i=5; i<10; i++) {
            double value = probability.valueProbableAtLeastWith(i / 10.0);
            assertThat("at " + i / 10.0, value, lessThanOrEqualTo(0.9));
            assertThat("at " + i / 10.0, value, greaterThanOrEqualTo(0.1));
        }

        assertThat("at 1.0", probability.valueProbableAtLeastWith(1.0), closeTo(0.1, 0.0001));
    }

    @Test
    public void moreThanOnePointWithSameValueGivesMoreProbabilityForThisPoint() throws Exception {
        final Probability probability = new LinearyInterpolatedProbability(0.1, 0.1, 0.9);

        for (int i=0; i<=3; i++) {
            assertThat("at " + i / 10.0, probability.valueProbableAtLeastWith(i / 10.0), equalTo(0.9));
        }

        for (int i=3; i<10; i++) {
            double value = probability.valueProbableAtLeastWith(i / 10.0);
            assertThat("at " + i / 10.0, value, lessThanOrEqualTo(0.9));
            assertThat("at " + i / 10.0, value, greaterThanOrEqualTo(0.1));
        }

        assertThat("at 1.0", probability.valueProbableAtLeastWith(1.0), closeTo(0.1, 0.0001));
    }

    private static void printProbability(Probability probability) {
        for (int i=0; i<=10; i++) {
            System.out.println("f(" + i / 10.0 + ") = " + probability.valueProbableAtLeastWith(i / 10.0));
        }
    }
}
