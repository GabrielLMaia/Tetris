package jogo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pe�as.*;

public class Controle implements KeyListener {

	Pe�aJ L=new Pe�aJ(1,4);
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
			L.descer();
			break;
		case KeyEvent.VK_RIGHT:
			L.direita();
			break;
		case KeyEvent.VK_LEFT:
			L.esquerda();
			break;
		case KeyEvent.VK_SPACE:
			L.harddrop();
			break;
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent k) {
		if(k.getKeyCode()==KeyEvent.VK_UP)
			L.girar();

	}

	@Override
	public void keyTyped(KeyEvent k) {
		

	}

}
