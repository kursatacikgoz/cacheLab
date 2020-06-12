package systemProject3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	static Ram ram = new Ram();
	static ArrayList<String[]> traceInputs = new ArrayList<String[]>();
	static Cache L1I;
	static Cache L1D;
	static Cache L2;

	public static void main(String[] args) {

		int S1 = Integer.parseInt(args[1]);
		int E1 = Integer.parseInt(args[3]);
		int b1 = Integer.parseInt(args[5]);

		int S2 = Integer.parseInt(args[7]);
		int E2 = Integer.parseInt(args[9]);
		int b2 = Integer.parseInt(args[11]);

		L1I = new Cache(S1, E1, b1);
		L1D = new Cache(S1, E1, b1);
		L2 = new Cache(S2, E2, b2);

		ram = saveRamFromTxt("ram.txt");

		String trace = args[13];

		readTrace(trace);

		Tracker.followTrace(ram, traceInputs, L1I, L1D, L2);

		System.out.println("L1I-hits:" + Counter.hitL1I + " L1I-misses:" + Counter.missL1I + " L1I-evictions:"
				+ Counter.evictionL1I);
		System.out.println("L1D-hits:" + Counter.hitL1D + " L1D-misses:" + Counter.missL1D + " L1D-evictions:"
				+ Counter.evictionL1D);
		System.out.println(
				"L2-hits:" + Counter.hitL2 + " L2-misses:" + Counter.missL2 + " L2-evictions:" + Counter.evictionL2);

		printCaches();
	}

	private static void printCaches() {
		System.out.println();
		System.out.println("L1I");
		int setSize = L1I.getSets().size();
		for (int i = 0; i < setSize; i++) {
			System.out.println("Set " + i);
			int lineSize = L1I.getSets().get(i).getLines().size();
			for (int j = 0; j < lineSize; j++) {
				int dataSize = L1I.getSets().get(i).getLines().get(j).getDatasSize();
				for (int k = 0; k < dataSize && L1I.getSets().get(i).getLines().get(j).getV() == 1 ; k++) {
					System.out.print(L1I.getSets().get(i).getLines().get(j).getDatas().get(k).getData() + " ");
				}
				System.out.println();
			}

		}
		
		System.out.println("L1D");
		setSize = L1D.getSets().size();
		for (int i = 0; i < setSize; i++) {
			System.out.println("Set " + i);
			int lineSize = L1D.getSets().get(i).getLines().size();
			for (int j = 0; j < lineSize; j++) {
				int dataSize = L1D.getSets().get(i).getLines().get(j).getDatasSize();
				for (int k = 0; k < dataSize && L1D.getSets().get(i).getLines().get(j).getV() == 1 ; k++) {
					System.out.print(L1D.getSets().get(i).getLines().get(j).getDatas().get(k).getData() + " ");
				}
				System.out.println();
			}

		}
		
		
		System.out.println();
		System.out.println("L2");
		setSize = L2.getSets().size();
		for (int i = 0; i < setSize; i++) {
			System.out.println("Set " + i);
			int lineSize = L2.getSets().get(i).getLines().size();
			for (int j = 0; j < lineSize; j++) {
				int dataSize = L2.getSets().get(i).getLines().get(j).getDatasSize();
				for (int k = 0; k < dataSize && L2.getSets().get(i).getLines().get(j).getV() == 1 ; k++) {
					System.out.print(L2.getSets().get(i).getLines().get(j).getDatas().get(k).getData() + " ");
				}
				System.out.println();
			}

		}

	}

	private static void readTrace(String trace) {
		BufferedReader reader;
		try {
			String[] task;
			reader = new BufferedReader(new FileReader(trace));
			String line = reader.readLine();

			while (line != null) {
				task = line.split("[\\p{Punct}\\s]+");
				task[1] = "" + Integer.parseInt(task[1], 16);
				traceInputs.add(task);
				line = reader.readLine();

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static Ram saveRamFromTxt(String string) {

		int adressInDecimal = 0;
		Scanner scan = null;
		try {
			scan = new Scanner(new File(string));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scan.hasNextLine()) {
			Scanner s2 = new Scanner(scan.nextLine());
			while (s2.hasNext()) {
				String s = s2.next();
				ram.addToRam(s, adressInDecimal);
				adressInDecimal++;
			}
		}

		return ram;
	}

}
