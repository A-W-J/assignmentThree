package eParserMain;

import java.util.Hashtable;
import java.io.IOException;

public class FragmentMasses {
	//let's add some more information here
	final int[] bIons;
	final int[] yIons;
	
	public FragmentMasses(int[] bIons, int[] yIons) {
		super();
		this.bIons = bIons;
		this.yIons = yIons;
	}
	
	public int[] getbIons() {
		return bIons;
	}

	public int[] getyIons() {
		return yIons;
	}

	public static String[] buildFragments(String sequence){
		String[] fragmentList = new String[sequence.length()];
		int counter = 0;
		for (int i = 1; i <= sequence.length(); i++) {
			String fragment = sequence.substring(0, i);
			fragmentList[counter] = fragment;
			counter = counter + 1;
		}
		return fragmentList;
	}
	
	public static int getFragMass(String frag, boolean flag){
		//loading in the table of amino acids
		//AminoTable aminoTable = AminoTable.makeAminoLookup();
		//HashMaps are normally faster than Hashtables
		Hashtable<Character, Integer> aminoTable = AminoTable.makeAminoTable();
		//we can loop over the string
		//instead of summing a list, we can just add each residue mass
		int fragMass = 0;
		for(char residue:frag.toCharArray()) {
			int residueMass = aminoTable.get(residue);
			fragMass = fragMass + residueMass;
		}
		//int fragMass = Math.round(fragMass);
		if(flag == true) {
			fragMass += 18;
			return fragMass;
		}
		fragMass += 1;
		return fragMass;
	}
	
	public static int[] buildMassList(String sequence, boolean flag) {
		//this will be our output array
		int[] fragmentMassList = new int[sequence.length()]; 
		String[] fragmentList = FragmentMasses.buildFragments(sequence);
		int counter = 0;
		for(String frag:fragmentList) {
			int mass = FragmentMasses.getFragMass(frag, flag);
			fragmentMassList[counter] = mass;
			counter = counter + 1;
		}
		return fragmentMassList;
	}
	
	//this is highly inefficient; look for a better method of this process
	public static String seqReverse(String forwardSeq) {
		String nstr = "";
		char ch;
		for (int i = 0; i < forwardSeq.length(); i++) {
			ch = forwardSeq.charAt(i);
			nstr = ch + nstr;
		}
		return nstr;
	}
	
	public static FragmentMasses dataAssembly(String sequence) throws IOException{
		//here, the boolean indicates whether or not we have a b-ion or a y-ion,
		//this dictates whether or not we add +18 or +1 to the mass
		//b-ion series
		boolean bFlag = false;
		int[] bIons = FragmentMasses.buildMassList(sequence, bFlag);
		//y-ion series
		String revSequence = FragmentMasses.seqReverse(sequence);
		boolean yFlag = true;
		int[] yIons = FragmentMasses.buildMassList(revSequence, yFlag);
		return new FragmentMasses(bIons, yIons);
	}
}

