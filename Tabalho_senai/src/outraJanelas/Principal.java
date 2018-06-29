package outraJanelas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
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


public class Principal {

	public static JFrame frmPrincipal;
	private static JTextField tfTamanho;
	private static JTextField tfEscritura;
	private static JTextField tfDono;
	static JButton button;
	public static Fazenda fazenda = new Fazenda();
	private static JEditorPane editorPane;
	private static JLabel lblImg = null;
	private static JPanel panel;
	private static JLabel lblNom;
	private static MetodosImagem abrirImagem = new MetodosImagem();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		frmPrincipal.setTitle("Principal");
		frmPrincipal.setBounds(100, 100, 1080, 720);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.setResizable(false);
		frmPrincipal.setLocationRelativeTo(null);
		
		Pergunta.main(null);
		
		
		frmPrincipal.getContentPane().setLayout(null);
		
		tfTamanho = new JTextField();
		tfTamanho.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tfTamanho.setEditable(false);
		tfTamanho.setBounds(27, 150, 175, 30);
		frmPrincipal.getContentPane().add(tfTamanho);
		tfTamanho.setColumns(10);
		
		tfEscritura = new JTextField();
		tfEscritura.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tfEscritura.setEditable(false);
		tfEscritura.setColumns(10);
		tfEscritura.setBounds(486, 128, 175, 30);
		frmPrincipal.getContentPane().add(tfEscritura);
		
		tfDono = new JTextField();
		tfDono.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tfDono.setEditable(false);
		tfDono.setColumns(10);
		tfDono.setBounds(10, 191, 379, 30);
		frmPrincipal.getContentPane().add(tfDono);
		
		editorPane = new JEditorPane();
		editorPane.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		editorPane.setEditable(false);
		editorPane.setBounds(10, 254, 379, 116);
		frmPrincipal.getContentPane().add(editorPane);
		
		panel = new JPanel();
		panel.setBorder(null);
		panel.setOpaque(false);
		panel.setBounds(388, 381, 303, 276);
		frmPrincipal.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		lblImg = new JLabel("");
		lblImg.setBorder(null);
		panel.add(lblImg, "name_7484632455859");
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setIcon(new ImageIcon(CadastrarAnimais.class.getResource("/img/logo-pequena-sem-texto.png")));
		
		JLabel label_1 = new JLabel("Tamanho:");
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Arial", Font.BOLD, 14));
		label_1.setBounds(10, 114, 175, 14);
		frmPrincipal.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Escritura:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(582, 177, 175, 14);
		frmPrincipal.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Propriet\u00E1rio:");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(10, 177, 378, 14);
		frmPrincipal.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Descri\u00E7\u00E3o:");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_4.setBounds(10, 239, 378, 14);
		frmPrincipal.getContentPane().add(label_4);
		
		
		
		lblNom = new JLabel("");
		lblNom.setForeground(Color.WHITE);
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		lblNom.setFont(new Font("Arial", Font.BOLD, 40));
		lblNom.setBounds(10, 32, 1054, 54);
		frmPrincipal.getContentPane().add(lblNom);
		
		
		ImageIcon icon = new ImageIcon("src/img/Principal.jpg");
		icon.setImage(icon.getImage().getScaledInstance(1074, 671, 1));
		JLabel foto = new JLabel("");
		foto.setBounds(0, 0, 1074, 671);
		frmPrincipal.getContentPane().add(foto);
		foto.setIcon(icon);
		
		JLabel label = new JLabel("");
		label.setBounds(190, 114, 46, 14);
		frmPrincipal.getContentPane().add(label);
		
		
		
	
		pega();
		menu();
	}
	
	public static void pega() {
		lblNom.setText(fazenda.getNome());
		tfTamanho.setText(fazenda.getTamanho());
		tfDono.setText(fazenda.getProprietario());
		tfEscritura.setText(fazenda.getEscritura());
		editorPane.setText(fazenda.getDescricao());
		abrirImagem.abrirImagem(fazenda.getImg(), null, panel, lblImg,fazenda.getImg());
	}
	public void menu() {

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(230, 230, 250));
		menuBar.setBackground(Color.DARK_GRAY);
		frmPrincipal.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.setOpaque(true);
		mnInicio.setEnabled(false);
		mnInicio.setForeground(new Color(230, 230, 250));
		mnInicio.setBackground(Color.DARK_GRAY);
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmPrincipal.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("Animais");
		mnNewMenu.setOpaque(true);
		mnNewMenu.setFocusPainted(true);
		mnNewMenu.setBorder(new CompoundBorder());
		mnNewMenu.setBackground(Color.DARK_GRAY);
		mnNewMenu.setForeground(new Color(230, 230, 250));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Cadastrar Animais");
		mntmCadastrarAnimais.setForeground(new Color(230, 230, 250));
		mntmCadastrarAnimais.setBackground(new Color(30, 144, 255));
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmPrincipal.dispose();
			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Visualizar Animais");
		mntmNewMenuItem.setBorder(null);
		mntmNewMenuItem.setBackground(new Color(30, 144, 255));
		mntmNewMenuItem.setForeground(new Color(230, 230, 250));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmPrincipal.dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnFuncionarios = new JMenu("Funcionarios");
		mnFuncionarios.setForeground(new Color(230, 230, 250));
		mnFuncionarios.setBackground(Color.DARK_GRAY);
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
		mnNewMenu_1.setBackground(Color.DARK_GRAY);
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
		mnNewMenu_2.setBackground(Color.DARK_GRAY);
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
		mnRelatrio.setBackground(Color.DARK_GRAY);
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
		mnOpes.setBackground(Color.DARK_GRAY);
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
			}
		});
		mnOpes.add(mntmMudarFazenda);
		mnOpes.add(mntmSada);
		frmPrincipal.getContentPane().setLayout(null);
	}
}
