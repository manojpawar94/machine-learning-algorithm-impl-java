package com.manoj.ml.utils;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author MANOJ PAWAR
 */
public class LinearDistribution {

    ;

    /**
     * It calculate the mean of provided values. The mean is sum of all values divided by total elements.
     * <p>
     * Formula: mean(x) = sum(x) / count(x)
     *
     * @param values
     * @return
     */
    public static double mean(Supplier<Stream<Double>> values) {
        return values.get().mapToDouble(Double::doubleValue).sum() / values.get().count();
    }

    /**
     * It calculate the variance of provided values. The variance is sum difference of each value from its mean value.
     * <p>
     * Formula: variance = sum ( ( x - mean(x) ) ^ 2 )
     *
     * @param values Values Series
     * @param mean   Mean of Series
     * @return It returns the Variance of Series
     */
    public static double variance(Supplier<Stream<Double>> values, double mean) {
        return values.get().map(value -> Math.pow((value - mean), 2)).mapToDouble(Double::doubleValue).sum();
    }

    /**
     * Formula: covariance = sum((x(i) - mean(x)) * (y(i) - mean(y)))
     *
     * @param inputValues
     * @param inputMean
     * @param outputValues
     * @param outputMean
     * @return
     */
    public static double covariance(List<Double> inputValues, double inputMean, List<Double> outputValues, double outputMean) {
        double covariance = 0.0;
        for (int elementIndex = 0; elementIndex < inputValues.size(); elementIndex++) {
            covariance += (inputValues.get(elementIndex) - inputMean) * (outputValues.get(elementIndex) - outputMean);
        }
        return covariance;
    }

    /**
     * Formula:
     * slope [m] = sum((x(i) - mean(x)) * (y(i) - mean(y))) / sum( (x(i) - mean(x) ) ^  2 )
     *
     * @param inputValues
     * @param inputMean
     * @param outputValues
     * @param outputMean
     * @return
     */
    public static double slope(List<Double> inputValues, double inputMean, List<Double> outputValues, double outputMean) {
        double covariance = 0.0;
        for (int elementIndex = 0; elementIndex < inputValues.size(); elementIndex++) {
            covariance += (inputValues.get(elementIndex) - inputMean) * (outputValues.get(elementIndex) - outputMean);
        }
        return covariance / inputValues.parallelStream().map(value -> Math.pow((value - inputMean), 2)).mapToDouble(Double::doubleValue).sum();
    }

    /**
     * Formula:
     * slope [m] = sum((x(i) - mean(x)) * (y(i) - mean(y))) / sum( (x(i) - mean(x) ) ^  2 )
     * slope [m] = covariance(x, y) / variance(x)
     *
     * @param covariance
     * @param inputVariance
     * @return
     */
    public static double slope(double covariance, double inputVariance) {
        return covariance / inputVariance;
    }

    /**
     * Formula: Output-Intercept = mean(y) - Slope[m] * mean(x)
     *
     * @param slope
     * @param inputMean
     * @param outputMean
     * @return
     */
    public static double outputIntercept(double slope, double inputMean, double outputMean) {
        return outputMean - slope * inputMean;
    }
}
