package peças;

import java.awt.Color;

import javax.swing.JLabel;

public class Bloco {
	public Peça peça;
	private JLabel bloco;
	private boolean vazio;

	public class Coor {
		public int x;
		public int y;
	};

	public Coor coor = new Coor();
	public Bloco[][] matrizBlocos;

	public Bloco(int x, int y, Bloco[][] matriz) {
		this.matrizBlocos = matriz;
		matrizBlocos[x][y] = this;
		coor.x = x;
		coor.y = y;
		setBloco(new JLabel());
		getBloco().setOpaque(true);
		getBloco().setBackground(Color.black);
		setVazio(true);
	}

	public boolean colidir(Peça peça) {
		if (isVazio() || this.peça == peça) {
			return false;
		}
		return true;
	}
	
	public boolean colidirSombra(Peça sombra,Peça atual) {
		if (isVazio() || this.peça == sombra || this.peça == atual) {
			return false;
		}
		return true;
	}

	public void limpar() {
		getBloco().setIcon(null);
		setVazio(true);
		peça = null;
	}

	public Bloco[] gerarPeça(Bloco[] blocoPeça,int x,int y) {
		if (isVazio()) {
			return blocoPeça;
		}
		for (int i = 0; i < blocoPeça.length; i++) {
			if (blocoPeça[i] == this) {
				break;
			} else if (blocoPeça[i] == null) {
				blocoPeça[i] = matrizBlocos[x][y];
				if (matrizBlocos[coor.x + 1][coor.y].peça == this.peça) {
					blocoPeça = matrizBlocos[coor.x + 1][coor.y]
							.gerarPeça(blocoPeça,coor.x+1,coor.y);
				}
				if (matrizBlocos[coor.x][coor.y - 1].peça == this.peça) {
					blocoPeça = matrizBlocos[coor.x ][coor.y - 1]
							.gerarPeça(blocoPeça,coor.x,coor.y-1);
				}
				if (matrizBlocos[coor.x][coor.y + 1].peça == this.peça) {
					blocoPeça = matrizBlocos[coor.x][coor.y + 1]
							.gerarPeça(blocoPeça,coor.x,coor.y+1);
				}
				if (matrizBlocos[coor.x - 1][coor.y].peça == this.peça) {
					blocoPeça = matrizBlocos[coor.x - 1][coor.y]
							.gerarPeça(blocoPeça,coor.x-1,coor.y);
				}
				break;
			}
		}
		return blocoPeça;
	}

	public boolean podeDescer() {
		return !matrizBlocos[coor.x + 1][coor.y].colidir(this.peça);
	}

	public Bloco descer(int vezes) {
		matrizBlocos[coor.x + vezes][coor.y].getBloco().setIcon(
				this.getBloco().getIcon());
		matrizBlocos[coor.x + vezes][coor.y].setVazio(isVazio());
		matrizBlocos[coor.x + vezes][coor.y].peça = this.peça;
		limpar();
		return matrizBlocos[coor.x + vezes][coor.y];
	}
	
	public Bloco direita(int vezes) {
		matrizBlocos[coor.x ][coor.y + vezes].getBloco().setIcon(
				this.getBloco().getIcon());
		matrizBlocos[coor.x][coor.y + vezes].setVazio(isVazio());
		matrizBlocos[coor.x][coor.y + vezes].peça = this.peça;
		limpar();
		return matrizBlocos[coor.x][coor.y + vezes];
	}
	
	public Bloco esquerda(int vezes) {
		matrizBlocos[coor.x][coor.y - vezes].getBloco().setIcon(
				this.getBloco().getIcon());
		matrizBlocos[coor.x][coor.y - vezes].setVazio(isVazio());
		matrizBlocos[coor.x][coor.y - vezes].peça = this.peça;
		limpar();
		return matrizBlocos[coor.x ][coor.y - vezes];
	}

	public void criar(Peça peça) {
		getBloco().setIcon(peça.icon);
		this.peça = peça;
		setVazio(false);
	}
	
	public void criarBlocoSombra(Peça peça) {
		getBloco().setIcon(peça.icon);
		this.peça = peça;
		setVazio(true);
	}

	public JLabel getBloco() {
		return bloco;
	}

	public void setBloco(JLabel bloco) {
		this.bloco = bloco;
	}

	public boolean isVazio() {
		return vazio;
	}

	public void setVazio(boolean vazio) {
		this.vazio = vazio;
	}

}
