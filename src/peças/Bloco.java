package pe�as;

import java.awt.Color;

import javax.swing.JLabel;
public class Bloco {
	private Pe�a pe�a;
	private JLabel bloco;
	private boolean vazio;
	private class Coor{
	     int x; 
	     int y;   
	 };
	Coor coor=new Coor();
	public Bloco[][] matrizBlocos;
	public Bloco(int x,int y,Bloco[][] matriz){
		this.matrizBlocos=matriz;
		matrizBlocos[x][y]=this;
		coor.x=x;
		coor.y=y;
		setBloco(new JLabel());
		getBloco().setOpaque(true);
		getBloco().setBackground(Color.black);
		setVazio(true);
	}
	public boolean colidir(Pe�a pe�a){
		if(isVazio()||this.pe�a==pe�a){
			return false;
		}
		return true;
	}
	public void limpar(){
		getBloco().setIcon(null);
		setVazio(true);
		pe�a=null;
	}
	
//	public boolean podeMover(int x,int y){
//		if(matrizBlocos[coor.x+x][coor.y+y].colidi(this))
//			return true;
//		return false;
//	}
	
//	public boolean podeDescer(){
//		return podeMover(-1,0);
//	}

	public void criar(Pe�a pe�a){
		getBloco().setIcon(pe�a.icon);
		this.pe�a=pe�a;
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

}
