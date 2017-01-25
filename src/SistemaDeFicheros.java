import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SistemaDeFicheros {
	static Cluster[] datos;
	Metadato[] metadatos;
	private int tamanyo = 20;
	private int tamCluster=4;
	static Directorio raiz;
	
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader (isr);
	
	public SistemaDeFicheros()
	{			
		datos = new Cluster[tamanyo];
		metadatos= new Metadato[tamanyo];
		raiz = new Directorio("C:");
		for(int i=0;i<tamanyo;i++){
			metadatos[i] = new Metadato(i);
		}
		datos[0]=raiz;
		metadatos[0].disponible=false;
		metadatos[0].fin=true;
	}
	
	public void mostrarMetadatos()
	{
		for (int i=0;i<tamanyo;i++)
		{
			System.out.println("Los metadatos del cluster "+i+":");
			System.out.println("Disponible: "+metadatos[i].disponible);
			System.out.println("Reservado: "+metadatos[i].reservado);
			System.out.println("Da�ado: "+metadatos[i].danyado);
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
		if(tamCadena%tamCluster!=0) numClusters++;
		int i=0;
		int a=0;
		int b=0;
		int clusterAnterior=-1;
		for(i=0; i<metadatos.length; i++){
			if(metadatos[i].disponible==true) a++;
		}
		if(a<numClusters) System.out.println("no hay espacio suficiente");
		else{
			i=0;
			int posicionRaiz=0;
			while(raiz.carpeta[posicionRaiz]!=null){
				posicionRaiz++;
			}
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
				if(b<tamCluster){
					raiz.carpeta[posicionRaiz]=new EntradaDirectorio(contenido, i, 'A');
				}
				if(clusterAnterior!=-1){
					if(numClusters==0) metadatos[i].fin=true;
					else metadatos[clusterAnterior].siguiente=metadatos[i];
				}else{
					raiz.carpeta[posicionRaiz]=new EntradaDirectorio(contenido, i, 'A');
					datos[0]=raiz;
				}
				clusterAnterior=i;
			}
		}
	}
	
	public void crearDirectorio(String nombre)
	{
		int i=0;
		
		while(metadatos[i].disponible!=true){
			i++;
		}
		if(metadatos[i].disponible){
			metadatos[i].disponible = false;
			datos[i]=new Directorio(nombre);
			int posicionRaiz=0;
			while(raiz.carpeta[posicionRaiz]!=null){
				posicionRaiz++;
			}
			raiz.carpeta[posicionRaiz]=new EntradaDirectorio(nombre, i, 'D');
		}else System.out.println("No hay espacio suficiente");
	}
	
	public StringTokenizer devolverST(String ruta) throws Exception
	{
		StringTokenizer st = new StringTokenizer(ruta,"/");
		return st;
	}

	public void Mostrar() throws Exception{
		System.out.println("Introduzca directorio a mostrar (Ej: C/Caperta/Carpeta2\n");
		String ruta;
		ruta=br.readLine();
		int carpetaAnterior=0;
		StringTokenizer st = devolverST(ruta);
		String subStr;
		int i=0;
		while(st.hasMoreTokens()){
			subStr=st.nextToken();
			if(subStr.equals("C")){
				carpetaAnterior=1;
			}else{
				Directorio carpeta=(Directorio) datos[carpetaAnterior];
				while(!carpeta.carpeta[i].nombre.equals(subStr)){
					i++;
				}
				if(carpeta.carpeta[i].nombre.equals(subStr))
					carpetaAnterior=carpeta.carpeta[i].clusterInicio;
				else{
					System.out.println("Error de ruta\n");
					break;
				}
			}
		}
		Directorio carpeta=(Directorio) datos[carpetaAnterior];
		i=0;
		while(carpeta.carpeta[i]!=null){
			System.out.println(carpeta.carpeta[i].nombre);
		}
	}	
}
