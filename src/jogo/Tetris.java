package jogo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import peças.*;

public class Tetris extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public final static int LARGURA = 20;
	public final static int COMPRIMENTO = 10;
	public final static int LARGURA_REAL = LARGURA + 5;
	public final static int COMPRIMENTO_REAL = COMPRIMENTO + 4;
	public final static int INICIO_COLUNA = 2;
	public final static int FIM_COLUNA = COMPRIMENTO_REAL - 3;
	public final static int INICIO_LINHA = 2;
	public final static int FALSE = -1;
	public final static int FIM_LINHA = LARGURA_REAL - 3;
	public final static int POSIÇÃO_LINHA = INICIO_LINHA;
	public final static int POSIÇÃO_COLUNA = (FIM_COLUNA + INICIO_COLUNA) / 2;
	public final static int PONTOS_LINHAS[] = new int[] { 200, 500, 800, 5000 };

	public static boolean pause = false;
	public static boolean usouHold = false;

	public static int intervalo;
	public static int pontuação;
	public static int pontuaçãoAux;
	public static Bloco[][] blocos = new Bloco[LARGURA_REAL][COMPRIMENTO_REAL];
	public static Peça peçaAtual;
	public static Peça peçaSombra;

	static javax.swing.Timer timer;

	public Tetris() {
		setLayout(new GridLayout(LARGURA, COMPRIMENTO, 0, 0));

		intervalo = 1000;

		pontuação = 0;
		pontuaçãoAux = 0;

		timer = new javax.swing.Timer(intervalo, this);

		for (int i = 0; i < LARGURA_REAL; i++) {
			for (int j = 0; j < COMPRIMENTO_REAL; j++) {
				blocos[i][j] = new Bloco(i, j, blocos);
				if (j <= 1 || j >= COMPRIMENTO + 2 || i >= LARGURA + 3) {
					blocos[i][j].setVazio(false);
				} else if (i > 2) {
					add(blocos[i][j].getBloco());
				}
			}
		}

		// peçaAtual=new PeçaO();
		// peçaAtual.criar(5,5,blocos);

		pegarDaLista();
	}

	public void actionPerformed(ActionEvent e) {
		if (!pause) {
			if (peçaAtual.podeDescer()) {
				peçaAtual.descer();
			} else {
				jogo();
			}
		}
	}

	public static void verificarIntervalo() {
		if (pontuação - pontuaçãoAux >= 5000) {
			intervalo = intervalo / 2;
			timer.setDelay(intervalo);
			pontuaçãoAux = pontuação + 5000;
		}
	}

	public static void pegarHold() {
		peçaAtual = Hold.getPeçaHold();
		peçaAtual.criar(POSIÇÃO_LINHA, POSIÇÃO_COLUNA, blocos);
	}

	public static void pegarDaLista() {
		Peça peçaAux = ListaPeças.pegarPrimeira();
		peçaAtual = peçaAux;
		// peçaSombra.gerarSombra();
		// peçaSombra.harddrop();
		peçaAtual.criar(POSIÇÃO_LINHA, POSIÇÃO_COLUNA, blocos);
		usouHold = false;
	}

	public static void jogo() {
		timer.stop();
		while (checarLinhas() || checarColunas())
			;
		pegarDaLista();
		if (peçaAtual.podeDescer()) {
			timer.start();
		}

	}

	public static boolean checarColunas() {
		return false;
	}

	public static void gravidade() {
		for (int i = FIM_LINHA; i >= INICIO_LINHA; i--) {
			for (int j = INICIO_COLUNA; j <= FIM_COLUNA; j++) {

				Bloco[] peça = new Bloco[4];
				peça = blocos[i][j].gerarPeça(peça, i, j);

				if (peça[0] != null) {
					boolean podeDescer = true;
					while (podeDescer) {
						for (int k = 0; k < peça.length; k++) {
							if (peça[k] != null) {
								if (!peça[k].podeDescer()) {
									podeDescer = false;
								}
							}
						}

						if (podeDescer) {
							for (int k = 0; k < peça.length; k++) {
								if (peça[k] != null) {
									peça[k] = peça[k].descer(1);
								}
							}
						}
					}
				}

			}
		}
	}

	public static void descerLinhas(int inicio, int numLinhasApagadas) {
		for (int i = inicio - 1; i >= INICIO_LINHA; i--) {
			for (int j = INICIO_COLUNA; j <= FIM_COLUNA; j++) {
				blocos[i][j].descer(numLinhasApagadas);
			}
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gravidade();
	}

	public static boolean checarLinhas() {
		int numDeBlocosLinha = 0;
		int inicio = -1;
		int fim = -2;

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

		if (fim == -2)
			fim = FIM_LINHA;
		if (inicio != -1) {
			int numDeLinhasEliminadas = 1 + (fim - inicio);
			pontuação += PONTOS_LINHAS[numDeLinhasEliminadas - 1];
			verificarIntervalo();
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
