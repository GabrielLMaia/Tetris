package jogo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controle implements KeyListener {
	public void keyPressed(KeyEvent k) {
		switch (k.getKeyCode()) {

		case KeyEvent.VK_DOWN:
			Tetris.pe�aAtual.descer();
			break;
		case KeyEvent.VK_RIGHT:
			Tetris.pe�aSombra.setarPosi��oSombra(Tetris.pe�aAtual, 1, false);
			Tetris.pe�aAtual.direita();
			break;
		case KeyEvent.VK_LEFT:
			Tetris.pe�aSombra.setarPosi��oSombra(Tetris.pe�aAtual, -1, false);
			Tetris.pe�aAtual.esquerda();
			break;
		case KeyEvent.VK_SPACE:
			Tetris.pe�aAtual.harddrop();
			Tetris.jogo();
			break;
		case KeyEvent.VK_G:
			Tetris.gravidade();
			break;
		case KeyEvent.VK_P:
			if (Tetris.pause) {
				Tetris.resumeMusic();
				Tetris.pause = false;
				Tetris.timer.start();
			} else {
				Tetris.pauseMusic();
				Tetris.pause = true;
				Tetris.timer.stop();
			}
			break;
		case KeyEvent.VK_SHIFT:
			if (!Tetris.usouHold) {
				Tetris.pe�aSombra.apagar();
				Hold.hold();
				Tetris.usouHold = true;
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_UP) {
			Tetris.pe�aSombra.setarPosi��oSombra(Tetris.pe�aAtual, 0, true);
			Tetris.pe�aAtual.girar();
		}

	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

}
