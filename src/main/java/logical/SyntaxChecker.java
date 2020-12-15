package logical;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SyntaxChecker {
	private SyntaxChecker() {
		// Evita que la clase sea instanciada
		throw new IllegalStateException("Utility class");
	}

	public static void format(String path) {
		/*
		 * Este metodo formatea un archivo enviado por parametro, con el proposito de que
		 * no existan lineas invalidas al momento de revisarlo.
		 * Actualmente usa una Expresion regular para verificar la sintaxis de cada linea.
		 */
		
		File file = new File(path + File.separator + "target.txt");
		
		final Logger logger = LogManager.getLogger(SyntaxChecker.class.getName());

		final String REGEX = "[P|C][;]\\d+[;]\\d+[,]\\d+";

		try {
			// Consigue una lista de lineas desde el archivo
			List<String> lines = org.apache.commons.io.FileUtils.readLines(file, StandardCharsets.UTF_8.toString());
			List<String> ids = new ArrayList<>();
			List<String> dump = new ArrayList<>();
			// Elimina lineas incorrectas
			int i = 0;
			while (i < lines.size()) {
				
				// Si no calza con el REGEX, se elimina y se logea.
				if (!lines.get(i).matches(REGEX)) {
					logger.trace("Se ha eliminado una linea con sintaxis incorrecta: {}", lines.get(i));
					
					dump.add(lines.get(i));
					lines.remove(i--);
				}
				else {
					// En caso de que la sintaxis sea correcta, se verifica id unica
					String[] arr = lines.get(i).split(";");
					if(ids.contains(arr[1])) {
						logger.trace("Se ha eliminado una linea que utiliza una id no disponible: {}", lines.get(i));
						
						dump.add(lines.get(i));
						lines.remove(i--);
					}
					else {
						ids.add(arr[1]);
					}
				}
				i++;
			}
			
			// Elimina duplicados
			LinkedHashSet<String> lhs = new LinkedHashSet<>(lines);
			
			logger.trace("Se han eliminado {} lineas duplicadas", lines.size() - lhs.size());
			
			// Se guarda el archivo.
			org.apache.commons.io.FileUtils.writeLines(file, lhs);
			org.apache.commons.io.FileUtils.writeLines(new File(path + File.separator + "dump.txt"), dump);

		} catch (Exception e) {
			// En caso de errores con el archivo, se logea.
			// TODO evaluar si es mejor arrojar los errores por sobre logearlos.
			logger.warn(e);
		}
	}
}