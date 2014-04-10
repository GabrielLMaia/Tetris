package jogo;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import peças.*;

public class Tetris extends JPanel {
	public static Bloco[][] blocos=new Bloco[22][10];
	private static final long serialVersionUID = 1L;
	public Tetris(){
		setLayout(new GridLayout(20, 10, 0, 0));
		for(int i=0;i<blocos.length;i++){
			for(int j=0;j<blocos[0].length;j++){
				blocos[i][j]=new Bloco(i,j);
				if(i>1){
				add(blocos[i][j].getBloco());
				blocos[i][j].getBloco().setText(i+" "+j);
				}
			}
		}	
		PeçaT T=new PeçaT(5,4);
		Bloco b=blocos[15][9];
		b.getBloco().setText("oi");
		blocos[16][9].setBloco(blocos[15][9].getBloco());
//		blocos[16][9].bloco.setText(blocos[15][9].bloco.getText()); work!
		
	}

	
}
