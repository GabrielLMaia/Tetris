package jogo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;
import pe�as.Bloco;
import pe�as.Pe�a;

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
	private final static int POSI��O_LINHA = INICIO_LINHA;
	private final static int POSI��O_COLUNA = (FIM_COLUNA + INICIO_COLUNA) / 2;
	private final static int PONTOS_LINHAS[] = new int[] { 200, 500, 800, 1500 };
	private final static int PONTOS_COLUNAS[] = new int[] { 200, 500, 800, 1500};
	
	private static Music musica;
	
	private static boolean pause = false;
	private static boolean usouHold = false;
	
	private static int intervalo;
	private static int nivel;
	private static int numLinhasElim;
	private static int numColunasElim;
	private static int pontua��o;
	
	private static Bloco[][] blocos = new Bloco[LARGURA_REAL][COMPRIMENTO_REAL];
	private static Pe�a pe�aAtual;
	private static Pe�a pe�aSombra;
	private static Principal p;
	static javax.swing.Timer timer;

	//inicializa a matriz do jogo
	public Tetris(Principal principal) {
		p=principal;
		setLayout(new GridLayout(LARGURA, COMPRIMENTO, 0, 0));
		setPontua��o(0);
		setNivel(1);
		intervalo=1200;
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
	}
	public static void setIntervaloInicial(){
		switch(Principal.getDificuldade()){
		case(0):intervalo=2500;break;
		case(1):intervalo=1200;break;
		case(2):intervalo=500;break;
		case(3):intervalo=50;break;
		}
		timer.setDelay(intervalo);
	}
	public void actionPerformed(ActionEvent e) {
//		new Thread(new Runnable() {
			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
				if (!isPause()) {
					if (getPe�aAtual().podeDescer()) {
						getPe�aAtual().descer();
					} else {
						jogo();
					}
				}
//				
//			}
//		}).start();
	}

	public static void verificarIntervalo() {
		if (getPontua��o() >= 5000 * getNivel()) {
			if (intervalo != 100) {
				setNivel((getPontua��o() / 5000) + 1);
				intervalo = intervalo - 100;
				System.out.println(intervalo);
				timer.setDelay(intervalo);
			}
		}
		Dados.setDados();
	}

	public static void pegarHold() {
		setPe�aAtual(Hold.getPe�aHold());
		getPe�aAtual().criar(POSI��O_LINHA, POSI��O_COLUNA, blocos);
		criarSombra();
	}

	public static void criarSombra() {
		setPe�aSombra(ListaPe�as.traduzir(getPe�aAtual().getTipo()));
		getPe�aSombra().setMatrizeCorSombra(blocos);
		getPe�aSombra().setarPosi��oSombra(getPe�aAtual(), 0, false);
	}

	public static void pegarDaLista() {
		setPe�aAtual(ListaPe�as.pegarPrimeira());
		getPe�aAtual().criar(POSI��O_LINHA, POSI��O_COLUNA, blocos);
		criarSombra();
		setUsouHold(false);
	}

	public static void jogo() {
		timer.stop();
		while (checarLinhas() || checarColunas())
			;
		pegarDaLista();
		if (getPe�aAtual().podeDescer()) {
			timer.start();
		} else {
			p.gameover();
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
			setNumLinhasElim(getNumLinhasElim() + numDeColunasEliminadas);
			setPontua��o(getPontua��o() + PONTOS_COLUNAS[numDeColunasEliminadas - 1]);
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

	public static void descerLinhas(int inicio, int numLinhasApagadas) {
		
		for (int i = inicio - 1; i >= INICIO_LINHA; i--) {
			for (int j = INICIO_COLUNA; j <= FIM_COLUNA; j++) {
				blocos[i][j].descer(numLinhasApagadas);
			}
		}
//		 try {
//		 Thread.sleep(500);
//		 System.out.println("sleep terminado");
//		 } catch (InterruptedException e) {
//		 e.printStackTrace();
//		 }
		 
		 
		if (Principal.isGravidade()){
			gravidade();
		}
			
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
		int cor=-1;
		int x2=1;
		for (int i = INICIO_LINHA; i <= FIM_LINHA; i++) {
			numDeBlocosLinha = 0;
			if(!blocos[i][INICIO_COLUNA].isVazio())
				cor=blocos[i][INICIO_COLUNA].pe�a.getCor();
			for (int j = INICIO_COLUNA; j <= FIM_COLUNA; j++) {
				if (!blocos[i][j].isVazio()) {
					numDeBlocosLinha++;
					if(cor!=blocos[i][j].pe�a.getCor())
						cor=-1;
				}
			}
			if(cor!=-1)
				x2=2;
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
			setPontua��o(getPontua��o() + PONTOS_LINHAS[numDeLinhasEliminadas - 1]*x2);
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

	public static Pe�a getPe�aAtual() {
		return pe�aAtual;
	}

	public static void setPe�aAtual(Pe�a pe�aAtual) {
		Tetris.pe�aAtual = pe�aAtual;
	}

	public static Pe�a getPe�aSombra() {
		return pe�aSombra;
	}

	public static void setPe�aSombra(Pe�a pe�aSombra) {
		Tetris.pe�aSombra = pe�aSombra;
	}

	public static boolean usouHold() {
		return usouHold;
	}

	public static void setUsouHold(boolean usouHold) {
		Tetris.usouHold = usouHold;
	}

	public static int getPontua��o() {
		return pontua��o;
	}

	public static void setPontua��o(int pontua��o) {
		Tetris.pontua��o = pontua��o;
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
	public static void reset(){
		for (int i = INICIO_LINHA; i <= FIM_LINHA; i++) {
			for (int j = INICIO_COLUNA; j <= FIM_COLUNA; j++) {
				blocos[i][j].limpar();
			}
		}
		setIntervaloInicial();
		pontua��o=0;
		nivel=1;
		numColunasElim=0;
		numLinhasElim=0;
		Hold.apagarHold();
		pegarDaLista();
	}

}
