public class MBR{
	private static String valor = "0000000000000000000000000000000000000"; //tamanho 37 pois eh uma instrucao
	private static int[] portastoMEM ={21,22};
	private static int[] portastoUC ={4,5};

	public static String getMBRfromUC(){
		Main.portas[(portastoUC[1]-1)] = 1;
		return valor;
	}
	public static void setMBRfromUC(String a){
		Main.portas[(portastoUC[0]-1)] = 1;
		valor = a;
	}

	
	public static String getMBRtoMEM(){
		Main.portas[(portastoMEM[0]-1)] = 1;
		return valor;
		}
	public static void setMEMtoMBR(String a){
		Main.portas[(portastoMEM[1]-1)] = 1;
		valor = a;
	}



}