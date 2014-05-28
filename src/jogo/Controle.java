package jogo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controle implements KeyListener {
	public void keyPressed(KeyEvent k) {
		switch (k.getKeyCode()) {

		case KeyEvent.VK_DOWN:
			Tetris.getPeçaAtual().descer();
			break;
		case KeyEvent.VK_RIGHT:
			Tetris.getPeçaSombra().setarPosiçãoSombra(Tetris.getPeçaAtual(), 1, false);
			Tetris.getPeçaAtual().direita();
			break;
		case KeyEvent.VK_LEFT:
			Tetris.getPeçaSombra().setarPosiçãoSombra(Tetris.getPeçaAtual(), -1, false);
			Tetris.getPeçaAtual().esquerda();
			break;
		case KeyEvent.VK_SPACE:
			Tetris.getPeçaAtual().harddrop();
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
				Tetris.getPeçaSombra().apagar();
				Hold.hold();
				Tetris.setUsouHold(true);
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_UP) {
			Tetris.getPeçaSombra().setarPosiçãoSombra(Tetris.getPeçaAtual(), 0, true);
			Tetris.getPeçaAtual().girar();
		}

	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

}
