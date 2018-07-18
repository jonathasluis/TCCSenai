package outraJanelas;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.awt.Toolkit;

public class EnviarEmail {

	private JFrame frmEnviarFeedback;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnviarEmail window = new EnviarEmail();
					window.frmEnviarFeedback.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EnviarEmail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEnviarFeedback = new JFrame();
		frmEnviarFeedback.setTitle("Enviar feedback");
		frmEnviarFeedback.setIconImage(Toolkit.getDefaultToolkit().getImage(EnviarEmail.class.getResource("/img/logo-pequena-sem-texto.png")));
		frmEnviarFeedback.setResizable(false);
		frmEnviarFeedback.setBounds(100, 100, 450, 300);
		frmEnviarFeedback.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEnviarFeedback.setLocationRelativeTo(null);
		frmEnviarFeedback.getContentPane().setLayout(null);
	}
	
	void enviarEmail(String de,String senha,String para,String titulo,String msg ) {
		//remetente
		boolean ssl = true;
		
		String smtp = "smtp.gmail.com";
		int porta = 445;

		try {
			SimpleEmail email = new SimpleEmail();
			email.setFrom(de);
			email.setSSLOnConnect(ssl);
			email.setHostName(smtp);
			email.setSmtpPort(porta);
			email.setAuthentication(de, senha);
			
			email.addTo(para);
			email.setSubject(titulo);
			email.setMsg(msg);
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
