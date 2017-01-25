
public class Metadato {
	
	boolean disponible,danyado,fin,reservado;
	int posicion;
	Metadato siguiente;
	
	public Metadato(int i)
	{
		posicion = i;
		disponible = true;
		danyado = false;
		fin = false;
		reservado = false;
		siguiente = null;
	}
	
	
	
}
