
public class SistemaOperativo {

	SistemaDeFicheros FAT;
	Consola interfaz;
	
	public SistemaOperativo() throws Exception
	{
		FAT = new SistemaDeFicheros();
		interfaz = new Consola(FAT);
	}
	
	
}
