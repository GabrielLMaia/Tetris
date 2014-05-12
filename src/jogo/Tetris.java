package jogo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import pe�as.*;

public class Tetris extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public final static int LARGURA = 20;
	public final static int COMPRIMENTO = 10;
	public final static int LARGURA_REAL = LARGURA + 4;
	public final static int COMPRIMENTO_REAL = COMPRIMENTO + 4;
	public final static int INICIO_COLUNA = 2;
	public final static int FIM_COLUNA = COMPRIMENTO_REAL - 3;
	public final static int INICIO_LINHA = 2;
	public final static int FALSE = -1;
	public final static int FIM_LINHA = LARGURA_REAL - 3;
	public final static int POSI��O_LINHA = INICIO_LINHA - 1;
	public final static int POSI��O_COLUNA = (FIM_COLUNA + INICIO_COLUNA) / 2;
	public final int PONTOS_LINHAS[] = new int[] { 200, 500, 800, 2000 };

	public static boolean pause = false;
	public static boolean usouHold = false;

	public static int intervalo;
	public static int pontua��o;
	public static int pontua��oAux;
	public static Bloco[][] blocos = new Bloco[LARGURA_REAL][COMPRIMENTO_REAL];
	public static Pe�a pe�aAtual;
	public static Pe�a pe�aSombra;

	static javax.swing.Timer timer;

	public Tetris() {
		setLayout(new GridLayout(LARGURA, COMPRIMENTO, 0, 0));

		intervalo = 2000;

		pontua��o = 0;
		pontua��oAux = 0;

		timer = new javax.swing.Timer(intervalo, this);

		for (int i = 0; i < LARGURA_REAL; i++) {
			for (int j = 0; j < COMPRIMENTO_REAL; j++) {
				blocos[i][j] = new Bloco(i, j, blocos);
				if (j <= 1 || j >= COMPRIMENTO + 2 || i >= LARGURA + 2) {
					blocos[i][j].setVazio(false);
				} else if (i > 1) {
					add(blocos[i][j].getBloco());
				}
			}
		}

		// pe�aAtual=new Pe�aO();
		// pe�aAtual.criar(5,5,blocos);

		pegarDaLista();
	}

	public void actionPerformed(ActionEvent e) {
		if (!pause) {
			if (pe�aAtual.podeDescer()) {
				pe�aAtual.descer();
			} else {
				jogo();
			}
		}
	}

	public void verificarIntervalo() {
		if (pontua��o - pontua��oAux >= 5000) {
			intervalo = intervalo / 2;
			pontua��oAux = pontua��o + 5000;
		}
	}

	public static void pegarHold() {
		pe�aAtual = Hold.getPe�aHold();
		pe�aAtual.criar(POSI��O_LINHA, POSI��O_COLUNA, blocos);
	}
	
	public static void pegarDaLista() {
		pe�aAtual = ListaPe�as.pegarPrimeira();
		pe�aAtual.criar(POSI��O_LINHA, POSI��O_COLUNA, blocos);
		usouHold=false;
	}

	public void jogo() {
		timer.stop();
		while (checarLinhas() || checarColunas())
			;
		pegarDaLista();
		timer.start();

	}

	public static boolean checarColunas() {
		return false;
	}

	public static void descerLinhas(int inicio, int numLinhasApagadas) {
		for (int i = inicio - 1; i >= INICIO_LINHA; i--) {
			for (int j = INICIO_COLUNA; j <= FIM_COLUNA; j++) {
				blocos[i][j].descer(numLinhasApagadas);
			}
		}
	}

	public boolean checarLinhas() {
		int numDeBlocosLinha = 0;
		int inicio = -1;
		int fim = -1;

		for (int i = INICIO_LINHA; i <= FIM_LINHA; i++) {
			numDeBlocosLinha = 0;
			for (int j = INICIO_COLUNA; j <= FIM_COLUNA; j++) {
				if (!blocos[i][j].isVazio()) {
					numDeBlocosLinha++;
				}
			}
			if (inicio == -1) {
				if (numDeBlocosLinha == COMPRIMENTO)
					inicio = i;
			} else if (numDeBlocosLinha != COMPRIMENTO) {
				fim = i - 1;
				break;
			}
		}

		if (fim == -1)
			fim = FIM_LINHA;
		if (inicio != -1) {
			int numDeLinhasEliminadas = 1 + (fim - inicio);
			pontua��o += PONTOS_LINHAS[numDeLinhasEliminadas];
			eliminarLinhas(inicio, fim);
			descerLinhas(inicio, numDeLinhasEliminadas);
			return true;
		}
		return false;
	}

	public static void eliminarLinhas(int inicio, int fim) {
		for (int i = inicio; i <= fim; i++) {
			for (int j = INICIO_COLUNA; j <= FIM_COLUNA; j++) {
				blocos[i][j].limpar();
			}
		}
	}

}
