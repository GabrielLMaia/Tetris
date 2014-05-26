package jogo;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel telas;
	private static final int LARGURA_TELA_JOGO = 600;
	private static final int LARGURA_REAL_TELA_JOGO = LARGURA_TELA_JOGO + 39;
	private static int dificuldade_inicial =1;
	private static boolean gravidade=true;
	private static boolean musica=true;
	private static char musicaTipo='t';
	private static final int COMPRIMENTO_TELA_JOGO = 600;
	private static final int COMPRIMENTO_REAL_TELA_JOGO = COMPRIMENTO_TELA_JOGO + 76;
	
	private Tetris tetris;
	private Dados dados;
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
		setBounds(100, 100, 486, 523);
		setLocationRelativeTo(null);
		setContentPane(telas);
		telas.setLayout(new CardLayout(0, 0));
		JPanel inicial = new JPanel();
		inicial.setBackground(Color.BLACK);
		telas.add(inicial, "tela inicial");

		JLabel Titulo = new JLabel("");
		Titulo.setIcon(new ImageIcon(Principal.class.getResource("/imagens/Tetris.jpg")));
		Titulo.setBounds(-15, 0, 452, 504);
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setFont(new Font("Tahoma", Font.PLAIN, 59));
		Titulo.setForeground(Color.WHITE);
		inicial.setLayout(null);
		
				final JLabel Iniciar = new JLabel("");
				Iniciar.setIcon(new ImageIcon(Principal.class.getResource("/imagens/JogarSel.png")));
				Iniciar.setFont(Iniciar.getFont().deriveFont(Iniciar.getFont().getStyle() | Font.BOLD));
				Iniciar.setBounds(116, 140, 142, 65);
				Iniciar.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent arg0) {
						((CardLayout) telas.getLayout()).show(telas, "jogo");
						setSize(COMPRIMENTO_REAL_TELA_JOGO, LARGURA_REAL_TELA_JOGO);
						setLocationRelativeTo(null);
						Tetris.timer.start();
					}
					@Override
					public void mouseEntered(MouseEvent arg0) {
						Iniciar.setIcon(new ImageIcon(Principal.class.getResource("/imagens/Jogar.png")));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						Iniciar.setIcon(new ImageIcon(Principal.class.getResource("/imagens/JogarSel.png")));
					}
				});
				inicial.add(Iniciar);
		
				final JLabel Sair = new JLabel("");
				Sair.setIcon(new ImageIcon(Principal.class.getResource("/imagens/SairSel.png")));
				Sair.setBounds(110, 280, 132, 42);
				Sair.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent arg0) {
						try {
							System.exit(1);
						} catch (Throwable e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					@Override
					public void mouseEntered(MouseEvent arg0) {
						Sair.setIcon(new ImageIcon(Principal.class.getResource("/imagens/Sair.png")));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						Sair.setIcon(new ImageIcon(Principal.class.getResource("/imagens/SairSel.png")));
					}
				});
				inicial.add(Sair);
		
				final JLabel Config = new JLabel("");
				Config.setIcon(new ImageIcon(Principal.class.getResource("/imagens/ConfigSel.png")));
				Config.setBounds(116, 216, 344, 49);
				Config.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent arg0) {
						try {
							((CardLayout) telas.getLayout()).show(telas, "Conf");
						} catch (Throwable e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					@Override
					public void mouseEntered(MouseEvent arg0) {
						Config.setIcon(new ImageIcon(Principal.class.getResource("/imagens/Config.png")));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						Config.setIcon(new ImageIcon(Principal.class.getResource("/imagens/ConfigSel.png")));
					}
				});
				inicial.add(Config);
		inicial.add(Titulo);
		JPanel gameover = new JPanel();
		telas.add(gameover, "game over");

		JPanel jogo = new JPanel();
		telas.add(jogo, "jogo");
		jogo.setLayout(null);

		ListaPeças lista = new ListaPeças();
		lista.setBounds(510, 0, 150, 600);
		jogo.add(lista);

		tetris = new Tetris(dados);
		tetris.setBounds(180, 0, 300, LARGURA_TELA_JOGO);
		jogo.add(tetris);

		Hold hold = new Hold();
		hold.setBounds(0, 0, 150, 120);
		jogo.add(hold);
		
		dados = new Dados(tetris);
		dados.setBounds(0, 120, 150, 480);
		jogo.add(dados);
		
		JLabel divE = new JLabel("");
		divE.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/Div.png")));
		divE.setBounds(150, 0, 30, 600);
		jogo.add(divE);

		JLabel divD = new JLabel("");
		divD.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/Div.png")));
		divD.setBounds(480, 0, 30, 600);
		jogo.add(divD);

		Controle C = new Controle(tetris);
		
		JPanel Configu = new JPanel();
		Configu.setBackground(Color.BLACK);
		telas.add(Configu, "Conf");
		Configu.setLayout(null);
		
		JLabel TilCon = new JLabel("");
		TilCon.setHorizontalAlignment(SwingConstants.CENTER);
		TilCon.setIcon(new ImageIcon(Principal.class.getResource("/imagens/Config.png")));
		TilCon.setBounds(37, 26, 423, 70);
		Configu.add(TilCon);
		
		final JLabel grav = new JLabel("");
		grav.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(isGravidade()){
					grav.setIcon(new ImageIcon(Principal.class.getResource("/imagens/GraOn.png")));
				}else{
					grav.setIcon(new ImageIcon(Principal.class.getResource("/imagens/GraOff.png")));
				}
				onOffGravidade();
			}
		});
		grav.setIcon(new ImageIcon(Principal.class.getResource("/imagens/GraOn.png")));
		grav.setBounds(10, 127, 460, 63);
		Configu.add(grav);
		
		JLabel voltar = new JLabel("");
		voltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout) telas.getLayout()).show(telas, "tela inicial");
			}
		});
		voltar.setIcon(new ImageIcon(Principal.class.getResource("/imagens/VoltarSel - Copia.png")));
		voltar.setBounds(37, 445, 114, 28);
		Configu.add(voltar);
		
		JLabel musicLabel = new JLabel("");
		musicLabel.setIcon(new ImageIcon(Principal.class.getResource("/imagens/MuOn.png")));
		musicLabel.setBounds(0, 196, 236, 63);
		Configu.add(musicLabel);
		
		JLabel musicTipoLabel = new JLabel("");
		musicTipoLabel.setIcon(new ImageIcon(Principal.class.getResource("/imagens/Tetris.png")));
		musicTipoLabel.setBounds(278, 207, 137, 38);
		Configu.add(musicTipoLabel);
		
		JLabel setaDir = new JLabel("");
		setaDir.setIcon(new ImageIcon(Principal.class.getResource("/imagens/setaDsel.png")));
		setaDir.setBounds(414, 204, 46, 45);
		Configu.add(setaDir);
		
		JLabel setaEsq = new JLabel("");
		setaEsq.setIcon(new ImageIcon(Principal.class.getResource("/imagens/setaEsel.png")));
		setaEsq.setBounds(222, 201, 46, 45);
		Configu.add(setaEsq);
		this.addKeyListener(C);

	}

	public static int getDificuldade_inicial() {
		return dificuldade_inicial;
	}

	public static void mudarDificuldade() {
		if(dificuldade_inicial==3){
			dificuldade_inicial=0;
		}else
			dificuldade_inicial++;
	}

	public static boolean isGravidade() {
		return gravidade;
	}

	public static void onOffGravidade() {
		if(gravidade){
			gravidade = false;
		}else
			gravidade=true;
	}

	public static boolean isMusica() {
		return musica;
	}

	public static void onOffMusica() {
		if(musica){
			musica = false;
		}else
			musica=true;
	}
}
