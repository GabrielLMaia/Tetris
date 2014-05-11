package peças;

import java.util.Random;

import javax.swing.ImageIcon;

import jogo.Principal;

public class Peça {
	ImageIcon icon;

	enum sentido {
		NORMAL, DIREIRA, BAIXO, ESQUERDA
	};

	protected int coorCentralX;
	protected int coorCentralY;
	protected Bloco[] blocosDaPeça = new Bloco[4];
	public Bloco[][] matrizLocal;
	sentido rotação;

	public Peça(int coorX, int coorY, Bloco[][] matrizBlocos) {
		matrizLocal = matrizBlocos;
		rotação = sentido.NORMAL;
		gerarCor();
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
			setCoor(0,-1);
			pintar();
		}
	}

	public void direita() {
		if (podeIrDireita()) {
			apagar();
			setCoor(0,+1);
			pintar();
		}
	}

	public boolean podeDescer() {
		setCoor(1,0);
		if (podePintar()) {
			setCoor(-1,0);
			return true;
		}
		setCoor(-1,0);
		return false;
	}

	public boolean podeIrEsquerda() {
		setCoor(0,-1);
		if (podePintar()) {
			setCoor(0,1);
			return true;
		}
		setCoor(0,1);
		return false;
	}

	public boolean podeIrDireita() {
		setCoor(0,1);
		if (podePintar()) {
			setCoor(0,-1);
			return true;
		}
		setCoor(0,-1);
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
			} else {
				setCoor(-1, 0);
				if(podeGirar()){
					setCoor(+1, 0);
					apagar();
					setCoor(-1, 0);
					rotacionar();
					pintar();
				}else
					setCoor(+1, 0);
			}
			break;
		case DIREIRA:
			if (podeGirar()) {
				apagar();
				rotacionar();
				pintar();
			} else {
				setCoor(0,1);
				if(podeGirar()){
					setCoor(0,-1);
					apagar();
					setCoor(0,1);
					rotacionar();
					pintar();
				}else
				setCoor(0,-1);
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
			} else {
				setCoor(0,-1);
				if(podeGirar()){
					setCoor(0,1);
					apagar();
					setCoor(0,-1);
					rotacionar();
					pintar();
				}else
				setCoor(0,1);
			}
			break;

		}
	}


	public void gerarCor() {
		Random r = new Random();
		int cor = r.nextInt(8);
		switch (cor) {
		case 0:
			icon = new ImageIcon(getClass().getResource("/imagens/V.png"));
			break;
		case 1:
			icon = new ImageIcon(Principal.class.getResource("/imagens/V.png"));
			break;
		case 2:
			icon = new ImageIcon(Principal.class.getResource("/imagens/V.png"));
			break;
		case 3:
			icon = new ImageIcon(
					Principal.class.getResource("/imagens/VERD.png"));
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
		if (blocosDaPeça[0].colidir(this)
				|| blocosDaPeça[1].colidir(this)
				|| blocosDaPeça[2].colidir(this)
				|| blocosDaPeça[3].colidir(this))
			return false;

		return true;
	}

	public void pintar() {
		blocosDaPeça[0].criar(this);
		blocosDaPeça[1].criar(this);
		blocosDaPeça[2].criar( this);
		blocosDaPeça[3].criar(this);
	}
	
	public void apagar() {
		blocosDaPeça[0].limpar();
		blocosDaPeça[1].limpar();
		blocosDaPeça[2].limpar();
		blocosDaPeça[3].limpar();
	}
	
	public void setCoor(int x,int y){
		coorCentralX+=x;
		coorCentralY+=y;
		AtualizarBlocosDaPeça();
	}
	
	public void rotacionar(){
		switch(rotação){
		case NORMAL:
			rotação=sentido.DIREIRA;
			break;
		case DIREIRA:
			rotação=sentido.BAIXO;
			break;
		case BAIXO:
			rotação=sentido.ESQUERDA;
			break;
		case ESQUERDA:
			rotação=sentido.NORMAL;
			break;
		}
		AtualizarBlocosDaPeça();
	}
	
	public void antiRotacionar(){
		switch(rotação){
		case NORMAL:
			rotação=sentido.ESQUERDA;
			break;
		case DIREIRA:
			rotação=sentido.NORMAL;
			break;
		case BAIXO:
			rotação=sentido.DIREIRA;
			break;
		case ESQUERDA:
			rotação=sentido.BAIXO;
			break;
		}
		AtualizarBlocosDaPeça();
	}
	
	public void AtualizarBlocosDaPeça(){}

}
