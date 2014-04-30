package peças;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import jogo.*;
public class Bloco {
	private Peça p;
	private JLabel bloco;
	private boolean vazio;
	private int coorx;
	private int coory;
	public Bloco[][] b;
	public Bloco(int x,int y,Bloco[][] b){
		this.b=b;
		b[x][y]=this;
		setBloco(new JLabel());
		getBloco().setOpaque(true);
		getBloco().setBackground(Color.black);
//		Tetris.blocos[x][y]=this;
		this.coorx=x;
		this.coory=y;
		setVazio(true);
	}
	public void teste(PeçaT p){
		this.p=p;
		System.out.println(p.f);
	
	}
	public boolean colidir(Peça p){
		if(isVazio()||this.p==p){
			return false;
		}
		return true;
	}
	public void limpar(){
		getBloco().setIcon(null);
		setVazio(true);
		p=null;
	}
//	public void mover(int x,int y){
//		b[coorx+x][coory+y].getBloco().setIcon(b[coorx][coory].getBloco().getIcon());
//		b[coorx+x][coory+y].setVazio(true);
//		limpar(b[coorx][coory]);
//		coorx=coorx+x;
//		coory=coory+y;
//	}
//	public void moverB(){
//		mover(-1,0);
//	}
//	public void moverE(){
//		mover(0,-1);
//	}
//	public void moverD(){
//		mover(0,+1);
//	}
	public void criar(ImageIcon icon,Peça p){
		getBloco().setIcon(icon);
		this.p=p;
		setVazio(false);
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

	public int getCoorx() {
		return coorx;
	}

	public void setCoorx(int coorx) {
		this.coorx = coorx;
	}

	public int getCoory() {
		return coory;
	}

	public void setCoory(int coory) {
		this.coory = coory;
	}
}
