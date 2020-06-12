package systemProject3;

public class Data {

	String data;
	int adress;
	String adressInBinary;

	public Data(String data, int adress) {
		this.data = data;
		this.adress = adress;
		adressInBinary = decimalToBinary(adress);
	}

	@Override
	public String toString() {
		return "Data [data=" + data + ", adress=" + adress + "]";
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	String decimalToBinary(int dec) {
		String bin = Integer.toBinaryString(dec);

		if (bin.length() < 32) {
			int length = 32 - bin.length();
			String concat = "";

			for (int j = 0; j < length; j++) {
				concat += "0";
			}

			return concat + bin;
		}

		return bin;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

}
