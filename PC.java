public  class PC{
	private static String pc = "0000000000000000";
	private static int[] portas ={1,2};
	
	
	public static String getPC(){
		Main.portas[(portas[1]-1)] = 1;
		return pc;
	}
	public static void setPC(String p){
		Main.portas[(portas[0]-1)] = 1;
		pc = p;
	}
}