package jogo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controle implements KeyListener {
	public void keyPressed(KeyEvent k) {
		switch (k.getKeyCode()) {

		case KeyEvent.VK_DOWN:
			Tetris.getPe�aAtual().descer();
			break;
		case KeyEvent.VK_RIGHT:
			Tetris.getPe�aSombra().setarPosi��oSombra(Tetris.getPe�aAtual(), 1, false);
			Tetris.getPe�aAtual().direita();
			break;
		case KeyEvent.VK_LEFT:
			Tetris.getPe�aSombra().setarPosi��oSombra(Tetris.getPe�aAtual(), -1, false);
			Tetris.getPe�aAtual().esquerda();
			break;
		case KeyEvent.VK_SPACE:
			Tetris.getPe�aAtual().harddrop();
			Tetris.jogo();
			break;
		case KeyEvent.VK_G:
			Tetris.gravidade();
			break;
		case KeyEvent.VK_P:
			if (Tetris.isPause()) {
				Tetris.resumeMusic();
				Tetris.setPause(false);
				Tetris.timer.start();
			} else {
				Tetris.pauseMusic();
				Tetris.setPause(true);
				Tetris.timer.stop();
			}
			break;
		case KeyEvent.VK_SHIFT:
			if (!Tetris.usouHold()) {
				Tetris.getPe�aSombra().apagar();
				Hold.hold();
				Tetris.setUsouHold(true);
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_UP) {
			Tetris.getPe�aSombra().setarPosi��oSombra(Tetris.getPe�aAtual(), 0, true);
			Tetris.getPe�aAtual().girar();
		}

	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

}
