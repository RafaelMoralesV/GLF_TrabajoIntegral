package logical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
	public static List<Entidad> parseFile(File target) 
			throws IOException, FileNotFoundException{
		List<Entidad> lista = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader(target));
		
		String st;
		while((st = br.readLine()) != null) {
			String[] arr = st.split("[;|,]", 4);
			lista.add(
					new Entidad(
							Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
							arr[0].charAt(0), Integer.parseInt(arr[1])
							)
					);
		}
		br.close();
		return lista;
	}
	
	public static List<Entidad> parseFile(String path) 
			throws IOException, FileNotFoundException{
		File target = new File(path);
		return parseFile(target);
	}
}
