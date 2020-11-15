package logical;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class ScannerTxt 
{ 
	public static void main(String[] args) throws FileNotFoundException {
	    Scanner sc = new Scanner(new File("Datos.txt"));
	    sc.useDelimiter(",|;|\n");
	   
	    List<Entidad> Lista = new ArrayList<Entidad>();
	    
    while (sc.hasNextLine()) { 
      int x = sc.nextInt();
      int y = sc.nextInt();
      char tipo = sc.next().charAt(0);
      int id = sc.nextInt();
      
      Entidad newEntidad = new Entidad(x,y,tipo,id);
      System.out.println(newEntidad.getTipo());
      
      //Lista.add(new Entidad(x,y,tipo,id)
      
      
      
      
      
   
    }
    
  } 
} 
