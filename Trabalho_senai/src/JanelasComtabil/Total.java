package JanelasComtabil;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import DAO.Compras;
import DAO.Vendas;
import JanelasAnimal.CadastrarAnimais;
import JanelasFuncionarios.CadastrarFuncionarios;
import crud.CrudCompras;
import crud.CrudVendas;
import outraJanelas.EnviarEmail;
import outraJanelas.Login;
import outraJanelas.NovaFazenda;
import outraJanelas.Pergunta;
import outraJanelas.Principal;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

public class Total {//

	private JFrame frmRelattio;
	private JLabel valorGasto;
	private JLabel valorReceita;
	private JLabel label;
	private Vendas vendas = new Vendas();
	private Compras compras = new Compras();
	private JTable tabelaGasto;
	private JTable tabelaReceita;
	private JScrollPane scrollPaneGasto;
	private JScrollPane scrollPaneReceita;
	private JCheckBox chckbxAno;
	private JCheckBox chckbxAnomes;
	private JCheckBox chckbxAnomesdia;
	private JCalendar calendar;
	Date data = new Date();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Total window = new Total();
					window.frmRelattio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Total() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmRelattio = new JFrame();
		frmRelattio.setTitle("Relat\u00F3tio");
		frmRelattio.setIconImage(Toolkit.getDefaultToolkit().getImage(Total.class.getResource("/img/logo-pequena-sem-texto.png")));
		frmRelattio.setBounds(100, 100, 1080, 720);
		frmRelattio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRelattio.setResizable(false);
		frmRelattio.setLocationRelativeTo(null);
		
		
		frmRelattio.getContentPane().setLayout(null);
		
		ImageIcon icon = new ImageIcon("src/img/relatorio.png");
		icon.setImage(icon.getImage().getScaledInstance(60, 58, 100));
		JLabel lblIcone = new JLabel("");
		lblIcone.setBounds(941, 11, 57, 55);
		lblIcone.setIcon(icon);
		frmRelattio.getContentPane().add(lblIcone);
		
		JLabel lblRelatorioContabil = new JLabel("Relat\u00F3rio Cont\u00E1bil");
		lblRelatorioContabil.setBounds(10, 11, 1054, 25);
		lblRelatorioContabil.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatorioContabil.setFont(new Font("Tahoma", Font.BOLD, 25));
		frmRelattio.getContentPane().add(lblRelatorioContabil);
		
		JLabel lblTotalGasto = new JLabel("Total Gasto");
		lblTotalGasto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalGasto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalGasto.setBounds(10, 219, 519, 20);
		frmRelattio.getContentPane().add(lblTotalGasto);
		
		JLabel lblReceita = new JLabel("Receita");
		lblReceita.setHorizontalAlignment(SwingConstants.CENTER);
		lblReceita.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReceita.setBounds(545, 219, 519, 20);
		frmRelattio.getContentPane().add(lblReceita);
		
		valorGasto = new JLabel("");
		valorGasto.setForeground(Color.BLACK);
		valorGasto.setFont(new Font("Tahoma", Font.BOLD, 20));
		valorGasto.setHorizontalAlignment(SwingConstants.TRAILING);
		valorGasto.setBounds(403, 518, 126, 25);
		frmRelattio.getContentPane().add(valorGasto);
		
		valorReceita = new JLabel("");
		valorReceita.setFont(new Font("Tahoma", Font.BOLD, 20));
		valorReceita.setForeground(Color.BLACK);
		valorReceita.setHorizontalAlignment(SwingConstants.TRAILING);
		valorReceita.setBounds(938, 518, 126, 25);
		frmRelattio.getContentPane().add(valorReceita);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setForeground(new Color(0, 0, 0));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotal.setBounds(10, 609, 104, 51);
		frmRelattio.getContentPane().add(lblTotal);
		
		label = new JLabel("0.0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(403, 609, 350, 51);
		frmRelattio.getContentPane().add(label);
		
		scrollPaneGasto = new JScrollPane();
		scrollPaneGasto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneGasto.setBounds(10, 250, 519, 259);
		frmRelattio.getContentPane().add(scrollPaneGasto);
		
		tabelaGasto = new JTable();
		tabelaGasto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Produto", "Pre\u00E7o", "Data"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabelaGasto.getColumnModel().getColumn(0).setResizable(false);
		tabelaGasto.getColumnModel().getColumn(0).setPreferredWidth(200);
		tabelaGasto.getColumnModel().getColumn(1).setResizable(false);
		tabelaGasto.getColumnModel().getColumn(1).setPreferredWidth(151);
		tabelaGasto.getColumnModel().getColumn(2).setResizable(false);
		tabelaGasto.getColumnModel().getColumn(2).setPreferredWidth(166);
		tabelaGasto.getTableHeader().setReorderingAllowed(false);
		tabelaGasto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneGasto.setViewportView(tabelaGasto);
		
		scrollPaneReceita = new JScrollPane();
		scrollPaneReceita.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneReceita.setBounds(545, 250, 519, 259);
		frmRelattio.getContentPane().add(scrollPaneReceita);
		
		tabelaReceita = new JTable();
		tabelaReceita.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Produto", "Pre\u00E7o", "Data"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabelaReceita.getColumnModel().getColumn(0).setResizable(false);
		tabelaReceita.getColumnModel().getColumn(0).setPreferredWidth(200);
		tabelaReceita.getColumnModel().getColumn(1).setResizable(false);
		tabelaReceita.getColumnModel().getColumn(1).setPreferredWidth(151);
		tabelaReceita.getColumnModel().getColumn(2).setResizable(false);
		tabelaReceita.getColumnModel().getColumn(2).setPreferredWidth(166);
		tabelaReceita.getTableHeader().setReorderingAllowed(false);
		tabelaReceita.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneReceita.setViewportView(tabelaReceita);
		
		JLabel lblFiltrarData = new JLabel("Filtrar Data:");
		lblFiltrarData.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFiltrarData.setBounds(10, 90, 84, 14);
		frmRelattio.getContentPane().add(lblFiltrarData);
		
		calendar = new JCalendar();
		calendar.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {//evento mudar dia
				if(chckbxAnomesdia.isSelected()) {
					colocaDadosNaTabelaGasto(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),//soma dos valores
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()),
						new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
				}
			}
		});
		calendar.getMonthChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {//evento mudar mes
				
				if(chckbxAnomes.isSelected()) {   
					colocaDadosNaTabelaGasto(new CrudCompras().procurarCompraDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas().procurarVendasDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras().procurarCompraDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()), 
						new CrudVendas().procurarVendasDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
				}
				
				if(chckbxAnomesdia.isSelected()) {
					colocaDadosNaTabelaGasto(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()),
						new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
				}
				
			}
		});
		calendar.getYearChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {//evento mudar ano
				
				if(chckbxAno.isSelected()) {
					colocaDadosNaTabelaGasto(new CrudCompras()
							.procurarCompraDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas()
							.procurarVendasDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras()
							.procurarCompraDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()),
						new CrudVendas()
							.procurarVendasDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()));
				}
				
				if (chckbxAnomes.isSelected()) {
					colocaDadosNaTabelaGasto(new CrudCompras().procurarCompraDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas().procurarVendasDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras().procurarCompraDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()),
						new CrudVendas().procurarVendasDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
				}
				if(chckbxAnomesdia.isSelected()) {
					colocaDadosNaTabelaGasto(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()),
						new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), 
							String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
				}
				

			}
		});
		
		calendar.getDayChooser().setVisible(false);
		calendar.getMonthChooser().setVisible(false);
		calendar.setBounds(147, 59, 217, 137);
		frmRelattio.getContentPane().add(calendar);
		
		chckbxAno = new JCheckBox("Ano");
		chckbxAno.setSelected(true);
		chckbxAno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAno.isSelected()) {
					calendar.getDayChooser().setVisible(false);
					calendar.getMonthChooser().setVisible(false);
					
					colocaDadosNaTabelaGasto(new CrudCompras()
							.procurarCompraDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas()
							.procurarVendasDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras()
							.procurarCompraDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()),
						new CrudVendas()
							.procurarVendasDataAno(String.valueOf(calendar.getYearChooser().getValue()), Principal.fazenda.getIdFazenda()));
				}
			}
		});
		chckbxAno.setBounds(370, 59, 97, 23);
		frmRelattio.getContentPane().add(chckbxAno);
		
		chckbxAnomes = new JCheckBox("Ano-Mes");
		chckbxAnomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAnomes.isSelected()) {
					calendar.getDayChooser().setVisible(false);
					calendar.getMonthChooser().setVisible(true);
					
					colocaDadosNaTabelaGasto(new CrudCompras().procurarCompraDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas().procurarVendasDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
					
					valor(new CrudCompras().procurarCompraDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()),
						new CrudVendas().procurarVendasDataAnoMes(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), Principal.fazenda.getIdFazenda()));
				}
			}
		});
		chckbxAnomes.setBounds(370, 88, 97, 23);
		frmRelattio.getContentPane().add(chckbxAnomes);
		
		chckbxAnomesdia = new JCheckBox("Ano-Mes-Dia");
		chckbxAnomesdia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAnomesdia.isSelected()) {
					calendar.getDayChooser().setVisible(true);
					calendar.getMonthChooser().setVisible(true);

					
					colocaDadosNaTabelaGasto(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
					
					colocaDadosNaTabelaReceita(new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
				
					valor(new CrudCompras().procurarCompraDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()),
						new CrudVendas().procurarVendasDataAnoDia(String.valueOf(calendar.getYearChooser().getValue()),
							String.valueOf(calendar.getMonthChooser().getMonth()+1), String.valueOf(calendar.getDayChooser().getDay()),  Principal.fazenda.getIdFazenda()));
				}
			}
		});
		chckbxAnomesdia.setBounds(370, 114, 97, 23);
		frmRelattio.getContentPane().add(chckbxAnomesdia);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(chckbxAno);
		bg.add(chckbxAnomes);
		bg.add(chckbxAnomesdia);
		
		compras.setIdFazenda(Principal.fazenda.getIdFazenda());
		vendas.setIdFazenda(Principal.fazenda.getIdFazenda());
		
		menu();
		
		colocaDadosNaTabelaGasto(CrudCompras.selecionaCompras(compras));
		colocaDadosNaTabelaReceita(CrudVendas.selecionaVendas(vendas));
	}
	
	void colocaDadosNaTabelaGasto(ResultSet rs) {
		
		DefaultTableModel modelo = (DefaultTableModel) tabelaGasto.getModel();
		modelo.setNumRows(0);
		
		try {
			while (rs.next()) {
				modelo.addRow(new Object[] {rs.getString("produto"),rs.getString("preco"),rs.getString("data_compra")});
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void colocaDadosNaTabelaReceita(ResultSet rs) {
		
		DefaultTableModel modelo = (DefaultTableModel) tabelaReceita.getModel();
		modelo.setNumRows(0);
		
		try {
			while (rs.next()) {
				modelo.addRow(new Object[] {rs.getString("produto"),rs.getString("preco"),rs.getString("datavenda")});	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void valor(ResultSet dadosCompras, ResultSet dadosVendas) {
		
		float valor1=0,valor2=0,total=0;
		
		try {
			while(dadosCompras.next()) {
				valor1 = valor1 + dadosCompras.getFloat("preco");
			}
			
			while(dadosVendas.next()) {
				valor2 = valor2 + dadosVendas.getFloat("preco");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		valorGasto.setText(String.valueOf(valor1));
		valorReceita.setText(String.valueOf(valor2));
		total = valor2 - valor1;
		if(total < 0) {
			label.setForeground(Color.RED);
		}
		if(total > 0) {
			label.setForeground(Color.GREEN);
		}
		label.setText(String.valueOf(total));
	}
	
	void menu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(Color.DARK_GRAY));
		menuBar.setBackground(new Color(128, 128, 128));
		frmRelattio.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.setBackground(new Color(128, 128, 128));
		mnInicio.setForeground(new Color(230, 230, 250));
		mnInicio.setIcon(new ImageIcon(Total.class.getResource("/img/Home.png")));
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmRelattio.dispose();
			}
		});
		
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("Gest\u00E3o");
		mnNewMenu.setForeground(new Color(230, 230, 250));
		mnNewMenu.setBackground(new Color(128, 128, 128));
		mnNewMenu.setIcon(new ImageIcon(Total.class.getResource("/img/gestao.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Cadastrar Animais");
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmRelattio.dispose();
			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Cadastrar funcionarios");
		mnNewMenu.add(mntmCadastrarFuncionarios);
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmRelattio.dispose();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("Financeiro");
		mnNewMenu_1.setBackground(new Color(128, 128, 128));
		mnNewMenu_1.setForeground(new Color(230, 230, 250));
		mnNewMenu_1.setIcon(new ImageIcon(Total.class.getResource("/img/money.png")));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCompra = new JMenuItem("Nova Compra");
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frmRelattio.dispose();
			}
		});
		mnNewMenu_1.add(mntmCompra);
		
		JMenuItem mntmNovaVenda = new JMenuItem("Nova Venda");
		mnNewMenu_1.add(mntmNovaVenda);
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmRelattio.dispose();
			}
		});
		
		JMenuItem mntmTotal = new JMenuItem("Total");
		mnNewMenu_1.add(mntmTotal);
		mntmTotal.setEnabled(false);
		
		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		mnOpes.setForeground(new Color(230, 230, 250));
		mnOpes.setBackground(new Color(128, 128, 128));
		mnOpes.setIcon(new ImageIcon(Total.class.getResource("/img/options.png")));
		menuBar.add(mnOpes);
		
		JMenuItem mntmNovaFazenda = new JMenuItem("Nova fazenda");
		mntmNovaFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaFazenda.main(null);
				frmRelattio.dispose();
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
				frmRelattio.dispose();
				Principal.frmPrincipal.dispose();
				Login.main(null);
			}
		});
		mnOpes.add(mntmDeslogar);
		
		JMenuItem mntmEnviar = new JMenuItem("Enviar feedback");
		mntmEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frmRelattio.dispose();
				EnviarEmail.main(null);
			}
		});
		mnOpes.add(mntmEnviar);
		
		mnOpes.add(mntmSada);
	}
}
