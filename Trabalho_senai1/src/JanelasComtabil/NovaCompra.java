package JanelasComtabil;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import JanelasAnimal.CadastrarAnimais;

import JanelasFuncionarios.CadastrarFuncionarios;
import crud.CrudCompras;
import outraJanelas.NovaFazenda;
import outraJanelas.Pergunta;
import outraJanelas.Principal;
import outraJanelas.VisualizarFazendas;
import javax.swing.JEditorPane;
import java.awt.SystemColor;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.Compras;
import DAO.Fazenda;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import java.awt.ComponentOrientation;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NovaCompra {
	
	int editar = 1;
	int x = 0;
	
	private JFrame frmCompraDeInsumos;
	private JTextField tfProduto;
	private JTextField tfData;
	private JTextField tfNota;
	private JTextField tfPreco;
	private JTextField txtFornecedor;
	private JTextField tfCNPJ;
	private JButton btnLimpar;
	private JTextField txtProucurarProdutos;
	private JTable table;
	private Compras compra = new Compras();
	private JScrollPane scrollPane;
	private JSpinner spinner;
	static Compras addCompras = new Compras();
	static Compras editarCompras = new Compras();
	static int teste = 0; 
	static int x1=1;
	int idfazenda=0;
	int contador =+1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaCompra window = new NovaCompra();
					window.frmCompraDeInsumos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NovaCompra() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCompraDeInsumos = new JFrame();
		frmCompraDeInsumos.setTitle("Compra de Insumos");
		frmCompraDeInsumos.setBounds(100, 100, 1080, 720);
		frmCompraDeInsumos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCompraDeInsumos.setLocationRelativeTo(null);
		frmCompraDeInsumos.setResizable(false);
		
		 compra.setIdFazenda(Principal.fazenda.getIdFazenda());
		
		JLabel lblCompraDeInsumos = new JLabel("Compra de Insumos");
		lblCompraDeInsumos.setForeground(Color.WHITE);
		lblCompraDeInsumos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompraDeInsumos.setFont(new Font("Arial", Font.BOLD, 25));
		lblCompraDeInsumos.setBounds(0, 11, 1074, 39);
		frmCompraDeInsumos.getContentPane().add(lblCompraDeInsumos);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setForeground(Color.WHITE);
		lblProduto.setFont(new Font("Arial", Font.BOLD, 14));
		lblProduto.setBounds(10, 80, 78, 20);
		frmCompraDeInsumos.getContentPane().add(lblProduto);
		
		tfProduto = new JTextField();
		tfProduto.addKeyListener(new KeyAdapter() {
			//EVENTO PARA QUANDO APERTAR "ENTER" DAR FOCO EM OUTRA CAIXA DE TEXTO
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==arg0.VK_ENTER) {
					txtFornecedor.requestFocus();
				}
			}
			
		});
		tfProduto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfProduto.setBounds(108, 82, 200, 20);
		frmCompraDeInsumos.getContentPane().add(tfProduto);
		tfProduto.setColumns(10);
		
		JLabel lblDataDaCompra = new JLabel("Data da Compra:");
		lblDataDaCompra.setForeground(Color.WHITE);
		lblDataDaCompra.setFont(new Font("Arial", Font.BOLD, 14));
		lblDataDaCompra.setBounds(405, 80, 125, 20);
		frmCompraDeInsumos.getContentPane().add(lblDataDaCompra);
		
		//VARIAVEL PARA PEGAR A DATA DO DIA ATUAL DO SEU COMPUTADOR
		Date data = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		String formatada = formato.format(data);
		
		tfData = new JTextField();
		tfData.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfData.setToolTipText("As datas s\u00E3o obtidas a partir do seu computador.\r\nCorrija se for necess\u00E1rio.");
		tfData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfData.setHorizontalAlignment(SwingConstants.CENTER);
		tfData.setBounds(546, 80, 188, 20);
		frmCompraDeInsumos.getContentPane().add(tfData);
		tfData.setColumns(10);
		tfData.setText(formatada);
		
		JLabel lblNumeroDaNota = new JLabel("Numero da Nota:");
		lblNumeroDaNota.setForeground(Color.WHITE);
		lblNumeroDaNota.setFont(new Font("Arial", Font.BOLD, 14));
		lblNumeroDaNota.setBounds(405, 111, 131, 20);
		frmCompraDeInsumos.getContentPane().add(lblNumeroDaNota);
		
		tfNota = new JTextField();
		tfNota.addKeyListener(new KeyAdapter() {
			//EVENTO PARA QUANDO APERTAR "ENTER" DAR FOCO EM OUTRA CAIXA DE TEXTO
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==e.VK_ENTER) {
					tfPreco.requestFocus();
				}
			}
		});
		tfNota.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfNota.setColumns(10);
		tfNota.setBounds(546, 111, 188, 20);
		frmCompraDeInsumos.getContentPane().add(tfNota);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setForeground(Color.WHITE);
		lblPreo.setFont(new Font("Arial", Font.BOLD, 14));
		lblPreo.setBounds(787, 80, 57, 20);
		frmCompraDeInsumos.getContentPane().add(lblPreo);
		

		
		
		tfPreco = new JTextField();
		tfPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		tfPreco.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfPreco.setBounds(886, 81, 164, 20);
		frmCompraDeInsumos.getContentPane().add(tfPreco);
		tfPreco.setColumns(10);
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setForeground(Color.WHITE);
		lblFornecedor.setFont(new Font("Arial", Font.BOLD, 14));
		lblFornecedor.setBounds(10, 111, 100, 20);
		frmCompraDeInsumos.getContentPane().add(lblFornecedor);
		
		txtFornecedor = new JTextField();
		txtFornecedor.addKeyListener(new KeyAdapter() {
			//EVENTO PARA QUANDO APERTAR "ENTER" DAR FOCO EM OUTRA CAIXA DE TEXTO
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==e.VK_ENTER) {
					tfCNPJ.requestFocus();
				}
			}
		});
		txtFornecedor.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtFornecedor.setBounds(108, 112, 200, 20);
		frmCompraDeInsumos.getContentPane().add(txtFornecedor);
		txtFornecedor.setColumns(10);
		
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setForeground(Color.WHITE);
		lblCnpj.setFont(new Font("Arial", Font.BOLD, 14));
		lblCnpj.setBounds(10, 142, 66, 20);
		frmCompraDeInsumos.getContentPane().add(lblCnpj);
		
		tfCNPJ = new JTextField();
		tfCNPJ.addKeyListener(new KeyAdapter() {
			//EVENTO PARA QUANDO APERTAR "ENTER" DAR FOCO EM OUTRA CAIXA DE TEXTO
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==e.VK_ENTER) {
					tfNota.requestFocus();
				}
			}
		});
		tfCNPJ.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfCNPJ.setBounds(108, 143, 200, 20);
		frmCompraDeInsumos.getContentPane().add(tfCNPJ);
		tfCNPJ.setColumns(10);
		
		//BOTÃO SALVAR E ADD OS DADOS NA TABELA DO BD
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//COMEÇO DO TRATAMENTO DE INFORMAÇÃO 
					if(tfProduto.getText().trim().equals("")) {
						tfProduto.requestFocus();
						JOptionPane.showMessageDialog(null, "Insira um Produto!", "ALERTA!",JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(txtFornecedor.getText().trim().equals("")){
						txtFornecedor.requestFocus();
						JOptionPane.showMessageDialog(null, "Insira um Fornecedor!", "ALERTA!",JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(tfCNPJ.getText().trim().equals("")) {
						int x = JOptionPane.showConfirmDialog(null, "Você deseja deixar o CNPJ nulo?", "ALERTA!",JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
						if (x==0) {
							tfCNPJ.setText("0000");
							tfNota.requestFocus();
							return;
						}else {
							tfCNPJ.requestFocus();
							return;
						}
					}	
					if(tfNota.getText().trim().equals("")) {
						int x = JOptionPane.showConfirmDialog(null, "Você deseja deixar a nota como nulo?", "ALERTA!",JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
						if (x==0) {
							tfNota.setText("0000");
							tfPreco.requestFocus();
							return;
						}else {
							tfNota.requestFocus();
							return;
						}
					}				
					if(tfPreco.getText().trim().equals("")) {
						tfPreco.requestFocus();
						JOptionPane.showMessageDialog(null, "Insira um valor!", "ALERTA!",JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if (spinner.getValue().equals(0)) {
						JOptionPane.showMessageDialog(null, "Insira uma quantidade!", "ALERTA!",JOptionPane.WARNING_MESSAGE);
						spinner.requestFocus();
						return;
					}//FIM DOS TESTES DE TRATAMENTO DE INFORMAÇÃO
					x1=0;
					colocaDadosDAO();
					new CrudCompras().addCompras(addCompras);
					
					//ADD LINHA NA TABELA DEPOIS DE SALVAR OS DADOS
					if (table.getRowCount()<=19) {
						int x = (teste*16)+scrollPane.getHeight();
						scrollPane.setBounds(26,290 , 1024, x);
					}
					criaTabela(new CrudCompras().selecionaCompras(compra));
					btnLimpar.doClick();
					JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!","SUCESSO!",JOptionPane.INFORMATION_MESSAGE);
					//TESTE DE SALVAR AS ALTERAÇÕES 
					if (editar==0) {
						int resposta = JOptionPane.showConfirmDialog(null, "Você deseja alterar os dados já salvos?","ALERTA",JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
						if (resposta == 0) {
							update();
							new CrudCompras().updCompras(addCompras);
						}
					}
			}
		});
		btnSalvar.setBounds(941, 637, 89, 23);
		frmCompraDeInsumos.getContentPane().add(btnSalvar);
		
		//LIMPAR TODOS OS DADOS 
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCNPJ.setText(null);
				spinner.setValue(0);
				tfNota.setText(null);
				tfProduto.setText(null);
				tfPreco.setText(null);
				txtFornecedor.setText(null);
				tfProduto.requestFocus();
				
			}
		});
		btnLimpar.setBounds(840, 637, 89, 23);
		frmCompraDeInsumos.getContentPane().add(btnLimpar);
		
		//CANCELAR A OPERAÇÃO E VOLTAR PARA A TELA PRINCIPAL
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCompraDeInsumos.dispose();
				Principal.frmPrincipal.setVisible(true);
			}
		});
		btnCancelar.setBounds(741, 637, 89, 23);
		frmCompraDeInsumos.getContentPane().add(btnCancelar);
		
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setForeground(Color.WHITE);
		lblQuantidade.setFont(new Font("Arial", Font.BOLD, 14));
		lblQuantidade.setBounds(787, 111, 89, 20);
		frmCompraDeInsumos.getContentPane().add(lblQuantidade);
		
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		spinner.setBounds(886, 112, 164, 20);
		frmCompraDeInsumos.getContentPane().add(spinner);
		
		
		txtProucurarProdutos = new JTextField();
		txtProucurarProdutos.setToolTipText("");
		txtProucurarProdutos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtProucurarProdutos.setForeground(Color.BLACK);
		txtProucurarProdutos.setColumns(10);
		txtProucurarProdutos.setBounds(26, 259, 369, 20);
		frmCompraDeInsumos.getContentPane().add(txtProucurarProdutos);
		
		
		
		
		
		JButton button = new JButton("Proucurar");
		button.addActionListener(new ActionListener() {
			//BOTÃO PROUCURAR
			public void actionPerformed(ActionEvent arg0) {
				//VARIAVEL PARA REGULAR O TAMNHO DA TABELA
				x1=0;	
				//CRIAR TABELA COM OS DADOS QUE FORAM PROUCURADOS 
				criaTabela(new CrudCompras().procurarCompra(txtProucurarProdutos.getText().toString(),idfazenda));
				//TRATAMENTO PARA AUMENTAR E DIMINUIR TABELA
				int tabela = table.getRowCount();
				int linha = tabela*16;
				int valor = 21+linha;
				scrollPane.setBounds(26, 290, 1024, valor);
				if (!(txtProucurarProdutos.getText()).trim().equals("")) {
					if (table.getRowCount()==0) {
						int x2 = 21;
						scrollPane.setBounds(26, 290, 1024, x2);
					}
				}else {
					criaTabela(new CrudCompras().selecionaCompras(compra));
		
				}
				//FIM DO TRATAMENTO 
				
				
			}
		});
		button.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		button.setForeground(Color.WHITE);
		button.setBackground(Color.DARK_GRAY);
		button.setFont(new Font("Arial", Font.BOLD, 12));
		button.setBounds(405, 258, 118, 23);
		frmCompraDeInsumos.getContentPane().add(button);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Arial", Font.BOLD, 12));
		scrollPane.setBorder(new CompoundBorder());
		scrollPane.setBounds(26, 290, 1024, 21);
		
		frmCompraDeInsumos.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				editar();
			}
		});
		
		table.setToolTipText("Clique duas vezes para editar os dados\r\n");
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setIgnoreRepaint(true);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setForeground(Color.DARK_GRAY);
		table.setBackground(SystemColor.control);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Produto", "Fornecedor", "CNPJ fornecedor", "N\u00FAmero da nota", "Quantidade ", "Pre\u00E7o", "Data da compra"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
	
		
	
	
		
		scrollPane.setViewportView(table);
		
		//IMAGEICON PARA COLOCAR IMAGEM NA TELA E REDIMENSIONAR 
		ImageIcon icon = new ImageIcon("src/img/compra1.png");
		icon.setImage(icon.getImage().getScaledInstance(57, 55, 100));
		JLabel llll = new JLabel("");
		llll.setBounds(941, 11, 57, 55);
		llll.setIcon(icon);
		
		
		//IMAGEICON PARA COLOCAR IMAGEM NA TELA E REDIMENSIONAR 
		ImageIcon img = new ImageIcon("src/img/fundo3.jpg");
		img.setImage(img.getImage().getScaledInstance(1074, 671, 100));
		frmCompraDeInsumos.getContentPane().add(llll);
		JLabel label = new JLabel("");
		label.setIcon(img);
		label.setBounds(0, 0, 1074, 671);
		frmCompraDeInsumos.getContentPane().add(label);
		
		
		//IF PARA VERIFICAR SE A TABLE ESTIVER VAZIA E DEIXAR VISIBLE.(FALSE)
		if (table.getRowCount()==0) {
			scrollPane.setVisible(false);
		}
		
		//CHAMAR MÉTODO
		
		criaTabela(new CrudCompras().selecionaCompras(compra));
		menu();
	}
		//MÉTODO PARA COLOCAR OS DADOS NA TABELA
	public void criaTabela(ResultSet rs) {
		
		DefaultTableModel tabela = (DefaultTableModel) table.getModel();
		tabela.setRowCount(0);
		
		try {
			while(rs.next()) {
				idfazenda=rs.getInt("id_fazenda");
				scrollPane.setVisible(true);
				tabela.addRow(new Object[] {rs.getString("produto"),rs.getString("fornecedor"),rs.getString("cnpj"),rs.getString("numero_nota"),
						rs.getInt("qtd"),rs.getDouble("preco"),rs.getString("data_compra")});
				
				
				
				//IF PARA FAZER A TABELA AUMENTAR 
				
				if (x1==1) {
					if (table.getRowCount() > teste  & table.getRowCount() <=19 ) {
						teste=+1;
						int x = (teste*16)+scrollPane.getHeight();
						scrollPane.setBounds(26,290 , 1024, x);		
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		//MÉTODO PARA COLOCAR O MENU NA TABELA
	public void menu() {

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(230, 230, 250));
		menuBar.setBackground(Color.DARK_GRAY);
		frmCompraDeInsumos.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.setOpaque(true);
		mnInicio.setForeground(new Color(230, 230, 250));
		mnInicio.setBackground(Color.DARK_GRAY);
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmCompraDeInsumos.dispose();
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
				frmCompraDeInsumos.dispose();
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
				frmCompraDeInsumos.dispose();
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
				frmCompraDeInsumos.dispose();
			}
		});
		mnFuncionarios.add(mntmCadastrarFuncionarios);
		
		JMenu mnNewMenu_1 = new JMenu("Compra de Insumos");
		mnNewMenu_1.setForeground(new Color(230, 230, 250));
		mnNewMenu_1.setBackground(Color.DARK_GRAY);
		mnNewMenu_1.setOpaque(true);
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCompra = new JMenuItem("Nova Compra");
		mntmCompra.setEnabled(false);
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frmCompraDeInsumos.dispose();
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
				frmCompraDeInsumos.dispose();
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
				frmCompraDeInsumos.dispose();
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
				frmCompraDeInsumos.dispose();
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
		frmCompraDeInsumos.getContentPane().setLayout(null);
	}
	
	public void colocaDadosDAO() {
		int teste = (int) spinner.getValue();
		addCompras.setProduto(tfProduto.getText().toString());
		addCompras.setCnpj(tfCNPJ.getText().toString());
		addCompras.setDataCompra(tfData.getText().toString());
		addCompras.setFornecedor(txtFornecedor.getText().toString());
		addCompras.setNumeroNota(tfNota.getText().toString());
		addCompras.setQuantidade(teste);
		addCompras.setPreco(Double.parseDouble(tfPreco.getText()));
		addCompras.setIdFazenda(Principal.fazenda.getIdFazenda());
	
	}
	
	public void editar() {
		int linha = table.getSelectedRow();
		
		tfProduto.setText(table.getValueAt(linha, 0).toString());
		txtFornecedor.setText(table.getValueAt(linha, 1).toString());
		tfCNPJ.setText(table.getValueAt(linha, 2).toString());
		tfNota.setText(table.getValueAt(linha, 3).toString());
		tfPreco.setText(table.getValueAt(linha, 5).toString());
		spinner.setValue(Integer.parseInt(table.getValueAt(linha, 4).toString()));
		tfData.setText(table.getValueAt(linha, 6).toString());
		tfData.setEnabled(false);
		tfData.setToolTipText("Não é possível alterar a data!");
		editar = 0;
	
	}
	public void update() {
		addCompras.setProduto(tfProduto.getText().toString());
		addCompras.setCnpj(tfCNPJ.getText().toString());
		addCompras.setFornecedor(txtFornecedor.getText().toString());
		addCompras.setNumeroNota(tfNota.getText().toString());
		addCompras.setQuantidade(teste);
		addCompras.setPreco(Double.parseDouble(tfPreco.getText()));
		addCompras.setIdFazenda(Principal.fazenda.getIdFazenda());
	

	}
	
	
}
