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
	 
    public void descer(int vezes){
    	matrizBlocos[coor.x+vezes][coor.y].getBloco().setIcon(this.getBloco().getIcon());
    	matrizBlocos[coor.x+vezes][coor.y].setVazio(isVazio());
    	matrizBlocos[coor.x+vezes][coor.y].pe�a=this.pe�a;
    	limpar();
    } 

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
