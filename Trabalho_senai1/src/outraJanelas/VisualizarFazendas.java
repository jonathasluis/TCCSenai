package outraJanelas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DAO.Fazenda;
import JanelasAnimal.CadastrarAnimais;
//import JanelasAnimal.VisualizarAnimais;
import JanelasComtabil.NovaCompra;
import JanelasComtabil.NovaVenda;
import JanelasComtabil.Total;
import JanelasComtabil.VisualizarCompras;
import JanelasComtabil.VisualizarVendas;
import JanelasFuncionarios.CadastrarFuncionarios;
import JanelasFuncionarios.VisualizarFuncionarios;
import banco.Conexao;
import crud.CrudFazenda;

public class VisualizarFazendas {

	private JFrame frame;
	private JTextField tfProcura;
	private JTable table;
	public byte[] img;
	static JButton btnProcurar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarFazendas window = new VisualizarFazendas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisualizarFazendas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 450);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(615, 366, 89, 23);
		frame.getContentPane().add(btnVoltar);
		
		btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfProcura.getText().trim().equals(""))
					tabela();
				else
					like();
			}
		});
		btnProcurar.setBounds(615, 10, 89, 20);
		frame.getContentPane().add(btnProcurar);
		
		tfProcura = new JTextField();
		tfProcura.setBounds(10, 10, 595, 20);
		frame.getContentPane().add(tfProcura);
		tfProcura.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 694, 292);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				passa();		
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Tamanho", "Propietario", "Escritura", "Descri\u00E7\u00E3o"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			/**
			 * 
			 */
			
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
		table.getColumnModel().getColumn(2).setPreferredWidth(97);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(165);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frame.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("Animais");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Cadastrar Animais");
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frame.dispose();
			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Visualizar Animais");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				VisualizarAnimais.main(null);
				frame.dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnFuncionarios = new JMenu("Funcionarios");
		menuBar.add(mnFuncionarios);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Cadastrar funcionarios");
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frame.dispose();
			}
		});
		mnFuncionarios.add(mntmCadastrarFuncionarios);
		
		JMenuItem mntmVisualizarFuncionarios = new JMenuItem("Visualizar funcionarios");
		mntmVisualizarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarFuncionarios.main(null);
				frame.dispose();
			}
		});
		mnFuncionarios.add(mntmVisualizarFuncionarios);
		
		JMenu mnNewMenu_1 = new JMenu("Compra de Insumos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCompra = new JMenuItem("Nova Compra");
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frame.dispose();
			}
		});
		mnNewMenu_1.add(mntmCompra);
		
		JMenuItem mntmVisualizarCompra = new JMenuItem("Visualizar Compras");
		mntmVisualizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarCompras.main(null);
				frame.dispose();
			}
		});
		mnNewMenu_1.add(mntmVisualizarCompra);
		
		JMenu mnNewMenu_2 = new JMenu("Vendas");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNovaVenda = new JMenuItem("Nova Venda");
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frame.dispose();
			}
		});
		mnNewMenu_2.add(mntmNovaVenda);
		
		JMenuItem mntmVisualizarVendas = new JMenuItem("Visualizar Vendas");
		mntmVisualizarVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarVendas.main(null);
				frame.dispose();
			}
		});
		mnNewMenu_2.add(mntmVisualizarVendas);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatrio);
		
		JMenuItem mntmTotal = new JMenuItem("Total");
		mntmTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Total.main(null);
				frame.dispose();
			}
		});
		mnRelatrio.add(mntmTotal);
		
		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnOpes);
		
		JMenuItem mntmNovaFazenda = new JMenuItem("Nova fazenda");
		mntmNovaFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaFazenda.main(null);
				frame.dispose();
			}
		});
		mnOpes.add(mntmNovaFazenda);
		
		JMenuItem mntmVisualizarFazenda = new JMenuItem("Visualizar Fazenda");
		mntmVisualizarFazenda.setEnabled(false);
		mntmVisualizarFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarFazendas.main(null);
				frame.dispose();
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
		
//		tabela();
	}
	
	void tabela() {
		ResultSet rs = new CrudFazenda().selefazenda();
		
		try {
			
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			modelo.setNumRows(0);
			
			while(rs.next()) {
				modelo.addRow(new Object[] {rs.getInt("idfazenda"),rs.getString("nome"),rs.getString("tamanho"),rs.getString("propi"),
						rs.getString("escri"),rs.getString("descri")});
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void like() {
		ResultSet rs = new CrudFazenda().likefazenda(tfProcura.getText());
		
		try {
			
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			modelo.setNumRows(0);
			
			while(rs.next()) {
				modelo.addRow(new Object[] {rs.getInt("idfazenda"),rs.getString("nome"),rs.getString("tamanho"),rs.getString("propi"),
						rs.getString("escri"),rs.getString("descri")});
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	void passa() {
		int linha = table.getSelectedRow();
		
		Fazenda edit = new Fazenda();
		edit.setId(Integer.parseInt(table.getValueAt(linha, 0).toString()));
		edit.setNome(table.getValueAt(linha, 1).toString());
		edit.setTamanho(table.getValueAt(linha, 2).toString());
		edit.setPropi(table.getValueAt(linha, 3).toString());
		edit.setEscri(table.getValueAt(linha, 4).toString());
		edit.setDescri(table.getValueAt(linha, 5).toString());
	
		ResultSet dados1 = null;
		String sql = "SELECT (img) FROM fazenda WHERE idfazenda=?";
		
			try {
				PreparedStatement stmt = new Conexao().getConexao().prepareStatement(sql);
				stmt.setInt(1, Integer.parseInt(table.getValueAt(linha, 0).toString()));
				dados1 = stmt.executeQuery();
				stmt.execute();
				stmt.close();
				
				if(dados1.next()) {
					edit.setImg(dados1.getBytes("img"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EditarRemoverFazenda.fazenda = edit;
			EditarRemoverFazenda.main(null);
	}
}
