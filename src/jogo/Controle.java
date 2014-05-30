package jogo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//nessa classe contém o que deve ser feito após cada tecla relevante ser apertada
public class Controle implements KeyListener {
	public void keyPressed(KeyEvent k) {
		switch (k.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			if(!Tetris.isPause())
			Tetris.getPeçaAtual().descer();
			break;
		case KeyEvent.VK_RIGHT:
			if(!Tetris.isPause()){
			Tetris.getPeçaSombra().setarPosiçãoSombra(Tetris.getPeçaAtual(), 1, false);
			Tetris.getPeçaAtual().direita();
			}
			break;
		case KeyEvent.VK_LEFT:
			if(!Tetris.isPause()){
			Tetris.getPeçaSombra().setarPosiçãoSombra(Tetris.getPeçaAtual(), -1, false);
			Tetris.getPeçaAtual().esquerda();
			}
			break;
		case KeyEvent.VK_SPACE:
			if(!Tetris.isPause()){
			Tetris.getPeçaAtual().harddrop();
			Tetris.jogo();
			}
			break;
//		para testar a gravidade manualmente
//		case KeyEvent.VK_G:
//			Tetris.gravidade();
//			break;
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
			if (!Tetris.usouHold()&&!Tetris.isPause()) {
				Tetris.getPeçaSombra().apagar();
				Hold.hold();
				Tetris.setUsouHold(true);
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_UP&&!Tetris.isPause()) {
			Tetris.getPeçaSombra().setarPosiçãoSombra(Tetris.getPeçaAtual(), 0, true);
			Tetris.getPeçaAtual().girar();
		}

	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

}
