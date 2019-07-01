import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.security.auth.callback.TextOutputCallback;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;

public class InterfaceGrafica {

	JFrame frame;
	private JTextField textAx;
	private JTextField textBx;
	private JTextField textCx;
	private JTextField textDx;
	private JTextField textMar;
	private JTextField textMbr;
	private JTextField textIr;
	private JTextField textPc;
	private JTextPane textPane;
	private JTextPane portas_pane;
	public int quantidadeLinhasPreCursor;
	private boolean flagExecutouComando = false;
	private JTextField textZero;
	private JTextField textOverflow;
	private JTextField textSign;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public InterfaceGrafica() {
		initialize();
	}
	
	public static String setPC(int p){
		String res;
		res = Integer.toBinaryString(p);
		res = String.format("%16s",res).replace(' ', '0');
		return res;
	}
	
	public void settaPortasEstado (String s){
		portas_pane.setText(s);
	}
	
	public void irPraLinha(int linha) {
		
		
		
		String textoJanela = textPane.getText();
		int posCursor = 0;
		
		int comecoLinhaSelecionada = 0;
		
		int posFimLinhaCursor = textoJanela.length();
		int posInicioLinhaCursor = 0;
		
		quantidadeLinhasPreCursor = 0;
		int linhasCursorBuffer = posCursor + 1;
		for(int i = 0; i<textoJanela.length()-2; i++) {
			if(textoJanela.charAt(i) == '\n') {
				linhasCursorBuffer++;
				quantidadeLinhasPreCursor++;
				if(quantidadeLinhasPreCursor == linha) {
					System.out.println("caractere atual" + posCursor);
					System.out.println("Indo para caractere: " + (i + quantidadeLinhasPreCursor));
					textPane.setCaretPosition(i);
					posCursor = i;
					break;
				}
			}
		}
		
		
		
		//pegando a pos inicial
		for(int i = posCursor + quantidadeLinhasPreCursor-1; i>=0; i--) {
			
			posInicioLinhaCursor = i;
			if(textoJanela.charAt(i) == '\n') {
				posInicioLinhaCursor = i+1;
				if(posCursor + quantidadeLinhasPreCursor + linha >= textoJanela.length()) posInicioLinhaCursor = i;
				break;
			}
		}
		
		//pegando a pos final
		for(int i = posInicioLinhaCursor; i<textoJanela.length(); i++) {
			
			if(textoJanela.charAt(i) == '\n' && i!=posInicioLinhaCursor ) {
				posFimLinhaCursor = i-1;
				if(linha == 0) posFimLinhaCursor = i;
				break;
			}
		}
		
		
		textPane.grabFocus();
		textPane.select(posInicioLinhaCursor - quantidadeLinhasPreCursor, posFimLinhaCursor - quantidadeLinhasPreCursor);
		
		
		
		
		
	}
	
	private void clickExecutarLinha(){
		
		if(flagExecutouComando) try {
			textPane.setCaretPosition(textPane.getCaretPosition()+1);
		}
		catch(Exception e) {
			
		}
		
		String textoJanela = textPane.getText();
		
	
		int posCursor = textPane.getCaretPosition();
		
		
		
		
		
		
		
		int comecoLinhaSelecionada = 0;
		
		int posFimLinhaCursor = textoJanela.length();
		int posInicioLinhaCursor = 0;
		
		
		quantidadeLinhasPreCursor = 0;
		int linhasCursorBuffer = posCursor + 1;
		for(int i = 0; i<linhasCursorBuffer && i<textoJanela.length()-2; i++) {
			if(textoJanela.charAt(i) == '\n') {
				linhasCursorBuffer++;
				quantidadeLinhasPreCursor++;
			}
		}
		
			//pegando a pos inicial
		for(int i = posCursor + quantidadeLinhasPreCursor-1; i>=0; i--) {
			
			posInicioLinhaCursor = i;
			if(textoJanela.charAt(i) == '\n') {

				posInicioLinhaCursor = i+1;
				break;
			}
		}
		
		//pegando a pos final
		for(int i = posInicioLinhaCursor; i<textoJanela.length(); i++) {
			
			if(textoJanela.charAt(i) == '\n' && i!=posInicioLinhaCursor ) {
				posFimLinhaCursor = i-1;
				break;
			}
		}
		
		
		
		textPane.grabFocus();
		textPane.select(posInicioLinhaCursor - quantidadeLinhasPreCursor, posFimLinhaCursor - quantidadeLinhasPreCursor);
		flagExecutouComando = true;
		try {
			Main.executarCodigo((textPane.getSelectedText()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textPc.setText(setPC(quantidadeLinhasPreCursor+1));
		textMar.setText(setPC(quantidadeLinhasPreCursor));
		
	}
	
	
	public void setRegistradorLabel(String registrador, String value) {
		switch(registrador.toLowerCase()) {
		case "ax":
			textAx.setText(value);
		break;
		case "bx":
			textBx.setText(value);
		break;
		case "cx":
			textCx.setText(value);
		break;
		case "dx":
			textDx.setText(value);
		break;
		case "pc":
			textPc.setText(value);
		break;
		case "mar":
			textMar.setText(value);
		break;
		case "mbr":
			textMbr.setText(value);
		break;
		case "ir":
			textIr.setText(value);
		break;
		case "overflow":
			textOverflow.setText(value);
		break;
		case "zero":
			textZero.setText(value);
		break;
		case "sign":
			textSign.setText(value);
		break;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 944, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textPane = new JTextPane();
		textPane.setBounds(10, 38, 282, 384);
		frame.getContentPane().add(textPane);
		
		
		
		
		
		
		// ========================== Botao executar =======================================
		JButton btnExecutar = new JButton("Executar");
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					
					Main.executarCodigo(textPane.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnExecutar.setBounds(155, 428, 137, 30);
		frame.getContentPane().add(btnExecutar);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(322, 266, 214, 143);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textDx = new JTextField();
		textDx.setBounds(40, 111, 157, 20);
		panel.add(textDx);
		textDx.setText("0000000000000000");
		textDx.setHorizontalAlignment(SwingConstants.CENTER);
		textDx.setBackground(Color.WHITE);
		textDx.setFont(new Font("Lucida Console", Font.BOLD, 13));
		textDx.setEditable(false);
		textDx.setColumns(10);
		
		textCx = new JTextField();
		textCx.setBounds(40, 79, 157, 20);
		panel.add(textCx);
		textCx.setText("0000000000000000");
		textCx.setHorizontalAlignment(SwingConstants.CENTER);
		textCx.setBackground(Color.WHITE);
		textCx.setFont(new Font("Lucida Console", Font.BOLD, 13));
		textCx.setEditable(false);
		textCx.setColumns(10);
		
		textBx = new JTextField();
		textBx.setBounds(40, 47, 157, 20);
		panel.add(textBx);
		textBx.setText("0000000000000000");
		textBx.setHorizontalAlignment(SwingConstants.CENTER);
		textBx.setBackground(Color.WHITE);
		textBx.setFont(new Font("Lucida Console", Font.BOLD, 13));
		textBx.setEditable(false);
		textBx.setColumns(10);
		
		textAx = new JTextField();
		textAx.setBounds(40, 16, 157, 20);
		panel.add(textAx);
		textAx.setText("0000000000000000");
		textAx.setHorizontalAlignment(SwingConstants.CENTER);
		textAx.setBackground(Color.WHITE);
		textAx.setFont(new Font("Lucida Console", Font.BOLD, 13));
		textAx.setEditable(false);
		textAx.setColumns(10);
		// ========================== Botao executar =======================================
		
		
		
		
		
		
		JLabel lblAx = new JLabel("AX");
		lblAx.setBounds(10, 11, 32, 30);
		panel.add(lblAx);
		lblAx.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblBx = new JLabel("BX");
		lblBx.setBounds(10, 42, 32, 30);
		panel.add(lblBx);
		lblBx.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblCx = new JLabel("CX");
		lblCx.setBounds(10, 75, 32, 30);
		panel.add(lblCx);
		lblCx.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblDx = new JLabel("DX");
		lblDx.setBounds(10, 106, 32, 30);
		panel.add(lblDx);
		lblDx.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblInsiraSeuCdigo = new JLabel("Registradores aritim\u00E9ticos");
		lblInsiraSeuCdigo.setBounds(322, 243, 166, 19);
		frame.getContentPane().add(lblInsiraSeuCdigo);
		lblInsiraSeuCdigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblInsiraSeuCdigo_1 = new JLabel("Insira seu c\u00F3digo aqui");
		lblInsiraSeuCdigo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInsiraSeuCdigo_1.setBounds(10, 11, 166, 19);
		frame.getContentPane().add(lblInsiraSeuCdigo_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBackground(SystemColor.controlHighlight);
		panel_1.setBounds(320, 67, 401, 152);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPc = new JLabel("PC");
		lblPc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPc.setBounds(20, 17, 25, 30);
		panel_1.add(lblPc);
		
		textPc = new JTextField();
		textPc.setText("0000000000000000");
		textPc.setHorizontalAlignment(SwingConstants.CENTER);
		textPc.setFont(new Font("Lucida Console", Font.BOLD, 13));
		textPc.setEditable(false);
		textPc.setColumns(10);
		textPc.setBackground(Color.WHITE);
		textPc.setBounds(48, 21, 165, 20);
		panel_1.add(textPc);
		
		textMar = new JTextField();
		textMar.setBounds(48, 51, 165, 20);
		panel_1.add(textMar);
		textMar.setText("0000000000000000");
		textMar.setHorizontalAlignment(SwingConstants.CENTER);
		textMar.setFont(new Font("Lucida Console", Font.BOLD, 13));
		textMar.setEditable(false);
		textMar.setColumns(10);
		textMar.setBackground(Color.WHITE);
		
		JLabel lblMar = new JLabel("MAR");
		lblMar.setBounds(12, 47, 32, 30);
		panel_1.add(lblMar);
		lblMar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblMbr = new JLabel("MBR");
		lblMbr.setBounds(12, 78, 32, 30);
		panel_1.add(lblMbr);
		lblMbr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textMbr = new JTextField();
		textMbr.setBounds(48, 82, 343, 20);
		panel_1.add(textMbr);
		textMbr.setText("0000000000000000000000000000000000000");
		textMbr.setHorizontalAlignment(SwingConstants.CENTER);
		textMbr.setFont(new Font("Lucida Console", Font.BOLD, 13));
		textMbr.setEditable(false);
		textMbr.setColumns(10);
		textMbr.setBackground(Color.WHITE);
		
		textIr = new JTextField();
		textIr.setBounds(48, 114, 343, 20);
		panel_1.add(textIr);
		textIr.setText("0000000000000000000000000000000000000");
		textIr.setHorizontalAlignment(SwingConstants.CENTER);
		textIr.setFont(new Font("Lucida Console", Font.BOLD, 13));
		textIr.setEditable(false);
		textIr.setColumns(10);
		textIr.setBackground(Color.WHITE);
		
		JLabel lblIr = new JLabel("IR");
		lblIr.setBounds(26, 108, 21, 30);
		panel_1.add(lblIr);
		lblIr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblRegistradoresDeControle = new JLabel("Registradores de controle");
		lblRegistradoresDeControle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistradoresDeControle.setBounds(320, 42, 166, 19);
		frame.getContentPane().add(lblRegistradoresDeControle);
		
		
		// ================== BOTAO de executar LINHA ======================
		JButton btnExecutarLinha = new JButton("Executar linha");
		btnExecutarLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickExecutarLinha();
			}
		});
		btnExecutarLinha.setBounds(10, 428, 137, 30);
		frame.getContentPane().add(btnExecutarLinha);
		
		JLabel lblFlags = new JLabel("Flags");
		lblFlags.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFlags.setBounds(558, 244, 40, 19);
		frame.getContentPane().add(lblFlags);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBackground(SystemColor.controlHighlight);
		panel_2.setBounds(546, 266, 175, 108);
		frame.getContentPane().add(panel_2);
		
		textZero = new JTextField();
		textZero.setText("0");
		textZero.setHorizontalAlignment(SwingConstants.CENTER);
		textZero.setFont(new Font("Lucida Console", Font.BOLD, 13));
		textZero.setEditable(false);
		textZero.setColumns(10);
		textZero.setBackground(Color.WHITE);
		textZero.setBounds(133, 45, 24, 20);
		panel_2.add(textZero);
		
		textOverflow = new JTextField();
		textOverflow.setText("0");
		textOverflow.setHorizontalAlignment(SwingConstants.CENTER);
		textOverflow.setFont(new Font("Lucida Console", Font.BOLD, 13));
		textOverflow.setEditable(false);
		textOverflow.setColumns(10);
		textOverflow.setBackground(Color.WHITE);
		textOverflow.setBounds(133, 18, 24, 20);
		panel_2.add(textOverflow);
		
		JLabel lblOverflowTag = new JLabel("Overflow flag (of)");
		lblOverflowTag.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOverflowTag.setBounds(10, 11, 120, 30);
		panel_2.add(lblOverflowTag);
		
		JLabel lblZeroFlagzf = new JLabel("Zero flag(zf)");
		lblZeroFlagzf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblZeroFlagzf.setBounds(43, 38, 89, 30);
		panel_2.add(lblZeroFlagzf);
		
		JLabel lblCompare = new JLabel("Sign flag(sf)");
		lblCompare.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCompare.setBounds(47, 70, 89, 30);
		panel_2.add(lblCompare);
		
		textSign = new JTextField();
		textSign.setText("0");
		textSign.setHorizontalAlignment(SwingConstants.CENTER);
		textSign.setFont(new Font("Lucida Console", Font.BOLD, 13));
		textSign.setEditable(false);
		textSign.setColumns(10);
		textSign.setBackground(Color.WHITE);
		textSign.setBounds(133, 74, 24, 20);
		panel_2.add(textSign);
		
		JLabel lblPortas = new JLabel("Portas");
		lblPortas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPortas.setBounds(736, 44, 46, 14);
		frame.getContentPane().add(lblPortas);
		
		portas_pane = new JTextPane();
		portas_pane.setBackground(SystemColor.window);
		portas_pane.setEditable(false);
		portas_pane.setBounds(736, 67, 166, 223);
		frame.getContentPane().add(portas_pane);
		
		
		
		
	}
	
}
