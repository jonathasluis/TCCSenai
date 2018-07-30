package JanelasComtabil;

import java.awt.Color;
import java.awt.ComponentOrientation;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import DAO.Compras;
import JanelasAnimal.CadastrarAnimais;
import JanelasFuncionarios.CadastrarFuncionarios;
import crud.CrudCompras;
import outraJanelas.EnviarEmail;
import outraJanelas.Login;
import outraJanelas.NovaFazenda;
import outraJanelas.Pergunta;
import outraJanelas.Principal;
import javax.swing.border.LineBorder;

public class NovaCompra {////
	
	int editar = 1;
	int x = 0;
	
	private JFrame frmCompraDeInsumos;
	private JTextField tfProduto;
	private JTextField tfData;
	private JTextField tfNota;
	private JTextField tfPreco;
	private JTextField txtFornecedor;
	private JButton btnLimpar;
	private JTextField txtProucurarProdutos;
	private JTable table;
	private Compras compra = new Compras();
	private JScrollPane scrollPane;
	private JSpinner spinner;
	static Compras addCompras = new Compras();
	static Compras editarCompras = new Compras();
	static int teste = 1; 
	static int x1=1;
	int idfazenda=0;
	int contador =+1;
	private JButton btnCancelar;
	private JFormattedTextField tfCNPJ;
	int mouseClick = 0;
	static String numero=null;
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
		frmCompraDeInsumos.setIconImage(Toolkit.getDefaultToolkit().getImage(NovaCompra.class.getResource("/img/logo-pequena-sem-texto.png")));
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
		tfProduto.setForeground(SystemColor.controlText);
		tfProduto.setFont(new Font("Arial", Font.PLAIN, 13));
		tfProduto.addKeyListener(new KeyAdapter() {
			//EVENTO PARA QUANDO APERTAR "ENTER" DAR FOCO EM OUTRA CAIXA DE TEXTO
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER) 
					txtFornecedor.requestFocus();
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
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String formatada = formato.format(data);
		
		tfData = new JTextField();
		tfData.setForeground(SystemColor.controlText);
		tfData.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tfData.setToolTipText("As datas s\u00E3o obtidas a partir do seu computador.\r\nCorrija se for necess\u00E1rio.");
		tfData.setFont(new Font("Arial", Font.PLAIN, 13));
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
		tfNota.setForeground(SystemColor.controlText);
		tfNota.setFont(new Font("Arial", Font.PLAIN, 13));
		tfNota.addKeyListener(new KeyAdapter() {
			//EVENTO PARA QUANDO APERTAR "ENTER" DAR FOCO EM OUTRA CAIXA DE TEXTO
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
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
		tfPreco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String numero = tfPreco.getText().toString();
				System.out.println(numero);
				numero = numero.replace(".", ",");
				System.out.println(numero);

				
			}
		});
		tfPreco.setForeground(SystemColor.controlText);
		tfPreco.setFont(new Font("Arial", Font.PLAIN, 13));
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
		txtFornecedor.setForeground(SystemColor.controlText);
		txtFornecedor.setFont(new Font("Arial", Font.PLAIN, 13));
		txtFornecedor.addKeyListener(new KeyAdapter() {
			//EVENTO PARA QUANDO APERTAR "ENTER" DAR FOCO EM OUTRA CAIXA DE TEXTO
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
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
		
		
		
		//BOT�O SALVAR E ADD OS DADOS NA TABELA DO BD
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//COME�O DO TRATAMENTO DE INFORMA��O 
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
					if(tfCNPJ.getText().contains(" ")) {
						int x = JOptionPane.showConfirmDialog(null, "Voc� deseja deixar o CNPJ nulo?", "ALERTA!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						if (x== JOptionPane.YES_OPTION) {
							tfCNPJ.setText("000.000.000/0000-00");
							tfNota.requestFocus();
							return;
						}else {
							tfCNPJ.requestFocus();
							return;
						}
					}	
					if(tfNota.getText().trim().equals("")) {
						int x = JOptionPane.showConfirmDialog(null, "Voc� deseja deixar a nota como nulo?", "ALERTA!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						if (x==JOptionPane.YES_OPTION) {
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
					}//FIM DOS TESTES DE TRATAMENTO DE INFORMA��O
					if (editar==1) {
						x1=0;
						colocaDadosDAO();
						new CrudCompras().addCompras(addCompras);
						
						//TESTE DE CADASTRO DE PRE�O
						 numero = tfPreco.getText().toString();
						numero = numero.replace(".", ",");
						//FIM DO TESTE
						
						//ADD LINHA NA TABELA DEPOIS DE SALVAR OS DADOS
						JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!","SUCESSO!",JOptionPane.INFORMATION_MESSAGE);
						if (table.getRowCount()<=19) {
							int x = (teste*16)+scrollPane.getHeight();
							scrollPane.setBounds(26, 290, 1024, x);	
						}
						new CrudCompras();
						criaTabela(CrudCompras.selecionaCompras(compra));
						btnLimpar.doClick();
					}
					//TESTE DE SALVAR AS ALTERA��ES 
					if (editar==0) {
						int resposta = JOptionPane.showConfirmDialog(null, "Voc� deseja alterar os dados j� salvos?","ALERTA",JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
						if (resposta == 0) {
							x1=0;
							update();
							new CrudCompras().updCompras(addCompras);
							new CrudCompras();
							criaTabela(CrudCompras.selecionaCompras(compra));
							btnCancelar.doClick();
						
						}
					}
			}
		});
		btnSalvar.setBounds(961, 637, 89, 23);
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
				tfData.setEnabled(true);
				tfData.setText(formatada);
				tfData.setEditable(false);
				tfData.setToolTipText("As datas s�o obtidas a partir do seu computador.\r\n" + 
					"Corrija se for necess�rio.");
				
			}
		});
		btnLimpar.setBounds(862, 637, 89, 23);
		frmCompraDeInsumos.getContentPane().add(btnLimpar);
		
		//CANCELAR A OPERA��O E VOLTAR PARA A TELA PRINCIPAL
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editar==1) {
					frmCompraDeInsumos.dispose();
					Principal.frmPrincipal.setVisible(true);
				}else if(editar==0) {
					editar=1;
					btnLimpar.setEnabled(true);
					btnLimpar.doClick();
				}
			}
		});
		btnCancelar.setBounds(763, 637, 89, 23);
		frmCompraDeInsumos.getContentPane().add(btnCancelar);
		
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setForeground(Color.WHITE);
		lblQuantidade.setFont(new Font("Arial", Font.BOLD, 14));
		lblQuantidade.setBounds(787, 111, 89, 20);
		frmCompraDeInsumos.getContentPane().add(lblQuantidade);
		
		
		spinner = new JSpinner();
		spinner.setForeground(SystemColor.controlText);
		spinner.setFont(new Font("Arial", Font.PLAIN, 13));
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
			//BOT�O PROUCURAR
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
					new CrudCompras();
					criaTabela(CrudCompras.selecionaCompras(compra));
		
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
		
		try {
			tfCNPJ = new JFormattedTextField(new MaskFormatter("###.###.###/####-##"));
			tfCNPJ.setForeground(SystemColor.controlText);
			tfCNPJ.setFont(new Font("Arial", Font.PLAIN, 13));
			tfCNPJ.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			tfCNPJ.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
						tfNota.requestFocus();
					}
				}
			});
			tfCNPJ.setBounds(108, 142, 200, 20);
			frmCompraDeInsumos.getContentPane().add(tfCNPJ);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Arial", Font.BOLD, 13));
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(26, 290, 1024, 21);
		
		frmCompraDeInsumos.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionBackground(new Color(51, 153, 255));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClick++;
				//new Thread(thread).start();	
				btnLimpar.setEnabled(false);
				editar();
				/*if(mouseClick%2==0) {
				
				mouseClick=0;
				}*/
			}
		});
		
		table.setToolTipText("Clique para editar os dados\r\n");
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setIgnoreRepaint(true);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Arial", Font.PLAIN, 13));
		table.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		table.setForeground(SystemColor.controlText);
		table.setBackground(SystemColor.controlHighlight);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID da compra", "Produto", "Fornecedor", "CNPJ fornecedor", "N\u00FAmero da nota", "Quantidade ", "Pre\u00E7o", "Data da compra"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
	
		
	
	
		
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
		
		//CHAMAR M�TODO
		x1=1;
		new CrudCompras();
		criaTabela(CrudCompras.selecionaCompras(compra));
		menu();
	}
		//M�TODO PARA COLOCAR OS DADOS NA TABELA
	public void criaTabela(ResultSet rs) {
		
		DefaultTableModel tabela = (DefaultTableModel) table.getModel();
		tabela.setRowCount(0);
		
		try {
			while(rs.next()) {
				idfazenda=rs.getInt("id_fazenda");
				scrollPane.setVisible(true);
				tabela.addRow(new Object[] {rs.getInt("id_compras"),rs.getString("produto"),rs.getString("fornecedor"),rs.getString("cnpj"),rs.getString("numero_nota"),
	 					rs.getInt("qtd"),rs.getDouble("preco"),rs.getString("data_compra")});
				
				
				//s
				//IF PARA FAZER A TABELA AUMENTAR 
				
				if (x1==1) {
					if (table.getRowCount() >= teste  & table.getRowCount() <=19 ) {
						teste=1;
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
		//M�TODO PARA COLOCAR O MENU NA TABELA
	public void menu() {

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(new Color(64, 64, 64)));
		menuBar.setFont(new Font("Arial", Font.PLAIN, 12));
		menuBar.setForeground(new Color(230, 230, 250));
		menuBar.setBackground(new Color(128, 128, 128));
		frmCompraDeInsumos.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.setIcon(new ImageIcon(NovaCompra.class.getResource("/img/Home.png")));
		mnInicio.setFont(new Font("Arial", Font.PLAIN, 12));
		mnInicio.setOpaque(true);
		mnInicio.setForeground(new Color(230, 230, 250));
		mnInicio.setBackground(new Color(128, 128, 128));
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmCompraDeInsumos.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("Gest\u00E3o");
		mnNewMenu.setIcon(new ImageIcon(NovaCompra.class.getResource("/img/gestao.png")));
		mnNewMenu.setFont(new Font("Arial", Font.PLAIN, 12));
		mnNewMenu.setOpaque(true);
		mnNewMenu.setFocusPainted(true);
		mnNewMenu.setBorder(new CompoundBorder());
		mnNewMenu.setBackground(new Color(128, 128, 128));
		mnNewMenu.setForeground(new Color(230, 230, 250));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Cadastrar Animais");
		mntmCadastrarAnimais.setFont(new Font("Arial", Font.PLAIN, 12));
		mntmCadastrarAnimais.setForeground(new Color(0, 0, 0));
		mntmCadastrarAnimais.setBackground(new Color(0, 128, 0));
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmCompraDeInsumos.dispose();
			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Cadastrar funcionarios");
		mnNewMenu.add(mntmCadastrarFuncionarios);
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmCompraDeInsumos.dispose();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("Financeiro");
		mnNewMenu_1.setIcon(new ImageIcon(NovaCompra.class.getResource("/img/money.png")));
		mnNewMenu_1.setFont(new Font("Arial", Font.PLAIN, 12));
		mnNewMenu_1.setForeground(new Color(230, 230, 250));
		mnNewMenu_1.setBackground(new Color(128, 128, 128));
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
		
		JMenuItem mntmNovaVenda = new JMenuItem("Nova Venda");
		mnNewMenu_1.add(mntmNovaVenda);
		
		JMenuItem mntmTotal = new JMenuItem("Total");
		mnNewMenu_1.add(mntmTotal);
		mntmTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Total.main(null);
				frmCompraDeInsumos.dispose();
			}
		});
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmCompraDeInsumos.dispose();
			}
		});
		
		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		mnOpes.setIcon(new ImageIcon(NovaCompra.class.getResource("/img/options.png")));
		mnOpes.setFont(new Font("Arial", Font.PLAIN, 12));
		mnOpes.setForeground(new Color(230, 230, 250));
		mnOpes.setBackground(new Color(128, 128, 128));
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
				Pergunta.contador = 1;
			}
		});
		mnOpes.add(mntmMudarFazenda);
		
		JMenuItem mntmDeslogar = new JMenuItem("Deslogar");
		mntmDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCompraDeInsumos.dispose();
				Principal.frmPrincipal.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		
		JMenuItem mntmEnviar = new JMenuItem("Enviar feedback");
		mntmEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frmCompraDeInsumos.dispose();
				EnviarEmail.main(null);
			}
		});
		mnOpes.add(mntmEnviar);
		mnOpes.add(mntmSada);
		frmCompraDeInsumos.getContentPane().setLayout(null);
	}
	
	public void colocaDadosDAO() {
		String preco = tfPreco.getText();
		preco = preco.replace(",", ".");
		int teste = (int) spinner.getValue();
		addCompras.setProduto(tfProduto.getText().toString());
		addCompras.setCnpj(tfCNPJ.getText().toString());
		addCompras.setDataCompra(tfData.getText().toString());
		addCompras.setFornecedor(txtFornecedor.getText().toString());
		addCompras.setNumeroNota(tfNota.getText().toString());
		addCompras.setQuantidade(teste);
		addCompras.setPreco(Double.parseDouble(preco));
		addCompras.setIdFazenda(Principal.fazenda.getIdFazenda());
	
	}
	
	public void editar() {
		int linha = table.getSelectedRow();
		
		tfProduto.setText(table.getValueAt(linha, 1).toString());
		txtFornecedor.setText(table.getValueAt(linha, 2).toString());
		tfCNPJ.setText(table.getValueAt(linha, 3).toString());
		tfNota.setText(table.getValueAt(linha, 4).toString());
		numero=table.getValueAt(linha, 6).toString();
		spinner.setValue(Integer.parseInt(table.getValueAt(linha, 5).toString()));
		tfData.setText(table.getValueAt(linha, 7).toString());
		tfData.setEnabled(false);
		tfData.setToolTipText("N�o � poss�vel alterar a data!");
		addCompras.setId(Integer.parseInt(table.getValueAt(linha, 0).toString()));

		tfPreco.setText(numero);
		editar = 0;
	
	}
	public void update() {
		
		numero = tfPreco.getText().toString();
		numero = numero.replace(",", ".");
		System.out.println(numero);
		addCompras.setProduto(tfProduto.getText().toString());
		addCompras.setCnpj(tfCNPJ.getText().toString());
		addCompras.setFornecedor(txtFornecedor.getText().toString());
		addCompras.setNumeroNota(tfNota.getText().toString());
		addCompras.setQuantidade(teste);
		addCompras.setPreco(Double.parseDouble(numero));
		addCompras.setIdFazenda(Principal.fazenda.getIdFazenda());
		

	}
/*	Runnable thread = new Runnable() {	
		@Override
		public void run() {
			try {
				while(true) { 
					Thread.sleep(1200);
					mouseClick=0;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};*/
}
