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

public class NovaVenda {

	private JFrame frmNovaVenda;
	private JTextField tfProduto;
	private JRadioButton rdbtnPlantio;
	private JRadioButton rdbtnAnimal;
	private JRadioButton rdbtnSubproduto;
	private JTextField tfPreco;
	private JTextField tfCliente;
	private JTextField tfData;
	public static JComboBox<String> cbAnimal;
	public static JComboBox<String> cbFazenda;
	ComboBox cb = new ComboBox();
	private JLabel lblProduto;
	private JLabel lblAnimal;
	int id;
	private JButton btnLimpar;

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
		
		JLabel lblNovaVenda = new JLabel("Nova Venda");
		lblNovaVenda.setHorizontalAlignment(SwingConstants.CENTER);
		lblNovaVenda.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNovaVenda.setBounds(0, 0, 714, 19);
		frmNovaVenda.getContentPane().add(lblNovaVenda);
		
		JLabel lblTipoDoProduto = new JLabel("Tipo do Produto:");
		lblTipoDoProduto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTipoDoProduto.setBounds(10, 50, 107, 20);
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
		rdbtnAnimal.setBounds(123, 50, 66, 20);
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
		rdbtnSubproduto.setBounds(191, 50, 94, 20);
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
		rdbtnPlantio.setBounds(287, 50, 66, 20);
		frmNovaVenda.getContentPane().add(rdbtnPlantio);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnPlantio);
		bg.add(rdbtnSubproduto);
		bg.add(rdbtnAnimal);
		
		lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProduto.setBounds(10, 80, 74, 20);
		frmNovaVenda.getContentPane().add(lblProduto);
		
		tfProduto = new JTextField();
		tfProduto.setBounds(79, 80, 210, 20);
		frmNovaVenda.getContentPane().add(tfProduto);
		tfProduto.setColumns(10);
		
		lblAnimal = new JLabel("Animal:");
		lblAnimal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAnimal.setBounds(10, 110, 74, 20);
		frmNovaVenda.getContentPane().add(lblAnimal);
		
		cbAnimal = new JComboBox<String>();
		cbAnimal.setBackground(Color.WHITE);
		cbAnimal.setBounds(79, 110, 210, 20);
		frmNovaVenda.getContentPane().add(cbAnimal);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuantidade.setBounds(460, 80, 94, 20);
		frmNovaVenda.getContentPane().add(lblQuantidade);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setBounds(564, 81, 78, 20);
		frmNovaVenda.getContentPane().add(spinner);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPreo.setBounds(460, 110, 74, 20);
		frmNovaVenda.getContentPane().add(lblPreo);
		
		tfPreco = new JTextField();
		tfPreco.setColumns(10);
		tfPreco.setBounds(532, 110, 110, 20);
		frmNovaVenda.getContentPane().add(tfPreco);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 200, 720, 1);
		frmNovaVenda.getContentPane().add(separator);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCliente.setBounds(10, 210, 74, 20);
		frmNovaVenda.getContentPane().add(lblCliente);
		
		tfCliente = new JTextField();
		tfCliente.setColumns(10);
		tfCliente.setBounds(79, 210, 210, 20);
		frmNovaVenda.getContentPane().add(tfCliente);
		
		JLabel lblFazenda = new JLabel("Fazenda:");
		lblFazenda.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFazenda.setBounds(10, 240, 74, 20);
		frmNovaVenda.getContentPane().add(lblFazenda);
		
		cbFazenda = new JComboBox<String>();
		cbFazenda.setBackground(Color.WHITE);
		cbFazenda.setBounds(79, 240, 210, 20);
		frmNovaVenda.getContentPane().add(cbFazenda);
		
		JLabel lblDataDaVenda = new JLabel("Data da Venda:");
		lblDataDaVenda.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDataDaVenda.setBounds(10, 270, 107, 20);
		frmNovaVenda.getContentPane().add(lblDataDaVenda);
		
		tfData = new JTextField();
		tfData.setHorizontalAlignment(SwingConstants.CENTER);
		tfData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfData.setColumns(10);
		tfData.setBounds(124, 270, 165, 20);
		frmNovaVenda.getContentPane().add(tfData);
		
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
		btnSalvar.setBounds(615, 366, 89, 23);
		frmNovaVenda.getContentPane().add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCliente.setText(null);
				tfData.setText(null);
				tfPreco.setText(null);
				tfProduto.setText(null);
				rdbtnSubproduto.setSelected(true);
				spinner.setValue(0);
			}
		});
		btnLimpar.setBounds(516, 366, 89, 23);
		frmNovaVenda.getContentPane().add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNovaVenda.dispose();
				Principal.frmPrincipal.setVisible(true);
			}
		});
		btnCancelar.setBounds(417, 366, 89, 23);
		frmNovaVenda.getContentPane().add(btnCancelar);
		
		Date data = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MMMMMMMM/yyyy");
		String formatada = formato.format(data);
		
		tfData.setText(formatada);
		
		JEditorPane dtrpnUmaVezSalvo = new JEditorPane();
		dtrpnUmaVezSalvo.setEditable(false);
		dtrpnUmaVezSalvo.setFont(new Font("Tahoma", Font.BOLD, 12));
		dtrpnUmaVezSalvo.setBackground(SystemColor.control);
		dtrpnUmaVezSalvo.setText("Uma vez salvo as vendas nao poder\u00E3o ser \r\ndeletadas e nem atualizadas");
		dtrpnUmaVezSalvo.setBounds(417, 298, 287, 57);
		frmNovaVenda.getContentPane().add(dtrpnUmaVezSalvo);
		
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
