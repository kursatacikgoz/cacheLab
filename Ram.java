package systemProject3;

import java.util.ArrayList;

public class Ram {

	ArrayList<Data> datas = new ArrayList<Data>();

	public void addToRam(String s, int adressInDecimal) {
		Data newData = new Data(s, adressInDecimal);
		datas.add(newData);
	}

	public void printRam() {
		for (int i = 0; i < datas.size(); i++) {
			System.out.println(datas.get(i).toString());
		}
	}

	/**
	 * @return the datas
	 */
	public ArrayList<Data> getDatas() {
		return datas;
	}

}
