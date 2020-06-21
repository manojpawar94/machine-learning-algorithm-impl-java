package com.manoj.ml.utils;

import java.util.HashMap;
import java.util.Map;

public class Evaluation {

    /**
     * Root Mean Square Error
     *
     * @param actual
     * @param predicted
     * @return
     */
    public static double rootMeanSqrError(HashMap<Double, Double> actual, HashMap<Double, Double> predicted) {
        double sumError = 0.0;

        for (Map.Entry<Double, Double> actualEntry : actual.entrySet()) {
            double predictionError = predicted.get(actualEntry.getKey()) - actualEntry.getValue();
            sumError += Math.pow(predictionError, 2);
        }

        double meanError = (double) sumError / actual.size();
        return Math.sqrt(meanError);
    }

}
