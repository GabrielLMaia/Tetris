package jogo;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lista.Lista;
import lista.No;
import pe�as.*;
//essa � a classe que cont�m a LSE de pe�as e mostra elas na tela
public class ListaPe�as extends JPanel {
	private static final long serialVersionUID = 1L;

	private final static int TAMANHO = 7;
	private final int LARGURA_LISTA = 13;
	private final int LARGURA_REAL_LISTA = LARGURA_LISTA + 1;
	private final int COMPRIMENTO_LISTA = 4;
	private final static int POSI��O_COLUNA = 2;
	private final static int[] POSI��ES_LINHA = new int[] { 1, 4, 7, 10, 13 };

	private static Lista<Pe�a> lista = new Lista<Pe�a>();
	private static Bloco[][] blocosLista;
	private static char[] bag = new char[] { 't', 'l', 's', 'z', 'o', 'j', 'i' };
	private static boolean[] repetido = new boolean[TAMANHO];

	public ListaPe�as() {
		this.setLayout(null);

		blocosLista = new Bloco[LARGURA_REAL_LISTA][COMPRIMENTO_LISTA];
		for (int i = 1; i <= 4; i++) {
			JLabel divLista = new JLabel("");
			divLista.setIcon(new ImageIcon(getClass().getResource(
					"/imagens/divLinha.png")));
			divLista.setBounds(0, 90*i, 120, 30);
			this.add(divLista);	
		}
		

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
			gerarPe�a();
		}
	}
	public static void limparLista(){
		No<Pe�a> aux = lista.getHead();
		for (int i = 0; i < 5; i++) {
			aux.getDado().apagar();
			aux=aux.getProx();
		}
	} 
	
	public static void atualizarLista() {
		No<Pe�a> aux = lista.getHead();
		for (int i = 0; i < 5; i++) {
			aux.getDado().criar(POSI��ES_LINHA[i], POSI��O_COLUNA, blocosLista);
			aux=aux.getProx();
		}
	}
	
	public static Pe�a pegarPrimeira(){
		limparLista();
		Pe�a proxima=lista.getHead().getDado();
		lista.remH();
		gerarPe�a();
		atualizarLista();
		return proxima;
	}
	//gera pe�a de forma que uma pe�a s� pode ser pega se ela n�o j� foi pega naquela "rodada",essa "rodada" acaba quando todos tipos de
	//pe�as foream pegos,assim um pe�a nunca ir� demorar mais de 12 para aparecer novamente
	public static void gerarPe�a() {
		Random random = new Random();
		int posi��o = random.nextInt(TAMANHO);
		int cont = 0;
		while (cont <= 6 && repetido[posi��o]) {
			if (posi��o == 6){
				posi��o = 0;
			}else
			posi��o++;
			cont++;
		}
		if (cont == 7) {
			for (int i = 0; i < TAMANHO; i++) {
				if(!repetido[i])
					System.out.println("fudeo muito");
				repetido[i] = false;
			}
		}
		if(repetido[posi��o])
			System.out.println("fudeo");
		lista.addT(traduzir(bag[posi��o]));
		repetido[posi��o] = true;
	}

	public static Pe�a traduzir(char c) {
		switch (c) {
		case 't':
			return new Pe�aT();
		case 's':
			return new Pe�aS();
		case 'z':
			return new Pe�aZ();
		case 'i':
			return new Pe�aI();
		case 'l':
			return new Pe�aL();
		case 'j':
			return new Pe�aJ();
		case 'o':
			return new Pe�aO();
		}
		return null;
	}
}
