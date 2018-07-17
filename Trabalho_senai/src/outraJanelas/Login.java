package outraJanelas;

import java.awt.Color;
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

import DAO.Usuario;
import banco.Conexao;
import crud.crudUsuarios;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {//
	Conexao c = new Conexao();
	
	
	String acesso="nao";
	private JFrame frmLogin;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;
	String armazenar ;
	private JButton btnEntrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo-pequena-sem-texto.png")));
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 309, 243);
		frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setResizable(false);
		frmLogin.setLocationRelativeTo(null);
		
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 273, 34);
		frmLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(10, 56, 54, 20);
		frmLogin.getContentPane().add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSenha.setBounds(10, 87, 54, 20);
		frmLogin.getContentPane().add(lblSenha);
		
		tfUsuario = new JTextField();
		tfUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					pfSenha.requestFocus();
				}
			}
		});
		tfUsuario.setBounds(65, 57, 183, 20);
		frmLogin.getContentPane().add(tfUsuario);
		tfUsuario.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnEntrar.doClick();
				}
			}
		});
		pfSenha.setBounds(65, 87, 183, 20);
		frmLogin.getContentPane().add(pfSenha);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(99, 181, 105, 23);
		frmLogin.getContentPane().add(btnEntrar);
		
		JLabel lblEsqueceuSuaSenha = new JLabel("Esqueceu sua Senha");
		lblEsqueceuSuaSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblEsqueceuSuaSenha.setForeground(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblEsqueceuSuaSenha.setForeground(Color.black);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!tfUsuario.getText().trim().equals("")) {
					String sistema = "";
					String senha = "";
					String destino = "";
					String titulo = "Nova senha";
					String novaSenha = gerarSenhaAleatoria();
					String msg = "sua nova senha é "+novaSenha;
					
					new crudUsuarios().updSenhaUsuario(novaSenha, tfUsuario.getText());
					new EnviarEmail().enviarEmail(sistema, senha, destino, titulo, msg);
				}else {
					JOptionPane.showMessageDialog(null, "");
				}
			}
		});
		lblEsqueceuSuaSenha.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEsqueceuSuaSenha.setBounds(178, 118, 105, 14);
		frmLogin.getContentPane().add(lblEsqueceuSuaSenha);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}			
		});
	}	
	
	public void logar() {
		ResultSet rs = null;
		String campoUsuario = tfUsuario.getText();
		String senha = String.valueOf(pfSenha.getPassword());
		  
		String sql = "select*from usuario where usuario=?";
		try {
			PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
			stmt.setString(1, campoUsuario);
			rs = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
			if(rs.next()) {
				String teste = rs.getString("usuario");
				
				if(teste.equals(campoUsuario)) {
						if (senha.equals(rs.getString("senha"))) {
							Usuario usuario = new Usuario();
							usuario.setEmail(rs.getString("email"));
							usuario.setIdUsuario(rs.getInt("idusuario"));
							usuario.setSenha(rs.getString("senha"));
							usuario.setUsuario(rs.getString("usuario"));
							Pergunta.usuario = usuario;
							JOptionPane.showMessageDialog(null, "Bem-Vindo "+usuario.getUsuario());
							frmLogin.dispose();
							Principal.main(null);
						}else {//if senha
							JOptionPane.showMessageDialog(null, "A senha inserida está incorreta.",null, JOptionPane.ERROR_MESSAGE);
							pfSenha.selectAll();
						}
					}
			}else {//if usuario
				JOptionPane.showMessageDialog(null, "<html>&emsp;&emsp;O usuario inserido não "
						+ "\ncorresponde a nenhuma conta.", null, JOptionPane.ERROR_MESSAGE);
				tfUsuario.selectAll();
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	

	}

	private static String gerarSenhaAleatoria() {
        int qtdeMaximaCaracteres = 8;
        String[] caracteres = {"1", "2", "4", "5", "6", "7", "8", "3",
        					   "9", "a", "b", "c", "d", "e", "f", "g",
        					   "h", "i", "j", "k", "l", "m", "n", "o",
        					   "p", "q", "r", "s", "t", "u", "v", "w",
        					   "x", "y", "z", "A", "B", "C", "D", "E", 
        					   "F", "G", "H", "I", "J", "K", "L", "M", 
        					   "N", "O", "P", "Q", "R", "S", "T", "U",
        					   "V", "W", "X", "Y", "Z", "ç", "Ç", "0" };
       
        StringBuilder senha = new StringBuilder();

        for (int i = 0; i < qtdeMaximaCaracteres; i++) {
            int posicao = (int) (Math.random() * caracteres.length);
            senha.append(caracteres[posicao]);
        }
        return senha.toString();
    }
}
