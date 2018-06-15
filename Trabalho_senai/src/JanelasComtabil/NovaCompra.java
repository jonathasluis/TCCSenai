package JanelasComtabil;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import JanelasAnimal.CadastrarAnimais;
import JanelasAnimal.VisualizarAnimais;
import JanelasFuncionarios.CadastrarFuncionarios;
import JanelasFuncionarios.VisualizarFuncionarios;
import crud.CrudCompras;
import outraJanelas.NovaFazenda;
import outraJanelas.Pergunta;
import outraJanelas.Principal;
import outraJanelas.VisualizarFazendas;

public class NovaCompra {

	private JFrame frmCompraDeInsumos;
	private JTextField tfProduto;
	private JTextField tfData;
	private JTextField tfNota;
	private JTextField tfPreco;
	private JTextField txtFornecedor;
	private JTextField tfCNPJ;
	private JButton btnLimpar;

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
		frmCompraDeInsumos.setBounds(100, 100, 606, 385);
		frmCompraDeInsumos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCompraDeInsumos.setLocationRelativeTo(null);
		frmCompraDeInsumos.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		frmCompraDeInsumos.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.frmPrincipal.setVisible(true);
				frmCompraDeInsumos.dispose();
			}
		});
		menuBar.add(mnInicio);
		
		JMenu mnNewMenu = new JMenu("Animais");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrarAnimais = new JMenuItem("Cadastrar Animais");
		mntmCadastrarAnimais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarAnimais.main(null);
				frmCompraDeInsumos.dispose();
			}
		});
		mnNewMenu.add(mntmCadastrarAnimais);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Visualizar Animais");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarAnimais.main(null);
				frmCompraDeInsumos.dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnFuncionarios = new JMenu("Funcionarios");
		menuBar.add(mnFuncionarios);
		
		JMenuItem mntmCadastrarFuncionarios = new JMenuItem("Cadastrar funcionarios");
		mntmCadastrarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarFuncionarios.main(null);
				frmCompraDeInsumos.dispose();
			}
		});
		mnFuncionarios.add(mntmCadastrarFuncionarios);
		
		JMenuItem mntmVisualizarFuncionarios = new JMenuItem("Visualizar funcionarios");
		mntmVisualizarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarFuncionarios.main(null);
				frmCompraDeInsumos.dispose();
			}
		});
		mnFuncionarios.add(mntmVisualizarFuncionarios);
		
		JMenu mnNewMenu_1 = new JMenu("Compra de Insumos");
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
		
		JMenuItem mntmVisualizarCompra = new JMenuItem("Visualizar Compras");
		mntmVisualizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarCompras.main(null);
				frmCompraDeInsumos.dispose();
			}
		});
		mnNewMenu_1.add(mntmVisualizarCompra);
		
		JMenu mnNewMenu_2 = new JMenu("Vendas");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNovaVenda = new JMenuItem("Nova Venda");
		mntmNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaVenda.main(null);
				frmCompraDeInsumos.dispose();
			}
		});
		mnNewMenu_2.add(mntmNovaVenda);
		
		JMenuItem mntmVisualizarVendas = new JMenuItem("Visualizar Vendas");
		mntmVisualizarVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarVendas.main(null);
				frmCompraDeInsumos.dispose();
			}
		});
		mnNewMenu_2.add(mntmVisualizarVendas);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rio");
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
		menuBar.add(mnOpes);
		
		JMenuItem mntmNovaFazenda = new JMenuItem("Nova fazenda");
		mntmNovaFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NovaFazenda.main(null);
				frmCompraDeInsumos.dispose();
			}
		});
		mnOpes.add(mntmNovaFazenda);
		
		JMenuItem mntmVisualizarFazenda = new JMenuItem("Visualizar Fazenda");
		mntmVisualizarFazenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizarFazendas.main(null);
				frmCompraDeInsumos.dispose();
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
		frmCompraDeInsumos.getContentPane().setLayout(null);
		
		JLabel lblCompraDeInsumos = new JLabel("Compra de Insumos");
		lblCompraDeInsumos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompraDeInsumos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCompraDeInsumos.setBounds(0, 0, 600, 19);
		frmCompraDeInsumos.getContentPane().add(lblCompraDeInsumos);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProduto.setBounds(10, 50, 66, 20);
		frmCompraDeInsumos.getContentPane().add(lblProduto);
		
		tfProduto = new JTextField();
		tfProduto.setBounds(86, 50, 200, 20);
		frmCompraDeInsumos.getContentPane().add(tfProduto);
		tfProduto.setColumns(10);
		
		JLabel lblDataDaCompra = new JLabel("Data da Compra:");
		lblDataDaCompra.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDataDaCompra.setBounds(10, 80, 125, 20);
		frmCompraDeInsumos.getContentPane().add(lblDataDaCompra);
		
		Date data = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MMMMMMMM/yyyy");
		String formatada = formato.format(data);
		
		tfData = new JTextField();
		tfData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfData.setHorizontalAlignment(SwingConstants.CENTER);
		tfData.setBounds(131, 80, 155, 20);
		frmCompraDeInsumos.getContentPane().add(tfData);
		tfData.setColumns(10);
		tfData.setText(formatada);
		
		JLabel lblNumeroDaNota = new JLabel("Numero da Nota:");
		lblNumeroDaNota.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNumeroDaNota.setBounds(10, 110, 116, 20);
		frmCompraDeInsumos.getContentPane().add(lblNumeroDaNota);
		
		tfNota = new JTextField();
		tfNota.setColumns(10);
		tfNota.setBounds(131, 110, 155, 20);
		frmCompraDeInsumos.getContentPane().add(tfNota);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPreo.setBounds(323, 80, 71, 20);
		frmCompraDeInsumos.getContentPane().add(lblPreo);
		
		tfPreco = new JTextField();
		tfPreco.setBounds(373, 81, 132, 20);
		frmCompraDeInsumos.getContentPane().add(tfPreco);
		tfPreco.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 159, 600, 1);
		frmCompraDeInsumos.getContentPane().add(separator);
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFornecedor.setBounds(10, 190, 92, 20);
		frmCompraDeInsumos.getContentPane().add(lblFornecedor);
		
		txtFornecedor = new JTextField();
		txtFornecedor.setBounds(98, 190, 188, 20);
		frmCompraDeInsumos.getContentPane().add(txtFornecedor);
		txtFornecedor.setColumns(10);
		
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCnpj.setBounds(10, 220, 66, 20);
		frmCompraDeInsumos.getContentPane().add(lblCnpj);
		
		tfCNPJ = new JTextField();
		tfCNPJ.setBounds(66, 220, 220, 20);
		frmCompraDeInsumos.getContentPane().add(tfCNPJ);
		tfCNPJ.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfProduto.getText().trim().equals("")) {
					if(!tfData.getText().trim().equals("")) {
						if(! tfNota.getText().trim().equals("")) {
							if(!tfPreco.getText().trim().equals("")) {
								if(!txtFornecedor.getText().trim().equals("")) {
									if(!tfCNPJ.getText().trim().equals("")) {
										new CrudCompras().addcompras(txtFornecedor.getText(), tfCNPJ.getText(), tfNota.getText(),
												Double.parseDouble(tfPreco.getText()), tfProduto.getText(), tfData.getText());
										JOptionPane.showMessageDialog(null, "salvo com sucesso!");
										btnLimpar.doClick();
									}else {
										tfCNPJ.requestFocus();
										JOptionPane.showMessageDialog(null, "Insira o CNPJ do fornecedor!");
									}
								}else {
									txtFornecedor.requestFocus();
									JOptionPane.showMessageDialog(null, "Insira o Fornecedor!");
								}
							}else {
								tfPreco.requestFocus();
								JOptionPane.showMessageDialog(null, "Insira o valor!");
							}
						}else {
							tfNota.requestFocus();
							JOptionPane.showMessageDialog(null, "Insira o numero da nota!");
						}
					}else {
						tfData.requestFocus();
						JOptionPane.showMessageDialog(null, "Insira a data!");
					}
				}else {
					tfProduto.requestFocus();
					JOptionPane.showMessageDialog(null, "Insira um Produto!");
				}
			}
		});
		btnSalvar.setBounds(501, 301, 89, 23);
		frmCompraDeInsumos.getContentPane().add(btnSalvar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCNPJ.setText(null);
				tfData.setText(null);
				tfNota.setText(null);
				tfProduto.setText(null);
				tfPreco.setText(null);
				txtFornecedor.setText(null);
				
			}
		});
		btnLimpar.setBounds(402, 301, 89, 23);
		frmCompraDeInsumos.getContentPane().add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCompraDeInsumos.dispose();
				Principal.frmPrincipal.setVisible(true);
			}
		});
		btnCancelar.setBounds(305, 301, 89, 23);
		frmCompraDeInsumos.getContentPane().add(btnCancelar);
		
		JEditorPane dtrpnUmaVezSalvo = new JEditorPane();
		dtrpnUmaVezSalvo.setText("Uma vez salvo as compras nao poder\u00E3o ser \r\ndeletadas e nem atualizadas");
		dtrpnUmaVezSalvo.setFont(new Font("Tahoma", Font.BOLD, 12));
		dtrpnUmaVezSalvo.setEditable(false);
		dtrpnUmaVezSalvo.setBackground(SystemColor.menu);
		dtrpnUmaVezSalvo.setBounds(303, 233, 287, 57);
		frmCompraDeInsumos.getContentPane().add(dtrpnUmaVezSalvo);
	}
}
