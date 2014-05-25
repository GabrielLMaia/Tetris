package jogo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controle implements KeyListener {
	Tetris tetris;
	public Controle(Tetris tetris){
		this.tetris=tetris;
	}
	public void keyPressed(KeyEvent k) {
		switch (k.getKeyCode()) {
		
		case KeyEvent.VK_DOWN:
			tetris.peçaAtual.descer();
			break;
		case KeyEvent.VK_RIGHT:
			tetris.peçaSombra.setarPosiçãoSombra(Tetris.peçaAtual, 1, false);
			tetris.peçaAtual.direita();
			break;
		case KeyEvent.VK_LEFT:
			tetris.peçaSombra.setarPosiçãoSombra(Tetris.peçaAtual, -1, false);
			tetris.peçaAtual.esquerda();
			break;
		case KeyEvent.VK_SPACE:
			Tetris.peçaAtual.harddrop();
			tetris.jogo();
			break;
		case KeyEvent.VK_G:
			Tetris.gravidade();
			break;
		case KeyEvent.VK_P:
			if (Tetris.pause) {
				Tetris.pause = false;
				Tetris.timer.start();
			} else {
				Tetris.pause = true;
				Tetris.timer.stop();
			}
			break;
		case KeyEvent.VK_SHIFT:
			if(!Tetris.usouHold){
				tetris.peçaSombra.apagar();
				Hold.hold();
//				tetris.criarSombra();
				Tetris.usouHold=true;
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_UP){
			Tetris.peçaSombra.setarPosiçãoSombra(Tetris.peçaAtual, 0, true);
			Tetris.peçaAtual.girar();
		}

	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

}
