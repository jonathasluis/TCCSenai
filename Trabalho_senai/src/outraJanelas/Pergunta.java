package outraJanelas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import DAO.Fazenda;
import DAO.Usuario;
import banco.Conexao;
import crud.CrudFazenda;

public class Pergunta {

	private JFrame frame;
	static JComboBox<String> comboBox;
	static Usuario usuario = new Usuario();
	private JButton btnOk;
	public static int contador=0;

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
	public Pergunta( ) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Pergunta.class.getResource("/img/logo-pequena-sem-texto.png")));
		frame.setResizable(false);
		frame.setBounds(100, 100, 350, 250);
		if(contador==0) {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Principal.frmPrincipal.setVisible(false);
		}else {
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Selecione a Fazenda");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 324, 28);
		frame.getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox<String>();
		comboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnOk.doClick();
				}
			}
		});
		comboBox.setBounds(83, 80, 180, 20);
		frame.getContentPane().add(comboBox);
		
		btnOk = new JButton("OK");
		btnOk.setBackground(SystemColor.controlHighlight);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String resp = String.valueOf(comboBox.getSelectedItem());
				if (contador==0) {
					Principal.frmPrincipal.setVisible(true);
				}
				
				passa(resp);
				//Principal.pega();
				frame.dispose();
			}
		});
		btnOk.setBounds(269, 187, 65, 23);
		frame.getContentPane().add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(SystemColor.controlHighlight);
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
				PreparedStatement stmt =  Conexao.conexao.prepareStatement(sql);
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
		 //Fazenda fazenda = new Fazenda();
		 ResultSet dados1 = CrudFazenda.selecionaFazenda(usuario);
		
			try {
				while(dados1.next()) {
						comboBox.addItem(dados1.getString("nome"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
