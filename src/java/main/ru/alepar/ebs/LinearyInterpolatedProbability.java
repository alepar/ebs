package ru.alepar.ebs;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.util.NavigableMap;
import java.util.TreeMap;

public class LinearyInterpolatedProbability implements Probability {

    private final UnivariateFunction function;

    public LinearyInterpolatedProbability(double... values) {
        function = calculateProbabilityFunction(values);
    }

    @Override
    public double valueProbableAtLeastWith(double probability) {
        return function.value(probability);
    }

    private static PolynomialSplineFunction calculateProbabilityFunction(double[] values) {
        final NavigableMap<DoubleCount, DoubleCount> doubleCounts = countDistribution(values);

        final double xvals[] = new double[doubleCounts.size()+1];
        final double yvals[] = new double[doubleCounts.size()+1];

        integrateDistribution(doubleCounts, xvals, yvals, values);

        return new LinearInterpolator().interpolate(xvals, yvals);
    }

    private static void integrateDistribution(NavigableMap<DoubleCount, DoubleCount> doubleCounts, double[] xvals, double[] yvals, double[] values) {
        final double weight = 1.0 / values.length;

        DoubleCount curPoint = doubleCounts.lastKey();

        xvals[0] = 0.0;
        yvals[0] = curPoint.value;

        for (int i = 1; i < yvals.length; i++) {
            xvals[i] = xvals[i-1] + curPoint.count * weight;
            yvals[i] = curPoint.value;

            curPoint = doubleCounts.lowerKey(curPoint);
        }
    }

    private static NavigableMap<DoubleCount, DoubleCount> countDistribution(double[] values) {
        final NavigableMap<DoubleCount, DoubleCount> doubleCounts = new TreeMap<>();

        for (double value : values) {
            DoubleCount doubleCount = doubleCounts.get(new DoubleCount(value));
            if (doubleCount == null) {
                doubleCount = new DoubleCount(value);
                doubleCounts.put(doubleCount, doubleCount);
            }
            doubleCount.count++;
        }
        return doubleCounts;
    }

    private static class DoubleCount implements Comparable<DoubleCount> {

        private final double value;
        private double count;

        private DoubleCount(double value) {
            this.value = value;
        }

        @Override
        public int compareTo(DoubleCount that) {
            return Double.valueOf(value).compareTo(that.value);
        }
    }
}
