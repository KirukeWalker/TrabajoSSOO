
public class SistemaOperativo {

	SistemaDeFicheros FAT;
	Consola interfaz;
	
	public SistemaOperativo()
	{
		FAT = new SistemaDeFicheros();
		interfaz = new Consola();
	}
	
	
}
