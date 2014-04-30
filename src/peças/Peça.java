package peças;

import java.util.Random;

import javax.swing.ImageIcon;

import jogo.Principal;

public  class Peça {
	ImageIcon icon;
	enum sentido{NORMAL,DIREIRA,BAIXO,ESQUERDA};
	int centralx;
	int centraly;
	sentido s;
	public Peça() {
		s=sentido.NORMAL;
		gerarCor();
	}

	public boolean descer(){
		centralx++;
		if(podePintar()){
			centralx--;
			apagar();
			centralx++;
			pintar();
			return true;
		}
		centralx--;
		return false;
	}
	public boolean esquerda(){
		centraly--;
		if(podePintar()){
			centraly++;
			apagar();
			centraly--;
			pintar();
			return true;
		}
		centraly++;
		return false;
	}
	public boolean direita(){
		centraly++;
		if(podePintar()){
			centraly--;
			apagar();
			centraly++;
			pintar();
			return true;
		}
		centraly--;
		return false;
	}
	public void harddrop(){	
		while(descer());	
	}
	public void girar(){
		switch (s) {
		case NORMAL:
			s=sentido.DIREIRA;
			if(podePintar()){
				s=sentido.NORMAL;
				apagar();
				s=sentido.DIREIRA;
				pintar();
			}else{
				s=sentido.NORMAL;
			}
				break;
		case DIREIRA:
			s=sentido.BAIXO;
			if(podePintar()){
				s=sentido.DIREIRA;
				apagar();
				s=sentido.BAIXO;
				pintar();
			}else{
				s=sentido.DIREIRA;
			}
				break;
		case BAIXO:
			s=sentido.ESQUERDA;
			if(podePintar()){
				s=sentido.BAIXO;
				apagar();
				s=sentido.ESQUERDA;
				pintar();
			}else{
				s=sentido.BAIXO;
			}
				break;
		case ESQUERDA:
			s=sentido.NORMAL;
			if(podePintar()){
				s=sentido.ESQUERDA;
				apagar();
				s=sentido.NORMAL;
				pintar();
			}else{
				s=sentido.ESQUERDA;
			}
				break;

		}
	}
	public boolean podePintar(){
		return false;
	}
	public void gerarCor(){
		Random r =new Random();
		int cor=r.nextInt(8);
		switch(cor){
		case 0:
			icon = new ImageIcon(Principal.class.getResource("/imagens/A.png"));
			break;
		case 1:
			icon = new ImageIcon(Principal.class.getResource("/imagens/AM.png"));
			break;
		case 2:
			icon = new ImageIcon(Principal.class.getResource("/imagens/V.png"));
			break;
		case 3:
			icon = new ImageIcon(Principal.class.getResource("/imagens/VERD.png"));
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
	public void pintar(){
		
	}
	public void apagar(){
		
	}
	
}
