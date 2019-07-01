public class Memoria{
	public static int fimcodigo = 0;
	public  static String[] mem = new String[65536]; 
	private  static int[] portas ={23,24};
	
	public static void getMemoria() {
		Main.portas[(portas[1]-1)] = 1;
		MBR.setMEMtoMBR(mem[Integer.parseInt(MAR.getMAR(),2)]);
	}
	
	public static void setMemoria(){
		Main.portas[(portas[0]-1)] = 1;
		mem[Integer.parseInt(MAR.getMAR(),2)] = MBR.getMBRtoMEM(); 
		fimcodigo++;
	}
}