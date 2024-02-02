package chatExamples;

import java.io.*;
import java.util.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.*;


public class HistogramExample {
	public static void main(String[] args) {
		double[] value = new double[100];
		Random generator = new Random();
		for(int i = 1;i<100;i++) {
			value[i] = generator.nextDouble();
		}
		int number = 10;
		HistogramDataset dataset = new HistogramDataset();
		dataset.setType(HistogramType.RELATIVE_FREQUENCY);
		dataset.addSeries("Histogram", value, number);
		String plotTitle = "Histogram";
		String xaxis = "number";
		String yaxis = "value";
		PlotOrientation orientation = PlotOrientation.VERTICAL;
		boolean show = false;
		boolean tooltips = false;
		boolean urls = false;
		JFreeChart chart = ChartFactory.createHistogram(plotTitle, xaxis, yaxis, dataset, orientation, show, tooltips, urls);
		int width = 500;
		int height = 300;
		try {
			ChartUtilities.saveChartAsPNG(new File("histogram.png"), chart, width, height);
		}
		catch (IOException e){}
	}
}
