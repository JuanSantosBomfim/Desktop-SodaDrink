package br.sodadrink.sp.view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import br.sodadrink.sp.DAO.CorredorDAO;
import br.sodadrink.sp.model.Corredor;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class CadastrarCorredor extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCorredor;

	JLabel lblNomeDoCorredor;

	public CadastrarCorredor(String titulo, String opcao, int id_corredor) {

		setBounds(100, 100, 450, 261);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 102, 255));
		panel.setBounds(0, 0, 446, 66);
		contentPanel.add(panel);

		JLabel label = new JLabel(titulo);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.BOLD, 22));
		label.setBounds(24, 21, 246, 23);
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 73, 414, 80);
		contentPanel.add(panel_1);

		txtCorredor = new JTextField();
		txtCorredor.setToolTipText("");
		txtCorredor.setText("");
		txtCorredor.setColumns(10);
		txtCorredor.setBounds(10, 29, 394, 31);
		panel_1.add(txtCorredor);

		lblNomeDoCorredor = new JLabel("Nome do Corredor");
		lblNomeDoCorredor.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNomeDoCorredor.setBounds(10, 11, 145, 14);
		panel_1.add(lblNomeDoCorredor);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 164, 414, 55);
		contentPanel.add(panel_2);

		JButton btnCadastrarCorredor = new JButton(opcao);
		btnCadastrarCorredor.setIcon(
				new ImageIcon(CadastrarCorredor.class.getResource("/br/sodadrink/sp/imagens/ic_check_18pt.png")));
		btnCadastrarCorredor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Corredor corredor = new Corredor();

				corredor.setNome(txtCorredor.getText().toString());
				corredor.setId_corredor(id_corredor);

				if (!txtCorredor.getText().toString().isEmpty()) {

					if (opcao.equals("Cadastrar")) {

						boolean resultado = CorredorDAO.cadastrarCorredorDAO(corredor);

						if (resultado == true) {

							JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
							dispose();

						} else {

							JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");

						}

					} else {

						boolean resultado = CorredorDAO.editarCorredorDAO(corredor);

						if (resultado == true) {

							JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
							dispose();

						} else {

							JOptionPane.showMessageDialog(null, "Erro ao Atualizar!");

						}

					}

				}else{
					
					JOptionPane.showMessageDialog(null, "Preencha o campo!");
					
				}

			}
		});

		btnCadastrarCorredor.setBounds(10, 11, 132, 30);
		panel_2.add(btnCadastrarCorredor);

		if (opcao.equals("Atualizar")) {

			preencherCampos(id_corredor);

		}

		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public void preencherCampos(int id_corredor) {

		List<Corredor> listaCorredor = new ArrayList<>();
		listaCorredor = CorredorDAO.listarCorredorPorIdDAO(id_corredor);

		String nomeCorredor = listaCorredor.get(0).getNome();

		txtCorredor.setText(nomeCorredor);
	}

}
