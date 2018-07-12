package JanelasAnimal;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import banco.Conexao;

public class CadastrarEspecie{

	private JFrame frmNovaEspecie;
	private JTextField textField;
	private JButton btnSalvar;

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
		frmNovaEspecie.setType(Type.UTILITY);
		frmNovaEspecie.setIconImage(Toolkit.getDefaultToolkit().getImage(CadastrarEspecie.class.getResource("/img/logo-pequena-sem-texto.png")));
		frmNovaEspecie.setTitle("Nova Especie");
		frmNovaEspecie.setResizable(false);
		frmNovaEspecie.setBounds(100, 100, 300, 150);
		frmNovaEspecie.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNovaEspecie.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() ==  KeyEvent.VK_ENTER){
					btnSalvar.doClick();
				}
			}
		});
		textField.setBounds(10, 36, 274, 20);
		frmNovaEspecie.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nome da Esp\u00E9cie:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 11, 274, 14);
		frmNovaEspecie.getContentPane().add(lblNewLabel);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(SystemColor.controlHighlight);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(textField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "campo vazio");
					return;	
				}
				
				verificaSeTemEspecie();
				//contador=1;	
			}
		});
		btnSalvar.setBounds(199, 92, 85, 23);
		frmNovaEspecie.getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(SystemColor.controlHighlight);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNovaEspecie.dispose();
			}
		});
		btnCancelar.setBounds(104, 92, 85, 23);
		frmNovaEspecie.getContentPane().add(btnCancelar);
		frmNovaEspecie.setLocationRelativeTo(null);
		
		this.comboBoxEspecie();
	}
	
	public void comboBoxEspecie() {
		ResultSet dados1=null;
		String sql = "SELECT (nome_es) FROM especie";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			dados1 = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			CadastrarAnimais.cbEspecie.removeAllItems();
			while(dados1.next()) {
				CadastrarAnimais.cbEspecie.addItem(dados1.getString("nome_es"));
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("foi nao");
		}
	}
	
	void Salvar() {
		
		String sql = "INSERT INTO especie (nome_es) VALUES (?)";
		
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, textField.getText().toLowerCase());
			stmt.execute();
			stmt.close();
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
			
			comboBoxEspecie();
			frmNovaEspecie.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	void verificaSeTemEspecie() {
		ResultSet dados=null;
		String sql = "select * from especie where nome_es = ?";
		
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, textField.getText().toLowerCase());
			dados = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
			if(!dados.next()) {
				Salvar();
			}else {
				JOptionPane.showMessageDialog(null, "Espécie já cadastrada!");
				textField.selectAll();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
