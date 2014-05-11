package jogo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import peças.*;

public class Controle implements KeyListener {


//	PeçaT y=new PeçaT(10,8); 
//	PeçaT g=new PeçaT(5,2);
//	PeçaT f=new PeçaT(7,2);
//	PeçaT s=new PeçaT(9,2);
//	PeçaT e=new PeçaT(11,2);
//	PeçaT u=new PeçaT(13,2);
//	PeçaT q=new PeçaT(15,2);
//	PeçaT b=new PeçaT(17,2);
//	PeçaT h=new PeçaT(19,2);
	

	public void keyPressed(KeyEvent k) {
switch(k.getKeyCode()){
		
		case KeyEvent.VK_DOWN:
			Tetris.peçaAtual.descer();
			Tetris.checarLinhas();
			break;
		case KeyEvent.VK_RIGHT:
			Tetris.peçaAtual.direita();
			break;
		case KeyEvent.VK_LEFT:
			Tetris.peçaAtual.esquerda();
			break;
		case KeyEvent.VK_SPACE:
			Tetris.peçaAtual.harddrop();
			break;
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent k) {
		if(k.getKeyCode()==KeyEvent.VK_UP)
			Tetris.peçaAtual.girar();

	}

	@Override
	public void keyTyped(KeyEvent k) {
		

	}

}
