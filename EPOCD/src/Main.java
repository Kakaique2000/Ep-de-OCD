import java.util.*;
import java.awt.EventQueue;
import java.io.*;

public class Main {
	
	public static InterfaceGrafica janela;
	
	public static void restart() {
		
		pcc.setPC("0000000000000000");
		irr.setIR("0000000000000000000000000000000000000");
		mbrr.setMBRfromUC("0000000000000000000000000000000000000");
		marr.setMAR("0000000000000000");
	}

	public static AX axx = new AX();
	public static BX bxx = new BX();
    public static CX cxx = new CX();
	public static DX dxx = new DX();

	public static PC pcc = new PC();
	public static IR irr = new IR();
	public static MBR mbrr = new MBR();
	public static MAR marr = new MAR();

	public static ULA ulaa = new ULA();
	public static Memoria memoriaa = new Memoria();
    public static int[] portas = new int[26];

	public static String[] array = new String[]{ "00001", "00010", "00011", "00100", "00101", "00110", "00111", "01000", "01001", "01010","01011", "01100", "01101" };
	public static String[] relacao = { "MOV", "ADD", "SUB", "MUL", "DIV", "CMP", "JMP", "JE ", "JNE", "JG ", "JL ","JGE", "JLE" }; 

	public static String[] registradores = new String[]{"1000000000000000","0100000000000000","1100000000000000","0010000000000000"};

	public static String convert16Binary(String s){
		int valor = 0;
		String[] registradores_aux = new String[]{"AX","BX","CX","DX"};
		String resp = "00000000000000";

			if (s.equalsIgnoreCase(registradores_aux[0])){
				resp = registradores[0]; 
				return(resp);
			}
			if(s.equalsIgnoreCase(registradores_aux[1])){
				resp = registradores[1];
				return(resp);
			}
			if(s.equalsIgnoreCase(registradores_aux[2])){
				resp = registradores[2];
				return(resp);
			}
			if(s.equalsIgnoreCase(registradores_aux[3])){
				resp = registradores[3];
				return(resp);
			}

		valor = Integer.parseInt(s);
	    s = Integer.toBinaryString(valor);
		s = String.format("%16s",s).replace(' ', '0');
		return(s);
	}

	// Lê o arquivo e armazena na memoria
	public static void leituraDoArquivo(String codigo) {
        try {
            BufferedReader reader = new BufferedReader(new StringReader(codigo));
            String line;
            line = reader.readLine();
            int a = 0;
            while (line != null && line.length() > 2) {
				String opcode = tradutorOpCode(line.substring(0,3));       
				String novaL = line.substring(4,line.length());
				String[] args = novaL.split(",");
				if(line.length()<= 4){
					novaL = line;
					args = novaL.split("\\s");
					args[0] = args[1];
					args[1] = "0000000000000000";
				}
				String arg1 = convert16Binary(args[0]);
				String arg2 = "0000000000000000";
				if(args.length > 1)
					arg2 = convert16Binary(args[1]);
				memoriaa.mem[a] = opcode+arg1+arg2;
                a++;
                line = reader.readLine();
            }
            memoriaa.mem[a] = "EXIT";
        } 
        catch (Exception e) {
        	e.printStackTrace();
        }; 
    }

    // tradutorOpCode o opCode de "texto" para "numerico" -> exemplo: "ADD" vira "00010"
	public static String tradutorOpCode(String s){
		for(int i = 0 ; i < relacao.length ; i++) 
			if(s.equalsIgnoreCase(relacao[i])) 
				return (array[i]);	
		return null;
	}

	public static void resetPortas(){
		for(int i = 0 ; i < portas.length ; i++)
			portas[i] = 0;
		
	}

	public static void imprimePortasTabela(){
		String settingString = "";
		for(int i = 0 ; i < portas.length/2 ; i++)
			settingString += 	"Porta " + (i+1) +": " + portas[i]+ "\t"  + "Porta " + (i+14) +": " + portas[i+13]+ " \n";
		janela.settaPortasEstado(settingString);
		
	}

	public static void imprimePortasHorizontal(){
		for(int i = 0 ; i < portas.length ; i++)
			System.out.print(portas[i]);
	}

	public static void imprime(boolean[] a) { 
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] ? "1 " : "0 ");
        }
        System.out.println();
    }

    // Atualiza o PC
    public static String incPC(String comandoPC){
		//Valor Maximo
		String teste = "1111111111111111"; 
		if(comandoPC.equalsIgnoreCase(teste)) {
			comandoPC = "0000000000000000";
			return(comandoPC);
		}
		int valor = (Integer.parseInt(comandoPC,2)+1);
		comandoPC = Integer.toBinaryString(valor);
		comandoPC = String.format("%16s",comandoPC).replace(' ', '0');
		return(comandoPC);
	}

	public static int qualRegistrador(String a){
		for(int i = 0 ; i < registradores.length ; i++){
			if(a.equalsIgnoreCase(registradores[i])) {	
				return i;
			}
		}
		return -1;
	}
	
	public static void executarCodigo(String codigo) throws IOException {
		
		restart();
		resetPortas();
		leituraDoArquivo(codigo);
		
		String auxIR = "0";
        while (auxIR != null) {
           marr.setMAR(pcc.getPC());
		   memoriaa.getMemoria(); 
		   irr.setIR(mbrr.getMBRfromUC());
		   auxIR = irr.getIR();
		   
		   if(auxIR == null) {
			  break;
		   }
		  if(auxIR.equalsIgnoreCase("EXIT")) {
			  break; 
		  }
		  
	
		  	
		   
		   String[] retorno = new String[4];
		   retorno = ulaa.executa(auxIR); 
		   pcc.setPC(incPC(pcc.getPC()));

//		 
//		   System.out.println("Flags:");
//		   System.out.println("Overflow flag(of): "+retorno[1]);
//		   System.out.println("Zero flag(zf): "+retorno[2]);
		   janela.setRegistradorLabel("Overflow", retorno[1]);
		   janela.setRegistradorLabel("Zero", retorno[2]);
		   janela.setRegistradorLabel("sign", retorno[3]);
		   
//		   System.out.println();

//		   System.out.print("PALAVRA HORIZONTAL: ");
//		   imprimePortasHorizontal();
//		   System.out.print(retorno[1]);
//           System.out.println(retorno[2]);		   
//		   System.out.println();

		   // Imprime o estado das portas "1" =  aberta e "0" = fechada
//		   System.out.println("ESTADO DAS PORTAS:");
//		   imprimePortasTabela();
//		   System.out.println();

		   // Imprime o estado atual dos registradores
//		   System.out.println("Registradores:");
//		   System.out.println("AX: "+axx.getAX()); 
//		   System.out.println("BX: "+bxx.getBX());
//		   System.out.println("CX: "+cxx.getCX());
//		   System.out.println("DX: "+dxx.getDX());
		   
		   janela.setRegistradorLabel("AX", axx.getAX());
		   janela.setRegistradorLabel("BX", bxx.getBX());
		   janela.setRegistradorLabel("CX", cxx.getCX());
		   janela.setRegistradorLabel("DX", dxx.getDX());
		   

		   // Isso aqui ta dando uma cagada, MBR e IR nao tao funcionando
//		   System.out.println("PC: "+pcc.getPC()); 
//		   System.out.println("MAR: "+marr.getMAR());
//		   System.out.println("MBR: "+mbrr.getMBRfromUC());
//		   System.out.println("IR: "+irr.getIR());
		   
		   janela.setRegistradorLabel("PC", pcc.getPC());
		   janela.setRegistradorLabel("MAR", marr.getMAR());
		   janela.setRegistradorLabel("MBR", mbrr.getMBRfromUC());
		   janela.setRegistradorLabel("IR", irr.getIR());
		   

		   resetPortas();
		   

		  
        }
        
       
        System.out.println("Fim da execucao");
       
        
	}


    public static void main(String[] args) throws IOException{
	
		
		//Interface grafica inicializador
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela = new InterfaceGrafica();
					janela.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
				
		
        
      
    }
}




