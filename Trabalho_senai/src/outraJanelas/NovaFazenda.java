package outraJanelas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

//import Imagem.ImagemFazenda;
import JanelasAnimal.CadastrarAnimais;
//import JanelasAnimal.VisualizarAnimais;
import JanelasComtabil.NovaCompra;
import JanelasComtabil.NovaVenda;
import JanelasComtabil.Total;
import JanelasComtabil.VisualizarCompras;
import JanelasComtabil.VisualizarVendas;
import JanelasFuncionarios.CadastrarFuncionarios;
import JanelasFuncionarios.VisualizarFuncionarios;
import crud.CrudFazenda;
import java.awt.Toolkit;

public class NovaFazenda {

	private JFrame frmNovaFazenda;
	private JTextField tfNome;
	private JTextField tfTamanho;
	private JTextField tfEscritura;
	private JTextField textField;
	public static JLabel lblImg;
	public static JPanel panel;
	public static File img;
//	ImagemFazenda i = new ImagemFazenda();
	private JEditorPane dtrpnDes;
	private JButton btnLimpar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaFazenda window = new NovaFazenda();
					window.frmNovaFazenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NovaFazenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNovaFazenda = new JFrame();
		frmNovaFazenda.setIconImage(Toolkit.getDefaultToolkit().getImage(NovaFazenda.class.getResource("/img/logo-pequena-sem-texto.png")));
		frmNovaFazenda.setTitle("Nova Fazenda");
		frmNovaFazenda.setBounds(100, 100, 720, 450);
		frmNovaFazenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNovaFazenda.setLocationRelativeTo(null);
		frmNovaFazenda.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		frmNovaFazenda.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmNovaFazenda.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("Animais");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Cadastrar Animais");
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmNovaFazenda.dispose();
			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Visualizar Animais");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				VisualizarAnimais.main(null);
				frmNovaFazenda.dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnFuncionarios = new JMenu("Funcionarios");
		menuBar.add(mnFuncionarios);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Cadastrar funcionarios");
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmNovaFazenda.dispose();
			}
		});
		mnFuncionarios.add(mntmCadastrarFuncionarios);
		
		JMenuItem mntmVisualizarFuncionarios = new JMenuItem("Visualizar funcionarios");
		mntmVisualizarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarFuncionarios.main(null);
				frmNovaFazenda.dispose();
			}
		});
		mnFuncionarios.add(mntmVisualizarFuncionarios);
		
		JMenu mnNewMenu_1 = new JMenu("Compra de Insumos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCompra = new JMenuItem("Nova Compra");
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frmNovaFazenda.dispose();
			}
		});
		mnNewMenu_1.add(mntmCompra);
		
		JMenuItem mntmVisualizarCompra = new JMenuItem("Visualizar Compras");
		mntmVisualizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarCompras.main(null);
				frmNovaFazenda.dispose();
			}
		});
		mnNewMenu_1.add(mntmVisualizarCompra);
		
		JMenu mnNewMenu_2 = new JMenu("Vendas");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNovaVenda = new JMenuItem("Nova Venda");
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmNovaFazenda.dispose();
			}
		});
		mnNewMenu_2.add(mntmNovaVenda);
		
		JMenuItem mntmVisualizarVendas = new JMenuItem("Visualizar Vendas");
		mntmVisualizarVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarVendas.main(null);
				frmNovaFazenda.dispose();
			}
		});
		mnNewMenu_2.add(mntmVisualizarVendas);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatrio);
		
		JMenuItem mntmTotal = new JMenuItem("Total");
		mntmTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Total.main(null);
				frmNovaFazenda.dispose();
			}
		});
		mnRelatrio.add(mntmTotal);
		
		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnOpes);
		
		JMenuItem mntmNovaFazenda = new JMenuItem("Nova fazenda");
		mntmNovaFazenda.setEnabled(false);
		mntmNovaFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaFazenda.main(null);
				frmNovaFazenda.dispose();
			}
		});
		mnOpes.add(mntmNovaFazenda);
		
		JMenuItem mntmVisualizarFazenda = new JMenuItem("Visualizar Fazenda");
		mntmVisualizarFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarFazendas.main(null);
				frmNovaFazenda.dispose();
			}
		});
		mnOpes.add(mntmVisualizarFazenda);
		
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
		frmNovaFazenda.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(398, 11, 306, 288);
		frmNovaFazenda.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		lblImg = new JLabel("");
		panel.add(lblImg, "name_20449716211995");
		
		JPanel nada = new JPanel();
		nada.setBounds(398, 299, 306, 33);
		frmNovaFazenda.getContentPane().add(nada);
		
		JButton btnFoto = new JButton("Foto");
		btnFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//img = i.selecionaImg();
				//i.abrirImagem(img);
			}
		});
		nada.add(btnFoto);
		
		JButton btnSalvar = new JButton("Salvar");
		/*btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!tfNome.getText().trim().equals("")) {
					if(!tfTamanho.getText().trim().equals("")) {
						if(! textField.getText().trim().equals("")) {
							new CrudFazenda().addfazenda(tfNome.getText(), tfTamanho.getText(), textField.getText(), 
								tfEscritura.getText(),dtrpnDes.getText() , i.getImagem());
							btnLimpar.doClick();
						}else {
							textField.requestFocus();
							JOptionPane.showMessageDialog(null, "Insira o nome do propietario!");
						}
					}else {
						tfTamanho.requestFocus();
						JOptionPane.showMessageDialog(null, "Insira o tamanho!");
					}
				}else {
					tfNome.requestFocus();
					JOptionPane.showMessageDialog(null, "Insira um nome!");
				}
			}
		});*/
		btnSalvar.setBounds(615, 366, 89, 23);
		frmNovaFazenda.getContentPane().add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfEscritura.setText(null);
				tfNome.setText(null);
				tfTamanho.setText(null);
				textField.setText(null);
				dtrpnDes.setText(null);
				
			}
		});
		btnLimpar.setBounds(516, 366, 89, 23);
		frmNovaFazenda.getContentPane().add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(417, 366, 89, 23);
		frmNovaFazenda.getContentPane().add(btnCancelar);
		
		JLabel lblNonaFazenda = new JLabel("Nova Fazenda");
		lblNonaFazenda.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNonaFazenda.setHorizontalAlignment(SwingConstants.CENTER);
		lblNonaFazenda.setBounds(10, 7, 378, 23);
		frmNovaFazenda.getContentPane().add(lblNonaFazenda);
		
		tfNome = new JTextField();
		tfNome.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					tfTamanho.requestFocus();
				}
			}
		});
		tfNome.setBounds(10, 65, 378, 30);
		frmNovaFazenda.getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setBounds(10, 51, 378, 14);
		frmNovaFazenda.getContentPane().add(lblNome);
		
		tfTamanho = new JTextField();
		tfTamanho.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tfTamanho.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					tfEscritura.requestFocus();
				}
			}
		});
		tfTamanho.setBounds(10, 128, 175, 30);
		frmNovaFazenda.getContentPane().add(tfTamanho);
		tfTamanho.setColumns(10);
		
		JLabel lblTamanho = new JLabel("Tamanho:");
		lblTamanho.setHorizontalAlignment(SwingConstants.CENTER);
		lblTamanho.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTamanho.setBounds(10, 114, 175, 14);
		frmNovaFazenda.getContentPane().add(lblTamanho);
		
		tfEscritura = new JTextField();
		tfEscritura.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tfEscritura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField.requestFocus();
				}
			}
		});
		tfEscritura.setColumns(10);
		tfEscritura.setBounds(213, 128, 175, 30);
		frmNovaFazenda.getContentPane().add(tfEscritura);
		
		JLabel lblEscritura = new JLabel("Escritura:");
		lblEscritura.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscritura.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEscritura.setBounds(213, 114, 175, 14);
		frmNovaFazenda.getContentPane().add(lblEscritura);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					dtrpnDes.requestFocus();
				}
			}
		});
		textField.setColumns(10);
		textField.setBounds(10, 191, 378, 30);
		frmNovaFazenda.getContentPane().add(textField);
		
		JLabel lblPro = new JLabel("Propriet\u00E1rio:");
		lblPro.setHorizontalAlignment(SwingConstants.CENTER);
		lblPro.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPro.setBounds(10, 177, 378, 14);
		frmNovaFazenda.getContentPane().add(lblPro);
		
		dtrpnDes = new JEditorPane();
		dtrpnDes.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		dtrpnDes.setBounds(10, 254, 378, 78);
		frmNovaFazenda.getContentPane().add(dtrpnDes);
		
		JLabel lblDescrio_1 = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescrio_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescrio_1.setBounds(10, 239, 378, 14);
		frmNovaFazenda.getContentPane().add(lblDescrio_1);
	}
}
