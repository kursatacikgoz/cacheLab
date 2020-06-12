package systemProject3;

import java.util.ArrayList;

public class Cache {

	ArrayList<Set> sets = new ArrayList<Set>();
	int sizeOfSetBits = 0;
	int sizeOfTagBits = 0;
	int sizeOfDataBits = 0;

	public Cache(int s, int e, int b) {
		int check = (int) Math.pow(2, s);
		for (int i = 0; i < check; i++) {
			Set set = new Set(e, b);
			sets.add(set);
		}

		if (s != 0) {
			sizeOfSetBits = s;
		}
		
		sizeOfDataBits = b;
		sizeOfTagBits = 32 - s - b;
	}

	/**
	 * @return the sets
	 */
	public ArrayList<Set> getSets() {
		return sets;
	}

	/**
	 * @param sets the sets to set
	 */
	public void setSets(ArrayList<Set> sets) {
		this.sets = sets;
	}

	public void addToCacheFrom(int start, int j, int whichCache, int time, Ram ram) { // int which cache 1 is L1I 
																						// 2 is L1D 3 is L2
		String binAdress = hexToBinary(Integer.toHexString(start * 8),32);

	
		String tag = binAdress.substring(0, this.sizeOfTagBits);
		String set = binAdress.substring(this.sizeOfTagBits, this.sizeOfTagBits + this.sizeOfSetBits);

		Set currentSet = null;

		if (this.sizeOfSetBits == 0) {
			currentSet = this.getSets().get(0);
		} else {
			int cSet = Integer.parseInt(set, 2);
			currentSet = this.getSets().get(cSet);
		}

		int n = currentSet.getLines().size();

		int check = isSetFull(currentSet);

		Line currentLine = null;

		if (check == -1) { // There is at least one empty space
			int s = currentSet.getLines().size();

			for (int i = 0; i < s; i++) {
				if (currentSet.getLines().get(i).getV() == 0) {
					currentLine = currentSet.getLines().get(i);
					break;
				}
			}
		} else { // There is no empty field on this set
			int s = currentSet.getLines().size();
			for (int i = 0; i < s; i++) {
				if (currentSet.getLines().get(i).getTime() == check) {
					currentSet.getLines().get(i).makeFreeDatas(whichCache);

					currentLine = currentSet.getLines().get(i);

				}
			}
		}

		currentLine.setV(1);
		currentLine.setTime(time);

		currentLine.setTag(tag);

		n = currentLine.getDatasSize();

		for (int i = 0; i < n; i++) {
			currentLine.getDatas().add(ram.getDatas().get(start + i));
		}

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


	int isSetFull(Set a) {
		int s = a.getLines().size();
		int minTime = Integer.MAX_VALUE;

		for (int i = 0; i < s; i++) {
			if (a.getLines().get(i).getV() == 0) {
				return -1;
			} else if (a.getLines().get(i).getTime() < minTime) {
				minTime = a.getLines().get(i).getTime();
			}
		}

		return minTime;
	}

}
