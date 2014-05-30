package pe�as;

import java.util.Random;

import javax.swing.ImageIcon;

import jogo.Principal;
//a pe�a possu� um vetor com 4 blocos,a matriz em que ela se localiza,o "tipo" de pe�a(L,J,S...),as coordenadas dela,e o sentido de rota��o 
//atual dela
public class Pe�a {
	ImageIcon icon;
	//optei por usar sentido como um enum
	enum sentido {
		NORMAL, DIREIRA, BAIXO, ESQUERDA
	};

	private int cor;
	protected char tipo;
	protected int coorCentralX;
	protected int coorCentralY;
	protected Bloco[] blocosDaPe�a = new Bloco[4];
	public Bloco[][] matrizLocal;
	sentido rota��o;

	public Pe�a() {
		rota��o = sentido.NORMAL;
		gerarCor();
	}

	public Pe�a(int coorX, int coorY, Bloco[][] matrizBlocos) {
		// super();
		rota��o = sentido.NORMAL;
		gerarCor();
		matrizLocal = matrizBlocos;
		setCoor(coorX, coorY);
		pintar();
	}
	//"criar" cria a pe�a no local informado,na matriz informada,semelhante ao construtor 
	public void criar(int coorX, int coorY, Bloco[][] matrizBlocos) {
		rota��o = sentido.NORMAL;
		coorCentralX = 0;
		coorCentralY = 0;
		matrizLocal = matrizBlocos;
		setCoor(coorX, coorY);
		pintar();
	}
	//toda a movimenta��o da pe�a funciona atraves do "setCoor" que � um m�todo que recebe as altera��es nas coordenadas da pe�a e cria
	//ela no local,para saber se pode se mover � utizalo o podePintar que verifica se algum do blocos de pe�a sofrem colis�o
	public void descer() {
		if (podeDescer()) {
			apagar();
			setCoor(1, 0);
			pintar();
		}
	}

	public void esquerda() {
		if (podeIrEsquerda()) {
			apagar();
			setCoor(0, -1);
			pintar();
		}
	}

	public void direita() {
		if (podeIrDireita()) {
			apagar();
			setCoor(0, +1);
			pintar();
		}
	}

	public boolean podeDescer() {
		setCoor(1, 0);
		if (podePintar()) {
			setCoor(-1, 0);
			return true;
		}
		setCoor(-1, 0);
		return false;
	}

	public boolean podeIrEsquerda() {
		setCoor(0, -1);
		if (podePintar()) {
			setCoor(0, 1);
			return true;
		}
		setCoor(0, 1);
		return false;
	}

	public boolean podeIrDireita() {
		setCoor(0, 1);
		if (podePintar()) {
			setCoor(0, -1);
			return true;
		}
		setCoor(0, -1);
		return false;
	}

	public boolean podeGirar() {
		switch (rota��o) {
		case NORMAL:
			rotacionar();
			if (podePintar()) {
				antiRotacionar();
				return true;
			} else {
				antiRotacionar();
				return false;
			}
		case DIREIRA:
			rotacionar();
			if (podePintar()) {
				antiRotacionar();
				return true;
			} else {
				antiRotacionar();
				return false;
			}
		case BAIXO:
			rotacionar();
			if (podePintar()) {
				antiRotacionar();
				return true;
			} else {
				antiRotacionar();
				return false;
			}
		case ESQUERDA:
			rotacionar();
			if (podePintar()) {
				antiRotacionar();
				return true;
			} else {
				antiRotacionar();
				return false;
			}
		}
		return false;

	}
	//hard drop � quando a pe�a cai at� n�o poder mais
	public void harddrop() {
		while (podeDescer())
			descer();
	}

	public void girar() {
		switch (rota��o) {
		case NORMAL:
			if (podeGirar()) {
				apagar();
				rotacionar();
				pintar();
			}
			break;
		case DIREIRA:
			if (podeGirar()) {
				apagar();
				rotacionar();
				pintar();
			}
			break;
		case BAIXO:
			if (podeGirar()) {
				apagar();
				rotacionar();
				pintar();
			}
			break;
		case ESQUERDA:
			if (podeGirar()) {
				apagar();
				rotacionar();
				pintar();
			}
			break;

		}
	}

	public void setMatrizeCorSombra(Bloco[][] matrizBlocos) {
		icon = new ImageIcon(getClass().getResource("/imagens/F.png"));
		this.matrizLocal = matrizBlocos;
	}
	//o sombra funciona da seguinte maneira,ela vai apartir da posi��o da pe�a atual cair,isso se a pe�a atual mudar,funciona parecido
	//como um hard drop;
	public void setarPosi��oSombra(Pe�a pe�a, int y, boolean girar) {
		boolean vaiMudar =pe�a.podeDescer() && !((y == 1 && !pe�a.podeIrDireita()) || (y == -1 && !pe�a.podeIrEsquerda()));
		if (vaiMudar) {
			if ((y != 0 || girar == true) && vaiMudar){
				apagar();
				
			}
			coorCentralX = pe�a.coorCentralX;
			coorCentralY = pe�a.coorCentralY + y;
			setCoor(0, 0);
			if (girar && pe�a.podeGirar()) {
				rotacionar();
			}
			while (podePintarSombra(pe�a)) {
				this.setCoor(1, 0);
			}
			this.setCoor(-1, 0);
			pintarSombra();
		}
	}
	

	public char getTipo() {
		return tipo;
	}

	public int getCor() {
		return cor;
	}
	//usando Random � gerado um n�mero e apartir dele a cor
	public void gerarCor() {
		Random r = new Random();
		int cor = r.nextInt(8);
		this.cor = cor;
		switch (cor) {
		case 0:
			icon = new ImageIcon(getClass().getResource("/imagens/V.png"));
			break;
		case 1:
			icon = new ImageIcon(Principal.class.getResource("/imagens/AM.png"));
			break;
		case 2:
			icon = new ImageIcon(Principal.class.getResource("/imagens/A.png"));
			break;
		case 3:
			icon = new ImageIcon(getClass().getResource("/imagens/VERD.png"));
			break;
		case 4:
			icon = new ImageIcon(Principal.class.getResource("/imagens/R.png"));
			break;
		case 5:
			icon = new ImageIcon(Principal.class.getResource("/imagens/C.png"));
			break;
		case 6:
			icon = new ImageIcon(Principal.class.getResource("/imagens/H.png"));
			break;
		case 7:
			icon = new ImageIcon(Principal.class.getResource("/imagens/P.png"));
			break;
		}
	}

	public boolean podePintar() {
		if (blocosDaPe�a[0].colidir(this) || blocosDaPe�a[1].colidir(this)
				|| blocosDaPe�a[2].colidir(this)
				|| blocosDaPe�a[3].colidir(this))
			return false;

		return true;
	}

	public boolean podePintarSombra(Pe�a atual) {
		if (blocosDaPe�a[0].colidirSombra(this, atual)
				|| blocosDaPe�a[1].colidirSombra(this, atual)
				|| blocosDaPe�a[2].colidirSombra(this, atual)
				|| blocosDaPe�a[3].colidirSombra(this, atual))
			return false;

		return true;
	}

	public void pintar() {
		blocosDaPe�a[0].criar(this);
		blocosDaPe�a[1].criar(this);
		blocosDaPe�a[2].criar(this);
		blocosDaPe�a[3].criar(this);
	}

	public void pintarSombra() {
		blocosDaPe�a[0].criarBlocoSombra(this);
		blocosDaPe�a[1].criarBlocoSombra(this);
		blocosDaPe�a[2].criarBlocoSombra(this);
		blocosDaPe�a[3].criarBlocoSombra(this);
	}

	public void apagar() {
		blocosDaPe�a[0].limpar();
		blocosDaPe�a[1].limpar();
		blocosDaPe�a[2].limpar();
		blocosDaPe�a[3].limpar();
	}

	public void setCoor(int x, int y) {
		coorCentralX += x;
		coorCentralY += y;
		AtualizarBlocosDaPe�a();
	}
	
	public void teste(){
		System.out.println("x: "+coorCentralX+"\n y:"+coorCentralY);
	}

	public void rotacionar() {
		switch (rota��o) {
		case NORMAL:
			rota��o = sentido.DIREIRA;
			break;
		case DIREIRA:
			rota��o = sentido.BAIXO;
			break;
		case BAIXO:
			rota��o = sentido.ESQUERDA;
			break;
		case ESQUERDA:
			rota��o = sentido.NORMAL;
			break;
		}
		AtualizarBlocosDaPe�a();
	}

	public void antiRotacionar() {
		switch (rota��o) {
		case NORMAL:
			rota��o = sentido.ESQUERDA;
			break;
		case DIREIRA:
			rota��o = sentido.NORMAL;
			break;
		case BAIXO:
			rota��o = sentido.DIREIRA;
			break;
		case ESQUERDA:
			rota��o = sentido.BAIXO;
			break;
		}
		AtualizarBlocosDaPe�a();
	}
	//esse m�todo ser� sobrescrito por todas as varia��es de pe�a,e atrav�s dele que a pe�a "sabe como se pintar". 
	public void AtualizarBlocosDaPe�a() {
	}

}
