package jogo;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import peças.*;

public class Tetris extends JPanel {
	private static final long serialVersionUID = 1L;
	public final static int LARGURA=20;
	public final static int COMPRIMENTO=10;
	public static Bloco[][] blocos=new Bloco[LARGURA+4][COMPRIMENTO+4];
	public Tetris(){
			
		setLayout(new GridLayout(LARGURA, COMPRIMENTO, 0, 0));	
		for(int i=0;i<blocos.length;i++){
			for(int j=0;j<blocos[0].length;j++){
				
				blocos[i][j]=new Bloco(i,j,blocos);
                if(j<=1||j>=COMPRIMENTO+2||i>=LARGURA+2){
                	blocos[i][j].setVazio(false);
				}else
				if(i>1){
				add(blocos[i][j].getBloco());			
				}
			}
		}
		
//		PeçaT T=new PeçaT(7,4);
//		Bloco b= new Bloco(9,9,blocos);
//		b.getBloco().setBackground(new Color(200));
//		b.getBloco().setText("oioi");
//		getComponent(9*9).setBackground(Color.blue);
//		blocos[9][9].getBloco().setBackground(Color.blue);
//		blocos[9][9].getBloco().setText("oioio");
//		System.out.println(T.blocos[0].b==blocos);
//		blocos[7][4].teste(T);
//		T.girar();
//		T.girar();
//		T.girar();
//		T.girar();
				
//				Bloco b=blocos[15][9];
//		b.getBloco().setText("oi");
//		blocos[16][9].setBloco(blocos[15][9].getBloco());
//		blocos[16][9].bloco.setText(blocos[15][9].bloco.getText()); work!
		
	}
	public void pegar(){

	}
	
}
