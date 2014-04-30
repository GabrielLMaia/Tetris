package peças;

import jogo.Tetris;

public class PeçaJ extends Peça {

	public PeçaJ(int pex, int pey) {
		super();
		centralx = pex;
		centraly = pey;
		pintar();

	}

	public boolean podePintar() {
		switch (s) {
		case NORMAL:

			if (Tetris.blocos[centralx][centraly].colidir(this)
					|| Tetris.blocos[centralx - 1][centraly - 1].colidir(this)
					|| Tetris.blocos[centralx][centraly - 1].colidir(this)
					|| Tetris.blocos[centralx][centraly + 1].colidir(this))
				return false;

			return true;
		case DIREIRA:
			if (Tetris.blocos[centralx][centraly].colidir(this)
					|| Tetris.blocos[centralx - 1][centraly].colidir(this)
					|| Tetris.blocos[centralx + 1][centraly].colidir(this)
					|| Tetris.blocos[centralx -1][centraly+1].colidir(this))
				return false;
			return true;
		case BAIXO:
			if (Tetris.blocos[centralx][centraly].colidir(this)
					|| Tetris.blocos[centralx+1][centraly + 1].colidir(this)
					|| Tetris.blocos[centralx][centraly - 1].colidir(this)
					|| Tetris.blocos[centralx][centraly + 1].colidir(this))
				return false;
			return true;
		case ESQUERDA:
			if (Tetris.blocos[centralx][centraly].colidir(this)
					|| Tetris.blocos[centralx - 1][centraly].colidir(this)
					|| Tetris.blocos[centralx + 1][centraly].colidir(this)
					|| Tetris.blocos[centralx +1][centraly-1].colidir(this))
				return false;
			return true;

		}
		return false;
	}

	public void pintar() {
		switch (s) {
		case NORMAL:
			Tetris.blocos[centralx][centraly].criar(icon, this);
			Tetris.blocos[centralx - 1][centraly - 1].criar(icon, this);
			Tetris.blocos[centralx][centraly - 1].criar(icon, this);
			Tetris.blocos[centralx][centraly + 1].criar(icon, this);
			break;
		case DIREIRA:
			Tetris.blocos[centralx][centraly].criar(icon, this);
			Tetris.blocos[centralx - 1][centraly].criar(icon, this);
			Tetris.blocos[centralx + 1][centraly].criar(icon, this);
			Tetris.blocos[centralx -1][centraly+1].criar(icon, this);
			break;
		case BAIXO:
			Tetris.blocos[centralx][centraly].criar(icon, this);
			Tetris.blocos[centralx+1][centraly +1].criar(icon, this);
			Tetris.blocos[centralx][centraly - 1].criar(icon, this);
			Tetris.blocos[centralx][centraly + 1].criar(icon, this);
			break;
		case ESQUERDA:
			Tetris.blocos[centralx][centraly].criar(icon, this);
			Tetris.blocos[centralx - 1][centraly].criar(icon, this);
			Tetris.blocos[centralx +1][centraly-1].criar(icon, this);
			Tetris.blocos[centralx + 1][centraly].criar(icon, this);
			break;

		}

	}

	public void apagar() {
		switch (s) {
		case NORMAL:
			Tetris.blocos[centralx][centraly].limpar();
			Tetris.blocos[centralx - 1][centraly - 1].limpar();
			Tetris.blocos[centralx][centraly - 1].limpar();
			Tetris.blocos[centralx][centraly + 1].limpar();
			break;
		case DIREIRA:
			Tetris.blocos[centralx][centraly].limpar();
			Tetris.blocos[centralx - 1][centraly].limpar();
			Tetris.blocos[centralx + 1][centraly].limpar();
			Tetris.blocos[centralx -1][centraly+1].limpar();
			break;
		case BAIXO:
			Tetris.blocos[centralx][centraly].limpar();
			Tetris.blocos[centralx+1][centraly + 1].limpar();
			Tetris.blocos[centralx][centraly - 1].limpar();
			Tetris.blocos[centralx][centraly + 1].limpar();
			break;
		case ESQUERDA:
			Tetris.blocos[centralx][centraly].limpar();
			Tetris.blocos[centralx - 1][centraly].limpar();
			Tetris.blocos[centralx + 1][centraly-1].limpar();
			Tetris.blocos[centralx + 1][centraly].limpar();
			break;

		}
	}

}
