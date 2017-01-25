public class Directorio extends Cluster {
	
	EntradaDirectorio[] carpeta;
	static int tamanyo = 20;
	
	public Directorio(String nomb)
	{
		tipo='d';
		contenido=nomb;
		carpeta = new EntradaDirectorio[tamanyo];
	}
	
}
