package jogo;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pe�as.Bloco;
import pe�as.Pe�a;

public class Hold extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static int LARGURA_AREA = 2;
	private final static int COMPRIMENTO_AREA = 4;

	private static Pe�a pe�aHold = null;
	
	private static Bloco[][] holdMatriz = new Bloco[LARGURA_AREA][COMPRIMENTO_AREA];
	
	//seta a imagem e inicializa a matriz onde a pe�a "hold" ficar�
	public Hold() {
		this.setLayout(null);
		this.setBackground(Color.green);
		JPanel areaHold = new JPanel();
		areaHold.setBounds(30, 30, 30 * COMPRIMENTO_AREA, 30 * LARGURA_AREA);
		areaHold.setLayout(new GridLayout(LARGURA_AREA, COMPRIMENTO_AREA, 0, 0));
		for (int i = 0; i < LARGURA_AREA; i++) {
			for (int j = 0; j < COMPRIMENTO_AREA; j++) {
				holdMatriz[i][j] = new Bloco(i, j, holdMatriz);
				areaHold.add(holdMatriz[i][j].getBloco());
			}
		}
		this.add(areaHold);

		JLabel holdImagem = new JLabel("");
		holdImagem.setIcon(new ImageIcon(getClass().getResource(
				"/imagens/HOLD.png")));
		holdImagem.setBounds(0, 0, 150, 120);
		this.add(holdImagem);
	}
	//apaga a pe�a hold,usado no reser
	public static void apagarHold(){
		if(pe�aHold!=null){
		pe�aHold.apagar();
		pe�aHold=null;
		}
	}
	//pega a pe�a hold
	public static Pe�a getPe�aHold(){
		return pe�aHold;
	}
	//pega a pe�a atual � d� "hold",se j� tiver alguma pe�a hold, ela ir� virar a pe�a atual
	public static void hold() {
		if (pe�aHold == null) {
			pe�aHold = Tetris.getPe�aAtual();
			pe�aHold.apagar();
			Tetris.pegarDaLista();
		} else {
			pe�aHold.apagar();
			Pe�a pe�aAux=Tetris.getPe�aAtual();
			pe�aAux.apagar();
			Tetris.pegarHold();
			pe�aHold = pe�aAux;
		}
		pe�aHold.criar(1, 2, holdMatriz);
	}

}
