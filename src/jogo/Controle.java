package jogo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pe�as.*;

public class Controle implements KeyListener {


//	Pe�aT y=new Pe�aT(10,8); 
//	Pe�aT g=new Pe�aT(5,2);
//	Pe�aT f=new Pe�aT(7,2);
//	Pe�aT s=new Pe�aT(9,2);
//	Pe�aT e=new Pe�aT(11,2);
//	Pe�aT u=new Pe�aT(13,2);
//	Pe�aT q=new Pe�aT(15,2);
//	Pe�aT b=new Pe�aT(17,2);
//	Pe�aT h=new Pe�aT(19,2);
	

	public void keyPressed(KeyEvent k) {
switch(k.getKeyCode()){
		
		case KeyEvent.VK_DOWN:
			Tetris.pe�aAtual.descer();
			Tetris.checarLinhas();
			break;
		case KeyEvent.VK_RIGHT:
			Tetris.pe�aAtual.direita();
			break;
		case KeyEvent.VK_LEFT:
			Tetris.pe�aAtual.esquerda();
			break;
		case KeyEvent.VK_SPACE:
			Tetris.pe�aAtual.harddrop();
			break;
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent k) {
		if(k.getKeyCode()==KeyEvent.VK_UP)
			Tetris.pe�aAtual.girar();

	}

	@Override
	public void keyTyped(KeyEvent k) {
		

	}

}
