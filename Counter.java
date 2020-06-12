package systemProject3;

public class Counter {
	static int hitL1I = 0;
	static int hitL1D = 0;
	static int hitL2 = 0;

	static int missL1I = 0;
	static int missL1D = 0;
	static int missL2 = 0;

	static int evictionL1I = 0;
	static int evictionL1D = 0;
	static int evictionL2 = 0;

	/**
	 * @return the hitL1I
	 */
	public int getHitL1I() {
		return hitL1I;
	}

	/**
	 * @return the hitL1D
	 */
	public int getHitL1D() {
		return hitL1D;
	}

	/**
	 * @return the hitL2
	 */
	public int getHitL2() {
		return hitL2;
	}

	/**
	 * @return the missL1I
	 */
	public int getMissL1I() {
		return missL1I;
	}

	/**
	 * @return the missL1D
	 */
	public int getMissL1D() {
		return missL1D;
	}

	/**
	 * @return the missL2
	 */
	public int getMissL2() {
		return missL2;
	}

	/**
	 * @param hitL1I the hitL1I to set
	 */
	public static void incrementHitL1I() {
		hitL1I++;
	}

	/**
	 * @param hitL1D the hitL1D to set
	 */
	public static void incrementHitL1D() {
		hitL1D++;
	}

	/**
	 * @param hitL2 the hitL2 to set
	 */
	public static void incrementHitL2() {
		hitL2++;
	}

	/**
	 * @param missL1I the missL1I to set
	 */
	public static void incrementMissL1I() {
		missL1I++;
	}

	/**
	 * @param missL1D the missL1D to set
	 */
	public static void incrementMissL1D() {
		missL1D++;
	}

	/**
	 * @param missL2 the missL2 to set
	 */
	public static void incrementMissL2() {
		missL2++;
	}

	/**
	 * @param missL1I the missL1I to set
	 */
	public static void incrementEvictionL1I() {
		evictionL1I++;
	}

	/**
	 * @param missL1D the missL1D to set
	 */
	public static void incrementEvictionL1D() {
		evictionL1D++;
	}

	/**
	 * @param missL2 the missL2 to set
	 */
	public static void incrementEvictionL2() {
		evictionL2++;
	}

	/**
	 * @return the evictionL1I
	 */
	public static int getEvictionL1I() {
		return evictionL1I;
	}

	/**
	 * @return the evictionL1D
	 */
	public static int getEvictionL1D() {
		return evictionL1D;
	}

	/**
	 * @return the evictionL2
	 */
	public static int getEvictionL2() {
		return evictionL2;
	}

}
