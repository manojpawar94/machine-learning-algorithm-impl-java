package com.manoj.ml.supervised;

import com.manoj.ml.modal.LinearDataSet;

import java.util.HashMap;
import java.util.List;

/**
 * @author MANOJ PAWAR
 */
public class LinearRegression {

    private LinearDataSet linearDataSet;
    private double slope = 0;
    private double outputIntercept = 0;

    /**
     * @param train
     */
    public LinearRegression(HashMap<Double, Double> train) {
        this.linearDataSet = new LinearDataSet();
        this.train(train);
    }

    /**
     * @param train
     */
    public void train(HashMap<Double, Double> train) {
        linearDataSet.setPoints(train);
        slope = linearDataSet.getLinerSlope();
        outputIntercept = linearDataSet.getOutputIntercept();
    }

    /**
     * @param input
     * @return
     */
    public double predict(double input) {
        return outputIntercept + slope * input;
    }

    /**
     * @param test
     * @return
     */
    public HashMap<Double, Double> predict(List<Double> test) {
        HashMap<Double, Double> predication = new HashMap<>();

        for (double input : test) {
            predication.put(input, predict(input));
        }
        return predication;
    }

    /**
     *
     * @param train
     * @param test
     * @return
     */
    public static HashMap<Double, Double> predict(HashMap<Double, Double> train, List<Double> test) {
        LinearDataSet linearDataSet = new LinearDataSet();
        linearDataSet.setPoints(train);
        double slope = linearDataSet.getLinerSlope();
        double outputIntercept = linearDataSet.getOutputIntercept();

        HashMap<Double, Double> predication = new HashMap<>();

        for (double input : test) {
            predication.put(input, outputIntercept + slope * input);
        }
        return predication;
    }
}
