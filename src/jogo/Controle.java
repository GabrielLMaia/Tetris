package jogo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controle implements KeyListener {
	public void keyPressed(KeyEvent k) {
		switch (k.getKeyCode()) {

		case KeyEvent.VK_DOWN:
			Tetris.peçaAtual.descer();
			break;
		case KeyEvent.VK_RIGHT:
			Tetris.peçaSombra.setarPosiçãoSombra(Tetris.peçaAtual, 1, false);
			Tetris.peçaAtual.direita();
			break;
		case KeyEvent.VK_LEFT:
			Tetris.peçaSombra.setarPosiçãoSombra(Tetris.peçaAtual, -1, false);
			Tetris.peçaAtual.esquerda();
			break;
		case KeyEvent.VK_SPACE:
			Tetris.peçaAtual.harddrop();
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
				Tetris.peçaSombra.apagar();
				Hold.hold();
				Tetris.usouHold = true;
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_UP) {
			Tetris.peçaSombra.setarPosiçãoSombra(Tetris.peçaAtual, 0, true);
			Tetris.peçaAtual.girar();
		}

	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

}
