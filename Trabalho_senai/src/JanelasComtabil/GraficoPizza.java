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
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraficoPizza extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraficoPizza frame = new GraficoPizza();
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
	public GraficoPizza() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 915, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setResizable(false);
		setLocationRelativeTo(null);
		criarGrafico();
	
		
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Sair");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				
			}
		});
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		criarGrafico();
	}
	
	public void criarGrafico() {
	DefaultPieDataset pizza = new DefaultPieDataset();
	
		pizza.setValue("Gasto", Float.parseFloat(Total.valorGasto.getText()));
		pizza.setValue("Receita", Float.parseFloat(Total.valorReceita.getText()));
		
		
		JFreeChart grafico = ChartFactory.createPieChart3D("Total", pizza, true, true, true);
		ChartPanel painel = new ChartPanel(grafico);
		getContentPane().add(painel);
	}
	
	

}