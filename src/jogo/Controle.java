package jogo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//nessa classe cont�m o que deve ser feito ap�s cada tecla relevante ser apertada
public class Controle implements KeyListener {
	public void keyPressed(KeyEvent k) {
		switch (k.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			if(!Tetris.isPause())
			Tetris.getPe�aAtual().descer();
			break;
		case KeyEvent.VK_RIGHT:
			if(!Tetris.isPause()){
			Tetris.getPe�aSombra().setarPosi��oSombra(Tetris.getPe�aAtual(), 1, false);
			Tetris.getPe�aAtual().direita();
			}
			break;
		case KeyEvent.VK_LEFT:
			if(!Tetris.isPause()){
			Tetris.getPe�aSombra().setarPosi��oSombra(Tetris.getPe�aAtual(), -1, false);
			Tetris.getPe�aAtual().esquerda();
			}
			break;
		case KeyEvent.VK_SPACE:
			if(!Tetris.isPause()){
			Tetris.getPe�aAtual().harddrop();
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
				Tetris.getPe�aSombra().apagar();
				Hold.hold();
				Tetris.setUsouHold(true);
			}
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_UP&&!Tetris.isPause()) {
			Tetris.getPe�aSombra().setarPosi��oSombra(Tetris.getPe�aAtual(), 0, true);
			Tetris.getPe�aAtual().girar();
		}

	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

}
