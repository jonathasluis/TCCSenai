package JanelasFuncionarios;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import DAO.Funcionario;
import Imagem.MetodosImagem;
import JanelasAnimal.CadastrarAnimais;
import JanelasComtabil.NovaCompra;
import JanelasComtabil.NovaVenda;
import JanelasComtabil.Total;
import banco.Conexao;
import crud.CrudFazenda;
import crud.CrudFuncionarios;
import outraJanelas.NovaFazenda;
import outraJanelas.Pergunta;
import outraJanelas.Principal;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CadastrarFuncionarios {

	private JFrame frmCadastrarFuncionarios;
	public  File img;
	private JTextField tfProcurar;
	private JTable tabela;
	private JTextField tfNome;
	private JTextField tfRg;
	private JRadioButton rdbtnMasculino;
	private JFormattedTextField ftfNascimento;
	private JTextField tfEmail;
	private JTextField tfCargo;
	private JTextField tfSalario;
	private JFormattedTextField ftfTelefone;
	private JButton btnLimpar;
	private JPanel panel;
	private JLabel lblImagem;
	private JButton btnDeletar;
	private JButton btnCancelar;
	private JFormattedTextField ftfCpf;
	private MetodosImagem mI = new MetodosImagem();
	private int contador=0;//contar quantos cliques
	private MaskFormatter maskaraData, maskaraTelefone,maskaraCpf;
	private JRadioButton rdbtnFeminino;
	private JRadioButton rdbtnAtivo;
	private JRadioButton rdbtnDesligado;
	private CrudFuncionarios funcionario = new CrudFuncionarios();
	private Funcionario DAOFuncionario = new Funcionario();
	int contadorParaEditar = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarFuncionarios window = new CadastrarFuncionarios();
					window.frmCadastrarFuncionarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CadastrarFuncionarios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {//inicio formatação mascara
			maskaraData = new MaskFormatter("####-##-##");
			maskaraTelefone = new MaskFormatter("(##) ####-####");
			maskaraCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//fim formatação mascara
		
		frmCadastrarFuncionarios = new JFrame();
		frmCadastrarFuncionarios.setTitle("Cadastrar Funcionarios");
		frmCadastrarFuncionarios.setBounds(100, 100, 1080, 720);
		frmCadastrarFuncionarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCadastrarFuncionarios.setLocationRelativeTo(null);
		frmCadastrarFuncionarios.setResizable(false);
		frmCadastrarFuncionarios.getContentPane().setLayout(null);
		
		JLabel lblFuncionarios = new JLabel("Funcionarios");
		lblFuncionarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuncionarios.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblFuncionarios.setBounds(10, 11, 1054, 25);
		frmCadastrarFuncionarios.getContentPane().add(lblFuncionarios);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(774, 25, 290, 217);
		frmCadastrarFuncionarios.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		lblImagem = new JLabel("");
		lblImagem.setIcon(new ImageIcon(CadastrarFuncionarios.class.getResource("/img/logo-pequena-sem-texto.png")));
		lblImagem.setToolTipText("Clique 2 vezes");
		lblImagem.addMouseListener(new MouseAdapter() {//inicio evento para escolher e abrir imagem
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contador++;
				new Thread(thread).start();	
				if(contador == 2) {
					img = mI.selecionaImg();
					if(img != null) {
						lblImagem.setHorizontalAlignment(SwingConstants.LEADING);
					}
					mI.abrirImagem(img, img, panel, lblImagem,null);
					contador=0;
				}else {
					return;
				}
			}// fim evento para escolher e abrir imagem
		});
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblImagem, "name_12082521761208");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 253, 1054, 373);
		frmCadastrarFuncionarios.getContentPane().add(scrollPane);
		
		tabela = new JTable();
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblImagem.setIcon(null);
				contadorParaEditar=1;
				btnLimpar.setEnabled(false);
				btnDeletar.setEnabled(true);
				pegaDadosDaTabela();
			}
		});
		tabela.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "Nascimento", "CPF", "RG", "Sexo", "Telefone", "Email", "Cargo", "Salario", "Fazenda", "Status"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabela.getColumnModel().getColumn(0).setResizable(false);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(70);
		tabela.getColumnModel().getColumn(1).setResizable(false);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setResizable(false);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(83);
		tabela.getColumnModel().getColumn(3).setResizable(false);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(95);
		tabela.getColumnModel().getColumn(4).setResizable(false);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(85);
		tabela.getColumnModel().getColumn(5).setResizable(false);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(6).setResizable(false);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(90);
		tabela.getColumnModel().getColumn(7).setResizable(false);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(8).setResizable(false);
		tabela.getColumnModel().getColumn(8).setPreferredWidth(90);
		tabela.getColumnModel().getColumn(9).setResizable(false);
		tabela.getColumnModel().getColumn(9).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(10).setResizable(false);
		tabela.getColumnModel().getColumn(10).setPreferredWidth(90);
		tabela.getColumnModel().getColumn(11).setResizable(false);
		tabela.getColumnModel().getColumn(11).setPreferredWidth(70);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tabela);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfNome.getText().trim().equals("")) { //inicio do tratamento de informação para salvar novo funcionario
					JOptionPane.showMessageDialog(null, "insira um nome", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfNome.requestFocus();
					return;
				}
				if(ftfNascimento.getText().contains(" ")){
					JOptionPane.showMessageDialog(null, "insira uma Data de Nascimento", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					ftfNascimento.requestFocus();
					return;
				}
				if(ftfCpf.getText().contains(" ")) {
					JOptionPane.showMessageDialog(null, "insira o CPF", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					ftfCpf.requestFocus();
					return;
				}
				if(tfRg.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "insira o RG", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfRg.requestFocus();
					return;
				}
				if(tfCargo.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "insira o cargo", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfCargo.requestFocus();
					return;
				}
				if(tfSalario.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "insira o salario", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfSalario.requestFocus();
					return;
				}//fim do tratamento de informação para salvar novo funcionario
				
				preencherDAOFuncionarioParaSalvarNovo();
				if(contadorParaEditar==0) {
					funcionario.addFun(DAOFuncionario);
					btnLimpar.doClick();
					JOptionPane.showMessageDialog(null, "salvo com sucesso!");
				}
				if(contadorParaEditar==1) {
					int resposta = JOptionPane.showConfirmDialog(null, "voce deseja alterar esse Funcionario? ", "alerta",
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(resposta==JOptionPane.YES_OPTION) {
						funcionario.updFun(DAOFuncionario);
						btnCancelar.doClick();
						JOptionPane.showMessageDialog(null, "Funcionario alterado com sucesso!");
					}else {
						return;
					}
					
				}
				colocaDadosNaTabela(CrudFuncionarios.selecionaFuncionario(DAOFuncionario));
			}
		});
		btnSalvar.setBackground(SystemColor.controlHighlight);
		btnSalvar.setBounds(975, 636, 89, 23);
		frmCadastrarFuncionarios.getContentPane().add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//evento do botao limpar
				tfNome.setText(null);
				ftfNascimento.setValue(null);
				ftfCpf.setValue(null);
				tfRg.setText(null);
				rdbtnMasculino.setSelected(true);
				ftfTelefone.setValue(null);
				tfCargo.setText(null);
				tfSalario.setText(null);
				tfEmail.setText(null);
				rdbtnAtivo.setSelected(true);
				lblImagem.setIcon(new ImageIcon(CadastrarFuncionarios.class.getResource("/img/logo-pequena-sem-texto.png")));
				lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
			}
		});//fim evento botao limpar
		btnLimpar.setBackground(SystemColor.controlHighlight);
		btnLimpar.setBounds(678, 636, 89, 23);
		frmCadastrarFuncionarios.getContentPane().add(btnLimpar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//inicio do evento do botao cancelar
				if(contadorParaEditar==0) {
					Principal.frmPrincipal.setVisible(true);
					frmCadastrarFuncionarios.dispose();
				}
				if(contadorParaEditar==1) {
					contadorParaEditar=0;
					btnDeletar.setEnabled(false);
					btnLimpar.setEnabled(true);
					btnLimpar.doClick();
				}
			}
		});//fim do evento do botao cancelar
		btnCancelar.setBackground(SystemColor.controlHighlight);
		btnCancelar.setBounds(777, 636, 89, 23);
		frmCadastrarFuncionarios.getContentPane().add(btnCancelar);
		
		btnDeletar = new JButton("Deletar");//inicio do evento do botao deletar
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja deletar esse dado?", "ALERTA!",
						JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if(resposta==JOptionPane.YES_OPTION) {
					funcionario.removeFun(DAOFuncionario);
					colocaDadosNaTabela(CrudFuncionarios.selecionaFuncionario(DAOFuncionario));
				}
			}
		});//fim do evento do botao deletar
		btnDeletar.setEnabled(false);
		btnDeletar.setBackground(SystemColor.controlHighlight);
		btnDeletar.setBounds(876, 636, 89, 23);
		frmCadastrarFuncionarios.getContentPane().add(btnDeletar);
		
		tfProcurar = new JTextField();
		tfProcurar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==e.VK_ENTER) {
					btnProcurar.doClick();
				}
			}
		});
		tfProcurar.setColumns(10);
		tfProcurar.setBounds(10, 222, 331, 20);
		frmCadastrarFuncionarios.getContentPane().add(tfProcurar);
		
		btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFuncionario.setIdFazenda(Principal.fazenda.getIdFazenda());
				if(tfProcurar.getText().trim().equals("")) {
					colocaDadosNaTabela(CrudFuncionarios.selecionaFuncionario(DAOFuncionario));
				}else {
					colocaDadosNaTabela(CrudFuncionarios.procurafuncionario(tfProcurar.getText(), DAOFuncionario));
				}
			}
		});
		btnProcurar.setBackground(SystemColor.controlHighlight);
		btnProcurar.setBounds(351, 222, 89, 20);
		frmCadastrarFuncionarios.getContentPane().add(btnProcurar);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(10, 60, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblNome);
		
		JLabel lblNascimento = new JLabel("Nascimento:");
		lblNascimento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNascimento.setBounds(10, 91, 80, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblNascimento);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCpf.setBounds(10, 122, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblCpf);
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRg.setBounds(10, 153, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblRg);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSexo.setBounds(10, 184, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblSexo);
		
		tfNome = new JTextField();
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==e.VK_ENTER) {
					ftfNascimento.requestFocus();
				}
			}
		});
		tfNome.setColumns(10);
		tfNome.setBounds(89, 60, 174, 20);
		frmCadastrarFuncionarios.getContentPane().add(tfNome);
		
		tfRg = new JTextField();
		tfRg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==e.VK_ENTER) {
					ftfTelefone.requestFocus();
				}
			}
		});
		tfRg.setColumns(10);
		tfRg.setBounds(89, 153, 174, 20);
		frmCadastrarFuncionarios.getContentPane().add(tfRg);
		
		ftfNascimento = new JFormattedTextField(maskaraData);
		ftfNascimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==e.VK_ENTER) {
					ftfCpf.requestFocus();
				}
			}
		});
		ftfNascimento.setToolTipText("aaaa/mm/dd");
		ftfNascimento.setBounds(89, 92, 174, 20);
		frmCadastrarFuncionarios.getContentPane().add(ftfNascimento);
		
		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setSelected(true);
		rdbtnMasculino.setBounds(89, 184, 83, 23);
		frmCadastrarFuncionarios.getContentPane().add(rdbtnMasculino);
		
		rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setBounds(174, 184, 109, 23);
		frmCadastrarFuncionarios.getContentPane().add(rdbtnFeminino);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefone.setBounds(420, 60, 70, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblTelefone);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(420, 91, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblEmail);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCargo.setBounds(420, 122, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblCargo);
		
		JLabel lblSalario = new JLabel("Salario:");
		lblSalario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSalario.setBounds(420, 153, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblSalario);
		
		JLabel lblDesligada = new JLabel("Status:");
		lblDesligada.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDesligada.setBounds(420, 184, 60, 20);
		frmCadastrarFuncionarios.getContentPane().add(lblDesligada);
		
		rdbtnAtivo = new JRadioButton("Ativo");
		rdbtnAtivo.setSelected(true);
		rdbtnAtivo.setBounds(500, 184, 60, 23);
		frmCadastrarFuncionarios.getContentPane().add(rdbtnAtivo);
		
		rdbtnDesligado = new JRadioButton("Desligado");
		rdbtnDesligado.setBounds(556, 184, 83, 23);
		frmCadastrarFuncionarios.getContentPane().add(rdbtnDesligado);
		
		ftfTelefone = new JFormattedTextField(maskaraTelefone);
		ftfTelefone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==e.VK_ENTER) {
					tfEmail.requestFocus();
				}
			}
		});
		ftfTelefone.setBounds(500, 61, 174, 20);
		frmCadastrarFuncionarios.getContentPane().add(ftfTelefone);
		
		tfEmail = new JTextField();
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==e.VK_ENTER) {
					tfCargo.requestFocus();
				}
			}
		});
		tfEmail.setColumns(10);
		tfEmail.setBounds(500, 92, 174, 20);
		frmCadastrarFuncionarios.getContentPane().add(tfEmail);
		
		tfCargo = new JTextField();
		tfCargo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==e.VK_ENTER) {
					tfSalario.requestFocus();
				}
			}
		});
		tfCargo.setColumns(10);
		tfCargo.setBounds(500, 123, 174, 20);
		frmCadastrarFuncionarios.getContentPane().add(tfCargo);
		
		tfSalario = new JTextField();
		tfSalario.setColumns(10);
		tfSalario.setBounds(500, 154, 174, 20);
		frmCadastrarFuncionarios.getContentPane().add(tfSalario);
		
		ButtonGroup bgSexo = new ButtonGroup();
		bgSexo.add(rdbtnMasculino);
		bgSexo.add(rdbtnFeminino);
		
		ButtonGroup bgStatus = new ButtonGroup();
		bgStatus.add(rdbtnAtivo);
		bgStatus.add(rdbtnDesligado);
		
		ftfCpf = new JFormattedTextField(maskaraCpf);
		ftfCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==e.VK_ENTER) {
					tfRg.requestFocus();
				}
			}
		});
		ftfCpf.setBounds(89, 122, 174, 20);
		frmCadastrarFuncionarios.getContentPane().add(ftfCpf);
		
		menu();
		DAOFuncionario.setIdFazenda(Principal.fazenda.getIdFazenda());
		colocaDadosNaTabela(CrudFuncionarios.selecionaFuncionario(DAOFuncionario));
	}
	
	void preencherDAOFuncionarioParaSalvarNovo() {
		DAOFuncionario.setNome(tfNome.getText());
		DAOFuncionario.setDataDeNascimento(ftfNascimento.getText());
		DAOFuncionario.setCpf(ftfCpf.getText());
		DAOFuncionario.setRg(tfRg.getText());
		DAOFuncionario.setTelefone(ftfTelefone.getText());
		DAOFuncionario.setEmail(tfEmail.getText());
		DAOFuncionario.setCargo(tfCargo.getText());
		DAOFuncionario.setSalario(Float.parseFloat(tfSalario.getText()));
		DAOFuncionario.setIdFazenda(Principal.fazenda.getIdFazenda());
		DAOFuncionario.setImg(mI.getImagem(img, panel));
		
		if(rdbtnMasculino.isSelected()) {
			DAOFuncionario.setSexo("M");
		}else {
			DAOFuncionario.setSexo("F");
		}
		
		if(rdbtnAtivo.isSelected()) {
			DAOFuncionario.setStatus("A");
		}else
			DAOFuncionario.setStatus("D");
	}
	
	void colocaDadosNaTabela(ResultSet rs) {
		DAOFuncionario.setIdFazenda(Principal.fazenda.getIdFazenda());
		String sexo;
		
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		
		try {
			while (rs.next()) {
				ResultSet rs2 = new CrudFazenda().selecionaFazendaEspecifica(rs.getInt("idfazenda"));
				String fazenda=null;
				String status=null;
				
				if(rs2.next())
					fazenda = rs2.getString("nome");
				
				if( rs.getString("sexo").equalsIgnoreCase("m"))
					sexo = "Masculino";
				else
					sexo="Feminino";
				
				if(rs.getString("status").equalsIgnoreCase("a")) {
					status="Ativo";
				}else {
					status="desligado";
				}
					
				modelo.addRow(new Object[] {rs.getInt("idfuncionarios"),rs.getString("nome_fun"),rs.getString("data_nasc"),
						rs.getString("cpf_fun"),rs.getString("rg_fun"),sexo,rs.getString("fone_fun"),rs.getString("email_fun"),
						rs.getString("cargo"),rs.getString("salario"),fazenda,status});
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void pegaDadosDaTabela() {
		int linha = tabela.getSelectedRow();
		
		DAOFuncionario.setIdFuncionario(Integer.parseInt(tabela.getValueAt(linha, 0).toString()));
		DAOFuncionario.setNome(tabela.getValueAt(linha, 1).toString());
		DAOFuncionario.setDataDeNascimento(tabela.getValueAt(linha, 2).toString());
		DAOFuncionario.setCpf(tabela.getValueAt(linha, 3).toString());
		DAOFuncionario.setRg(tabela.getValueAt(linha, 4).toString());
		DAOFuncionario.setSexo(tabela.getValueAt(linha, 5).toString());
		DAOFuncionario.setTelefone(tabela.getValueAt(linha, 6).toString());
		DAOFuncionario.setEmail(tabela.getValueAt(linha, 7).toString());
		DAOFuncionario.setCargo(tabela.getValueAt(linha, 8).toString());
		DAOFuncionario.setSalario(Float.parseFloat(tabela.getValueAt(linha, 9).toString()));
		DAOFuncionario.setStatus(tabela.getValueAt(linha, 11).toString());
		
		ResultSet dados1 = null;
		String sql = "SELECT (img) FROM funcionarios WHERE idfuncionarios=?";
		
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(tabela.getValueAt(linha, 0).toString()));
			dados1 = stmt.executeQuery();
			stmt.execute();
			stmt.close();
				
			if(dados1.next()) {
				DAOFuncionario.setImg(dados1.getBytes("img"));
			}
				
		} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		tfNome.setText(DAOFuncionario.getNome());
		ftfNascimento.setText(DAOFuncionario.getDataDeNascimento());
		ftfCpf.setText(DAOFuncionario.getCpf());
		tfRg.setText(DAOFuncionario.getRg());
		ftfTelefone.setText(DAOFuncionario.getTelefone());
		tfEmail.setText(DAOFuncionario.getEmail());
		tfCargo.setText(DAOFuncionario.getCargo());
		tfSalario.setText(String.valueOf(DAOFuncionario.getSalario()));
		
		if(DAOFuncionario.getSexo().equalsIgnoreCase("masculino")) {
			rdbtnMasculino.setSelected(true);
		}else if(DAOFuncionario.getSexo().equalsIgnoreCase("feminino")){
			rdbtnFeminino.setSelected(true);
		}
		
		if (DAOFuncionario.getStatus().equalsIgnoreCase("ativo")) {
			rdbtnAtivo.setSelected(true);
		}if(DAOFuncionario.getStatus().equalsIgnoreCase("desligado"))
			rdbtnDesligado.setSelected(true);
		
		if(DAOFuncionario.getImg()!= null) {
			lblImagem.setHorizontalAlignment(SwingConstants.LEADING);
			mI.abrirImagem(DAOFuncionario.getImg(), null, panel, lblImagem, DAOFuncionario.getImg());
		}else {
			lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
			lblImagem.setIcon(new ImageIcon(CadastrarFuncionarios.class.getResource("/img/logo-pequena-sem-texto.png")));
		}
		
			
		
	}
	
	Runnable thread = new Runnable() {	//inicio da tarefa paralela de contar cliques
		@Override
		public void run() {
			try {
				while(true) {
					Thread.sleep(1500);
					contador=0;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};//fim da tarefa paralela de contar cliques
	private JButton btnProcurar;
	
	void menu() {
		JMenuBar menuBar = new JMenuBar();
		frmCadastrarFuncionarios.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmCadastrarFuncionarios.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("Animais");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Cadastrar Animais");
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmCadastrarFuncionarios.dispose();
			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenu mnFuncionarios = new JMenu("Funcionarios");
		menuBar.add(mnFuncionarios);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Cadastrar funcionarios");
		mntmCadastrarFuncionarios.setEnabled(false);
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmCadastrarFuncionarios.dispose();
			}
		});
		mnFuncionarios.add(mntmCadastrarFuncionarios);
		
		JMenu mnNewMenu_1 = new JMenu("Compra de Insumos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCompra = new JMenuItem("Nova Compra");
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frmCadastrarFuncionarios.dispose();
			}
		});
		mnNewMenu_1.add(mntmCompra);
		
		JMenu mnNewMenu_2 = new JMenu("Vendas");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNovaVenda = new JMenuItem("Nova Venda");
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmCadastrarFuncionarios.dispose();
			}
		});
		mnNewMenu_2.add(mntmNovaVenda);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatrio);
		
		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnOpes);
		
		JMenuItem mntmTotal = new JMenuItem("Total");
		mntmTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Total.main(null);
				frmCadastrarFuncionarios.dispose();
			}
		});
		mnRelatrio.add(mntmTotal);
		
		JMenuItem mntmNovaFazenda = new JMenuItem("Nova fazenda");
		mntmNovaFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaFazenda.main(null);
				frmCadastrarFuncionarios.dispose();
			}
		});
		mnOpes.add(mntmNovaFazenda);
		
		JMenuItem mntmSada = new JMenuItem("Sair");
		mntmSada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnOpes.add(mntmSada);
		
		JMenuItem mntmMudarFazenda = new JMenuItem("Mudar Fazenda");
		mntmMudarFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pergunta.main(null);
			}
		});
		mnOpes.add(mntmMudarFazenda);
	}
}
