package peças;

import java.awt.Color;

import jogo.Tetris;

public class PeçaT extends Peças {
	
	public PeçaT(int pex,int pey) {
		super();	
		blocos[0]=Tetris.blocos[pex-1][pey];
		blocos[1]=Tetris.blocos[pex][pey-1];
		blocos[2]=Tetris.blocos[pex][pey];
		blocos[3]=Tetris.blocos[pex][pey+1];
		for (int i = 0; i <4; i++) {
			blocos[i].getBloco().setBackground(Color.green);
			blocos[i].setVazio(false);
		}
	}

	@Override
	public boolean colidirB() {
		switch(s){
		case NORMAL:
			if(blocos[1].colidirB()||blocos[2].colidirB()||blocos[3].colidirB())
				return true;
			else
				return false;
		case DIREIRA:
			if(blocos[0].colidirB()||blocos[3].colidirB())
				return true;
			else
				return false;
		case BAIXO:
			if(blocos[0].colidirB()||blocos[1].colidirB()||blocos[3].colidirB())
				return true;
			else
				return false;
		case ESQUERDA:
			if(blocos[0].colidirB()||blocos[1].colidirB())
				return true;
			else
				return false;
		}
		return false;
		
	}

	@Override
	public boolean colidirD() {
		switch(s){
		case NORMAL:
			if(blocos[0].colidirD()||blocos[3].colidirD())
				return true;
			else
				return false;
		case DIREIRA:
			if(blocos[0].colidirD()||blocos[1].colidirD()||blocos[3].colidirD())
				return true;
			else
				return false;
		case BAIXO:
			if(blocos[0].colidirD()||blocos[1].colidirD())
				return true;
			else
				return false;
		case ESQUERDA:
			if(blocos[1].colidirD()||blocos[2].colidirD()||blocos[3].colidirD())
				return true;
			else
				return false;
		}
		return false;
	}

	@Override
	public boolean colidirE() {
		switch(s){
		case NORMAL:
			if(blocos[0].colidirD()||blocos[1].colidirD())
				return true;
			else
				return false;
		case DIREIRA:
			if(blocos[1].colidirD()||blocos[2].colidirD()||blocos[3].colidirD())
				return true;
			else
				return false;
		case BAIXO:
			if(blocos[0].colidirD()||blocos[3].colidirD())
				return true;
			else
				return false;
		case ESQUERDA:
			if(blocos[1].colidirD()||blocos[2].colidirD()||blocos[3].colidirD())
				return true;
			else
				return false;
		}
		return false;
	}

	@Override
	public void moverB() {
		
		
	}

	@Override
	public void moverD() {
		
		
	}

	@Override
	public void moverE() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void girar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pintar() {
		
		
	}

	
	
	
}
//     0   
//    123 