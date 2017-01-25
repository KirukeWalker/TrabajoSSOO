import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Consola {
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader (isr);
	
	Consola (SistemaDeFicheros sdf) throws Exception{
		int option=0;
		while(option!=10){
			option=0;
			System.out.println("Opciones:");
			System.out.println("	1. Mostrar");
			System.out.println("	2. Crear Directorio");
			System.out.println("	3. Borrar Directorio");
			System.out.println("	4. Crear Archivo");
			System.out.println("	5. Borrar Archivo");
			System.out.println("	6. Copiar Archivo");
			System.out.println("	7. Mover Archivo");
			System.out.println("	8. Mostrar metadatos");
			System.out.println("	9.");
			System.out.println("	10. Salir");
			
			option =Integer.parseInt (br.readLine());
			
			switch(option){
			case 1:
				sdf.Mostrar();
				break;
			case 2:
				sdf.crearDirectorio("tmp");
				break;
			case 3:
				break;
			case 4:
				sdf.crearArchivo("Archivo1");
				sdf.crearArchivo("Archivo2","tmp");
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				sdf.mostrarMetadatos();
				break;
			case 9:
				break;
			}
		}
	}
}
