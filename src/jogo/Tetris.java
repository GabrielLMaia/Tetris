package jogo;
import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import peças.*;

public class Tetris extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public final static int LARGURA=20;
	public final static int COMPRIMENTO=10;
	public final static int LARGURA_REAL=LARGURA+4;
	public final static int COMPRIMENTO_REAL=COMPRIMENTO+4;
	public final static int INICIO_COLUNA=2;
	public final static int FIM_COLUNA=COMPRIMENTO_REAL-3;
	public final static int INICIO_LINHA=2;
	public final static int FALSE=-1;
	public final static int FIM_LINHA=LARGURA_REAL-3;
	
	public static int intervalo =2000;
	public static Bloco[][] blocos=new Bloco[LARGURA_REAL][COMPRIMENTO_REAL];
	public static PeçaZ peçaAtual;
	public static PeçaT peçaSombra;
	
	public Tetris(){		
		setLayout(new GridLayout(LARGURA, COMPRIMENTO, 0, 0));	
		for(int i=0;i<LARGURA_REAL;i++){
			for(int j=0;j<COMPRIMENTO_REAL;j++){
				blocos[i][j]=new Bloco(i,j,blocos);
                if(j<=1||j>=COMPRIMENTO+2||i>=LARGURA+2){
                	blocos[i][j].setVazio(false);
				}else
				if(i>1){
				add(blocos[i][j].getBloco());			
				}
			}
		}

		peçaAtual=new PeçaZ(5,5,blocos);
		PeçaI t=new PeçaI(FIM_LINHA,INICIO_COLUNA+1,blocos);
		PeçaI te=new PeçaI(FIM_LINHA,INICIO_COLUNA+5,blocos);
	
	}
	
	public Peça pegar(){
		return null;
	}
	
	public void jogo(){
		//peçaAtual=pegar();
		while(peçaAtual.podeDescer()){
//			Timer t=new Timer();
			peçaAtual.descer();
		}
		while(checarLinhas()!=-1&&checarColunas()!=-1);
		jogo();
	}
	public int checarColunas(){
		return -1;
	}
	public static int checarLinhas(){
		int numDeBlocosLinha=0;
		int inicio=-1;
		int fim=-1;
		
		for (int i = INICIO_LINHA; i <= FIM_LINHA; i++) {
			numDeBlocosLinha=0;
			for (int j = INICIO_COLUNA; j <= FIM_COLUNA; j++) {
				if(!blocos[i][j].isVazio()){
					numDeBlocosLinha++;
				}
			}
			if(inicio!=0){
				if(numDeBlocosLinha==COMPRIMENTO)
					inicio=i;
			}else
			if(numDeBlocosLinha!=COMPRIMENTO){
				fim=i-1;
				break;
			}
		}

		if(fim==-1)
			fim=FIM_LINHA;
		if(inicio!=-1){
			eliminarLinhas(inicio, fim);
			return inicio;
		}
		return FALSE;
	}

	public static void eliminarLinhas(int inicio,int fim){
//		System.out.println("oi");
		for (int i = inicio; i <= fim; i++) {
			for (int j = INICIO_COLUNA; j <= FIM_COLUNA; j++) {
				blocos[i][j].limpar();
			}
		}
	}
	
}
