package com.manoj.ml.modal;

import com.manoj.ml.utils.LinearDistribution;

import java.util.HashMap;
import java.util.stream.Collectors;

/**
 *
 * @author MANOJ PAWAR
 */
public class LinearDataSet {
    private HashMap<Double, Double> points;

    public LinearDataSet() {
        points = new HashMap<>();
    }

    public HashMap<Double, Double> getPoints() {
        return points;
    }

    public void addPoint(Double input, Double output) {
        this.points.put(input, output);
    }

    public void setPoints(HashMap<Double, Double> points) {
        this.points.putAll(points);
    }

    public double getMeanInput() {
        return LinearDistribution.mean(() -> getPoints().keySet().parallelStream());
    }

    public double getVarianceInput() {
        return LinearDistribution.variance(() -> getPoints().keySet().parallelStream(), getMeanInput());
    }

    public double getMeanOutput() {
        return LinearDistribution.mean(() -> getPoints().values().parallelStream());
    }

    public double getVarianceOutput() {
        return LinearDistribution.variance(() -> getPoints().values().parallelStream(), getMeanOutput());
    }

    public double getCovariance() {
        return LinearDistribution.covariance(getPoints().keySet().stream().collect(Collectors.toList()), getMeanInput(), getPoints().values().stream().collect(Collectors.toList()), getMeanOutput());
    }

    public double getLinerSlope() {
        return LinearDistribution.slope(getCovariance(), getVarianceInput());
    }

    public double getOutputIntercept() {
        return LinearDistribution.outputIntercept(getLinerSlope(), getMeanInput(), getMeanOutput());
    }
}
