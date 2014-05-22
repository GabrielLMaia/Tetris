package jogo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dados extends JPanel {

	private static final long serialVersionUID = 1L;
	private Tetris tetris;
	private JLabel pontos=new JLabel();
	private JLabel nivel=new JLabel();
	private JLabel linhas=new JLabel();
	private JLabel colunas=new JLabel();
	private final int POSIÇÃO_UM=35;
	private final int ESPAÇAMENTO=90;
	public Dados(Tetris tetris){
		this.setLayout(null);
		this.tetris=tetris;
		this.setBackground(Color.green);
		
		Font font =new Font("Vani", Font.PLAIN, 16);
		
		pontos.setBounds(35, POSIÇÃO_UM, 120, 20);
		pontos.setForeground(Color.WHITE);
		pontos.setFont(font);
		add(pontos);
		
		nivel.setBounds(35, POSIÇÃO_UM+ESPAÇAMENTO, 120, 20);
		nivel.setForeground(Color.WHITE);
		nivel.setFont(font);
		add(nivel);
		
		linhas.setBounds(35, POSIÇÃO_UM+ESPAÇAMENTO*2, 120, 20);
		linhas.setForeground(Color.WHITE);
		linhas.setFont(font);
		add(linhas);
		
		colunas.setBounds(35, POSIÇÃO_UM+ESPAÇAMENTO*3, 120, 20);
		colunas.setForeground(Color.WHITE);
		colunas.setFont(font);
		add(colunas);
		
		JLabel imagDados=new JLabel();
		imagDados.setBounds(0,0,150,480);
		imagDados.setIcon(new ImageIcon(getClass().getResource("/imagens/Dados.png")));
		add(imagDados);
		
		tetris.dados=this;
		setDados();
	}
	public void setDados(){
		pontos.setText(""+tetris.pontuação);
		nivel.setText(""+tetris.nivel);
		linhas.setText(""+tetris.numLinhasElim);
		colunas.setText(""+tetris.numColunasElim);
	}
}
