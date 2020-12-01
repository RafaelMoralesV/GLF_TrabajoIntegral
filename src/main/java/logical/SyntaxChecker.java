package logical;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SyntaxChecker {
	private SyntaxChecker() {
		// Evita que la clase sea instanciada
		throw new IllegalStateException("Utility class");
	}

	public static void validar(File file) {

		final Logger logger = LogManager.getLogger(SyntaxChecker.class.getName());

		final String REGEX = "[P|C][;]\\d+[;]\\d+[,]\\d+";
		int cont = 0;

		try {
			List<String> lines = org.apache.commons.io.FileUtils.readLines(file, StandardCharsets.UTF_8.toString());

			int i = 0;
			while (i < lines.size()) {
				if (!lines.get(i).matches(REGEX)) {
					logger.trace("Removida la linea: {}", cont);
					logger.trace(lines.get(i));
					lines.remove(i--);
				}
				cont++;
				i++;
			}
			org.apache.commons.io.FileUtils.writeLines(file, lines);

		} catch (Exception e) {
			logger.warn(e);
		}
	}
}