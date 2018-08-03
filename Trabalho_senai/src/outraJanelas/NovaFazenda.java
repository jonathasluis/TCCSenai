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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DAO.Fazenda;
import Imagem.MetodosImagem;
import JanelasAnimal.CadastrarAnimais;
import JanelasComtabil.NovaCompra;
import JanelasComtabil.NovaVenda;
import JanelasComtabil.Total;
import JanelasFuncionarios.CadastrarFuncionarios;
import banco.Conexao;
import crud.CrudFazenda;

public class NovaFazenda {

	private JFrame frmNovaFazenda;
	private JTextField tfNome;
	private JTextField tfTamanho;
	private JTextField tfEscritura;
	private JTextField tfProprietario;
	public static JLabel lblImg;
	public static JPanel panel;
	public static File img;
	MetodosImagem mI = new MetodosImagem();
	private JButton btnLimpar;
	private JTextField tfQtdAnimais;
	private JTextField tfQtdFuncionarios;
	int contadorEditar = 0;
	private JButton btnDeletar;
	private JTextArea taDescricao;
	Fazenda fazenda = new Fazenda();
	private JTable table;

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
		btnSalvar.setBackground(SystemColor.controlHighlight);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfNome.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "insira um nome", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfNome.requestFocus();
					return;
				}
				if(tfTamanho.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "insira o tamanho da propriedade", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfTamanho.requestFocus();
					return;
				}
				if (tfEscritura.getText().trim().equals("")) {
					int resposta = JOptionPane.showConfirmDialog(null, "Deseja deixar o campo escritura nulo", "ALERTA!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (resposta == JOptionPane.YES_OPTION) {
						tfEscritura.setText("00000");
					}else {
						tfEscritura.requestFocus();
						return;
					}
				}
				if (tfProprietario.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "insira o nome do proprietário", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfProprietario.requestFocus();
					return;
				}
				if (taDescricao.getText().trim().equals("")) {
					int resposta = JOptionPane.showConfirmDialog(null, "Deseja deixar o campo descrição nulo", "ALERTA!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (resposta == JOptionPane.YES_OPTION) {
						taDescricao.setText(null);
					}else {
						taDescricao.requestFocus();
						return;
					}
				}
				
				if (contadorEditar==0) {
					DAOFazenda();
					new CrudFazenda().addFazenda(fazenda);
					JOptionPane.showMessageDialog(null, "Fazenda cadastrada com sucesso!");
					btnLimpar.doClick();
					colocaDadosNaTabela(CrudFazenda.selecionaFazenda(Pergunta.usuario));
				}
				if (contadorEditar==1) {
					int resposta = JOptionPane.showConfirmDialog(null, "voce deseja alterar esse Fazenda? ", "alerta",
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(resposta==JOptionPane.YES_OPTION) {
						DAOFazenda();
						new CrudFazenda().updFazenda(fazenda);
						JOptionPane.showMessageDialog(null, "Fazenda alterada com sucesso!");
					}else {
						return;
					}
				}
				
				int resposta = JOptionPane.showConfirmDialog(null, "voce deseja alterar para essa Fazenda? ", "alerta",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(resposta==JOptionPane.YES_OPTION) {
					Pergunta.main(null);
					Pergunta.contador = 1;
					Pergunta.comboBox.setSelectedItem(fazenda.getNome());
				}else {
					return;
				}
				
			}
		});
		btnSalvar.setBounds(975, 636, 89, 23);
		frmNovaFazenda.getContentPane().add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfEscritura.setText(null);
				tfNome.setText(null);
				tfTamanho.setText(null);
				tfProprietario.setText(null);
				taDescricao.setText(null);
				lblImg.setHorizontalAlignment(SwingConstants.CENTER);
				lblImg.setIcon(new ImageIcon(NovaFazenda.class.getResource("/img/logo-pequena-sem-texto.png")));
				tfQtdAnimais.setText("0");
				tfQtdFuncionarios.setText("0");
			}
		});
		btnLimpar.setBounds(678, 636, 89, 23);
		frmNovaFazenda.getContentPane().add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (contadorEditar==0) {
					Principal.frmPrincipal.setVisible(true);
					frmNovaFazenda.dispose();
				}
				if(contadorEditar==1) {
					contadorEditar=0;
					btnDeletar.setEnabled(false);
					btnLimpar.setEnabled(true);
					btnLimpar.doClick();
				}
			}
		});
		btnCancelar.setBounds(777, 636, 89, 23);
		frmNovaFazenda.getContentPane().add(btnCancelar);
		
		btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resposta = JOptionPane.showConfirmDialog(null, "Todos os dados dessa Fazenda serão excluidos,deseja continuar", "ALERTA!",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(resposta==JOptionPane.YES_OPTION) {
					new CrudFazenda().deletaFazenda(fazenda);
					btnCancelar.doClick();//para zerar variavel editar e habilitar os botoes
					btnLimpar.doClick();
					colocaDadosNaTabela(CrudFazenda.selecionaFazenda(Pergunta.usuario));
					
					if(fazenda.getIdFazenda()==Principal.fazenda.getIdFazenda()) {//se a fazenda logada for excluida manda mudar
						Pergunta.main(null);
						Pergunta.contador = 1;
					}
				}
			}
		});
		btnDeletar.setEnabled(false);
		btnDeletar.setBounds(876, 636, 89, 23);
		frmNovaFazenda.getContentPane().add(btnDeletar);
		
		JLabel lblNonaFazenda = new JLabel("Fazendas");
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
		lblTamanho.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTamanho.setBounds(10, 91, 60, 20);
		frmNovaFazenda.getContentPane().add(lblTamanho);
		
		tfEscritura = new JTextField();
		tfEscritura.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tfEscritura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfProprietario.requestFocus();
				}
			}
		});
		tfEscritura.setColumns(10);
		tfEscritura.setBounds(80, 123, 200, 20);
		frmNovaFazenda.getContentPane().add(tfEscritura);
		
		JLabel lblEscritura = new JLabel("Escritura:");
		lblEscritura.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEscritura.setBounds(10, 123, 60, 20);
		frmNovaFazenda.getContentPane().add(lblEscritura);
		
		tfProprietario = new JTextField();
		tfProprietario.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tfProprietario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					taDescricao.requestFocus();
				}
			}
		});
		tfProprietario.setColumns(10);
		tfProprietario.setBounds(510, 61, 190, 20);
		frmNovaFazenda.getContentPane().add(tfProprietario);
		
		JLabel lblPro = new JLabel("Propriet\u00E1rio:");
		lblPro.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPro.setBounds(420, 61, 80, 20);
		frmNovaFazenda.getContentPane().add(lblPro);
		
		JLabel lblDescrio_1 = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescrio_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescrio_1.setBounds(420, 92, 70, 150);
		frmNovaFazenda.getContentPane().add(lblDescrio_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setOpaque(false);
		scrollPane.setInheritsPopupMenu(true);
		scrollPane.setBounds(10, 253, 1054, 373);
		frmNovaFazenda.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pegaDadosDaTabela();
				btnLimpar.setEnabled(false);
				btnDeletar.setEnabled(true);
				contadorEditar=1;
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID ", "Nome da Fazenda", "Tamanho da Fazenda", "Escritura da Fazenda", "Descri\u00E7\u00E3o da Fazenda", "Proprietario"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(99);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(203);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(160);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(160);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(270);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(160);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JLabel lblQuantidadeDeAnimais = new JLabel("Quantidade de animais:");
		lblQuantidadeDeAnimais.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantidadeDeAnimais.setBounds(10, 178, 157, 20);
		frmNovaFazenda.getContentPane().add(lblQuantidadeDeAnimais);
		
		JLabel lblQuantidadeDeFuncionaris = new JLabel("Quantidade de funcionarios:");
		lblQuantidadeDeFuncionaris.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantidadeDeFuncionaris.setBounds(10, 209, 180, 20);
		frmNovaFazenda.getContentPane().add(lblQuantidadeDeFuncionaris);
		
		tfQtdAnimais = new JTextField();
		tfQtdAnimais.setText("0");
		tfQtdAnimais.setEditable(false);
		tfQtdAnimais.setBorder(null);
		tfQtdAnimais.setOpaque(false);
		tfQtdAnimais.setBounds(197, 179, 76, 20);
		frmNovaFazenda.getContentPane().add(tfQtdAnimais);
		tfQtdAnimais.setColumns(10);
		
		tfQtdFuncionarios = new JTextField();
		tfQtdFuncionarios.setText("0");
		tfQtdFuncionarios.setOpaque(false);
		tfQtdFuncionarios.setEditable(false);
		tfQtdFuncionarios.setColumns(10);
		tfQtdFuncionarios.setBorder(null);
		tfQtdFuncionarios.setBounds(197, 210, 76, 20);
		frmNovaFazenda.getContentPane().add(tfQtdFuncionarios);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(510, 92, 190, 150);
		frmNovaFazenda.getContentPane().add(scrollPane_1);
		
		taDescricao = new JTextArea();
		taDescricao.setLineWrap(true);
		taDescricao.setWrapStyleWord(true);
		scrollPane_1.setViewportView(taDescricao);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(NovaFazenda.class.getResource("/img/gradiente_Branco.jpg")));
		label.setBounds(0, 0, 1074, 670);
		frmNovaFazenda.getContentPane().add(label);
		
		menu();
		colocaDadosNaTabela(CrudFazenda.selecionaFazenda(Pergunta.usuario));
	}
	
	void DAOFazenda() {
		fazenda.setNome(tfNome.getText());
		fazenda.setTamanho(tfTamanho.getText());
		fazenda.setEscritura(tfEscritura.getText());
		fazenda.setProprietario(tfProprietario.getText());
		fazenda.setDescricao(taDescricao.getText());
		fazenda.setImg(mI.getImagem(img, panel));
		fazenda.setIdUsuario(Pergunta.usuario.getIdUsuario());
	}
	
	void colocaDadosNaTabela(ResultSet rs) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);
		
		try {
			while (rs.next()) {
				modelo.addRow(new Object[] {rs.getInt("idfazenda"),rs.getString("nome"),rs.getString("tamanho"),rs.getString("escritura"),rs.getString("descri"),
						rs.getString("proprietario")});	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void pegaDadosDaTabela() {
		int linha = table.getSelectedRow();
		
		fazenda.setIdFazenda(Integer.parseInt(table.getValueAt(linha, 0).toString()));
		fazenda.setNome(table.getValueAt(linha, 1).toString());
		fazenda.setTamanho(table.getValueAt(linha, 2).toString());
		fazenda.setEscritura(table.getValueAt(linha, 3).toString());
		fazenda.setDescricao(table.getValueAt(linha, 4).toString());
		fazenda.setProprietario(table.getValueAt(linha, 5).toString());
		
		ResultSet rs = null;
		String sql = "SELECT (img) FROM fazenda WHERE idfazenda=?";
		
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(table.getValueAt(linha, 0).toString()));
			rs = stmt.executeQuery();
			stmt.execute();
			stmt.close();
				
			if(rs.next()) {
				fazenda.setImg(rs.getBytes("img"));
			}
				
			tfNome.setText(fazenda.getNome());
			tfTamanho.setText(fazenda.getTamanho());
			tfEscritura.setText(fazenda.getEscritura());
			taDescricao.setText(fazenda.getDescricao());
			tfProprietario.setText(fazenda.getProprietario());
			contaQtdAnimaisFuncionarios(fazenda.getIdFazenda());
			
			if(fazenda.getImg()!= null) {
				lblImg.setHorizontalAlignment(SwingConstants.LEADING);
				mI.abrirImagem(fazenda.getImg(), null, panel, lblImg, fazenda.getImg());
			}else {
				lblImg.setHorizontalAlignment(SwingConstants.CENTER);
				lblImg.setIcon(new ImageIcon(CadastrarFuncionarios.class.getResource("/img/logo-pequena-sem-texto.png")));
			}
			
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void contaQtdAnimaisFuncionarios(int idFazenda) {
		int qtdAnimais=0;
		ResultSet rsAnimais = null;
		String sqlAnimais = "SELECT quantidade FROM animais where idfazenda=?";
		try {
			PreparedStatement stmtAnimais = Conexao.conexao.prepareStatement(sqlAnimais);
			stmtAnimais.setInt(1, idFazenda);
			rsAnimais = stmtAnimais.executeQuery();
			stmtAnimais.execute();
			stmtAnimais.close();
			
			while (rsAnimais.next()) {
				qtdAnimais += rsAnimais.getInt("quantidade");//faz a soma da quantidade de animais
			}
			tfQtdAnimais.setText(String.valueOf(qtdAnimais));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rsFuncionarios = null;
		String sqlFuncionarios = "SELECT count(nome_fun) AS qtdFuncionarios FROM funcionarios where idfazenda=?";//vai retornar um valor contado a qtd de linhas
		try {
			PreparedStatement stmtFuncionarios = Conexao.conexao.prepareStatement(sqlFuncionarios);
			stmtFuncionarios.setInt(1, idFazenda);
			rsFuncionarios = stmtFuncionarios.executeQuery();
			stmtFuncionarios.execute();
			stmtFuncionarios.close();
			
			if (rsFuncionarios.next()) {
				tfQtdFuncionarios.setText(rsFuncionarios.getString("qtdFuncionarios"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
