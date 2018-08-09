package JanelasComtabil;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Grafico extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grafico frame = new Grafico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Grafico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1065, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		criarGrafico();
	
		
		setContentPane(contentPane);
		criarGrafico();
	}
	
	public void criarGrafico() {
	DefaultPieDataset pizza = new DefaultPieDataset();
		
		pizza.setValue("Produto", 5);
		pizza.setValue("Compra",5);
		
		JFreeChart grafico = ChartFactory.createPieChart3D("as", pizza);
		ChartPanel painel = new ChartPanel(grafico);
		add(painel);
	}
	
	

}
