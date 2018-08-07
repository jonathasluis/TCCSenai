package outraJanelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import DAO.Fazenda;
import JanelasAnimal.CadastrarAnimais;
import JanelasComtabil.NovaCompra;
import JanelasComtabil.NovaVenda;
import JanelasComtabil.Total;
import JanelasFuncionarios.CadastrarFuncionarios;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
//

public class Principal {

	public static JFrame frmPrincipal;
	static JButton button;
	public static Fazenda fazenda = new Fazenda();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Principal window = new Principal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/logo-pequena-sem-texto.png")));
		frmPrincipal.setTitle("Principal");
		frmPrincipal.setBounds(100, 100, 1080, 720);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.setResizable(false);
		frmPrincipal.setLocationRelativeTo(null);
		frmPrincipal.getContentPane().setLayout(null);
		
		Pergunta.main(null);
		Pergunta.contador = 0;	
		
		JLabel lblNomeDoSistema = new JLabel("Nome do sistema");
		lblNomeDoSistema.setBackground(Color.WHITE);
		lblNomeDoSistema.setForeground(Color.DARK_GRAY);
		lblNomeDoSistema.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNomeDoSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeDoSistema.setBounds(10, 11, 1054, 43);
		frmPrincipal.getContentPane().add(lblNomeDoSistema);
		
		ImageIcon iconAnimal = new ImageIcon("src/img/vaca1.png");
		iconAnimal.setImage(iconAnimal.getImage().getScaledInstance(135,101 ,100));
		JButton btnAnimais = new JButton("");
		btnAnimais.setBorderPainted(false);
		btnAnimais.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnimais.setFocusCycleRoot(true);
		btnAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarAnimais.main(null);
				frmPrincipal.setVisible(false);
			}
		});
		btnAnimais.setContentAreaFilled(false);
		btnAnimais.setBackground(Color.GRAY);
		btnAnimais.setBounds(10, 104, 135,101);
		btnAnimais.setIcon(iconAnimal);
		frmPrincipal.getContentPane().add(btnAnimais);
		
		JLabel lblAnimais = new JLabel("Animais");
		lblAnimais.setForeground(Color.BLACK);
		lblAnimais.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAnimais.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnimais.setBounds(10, 206, 120, 14);
		frmPrincipal.getContentPane().add(lblAnimais);
		
		ImageIcon iconFuncionarios = new ImageIcon("src/img/t3.png");
		iconFuncionarios.setImage(iconFuncionarios.getImage().getScaledInstance(135, 101 ,100));
		JButton btnFuncionarios = new JButton("");
		btnFuncionarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFuncionarios.setBorderPainted(false);
		btnFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmPrincipal.setVisible(false);
				
			}
		});
		btnFuncionarios.setContentAreaFilled(false);
		btnFuncionarios.setBounds(245, 104, 135,101);
		btnFuncionarios.setIcon(iconFuncionarios);
		frmPrincipal.getContentPane().add(btnFuncionarios);
		
		JLabel lblFuncionarios = new JLabel("Funcionarios");
		lblFuncionarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuncionarios.setForeground(Color.BLACK);
		lblFuncionarios.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFuncionarios.setBounds(255, 206, 120, 14);
		frmPrincipal.getContentPane().add(lblFuncionarios);
		//aa
		ImageIcon iconCompras = new ImageIcon("src/img/compra12.png");
		iconCompras.setImage(iconCompras.getImage().getScaledInstance(135,101 ,100));
		JButton btnCompras = new JButton("");
		btnCompras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCompras.setBorderPainted(false);
		btnCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frmPrincipal.setVisible(false);
			}
		});
		btnCompras.setContentAreaFilled(false);
		btnCompras.setBounds(470, 104, 135,101);
		btnCompras.setIcon(iconCompras);
		frmPrincipal.getContentPane().add(btnCompras);
		
		JLabel lblCompras = new JLabel("Compras");
		lblCompras.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompras.setForeground(Color.BLACK);
		lblCompras.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCompras.setBounds(470, 207, 120, 14);
		frmPrincipal.getContentPane().add(lblCompras);
		
		ImageIcon iconVendas = new ImageIcon("src/img/t1.png");
		iconVendas.setImage(iconVendas.getImage().getScaledInstance(135,101 ,100));
		JButton btnVendas = new JButton("");
		btnVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVendas.setBorderPainted(false);
		btnVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmPrincipal.setVisible(false);
			}
		});
		btnVendas.setContentAreaFilled(false);
		btnVendas.setBounds(700, 104, 135,101);
		btnVendas.setIcon(iconVendas);
		frmPrincipal.getContentPane().add(btnVendas);
		
		JLabel lblVendas = new JLabel("Vendas");
		lblVendas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVendas.setForeground(Color.BLACK);
		lblVendas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVendas.setBounds(700, 207, 120, 14);
		frmPrincipal.getContentPane().add(lblVendas);
		
		ImageIcon iconUsuario = new ImageIcon("src/img/usu1.png");
		iconUsuario.setImage(iconUsuario.getImage().getScaledInstance(135,101 ,100));
		JButton btnUsuario = new JButton("");
		btnUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuario.setBorderPainted(false);
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Usuario.main(null);
				//frmPrincipal.setVisible(false);
			}
		});
		btnUsuario.setContentAreaFilled(false);
		btnUsuario.setBounds(920, 104, 135,101);
		btnUsuario.setIcon(iconUsuario);
		frmPrincipal.getContentPane().add(btnUsuario);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(920, 207, 120, 14);
		frmPrincipal.getContentPane().add(lblUsuario);
		
		JPanel panelSite = new JPanel();
		panelSite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					URI link = new URI("www.youtube.com");//abrir navegador
					Desktop.getDesktop().browse(link);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panelSite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelSite.setOpaque(false);
		panelSite.setBounds(10, 438, 186, 40);
		frmPrincipal.getContentPane().add(panelSite);
		panelSite.setLayout(new BorderLayout(0, 0));
		
		ImageIcon iconSite = new ImageIcon("src/img/WWW-Icon.png");
		iconSite.setImage(iconSite.getImage().getScaledInstance(40, 40 ,100));
		JLabel lblImgSite = new JLabel("");
		panelSite.add(lblImgSite, BorderLayout.WEST);
		lblImgSite.setIcon(iconSite);
		
		JLabel lblSite = new JLabel("  www.fodase.com");
		panelSite.add(lblSite, BorderLayout.CENTER);
		
		JPanel panelPdf = new JPanel();
		panelPdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					URI link = new URI("www.youtube.com");//abrir navegador
					Desktop.getDesktop().browse(link);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelPdf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelPdf.setOpaque(false);
		panelPdf.setBounds(10, 489, 186, 40);
		frmPrincipal.getContentPane().add(panelPdf);
		panelPdf.setLayout(new BorderLayout(0, 0));
		
		ImageIcon iconPdf = new ImageIcon("src/img/pdf.png");
		iconPdf.setImage(iconPdf.getImage().getScaledInstance(40, 40, 100));
		JLabel lblImgPdf = new JLabel("");
		panelPdf.add(lblImgPdf, BorderLayout.WEST);
		lblImgPdf.setIcon(iconPdf);
		
		JLabel lblManual = new JLabel("  Manual");
		panelPdf.add(lblManual, BorderLayout.CENTER);
		
		JLabel lblVersao = new JLabel("Versao 2.0");
		lblVersao.setForeground(Color.BLACK);
		lblVersao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVersao.setBounds(984, 645, 80, 14);
		frmPrincipal.getContentPane().add(lblVersao);
		
		JLabel foto = new JLabel("");
		foto.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		foto.setIcon(new ImageIcon(Principal.class.getResource("/img/gradiente_Branco.jpg")));
		foto.setFont(new Font("Tahoma", Font.BOLD, 11));
		foto.setForeground(Color.WHITE);
		foto.setBorder(null);
		foto.setBounds(0, 0, 1074, 671);
		frmPrincipal.getContentPane().add(foto);
		
		menu();
	}
	
	public void menu() {	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(128, 128, 128), new Color(128, 128, 128), new Color(128, 128, 128), new Color(105, 105, 105)));
		menuBar.setForeground(Color.GREEN);
		menuBar.setBackground(Color.DARK_GRAY);
		frmPrincipal.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("");
		mnInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnInicio.setOpaque(true);
		mnInicio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnInicio.setIcon(new ImageIcon(Principal.class.getResource("/img/Icone_Inicio.png")));
		mnInicio.setForeground(Color.WHITE);
		mnInicio.setBackground(Color.DARK_GRAY);
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				//frmPrincipal.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("");
		mnNewMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.setIcon(new ImageIcon(Principal.class.getResource("/img/Icone_GEstao.png")));
		mnNewMenu.setOpaque(true);
		mnNewMenu.setFocusPainted(true);
		mnNewMenu.setBorder(new CompoundBorder());
		mnNewMenu.setBackground(Color.DARK_GRAY);
		mnNewMenu.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Animais");
		mntmCadastrarAnimais.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmCadastrarAnimais.setBorder(new LineBorder(new Color(34, 139, 34)));
		mntmCadastrarAnimais.setOpaque(true);
		mntmCadastrarAnimais.setForeground(new Color(0, 0, 0));
		mntmCadastrarAnimais.setBackground(SystemColor.menu);
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmPrincipal.dispose();
			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Funcionarios");
		mntmCadastrarFuncionarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.add(mntmCadastrarFuncionarios);
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmPrincipal.dispose();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("");
		mnNewMenu_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.setIcon(new ImageIcon(Principal.class.getResource("/img/Icone_Financeiro.png")));
		mnNewMenu_1.setForeground(Color.WHITE);
		mnNewMenu_1.setBackground(Color.DARK_GRAY);
		mnNewMenu_1.setOpaque(true);
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCompra = new JMenuItem("Compra");
		mntmCompra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmCompra.setEnabled(true);
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frmPrincipal.dispose();
			}
		});
		mnNewMenu_1.add(mntmCompra);
		
		JMenuItem mntmNovaVenda = new JMenuItem("Venda");
		mntmNovaVenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.add(mntmNovaVenda);
		
		JMenuItem mntmTotal = new JMenuItem("Total");
		mntmTotal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.add(mntmTotal);
		mntmTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Total.main(null);
				frmPrincipal.dispose();
			}
		});
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmPrincipal.dispose();
			}
		});
		
		JMenu mnOpes = new JMenu("");
		mnOpes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOpes.setIcon(new ImageIcon(Principal.class.getResource("/img/Icone_OPCAO.png")));
		mnOpes.setForeground(Color.WHITE);
		mnOpes.setBackground(Color.DARK_GRAY);
		mnOpes.setOpaque(true);
		menuBar.add(mnOpes);
		
		JMenuItem mntmNovaFazenda = new JMenuItem("Fazenda");
		mntmNovaFazenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmNovaFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaFazenda.main(null);
				frmPrincipal.dispose();
			}
		});
		mnOpes.add(mntmNovaFazenda);
		
		JMenuItem mntmSada = new JMenuItem("Sair");
		mntmSada.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmMudarFazenda = new JMenuItem("Mudar Fazenda");
		mntmMudarFazenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmMudarFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pergunta.main(null);
				Pergunta.contador = 1;
			}
		});
		mnOpes.add(mntmMudarFazenda);
		
		JMenuItem mntmDeslogar = new JMenuItem("Deslogar");
		mntmDeslogar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPrincipal.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		
		JMenuItem mntmEnviar = new JMenuItem("Enviar feedback");
		mntmEnviar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frmPrincipal.dispose();
				EnviarEmail.main(null);
			}
		});
		mnOpes.add(mntmEnviar);
		
		mnOpes.add(mntmSada);
		frmPrincipal.getContentPane().setLayout(null);
	}
}
