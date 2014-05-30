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
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel telas;
	private static final int LARGURA_TELA_JOGO = 600;
	private static final int LARGURA_REAL_TELA_JOGO = LARGURA_TELA_JOGO + 39;
	private static boolean gravidade = true;
	private static int dif=1;
	private static JLabel pontos;
	private static boolean musica = true;
	private static char musicaTipo = 't';
	private static final int COMPRIMENTO_TELA_JOGO = 600;
	private static final int COMPRIMENTO_REAL_TELA_JOGO = COMPRIMENTO_TELA_JOGO + 76;
	private static Placar placar = new Placar();
	private Tetris tetris;
	private Dados dados;
	private JTextField nome;
	private static JTable table;

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

		this.setTitle("Tetris Blaster");
		telas = new JPanel();
		setBounds(100, 100, 486, 523);
		setLocationRelativeTo(null);
		setContentPane(telas);
		telas.setLayout(new CardLayout(0, 0));
		JPanel inicial = new JPanel();
		inicial.setBackground(Color.BLACK);
		telas.add(inicial, "tela inicial");

		JLabel Titulo = new JLabel("");
		Titulo.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/Tetris.jpg")));
		Titulo.setBounds(-15, 0, 452, 504);
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setFont(new Font("Tahoma", Font.PLAIN, 59));
		Titulo.setForeground(Color.WHITE);
		inicial.setLayout(null);

		final JLabel Iniciar = new JLabel("");
		Iniciar.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/JogarSel.png")));
		Iniciar.setFont(Iniciar.getFont().deriveFont(
				Iniciar.getFont().getStyle() | Font.BOLD));
		Iniciar.setBounds(116, 140, 142, 65);
		Iniciar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Tetris.setIntervaloInicial();
				Tetris.reset();
				((CardLayout) telas.getLayout()).show(telas, "jogo");
				setSize(COMPRIMENTO_REAL_TELA_JOGO, LARGURA_REAL_TELA_JOGO);
				setLocationRelativeTo(null);
				Tetris.timer.start();
				if (isMusica())
					Tetris.music(musicaTipo);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				Iniciar.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/Jogar.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Iniciar.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/JogarSel.png")));
			}
		});
		inicial.add(Iniciar);

		final JLabel Sair = new JLabel("");
		Sair.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/SairSel.png")));
		Sair.setBounds(110, 280, 132, 42);
		Sair.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Tetris.finalizarMusic();
					System.exit(1);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				Sair.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/Sair.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Sair.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/SairSel.png")));
			}
		});
		inicial.add(Sair);

		JPanel jogo = new JPanel();
		telas.add(jogo, "jogo");
		jogo.setLayout(null);

		ListaPeças lista = new ListaPeças();
		lista.setBounds(510, 0, 150, 600);
		jogo.add(lista);

		tetris = new Tetris(this);
		tetris.setBounds(180, 0, 300, LARGURA_TELA_JOGO);
		jogo.add(tetris);

		Hold hold = new Hold();
		hold.setBounds(0, 0, 150, 120);
		jogo.add(hold);

		dados = new Dados();
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

		final JLabel Config = new JLabel("");
		Config.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/ConfigSel.png")));
		Config.setBounds(116, 216, 344, 49);
		Config.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					((CardLayout) telas.getLayout()).show(telas, "conf");
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				Config.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/Config.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Config.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/ConfigSel.png")));
			}
		});
		inicial.add(Config);
		inicial.add(Titulo);
		JPanel gameover = new JPanel();
		gameover.setBackground(Color.BLACK);
		telas.add(gameover, "gameover");
		gameover.setLayout(null);

		JLabel lblSeusPontos = new JLabel("Seus pontos:");
		lblSeusPontos.setFont(new Font("FixedsysTTF", Font.BOLD, 20));
		lblSeusPontos.setForeground(Color.WHITE);
		lblSeusPontos.setBounds(10, 11, 144, 34);
		gameover.add(lblSeusPontos);

		pontos = new JLabel("");
		pontos.setForeground(Color.WHITE);
		pontos.setFont(new Font("FixedsysTTF", Font.BOLD, 20));
		pontos.setBounds(164, 11, 296, 34);
		gameover.add(pontos);

		JLabel lblSeuNome = new JLabel("Seu nome:");
		lblSeuNome.setForeground(Color.WHITE);
		lblSeuNome.setFont(new Font("FixedsysTTF", Font.BOLD, 20));
		lblSeuNome.setBounds(10, 56, 113, 34);
		gameover.add(lblSeuNome);

		nome = new JTextField();
		nome.setFont(new Font("FixedsysTTF", Font.PLAIN, 20));
		nome.setBounds(133, 55, 296, 40);
		gameover.add(nome);
		nome.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 149, 470, 261);
		gameover.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(placar.matrizTabela(),
				new String[] { "Nome", "Pontos" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(table);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				placar.armazenar(nome.getText(),
						Integer.parseInt(pontos.getText()));
				setModelo();
			}
		});
		btnSalvar.setBounds(174, 107, 90, 28);
		gameover.add(btnSalvar);
		
		final JLabel voltar2 = new JLabel("");
		voltar2.setBounds(10, 444, 114, 28);
		gameover.add(voltar2);
		voltar2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout) telas.getLayout()).show(telas, "tela inicial");
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				voltar2.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/Voltar.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				voltar2.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/VoltarSel.png")));
			}
		});
		voltar2.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/VoltarSel.png")));
		Controle C = new Controle();

		JPanel config = new JPanel();
		config.setBackground(Color.BLACK);
		telas.add(config, "conf");
		config.setLayout(null);

		JLabel TilCon = new JLabel("");
		TilCon.setHorizontalAlignment(SwingConstants.CENTER);
		TilCon.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/ConfigSel.png")));
		TilCon.setBounds(37, 26, 423, 70);
		config.add(TilCon);

		final JLabel grav = new JLabel("");
		grav.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (!isGravidade()) {
					grav.setIcon(new ImageIcon(Principal.class
							.getResource("/imagens/GraOn.png")));
				} else {
					grav.setIcon(new ImageIcon(Principal.class
							.getResource("/imagens/GraOff.png")));
				}
				onOffGravidade();
			}
		});
		grav.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/GraOn.png")));
		grav.setBounds(10, 127, 460, 63);
		config.add(grav);

		final JLabel voltar = new JLabel("");
		voltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((CardLayout) telas.getLayout()).show(telas, "tela inicial");
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				voltar.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/Voltar.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				voltar.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/VoltarSel.png")));
			}
		});
		voltar.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/VoltarSel.png")));
		voltar.setBounds(37, 445, 114, 28);
		config.add(voltar);

		final JLabel musicLabel = new JLabel("");
		musicLabel.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/MuOn.png")));
		musicLabel.setBounds(0, 196, 236, 63);
		config.add(musicLabel);
		musicLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (isMusica()) {
					musicLabel.setIcon(new ImageIcon(Principal.class
							.getResource("/imagens/MuOff.png")));
				} else {
					musicLabel.setIcon(new ImageIcon(Principal.class
							.getResource("/imagens/MuOn.png")));
				}
				onOffMusica();
			}
		});

		final JLabel musicTipoLabel = new JLabel("");
		musicTipoLabel.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/Tetris.png")));
		musicTipoLabel.setBounds(278, 207, 137, 38);
		config.add(musicTipoLabel);

		final JLabel setaD = new JLabel("");
		setaD.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/setaDsel.png")));
		setaD.setBounds(414, 206, 46, 45);
		config.add(setaD);
		setaD.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					switch (musicaTipo) {
					case ('m'):
						musicaTipo = 'z';
						musicTipoLabel.setIcon(new ImageIcon(Principal.class
								.getResource("/imagens/Zelda.png")));
						break;
					case ('t'):
						musicaTipo = 'm';
						musicTipoLabel.setIcon(new ImageIcon(Principal.class
								.getResource("/imagens/Megaman.png")));
						break;
					case ('z'):
						musicaTipo = 't';
						musicTipoLabel.setIcon(new ImageIcon(Principal.class
								.getResource("/imagens/Tetris.png")));
						break;
					}
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setaD.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/setaD.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setaD.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/setaDsel.png")));
			}
		});

		final JLabel setaEsq = new JLabel("");
		setaEsq.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					switch (musicaTipo) {
					case ('t'):
						musicaTipo = 'z';
						musicTipoLabel.setIcon(new ImageIcon(Principal.class
								.getResource("/imagens/Zelda.png")));
						break;
					case ('z'):
						musicaTipo = 'm';
						musicTipoLabel.setIcon(new ImageIcon(Principal.class
								.getResource("/imagens/Megaman.png")));
						break;
					case ('m'):
						musicaTipo = 't';
						musicTipoLabel.setIcon(new ImageIcon(Principal.class
								.getResource("/imagens/Tetris.png")));
						break;
					}
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setaEsq.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/setaE.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setaEsq.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/setaEsel.png")));
			}
		});
		setaEsq.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/setaEsel.png")));
		setaEsq.setBounds(222, 201, 46, 45);
		config.add(setaEsq);

		JLabel dificuldadeT = new JLabel("");
		dificuldadeT.setIcon(new ImageIcon(Principal.class
				.getResource("/imagens/Dificuldade.png")));
		dificuldadeT.setBounds(16, 270, 206, 45);
		config.add(dificuldadeT);
		
		final JLabel dificuldade = new JLabel("");
		dificuldade.setIcon(new ImageIcon(Principal.class.getResource("/imagens/Normal.png")));
		dificuldade.setBounds(278, 273, 127, 38);
		config.add(dificuldade);
		
		final JLabel setaEsqDif = new JLabel("");
		setaEsqDif.setIcon(new ImageIcon(Principal.class.getResource("/imagens/setaEsel.png")));
		setaEsqDif.setBounds(222, 265, 46, 45);
		config.add(setaEsqDif);
		setaEsqDif.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					switch (dif) {
					case (0):
						dificuldade.setIcon(new ImageIcon(Principal.class
								.getResource("/imagens/skull.jpg")));
						break;
					case (1):
						dificuldade.setIcon(new ImageIcon(Principal.class
								.getResource("/imagens/Fácil.png")));
						break;
					case (2):
						dificuldade.setIcon(new ImageIcon(Principal.class
								.getResource("/imagens/Normal.png")));
						break;
					case (3):
						dificuldade.setIcon(new ImageIcon(Principal.class
								.getResource("/imagens/Difícil.png")));
						break;
					}
					diminuirDificuldade();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setaEsqDif.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/setaE.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setaEsqDif.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/setaEsel.png")));
			}
		});
		final JLabel setaDiDif = new JLabel("");
		setaDiDif.setIcon(new ImageIcon(Principal.class.getResource("/imagens/setaDsel.png")));
		setaDiDif.setBounds(414, 271, 46, 45);
		config.add(setaDiDif);
		setaDiDif.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					switch (dif) {
					case (2):
						dificuldade.setIcon(new ImageIcon(Principal.class
								.getResource("/imagens/skull.jpg")));
						break;
					case (3):
						dificuldade.setIcon(new ImageIcon(Principal.class
								.getResource("/imagens/Fácil.png")));
						break;
					case (0):
						dificuldade.setIcon(new ImageIcon(Principal.class
								.getResource("/imagens/Normal.png")));
						break;
					case (1):
						dificuldade.setIcon(new ImageIcon(Principal.class
								.getResource("/imagens/Difícil.png")));
						break;
					}
					aumentarDificuldade();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setaDiDif.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/setaD.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setaDiDif.setIcon(new ImageIcon(Principal.class
						.getResource("/imagens/setaDsel.png")));
			}
		});
		this.addKeyListener(C);

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				Tetris.finalizarMusic();
				System.exit(0);
			}
		});

	}
	public void gameover(){
		Tetris.setIntervaloInicial();
		setBounds(100, 100, 486, 523);
		setLocationRelativeTo(null);
		((CardLayout) telas.getLayout()).show(telas, "gameover");
		pontos.setText(""+Tetris.getPontuação());
		Tetris.timer.stop();
		if (isMusica())
			Tetris.pauseMusic();;
	}


	public static boolean isGravidade() {
		return gravidade;
	}

	public static char getMusicaTipo() {
		return musicaTipo;
	}

	public static void onOffGravidade() {
		if (gravidade) {
			gravidade = false;
		} else
			gravidade = true;
	}

	public static boolean isMusica() {
		return musica;
	}

	public static void onOffMusica() {
		if (musica) {
			musica = false;
		} else
			musica = true;
	}

	public static int getDificuldade() {
		return dif;
	}

	public static void aumentarDificuldade() {
		if(dif==3){
			dif=0;
		}else
		dif++;
	}
	public static void diminuirDificuldade() {
		if(dif==0){
			dif=3;
		}else
		dif--;
	}
	public static void setModelo(){
		table.setModel(new DefaultTableModel(placar.matrizTabela(),
				new String[] { "Nome", "Pontos" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}
}
