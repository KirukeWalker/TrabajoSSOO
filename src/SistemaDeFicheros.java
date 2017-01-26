import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SistemaDeFicheros extends Thread {
	Cluster[] datos;
	Metadato[] metadatos;
	private int tamanyo = 20;
	Directorio raiz;
	boolean running;
	
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
		running=true;
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
			if(metadatos[i].siguiente!=null){
				System.out.println("Siguiente: "+metadatos[i].siguiente.posicion+"\n");
			}
			else{System.out.println("Siguiente: NULL\n");}
		}
	}
	
	public void crearArchivo(String contenido)
	{
		int a=0;
		for(int i=0; i<metadatos.length; i++){
			if(metadatos[i].disponible==true) a++;
		}
		if(a<=0) System.out.println("no hay espacio suficiente");
		else{
			int i=0;
			int posicionRaiz=0;
			while(raiz.carpeta[posicionRaiz]!=null) 
				posicionRaiz++;
			while(metadatos[i].disponible!=true)
				i++;
			metadatos[i].disponible = false;
			datos[i]= new Archivo(contenido);
			raiz.carpeta[posicionRaiz]=new EntradaDirectorio(contenido, i, 'A');
			metadatos[i].fin=true;
			}
		datos[0]=raiz;
	}
	
	public void crearArchivo(String contenido, String directorio)
	{
		Directorio dir=new Directorio(" ");
		int i=0;
		int posDirDatos=0;
		while(!datos[posDirDatos].contenido.equals(directorio)){
			posDirDatos++;
		}
		if(datos[posDirDatos].contenido.equals(directorio)){
			dir=(Directorio)datos[posDirDatos];
		}
		else System.out.println("El directorio no existe");
		int a=0;
		for(i=0; i<metadatos.length; i++){
			if(metadatos[i].disponible==true) a++;
		}
		if(a<=0) System.out.println("no hay espacio suficiente");
		else{
			i=0;
			int posicionRaiz=0;
			while(dir.carpeta[posicionRaiz]!=null) 
				posicionRaiz++;
			while(metadatos[i].disponible!=true)
				i++;
			metadatos[i].disponible = false;
			datos[i]= new Archivo(contenido);
			dir.carpeta[posicionRaiz]=new EntradaDirectorio(contenido, i, 'A');
			datos[posDirDatos]=dir;
			metadatos[i].fin=true;
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
	
	public void mover(String nombre, String origen, String destino){
		Directorio dir = new Directorio(" ");
		Directorio der = new Directorio(" ");
		EntradaDirectorio enDir = new EntradaDirectorio(nombre, 0, 'a');
		
		int i=0;
		
		int clusterOrigen=0;
		while(!datos[clusterOrigen].contenido.equals(origen)){
			clusterOrigen++;
		}
		if(datos[clusterOrigen].contenido.equals(origen)){
			dir=(Directorio)datos[clusterOrigen];
			
			int clusterDestino=0;
			while(!datos[clusterDestino].contenido.equals(origen)){
				clusterDestino++;
			}
			if(datos[clusterDestino].contenido.equals(origen)){
				der=(Directorio)datos[clusterDestino];
				
				int j=0;
				while(dir.carpeta[j]!=null){
					if(!dir.carpeta[j].nombre.equals(nombre)){
						j++;
					}else break;
				}
				
				if(dir.carpeta[j].nombre.equals(nombre)){
					enDir = dir.carpeta[j];
					
					dir.carpeta[j].nombre=null;
					dir.carpeta[j].clusterInicio=-1;
					dir.carpeta[j].tipo=' ';
					
					int p=0;
					while(der.carpeta[p] != null){
						p++;
					}
					if(der.carpeta[p]==null){
						der.carpeta[p]=enDir;
					}
					datos[clusterDestino]=der;
					datos[clusterOrigen]=dir;
				}
			}
			else System.out.println("El directorio no existe");
		}
		else System.out.println("El directorio no existe");	
	}
	
	public void eliminarArchivo(String nom, String lugar){
		int clusterOrigen=0;
		while(!datos[clusterOrigen].contenido.equals(lugar)){
			clusterOrigen++;
		}
		int i=0;
		Directorio dir=new Directorio(" ");
		dir=(Directorio)datos[clusterOrigen];
		while(!dir.carpeta[i].nombre.equals(nom)){
			i++;
		}
		if(dir.carpeta[i].nombre.equals(nom)){
			dir.carpeta[i].nombre=null;
			dir.carpeta[i].clusterInicio=-1;
			dir.carpeta[i].tipo=' ';
		}
	}
	
	public void eliminarDirectorio(String nomb){
		int clusterOrigen=0;
		while(!datos[clusterOrigen].contenido.equals(nomb)){
			clusterOrigen++;
		}
		Directorio dir=new Directorio(" ");
		dir=(Directorio)datos[clusterOrigen];
		int i=0;
		while(dir.carpeta[i]!=null){
			int aux=dir.carpeta[i].clusterInicio;
			metadatos[aux].disponible=true;
			i++;
		}
		metadatos[clusterOrigen].disponible=true;
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
	
	public void run(){
		while(running){
			eliminarDirectorio("tmp");
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void killRun(){
		running=false;
	}
}
