package fin.demes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FinDeMes {

	private static final String DANI = "Dani";
	private static final String DANI_LE_DEBE_A_JOHANNA = "Dani le debe a Johanna ";
	private static final String DATE_FORMAT = "yyyy.MM.dd";
	private static final String EN_PACES = "en paces";
	private static final String EXTENSION = ".txt";
	private static final String INPUT_FILENAME = "C:\\findemes\\input.txt";
	private static final String JOHANNA = "Johanna";
	private static final String OUTPUT_FILENAME_PREFIX = "C:\\findemes\\";

	public static void main(String[] args) {

		try {
			System.out.println(calculateGastosFinDeMes());
		} catch (Exception e) {
			System.err.println("algo paso...: " + e.getMessage());
		}
	}

	public static String calculateGastosFinDeMes()
			throws FileNotFoundException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(INPUT_FILENAME)));
		String outputFileName = OUTPUT_FILENAME_PREFIX
				+ new SimpleDateFormat(DATE_FORMAT).format(Calendar
						.getInstance().getTime()) + EXTENSION;
		BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName));
		String strLine;
		Double resultDani = 0.0;
		Double resultJohanna = 0.0;
		String text = "";
		while ((strLine = in.readLine()) != null) {
			String[] trozos = strLine.split(" ");
			if (JOHANNA.equalsIgnoreCase(trozos[0])) {
				resultJohanna += Double.parseDouble(trozos[1]);
			} else if (DANI.equalsIgnoreCase(trozos[0])) {
				resultDani += Double.parseDouble(trozos[1]);
			}
			out.write(strLine);
			out.newLine();
		}
		if (resultJohanna > resultDani) {
			text = DANI_LE_DEBE_A_JOHANNA + ((resultJohanna - resultDani) / 2);
		} else if (resultDani > resultJohanna) {
			text = DANI_LE_DEBE_A_JOHANNA + ((resultDani - resultJohanna) / 2);
		} else {
			text = EN_PACES;
		}
		in.close();
		out.close();
		return text;
	}
}
