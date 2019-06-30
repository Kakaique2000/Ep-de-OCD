public  class IR {
	private static String conteudo = "0000000000000000000000000000000000000"; //endereco // inst = 5 bits do opcode+16bits do arg1+16bits do arg2 = 37bits
	private static int[] portas ={12,14,16,13,15}; //13 e 15 saida

	
	public static String getIR(){
		Main.portas[(portas[3]-1)] = 1;
		Main.portas[(portas[4]-1)] = 1;
		return conteudo;
	}
	public static void setIR(String aux){
		Main.portas[(portas[0]-1)] = 1;
		Main.portas[(portas[1]-1)] = 1;
		Main.portas[(portas[2]-1)] = 1;
		conteudo = aux;
	}

}
