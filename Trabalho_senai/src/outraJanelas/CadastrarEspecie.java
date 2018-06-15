package outraJanelas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import JanelasAnimal.CadastrarAnimais;
import banco.Conexao;

public class CadastrarEspecie {

	private JFrame frmNovaEspecie;
	private JTextField textField;
	private JButton button;
	Conexao con = new Conexao();
	String palavra = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarEspecie window = new CadastrarEspecie();
					window.frmNovaEspecie.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CadastrarEspecie() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNovaEspecie = new JFrame();
		frmNovaEspecie.setTitle("Nova Especie");
		frmNovaEspecie.setResizable(false);
		frmNovaEspecie.setBounds(100, 100, 450, 175);
		frmNovaEspecie.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNovaEspecie.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() ==  KeyEvent.VK_ENTER){
					button.doClick();
				}
			}
		});
		textField.setBounds(137, 43, 270, 20);
		frmNovaEspecie.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome da Esp\u00E9cie:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 45, 132, 14);
		frmNovaEspecie.getContentPane().add(lblNewLabel);
		
		button = new JButton("Salvar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				result();
				
				if(!(palavra == "tem")) {
					if(!textField.getText().trim().equals("")) {
						Salvar();
					}else
						JOptionPane.showInternalMessageDialog(null, "campo vazio");
				}
					
				else {
					JOptionPane.showMessageDialog(null, "Especie já cadastrada!");
					textField.selectAll();
				}
					
			}
		});
		button.setBounds(334, 109, 100, 23);
		frmNovaEspecie.getContentPane().add(button);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNovaEspecie.dispose();
			}
		});
		btnCancelar.setBounds(224, 109, 100, 23);
		frmNovaEspecie.getContentPane().add(btnCancelar);
		
		JLabel lblIrParaTela = new JLabel("Ir para tela de Cadastro de Ra\u00E7a");
		lblIrParaTela.setBounds(201, 6, 184, 14);
		frmNovaEspecie.getContentPane().add(lblIrParaTela);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NovaRaca.main(null);
				frmNovaEspecie.dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(CadastrarEspecie.class.getResource("/img/seta.png")));
		btnNewButton.setBounds(395, 4, 39, 19);
		frmNovaEspecie.getContentPane().add(btnNewButton);
		frmNovaEspecie.setLocationRelativeTo(null);
		
		CadastrarAnimais.btnOk.doClick();
	}
	
	void Salvar() {
		
		String sql = "INSERT INTO especie (nome_es) VALUES (?)";
		
		try {
			PreparedStatement stmt = con.getConexao().prepareStatement(sql);
			stmt.setString(1, textField.getText().toLowerCase());
			stmt.execute();
			stmt.close();
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	void result() {
		ResultSet dados=null;
		String sql = "select * from especie";
		
		try {
			PreparedStatement stmt = con.getConexao().prepareStatement(sql);
			dados = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
			while(dados.next()) {
				if(textField.getText().equalsIgnoreCase(dados.getString("nome_es"))) {
					palavra = "tem";
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
