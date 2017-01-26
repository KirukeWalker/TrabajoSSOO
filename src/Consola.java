import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Consola extends Thread {
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader (isr);
	SistemaDeFicheros sdf;
	
	Consola(SistemaDeFicheros sistdefich){
		sdf=sistdefich;
	}
	
	
	public void run(){
		int option=0;
		while(option!=11&&option!=12){
			option=0;
			System.out.println("Opciones:");
			System.out.println("	1. Matar BorraTMPCada5Segundos");
			System.out.println("	2. Crear Directorio");
			System.out.println("	3. Borrar Directorio");
			System.out.println("	4. Crear Archivo");
			System.out.println("	5. Borrar Archivo");
			System.out.println("	6. Mostrar Procesos en ejecución");
			System.out.println("	7. Mover Archivo");
			System.out.println("	8. Mostrar Metadatos");
			System.out.println("	9. BorrarTMPCada5Segundos");
			System.out.println("	10. Mostrar Directorio");
			System.out.println("	11. Matar Consola");
			System.out.println("	12. Salir");
			
			try {
				option =Integer.parseInt (br.readLine());
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			switch(option){
			case 1:
				try {
					sdf.killRun();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sdf.matarProceso("BorrarTMPCada5Seg");
				break;
				
			case 2:
				System.out.println("\nIntroduzca el nombre del directorio que quieres crear:\n");
				try {
					String Cadena = br.readLine();
					sdf.crearDirectorio(Cadena);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			case 3:
				System.out.println("\nIntroduzca el nombre del directorio que quieres borrar\n");
				try {
					String Cadena = br.readLine();
					sdf.eliminarDirectorio(Cadena);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				
				System.out.println("\nSelecciona como quieres crear el archivo: en directorio raiz o en otro directorio:\n");
				System.out.println("1:Directorio Raiz\n");
				System.out.println("2: en otro directorio\n");
				try {
					int opciones = Integer.parseInt (br.readLine());
					if(opciones == 1){
						System.out.println("\nIntroduzca el nombre del archivo que quieres crear:\n");
						String Cadena = br.readLine();
						
						sdf.crearArchivo(Cadena);
					}
					else if(opciones == 2){
						System.out.println("\nIntroduzca el nombre del archivo que quieres crear:\n");
						String Cadena = br.readLine();
						System.out.println("\nIntroduzca el nombre del directorio en el que se encuentra:\n");
						String Destino = br.readLine();
						sdf.crearArchivo(Cadena, Destino);
					}
					
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

					break;
				
				
			case 5:
				System.out.println("\nSeleccione el archivo que quiere borrar:\n");
				try {
					String Cadena = br.readLine();
					System.out.println("\nSeleccione la ruta del archivo que quiere borrar:\n");
					String Destino = br.readLine();
					
					sdf.eliminarArchivo(Cadena, Destino);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
				
			case 6:
				sdf.mostrarProc();
				break;
				
			case 7:
				System.out.println("\nIntroduzca el nombre del archivo que quieres mover:\n");
				try {
					String Cadena = br.readLine();
					System.out.println("\nIntrouzca la ruta origen del nombre del archivo que quieres mover:\n");
					String Origen = br.readLine();
					System.out.println("\nIntroduzca la ruta destino del nombre del archivo que quieres mover:\n");
					String Destino = br.readLine();
					
					sdf.mover(Cadena, Origen, Destino);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 8:
				sdf.mostrarMetadatos();
				break;
			case 9:
				sdf.start();
				break;
			case 10:
				sdf.mostrarDirectorio("tmp");
				break;
			case 11:
				sdf.matarProceso("Consola");
				break;
			}
		}
	}
}