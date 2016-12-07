import java.util.StringTokenizer;


public class SistemaDeFicheros {

	static Cluster[] datos;
	Metadato[] metadatos;
	private int tamanyo = 20;
	private int tamCluster=4;
	
	public SistemaDeFicheros()
	{			
		datos = new Cluster[tamanyo];
		metadatos= new Metadato[tamanyo];
		
		for(int i=0;i<tamanyo;i++)
		{
			metadatos[i] = new Metadato(i);
		}
	}
	
	public void mostrarMetadatos()
	{
		for (int i=0;i<tamanyo;i++)
		{
			System.out.println("Los metadatos del cluster "+i+":");
			System.out.println("Disponible: "+metadatos[i].disponible);
			System.out.println("Reservado: "+metadatos[i].reservado);
			System.out.println("Dañado: "+metadatos[i].danyado);
			System.out.println("Fin: "+metadatos[i].fin);
			System.out.println("Siguiente: "+metadatos[i].siguiente.posicion);
		}
	}
	
	public void crearArchivo(String contenido)
	{
		char[] cadena;
		cadena=contenido.toCharArray();
		int tamCadena=cadena.length;
		int numClusters=tamCadena/tamCluster;
		int i=0;
		int a=0;
		int b=0;
		int posAnterior=0;
		while(numClusters!=0){
			numClusters--;
			char[] parte=new char[tamCluster];
			for(a=0; a<tamCluster; a++){
				parte[a]=cadena[b];
				b++;
			}
			String parteContenido=String.valueOf(parte);
			while(metadatos[i].disponible!=true)
			{
				i++;
			}
			metadatos[i].disponible = false;
			datos[i]= new Archivo(parteContenido);
		}
	}
	
	public void crearDirectorio(String nombre)
	{
		int i=0;
		
		while(metadatos[i].disponible!=true)
		{
			i++;
		}
		metadatos[i].disponible = false;
		datos[i]=new Directorio(nombre);
	}
	
	public void devolverDirectorio(String ruta) throws Exception
	{
		Cluster c = new Cluster();
		if ( c instanceof Directorio) 
		{
			Directorio d = (Directorio) c;
			StringTokenizer st = new StringTokenizer(ruta,"/");
		}
	}
	
}
