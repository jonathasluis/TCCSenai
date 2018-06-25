package outraJanelas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.LineBorder;

import DAO.Fazenda;
import JanelasAnimal.CadastrarAnimais;
import JanelasComtabil.NovaCompra;
import JanelasComtabil.NovaVenda;
import JanelasComtabil.Total;
import JanelasComtabil.VisualizarCompras;
import JanelasComtabil.VisualizarVendas;
import JanelasFuncionarios.CadastrarFuncionarios;
import JanelasFuncionarios.VisualizarFuncionarios;

public class Principal {

	public static JFrame frmPrincipal;
	public static JTextField tfFazenda;
	private JTextField tfTamanho;
	private JTextField tfEscritura;
	private JTextField tfDono;
	static JButton button;
	public static Fazenda fazenda = new Fazenda();
	private JEditorPane editorPane;
	private JLabel lblImg;
	private JPanel panel;

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
		frmPrincipal.setBounds(100, 100, 720, 450);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.setResizable(false);
		frmPrincipal.setLocationRelativeTo(null);
		
		Pergunta.main(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.setEnabled(false);
		mnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmPrincipal.setVisible(false);
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("Animais");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Cadastrar Animais");
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmPrincipal.setVisible(false);			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Visualizar Animais");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//VisualizarAnimais.main(null);
				frmPrincipal.setVisible(false);			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnFuncionarios = new JMenu("Funcionarios");
		menuBar.add(mnFuncionarios);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Cadastrar funcionarios");
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmPrincipal.setVisible(false);			}
		});
		mnFuncionarios.add(mntmCadastrarFuncionarios);
		
		JMenuItem mntmVisualizarFuncionarios = new JMenuItem("Visualizar funcionarios");
		mntmVisualizarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarFuncionarios.main(null);
				frmPrincipal.setVisible(false);			}
		});
		mnFuncionarios.add(mntmVisualizarFuncionarios);
		
		JMenu mnNewMenu_1 = new JMenu("Compra de Insumos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCompra = new JMenuItem("Nova Compra");
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frmPrincipal.setVisible(false);			}
		});
		mnNewMenu_1.add(mntmCompra);
		
		JMenuItem mntmVisualizarCompra = new JMenuItem("Visualizar Compras");
		mntmVisualizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarCompras.main(null);
				frmPrincipal.setVisible(false);			}
		});
		mnNewMenu_1.add(mntmVisualizarCompra);
		
		JMenu mnNewMenu_2 = new JMenu("Vendas");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNovaVenda = new JMenuItem("Nova Venda");
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmPrincipal.setVisible(false);			}
		});
		mnNewMenu_2.add(mntmNovaVenda);
		
		JMenuItem mntmVisualizarVendas = new JMenuItem("Visualizar Vendas");
		mntmVisualizarVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarVendas.main(null);
				frmPrincipal.setVisible(false);			}
		});
		mnNewMenu_2.add(mntmVisualizarVendas);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
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
		menuBar.add(mnOpes);
		
		JMenuItem mntmNovaFazenda = new JMenuItem("Nova fazenda");
		mntmNovaFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaFazenda.main(null);
				frmPrincipal.setVisible(false);			}
		});
		mnOpes.add(mntmNovaFazenda);
		
		JMenuItem mntmVisualizarFazenda = new JMenuItem("Visualizar Fazenda");
		mntmVisualizarFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarFazendas.main(null);
				frmPrincipal.setVisible(false);			}
		});
		mnOpes.add(mntmVisualizarFazenda);
		
		JMenuItem mntmSada = new JMenuItem("Sair");
		mntmSada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmDeslogar = new JMenuItem("Deslogar");
		mntmDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPrincipal.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		
		JMenuItem mntmMudarFazenda = new JMenuItem("Mudar Fazenda");
		mntmMudarFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pergunta.main(null);
			}
		});
		mnOpes.add(mntmMudarFazenda);
		mnOpes.add(mntmSada);
		frmPrincipal.getContentPane().setLayout(null);
		
		tfFazenda = new JTextField();
		tfFazenda.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tfFazenda.setEditable(false);
		tfFazenda.setBounds(10, 65, 379, 30);
		tfFazenda.setColumns(10);
		frmPrincipal.getContentPane().add(tfFazenda);
		
		tfTamanho = new JTextField();
		tfTamanho.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tfTamanho.setEditable(false);
		tfTamanho.setBounds(10, 128, 175, 30);
		frmPrincipal.getContentPane().add(tfTamanho);
		tfTamanho.setColumns(10);
		
		tfEscritura = new JTextField();
		tfEscritura.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tfEscritura.setEditable(false);
		tfEscritura.setColumns(10);
		tfEscritura.setBounds(214, 128, 175, 30);
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
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(399, 11, 305, 359);
		frmPrincipal.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		lblImg = new JLabel("");
		panel.add(lblImg, "name_3365331609840");
		
		JLabel label = new JLabel("Nome:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(10, 51, 378, 14);
		frmPrincipal.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Tamanho:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(10, 114, 175, 14);
		frmPrincipal.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Escritura:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(214, 114, 175, 14);
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
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pega();
				abrirImagem(fazenda.getImg());
			}
		});
		button.setVisible(false);
		button.setBounds(10, 377, 11, 23);
		frmPrincipal.getContentPane().add(button);
	}
	
	void pega() {
		tfFazenda.setText(fazenda.getNome());
		tfTamanho.setText(fazenda.getTamanho());
		tfDono.setText(fazenda.getProprietario());
		tfEscritura.setText(fazenda.getEscritura());
		editorPane.setText(fazenda.getDescricao());
		
	}
	
public void abrirImagem(Object source) {//abra a imagem
		
		if(source instanceof File) {
			ImageIcon icon = new ImageIcon(NovaFazenda.img.getAbsolutePath());
			icon.setImage(icon.getImage().getScaledInstance(panel.getWidth(), panel.getHeight(), 100));
			lblImg.setIcon(icon);
		
		}else if(source instanceof byte[]) {
			ImageIcon icon = new ImageIcon(fazenda.getImg());
			icon.setImage(icon.getImage().getScaledInstance(panel.getWidth(), panel.getHeight(), 100));
			lblImg.setIcon(icon);
		}
	}
}
