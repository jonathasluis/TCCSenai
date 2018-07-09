package JanelasComtabil;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import JanelasAnimal.CadastrarAnimais;
import JanelasAnimal.ComboBox;
import JanelasFuncionarios.CadastrarFuncionarios;
import banco.Conexao;
import outraJanelas.NovaFazenda;
import outraJanelas.Pergunta;
import outraJanelas.Principal;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.border.BevelBorder;

public class NovaVenda {

	private JFrame frmNovaVenda;
	private JTextField tfProduto;
	private JRadioButton rdbtnPlantio;
	private JRadioButton rdbtnAnimal;
	private JRadioButton rdbtnSubproduto;
	private JTextField tfPreco;
	private JTextField tfCliente;
	public static JComboBox<String> cbAnimal;
	ComboBox cb = new ComboBox();
	private JLabel lblProduto;
	private JLabel lblAnimal;
	int id;
	private JButton btnLimpar;
	private JTable table;
	private JTextField textField;

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
				cbAnimal.setVisible(true);
				lblProduto.setVisible(false);
				tfProduto.setVisible(false);
				lblAnimal.setVisible(true);
			}
		});
		rdbtnAnimal.setBounds(123, 80, 66, 20);
		frmNovaVenda.getContentPane().add(rdbtnAnimal);
		
		rdbtnSubproduto = new JRadioButton("Subproduto");
		rdbtnSubproduto.setSelected(true);
		rdbtnSubproduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbAnimal.setVisible(true);
				lblProduto.setVisible(true);
				tfProduto.setVisible(true);
				lblAnimal.setVisible(true);
			}
		});
		rdbtnSubproduto.setBounds(191, 80, 94, 20);
		frmNovaVenda.getContentPane().add(rdbtnSubproduto);
		
		rdbtnPlantio = new JRadioButton("Plantio");
		rdbtnPlantio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbAnimal.setVisible(false);
				lblAnimal.setVisible(false);
				lblProduto.setVisible(true);
				tfProduto.setVisible(true);
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
		cbAnimal.setBackground(Color.WHITE);
		cbAnimal.setBounds(546, 111, 188, 20);
		frmNovaVenda.getContentPane().add(cbAnimal);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantidade.setBounds(787, 111, 89, 20);
		frmNovaVenda.getContentPane().add(lblQuantidade);
		
		JSpinner spinner = new JSpinner();
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
				String tipo=null;
				String animal;
				
				if(rdbtnAnimal.isSelected()) {
					tipo="animal";
					animal=cbAnimal.getSelectedItem().toString();
					idAnimal(cbAnimal.getSelectedItem().toString());
					//new CrudAnimal().removeAnimal(id);
					JOptionPane.showMessageDialog(null, "animal removido com sucesso");
				}
					
				if(rdbtnPlantio.isSelected()) {
					tipo="plantio";
					animal=null;
				}
					
				if(rdbtnSubproduto.isSelected()) {
					tipo="subproduto";
					animal=cbAnimal.getSelectedItem().toString();
				}
					
				
				String qtd = String.valueOf(spinner.getValue());
				
				if(tfProduto.getText().trim().equals("") && (rdbtnPlantio.isSelected() || rdbtnSubproduto.isSelected())) {
					JOptionPane.showMessageDialog(null, "insira o  produto");
					tfProduto.requestFocus();
				} else if(!tfPreco.getText().trim().equals("")){
					//new CrudVendas().addvendas(tfProduto.getText(), id, Double.parseDouble(tfPreco.getText()), tfCliente.getText(), qtd, tfData.getText(), cbFazenda.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "salvo com sucesso");
					btnLimpar.doClick();
				}
				
			}
		});
		btnSalvar.setBounds(975, 637, 89, 23);
		frmNovaVenda.getContentPane().add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCliente.setText(null);
				ftf.setText(null);
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
		SimpleDateFormat formato = new SimpleDateFormat("dd/MMMMMMMM/yyyy");
		String formatada = formato.format(data);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 290, 1024, 336);
		frmNovaVenda.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JFormattedTextField ftfData = new JFormattedTextField();
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
		
		menu();
		
		//cb.comboBoxAnimal();
		//cb.comboBoxFazenda();
	}
	
	void idAnimal(String nome) {
		String sql = "SELECT (idanimal) from animais where nome_a=?";
		ResultSet rs= null;
		try {
			PreparedStatement stmt = new Conexao().getConexao().prepareStatement(sql);
			stmt.setString(1, cbAnimal.getSelectedItem().toString());
			rs = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
			while(rs.next()) {
				id = rs.getInt("idanimal");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	void menu() {
		JMenuBar menuBar = new JMenuBar();
		frmNovaVenda.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmNovaVenda.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("Animais");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Cadastrar Animais");
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmNovaVenda.dispose();
			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenu mnFuncionarios = new JMenu("Funcionarios");
		menuBar.add(mnFuncionarios);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Cadastrar funcionarios");
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmNovaVenda.dispose();
			}
		});
		mnFuncionarios.add(mntmCadastrarFuncionarios);
		
		JMenu mnNewMenu_1 = new JMenu("Compra de Insumos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCompra = new JMenuItem("Nova Compra");
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaCompra.main(null);
				frmNovaVenda.dispose();
			}
		});
		mnNewMenu_1.add(mntmCompra);

		JMenu mnNewMenu_2 = new JMenu("Vendas");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNovaVenda = new JMenuItem("Nova Venda");
		mntmNovaVenda.setEnabled(false);
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmNovaVenda.dispose();
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
				frmNovaVenda.dispose();
			}
		});
		mnRelatrio.add(mntmTotal);
		
		JMenuItem mntmNovaFazenda = new JMenuItem("Nova fazenda");
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
			}
		});
		mnOpes.add(mntmMudarFazenda);
		mnOpes.add(mntmSada);
	}
}
