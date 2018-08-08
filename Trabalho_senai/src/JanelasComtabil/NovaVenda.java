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
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import DAO.Vendas;
import JanelasAnimal.CadastrarAnimais;
import JanelasFuncionarios.CadastrarFuncionarios;
import banco.Conexao;
import crud.CrudVendas;
import outraJanelas.EnviarEmail;
import outraJanelas.Login;
import outraJanelas.NovaFazenda;
import outraJanelas.Pergunta;
import outraJanelas.Principal;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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
	private JTextField tfProcurar;
	private JFormattedTextField ftfData;
	private MaskFormatter mask;
	static String numero=null;
	Vendas venda = new Vendas();
	private JSpinner spinner;
	int contadorEditar = 0;
	private JScrollPane scrollPane;

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
		try {//inicio formata��o mascara
			mask = new MaskFormatter("####-##-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//fim formata��o mascara
		
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
		rdbtnAnimal.setOpaque(false);
		rdbtnAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbAnimal.setEnabled(true);
				tfProduto.setEnabled(false);
			}
		});
		rdbtnAnimal.setBounds(123, 80, 66, 20);
		frmNovaVenda.getContentPane().add(rdbtnAnimal);
		
		rdbtnSubproduto = new JRadioButton("Subproduto");
		rdbtnSubproduto.setOpaque(false);
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
		rdbtnPlantio.setOpaque(false);
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
		btnSalvar.setBackground(SystemColor.controlHighlight);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfCliente.getText().trim().equals("")) {//inicio do tratamento de informa�ao 
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
				}
				if((rdbtnSubproduto.isSelected() && tfProduto.getText().trim().equals("")) ||
						(rdbtnPlantio.isSelected() && tfProduto.getText().trim().equals(""))) {
					JOptionPane.showMessageDialog(null, "Insira o produto", "ALERTA!", JOptionPane.WARNING_MESSAGE);
					tfProduto.requestFocus();
					return;
				}//fim do tratamento de informa�ao 
				
				//TESTE DE CADASTRO DE PRE�O
				numero = tfPreco.getText().toString();
				numero = numero.replace(".", ",");
				//FIM DO TESTE
				
				if (contadorEditar==0) {
					pegaIdAnimal();
					preencherDAOparaSalvarVenda();
					new CrudVendas().addvendas(venda);
					JOptionPane.showMessageDialog(null, "salvo com sucesso!");
				}
			}
		});
		btnSalvar.setBounds(975, 637, 89, 23);
		frmNovaVenda.getContentPane().add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBackground(SystemColor.controlHighlight);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCliente.setText(null);
				ftfData.setValue(null);
				tfPreco.setText(null);
				tfProduto.setText(null);
				rdbtnSubproduto.setSelected(true);
				spinner.setValue(0);
				cbAnimal.setSelectedIndex(0);
			}
		});
		btnLimpar.setBounds(876, 637, 89, 23);
		frmNovaVenda.getContentPane().add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(SystemColor.controlHighlight);
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
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(169, 169, 169));
		scrollPane.setFont(new Font("Arial", Font.BOLD, 13));
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(10, 253, 1054, 136);
		
		frmNovaVenda.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionBackground(new Color(51, 153, 255));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		
		table.setToolTipText("Clique para editar os dados\r\n");
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setIgnoreRepaint(true);
		table.setFont(new Font("Arial", Font.PLAIN, 13));
		table.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		table.setForeground(SystemColor.controlText);
		table.setBackground(new Color(169, 169, 169));
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID da venda","Tipo do Produto", "Produto", "Animal", "N\u00FAmero da nota", "Quantidade ", "Pre\u00E7o", "Data da venda",
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
		
		ftfData = new JFormattedTextField(mask);
		ftfData.setText(formatada);
		ftfData.setBounds(546, 80, 188, 20);
		frmNovaVenda.getContentPane().add(ftfData);
		
		tfProcurar = new JTextField();
		tfProcurar.setToolTipText("");
		tfProcurar.setForeground(Color.BLACK);
		tfProcurar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfProcurar.setColumns(10);
		tfProcurar.setBounds(10, 222, 331, 20);
		frmNovaVenda.getContentPane().add(tfProcurar);
		
		JButton btnProcurar = new JButton("Proucurar");
		btnProcurar.setForeground(Color.WHITE);
		btnProcurar.setFont(new Font("Arial", Font.BOLD, 12));
		btnProcurar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnProcurar.setBackground(Color.DARK_GRAY);
		btnProcurar.setBounds(351, 222, 89, 20);
		frmNovaVenda.getContentPane().add(btnProcurar);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(NovaVenda.class.getResource("/img/gradiente_Branco.jpg")));
		label_1.setBounds(0, 0, 1074, 670);
		frmNovaVenda.getContentPane().add(label_1);
		
		menu();
		comboBoxAnimal();
		venda.setIdFazenda(Principal.fazenda.getIdFazenda());
		criaTabela(CrudVendas.selecionaVendas(venda));
	}
	
	void preencherDAOparaSalvarVenda() {
		venda.setCliente(tfCliente.getText());
		venda.setDataVenda(ftfData.getText());
		venda.setPreco(Double.parseDouble(numero));
		venda.setQuantidade((int) spinner.getValue());
		venda.setIdFazenda(Principal.fazenda.getIdFazenda());
		
		if (rdbtnAnimal.isSelected()) {
			venda.setProduto(null);
			venda.setIdanimal(id);
			venda.setTipoDoProduto(1);
		}
		if (rdbtnSubproduto.isSelected()) {
			venda.setProduto(tfProduto.getText());
			venda.setIdanimal(id);
			venda.setTipoDoProduto(2);
		}
		if (rdbtnPlantio.isSelected()) {
			venda.setIdanimal(1);
			venda.setProduto(tfProduto.getText());
			venda.setTipoDoProduto(3);
		}
	}
	
	//M�TODO PARA COLOCAR OS DADOS NA TABELA a
		public void criaTabela(ResultSet rs) {
			
			DefaultTableModel tabela = (DefaultTableModel) table.getModel();
			tabela.setRowCount(0);
			
			String tipo=null;
			String animal=null;
			
			try {
				while(rs.next()) {
					
					if (rs.getInt("tipodoproduto")==1) {
						tipo="Animal";
					}
					if (rs.getInt("tipodoproduto")==2) {
						tipo="Subproduto";
					}
					if (rs.getInt("tipodoproduto")==3) {
						tipo="Plantio";
					}
					
					
					ResultSet dados=null;//inicio do resultset para pegar o nome do animal
					String sql = "SELECT idvendas, animais.nomelote FROM vendas "+
							"INNER JOIN animais ON vendas.idanimal = animais.idanimal where idvendas=?";
					try {
						PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
						stmt.setInt(1, rs.getInt("idvendas"));
						dados = stmt.executeQuery();
						stmt.execute();
						stmt.close();	
						
						if (dados.next()) {
							animal=dados.getString("nomeLote");
						}
						if (rs.getInt("idanimal")==1) {
							animal=null;
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("erro ao pegar nome animal");
					}//fim do resultset para pegar o nome do animal
					
					tabela.addRow(new Object[] {rs.getInt("idvendas"),tipo,rs.getString("produto"),animal,rs.getString("numeronota"),
		 					rs.getInt("qtd"),rs.getDouble("preco"),rs.getString("datavenda")});
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	void comboBoxAnimal() {
		ResultSet dados=null;
		String sql = "SELECT nomeLote, raca.nome_ra, raca FROM animais "
				+ "INNER JOIN raca ON raca = idraca where idfazenda = ? order by nomeLote";//seleciona o animal com o nome da ra�a
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, Principal.fazenda.getIdFazenda());
			dados = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			cbAnimal.removeAllItems();
			while(dados.next()) {
				ResultSet dados2=null;
				String sql2 = "SELECT  id_especie, especie.nome_es FROM raca " + 
						"INNER JOIN especie " + 
						"ON id_especie = idespecie where idraca = ?";//seleciona o nome da especie da ra�a
				
				PreparedStatement stmt2 = Conexao.conexao.prepareStatement(sql2);
				stmt2.setInt(1, dados.getInt("raca"));
				dados2 = stmt2.executeQuery();
				stmt2.execute();
				stmt2.close();
				
				if (dados2.next()) {
					cbAnimal.addItem(dados.getString("nomeLote")+" - "+dados.getString("nome_ra")+" - "+dados2.getString("nome_es"));
				}
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erro ao preencher comboBox animnal");
		}
	}
	
	void pegaIdAnimal() {
		String palavraCheia = cbAnimal.getSelectedItem().toString();
		String[] palavraCortada = palavraCheia.split("-");
		ResultSet rs = null;
		String sql = "select idanimal from animais where nomeLote=?";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, palavraCortada[0]);
			rs = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
			if (rs.next()) {
				id = rs.getInt("idanimal");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void menu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(Color.DARK_GRAY));
		menuBar.setBackground(new Color(128, 128, 128));
		frmNovaVenda.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.setForeground(new Color(230, 230, 250));
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
