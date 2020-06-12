package systemProject3;

import java.util.ArrayList;

public class Set {

	ArrayList<Line> lines = new ArrayList<Line>();

	public Set(int e, int b) {
		int B = (int) Math.pow(2, b);
		for (int i = 0; i < e; i++) {
			Line line = new Line(B);
			lines.add(line);

		}
	}

	/**
	 * @return the lines
	 */
	public ArrayList<Line> getLines() {
		return lines;
	}

	/**
	 * @param lines the lines to set
	 */
	public void setLines(ArrayList<Line> lines) {
		this.lines = lines;
	}

}
