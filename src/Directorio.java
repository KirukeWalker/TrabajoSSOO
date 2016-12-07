
public class Directorio extends Cluster {
	
	String nombre;
	EntradaDirectorio[] carpeta;
	static int tamanyo = 20;
	
	public Directorio(String nomb)
	{
		tipo='d';
		nombre=nomb;
		carpeta = new EntradaDirectorio[tamanyo];
	}
	
}
