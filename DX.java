public  class DX{
	private static String dx = "0000000000000000";
	public static int[] portas = {25,26}; //entrada e saida

	
	public static String getDX(){
		Main.portas[(portas[1]-1)] = 1;
		return dx;
	}
	public static void setDX(String x){
		Main.portas[(portas[0]-1)] = 1;
		dx = x;
	}
}