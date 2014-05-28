package jogo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;
import peças.Bloco;
import peças.Peça;

public class Tetris extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private final static int LARGURA = 20;
	private final static int COMPRIMENTO = 10;
	public final static int LARGURA_REAL = LARGURA + 5;
	private final static int COMPRIMENTO_REAL = COMPRIMENTO + 4;
	private final static int INICIO_COLUNA = 2;
	private final static int FIM_COLUNA = COMPRIMENTO_REAL - 3;
	private final static int INICIO_LINHA = 2;
	private final static int FIM_LINHA = LARGURA_REAL - 3;
	private final static int POSIÇÃO_LINHA = INICIO_LINHA;
	private final static int POSIÇÃO_COLUNA = (FIM_COLUNA + INICIO_COLUNA) / 2;
	private final static int PONTOS_LINHAS[] = new int[] { 200, 500, 800, 5000 };
	private final static int PONTOS_COLUNAS[] = new int[] { 5000, 7000, 9000,
			20000 };
	static Music musica;
	private static boolean pause = false;
	private static boolean usouHold = false;
	private static int intervalo;
	private static int nivel;
	private static int numLinhasElim;
	private static int numColunasElim;
	private static int pontuação;
	private static Bloco[][] blocos = new Bloco[LARGURA_REAL][COMPRIMENTO_REAL];
	private static Peça peçaAtual;
	private static Peça peçaSombra;

	static javax.swing.Timer timer;

	public Tetris(Dados dados) {
		setLayout(new GridLayout(LARGURA, COMPRIMENTO, 0, 0));
		intervalo = 1600;
		// music('z');
		setPontuação(0);
		setNivel(1);
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
		if (!isPause()) {
			if (getPeçaAtual().podeDescer()) {
				getPeçaAtual().descer();
			} else {
				jogo();
			}
		}
	}

	public static void verificarIntervalo() {
		if (getPontuação() >= 5000 * getNivel()) {
			if (intervalo != 100) {
				setNivel((getPontuação() / 5000) + 1);
				intervalo = intervalo - 100;
				timer.setDelay(intervalo);
			}
		}
		Dados.setDados();
	}

	public static void pegarHold() {
		setPeçaAtual(Hold.getPeçaHold());
		getPeçaAtual().criar(POSIÇÃO_LINHA, POSIÇÃO_COLUNA, blocos);
		// System.out.println(peçaAtual.getTipo());
		criarSombra();
	}

	public static void criarSombra() {
		setPeçaSombra(ListaPeças.traduzir(getPeçaAtual().getTipo()));
		getPeçaSombra().setMatrizeCorSombra(blocos);
		getPeçaSombra().setarPosiçãoSombra(getPeçaAtual(), 0, false);
	}

	public static void pegarDaLista() {
		setPeçaAtual(ListaPeças.pegarPrimeira());
		getPeçaAtual().criar(POSIÇÃO_LINHA, POSIÇÃO_COLUNA, blocos);
		criarSombra();
		setUsouHold(false);
	}

	public static void jogo() {
		timer.stop();
		while (checarLinhas() || checarColunas())
			;
		pegarDaLista();
		if (getPeçaAtual().podeDescer()) {
			timer.start();
		} else {
			// gameover
		}

	}

	public static boolean checarColunas() {
		int contCores = 0;
		int corAtual = -1;
		int inicio = -1;
		int fim = -1;
		boolean colunaEliminar = false;
		for (int i = INICIO_COLUNA; i <= FIM_COLUNA; i++) {
			colunaEliminar = false;
			contCores = 0;
			if (blocos[INICIO_LINHA][i].peça != null) {
				corAtual = blocos[INICIO_LINHA][i].peça.getCor();
				contCores++;

			}
			for (int j = INICIO_LINHA + 1; j <= FIM_LINHA; j++) {
				if (blocos[j][i].peça != null) {
					if (blocos[j][i].peça.getCor() == corAtual) {
						contCores++;
					} else {
						corAtual = blocos[j][i].peça.getCor();
						contCores = 1;
					}
				}
				if (contCores == 10) {
					colunaEliminar = true;
					break;
				}
			}
			if (inicio != -1) {
				if (!colunaEliminar) {
					fim = i - 1;
					break;
				}
			} else {
				if (colunaEliminar) {
					inicio = i;
				}
			}
			corAtual = -1;
		}
		if (fim == -1) {
			fim = FIM_COLUNA;
		}
		if (inicio != -1) {
			int numDeColunasEliminadas = 1 + (fim - inicio);
			setNumLinhasElim(getNumLinhasElim() + numDeColunasEliminadas);
			setPontuação(getPontuação() + PONTOS_COLUNAS[numDeColunasEliminadas - 1]);
			verificarIntervalo();
			eliminarColunas(inicio, fim);
			puxarColunas(inicio, numDeColunasEliminadas);
			return true;
		}
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
		// try {
		// Thread t=new Thread();
		// synchronized (t) {
		// t.wait(2000);
		// }
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		if (Principal.isGravidade())
			gravidade();
	}

	public static void puxarColunas(int inicio, int numLinhasApagadas) {
		if (inicio <= (INICIO_COLUNA + FIM_COLUNA) / 2) {
			for (int i = inicio + 1; i <= FIM_COLUNA; i++) {
				for (int j = INICIO_LINHA; j <= FIM_LINHA; j++) {
					blocos[j][i].esquerda(numLinhasApagadas);
				}
			}
		} else {
			for (int i = inicio - 1; i >= INICIO_COLUNA; i--) {
				for (int j = INICIO_LINHA; j <= FIM_LINHA; j++) {
					blocos[j][i].direita(numLinhasApagadas);
				}
			}
		}
		if (Principal.isGravidade())
			gravidade();
	}

	public static boolean checarLinhas() {
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
			setNumLinhasElim(getNumLinhasElim() + numDeLinhasEliminadas);
			setPontuação(getPontuação() + PONTOS_LINHAS[numDeLinhasEliminadas - 1]);
			verificarIntervalo();
			eliminarLinhas(inicio, fim);
			descerLinhas(inicio, numDeLinhasEliminadas);
			return true;
		}
		return false;
	}

	public static void eliminarColunas(int inicio, int fim) {
		for (int i = inicio; i <= fim; i++) {
			for (int j = INICIO_LINHA; j <= FIM_LINHA; j++) {
				blocos[j][i].limpar();
			}
		}
	}

	public static void eliminarLinhas(int inicio, int fim) {
		for (int i = inicio; i <= fim; i++) {
			for (int j = INICIO_COLUNA; j <= FIM_COLUNA; j++) {
				blocos[i][j].limpar();
			}
		}
	}

	public static void music(char music) {
		TinySound.init();
		switch (music) {
		case ('m'):
			musica = TinySound.loadMusic("Musicas/Megaman.wav");
			break;
		case ('z'):
			musica = TinySound.loadMusic("Musicas/Zelda.wav");
			break;
		case ('t'):
			musica = TinySound.loadMusic("Musicas/Tetris.wav");
			break;
		}
		musica.play(true);
	}

	public static void pauseMusic() {
		musica.pause();
	}

	public static void resumeMusic() {
		musica.resume();
	}

	public static void finalizarMusic() {
		TinySound.shutdown();
	}

	public static boolean isPause() {
		return pause;
	}

	public static void setPause(boolean pause) {
		Tetris.pause = pause;
	}

	public static Peça getPeçaAtual() {
		return peçaAtual;
	}

	public static void setPeçaAtual(Peça peçaAtual) {
		Tetris.peçaAtual = peçaAtual;
	}

	public static Peça getPeçaSombra() {
		return peçaSombra;
	}

	public static void setPeçaSombra(Peça peçaSombra) {
		Tetris.peçaSombra = peçaSombra;
	}

	public static boolean usouHold() {
		return usouHold;
	}

	public static void setUsouHold(boolean usouHold) {
		Tetris.usouHold = usouHold;
	}

	public static int getPontuação() {
		return pontuação;
	}

	public static void setPontuação(int pontuação) {
		Tetris.pontuação = pontuação;
	}

	public static int getNivel() {
		return nivel;
	}

	public static void setNivel(int nivel) {
		Tetris.nivel = nivel;
	}

	public static int getNumLinhasElim() {
		return numLinhasElim;
	}

	public static void setNumLinhasElim(int numLinhasElim) {
		Tetris.numLinhasElim = numLinhasElim;
	}

	public static int getNumColunasElim() {
		return numColunasElim;
	}

	public static void setNumColunasElim(int numColunasElim) {
		Tetris.numColunasElim = numColunasElim;
	}

}
