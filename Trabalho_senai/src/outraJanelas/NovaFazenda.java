package outraJanelas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Imagem.MetodosImagem;
import JanelasAnimal.CadastrarAnimais;
import JanelasComtabil.NovaCompra;
import JanelasComtabil.NovaVenda;
import JanelasComtabil.Total;
import JanelasFuncionarios.CadastrarFuncionarios;

public class NovaFazenda {

	private JFrame frmNovaFazenda;
	private JTextField tfNome;
	private JTextField tfTamanho;
	private JTextField tfEscritura;
	private JTextField textField;
	public static JLabel lblImg;
	public static JPanel panel;
	public static File img;
	MetodosImagem mI = new MetodosImagem();
	private JEditorPane dtrpnDes;
	private JButton btnLimpar;
	private JTextField tfQtdAnimais;
	private JTextField textField_1;

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
		frmNovaFazenda.setBounds(100, 100, 1080,720);
		frmNovaFazenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNovaFazenda.setLocationRelativeTo(null);
		frmNovaFazenda.setResizable(false);
		
		
		frmNovaFazenda.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setToolTipText("2 cliques para selecionar a imagem");
		panel.setOpaque(false);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(774, 25, 290, 217);
		frmNovaFazenda.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		lblImg = new JLabel("");
		lblImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==2) {
					img = mI.selecionaImg();
					if(img != null) {
						lblImg.setHorizontalAlignment(SwingConstants.LEADING);
					}
					mI.abrirImagem(img, img, panel, lblImg,null);
				}
			}
		});
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setIcon(new ImageIcon(NovaFazenda.class.getResource("/img/logo-pequena-sem-texto.png")));
		panel.add(lblImg, "name_20449716211995");
		
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
		btnSalvar.setBounds(975, 636, 89, 23);
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
		btnLimpar.setBounds(876, 636, 89, 23);
		frmNovaFazenda.getContentPane().add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(777, 636, 89, 23);
		frmNovaFazenda.getContentPane().add(btnCancelar);
		
		JLabel lblNonaFazenda = new JLabel("Nova Fazenda");
		lblNonaFazenda.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNonaFazenda.setHorizontalAlignment(SwingConstants.CENTER);
		lblNonaFazenda.setBounds(10, 11, 1054, 25);
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
		tfNome.setBounds(80, 61, 200, 20);
		frmNovaFazenda.getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setBounds(10, 60, 60, 20);
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
		tfTamanho.setBounds(80, 92, 200, 20);
		frmNovaFazenda.getContentPane().add(tfTamanho);
		tfTamanho.setColumns(10);
		
		JLabel lblTamanho = new JLabel("Tamanho:");
		lblTamanho.setHorizontalAlignment(SwingConstants.CENTER);
		lblTamanho.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTamanho.setBounds(10, 91, 60, 20);
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
		tfEscritura.setBounds(500, 60, 200, 20);
		frmNovaFazenda.getContentPane().add(tfEscritura);
		
		JLabel lblEscritura = new JLabel("Escritura:");
		lblEscritura.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscritura.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEscritura.setBounds(420, 60, 60, 20);
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
		textField.setBounds(520, 92, 180, 20);
		frmNovaFazenda.getContentPane().add(textField);
		
		JLabel lblPro = new JLabel("Propriet\u00E1rio:");
		lblPro.setHorizontalAlignment(SwingConstants.CENTER);
		lblPro.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPro.setBounds(420, 91, 80, 20);
		frmNovaFazenda.getContentPane().add(lblPro);
		
		dtrpnDes = new JEditorPane();
		dtrpnDes.setOpaque(false);
		dtrpnDes.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		dtrpnDes.setBounds(80, 123, 200, 107);
		frmNovaFazenda.getContentPane().add(dtrpnDes);
		
		JLabel lblDescrio_1 = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescrio_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescrio_1.setBounds(10, 123, 70, 107);
		frmNovaFazenda.getContentPane().add(lblDescrio_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setInheritsPopupMenu(true);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 253, 1054, 373);
		frmNovaFazenda.getContentPane().add(scrollPane);
		
		JLabel lblQuantidadeDeAnimais = new JLabel("Quantidade de animais:");
		lblQuantidadeDeAnimais.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantidadeDeAnimais.setBounds(420, 122, 157, 20);
		frmNovaFazenda.getContentPane().add(lblQuantidadeDeAnimais);
		
		JLabel lblQuantidadeDeFuncionaris = new JLabel("Quantidade de funcionarios:");
		lblQuantidadeDeFuncionaris.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantidadeDeFuncionaris.setBounds(420, 153, 180, 20);
		frmNovaFazenda.getContentPane().add(lblQuantidadeDeFuncionaris);
		
		tfQtdAnimais = new JTextField();
		tfQtdAnimais.setText("0");
		tfQtdAnimais.setEditable(false);
		tfQtdAnimais.setBorder(null);
		tfQtdAnimais.setOpaque(false);
		tfQtdAnimais.setBounds(607, 123, 76, 20);
		frmNovaFazenda.getContentPane().add(tfQtdAnimais);
		tfQtdAnimais.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setOpaque(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBounds(607, 154, 76, 20);
		frmNovaFazenda.getContentPane().add(textField_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(NovaFazenda.class.getResource("/img/gradiente_Branco.jpg")));
		label.setBounds(0, 0, 1074, 670);
		frmNovaFazenda.getContentPane().add(label);
		
		menu();
	}
	
	void menu(){
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(Color.DARK_GRAY));
		menuBar.setBackground(Color.GRAY);
		frmNovaFazenda.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.setForeground(new Color(230, 230, 250));
		mnInicio.setBackground(new Color(128, 128, 128));
		mnInicio.setIcon(new ImageIcon(NovaFazenda.class.getResource("/img/Home.png")));
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmNovaFazenda.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("Gest\u00E3o");
		mnNewMenu.setForeground(new Color(230, 230, 250));
		mnNewMenu.setBackground(new Color(128, 128, 128));
		mnNewMenu.setIcon(new ImageIcon(NovaFazenda.class.getResource("/img/gestao.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Animais");
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmNovaFazenda.dispose();
			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Funcionarios");
		mnNewMenu.add(mntmCadastrarFuncionarios);
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmNovaFazenda.dispose();
			}
		});

		JMenu mnNewMenu_1 = new JMenu("Financeiro");
		mnNewMenu_1.setForeground(new Color(230, 230, 250));
		mnNewMenu_1.setBackground(new Color(128, 128, 128));
		mnNewMenu_1.setIcon(new ImageIcon(NovaFazenda.class.getResource("/img/money.png")));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCompra = new JMenuItem("Compra");
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frmNovaFazenda.dispose();
			}
		});
		mnNewMenu_1.add(mntmCompra);
		
		JMenuItem mntmNovaVenda = new JMenuItem("Venda");
		mnNewMenu_1.add(mntmNovaVenda);
		
		JMenuItem mntmTotal = new JMenuItem("Total");
		mnNewMenu_1.add(mntmTotal);
		mntmTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Total.main(null);
				frmNovaFazenda.dispose();
			}
		});
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmNovaFazenda.dispose();
			}
		});
		
		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		mnOpes.setForeground(new Color(230, 230, 250));
		mnOpes.setBackground(new Color(128, 128, 128));
		mnOpes.setIcon(new ImageIcon(NovaFazenda.class.getResource("/img/options.png")));
		menuBar.add(mnOpes);
		
		JMenuItem mntmNovaFazenda = new JMenuItem("Fazenda");
		mntmNovaFazenda.setEnabled(false);
		mntmNovaFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaFazenda.main(null);
				frmNovaFazenda.dispose();
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
				frmNovaFazenda.dispose();
				Principal.frmPrincipal.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		
		JMenuItem mntmEnviar = new JMenuItem("Enviar feedback");
		mntmEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frmNovaFazenda.dispose();
				EnviarEmail.main(null);
			}
		});
		mnOpes.add(mntmEnviar);
		
		mnOpes.add(mntmSada);
	}
}
