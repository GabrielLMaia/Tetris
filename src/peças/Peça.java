package pe�as;

import java.util.Random;

import javax.swing.ImageIcon;

import jogo.Principal;

public class Pe�a {
	ImageIcon icon;

	enum sentido {
		NORMAL, DIREIRA, BAIXO, ESQUERDA
	};

	protected int coorCentralX;
	protected int coorCentralY;
	protected Bloco[] blocosDaPe�a = new Bloco[4];
	public Bloco[][] matrizLocal;
	sentido rota��o;
	public Pe�a(){
		rota��o = sentido.NORMAL;
		gerarCor();
	}
	public Pe�a(int coorX, int coorY, Bloco[][] matrizBlocos) {
//		super();
		rota��o = sentido.NORMAL;
		gerarCor();
		matrizLocal = matrizBlocos;
		setCoor(coorX, coorY);
		pintar();
	}
	public void criar(int coorX, int coorY, Bloco[][] matrizBlocos){
		coorCentralX=0;
		coorCentralY=0;
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
//			else {
//				setCoor(-1, 0);
//				if(podeGirar()){
//					setCoor(+1, 0);
//					apagar();
//					setCoor(-1, 0);
//					rotacionar();
//					pintar();
//				}else
//					setCoor(+1, 0);
//			}
			break;
		case DIREIRA:
			if (podeGirar()) {
				apagar();
				rotacionar();
				pintar();
			}
//			else {
//				setCoor(0,1);
//				if(podeGirar()){
//					setCoor(0,-1);
//					apagar();
//					setCoor(0,1);
//					rotacionar();
//					pintar();
//				}else
//				setCoor(0,-1);
//			}
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
//			else {
//				setCoor(0,-1);
//				if(podeGirar()){
//					setCoor(0,1);
//					apagar();
//					setCoor(0,-1);
//					rotacionar();
//					pintar();
//				}else
//				setCoor(0,1);
//			}
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
		if (blocosDaPe�a[0].colidir(this)
				|| blocosDaPe�a[1].colidir(this)
				|| blocosDaPe�a[2].colidir(this)
				|| blocosDaPe�a[3].colidir(this))
			return false;

		return true;
	}

	public void pintar() {
		blocosDaPe�a[0].criar(this);
		blocosDaPe�a[1].criar(this);
		blocosDaPe�a[2].criar( this);
		blocosDaPe�a[3].criar(this);
	}
	
	public void apagar() {
		blocosDaPe�a[0].limpar();
		blocosDaPe�a[1].limpar();
		blocosDaPe�a[2].limpar();
		blocosDaPe�a[3].limpar();
	}
	
	public void setCoor(int x,int y){
		coorCentralX+=x;
		coorCentralY+=y;
		AtualizarBlocosDaPe�a();
	}
	
	public void rotacionar(){
		switch(rota��o){
		case NORMAL:
			rota��o=sentido.DIREIRA;
			break;
		case DIREIRA:
			rota��o=sentido.BAIXO;
			break;
		case BAIXO:
			rota��o=sentido.ESQUERDA;
			break;
		case ESQUERDA:
			rota��o=sentido.NORMAL;
			break;
		}
		AtualizarBlocosDaPe�a();
	}
	
	public void antiRotacionar(){
		switch(rota��o){
		case NORMAL:
			rota��o=sentido.ESQUERDA;
			break;
		case DIREIRA:
			rota��o=sentido.NORMAL;
			break;
		case BAIXO:
			rota��o=sentido.DIREIRA;
			break;
		case ESQUERDA:
			rota��o=sentido.BAIXO;
			break;
		}
		AtualizarBlocosDaPe�a();
	}
	
	public void AtualizarBlocosDaPe�a(){}

}
