package jogo;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import pe�as.Bloco;
import pe�as.Pe�a;

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
	public final static int POSI��O_LINHA = INICIO_LINHA;
	public final static int POSI��O_COLUNA = (FIM_COLUNA + INICIO_COLUNA) / 2;
	public final static int PONTOS_LINHAS[] = new int[] { 200, 500, 800, 5000 };
	public final static int PONTOS_COLUNAS[] = new int[] { 5000, 7000, 9000,
			20000 };

	public static boolean pause = false;
	public static boolean usouHold = false;

	public static int intervalo;
	public static int nivel;
	public static int numLinhasElim;
	public static int numColunasElim;
	public static int pontua��o;
	public static int pontua��oAux;
	public static Bloco[][] blocos = new Bloco[LARGURA_REAL][COMPRIMENTO_REAL];
	public static Pe�a pe�aAtual;
	public static Pe�a pe�aSombra;

	static javax.swing.Timer timer;

	public Dados dados;
	
	public Tetris(Dados dados) {
		setLayout(new GridLayout(LARGURA, COMPRIMENTO, 0, 0));
		// setBackground(Color.green);
		intervalo = 1600;

		pontua��o = 0;
		nivel=1;
		timer = new javax.swing.Timer(intervalo, this);

		this.dados=dados;
		
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
		if(pontua��o >= 5000*nivel){
			if(intervalo!=100){
			nivel=(pontua��o/5000)+1;
			intervalo =intervalo -100;
			timer.setDelay(intervalo);
			}
		}
		dados.setDados();
	}

	public static void pegarHold() {
		pe�aAtual = Hold.getPe�aHold();
		pe�aAtual.criar(POSI��O_LINHA, POSI��O_COLUNA, blocos);
		// System.out.println(pe�aAtual.getTipo());
		criarSombra();
	}

	public static void criarSombra() {
		pe�aSombra = ListaPe�as.traduzir(pe�aAtual.getTipo());
		pe�aSombra.setMatrizeCorSombra(blocos);
		pe�aSombra.setarPosi��oSombra(pe�aAtual, 0, false);
		pe�aAtual.teste();
		pe�aSombra.teste();
	}

	public static void pegarDaLista() {
		pe�aAtual = ListaPe�as.pegarPrimeira();
		pe�aAtual.criar(POSI��O_LINHA, POSI��O_COLUNA, blocos);
		criarSombra();
		usouHold = false;
	}

	public void jogo() {
		timer.stop();
		while (checarLinhas() || checarColunas())
			;
		pegarDaLista();
		if (pe�aAtual.podeDescer()) {
			timer.start();
		} else {
			// gameover
		}

	}

	public boolean checarColunas() {
		int contCores = 0;
		int corAtual = -1;
		int inicio = -1;
		int fim = -1;
		boolean colunaEliminar = false;
		for (int i = INICIO_COLUNA; i <= FIM_COLUNA; i++) {
			colunaEliminar = false;
			contCores = 0;
			if (blocos[INICIO_LINHA][i].pe�a != null) {
				corAtual = blocos[INICIO_LINHA][i].pe�a.getCor();
				contCores++;

			}
			for (int j = INICIO_LINHA + 1; j <= FIM_LINHA; j++) {
				if (blocos[j][i].pe�a != null) {
					if (blocos[j][i].pe�a.getCor() == corAtual) {
						contCores++;
					} else {
						corAtual = blocos[j][i].pe�a.getCor();
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
			numLinhasElim+=numDeColunasEliminadas;
			pontua��o += PONTOS_COLUNAS[numDeColunasEliminadas - 1];
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

				Bloco[] pe�a = new Bloco[4];
				pe�a = blocos[i][j].gerarPe�a(pe�a, i, j);

				if (pe�a[0] != null) {
					boolean podeDescer = true;
					while (podeDescer) {
						for (int k = 0; k < pe�a.length; k++) {
							if (pe�a[k] != null) {
								if (!pe�a[k].podeDescer()) {
									podeDescer = false;
								}
							}
						}

						if (podeDescer) {
							for (int k = 0; k < pe�a.length; k++) {
								if (pe�a[k] != null) {
									pe�a[k] = pe�a[k].descer(1);
								}
							}
						}
					}
				}

			}
		}
	}

	public void descerLinhas(int inicio, int numLinhasApagadas) {
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
		gravidade();
	}

	public void puxarColunas(int inicio, int numLinhasApagadas) {
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
		gravidade();
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
			numLinhasElim+=numDeLinhasEliminadas;
			pontua��o += PONTOS_LINHAS[numDeLinhasEliminadas - 1];
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

}
