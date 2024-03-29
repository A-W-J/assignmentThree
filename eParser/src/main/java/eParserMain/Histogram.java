package eParserMain;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

public class Histogram {
	public static double[] convertData(ArrayList<Integer> ions) {
		double[] value = new double[ions.size()];
		for(int i = 0; i < ions.size(); i++) {
			double d = ions.get(i);
			value[i] = d;			
		}
		return value;
	}
	public static JFreeChart drawHistogram(ArrayList<Integer> ions, int bins) {
		double[] value = Histogram.convertData(ions);
		HistogramDataset dataset = new HistogramDataset();
		dataset.setType(HistogramType.RELATIVE_FREQUENCY);
		dataset.addSeries("Histogram", value, bins);
		String plotTitle = "Histogram";
		String xaxis = "number";
		String yaxis = "value";
		PlotOrientation orientation = PlotOrientation.VERTICAL;
		boolean show = false;
		boolean tooltips = false;
		boolean urls = false;
		JFreeChart chart = ChartFactory.createHistogram(plotTitle, xaxis, yaxis, dataset, orientation, show, tooltips, urls);
		return chart;
	}

}
