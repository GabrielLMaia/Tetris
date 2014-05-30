package jogo;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import peças.Bloco;
import peças.Peça;

public class Hold extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static int LARGURA_AREA = 2;
	private final static int COMPRIMENTO_AREA = 4;

	private static Peça peçaHold = null;
	
	private static Bloco[][] holdMatriz = new Bloco[LARGURA_AREA][COMPRIMENTO_AREA];
	
	//seta a imagem e inicializa a matriz onde a peça "hold" ficará
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
	//apaga a peça hold,usado no reser
	public static void apagarHold(){
		if(peçaHold!=null){
		peçaHold.apagar();
		peçaHold=null;
		}
	}
	//pega a peça hold
	public static Peça getPeçaHold(){
		return peçaHold;
	}
	//pega a peça atual é dá "hold",se já tiver alguma peça hold, ela irá virar a peça atual
	public static void hold() {
		if (peçaHold == null) {
			peçaHold = Tetris.getPeçaAtual();
			peçaHold.apagar();
			Tetris.pegarDaLista();
		} else {
			peçaHold.apagar();
			Peça peçaAux=Tetris.getPeçaAtual();
			peçaAux.apagar();
			Tetris.pegarHold();
			peçaHold = peçaAux;
		}
		peçaHold.criar(1, 2, holdMatriz);
	}

}
