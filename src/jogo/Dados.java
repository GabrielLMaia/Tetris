package jogo;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dados extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JLabel pontos=new JLabel();
	private static JLabel nivel=new JLabel();
	private static JLabel linhas=new JLabel();
	private static JLabel colunas=new JLabel();
	private final int POSI��O_UM=35;
	private final int ESPA�AMENTO=90;
	public Dados(){
		this.setLayout(null);
		
		Font font =new Font("Vani", Font.PLAIN, 16);
		
		pontos.setBounds(35, POSI��O_UM, 120, 20);
		pontos.setForeground(Color.WHITE);
		pontos.setFont(font);
		add(pontos);
		
		nivel.setBounds(35, POSI��O_UM+ESPA�AMENTO, 120, 20);
		nivel.setForeground(Color.WHITE);
		nivel.setFont(font);
		add(nivel);
		
		linhas.setBounds(35, POSI��O_UM+ESPA�AMENTO*2, 120, 20);
		linhas.setForeground(Color.WHITE);
		linhas.setFont(font);
		add(linhas);
		
		colunas.setBounds(35, POSI��O_UM+ESPA�AMENTO*3, 120, 20);
		colunas.setForeground(Color.WHITE);
		colunas.setFont(font);
		add(colunas);
		
		JLabel imagDados=new JLabel();
		imagDados.setBounds(0,0,150,480);
		imagDados.setIcon(new ImageIcon(getClass().getResource("/imagens/Dados.png")));
		add(imagDados);
		
		setDados();
	}
	public static void setDados(){
		pontos.setText(""+Tetris.getPontua��o());
		nivel.setText(""+Tetris.getNivel());
		linhas.setText(""+Tetris.getNumLinhasElim());
		colunas.setText(""+Tetris.getNumColunasElim());
	}
}
