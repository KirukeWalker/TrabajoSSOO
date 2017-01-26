
public class SistemaOperativo{
	String[] procEjec=new String[20];
	SistemaDeFicheros FAT;
	Consola interfaz;
	
	public SistemaOperativo() throws Exception
	{
		procEjec[0]="SistemaOperativo";
		FAT = new SistemaDeFicheros();
		interfaz = new Consola(FAT);
		procEjec[1]="Consola";
		interfaz.start();
	}
	
	
}
