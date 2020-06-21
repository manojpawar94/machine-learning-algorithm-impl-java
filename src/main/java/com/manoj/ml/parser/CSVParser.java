package com.manoj.ml.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author MANOJ PAWAR
 */
public class CSVParser {

    /**
     *
     * @param filepath
     * @param columnIndexes
     * @return
     * @throws IOException
     */
    public static ArrayList<String[]> load(String filepath, int[] columnIndexes) throws IOException {
        int[] linesToSkip = new int[1];
        return load(filepath, columnIndexes, ",", linesToSkip);
    }

    /**
     *
     * @param filepath
     * @param columnIndexes
     * @param delimiter
     * @return
     * @throws IOException
     */
    public static ArrayList<String[]> load(String filepath, int[] columnIndexes, String delimiter) throws IOException {
        int[] linesToSkip = new int[1];
        return load(filepath, columnIndexes, delimiter, linesToSkip);
    }

    /**
     *
     * @param filepath
     * @param columnIndexes
     * @param delimiter
     * @param linesToSkip
     * @return
     * @throws IOException
     */
    public static ArrayList<String[]> load(String filepath, int[] columnIndexes, String delimiter, int[] linesToSkip) throws IOException {
        HashMap<Double, Double> dataset = new HashMap<>();
        int rowIndex = 0;
        int linesToSkipIndex = 0;

        ArrayList<String[]> matrix = new ArrayList<>();
        for (String line : Files.readAllLines(Paths.get(filepath))) {
            if (linesToSkipIndex < linesToSkip.length && rowIndex == linesToSkip[linesToSkipIndex]) {
                linesToSkipIndex++;
                continue;
            }
            String[] matrixRow = new String[columnIndexes.length];
            String[] columns = line.split(delimiter);
            int index = 0;
            for (int position : columnIndexes) {
                matrixRow[index++] = columns[position];
            }
            matrix.add(matrixRow);
            rowIndex++;
        }
        return matrix;
    }

    /**
     *
     * @param matrix
     * @return
     */
    public static HashMap<Double, Double> getInputOutputPoints(ArrayList<String[]> matrix) {
        HashMap<Double, Double> inputOutMapPoints = new HashMap<>();

        matrix.forEach(matrixRow -> {
            double input = 0.0;
            double output = 0.0;

            try {
                input = Double.parseDouble(matrixRow[0].trim());
            } catch (NumberFormatException e) {
            }

            try {
                output = Double.parseDouble(matrixRow[1].trim());
            } catch (NumberFormatException e) {
            }

            inputOutMapPoints.put(input, output);
        });
        return inputOutMapPoints;
    }
}
