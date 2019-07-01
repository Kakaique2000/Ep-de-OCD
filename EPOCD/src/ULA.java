import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

public class ULA{

	private static int[] portas = {17,18,19} ;
	
	private static boolean testeEqual = false;
	private static boolean testeGreater = false;
	private static boolean testeLower = false;

	private static void resetFlags(){
		testeEqual = false;
		testeGreater = false;
		testeLower = false;
	}

	public static String[] executa(String s){

		Main.portas[(portas[0]-1)] = 1;
		Main.portas[(portas[1]-1)] = 1;
		Main.portas[(portas[2]-1)] = 1;
		
		// Separacao dos comandos
		
		String opcode = s.substring(0,5); 
		int registra1 = Main.qualRegistrador(s.substring(5,21));
		int registra2 = Main.qualRegistrador(s.substring(21,37));

		String aux_comando_registra1 = s.substring(5,21);
		String aux_comando_registra2 = s.substring(21,37);

		boolean boolRegitrador1 = false;
		boolean boolRegistrador2 = false;

		String[] resp = new String[4];
		resp[0] = "0";
		resp[1] = "0";
		resp[2] = "0";
		resp[3] = "0";

		// se for diferente de "-1" significa que se trata de um registrador (AX, BX, CX ou DX)
		if(registra1 != -1){
			boolRegitrador1 = true;
			if(registra1 == 0) 
				aux_comando_registra1 = Main.axx.getAX();
			if(registra1 == 1)
				aux_comando_registra1 = Main.bxx.getBX();
			if(registra1 == 2)
				aux_comando_registra1 = Main.cxx.getCX();
			if(registra1 == 3)
				aux_comando_registra1 = Main.dxx.getDX();

		}
		if(registra2!= -1){ 
			boolRegistrador2 = true;
			if(registra2 == 0)
				aux_comando_registra2 = Main.axx.getAX();
			if(registra2 == 1)
				aux_comando_registra2 = Main.bxx.getBX();
			if(registra2 == 2)
				aux_comando_registra2 = Main.cxx.getCX();
			if(registra2 == 3)
				aux_comando_registra2 = Main.dxx.getDX();
		}

		// MOV
		if(opcode.equalsIgnoreCase(Main.array[0])){ 
			if(registra1 == 0){
				if(registra2 == 0)
					aux_comando_registra2 = Main.axx.getAX();
				if(registra2 == 1)
					aux_comando_registra2 = Main.bxx.getBX();
				if(registra2 == 2)
					aux_comando_registra2 = Main.cxx.getCX();
				if(registra2 == 3)
					aux_comando_registra2 = Main.dxx.getDX();
				Main.axx.setAX(aux_comando_registra2);
			}
			if(registra1 == 1){
				if(registra2 == 0)
					aux_comando_registra2 = Main.axx.getAX();
				if(registra2 == 1)
					aux_comando_registra2 = Main.bxx.getBX();
				if(registra2 == 2)
					aux_comando_registra2 = Main.cxx.getCX();
				if(registra2 == 3)
					aux_comando_registra2 = Main.dxx.getDX();
				Main.bxx.setBX(aux_comando_registra2);
			}

			if(registra1 == 2){
				if(registra2 == 0)
					aux_comando_registra2 = Main.axx.getAX();
				if(registra2 == 1)
					aux_comando_registra2 = Main.bxx.getBX();
				if(registra2 == 2)
					aux_comando_registra2 = Main.cxx.getCX();
				if(registra2 == 3)
					aux_comando_registra2 = Main.dxx.getDX();
				Main.cxx.setCX(aux_comando_registra2);
			}
			if(registra1 == 3){
				if(registra2 == 0)
					aux_comando_registra2 = Main.axx.getAX();
				if(registra2 == 1)
					aux_comando_registra2 = Main.bxx.getBX();
				if(registra2 == 2)
					aux_comando_registra2 = Main.cxx.getCX();
				if(registra2 == 3)
					aux_comando_registra2 = Main.dxx.getDX();
				Main.dxx.setDX(aux_comando_registra2);
			}
		
			return(resp);
		}

		// ADD
		if(opcode.equalsIgnoreCase(Main.array[1])){
			resp = add(aux_comando_registra1, aux_comando_registra2);
		}

		// SUB
		if(opcode.equalsIgnoreCase(Main.array[2])){
			resp = sub(aux_comando_registra1, aux_comando_registra2);
		}

		// MUL
		if(opcode.equalsIgnoreCase(Main.array[3])){
			resp = mul(aux_comando_registra1, aux_comando_registra2);
		}

		// DIV
		if(opcode.equalsIgnoreCase(Main.array[4])){
			resp = div(aux_comando_registra1, aux_comando_registra2);
		}

		// CMP
		if(opcode.equalsIgnoreCase(Main.array[5])){
			cmp(aux_comando_registra1,aux_comando_registra2);
			if(testeEqual) resp[3] = "0";
			if(testeLower) resp[3] = "-1";
			if(testeGreater) resp[3] = "1";
			return(resp);
		}

		// JMP
		if(opcode.equalsIgnoreCase(Main.array[6])){ 
			jmp(aux_comando_registra1,aux_comando_registra2);
			return(resp);
		}

		// JE
		if(opcode.equalsIgnoreCase(Main.array[7])){
			je(aux_comando_registra1,aux_comando_registra2);
			return(resp);
		}

		// JNE
		if(opcode.equalsIgnoreCase(Main.array[8])){
			jne(aux_comando_registra1,aux_comando_registra2);
			return(resp);
		}

		// JG
		if(opcode.equalsIgnoreCase(Main.array[9])){
			jg(aux_comando_registra1,aux_comando_registra2);
			return(resp);
		}

		// JL
		if(opcode.equalsIgnoreCase(Main.array[10])){
			jl(aux_comando_registra1,aux_comando_registra2);
			return(resp);
		}

		// JGE
		if(opcode.equalsIgnoreCase(Main.array[11])){
			jge(aux_comando_registra1,aux_comando_registra2);
			return(resp);
		}

		// JLE
		if(opcode.equalsIgnoreCase(Main.array[12])){
			jle(aux_comando_registra1,aux_comando_registra2);
			return(resp);
		}

		if(boolRegitrador1){
			if(registra1 == 0){
				if(registra2 == 0)
					aux_comando_registra2 = Main.axx.getAX();
				if(registra2 == 1)
					aux_comando_registra2 = Main.bxx.getBX();
				if(registra2 == 2)
					aux_comando_registra2 = Main.cxx.getCX();
				if(registra2 == 3)
					aux_comando_registra2 = Main.dxx.getDX();
				Main.axx.setAX(aux_comando_registra2);
			}
			if(registra1 == 1){
				if(registra2 == 0)
					aux_comando_registra2 = Main.axx.getAX();
				if(registra2 == 1)
					aux_comando_registra2 = Main.bxx.getBX();
				if(registra2 == 2)
					aux_comando_registra2 = Main.cxx.getCX();
				if(registra2 == 3)
					aux_comando_registra2 = Main.dxx.getDX();
				Main.bxx.setBX(aux_comando_registra2);
				}

			if(registra1 == 2){
				if(registra2 == 0)
					aux_comando_registra2 = Main.axx.getAX();
				if(registra2 == 1)
					aux_comando_registra2 = Main.bxx.getBX();
				if(registra2 == 2)
					aux_comando_registra2 = Main.cxx.getCX();
				if(registra2 == 3)
					aux_comando_registra2 = Main.dxx.getDX();
				Main.cxx.setCX(aux_comando_registra2);
			}
			if(registra1 == 3){
				if(registra2 == 0)
					aux_comando_registra2 = Main.axx.getAX();
				if(registra2 == 1)
					aux_comando_registra2 = Main.bxx.getBX();
				if(registra2 == 2)
					aux_comando_registra2 = Main.cxx.getCX();
				if(registra2 == 3)
					aux_comando_registra2 = Main.dxx.getDX();
				Main.dxx.setDX(aux_comando_registra2);
			}

		}

		if((boolRegitrador1 == true)&& (boolRegistrador2 == false)) {
				if(registra1 == 0){
					Main.axx.setAX(resp[0]);
				}
					if(registra1 == 1){
						Main.bxx.setBX(resp[0]);
					}
						if(registra1 == 2){
							Main.cxx.setCX(resp[0]);
						}
							if(registra1 == 3){
								Main.dxx.setDX(resp[0]);
							}
			}
		return(resp);
	}


	
	/*
		METODOS JUMPS
	*/
	private static void jmp(String a , String b){ 
		int valor = (Integer.parseInt(a,2)-1); 
		Main.janela.irPraLinha(valor);
		a = Integer.toBinaryString(valor);
		a = String.format("%16s",a).replace(' ', '0');
		PC.setPC(a);
	}
	private static void je(String a , String b){
		int valor = (Integer.parseInt(a,2)-1);		
		a = Integer.toBinaryString(valor);
		a = String.format("%16s",a).replace(' ', '0');
		//if(testeEqual == true)
		if(testeEqual == true) {
			PC.setPC(a);
			Main.janela.irPraLinha(valor);
		}
			
		resetFlags();
	}
	public static void jne(String a , String b){
		int valor = (Integer.parseInt(a,2)-1);		
		a = Integer.toBinaryString(valor);
		a = String.format("%16s",a).replace(' ', '0');
		if(testeEqual == false){
			PC.setPC(a);
			Main.janela.irPraLinha(valor);
		}
		resetFlags();
	}
	public static void jg(String a , String b){
		int valor = (Integer.parseInt(a,2)-1);		
		a = Integer.toBinaryString(valor);
		a = String.format("%16s",a).replace(' ', '0');
		if(testeGreater == true){
			PC.setPC(a);
			Main.janela.irPraLinha(valor);
		}
		resetFlags();
	}
	public static void jl(String a , String b){
		int valor = (Integer.parseInt(a,2)-1);		
		a = Integer.toBinaryString(valor);
		a = String.format("%16s",a).replace(' ', '0');
		if(testeLower == true){
			PC.setPC(a);
			Main.janela.irPraLinha(valor);
		}
		resetFlags();
	}
	public static void jge(String a , String b){
		int valor = (Integer.parseInt(a,2)-1);		
		a = Integer.toBinaryString(valor);
		a = a.substring((a.length()/2),a.length());
		if((testeEqual == true) || (testeGreater == true)){
			PC.setPC(a);
			Main.janela.irPraLinha(valor);
		}
		resetFlags();
	}
	public static void jle(String a , String b){
		int valor = (Integer.parseInt(a,2)-1);		
		a = Integer.toBinaryString(valor);
		a = a.substring((a.length()/2),a.length());
		if((testeEqual == true) || (testeLower == true)){
			PC.setPC(a);
			Main.janela.irPraLinha(valor);
		}
		resetFlags();
	}


	/*
		METODOS CALCULA
	*/
	private static String[] mul(String a , String b){
			int va = Integer.parseInt(a,2);
			int vb = Integer.parseInt(b,2);
			int soma = va*vb;
			int carry = 0;
			int zero = 0;
			if(soma > 32767) carry = 1;
			if(soma == 0) zero = 1;
			String res = Integer.toBinaryString(soma);
			if(soma < 0){
			res = res.substring(25,32);
			res = "1"+res.substring(0,(res.length()-1));
			}
			else res = String.format("%16s",res).replace(' ', '0');
			String[] resposta = new String[3];
			resposta[0] = res;
			resposta[1] = Integer.toString(carry);
			resposta[2] = Integer.toString(zero);
			return(resposta);
	}
	
	private static String[] div(String a , String b){
		int va = Integer.parseInt(a,2);
		int vb = Integer.parseInt(b,2);
		int soma = va/vb;
		int carry = 0;
		int zero = 0;
		if(soma > 32767) 
			carry = 1;
		if(soma == 0) 
			zero = 1;
		String res = Integer.toBinaryString(soma);
		if(soma < 0){
			res = res.substring(25,32);
			res = "1"+res.substring(0,(res.length()-1));
		}
		else 
			res = String.format("%16s",res).replace(' ', '0');
		String[] resposta = new String[3];
		resposta[0] = res;
		resposta[1] = Integer.toString(carry);
		resposta[2] = Integer.toString(zero);
		return(resposta);
	}

	private static String[] sub(String a , String b){ 
		int va = Integer.parseInt(a,2);
		int vb = Integer.parseInt(b,2);
		int soma = va-vb;
		int carry = 0;
		int zero = 0;
		if(soma > 32767) 
			carry = 1;
		if(soma == 0) 
			zero = 1;
		String res = Integer.toBinaryString(soma);
		if(soma < 0){
			res = res.substring(25,32);
			res = "1"+res.substring(0,(res.length()-1));
		}
		else 
			res = String.format("%16s",res).replace(' ', '0');
		String[] resposta = new String[3];
		resposta[0] = res;
		resposta[1] = Integer.toString(carry);
		resposta[2] = Integer.toString(zero);
		return(resposta);
	}

	private static String[] add(String a , String b){
		int va = Integer.parseInt(a,2);
		int vb = Integer.parseInt(b,2);
		int soma = va+vb;
		int carry = 0;
		int zero = 0;
		if(soma > 32767) 
			carry = 1;
		if(soma == 0) 
			zero = 1;
		String res = Integer.toBinaryString(soma);
		if(soma < 0){
			res = res.substring(25,32);
			res = "1"+res.substring(0,(res.length()-1));
		}
		else 
			res = String.format("%16s",res).replace(' ', '0');
		String[] resposta = new String[3];
		resposta[0] = res;
		resposta[1] = Integer.toString(carry);
		resposta[2] = Integer.toString(zero);
		return(resposta);
	}

	private static void cmp(String a, String b){
		resetFlags();
		int va = Integer.parseInt(a,2);
		int vb = Integer.parseInt(b,2);
		if(va == vb) 
			testeEqual = true;
		if(va > vb)
			testeGreater = true;
		if(va < vb)
			testeLower = true;
	
	}
}