package JanelasComtabil;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;

import DAO.Vendas;
import JanelasAnimal.CadastrarAnimais;
import JanelasFuncionarios.CadastrarFuncionarios;
import banco.Conexao;
import outraJanelas.EnviarEmail;
import outraJanelas.Login;
import outraJanelas.NovaFazenda;
import outraJanelas.Pergunta;
import outraJanelas.Principal;
import javax.swing.border.LineBorder;

public class NovaVenda {

	private JFrame frmNovaVenda;
	private JTextField tfProduto;
	private JRadioButton rdbtnPlantio;
	private JRadioButton rdbtnAnimal;
	private JRadioButton rdbtnSubproduto;
	private JTextField tfPreco;
	private JTextField tfCliente;
	public static JComboBox<String> cbAnimal;
	private JLabel lblProduto;
	private JLabel lblAnimal;
	int id;
	private JButton btnLimpar;
	private JTable table;
	private JTextField textField;
	private JFormattedTextField ftfData;
	private MaskFormatter mask;
	static String numero=null;
	Vendas venda = new Vendas();
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaVenda window = new NovaVenda();
					window.frmNovaVenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NovaVenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {//inicio formatação mascara
			mask = new MaskFormatter("####-##-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//fim formatação mascara
		
		frmNovaVenda = new JFrame();
		frmNovaVenda.setIconImage(Toolkit.getDefaultToolkit().getImage(NovaVenda.class.getResource("/img/logo-pequena-sem-texto.png")));
		frmNovaVenda.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		frmNovaVenda.setTitle("Nova Venda");
		frmNovaVenda.setBounds(100, 100, 1080, 720);
		frmNovaVenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNovaVenda.setLocationRelativeTo(null);
		frmNovaVenda.setResizable(false);
		frmNovaVenda.getContentPane().setLayout(null);
		
		ImageIcon icon = new ImageIcon("src/img/compra1.png");
		icon.setImage(icon.getImage().getScaledInstance(57, 55, 100));
		JLabel label = new JLabel("");
		label.setBounds(941, 11, 57, 55);
		label.setIcon(icon);
		frmNovaVenda.getContentPane().add(label);
		
		
		JLabel lblNovaVenda = new JLabel("Nova Venda");
		lblNovaVenda.setHorizontalAlignment(SwingConstants.CENTER);
		lblNovaVenda.setFont(new Font("Arial", Font.BOLD, 25));
		lblNovaVenda.setBounds(0, 11, 1074, 39);
		frmNovaVenda.getContentPane().add(lblNovaVenda);
		
		JLabel lblTipoDoProduto = new JLabel("Tipo do Produto:");
		lblTipoDoProduto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTipoDoProduto.setBounds(10, 80, 107, 20);
		frmNovaVenda.getContentPane().add(lblTipoDoProduto);
		
		rdbtnAnimal = new JRadioButton("Animal");
		rdbtnAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbAnimal.setEnabled(true);
				tfProduto.setEnabled(false);
			}
		});
		rdbtnAnimal.setBounds(123, 80, 66, 20);
		frmNovaVenda.getContentPane().add(rdbtnAnimal);
		
		rdbtnSubproduto = new JRadioButton("Subproduto");
		rdbtnSubproduto.setSelected(true);
		rdbtnSubproduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbAnimal.setEnabled(true);
				tfProduto.setEnabled(true);
			}
		});
		rdbtnSubproduto.setBounds(191, 80, 94, 20);
		frmNovaVenda.getContentPane().add(rdbtnSubproduto);
		
		rdbtnPlantio = new JRadioButton("Plantio");
		rdbtnPlantio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbAnimal.setEnabled(false);
				tfProduto.setEnabled(true);
			}
		});
		rdbtnPlantio.setBounds(287, 80, 66, 20);
		frmNovaVenda.getContentPane().add(rdbtnPlantio);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnPlantio);
		bg.add(rdbtnSubproduto);
		bg.add(rdbtnAnimal);
		
		lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProduto.setBounds(10, 111, 78, 20);
		frmNovaVenda.getContentPane().add(lblProduto);
		
		tfProduto = new JTextField();
		tfProduto.setBounds(108, 111, 200, 20);
		frmNovaVenda.getContentPane().add(tfProduto);
		tfProduto.setColumns(10);
		
		lblAnimal = new JLabel("Lote de animais:");
		lblAnimal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAnimal.setBounds(405, 111, 131, 20);
		frmNovaVenda.getContentPane().add(lblAnimal);
		
		cbAnimal = new JComboBox<String>();
		cbAnimal.setBackground(SystemColor.controlHighlight);
		cbAnimal.setBounds(546, 111, 188, 20);
		frmNovaVenda.getContentPane().add(cbAnimal);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantidade.setBounds(787, 111, 89, 20);
		frmNovaVenda.getContentPane().add(lblQuantidade);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setBounds(886, 112, 164, 20);
		frmNovaVenda.getContentPane().add(spinner);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPreco.setBounds(787, 80, 57, 20);
		frmNovaVenda.getContentPane().add(lblPreco);
		
		tfPreco = new JTextField();
		tfPreco.setColumns(10);
		tfPreco.setBounds(886, 81, 164, 20);
		tfPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE || (e.getKeyChar() == KeyEvent.VK_0)|| (e.getKeyChar() == KeyEvent.VK_1)|| (e.getKeyChar() == KeyEvent.VK_2) 
			            || (e.getKeyChar() == KeyEvent.VK_3)|| (e.getKeyChar() == KeyEvent.VK_4)|| (e.getKeyChar() == KeyEvent.VK_5)|| (e.getKeyChar() == KeyEvent.VK_6)|| (e.getKeyChar() == KeyEvent.VK_7)
			            || (e.getKeyChar() == KeyEvent.VK_8)|| (e.getKeyChar() == KeyEvent.VK_9)||(e.getKeyChar() == KeyEvent.VK_ENTER)||(e.getKeyChar() == KeyEvent.VK_COMMA))
			    { 
					
			    }else{
			         e.consume();
			    }
			}
		});
		frmNovaVenda.getContentPane().add(tfPreco);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCliente.setBounds(10, 142, 100, 20);
		frmNovaVenda.getContentPane().add(lblCliente);
		
		tfCliente = new JTextField();
		tfCliente.setColumns(10);
		tfCliente.setBounds(108, 142, 200, 20);
		frmNovaVenda.getContentPane().add(tfCliente);
		
		JLabel lblDataDaVenda = new JLabel("Data da Venda:");
		lblDataDaVenda.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDataDaVenda.setBounds(405, 80, 125, 20);
		frmNovaVenda.getContentPane().add(lblDataDaVenda);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfCliente.getText().trim().equals("")) {//inicio do tratamento de informaçao 
					JOptionPane.showMessageDialog(null, "Insira o nome do Cliente", "ALERTA!", JOptionPane.WARNING_MESSAGE);
					tfCliente.requestFocus();
					return;
				}
				if(ftfData.getText().contains(" ")) {
					JOptionPane.showMessageDialog(null, "Insira a data da Venda", "ALERTA!", JOptionPane.WARNING_MESSAGE);
					ftfData.requestFocus();
					return;
				}
				if(tfPreco.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira o valor", "ALERTA!", JOptionPane.WARNING_MESSAGE);
					tfPreco.requestFocus();
					return;
				}
				if (spinner.getValue().equals(0)) {
					JOptionPane.showMessageDialog(null, "Insira uma quantidade!", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					spinner.requestFocus();
					return;
				}//fim do tratamento de informaçao 
				
				//TESTE DE CADASTRO DE PREÇO
				numero = tfPreco.getText().toString();
				numero = numero.replace(".", ",");
				//FIM DO TESTE
				
				
			}
		});
		btnSalvar.setBounds(975, 637, 89, 23);
		frmNovaVenda.getContentPane().add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCliente.setText(null);
				ftfData.setValue(null);
				tfPreco.setText(null);
				tfProduto.setText(null);
				rdbtnSubproduto.setSelected(true);
				spinner.setValue(0);
			}
		});
		btnLimpar.setBounds(876, 637, 89, 23);
		frmNovaVenda.getContentPane().add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNovaVenda.dispose();
				Principal.frmPrincipal.setVisible(true);
			}
		});
		btnCancelar.setBounds(777, 637, 89, 23);
		frmNovaVenda.getContentPane().add(btnCancelar);
		
		Date data = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String formatada = formato.format(data);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 290, 1024, 336);
		frmNovaVenda.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		ftfData = new JFormattedTextField(mask);
		ftfData.setText(formatada);
		ftfData.setBounds(546, 80, 188, 20);
		frmNovaVenda.getContentPane().add(ftfData);
		
		textField = new JTextField();
		textField.setToolTipText("");
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBounds(26, 259, 369, 20);
		frmNovaVenda.getContentPane().add(textField);
		
		JButton button = new JButton("Proucurar");
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Arial", Font.BOLD, 12));
		button.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		button.setBackground(Color.DARK_GRAY);
		button.setBounds(405, 259, 118, 23);
		frmNovaVenda.getContentPane().add(button);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(NovaVenda.class.getResource("/img/gradiente_Branco.jpg")));
		label_1.setBounds(0, 0, 1074, 670);
		frmNovaVenda.getContentPane().add(label_1);
		
		menu();
	}
	
	void preencherDAOparaSalvarCompra() {
		venda.setCliente(tfCliente.getText());
		venda.setDataVenda(ftfData.getText());
		venda.setPreco(Double.parseDouble(numero));
		venda.setProduto(tfProduto.getText());
		venda.setQuantidade((int) spinner.getValue());
		venda.setIdFazenda(Principal.fazenda.getIdFazenda());
	}
	
	void comboBoxAnimal() {
		ResultSet dados1=null;
		String sql = "SELECT (nome_es) FROM especie order by nome_es";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			dados1 = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			cbAnimal.removeAllItems();
			while(dados1.next()) {
				cbAnimal.addItem(dados1.getString("nome_es"));
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erro ao preencher comboBox especie");
		}
	}
	
	void menu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(Color.DARK_GRAY));
		menuBar.setBackground(new Color(128, 128, 128));
		frmNovaVenda.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.setForeground(new Color(230, 230, 250));
		mnInicio.setIcon(new ImageIcon(NovaVenda.class.getResource("/img/Home.png")));
		mnInicio.setBackground(new Color(128, 128, 128));
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmNovaVenda.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("Gest\u00E3o");
		mnNewMenu.setForeground(new Color(230, 230, 250));
		mnNewMenu.setIcon(new ImageIcon(NovaVenda.class.getResource("/img/gestao.png")));
		mnNewMenu.setBackground(new Color(128, 128, 128));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Animais");
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmNovaVenda.dispose();
			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Funcionarios");
		mnNewMenu.add(mntmCadastrarFuncionarios);
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmNovaVenda.dispose();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("Financeiro");
		mnNewMenu_1.setForeground(new Color(230, 230, 250));
		mnNewMenu_1.setIcon(new ImageIcon(NovaVenda.class.getResource("/img/money.png")));
		mnNewMenu_1.setBackground(new Color(128, 128, 128));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCompra = new JMenuItem("Compra");
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frmNovaVenda.dispose();
			}
		});
		mnNewMenu_1.add(mntmCompra);
		
		JMenuItem mntmNovaVenda = new JMenuItem("Venda");
		mnNewMenu_1.add(mntmNovaVenda);
		mntmNovaVenda.setEnabled(false);
		
		JMenuItem mntmTotal = new JMenuItem("Total");
		mnNewMenu_1.add(mntmTotal);
		mntmTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Total.main(null);
				frmNovaVenda.dispose();
			}
		});
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmNovaVenda.dispose();
			}
		});
		
		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		mnOpes.setForeground(new Color(230, 230, 250));
		mnOpes.setIcon(new ImageIcon(NovaVenda.class.getResource("/img/options.png")));
		mnOpes.setBackground(new Color(128, 128, 128));
		menuBar.add(mnOpes);
		
		JMenuItem mntmNovaFazenda = new JMenuItem("Fazenda");
		mntmNovaFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaFazenda.main(null);
				frmNovaVenda.dispose();
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
				frmNovaVenda.dispose();
				Principal.frmPrincipal.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		
		JMenuItem mntmEnviar = new JMenuItem("Enviar feedback");
		mntmEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frmNovaVenda.dispose();
				EnviarEmail.main(null);
			}
		});
		mnOpes.add(mntmEnviar);
		mnOpes.add(mntmSada);
	}
}
