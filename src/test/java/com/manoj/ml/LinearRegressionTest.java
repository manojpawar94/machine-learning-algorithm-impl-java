package com.manoj.ml;

import com.manoj.ml.modal.LinearDataSet;
import com.manoj.ml.parser.CSVParser;
import com.manoj.ml.supervised.LinearRegression;
import com.manoj.ml.utils.Evaluation;
import com.manoj.ml.utils.ScatterPlot;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class LinearRegressionTest {

    @Test
    public void checkMean() {
        LinearDataSet dataSet = new LinearDataSet();
        dataSet.addPoint(1.0, 1.0);
        dataSet.addPoint(2.0, 3.0);
        dataSet.addPoint(4.0, 3.0);
        dataSet.addPoint(3.0, 2.0);
        dataSet.addPoint(5.0, 5.0);

        System.out.println("X - Mean: " + dataSet.getMeanInput());
        System.out.println("X - Variance: " + dataSet.getVarianceInput());

        System.out.println();
        System.out.println("Y - Mean: " + dataSet.getMeanOutput());
        System.out.println("Y - Variance: " + dataSet.getVarianceOutput());
        System.out.println();
        System.out.println("Covariance: " + dataSet.getCovariance());
        System.out.println("Linear Slop: " + dataSet.getLinerSlope());

        ArrayList<Double> testInputs = new ArrayList<>();
        testInputs.add(2.4);
        testInputs.add(5.0);
        testInputs.add(7.2);

        HashMap<Double, Double> predictionResult = LinearRegression.predict(dataSet.getPoints(), dataSet.getPoints().keySet().parallelStream().collect(Collectors.toList()));
        predictionResult.forEach((input, output) -> System.out.println("{ 'input': '" + input + "', 'output': '" + output + "'}"));

        System.out.println("Root Mean Error: " + Evaluation.rootMeanSqrError(dataSet.getPoints(), predictionResult));
    }

    @Test
    public void testSampleFile() {
        int[] columnIndexes = {1, 2};
        String filepath = "/Users/manojpawar/NgInova/Projecta/MachineLearning/src/test/resources/weight-height.csv";
        String test = "/Users/manojpawar/NgInova/Projecta/MachineLearning/src/test/resources/weight-height.csv";
        try {
            HashMap<Double, Double> data = CSVParser.getInputOutputPoints(CSVParser.load(filepath, columnIndexes));

            HashMap<Double, Double> testDataSet = CSVParser.getInputOutputPoints(CSVParser.load(test, columnIndexes));
            HashMap<Double, Double> predictionResult = LinearRegression.predict(data, testDataSet.keySet().parallelStream()
                                                                                                .collect(Collectors.toList()));
            //data.forEach((input, output) -> System.out.println("{ 'input': '" + input + "', 'output': '" + output + "'}"));

            System.out.println();

            predictionResult.forEach((input, output) -> System.out.println("{ 'input': '" + input + "', 'output': '" + output + "'}"));
            System.out.println("Root Mean Error: " + Evaluation.rootMeanSqrError(data, predictionResult));


            ScatterPlot scatterPlot = new ScatterPlot("Height Predication vs Weight");
            scatterPlot.addXYSeries("train", data);
            scatterPlot.addXYSeries("test", predictionResult);
            scatterPlot.plot();

            Thread.sleep(50000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
