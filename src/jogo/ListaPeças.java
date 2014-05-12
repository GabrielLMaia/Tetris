package jogo;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lista.Lista;
import lista.No;
import peças.*;

public class ListaPeças extends JPanel {
	private static final long serialVersionUID = 1L;

	private final static int TAMANHO = 7;
	private final int LARGURA_LISTA = 13;
	private final int LARGURA_REAL_LISTA = LARGURA_LISTA + 1;
	private final int COMPRIMENTO_LISTA = 4;
	private final static int POSIÇÃO_COLUNA = 2;
	private final static int[] POSIÇÕES_LINHA = new int[] { 1, 4, 7, 10, 13 };

	private static Lista<Peça> lista = new Lista<Peça>();
	private static Bloco[][] blocosLista;
	private static char[] bag = new char[] { 't', 'l', 's', 'z', 'o', 'j', 'i' };
	private static boolean[] repetido = new boolean[TAMANHO];

	public ListaPeças() {
		this.setLayout(null);

		blocosLista = new Bloco[LARGURA_REAL_LISTA][COMPRIMENTO_LISTA];

		JLabel divLista = new JLabel("");
		divLista.setIcon(new ImageIcon(getClass().getResource(
				"/imagens/divLinha.png")));
		divLista.setBounds(0, 90, 120, 30);
		this.add(divLista);

		JPanel areaLista = new JPanel();
		areaLista.setBounds(0, 30, 120, 30 * LARGURA_REAL_LISTA);
		areaLista.setLayout(new GridLayout(LARGURA_REAL_LISTA,
				COMPRIMENTO_LISTA, 0, 0));
		for (int i = 0; i < LARGURA_REAL_LISTA; i++) {
			for (int j = 0; j < COMPRIMENTO_LISTA; j++) {
				blocosLista[i][j] = new Bloco(i, j, blocosLista);
				areaLista.add(blocosLista[i][j].getBloco());
			}
		}
		this.add(areaLista);

		JLabel imagemLista = new JLabel("");
		imagemLista.setIcon(new ImageIcon(getClass().getResource(
				"/imagens/List.png")));
		imagemLista.setBounds(0, 0, 150, 600);
		this.add(imagemLista);
		
		inicialiazarLista();
		
		atualizarLista();
	}
	public void inicialiazarLista(){
		for (int i = 0; i < 5; i++) {
			gerarPeça();
		}
	}
	public static void limparLista(){
		No<Peça> aux = lista.getHead();
		for (int i = 0; i < 5; i++) {
			aux.getDado().apagar();
			aux=aux.getProx();
		}
	} 
	
	public static void atualizarLista() {
		No<Peça> aux = lista.getHead();
		for (int i = 0; i < 5; i++) {
			aux.getDado().criar(POSIÇÕES_LINHA[i], POSIÇÃO_COLUNA, blocosLista);
			aux=aux.getProx();
		}
	}
	
	public static Peça pegarPrimeira(){
		limparLista();
		Peça proxima=lista.getHead().getDado();
		lista.remH();
		gerarPeça();
		atualizarLista();
		return proxima;
	}
	
	public static void gerarPeça() {
		Random random = new Random();
		int posição = random.nextInt(TAMANHO);
		int cont = 0;
		while (cont < 6 && repetido[posição]) {
			if (posição == 6)
				posição = 0;
			posição++;
			cont++;
		}
		if (cont == 6) {
			for (int i = 0; i < TAMANHO; i++) {
				repetido[i] = false;
			}
		}
		lista.addT(traduzir(bag[posição]));
		repetido[posição] = true;
	}

	public static Peça traduzir(char c) {
		switch (c) {
		case 't':
			return new PeçaT();
		case 's':
			return new PeçaS();
		case 'z':
			return new PeçaZ();
		case 'i':
			return new PeçaI();
		case 'l':
			return new PeçaL();
		case 'j':
			return new PeçaJ();
		case 'o':
			return new PeçaO();
		}
		return null;
	}
}
