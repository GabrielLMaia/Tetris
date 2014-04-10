package jogo;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel telas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telas = new JPanel();
		setBounds(100,100,500,500);
		setLocationRelativeTo(null);
		setContentPane(telas);
		telas.setLayout(new CardLayout(0, 0));
		JPanel inicial = new JPanel();
		inicial.setBackground(Color.BLACK);
		telas.add(inicial, "tela inicial");
		
		JLabel Titulo = new JLabel("Tetris");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setFont(new Font("Tahoma", Font.PLAIN, 59));
		Titulo.setForeground(Color.WHITE);
		
		JLabel Iniciar = new JLabel("Iniciar");
		Iniciar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				 ((CardLayout) telas.getLayout()).show(telas, "tetris");
				 setSize(500, 600);
				 setLocationRelativeTo(null);
			}
		});
		Iniciar.setForeground(Color.WHITE);
		
		JLabel Config = new JLabel("Configura\u00E7\u00F5es");
		Config.setForeground(Color.WHITE);
		GroupLayout gl_inicial = new GroupLayout(inicial);
		gl_inicial.setHorizontalGroup(
			gl_inicial.createParallelGroup(Alignment.TRAILING)
				.addComponent(Titulo, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
				.addGroup(gl_inicial.createSequentialGroup()
					.addGap(224)
					.addComponent(Iniciar)
					.addContainerGap(223, Short.MAX_VALUE))
				.addGroup(gl_inicial.createSequentialGroup()
					.addContainerGap(203, Short.MAX_VALUE)
					.addComponent(Config)
					.addGap(198))
		);
		gl_inicial.setVerticalGroup(
			gl_inicial.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inicial.createSequentialGroup()
					.addGap(74)
					.addComponent(Titulo)
					.addGap(81)
					.addComponent(Iniciar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(Config)
					.addContainerGap(183, Short.MAX_VALUE))
		);
		inicial.setLayout(gl_inicial);
		Tetris tetris =new Tetris();
	
		telas.add(tetris, "tetris");
		JPanel gameover = new JPanel();
		telas.add(gameover, "game over");
		
		JPanel teste = new JPanel();
		telas.add(teste, "name_22404026101542");
		teste.setLayout(new GridLayout(16, 10, 1, 1));		
		
						
		
	}
}
