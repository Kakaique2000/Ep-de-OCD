public  class BX{
	private static String bx = "0000000000000000";
	public static int[] portas = {8,9}; //entrada e saida
	public static String getBX(){
		Main.portas[(portas[1]-1)] = 1;
		return bx;
	}
	public static void setBX(String b){
		Main.portas[(portas[0]-1)] = 1;
		bx = b;
	}
}