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
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import java.awt.SystemColor;

public class NovaRaca {

	private JFrame frmNovaRaca;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JButton btnSalvar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaRaca window = new NovaRaca();
					window.frmNovaRaca.setVisible(true);
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
		frmNovaRaca = new JFrame();
		frmNovaRaca.setType(Type.UTILITY);
		frmNovaRaca.setTitle("Nova Ra\u00E7a");
		frmNovaRaca.setResizable(false);
		frmNovaRaca.setBounds(100, 100, 300, 200);
		frmNovaRaca.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNovaRaca.getContentPane().setLayout(null);
		
		JLabel lblDespcie = new JLabel("Esp\u00E9cie:");
		lblDespcie.setHorizontalAlignment(SwingConstants.CENTER);
		lblDespcie.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDespcie.setBounds(10, 16, 274, 14);
		frmNovaRaca.getContentPane().add(lblDespcie);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 97, 274, 20);
		frmNovaRaca.getContentPane().add(textField);
		
		JButton button = new JButton("Cancelar");
		button.setBackground(SystemColor.controlHighlight);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmNovaRaca.dispose();
			}
		});
		button.setBounds(104, 138, 85, 23);
		frmNovaRaca.getContentPane().add(button);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(SystemColor.controlHighlight);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		btnSalvar.setBounds(199, 138, 85, 23);
		frmNovaRaca.getContentPane().add(btnSalvar);
		
		JLabel lblRaa = new JLabel("Ra\u00E7a:");
		lblRaa.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRaa.setBounds(10, 72, 274, 14);
		frmNovaRaca.getContentPane().add(lblRaa);
		
		comboBox = new JComboBox<String>();
		comboBox.setBackground(SystemColor.controlHighlight);
		comboBox.setBounds(10, 41, 274, 20);
		frmNovaRaca.getContentPane().add(comboBox);
		frmNovaRaca.setLocationRelativeTo(null);
		
		comboBoxEspecie();
	}
	
	public void comboBoxEspecie() {
		
		 ResultSet dados1;
		
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
}
