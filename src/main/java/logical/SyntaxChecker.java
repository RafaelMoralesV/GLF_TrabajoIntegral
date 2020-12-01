package logical;

import java.io.*;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SyntaxChecker {
	public static void Validar(File file) {

		Logger LOGGER = LogManager.getLogger(SyntaxChecker.class.getName());

		String REGEX = "[P|C][;]\\d+[;]\\d+[,]\\d+";
//		BufferedReader objReader = null;
		int cont = 0;

		try {
			List<String> lines = org.apache.commons.io.FileUtils.readLines(file);

			for (int i = 0; i < lines.size(); i++) {
				if (!lines.get(i).matches(REGEX)) {
					LOGGER.trace("Removida la linea: " + cont);
					LOGGER.trace(lines.get(i));
					lines.remove(i--);
				}
				cont++;
			}
			org.apache.commons.io.FileUtils.writeLines(file, lines);

		} catch (IOException e) {
			LOGGER.warn(e);
		} catch (Exception e) {
			LOGGER.warn(e);
		}
	}

	public static void main(String[] args) {
		// Does nothing
		return;
	}
}