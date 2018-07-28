package teste;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import DAO.Fazenda;
import Imagem.MetodosImagem;
import JanelasAnimal.CadastrarAnimais;
import JanelasComtabil.NovaCompra;
import JanelasComtabil.NovaVenda;
import JanelasComtabil.Total;
import JanelasFuncionarios.CadastrarFuncionarios;
import outraJanelas.Pergunta;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;


public class NovaPrincipalTeste {

	public static JFrame frmPrincipal;
	static JButton button;
	public static Fazenda fazenda = new Fazenda();
	private static MetodosImagem abrirImagem = new MetodosImagem();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					NovaPrincipalTeste window = new NovaPrincipalTeste();
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
	public NovaPrincipalTeste() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(NovaPrincipalTeste.class.getResource("/img/logo-pequena-sem-texto.png")));
		frmPrincipal.setTitle("Principal");
		frmPrincipal.setBounds(100, 100, 1080, 720);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.setResizable(false);
		frmPrincipal.setLocationRelativeTo(null);
		frmPrincipal.getContentPane().setLayout(null);
		
		Pergunta.main(null);
		Pergunta.contador = 0;		
		
		JLabel lblAnimais = new JLabel("Animais");
		lblAnimais.setForeground(Color.WHITE);
		lblAnimais.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAnimais.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnimais.setBounds(10, 206, 120, 14);
		frmPrincipal.getContentPane().add(lblAnimais);
		
		JLabel lblNomeDoSistema = new JLabel("Nome do sistema");
		lblNomeDoSistema.setForeground(Color.WHITE);
		lblNomeDoSistema.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNomeDoSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeDoSistema.setBounds(10, 11, 1054, 43);
		frmPrincipal.getContentPane().add(lblNomeDoSistema);
		
		ImageIcon iconAnimal = new ImageIcon("src/img/Vaca.png");
		iconAnimal.setImage(iconAnimal.getImage().getScaledInstance(116, 58 ,100));
		JButton btnAnimais = new JButton("");
		btnAnimais.setContentAreaFilled(false);
		btnAnimais.setBackground(Color.GRAY);
		btnAnimais.setBounds(10, 145, 120,60);
		btnAnimais.setIcon(iconAnimal);
		frmPrincipal.getContentPane().add(btnAnimais);
		
		JButton btnFuncionarios = new JButton("Funcionarios");
		btnFuncionarios.setContentAreaFilled(false);
		btnFuncionarios.setBounds(245, 145, 120,60);
		frmPrincipal.getContentPane().add(btnFuncionarios);
		
		JButton btnCompras = new JButton("Compras");
		btnCompras.setContentAreaFilled(false);
		btnCompras.setBounds(470, 145, 120,60);
		frmPrincipal.getContentPane().add(btnCompras);
		
		JButton btnVendas = new JButton("Vendas");
		btnVendas.setContentAreaFilled(false);
		btnVendas.setBounds(700, 145, 120,60);
		frmPrincipal.getContentPane().add(btnVendas);
		
		JButton btnUsuario = new JButton("Usuario");
		btnUsuario.setContentAreaFilled(false);
		btnUsuario.setBounds(920, 145, 120,60);
		//btnUsuario.setIcon();
		frmPrincipal.getContentPane().add(btnUsuario);
		
		JLabel lblVersao = new JLabel("Versao 2.0");
		lblVersao.setForeground(Color.WHITE);
		lblVersao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVersao.setBounds(984, 645, 80, 14);
		frmPrincipal.getContentPane().add(lblVersao);
		
		ImageIcon iconSite = new ImageIcon("src/img/WWW-Icon.png");
		iconSite.setImage(iconSite.getImage().getScaledInstance(40, 40 ,100));
		
		JPanel panelSite = new JPanel();
		panelSite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelSite.setOpaque(false);
		panelSite.setBounds(10, 438, 186, 40);
		frmPrincipal.getContentPane().add(panelSite);
		panelSite.setLayout(new BorderLayout(0, 0));
		
		JLabel lblImgSite = new JLabel("");
		panelSite.add(lblImgSite, BorderLayout.WEST);
		lblImgSite.setIcon(iconSite);
		
		JLabel lblSite = new JLabel("  www.fodase.com");
		lblSite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					URI link = new URI("www.youtube.com");
					Desktop.getDesktop().browse(link);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panelSite.add(lblSite, BorderLayout.CENTER);
		
		JPanel panelPdf = new JPanel();
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
		
		ImageIcon icon = new ImageIcon("src/img/Principal3.jpg");
		icon.setImage(icon.getImage().getScaledInstance(1074, 671, 100));
		JLabel foto = new JLabel("");
		foto.setFont(new Font("Tahoma", Font.BOLD, 11));
		foto.setForeground(Color.WHITE);
		foto.setBorder(null);
		foto.setBounds(0, 0, 1074, 671);
		frmPrincipal.getContentPane().add(foto);
		foto.setIcon(icon);
		
		
		
		menu();
		
	}
	
	public void menu() {	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(Color.DARK_GRAY));
		menuBar.setForeground(Color.GREEN);
		menuBar.setBackground(new Color(128, 128, 128));
		frmPrincipal.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.setOpaque(true);
		mnInicio.setEnabled(false);
		mnInicio.setForeground(new Color(230, 230, 250));
		mnInicio.setBackground(new Color(128, 128, 128));
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				NovaPrincipalTeste.frmPrincipal.setVisible(true);
				//frmPrincipal.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("Gest\u00E3o");
		mnNewMenu.setOpaque(true);
		mnNewMenu.setFocusPainted(true);
		mnNewMenu.setBorder(new CompoundBorder());
		mnNewMenu.setBackground(new Color(128, 128, 128));
		mnNewMenu.setForeground(new Color(230, 230, 250));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Cadastrar Animais");
		mntmCadastrarAnimais.setBorder(new LineBorder(new Color(34, 139, 34)));
		mntmCadastrarAnimais.setOpaque(true);
		mntmCadastrarAnimais.setForeground(new Color(230, 230, 250));
		mntmCadastrarAnimais.setBackground(new Color(34, 139, 34));
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmPrincipal.dispose();
			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Cadastrar funcionarios");
		mnNewMenu.add(mntmCadastrarFuncionarios);
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmPrincipal.dispose();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("Financeiro");
		mnNewMenu_1.setForeground(new Color(230, 230, 250));
		mnNewMenu_1.setBackground(new Color(128, 128, 128));
		mnNewMenu_1.setOpaque(true);
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCompra = new JMenuItem("Nova Compra");
		mntmCompra.setEnabled(true);
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frmPrincipal.dispose();
			}
		});
		mnNewMenu_1.add(mntmCompra);
		
		JMenuItem mntmNovaVenda = new JMenuItem("Nova Venda");
		mnNewMenu_1.add(mntmNovaVenda);
		
		JMenuItem mntmTotal = new JMenuItem("Total");
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
		
		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		mnOpes.setIcon(new ImageIcon("G:\\admin_option_file.png"));
		mnOpes.setForeground(new Color(230, 230, 250));
		mnOpes.setBackground(new Color(128, 128, 128));
		mnOpes.setOpaque(true);
		menuBar.add(mnOpes);
		
		JMenuItem mntmNovaFazenda = new JMenuItem("Nova fazenda");
		mntmNovaFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaPrincipalTeste.main(null);
				frmPrincipal.dispose();
			}
		});
		mnOpes.add(mntmNovaFazenda);
		
		JMenuItem mntmSada = new JMenuItem("Sair");
		mntmSada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmMudarFazenda = new JMenuItem("Mudar Fazenda");
		mntmMudarFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*Pergunta.main(null);
				Pergunta.contador = 1;*/
			}
		});
		mnOpes.add(mntmMudarFazenda);
		
		JMenuItem mntmDeslogar = new JMenuItem("Deslogar");
		mntmDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPrincipal.dispose();
				//Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		
		JMenuItem mntmEnviar = new JMenuItem("Enviar feedback");
		mntmEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frmPrincipal.dispose();
				//EnviarEmail.main(null);
			}
		});
		mnOpes.add(mntmEnviar);
		
		mnOpes.add(mntmSada);
		frmPrincipal.getContentPane().setLayout(null);
	}
}
