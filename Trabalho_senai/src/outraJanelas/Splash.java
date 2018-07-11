package outraJanelas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Splash {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash window = new Splash();
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
	public Splash() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 289);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setUndecorated(true); //tirar os botoes de cima e a borda
		frame.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f)); //tranparente
		frame.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));				  //tranparente
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 450, 283);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(Splash.class.getResource("/img/logo-pequena-sem-texto.png")));
		frame.getContentPane().add(label);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(0, 277, 450, 12);
		progressBar.setForeground(UIManager.getColor("ProgressBar.foreground"));
		progressBar.setStringPainted(true);
		progressBar.setMaximum(50);
		frame.getContentPane().add(progressBar);
		
		new Thread(proximaJanela).start();	//inicia a tarefa paralela
	}
	
	Runnable proximaJanela = new Runnable() {	
		@Override
		public void run() {
			int x=0;
			try {
				while(true) {
					Thread.sleep(100);
					 x++;
					 progressBar.setValue(x);
					 if(x==50) {
						 break;
					 }
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.dispose();
			Login.main(null);
		}
	};
	private JProgressBar progressBar;

}
