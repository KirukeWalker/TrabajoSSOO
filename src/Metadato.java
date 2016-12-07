
public class Metadato {
	
	boolean disponible,danyado,fin,reservado;
	int posicion;
	Metadato siguiente;
	protected Cluster contenido;
	
	public Metadato(int i)
	{
		posicion = i;
		disponible = true;
		danyado = false;
		fin = false;
		reservado = false;
		siguiente = null;
		contenido = SistemaDeFicheros.datos[i];
	}
	
	
	
}
