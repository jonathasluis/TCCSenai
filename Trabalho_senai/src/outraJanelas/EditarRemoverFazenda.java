package outraJanelas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import DAO.Fazenda;
import Imagem.ImagemEditarFazenda;
import crud.CrudFazenda;

public class EditarRemoverFazenda {

	private JFrame frame;
	private JTextField textField;
	private JTextField tamanho;
	private JTextField escritura;
	private JTextField pro;
	static Fazenda fazenda;
	public static JPanel panel;
	public static JLabel label_6;
	ImagemEditarFazenda i = new ImagemEditarFazenda();
	 public static File img;
	 private JEditorPane des;
	 private JButton button_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarRemoverFazenda window = new EditarRemoverFazenda();
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
	public EditarRemoverFazenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 436);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Nova Fazenda");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(10, 11, 378, 23);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(10, 55, 378, 14);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		textField.setBounds(10, 69, 378, 30);
		frame.getContentPane().add(textField);
		
		tamanho = new JTextField();
		tamanho.setColumns(10);
		tamanho.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		tamanho.setBounds(10, 132, 175, 30);
		frame.getContentPane().add(tamanho);
		
		JLabel label_2 = new JLabel("Tamanho:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setBounds(10, 118, 175, 14);
		frame.getContentPane().add(label_2);
		
		escritura = new JTextField();
		escritura.setColumns(10);
		escritura.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		escritura.setBounds(213, 132, 175, 30);
		frame.getContentPane().add(escritura);
		
		JLabel label_3 = new JLabel("Escritura:");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(213, 118, 175, 14);
		frame.getContentPane().add(label_3);
		
		pro = new JTextField();
		pro.setColumns(10);
		pro.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		pro.setBounds(10, 195, 378, 30);
		frame.getContentPane().add(pro);
		
		JLabel label_4 = new JLabel("Propriet\u00E1rio:");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_4.setBounds(10, 181, 378, 14);
		frame.getContentPane().add(label_4);
		
		des = new JEditorPane();
		des.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		des.setBounds(10, 258, 378, 78);
		frame.getContentPane().add(des);
		
		JLabel label_5 = new JLabel("Descri\u00E7\u00E3o:");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_5.setBounds(10, 243, 378, 14);
		frame.getContentPane().add(label_5);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(398, 11, 306, 288);
		frame.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		label_6 = new JLabel("");
		panel.add(label_6, "name_32088458593375");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(398, 299, 306, 33);
		frame.getContentPane().add(panel_1);
		
		JButton button = new JButton("Foto");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				img = i.selecionaImg();
				i.abrirImagem(img);
			}
		});
		panel_1.add(button);
		
		button_1 = new JButton("Cancelar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setBounds(417, 373, 89, 23);
		frame.getContentPane().add(button_1);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrudFazenda remover = new CrudFazenda();
				int resposta = JOptionPane.showConfirmDialog(null, "voce deseja remover essa fazenda ", "alerta", 
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				
						if(resposta == JOptionPane.YES_OPTION) {
							remover.delfazenda(fazenda.getId());
							JOptionPane.showMessageDialog(null, "fazenda removida com sucesso");
							frame.dispose();
							VisualizarFazendas.btnProcurar.doClick();
						}else
							return;
			}
		});
		btnRemover.setBounds(516, 373, 89, 23);
		frame.getContentPane().add(btnRemover);
		
		JButton button_3 = new JButton("Salvar");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().trim().equals("")) {
					if(!tamanho.getText().trim().equals("")) {
						if(! textField.getText().trim().equals("")) {
							new CrudFazenda().updfazenda(textField.getText(), tamanho.getText(),
									pro.getText(), escritura.getText(), des.getText(), i.getImagem(), fazenda.getId());
							button_1.doClick();
							VisualizarFazendas.btnProcurar.doClick();
						}else {
							pro.requestFocus();
							JOptionPane.showMessageDialog(null, "Insira o nome do propietario!");
						}
					}else {
						tamanho.requestFocus();
						JOptionPane.showMessageDialog(null, "Insira o tamanho!");
					}
				}else {
					textField.requestFocus();
					JOptionPane.showMessageDialog(null, "Insira um nome!");
				}
			}
		});
		button_3.setBounds(615, 373, 89, 23);
		frame.getContentPane().add(button_3);
		
		tela();
	}
	
	void tela() {
		textField.setText(fazenda.getNome());
		tamanho.setText(fazenda.getTamanho());
		pro.setText(fazenda.getPropi());
		des.setText(fazenda.getDescri());
		escritura.setText(fazenda.getEscri());
		
		abrirImagem(fazenda.getImg());
	}
	
public void abrirImagem(Object source) {//abra a imagem
		
		if(source instanceof File) {
			ImageIcon icon = new ImageIcon();
			icon.setImage(icon.getImage().getScaledInstance(panel.getWidth(), panel.getHeight(), 100));
			label_6.setIcon(icon);
		
		}else if(source instanceof byte[]) {
			ImageIcon icon = new ImageIcon(fazenda.getImg());
			icon.setImage(icon.getImage().getScaledInstance(panel.getWidth(), panel.getHeight(), 100));
			label_6.setIcon(icon);
		}
	}
}
