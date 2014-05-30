package pe�as;

import java.awt.Color;

import javax.swing.JLabel;
//o bloco cont�m uma JLabel poder� conter a imagem que ficar� vis�vel na tela,uma vari�vel boolean para saber se le est� ou n�o vazio,a
//matriz em que se encontra e as coordenadas dele nessa matriz
public class Bloco {
	public Pe�a pe�a;
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
	//o bloco ir� "colidir" caso n�o seja vazio ou a pe�a dele for a mesma passada 
	public boolean colidir(Pe�a pe�a) {
		if (isVazio() || this.pe�a == pe�a) {
			return false;
		}
		return true;
	}
	
	public boolean colidirSombra(Pe�a sombra,Pe�a atual) {
		if (isVazio() || this.pe�a == sombra || this.pe�a == atual) {
			return false;
		}
		return true;
	}

	public void limpar() {
		getBloco().setIcon(null);
		setVazio(true);
		pe�a = null;
	}
	//o bloco vai gerar um vetor de blocos,"a pe�a atual dele",ele verifica se ele possu� bloco adjacentes da mesma pe�a,caso possua esse
	//bloco vai entrar no vetor e far� a mesma checagem
	public Bloco[] gerarPe�a(Bloco[] blocoPe�a,int x,int y) {
		if (isVazio()) {
			return blocoPe�a;
		}
		for (int i = 0; i < blocoPe�a.length; i++) {
			if (blocoPe�a[i] == this) {
				break;
			} else if (blocoPe�a[i] == null) {
				blocoPe�a[i] = matrizBlocos[x][y];
				if (matrizBlocos[coor.x + 1][coor.y].pe�a == this.pe�a) {
					blocoPe�a = matrizBlocos[coor.x + 1][coor.y]
							.gerarPe�a(blocoPe�a,coor.x+1,coor.y);
				}
				if (matrizBlocos[coor.x][coor.y - 1].pe�a == this.pe�a) {
					blocoPe�a = matrizBlocos[coor.x ][coor.y - 1]
							.gerarPe�a(blocoPe�a,coor.x,coor.y-1);
				}
				if (matrizBlocos[coor.x][coor.y + 1].pe�a == this.pe�a) {
					blocoPe�a = matrizBlocos[coor.x][coor.y + 1]
							.gerarPe�a(blocoPe�a,coor.x,coor.y+1);
				}
				if (matrizBlocos[coor.x - 1][coor.y].pe�a == this.pe�a) {
					blocoPe�a = matrizBlocos[coor.x - 1][coor.y]
							.gerarPe�a(blocoPe�a,coor.x-1,coor.y);
				}
				break;
			}
		}
		return blocoPe�a;
	}

	public boolean podeDescer() {
		return !matrizBlocos[coor.x + 1][coor.y].colidir(this.pe�a);
	}
	//ele se "movimenta" pintando o bloco em outra posi��o e apagando o da posi��o anterior
	public Bloco descer(int vezes) {
		matrizBlocos[coor.x + vezes][coor.y].getBloco().setIcon(
				this.getBloco().getIcon());
		matrizBlocos[coor.x + vezes][coor.y].setVazio(isVazio());
		matrizBlocos[coor.x + vezes][coor.y].pe�a = this.pe�a;
		limpar();
		return matrizBlocos[coor.x + vezes][coor.y];
	}
	
	public Bloco direita(int vezes) {
		matrizBlocos[coor.x ][coor.y + vezes].getBloco().setIcon(
				this.getBloco().getIcon());
		matrizBlocos[coor.x][coor.y + vezes].setVazio(isVazio());
		matrizBlocos[coor.x][coor.y + vezes].pe�a = this.pe�a;
		limpar();
		return matrizBlocos[coor.x][coor.y + vezes];
	}
	
	public Bloco esquerda(int vezes) {
		matrizBlocos[coor.x][coor.y - vezes].getBloco().setIcon(
				this.getBloco().getIcon());
		matrizBlocos[coor.x][coor.y - vezes].setVazio(isVazio());
		matrizBlocos[coor.x][coor.y - vezes].pe�a = this.pe�a;
		limpar();
		return matrizBlocos[coor.x ][coor.y - vezes];
	}

	public void criar(Pe�a pe�a) {
		getBloco().setIcon(pe�a.icon);
		this.pe�a = pe�a;
		setVazio(false);
	}
	
	public void criarBlocoSombra(Pe�a pe�a) {
		if(this.isVazio()){
		getBloco().setIcon(pe�a.icon);
		this.pe�a = pe�a;
		setVazio(true);
		}
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
