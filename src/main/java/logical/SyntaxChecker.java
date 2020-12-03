package logical;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SyntaxChecker {
	private SyntaxChecker() {
		// Evita que la clase sea instanciada
		throw new IllegalStateException("Utility class");
	}

	public static void format(File file) {
		/*
		 * Este metodo formatea un archivo enviado por parametro, con el proposito de que
		 * no existan lineas invalidas al momento de revisarlo.
		 * Actualmente usa una Expresion regular para verificar la sintaxis de cada linea.
		 */
		final Logger logger = LogManager.getLogger(SyntaxChecker.class.getName());

		final String REGEX = "[P|C][;]\\d+[;]\\d+[,]\\d+";
		int cont = 0;

		try {
			// Consigue una lista de lineas desde el archivo
			List<String> lines = org.apache.commons.io.FileUtils.readLines(file, StandardCharsets.UTF_8.toString());

			// Elimina lineas incorrectas
			int i = 0;
			while (i < lines.size()) {
				// Si no calza con el REGEX, se elimina y se logea.
				if (!lines.get(i).matches(REGEX)) {
					logger.trace("Removida la linea: {}", cont);
					logger.trace(lines.get(i));
					lines.remove(i--);
				}
				cont++;
				i++;
			}
			
			// Elimina duplicados
			LinkedHashSet<String> lhs = new LinkedHashSet<>(lines);
			
			logger.trace("Se han eliminado {} lineas duplicadas", lines.size() - lhs.size());
			
			// Se guarda el archivo.
			org.apache.commons.io.FileUtils.writeLines(file, lhs);

		} catch (Exception e) {
			// En caso de errores con el archivo, se logea.
			// TODO evaluar si es mejor arrojar los errores por sobre logearlos.
			logger.warn(e);
		}
	}
}