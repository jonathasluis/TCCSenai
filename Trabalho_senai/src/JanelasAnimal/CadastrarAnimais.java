package JanelasAnimal;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import DAO.Animal;
import Imagem.MetodosImagem;
import JanelasComtabil.NovaCompra;
import JanelasComtabil.NovaVenda;
import JanelasComtabil.Total;
import JanelasFuncionarios.CadastrarFuncionarios;
import banco.Conexao;
import crud.CrudAnimal;
import crud.CrudFazenda;
import outraJanelas.EnviarEmail;
import outraJanelas.Login;
import outraJanelas.NovaFazenda;
import outraJanelas.Pergunta;
import outraJanelas.Principal;

public class CadastrarAnimais {//teste3

	private JFrame frmCadastroDeAnimais;
	private JTextField tfNomeLote;
	private JTable tabela;
	private JTextField tfDestino;
	private JTextField tfProcurar;
	static JComboBox<String> cbEspecie;
	static JComboBox<String> cbRaca;
	private JFormattedTextField ftfDataNascimento;
	private JFormattedTextField ftfDataCompra;
	private JSpinner spinnerQuantidade;
	private JButton btnProcurar;
	private JRadioButton rdbtnMacho;
	private JRadioButton rdbtnFemea;
	private JPanel panel;
	private File img;
	private MaskFormatter mask;
	protected static int contador=0;//contar quantos cliques
	private MetodosImagem mI = new MetodosImagem();
	private JLabel lblImagem;
	private Animal animal = new Animal();
	private JButton btnLimpar;
	private JScrollPane scrollPane;
	private CrudAnimal crud = new CrudAnimal();
	int contadorParaEditar=0;
	private JButton btnDeletar;
	private JButton btnCancelar;
	private JButton btnSalvar;
	
	//tabela
	static int teste = 1; 
	static int x1=1;
	//tabela
	
	static int indexCbEspecie;
	/**
	 *
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarAnimais window = new CadastrarAnimais();
					window.frmCadastroDeAnimais.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CadastrarAnimais() {
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
		
		frmCadastroDeAnimais = new JFrame();
		frmCadastroDeAnimais.setIconImage(Toolkit.getDefaultToolkit().getImage(CadastrarAnimais.class.getResource("/img/logo-pequena-sem-texto.png")));
		frmCadastroDeAnimais.setTitle("Cadastro de Animais");
		frmCadastroDeAnimais.setBounds(100, 100, 1080, 720);
		frmCadastroDeAnimais.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCadastroDeAnimais.setLocationRelativeTo(null);
		frmCadastroDeAnimais.setResizable(false);
		frmCadastroDeAnimais.getContentPane().setLayout(null);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataDeNascimento.setBounds(10, 100, 126, 20);
		frmCadastroDeAnimais.getContentPane().add(lblDataDeNascimento);
		
		ftfDataNascimento = new JFormattedTextField(mask);
		ftfDataNascimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {//evento de apertar ENTER e mudar de componente
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					ftfDataCompra.requestFocus();
				}
			}
		});
		ftfDataNascimento.setToolTipText("aaaa-mm-dd");
		ftfDataNascimento.setBounds(140, 100, 164, 20);
		frmCadastroDeAnimais.getContentPane().add(ftfDataNascimento);
		
		JLabel lblEspecie = new JLabel("Destino:");
		lblEspecie.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEspecie.setBounds(420, 170, 80, 20);
		frmCadastroDeAnimais.getContentPane().add(lblEspecie);
		
		JLabel lblDataDaCompra = new JLabel("Data da Compra:");
		lblDataDaCompra.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataDaCompra.setBounds(10, 135, 120, 20);
		frmCadastroDeAnimais.getContentPane().add(lblDataDaCompra);
		
		//VARIAVEL PARA PEGAR A DATA DO DIA ATUAL DO SEU COMPUTADOR
		Date data = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		String formatada = formato.format(data);
		
		ftfDataCompra = new JFormattedTextField(mask);//define a mascara
		ftfDataCompra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {//evento de apertar ENTER e mudar de componente
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					spinnerQuantidade.requestFocus();
				}
			}
		});
		ftfDataCompra.setText(formatada);
		ftfDataCompra.setToolTipText("aaaa-mm-dd");
		ftfDataCompra.setBounds(140, 135, 164, 20);
		frmCadastroDeAnimais.getContentPane().add(ftfDataCompra);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantidade.setBounds(420, 65, 80, 20);
		frmCadastroDeAnimais.getContentPane().add(lblQuantidade);
		
		spinnerQuantidade = new JSpinner();
		spinnerQuantidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {//evento de apertar ENTER e mudar de componente
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfDestino.requestFocus();
				}
			}
		});
		spinnerQuantidade.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerQuantidade.setBounds(506, 65, 164, 20);
		frmCadastroDeAnimais.getContentPane().add(spinnerQuantidade);
		
		JLabel label = new JLabel("Especie:");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(420, 100, 80, 20);
		frmCadastroDeAnimais.getContentPane().add(label);
		
		cbEspecie = new JComboBox<String>();
		cbEspecie.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
		cbEspecie.addItemListener(new ItemListener() {//inicio evento mudar item comboBox especie
			public void itemStateChanged(ItemEvent arg0) {
				if(cbEspecie.getItemCount() > 0) {
					cbRaca.removeAllItems();
					ComboBox.comboBoxRaca(ComboBox.pegaIdEspecie(cbEspecie.getSelectedItem().toString()));
				}
			}
		});//fim evento mudar item comboBox especie
		cbEspecie.setBackground(SystemColor.controlHighlight);
		cbEspecie.setBounds(506, 100, 164, 20);
		frmCadastroDeAnimais.getContentPane().add(cbEspecie);
		
		JLabel lblNomeDoLote = new JLabel("Nome do Lote:");
		lblNomeDoLote.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNomeDoLote.setBounds(10, 65, 120, 20);
		frmCadastroDeAnimais.getContentPane().add(lblNomeDoLote);
		
		tfNomeLote = new JTextField();
		tfNomeLote.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {//evento de apertar ENTER e mudar de componente
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					ftfDataNascimento.requestFocus();
				}
			}
		});
		tfNomeLote.setBounds(140, 65, 164, 20);
		frmCadastroDeAnimais.getContentPane().add(tfNomeLote);
		tfNomeLote.setColumns(10);
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(774, 25, 290, 217);
		frmCadastroDeAnimais.getContentPane().add(panel);
		
		lblImagem = new JLabel("");
		lblImagem.setIcon(new ImageIcon(CadastrarAnimais.class.getResource("/img/logo-pequena-sem-texto.png")));
		lblImagem.setToolTipText("Clique 2 vezes");
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//evento de clique para selecionar imagem
				
				if(arg0.getClickCount()==2) {
					img = mI.selecionaImg();
					if(img != null) {
						lblImagem.setHorizontalAlignment(SwingConstants.LEADING);
					}
					mI.abrirImagem(img, img, panel, lblImagem,null);
				}
			}
		});
		panel.setLayout(new CardLayout(0, 0));
		panel.add(lblImagem, "name_6315985644698");
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSexo.setBounds(10, 170, 46, 20);
		frmCadastroDeAnimais.getContentPane().add(lblSexo);
		
		rdbtnMacho = new JRadioButton("Macho");
		rdbtnMacho.setOpaque(false);
		rdbtnMacho.setSelected(true);
		rdbtnMacho.setBounds(62, 170, 68, 23);
		frmCadastroDeAnimais.getContentPane().add(rdbtnMacho);
		
		rdbtnFemea = new JRadioButton("Femea");
		rdbtnFemea.setOpaque(false);
		rdbtnFemea.setBounds(140, 170, 80, 23);
		frmCadastroDeAnimais.getContentPane().add(rdbtnFemea);
		
		JLabel lblRaa = new JLabel("Ra\u00E7a:");
		lblRaa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRaa.setBounds(420, 135, 80, 20);
		frmCadastroDeAnimais.getContentPane().add(lblRaa);
		
		cbRaca = new JComboBox<String>();
		cbRaca.setBackground(SystemColor.controlHighlight);
		cbRaca.setBounds(506, 135, 164, 20);
		frmCadastroDeAnimais.getContentPane().add(cbRaca);
		
		scrollPane =  new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 253, 1054, 23);
		frmCadastroDeAnimais.getContentPane().add(scrollPane);
		
		tabela = new JTable();
		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//evento de clique na tabela
				lblImagem.setIcon(null);
				btnLimpar.doClick();
				pegaDadosDaTabela();
				contadorParaEditar=1;
				btnDeletar.setEnabled(true);
				btnLimpar.setEnabled(false);
			}
		});
		tabela.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Nascimento", "Especie", "Ra\u00E7a", "Sexo", "Destino", "Quantidade", "Data Obten\u00E7\u00E3o", "Fazenda"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabela.getColumnModel().getColumn(0).setResizable(false);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1).setResizable(false);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(2).setResizable(false);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(3).setResizable(false);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(4).setResizable(false);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(5).setResizable(false);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(78);
		tabela.getColumnModel().getColumn(6).setResizable(false);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(7).setResizable(false);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(90);
		tabela.getColumnModel().getColumn(8).setResizable(false);
		tabela.getColumnModel().getColumn(8).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(9).setResizable(false);
		tabela.getColumnModel().getColumn(9).setPreferredWidth(125);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tabela);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfNomeLote.getText().trim().equals("")) {//inicio do tratamento de informa��o para salvar novo animal
					JOptionPane.showMessageDialog(null, "insira um nome", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfNomeLote.requestFocus();
					return;
				}
				if(ftfDataNascimento.getText().contains(" ") || ftfDataNascimento.getText().equals("0000-00-00")){
					JOptionPane.showMessageDialog(null, "insira uma Data de Nascimento v�lida", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					ftfDataNascimento.requestFocus();
					ftfDataNascimento.selectAll();
					return;
				}
				if(ftfDataCompra.getText().contains(" ") || ftfDataCompra.getText().equals("0000-00-00")){
					JOptionPane.showMessageDialog(null, "insira a Data da \"Compra\" v�lida", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					ftfDataCompra.requestFocus();
					ftfDataCompra.selectAll();
					return;
				}
				if(tfDestino.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "insira o Destino", "ALERTA!",JOptionPane.WARNING_MESSAGE);
					tfDestino.requestFocus();
					return;
				}// fim do tratamento de informa��o para salvar novo animal
				
				preencherDAOAnimalParaSalvarNovo();
				
				if(contadorParaEditar==0) {
					crud.addAnimal(animal);
					//tabela
					x1=0;
					if (scrollPane.getHeight()<=339) {
						int x = (teste*16)+scrollPane.getHeight();
						scrollPane.setBounds(10, 253, 1054, x);
					}
					//tabela
					JOptionPane.showMessageDialog(null, "salvo com sucesso!");
					btnLimpar.doClick();
				}
				else if(contadorParaEditar==1) {
					//tabela
					x1=0;
					//tabela
					int resposta = JOptionPane.showConfirmDialog(null, "voce deseja alterar esse(s) Animal? ", "alerta",
							JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(resposta == JOptionPane.YES_OPTION) {
						crud.updAnimal(animal);
						btnCancelar.doClick();
						JOptionPane.showMessageDialog(null, "alterado com sucesso!");
						colocaDadosNaTabela(CrudAnimal.selecionaAnimais(animal));
					}else {
						return;
					}
				}
				colocaDadosNaTabela(CrudAnimal.selecionaAnimais(animal));
				
			}
		});
		btnSalvar.setBackground(SystemColor.controlHighlight);
		btnSalvar.setBounds(972, 602, 89, 23);
		frmCadastroDeAnimais.getContentPane().add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//inicio a��o do bot�o cancelar
				if(contadorParaEditar==0) {
					frmCadastroDeAnimais.dispose();
					Principal.frmPrincipal.setVisible(true);
				}else if(contadorParaEditar==1) {
					contadorParaEditar=0;
					btnDeletar.setEnabled(false);
					btnLimpar.setEnabled(true);
					btnLimpar.doClick();
				}
			}
		});//fim a��o do bot�o cancelar
		btnCancelar.setBackground(SystemColor.controlHighlight);
		btnCancelar.setBounds(774, 602, 89, 23);
		frmCadastroDeAnimais.getContentPane().add(btnCancelar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//inicio da a��o do bot�o limpar
				tfNomeLote.setText(null);
				spinnerQuantidade.setValue(0);
				cbEspecie.setSelectedIndex(0);
				tfDestino.setText(null);
				rdbtnMacho.setSelected(true);
				lblImagem.setIcon(new ImageIcon(CadastrarFuncionarios.class.getResource("/img/logo-pequena-sem-texto.png")));
				lblImagem.setHorizontalAlignment(SwingConstants.CENTER);	
				ftfDataCompra.setValue(null);
				ftfDataNascimento.setValue(null);
			}
		});//fim a��o do bot�o limpar
		btnLimpar.setBackground(SystemColor.controlHighlight);
		btnLimpar.setBounds(675, 602, 89, 23);
		frmCadastroDeAnimais.getContentPane().add(btnLimpar);
		
		btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {//inicio evento botao deletar
			public void actionPerformed(ActionEvent arg0) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja deletar esse dado?", "ALERTA!",
						JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if(resposta==JOptionPane.YES_OPTION) {
					
					btnCancelar.doClick();
					crud.removeAnimal(animal);
					colocaDadosNaTabela(CrudAnimal.selecionaAnimais(animal));
					//tabela
					x1=0;
					if (tabela.getRowCount()<=19) {
						int x = scrollPane.getHeight()-16;
						scrollPane.setBounds(10, 253, 1054, x);
					}
					//tabela
					
					
				}
			}
		});//fim evento botao deletar
		btnDeletar.setEnabled(false);
		btnDeletar.setBackground(SystemColor.controlHighlight);
		btnDeletar.setBounds(873, 602, 89, 23);
		frmCadastroDeAnimais.getContentPane().add(btnDeletar);
		
		JLabel lblAnimais = new JLabel("Animais");
		lblAnimais.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblAnimais.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnimais.setBounds(10, 11, 1054, 25);
		frmCadastroDeAnimais.getContentPane().add(lblAnimais);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnFemea);
		bg.add(rdbtnMacho);
		
		
		tfDestino = new JTextField();
		tfDestino.setBounds(506, 170, 164, 20);
		frmCadastroDeAnimais.getContentPane().add(tfDestino);
		tfDestino.setColumns(10);
		
		tfProcurar = new JTextField();
		tfProcurar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {//evento de apertar ENTER e mudar de componente
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
					btnProcurar.doClick();
				}
			}
		});
		tfProcurar.setBounds(10, 222, 331, 20);
		frmCadastroDeAnimais.getContentPane().add(tfProcurar);
		tfProcurar.setColumns(10);
		
		btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//inicio da a�ao do botao procurar
				//variavel para delimitar o tamanho da tabela
				x1=0;
				//tratamento para almentar a tabela
				colocaDadosNaTabela(CrudAnimal.procuraAnimal(tfProcurar.getText(),animal ));	
				int animal = new Animal().getIdAnimal();
				
				int tabel = tabela.getRowCount();
				int linha = tabel*16;
				int valor = 23+linha;
				scrollPane.setBounds(10, 253, 1054, valor);
				if (!(tfProcurar.getText()).trim().equals("")) {
					if (tabela.getRowCount()==0) {
						int x2 = 23;
						scrollPane.setBounds(10, 253, 1054, x2);
					}
				}
				
			}
		});//fim da a�ao do botao procurar
		btnProcurar.setBackground(SystemColor.controlHighlight);
		btnProcurar.setBounds(351, 222, 89, 20);
		frmCadastroDeAnimais.getContentPane().add(btnProcurar);
		
		JButton btnCadastrarNovaEspecie = new JButton("...");
		btnCadastrarNovaEspecie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//a�ao do botao cadastrar especie
				if(CadastrarEspecie.limit == 0) {
					CadastrarEspecie.main(null);
					CadastrarEspecie.limit = 1;
				}else {
					CadastrarEspecie.frmNovaEspecie.requestFocus();
				}
			}
		});
		btnCadastrarNovaEspecie.setBackground(SystemColor.controlHighlight);
		btnCadastrarNovaEspecie.setToolTipText("Cadastrar nova Especie");
		btnCadastrarNovaEspecie.setBounds(675, 100, 20, 20);
		frmCadastroDeAnimais.getContentPane().add(btnCadastrarNovaEspecie);
		
		JButton btnCadastrarNovaRaca = new JButton("...");
		btnCadastrarNovaRaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				indexCbEspecie = cbEspecie.getSelectedIndex();
				if(NovaRaca.limit == 0) {
					NovaRaca.main(null);
					NovaRaca.limit = 1;
				}else {
					NovaRaca.frmNovaRaca.requestFocus();
				}
				
			}
		});
		btnCadastrarNovaRaca.setToolTipText("Cadastrar nova Ra\u00E7a");
		btnCadastrarNovaRaca.setBackground(SystemColor.controlHighlight);
		btnCadastrarNovaRaca.setBounds(675, 135, 20, 20);
		frmCadastroDeAnimais.getContentPane().add(btnCadastrarNovaRaca);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(CadastrarAnimais.class.getResource("/img/gradiente_Branco.jpg")));
		label_1.setBounds(0, 0, 1074, 670);
		frmCadastroDeAnimais.getContentPane().add(label_1);
		
		
		//tabela
		x1=1;
		//tabela
		menu();
		new ComboBox().comboBoxEspecie();
		animal.setIdFazenda(Principal.fazenda.getIdFazenda());
		colocaDadosNaTabela(CrudAnimal.selecionaAnimais(animal));	
		
		//tabela
		//IF PARA VERIFICAR SE A TABLE ESTIVER VAZIA E DEIXAR VISIBLE.(FALSE)
		if (tabela.getRowCount()== 0) {
			scrollPane.setVisible(false);
		}
		//tabela
	
				
				
	}//fim do inicialize
	
	void preencherDAOAnimalParaSalvarNovo() {
		animal.setNomeLote(tfNomeLote.getText());
		animal.setDataDeNascimento(ftfDataNascimento.getText());
		animal.setDataCompra(ftfDataCompra.getText());
		animal.setQuantidade((int) spinnerQuantidade.getValue());
		animal.setRaca(ComboBox.pegaIdRaca(cbRaca.getSelectedItem().toString()));
		animal.setIdFazenda(Principal.fazenda.getIdFazenda());
		animal.setDestino(tfDestino.getText());
		animal.setImagem(mI.getImagem(img, panel));
		if(rdbtnMacho.isSelected())
			animal.setSexo("M");
		else
			animal.setSexo("F");
	}
	
	void colocaDadosNaTabela(ResultSet rs) {
		animal.setIdFazenda(Principal.fazenda.getIdFazenda());
		String sexo;
		//tabela
		scrollPane.setVisible(true);
		//tabela
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setNumRows(0);
		
		try {
			while (rs.next()) {
				ResultSet rs2 = new CrudFazenda().selecionaFazendaEspecifica(rs.getInt("idfazenda"));
				String fazenda=null;
				
				
				if(rs2.next())
					fazenda = rs2.getString("nome");
				
				if( rs.getString("sexo").equalsIgnoreCase("m"))
					sexo = "Masculino";
				else
					sexo="Feminino";
				modelo.addRow(new Object[] {rs.getInt("idanimal"),rs.getString("nomelote"),rs.getString("datadenascimento"),
						ComboBox.pegaNomeEspecie(rs.getInt("raca")),ComboBox.pegaNomeRaca(rs.getInt("raca")),sexo,rs.getString("destino"),rs.getString("quantidade"),
						rs.getString("datacompra"),fazenda});
				//tabela
				if (x1==1) {
					if (tabela.getRowCount() >= teste & scrollPane.getHeight()<=339) {
						teste=1;
						teste=+1;
						int x = (teste*16)+scrollPane.getHeight();
						scrollPane.setBounds(10, 253, 1054, x);
					}
				}
				//tabela
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void pegaDadosDaTabela() {
		int linha = tabela.getSelectedRow();
		
		animal.setIdAnimal(Integer.parseInt(tabela.getValueAt(linha, 0).toString()));
		animal.setNomeLote(tabela.getValueAt(linha, 1).toString());
		animal.setDataDeNascimento(tabela.getValueAt(linha, 2).toString());
		animal.setRaca(ComboBox.pegaIdRaca(tabela.getValueAt(linha, 4).toString()));
		animal.setSexo(tabela.getValueAt(linha, 5).toString());
		animal.setDestino(tabela.getValueAt(linha, 6).toString());
		animal.setQuantidade(Integer.parseInt(tabela.getValueAt(linha, 7).toString()));
		animal.setDataCompra(tabela.getValueAt(linha, 8).toString());
		animal.setIdAnimal(Integer.parseInt(tabela.getValueAt(linha, 0).toString()));
		
		
		
		ResultSet dados1 = null;
		String sql = "SELECT (img) FROM animais WHERE idanimal=?";
		
			try {
				PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
				stmt.setInt(1, Integer.parseInt(tabela.getValueAt(linha, 0).toString()));
				dados1 = stmt.executeQuery();
				stmt.execute();
				stmt.close();
				
				if(dados1.next()) {
					animal.setImagem(dados1.getBytes("img"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			tfNomeLote.setText(animal.getNomeLote());
			ftfDataNascimento.setText(animal.getDataDeNascimento());
			ftfDataCompra.setText(animal.getDataCompra());
			spinnerQuantidade.setValue(animal.getQuantidade());
			tfDestino.setText(animal.getDestino());
			cbEspecie.setSelectedItem(ComboBox.pegaNomeEspecie(animal.getRaca()));
			cbRaca.setSelectedItem(ComboBox.pegaNomeRaca(animal.getRaca()));	
			
			if(animal.getSexo().equalsIgnoreCase("masculino")) {
				rdbtnMacho.setSelected(true);
			}
			
			if(animal.getSexo().equalsIgnoreCase("feminino")) {
				rdbtnFemea.setSelected(true);
			}
			
			if(animal.getImagem()!= null) {
				lblImagem.setHorizontalAlignment(SwingConstants.LEADING);
				mI.abrirImagem(animal.getImagem(), null, panel, lblImagem,animal.getImagem());
			}else {
				lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
				lblImagem.setIcon(new ImageIcon(CadastrarFuncionarios.class.getResource("/img/logo-pequena-sem-texto.png")));
			}
	}

	public void menu() {	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(128, 128, 128), new Color(128, 128, 128), new Color(128, 128, 128), new Color(105, 105, 105)));
		menuBar.setForeground(Color.GREEN);
		menuBar.setBackground(Color.DARK_GRAY);
		frmCadastroDeAnimais.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("");
		mnInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnInicio.setOpaque(true);
		mnInicio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnInicio.setIcon(new ImageIcon(Principal.class.getResource("/img/Icone_Inicio.png")));
		mnInicio.setForeground(Color.WHITE);
		mnInicio.setBackground(Color.DARK_GRAY);
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmCadastroDeAnimais.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("");
		mnNewMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.setIcon(new ImageIcon(Principal.class.getResource("/img/Icone_GEstao.png")));
		mnNewMenu.setOpaque(true);
		mnNewMenu.setFocusPainted(true);
		mnNewMenu.setBorder(new CompoundBorder());
		mnNewMenu.setBackground(Color.DARK_GRAY);
		mnNewMenu.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Animais");
		mntmCadastrarAnimais.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmCadastrarAnimais.setBorder(new LineBorder(new Color(34, 139, 34)));
		mntmCadastrarAnimais.setOpaque(true);
		mntmCadastrarAnimais.setForeground(new Color(0, 0, 0));
		mntmCadastrarAnimais.setBackground(SystemColor.menu);
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmCadastroDeAnimais.dispose();
			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Funcionarios");
		mntmCadastrarFuncionarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.add(mntmCadastrarFuncionarios);
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmCadastroDeAnimais.dispose();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("");
		mnNewMenu_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.setIcon(new ImageIcon(Principal.class.getResource("/img/Icone_Financeiro.png")));
		mnNewMenu_1.setForeground(Color.WHITE);
		mnNewMenu_1.setBackground(Color.DARK_GRAY);
		mnNewMenu_1.setOpaque(true);
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCompra = new JMenuItem("Compra");
		mntmCompra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmCompra.setEnabled(true);
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frmCadastroDeAnimais.dispose();
			}
		});
		mnNewMenu_1.add(mntmCompra);
		
		JMenuItem mntmNovaVenda = new JMenuItem("Venda");
		mntmNovaVenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.add(mntmNovaVenda);
		
		JMenuItem mntmTotal = new JMenuItem("Total");
		mntmTotal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu_1.add(mntmTotal);
		mntmTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Total.main(null);
				frmCadastroDeAnimais.dispose();
			}
		});
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmCadastroDeAnimais.dispose();
			}
		});
		
		JMenu mnOpes = new JMenu("");
		mnOpes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOpes.setIcon(new ImageIcon(Principal.class.getResource("/img/Icone_OPCAO.png")));
		mnOpes.setForeground(Color.WHITE);
		mnOpes.setBackground(Color.DARK_GRAY);
		mnOpes.setOpaque(true);
		menuBar.add(mnOpes);
		
		JMenuItem mntmNovaFazenda = new JMenuItem("Fazenda");
		mntmNovaFazenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmNovaFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaFazenda.main(null);
				frmCadastroDeAnimais.dispose();
			}
		});
		mnOpes.add(mntmNovaFazenda);
		
		JMenuItem mntmSada = new JMenuItem("Sair");
		mntmSada.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmMudarFazenda = new JMenuItem("Mudar Fazenda");
		mntmMudarFazenda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmMudarFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pergunta.main(null);
				Pergunta.contador = 1;
			}
		});
		mnOpes.add(mntmMudarFazenda);
		
		JMenuItem mntmDeslogar = new JMenuItem("Deslogar");
		mntmDeslogar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmCadastroDeAnimais.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		
		JMenuItem mntmEnviar = new JMenuItem("Enviar feedback");
		mntmEnviar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frmPrincipal.dispose();
				EnviarEmail.main(null);
			}
		});
		mnOpes.add(mntmEnviar);
		
		mnOpes.add(mntmSada);
		frmCadastroDeAnimais.getContentPane().setLayout(null);
	}
}
