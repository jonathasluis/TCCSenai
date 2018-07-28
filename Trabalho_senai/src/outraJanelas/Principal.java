package outraJanelas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import DAO.Fazenda;
import Imagem.MetodosImagem;
import JanelasAnimal.CadastrarAnimais;
import JanelasComtabil.NovaCompra;
import JanelasComtabil.NovaVenda;
import JanelasComtabil.Total;
import JanelasFuncionarios.CadastrarFuncionarios;


public class Principal {

	public static JFrame frmPrincipal;
	static JButton button;
	public static Fazenda fazenda = new Fazenda();
	private static JEditorPane editorPane;
	private static JLabel lblImg = null;
	private static JPanel panel;
	private static JLabel lblNom;
	private static MetodosImagem abrirImagem = new MetodosImagem();
	private static JLabel prop;
	private static JLabel tamanho;
	private static JLabel escri;

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
		Pergunta.main(null);
		Pergunta.contador = 0;		
		
		frmPrincipal.getContentPane().setLayout(null);
		
		editorPane = new JEditorPane();
		editorPane.setOpaque(false);
		editorPane.setForeground(Color.WHITE);
		editorPane.setFont(new Font("Arial", Font.BOLD, 17));
		editorPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		editorPane.setEditable(false);
		editorPane.setBounds(9, 358, 521, 175);
		frmPrincipal.getContentPane().add(editorPane);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		panel.setBounds(745, 115, 303, 276);
		frmPrincipal.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		lblImg = new JLabel("");
		lblImg.setBackground(SystemColor.textHighlightText);
		panel.add(lblImg, "name_4493982702195");
		lblImg.setBorder(null);
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setIcon(new ImageIcon(CadastrarAnimais.class.getResource("/img/logo-pequena-sem-texto.png")));
		
		JLabel label_1 = new JLabel("Tamanho:");
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Arial", Font.BOLD, 22));
		label_1.setBounds(9, 228, 117, 26);
		frmPrincipal.getContentPane().add(label_1);
		
		JLabel label_3 = new JLabel("Propriet\u00E1rio:");
		label_3.setForeground(Color.WHITE);
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Arial", Font.BOLD, 22));
		label_3.setBounds(9, 188, 129, 29);
		frmPrincipal.getContentPane().add(label_3);
		
		
		
		lblNom = new JLabel("");
		lblNom.setForeground(Color.WHITE);
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		lblNom.setFont(new Font("Arial", Font.BOLD, 40));
		lblNom.setBounds(10, 32, 1054, 54);
		frmPrincipal.getContentPane().add(lblNom);
		
		
		ImageIcon icon = new ImageIcon("src/img/Principal3.jpg");
		icon.setImage(icon.getImage().getScaledInstance(1074, 671, 1));
		
		prop = new JLabel("");
		prop.setForeground(Color.WHITE);
		prop.setFont(new Font("Arial", Font.BOLD, 22));
		prop.setBounds(143, 188, 420, 29);
		frmPrincipal.getContentPane().add(prop);
		
		tamanho = new JLabel("");
		tamanho.setForeground(Color.WHITE);
		tamanho.setFont(new Font("Arial", Font.BOLD, 22));
		tamanho.setBounds(136, 228, 427, 26);
		frmPrincipal.getContentPane().add(tamanho);
		
		JLabel lblEscritura = new JLabel("Escritura:");
		lblEscritura.setHorizontalAlignment(SwingConstants.LEFT);
		lblEscritura.setForeground(Color.WHITE);
		lblEscritura.setFont(new Font("Arial", Font.BOLD, 22));
		lblEscritura.setBounds(9, 271, 117, 26);
		frmPrincipal.getContentPane().add(lblEscritura);
		
		escri = new JLabel("");
		escri.setForeground(Color.WHITE);
		escri.setFont(new Font("Arial", Font.BOLD, 22));
		escri.setBounds(143, 271, 427, 26);
		frmPrincipal.getContentPane().add(escri);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescrio.setForeground(Color.WHITE);
		lblDescrio.setFont(new Font("Arial", Font.BOLD, 22));
		lblDescrio.setBounds(9, 308, 117, 26);
		frmPrincipal.getContentPane().add(lblDescrio);
		JLabel foto = new JLabel("");
		foto.setBounds(0, 0, 1074, 671);
		frmPrincipal.getContentPane().add(foto);
		foto.setIcon(icon);
		
		
		
	
		//pega();
		menu();
	}
	
	public static void pega() {
		lblNom.setText(fazenda.getNome());
		prop.setText(fazenda.getProprietario());
		escri.setText(fazenda.getEscritura());
		tamanho.setText(fazenda.getTamanho());
		editorPane.setText(fazenda.getDescricao());
		abrirImagem.abrirImagem(fazenda.getImg(), null, panel, lblImg,fazenda.getImg());
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
				Principal.frmPrincipal.setVisible(true);
				//frmPrincipal.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("Animais");
		mnNewMenu.setOpaque(true);
		mnNewMenu.setFocusPainted(true);
		mnNewMenu.setBorder(new CompoundBorder());
		mnNewMenu.setBackground(new Color(47, 79, 79));
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
		
		JMenu mnFuncionarios = new JMenu("Funcionarios");
		mnFuncionarios.setForeground(new Color(230, 230, 250));
		mnFuncionarios.setBackground(new Color(128, 128, 128));
		mnFuncionarios.setOpaque(true);
		menuBar.add(mnFuncionarios);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Cadastrar funcionarios");
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmPrincipal.dispose();
			}
		});
		mnFuncionarios.add(mntmCadastrarFuncionarios);
		
		JMenu mnNewMenu_1 = new JMenu("Compra de Insumos");
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
		
		JMenu mnNewMenu_2 = new JMenu("Vendas");
		mnNewMenu_2.setForeground(new Color(230, 230, 250));
		mnNewMenu_2.setBackground(new Color(128, 128, 128));
		mnNewMenu_2.setOpaque(true);
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNovaVenda = new JMenuItem("Nova Venda");
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmPrincipal.dispose();
			}
		});
		mnNewMenu_2.add(mntmNovaVenda);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
		mnRelatrio.setForeground(new Color(230, 230, 250));
		mnRelatrio.setBackground(new Color(128, 128, 128));
		mnRelatrio.setOpaque(true);
		menuBar.add(mnRelatrio);
		
		JMenuItem mntmTotal = new JMenuItem("Total");
		mntmTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Total.main(null);
				frmPrincipal.dispose();
			}
		});
		mnRelatrio.add(mntmTotal);
		
		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		mnOpes.setForeground(new Color(230, 230, 250));
		mnOpes.setBackground(new Color(47, 79, 79));
		mnOpes.setOpaque(true);
		menuBar.add(mnOpes);
		
		JMenuItem mntmNovaFazenda = new JMenuItem("Nova fazenda");
		mntmNovaFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaFazenda.main(null);
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
				Pergunta.main(null);
				Pergunta.contador = 1;
			}
		});
		mnOpes.add(mntmMudarFazenda);
		
		JMenuItem mntmDeslogar = new JMenuItem("Deslogar");
		mntmDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPrincipal.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		
		JMenuItem mntmEnviar = new JMenuItem("Enviar feedback");
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
