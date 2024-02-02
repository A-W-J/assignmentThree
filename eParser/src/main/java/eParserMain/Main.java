package eParserMain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

public class Main {
	public static void main(String[] args) throws IOException {
		//read sequences from a file
		String seqFilename = "C:\\Users\\alexw\\Documents\\Ohio State\\Searle Lab\\Projects\\assignmentThree\\ecoli_tryptic_peptide_sequences.txt";
		String[] seqs = SeqArray.readSequences(seqFilename);
		//initialize global b-ion set
		ArrayList<Integer> bIons = new ArrayList<Integer>();
		//initialize global y-ion set
		ArrayList<Integer> yIons = new ArrayList<Integer>();
		//iterate over each sequence
		for(String sequence:seqs) {
			//for each sequence, create a FragmentMass object
			FragmentMasses frags = FragmentMasses.dataAssembly(sequence);
			//iterate over the b-ion fragment masses
			for(int bIon:frags.getbIons()) {
				if(bIon < 500) {
					bIons.add(bIon); //if a mass is less than 500 m/z, place it in the global b-ion set
				}
			}
			//iterate over the y-ion fragment masses
			for(int yIon:frags.getyIons()) {
				if(yIon < 500) {
					yIons.add(yIon); //if a mass is less than 500 m/z, place it in the global y-ion set
				}
			}
		}
		int width = 500;
		int height = 300;
		//make a histogram for b-ions
		JFreeChart bHist = Histogram.drawHistogram(bIons, 10);
		try {
			ChartUtilities.saveChartAsPNG(new File("b_ion_histogram.png"), bHist, width, height);
		}
		catch (IOException e){}
		//make a histogram for y-ions
		JFreeChart yHist = Histogram.drawHistogram(yIons, 10);
		try {
			ChartUtilities.saveChartAsPNG(new File("y_ion_histogram.png"), yHist, width, height);
		}
		catch (IOException e){}
	}
}

