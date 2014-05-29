package peças;

import java.util.Random;

import javax.swing.ImageIcon;

import jogo.Principal;

public class Peça {
	ImageIcon icon;

	enum sentido {
		NORMAL, DIREIRA, BAIXO, ESQUERDA
	};

	private int cor;
	protected char tipo;
	protected int coorCentralX;
	protected int coorCentralY;
	protected Bloco[] blocosDaPeça = new Bloco[4];
	public Bloco[][] matrizLocal;
	sentido rotação;

	public Peça() {
		rotação = sentido.NORMAL;
		gerarCor();
	}

	public Peça(int coorX, int coorY, Bloco[][] matrizBlocos) {
		// super();
		rotação = sentido.NORMAL;
		gerarCor();
		matrizLocal = matrizBlocos;
		setCoor(coorX, coorY);
		pintar();
	}

	public void criar(int coorX, int coorY, Bloco[][] matrizBlocos) {
		rotação = sentido.NORMAL;
		coorCentralX = 0;
		coorCentralY = 0;
		matrizLocal = matrizBlocos;
		setCoor(coorX, coorY);
		pintar();
	}

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
		switch (rotação) {
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

	public void harddrop() {
		while (podeDescer())
			descer();
	}

	public void girar() {
		switch (rotação) {
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

	public void setarPosiçãoSombra(Peça peça, int y, boolean girar) {
		boolean vaiMudar = !((y == 1 && !peça.podeIrDireita()) || (y == -1 && !peça.podeIrEsquerda()));
		if (vaiMudar) {
			if ((y != 0 || girar == true) && vaiMudar){
				apagar();
				
			}
			coorCentralX = peça.coorCentralX;
			coorCentralY = peça.coorCentralY + y;
			setCoor(0, 0);
			if (girar && peça.podeGirar()) {
				rotacionar();
			}
			while (podePintarSombra(peça)) {
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
		if (blocosDaPeça[0].colidir(this) || blocosDaPeça[1].colidir(this)
				|| blocosDaPeça[2].colidir(this)
				|| blocosDaPeça[3].colidir(this))
			return false;

		return true;
	}

	public boolean podePintarSombra(Peça atual) {
		if (blocosDaPeça[0].colidirSombra(this, atual)
				|| blocosDaPeça[1].colidirSombra(this, atual)
				|| blocosDaPeça[2].colidirSombra(this, atual)
				|| blocosDaPeça[3].colidirSombra(this, atual))
			return false;

		return true;
	}

	public void pintar() {
		blocosDaPeça[0].criar(this);
		blocosDaPeça[1].criar(this);
		blocosDaPeça[2].criar(this);
		blocosDaPeça[3].criar(this);
	}

	public void pintarSombra() {
		blocosDaPeça[0].criarBlocoSombra(this);
		blocosDaPeça[1].criarBlocoSombra(this);
		blocosDaPeça[2].criarBlocoSombra(this);
		blocosDaPeça[3].criarBlocoSombra(this);
	}

	public void apagar() {
		blocosDaPeça[0].limpar();
		blocosDaPeça[1].limpar();
		blocosDaPeça[2].limpar();
		blocosDaPeça[3].limpar();
	}

	public void setCoor(int x, int y) {
		coorCentralX += x;
		coorCentralY += y;
		AtualizarBlocosDaPeça();
	}
	
	public void teste(){
		System.out.println("x: "+coorCentralX+"\n y:"+coorCentralY);
	}

	public void rotacionar() {
		switch (rotação) {
		case NORMAL:
			rotação = sentido.DIREIRA;
			break;
		case DIREIRA:
			rotação = sentido.BAIXO;
			break;
		case BAIXO:
			rotação = sentido.ESQUERDA;
			break;
		case ESQUERDA:
			rotação = sentido.NORMAL;
			break;
		}
		AtualizarBlocosDaPeça();
	}

	public void antiRotacionar() {
		switch (rotação) {
		case NORMAL:
			rotação = sentido.ESQUERDA;
			break;
		case DIREIRA:
			rotação = sentido.NORMAL;
			break;
		case BAIXO:
			rotação = sentido.DIREIRA;
			break;
		case ESQUERDA:
			rotação = sentido.BAIXO;
			break;
		}
		AtualizarBlocosDaPeça();
	}

	public void AtualizarBlocosDaPeça() {
	}

}
