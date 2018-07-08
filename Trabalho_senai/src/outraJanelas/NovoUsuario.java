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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import banco.Conexao;
import crud.crudUsuarios;
import java.awt.Toolkit;

public class NovoUsuario {
	

	private JFrame frame;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;
	private JPasswordField pfConfirma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovoUsuario window = new NovoUsuario();
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
	public NovoUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(NovoUsuario.class.getResource("/img/logo-pequena-sem-texto.png")));
		frame.setBounds(100, 100, 447, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastrar Novo Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(68, 11, 335, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setBounds(10, 77, 78, 14);
		frame.getContentPane().add(lblUsuario);
		
		tfUsuario = new JTextField();
		tfUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == arg0.VK_ENTER) {
					pfSenha.requestFocus();
					
				}
			}
		});
		tfUsuario.setBounds(140, 76, 227, 20);
		frame.getContentPane().add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(10, 114, 50, 14);
		frame.getContentPane().add(lblSenha);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha");
		lblConfirmarSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConfirmarSenha.setBounds(10, 139, 126, 14);
		frame.getContentPane().add(lblConfirmarSenha);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usu = tfUsuario.getText().toString();
				String senh = String.copyValueOf(pfSenha.getPassword());
				String senhCon= String.copyValueOf(pfConfirma.getPassword());
				Conexao c = new Conexao();
				
				
				if (usu.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe um usuario válido");
					tfUsuario.requestFocus();
					return;
				}
				
				if (senh.equals(senhCon)) {
					crudUsuarios crud = new crudUsuarios();
					crud.addusu(usu, senh);
					JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
					
					
				}if(!(senh.equals(senhCon))) {
					JOptionPane.showMessageDialog(null, "As senhas não estão se relacioando"
							+ "");
				}
				
				ResultSet tabela = null;
				  
				String sql = "select*from usuario where usuario=? and senha=?";
				try {
					PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
					stmt.setString(1, tfUsuario.getText().toString());
					stmt.setString(2, String.valueOf(pfSenha.getPassword()));
					tabela = stmt.executeQuery();
					if(tabela.next()) {
						frame.dispose();
						Principal.main(null);
					}else {

						JOptionPane.showMessageDialog(null, "Usuario e/ou Senha inválido(s)!");						
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnSalvar.setBounds(48, 206, 146, 35);
		frame.getContentPane().add(btnSalvar);
		
		pfSenha = new JPasswordField();
		pfSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_ENTER) {
					pfConfirma.requestFocus();
				}
			}
		});
		pfSenha.setBounds(140, 107, 227, 20);
		frame.getContentPane().add(pfSenha);
		
		pfConfirma = new JPasswordField();
		pfConfirma.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==e.VK_ENTER) {
					btnSalvar.doClick();
					
				}
			}
		});
		pfConfirma.setBounds(140, 138, 227, 20);
		frame.getContentPane().add(pfConfirma);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Login.main(null);
			}
		});
		btnCancelar.setBounds(244, 206, 146, 35);
		frame.getContentPane().add(btnCancelar);
	}
}
