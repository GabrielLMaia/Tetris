package peças;

import java.awt.Color;

import javax.swing.JLabel;
import jogo.*;
public class Bloco {
	private Peças p;
	private JLabel bloco =new JLabel();
	private boolean vazio;
	private int coorx;
	private int coory;
	
	public Bloco(int x,int y){
		getBloco().setOpaque(true);
		getBloco().setBackground(Color.black);
		Tetris.blocos[x][y]=this;
		this.coorx=x;
		this.coory=y;
		setVazio(true);
	}
	public boolean colidirB(){
		if(!Tetris.blocos[coorx-1][coory].isVazio()&&!Tetris.blocos[coorx][coory].isVazio()){
			return true;
		}
		return false;
	}
	public boolean colidirC(){
		if(!Tetris.blocos[coorx+1][coory].isVazio()&&!Tetris.blocos[coorx][coory].isVazio()){
			return true;
		}
		return false;
	}
	public boolean colidirD(){
		if(!Tetris.blocos[coorx][coory+1].isVazio()&&!Tetris.blocos[coorx][coory].isVazio()){
			return true;
		}
		return false;
	}
	public boolean colidirE(){
		if(!Tetris.blocos[coorx][coory-1].isVazio()&&!Tetris.blocos[coorx][coory].isVazio()){
			return true;
		}
		return false;
	}
	public void limpar(Bloco b){
		b.getBloco().setIcon(null);
		b.setVazio(true);
	}
	public void mover(int x,int y){
		Tetris.blocos[coorx+x][coory+y].getBloco().setIcon(Tetris.blocos[coorx][coory].getBloco().getIcon());
		Tetris.blocos[coorx+x][coory+y].setVazio(true);
		limpar(Tetris.blocos[coorx][coory]);
		coorx=coorx+x;
		coory=coory+y;
	}
	public void moverB(){
		mover(-1,0);
	}
	public void moverE(){
		mover(0,-1);
	}
	public void moverD(){
		mover(0,+1);
	}
	public JLabel getBloco() {
		return bloco;
	}
	public void setBloco(JLabel bloco) {
		this.bloco = bloco;
	}
	public boolean isVazio() {
		return vazio;
	}
	public void setVazio(boolean vazio) {
		this.vazio = vazio;
	}
}
