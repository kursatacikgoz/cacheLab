package systemProject3;

import java.util.ArrayList;

public class Line {

	String tag;
	int time = 0;
	int v = 0;
	ArrayList<Data> datas = new ArrayList<Data>();
	int datasSize;

	public Line(int b) {
		datasSize = b; // byte data can be stored

		datas.clear();
	}

	/**
	 * @return the datas
	 */
	public ArrayList<Data> getDatas() {
		return datas;
	}

	/**
	 * @param datas the datas to set
	 */
	public void setDatas(ArrayList<Data> datas) {
		this.datas = datas;
	}

	/**
	 * @return the v
	 */
	public int getV() {
		return v;
	}

	/**
	 * @param v the v to set
	 */
	public void setV(int v) {
		this.v = v;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @return the datasSize
	 */
	public int getDatasSize() {
		return datasSize;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * @param datasSize the datasSize to set
	 */
	public void setDatasSize(int datasSize) {
		this.datasSize = datasSize;
	}

	public void makeFreeDatas(int check) { // int which cache 1 is L1I 2 is L1D 3 is L2
		tag = "";
		time = 0;
		v = 0;
		datas.clear();

		if (check == 1) {
			Counter.incrementEvictionL1I();
		} else if (check == 2) {
			Counter.incrementEvictionL1D();
		} else if (check == 3) {
			Counter.incrementEvictionL2();
		}

	}

}
