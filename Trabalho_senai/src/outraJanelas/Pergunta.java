package outraJanelas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import DAO.Fazenda;
import banco.Conexao;

public class Pergunta {

	private JFrame frame;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pergunta window = new Pergunta();
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
	public Pergunta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setType(Type.UTILITY);
		frame.setBounds(100, 100, 350, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		
		JLabel lblNewLabel = new JLabel("Selecione a Fazenda");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 324, 28);
		frame.getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(83, 80, 183, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String resp = String.valueOf(comboBox.getSelectedItem());
				passa(resp);
				Principal.button.doClick();
				Principal.frmPrincipal.setEnabled(true);
				frame.dispose();
			}
		});
		btnOk.setBounds(269, 187, 65, 23);
		frame.getContentPane().add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancelar.setBounds(170, 187, 89, 23);
		frame.getContentPane().add(btnCancelar);
		
		comboBoxFazenda();
	}
	
	void passa(String resp) {
		ResultSet dados1 = null;
		Fazenda edit = new Fazenda();
		String sql = "SELECT * FROM fazenda WHERE nome=?";
		
			try {
				PreparedStatement stmt = new Conexao().getConexao().prepareStatement(sql);
				stmt.setString(1, resp);
				dados1 = stmt.executeQuery();
				stmt.execute();
				stmt.close();
				
				if(dados1.next()) {
					edit.setIdFazenda(dados1.getInt("idfazenda"));
					edit.setNome(dados1.getString("nome"));
					edit.setTamanho(dados1.getString("tamanho"));
					edit.setProprietario(dados1.getString("proprietario"));
					edit.setEscritura(dados1.getString("escritura"));
					edit.setDescricao(dados1.getString("descri"));
					edit.setImg(dados1.getBytes("img"));
					Principal.fazenda = edit;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void comboBoxFazenda() {
		
		 ResultSet dados1 = null;
		
		Conexao c = new Conexao();// conexão
		String sql = "SELECT * FROM fazenda";
		try {
			PreparedStatement stmt = c.getConexao().prepareStatement(sql);
			dados1 = stmt.executeQuery();
			stmt.execute();
			stmt.close();

			while(dados1.next()) {
					comboBox.addItem(dados1.getString("nome"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("foi nao");
		}
	}
}
