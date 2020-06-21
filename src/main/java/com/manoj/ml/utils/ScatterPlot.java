package com.manoj.ml.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ScatterPlot extends ApplicationFrame {

    private final static int PANEL_WIDTH = 560;
    private final static int PANEL_HEIGHT = 370;

    private XYSeriesCollection dataset;
    private JFreeChart chart;
    private String chartTitle;
    private String xLabel;
    private String yLabel;
    private PlotOrientation plotOrientation;
    private boolean legend = true;
    private boolean tooltips = true;
    private boolean urls = false;
    private boolean domainZoomable = true;
    private boolean rangeZoomable = true;

    public ScatterPlot(String title) {
        super(title);
        dataset = new XYSeriesCollection();
        plotOrientation = PlotOrientation.HORIZONTAL;

    }

    public void plot() {
        plot(PANEL_WIDTH, PANEL_WIDTH);
    }

    public void plot(int width, int height) {
        chart = ChartFactory.createScatterPlot(chartTitle, xLabel, yLabel, dataset, plotOrientation, legend, tooltips, urls);
        ChartPanel chartpanel = new ChartPanel(chart);
        chartpanel.setDomainZoomable(domainZoomable);
        chartpanel.setRangeZoomable(rangeZoomable);
        chartpanel.setPreferredSize(new Dimension(width, height));
        setContentPane(chartpanel);
        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }

    public void addXYSeries(String name, HashMap<Double, Double> dataSeries) {
        XYSeries xySeries = new XYSeries(name);
        for (Map.Entry<Double, Double> entry : dataSeries.entrySet()) {
            xySeries.add(entry.getKey(), entry.getValue());
        }
        dataset.addSeries(xySeries);
    }

}
