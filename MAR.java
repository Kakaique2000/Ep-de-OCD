public class MAR{
	private static String conteudo = "0000000000000000"; 
	private static int[] portas ={3,20};

	
	public static String getMAR(){
		Main.portas[(portas[1]-1)] = 1;
		return conteudo;
	}
	public static void setMAR(String aux){
		Main.portas[(portas[0]-1)] = 1;
		conteudo = aux;
	}
}