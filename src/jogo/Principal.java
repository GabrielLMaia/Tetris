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

import javax.swing.ImageIcon;

import java.awt.Component;

import javax.swing.Box;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel telas;
	private static final int LARGURA_TELA_JOGO=600;
	private static final int LARGURA_REAL_TELA_JOGO=LARGURA_TELA_JOGO+39;
	private static final int COMPRIMENTO_TELA_JOGO=600;
	private static final int COMPRIMENTO_REAL_TELA_JOGO=COMPRIMENTO_TELA_JOGO+76;
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
		Titulo.setBounds(0, 74, 484, 72);
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setFont(new Font("Tahoma", Font.PLAIN, 59));
		Titulo.setForeground(Color.WHITE);
		
		JLabel Iniciar = new JLabel("Iniciar");
		Iniciar.setBounds(208, 227, 50, 14);
		Iniciar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				 ((CardLayout) telas.getLayout()).show(telas, "jogo");
				 setSize(COMPRIMENTO_REAL_TELA_JOGO, LARGURA_REAL_TELA_JOGO);
				 setLocationRelativeTo(null);
			}
		});
		Iniciar.setForeground(Color.WHITE);
		
		JLabel Config = new JLabel("Configura\u00E7\u00F5es");
		Config.setBounds(208, 253, 97, 14);
		Config.setForeground(Color.WHITE);
		inicial.setLayout(null);
		inicial.add(Titulo);
		inicial.add(Iniciar);
		inicial.add(Config);
		
		JLabel lblVaiSeFuder = new JLabel("Vai se fuder");
		lblVaiSeFuder.setForeground(Color.WHITE);
		lblVaiSeFuder.setBounds(208, 278, 91, 14);
		inicial.add(lblVaiSeFuder);
		Tetris tetris =new Tetris();
		tetris.setBounds(180, 0, 300, LARGURA_TELA_JOGO);
//		telas.add(tetris, "tetris");
		JPanel gameover = new JPanel();
		telas.add(gameover, "game over");
		JPanel teste = new JPanel();
		telas.add(teste, "name_22404026101542");
		teste.setLayout(new GridLayout(16, 10, 1, 1));		
		
		JPanel jogo = new JPanel();
		telas.add(jogo, "jogo");
		jogo.setLayout(null);
		jogo.add(tetris);
		
		JLabel aux = new JLabel("");
		 aux.setIcon(new ImageIcon(Principal.class.getResource("/imagens/HOLD.png")));
		 aux.setBounds(0, 0, 150, 150);
		jogo.add( aux);
		
		JLabel divE = new JLabel("");
		divE.setIcon(new ImageIcon(Principal.class.getResource("/imagens/Div.png")));
		divE.setBounds(150, 0, 30, 600);
		jogo.add(divE);
		
		JLabel divD = new JLabel("");
		divD.setIcon(new ImageIcon(Principal.class.getResource("/imagens/Div.png")));
		divD.setBounds(480, 0, 30, 600);
		jogo.add(divD);
		
		ListaPeças lista =new ListaPeças();
		 lista.setBounds(510,0 , 150, 600);
		jogo.add( lista);
		lista.setBackground(Color.GREEN);
		
//		JLabel imagemLista = new JLabel("");
//		imagemLista.setIcon(new ImageIcon(Principal.class.getResource("/imagens/List.png")));
//		imagemLista.setBounds(510,0,150,600);
//		jogo.add(imagemLista);
		Controle C=new Controle();
		this.addKeyListener(C);			
		
	}
}
