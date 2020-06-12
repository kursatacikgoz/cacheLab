package systemProject3;

import java.util.ArrayList;

public class Tracker { // in int L1I is 1 L1D is 2 and L2 is 3

	static Ram ram;
	static ArrayList<String[]> traceInputs;
	static Cache L1I;
	static Cache L1D;
	static Cache L2;

	public static void followTrace(Ram r, ArrayList<String[]> t, Cache l1i, Cache l1d, Cache l2) {
		L1I = l1i;
		L1D = l1d;
		L2 = l2;

		ram = r;
		traceInputs = t;

		int start;
		int count;
		Line c;
		String temp;
		String setOfL2;
		int check1 = 0, check2 = 0, check3 = 0;
		String setOfL1D;

		for (int i = 0; i < traceInputs.size(); i++) {
			String type = traceInputs.get(i)[0];

			if (traceInputs.get(i)[2].equals("0")) {
				for (int j = 0; j < traceInputs.get(i).length; j++) {
					System.out.print(traceInputs.get(i)[j] + " ");
				}
				System.out.println();
				System.out.print("This instruction is skipped because size is 0");
				System.out.println();
				System.out.println();
				continue;
			}

			switch (type) {
			case "S":
				System.out.println(
						traceInputs.get(i)[0] + " " + Integer.toHexString(Integer.parseInt(traceInputs.get(i)[1])) + " "
								+ traceInputs.get(i)[2] + " " + traceInputs.get(i)[2]);

				start = (int) ((Integer.parseInt(traceInputs.get(i)[1])) / 8);
				count = (int) ((Integer.parseInt(traceInputs.get(i)[2])) );

				for (int j = 0; j < count; j++) {
					String s = traceInputs.get(i)[3].substring(2 * j, 2 * j + 2);
					ram.getDatas().get(start + j).setData(s);
				}

				c = checkInCache(L1I, Integer.toHexString(start * 8),
						ram.getDatas().get(Integer.parseInt(traceInputs.get(i)[1]) / 8), 2);

				check1 = 0;
				check2 = 0;
				check3 = 0;

				if (c != null) {
					Counter.incrementHitL1I();
					System.out.print("L1I hit ");
					check1 = 1;
					
					String bin = hexToBinary(traceInputs.get(i)[1], 32);
					String setNum = bin.substring(L1D.sizeOfTagBits, L1D.sizeOfTagBits + L1D.sizeOfSetBits);
					
					int set =0;
					if(setNum.length()!=0)	set= Integer.parseInt(setNum,2);
					else set = 0;
					
					for (int j = 0; j < L1I.getSets().get(set).getLines().size(); j++) {
						if(L1I.getSets().get(set).getLines().get(j).getTime() == i+1) {
							for (int j2 = 0; j2 < count; j2++) {
								String s = traceInputs.get(i)[3].substring(2 * j2, 2 * j2 + 2);
								L1I.getSets().get(set).getLines().get(j).getDatas().get(j2).setData(s); 
							}
							break;
						}
					}
				}

				c = checkInCache(L1D, Integer.toHexString(start * 8),
						ram.getDatas().get(Integer.parseInt(traceInputs.get(i)[1]) / 8), 2);

				if (c != null) {
					Counter.incrementHitL1D();
					System.out.print("L1D hit ");
					check2 = 1;
					
					String bin = hexToBinary(traceInputs.get(i)[1], 32);
					String setNum = bin.substring(L1D.sizeOfTagBits, L1D.sizeOfTagBits + L1D.sizeOfSetBits);
					
					int set =0;
					if(setNum.length()!=0)	set= Integer.parseInt(setNum,2);
					else set = 0;
					
					for (int j = 0; j < L1D.getSets().get(set).getLines().size(); j++) {
						if(L1D.getSets().get(set).getLines().get(j).getTime() == i+1) {
							for (int j2 = 0; j2 < count; j2++) {
								String s = traceInputs.get(i)[3].substring(2 * j2, 2 * j2 + 2);
								L1D.getSets().get(set).getLines().get(j).getDatas().get(j2).setData(s); 
							}
							break;
						}
					}
				}

				c = checkInCache(L2, Integer.toHexString(start * 8),
						ram.getDatas().get(Integer.parseInt(traceInputs.get(i)[1]) / 8), 2);

				if (c != null) {
					Counter.incrementHitL2();
					System.out.println("L2 hit ");
					check3 = 1;
					
					String bin = hexToBinary(traceInputs.get(i)[1], 32);
					String setNum = bin.substring(L1D.sizeOfTagBits, L1D.sizeOfTagBits + L1D.sizeOfSetBits);
					
					int set =0;
					if(setNum.length()!=0)	set= Integer.parseInt(setNum,2);
					else set = 0;
					
					for (int j = 0; j < L2.getSets().get(set).getLines().size(); j++) {
						if(L2.getSets().get(set).getLines().get(j).getTime() == i+1) {
							for (int j2 = 0; j2 < count; j2++) {
								String s = traceInputs.get(i)[3].substring(2 * j2, 2 * j2 + 2);
								L2.getSets().get(set).getLines().get(j).getDatas().get(j2).setData(s); 
							}
							break;
						}
					}
				}

				// System.out.println();

				System.out.print("Store in ");

				if (check1 != 0) {
					System.out.print("L1I, ");
				}

				if (check2 != 0) {
					System.out.print("L1D, ");
				}

				if (check3 != 0) {
					System.out.print("L2 ");
				}

				System.out.println("RAM");
				System.out.println();
				break;
			case "I":
				System.out.println(traceInputs.get(i)[0] + " "
						+ Integer.toHexString(Integer.parseInt(traceInputs.get(i)[1])) + " " + traceInputs.get(i)[2]);

				start = (int) ((Integer.parseInt(traceInputs.get(i)[1])) / 8);

				Data x = ram.getDatas().get(Integer.parseInt(traceInputs.get(i)[1]) / 8);
				String y = Integer.toHexString(start * 8);

				c = checkInCache(L1I, y, x, 1);

				if (c != null) {
					Counter.incrementHitL1I();
					System.out.print("L1I hit, ");
				} else {
					Counter.incrementMissL1I();
					System.out.print("L1I miss, ");
					L1I.addToCacheFrom(start, Integer.parseInt(traceInputs.get(i)[2]), 1, i + 1, ram); // third argument
																										// means it is
																										// L1D
				}

				c = checkInCache(L2, Integer.toHexString(start * 8),
						ram.getDatas().get(Integer.parseInt(traceInputs.get(i)[1]) / 8), 3); // three means it is L2

				if (c != null) {
					Counter.incrementHitL2();
					System.out.println("L2 hit ");
				} else {
					Counter.incrementMissL2();
					System.out.println("L2 miss ");
					L2.addToCacheFrom(start, Integer.parseInt(traceInputs.get(i)[2]), 3, i + 1, ram); // third argument
																										// means it is
																										// L2
				}

				temp = hexToBinary(Integer.toHexString(Integer.parseInt(traceInputs.get(i)[1])), 32);

				String setOfL1I = temp.substring(L1I.sizeOfTagBits, L1I.sizeOfTagBits + L1I.sizeOfSetBits);

				setOfL2 = temp.substring(L2.sizeOfTagBits, L2.sizeOfTagBits + L2.sizeOfSetBits);

				if (setOfL1I.equals(""))
					setOfL1I = "0";
				if (setOfL2.equals(""))
					setOfL2 = "0";

				System.out.print("Place in L2");

				if (L2.getSets().size() != 1) {
					System.out.print(" set " + Integer.parseInt(setOfL2, 2));
				}

				System.out.print(", L1I");
				if (L1I.getSets().size() != 1) {
					System.out.print(" set " + Integer.parseInt(setOfL1I, 2));
				}

				System.out.println();
				System.out.println();
				break;
			case "M":
				System.out.println(
						traceInputs.get(i)[0] + " " + Integer.toHexString(Integer.parseInt(traceInputs.get(i)[1])) + " "
								+ traceInputs.get(i)[2] + " " + traceInputs.get(i)[3]);

				start = (int) ((Integer.parseInt(traceInputs.get(i)[1])) / 8);

				c = checkInCache(L1D, Integer.toHexString(start * 8),
						ram.getDatas().get(Integer.parseInt(traceInputs.get(i)[1]) / 8), 2);

				if (c != null) {
					Counter.incrementHitL1D();
					System.out.print("L1D hit, ");
				} else {
					Counter.incrementMissL1D();
					System.out.print("L1D miss, ");
					L1D.addToCacheFrom(start, Integer.parseInt(traceInputs.get(i)[2]), 2, i + 1, ram); // third argument
																										// means it is
																										// L1D
				}

				c = checkInCache(L2, Integer.toHexString(start * 8),
						ram.getDatas().get(Integer.parseInt(traceInputs.get(i)[1]) / 8), 3); // three means it is L2

				if (c != null) {
					Counter.incrementHitL2();
					System.out.println("L2 hit ");
				} else {
					Counter.incrementMissL2();
					System.out.println("L2 miss ");
					L2.addToCacheFrom(start, Integer.parseInt(traceInputs.get(i)[2]), 3, i + 1, ram); // third argument
																										// means it is
																										// L2
				}

				temp = hexToBinary(Integer.toHexString(Integer.parseInt(traceInputs.get(i)[1])), 32);

				setOfL1D = temp.substring(L1D.sizeOfTagBits, L1D.sizeOfTagBits + L1D.sizeOfSetBits);

				setOfL2 = temp.substring(L2.sizeOfTagBits, L2.sizeOfTagBits + L2.sizeOfSetBits);

				if (setOfL1D.equals(""))
					setOfL1D = "0";
				if (setOfL2.equals(""))
					setOfL2 = "0";

				System.out.print("Place in L2");

				if (L2.getSets().size() != 1) {
					System.out.print(" set " + Integer.parseInt(setOfL2, 2));
				}

				System.out.print(", L1D");
				if (L1D.getSets().size() != 1) {
					System.out.print(" set " + Integer.parseInt(setOfL1D, 2));
				}

				start = (int) ((Integer.parseInt(traceInputs.get(i)[1])) / 8);
				count = (int) ((Integer.parseInt(traceInputs.get(i)[2])) );

				for (int j = 0; j < count; j++) {
					String s = traceInputs.get(i)[3].substring(2 * j, 2 * j + 2);
					ram.getDatas().get(start + j).setData(s);
				}
				
				String bin = hexToBinary(traceInputs.get(i)[1], 32);
				String setNum = bin.substring(L1D.sizeOfTagBits, L1D.sizeOfTagBits + L1D.sizeOfSetBits);
				
				int set =0;
				if(setNum.length()!=0)	set= Integer.parseInt(setNum,2);
				else set = 0;
				
				for (int j = 0; j < L1D.getSets().get(set).getLines().size(); j++) {
					if(L1D.getSets().get(set).getLines().get(j).getTime() == i+1) {
						for (int j2 = 0; j2 < count; j2++) {
							String s = traceInputs.get(i)[3].substring(2 * j2, 2 * j2 + 2);
							L1D.getSets().get(set).getLines().get(j).getDatas().get(j2).setData(s); 
						}
						break;
					}
				}
				
				for (int j = 0; j < L2.getSets().get(set).getLines().size(); j++) {
					if(L2.getSets().get(set).getLines().get(j).getTime() == i+1) {
						for (int j2 = 0; j2 < count; j2++) {
							String s = traceInputs.get(i)[3].substring(2 * j2, 2 * j2 + 2);
							L2.getSets().get(set).getLines().get(j).getDatas().get(j2).setData(s); 
						}
						break;
					}
				}

				c = checkInCache(L1I, Integer.toHexString(start * 8),
						ram.getDatas().get(Integer.parseInt(traceInputs.get(i)[1]) / 8), 2);

				check1 = 0;
				check2 = 0;
				check3 = 0;

				if (c != null) {
					check1 = 1;
				}

				c = checkInCache(L1D, Integer.toHexString(start * 8),
						ram.getDatas().get(Integer.parseInt(traceInputs.get(i)[1]) / 8), 2);

				if (c != null) {
					check2 = 1;
				}

				c = checkInCache(L2, Integer.toHexString(start * 8),
						ram.getDatas().get(Integer.parseInt(traceInputs.get(i)[1]) / 8), 2);

				if (c != null) {
					check3 = 1;
				}

				System.out.println();

				System.out.print("Store in ");

				if (check1 != 0) {
					System.out.print("L1I, ");
				}

				if (check2 != 0) {
					System.out.print("L1D, ");
				}

				if (check3 != 0) {
					System.out.print("L2 ");
				}

				System.out.println("RAM");
				System.out.println();
				break;
			case "L":
				System.out.println(traceInputs.get(i)[0] + " "
						+ Integer.toHexString(Integer.parseInt(traceInputs.get(i)[1])) + " " + traceInputs.get(i)[2]);

				start = (int) ((Integer.parseInt(traceInputs.get(i)[1])) / 8);

				c = checkInCache(L1D, Integer.toHexString(start * 8),
						ram.getDatas().get(Integer.parseInt(traceInputs.get(i)[1]) / 8), 2);

				if (c != null) {
					Counter.incrementHitL1D();
					System.out.print("L1D hit, ");
				} else {
					Counter.incrementMissL1D();
					System.out.print("L1D miss, ");
					L1D.addToCacheFrom(start, Integer.parseInt(traceInputs.get(i)[2]), 2, i + 1, ram); // third argument
																										// means it is
																										// L1D
				}

				c = checkInCache(L2, Integer.toHexString(start * 8),
						ram.getDatas().get(Integer.parseInt(traceInputs.get(i)[1]) / 8), 3); // three means it is L2

				if (c != null) {
					Counter.incrementHitL2();
					System.out.println("L2 hit ");
				} else {
					Counter.incrementMissL2();
					System.out.println("L2 miss ");
					L2.addToCacheFrom(start, Integer.parseInt(traceInputs.get(i)[2]), 3, i + 1, ram); // third argument
																										// means it is
																										// L2
				}

				temp = hexToBinary(Integer.toHexString(Integer.parseInt(traceInputs.get(i)[1])), 32);

				setOfL1D = temp.substring(L1D.sizeOfTagBits, L1D.sizeOfTagBits + L1D.sizeOfSetBits);

				setOfL2 = temp.substring(L2.sizeOfTagBits, L2.sizeOfTagBits + L2.sizeOfSetBits);

				if (setOfL1D.equals(""))
					setOfL1D = "0";
				if (setOfL2.equals(""))
					setOfL2 = "0";

				System.out.print("Place in L2");

				if (L2.getSets().size() != 1) {
					System.out.print(" set " + Integer.parseInt(setOfL2, 2));
				}

				System.out.print(", L1D");
				if (L1D.getSets().size() != 1) {
					System.out.print(" set " + Integer.parseInt(setOfL1D, 2));
				}

				System.out.println();
				System.out.println();
				break;
			default:
				break;
			}
		}

		// ram.printRam();

	}

	static Line checkInCache(Cache c, String hexAdress, Data d, int j) {
		Line res = null;

		String binAdress = hexToBinary(hexAdress, 32);

		String tag = binAdress.substring(0, c.sizeOfTagBits);
		String set = binAdress.substring(c.sizeOfTagBits, c.sizeOfTagBits + c.sizeOfSetBits);

		Set currentSet = null;

		if (c.sizeOfSetBits == 0) {
			currentSet = c.getSets().get(0);

		} else {
			currentSet = c.getSets().get(Integer.parseInt(set, 2));
		}

		int n = currentSet.getLines().size();

		for (int i = 0; i < n; i++) {
			if (currentSet.getLines().get(i).getDatas().contains(d)) {
				res = currentSet.getLines().get(i);
				break;
			}
		}

		return res;

	}

	static String hexToBinary(String hex, int bit) {
		int i = Integer.parseInt(hex, 16);
		String bin = Integer.toBinaryString(i);

		if (bin.length() < bit) {
			int length = bit - bin.length();
			String concat = "";

			for (int j = 0; j < length; j++) {
				concat += "0";
			}

			return concat + bin;
		}

		return bin;
	}

}
