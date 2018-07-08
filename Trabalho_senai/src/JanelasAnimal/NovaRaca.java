package JanelasAnimal;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import banco.Conexao;

public class NovaRaca {

	private JFrame frmNovaRaa;
	private JTextField textField;
	private JComboBox comboBox;
	Conexao con = new Conexao();
	String palavra = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaRaca window = new NovaRaca();
					window.frmNovaRaa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NovaRaca() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNovaRaa = new JFrame();
		frmNovaRaa.setTitle("Nova Ra\u00E7a");
		frmNovaRaa.setResizable(false);
		frmNovaRaa.setBounds(100, 100, 450, 175);
		frmNovaRaa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNovaRaa.getContentPane().setLayout(null);
		
		JLabel lblDespcie = new JLabel("Esp\u00E9cie:");
		lblDespcie.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDespcie.setBounds(20, 11, 80, 14);
		frmNovaRaa.getContentPane().add(lblDespcie);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(83, 36, 270, 20);
		frmNovaRaa.getContentPane().add(textField);
		
		JButton button = new JButton("Cancelar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmNovaRaa.dispose();
			}
		});
		button.setBounds(224, 113, 100, 23);
		frmNovaRaa.getContentPane().add(button);
		
		JButton button_1 = new JButton("Salvar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				result();
				
				if(!(palavra == "tem"))
					if(!textField.getText().trim().equals("")) {
						Salvar();
					}else
						JOptionPane.showMessageDialog(null, "campo vazio");
				else {
					JOptionPane.showMessageDialog(null, "Raça já cadastrada!");
					textField.selectAll();
				}
			}
		});
		button_1.setBounds(334, 113, 100, 23);
		frmNovaRaa.getContentPane().add(button_1);
		
		JLabel lblRaa = new JLabel("Ra\u00E7a:");
		lblRaa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRaa.setBounds(20, 36, 62, 14);
		frmNovaRaa.getContentPane().add(lblRaa);
		
		comboBox = new JComboBox();
		comboBox.setBounds(83, 9, 168, 20);
		frmNovaRaa.getContentPane().add(comboBox);
		frmNovaRaa.setLocationRelativeTo(null);
		
		comboBoxEspecie();
	}
	
	public void comboBoxEspecie() {
		
		 ResultSet dados1;
		
		Conexao c = new Conexao();// conexão
		String sql = "SELECT (nome_es) FROM especie";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			dados1 = stmt.executeQuery();
			stmt.execute();
			stmt.close();

			while(dados1.next()) {
					comboBox.addItem(dados1.getString("nome_es"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("foi nao");
		}
	}
	
void Salvar() {
		
		String sql = "INSERT INTO raca (nome_ra,especie_ra) VALUES (?,?)";
		
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, textField.getText().toLowerCase());
			stmt.setString(2, comboBox.getSelectedItem().toString());
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
		String sql = "select * from raca";
		
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			dados = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
			while(dados.next()) {
				if(textField.getText().equalsIgnoreCase(dados.getString("nome_ra"))) {
					if(comboBox.getSelectedItem().toString().equalsIgnoreCase(dados.getString("especie_ra"))) {
						palavra = "tem";
						
					}
				} else
					palavra=null;
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
