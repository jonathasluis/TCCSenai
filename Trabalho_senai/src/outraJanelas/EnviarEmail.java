package outraJanelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
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

}
