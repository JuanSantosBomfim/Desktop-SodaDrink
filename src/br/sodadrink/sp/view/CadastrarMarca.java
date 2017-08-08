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
import br.sodadrink.sp.DAO.MarcaDAO;
import br.sodadrink.sp.model.Marca;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class CadastrarMarca extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel panelPrincipal = new JPanel();
	private JTextField txtNomeMarca;

	public CadastrarMarca(String titulo, String opcao, int id_marca, int linha) {

		setBounds(100, 100, 450, 261);
		getContentPane().setLayout(new BorderLayout());
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 434, 66);
		panelPrincipal.add(panelSuperior);

		JLabel label = new JLabel(titulo);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.BOLD, 22));
		label.setBounds(24, 21, 246, 23);
		panelSuperior.add(label);

		JPanel panelDadosMarca = new JPanel();
		panelDadosMarca.setLayout(null);
		panelDadosMarca.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDadosMarca.setBounds(10, 73, 414, 80);
		panelPrincipal.add(panelDadosMarca);

		txtNomeMarca = new JTextField();
		txtNomeMarca.setToolTipText("");
		txtNomeMarca.setText("");
		txtNomeMarca.setColumns(10);
		txtNomeMarca.setBounds(10, 29, 394, 31);
		panelDadosMarca.add(txtNomeMarca);

		JLabel label_1 = new JLabel("Nome da Marca");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_1.setBounds(10, 11, 122, 14);
		panelDadosMarca.add(label_1);

		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setLayout(null);
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(10, 164, 414, 55);
		panelPrincipal.add(panelOpcoes);

		JButton btnEvento = new JButton(opcao);
		btnEvento.setIcon(new ImageIcon(CadastrarMarca.class.getResource("/br/sodadrink/sp/imagens/ic_check_18pt.png")));
		btnEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean continua = verificaCampo();

				if (continua == true) {

					Marca marca = new Marca();

					marca.setMarca(txtNomeMarca.getText().toString());
					
					if (opcao.equals("Atualizar")) {

						marca.setId_marca(id_marca);

						boolean retorno = MarcaDAO.editarMarcaDAO(marca);

						if (retorno == true) {

							JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao Atualizar!");
						}

					} else if (opcao.equals("Cadastrar")) {

						boolean retorno = MarcaDAO.cadastrarMarcaDAO(marca);

						if (retorno == true) {

							JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");
						}

					}

				} else {

					JOptionPane.showMessageDialog(null, "Preencha o Campo!");

				}

			}
		});

		btnEvento.setBounds(10, 11, 132, 30);
		panelOpcoes.add(btnEvento);

		if (opcao.equals("Atualizar")) {

			List<Marca> listMarca = new ArrayList<>();

			listMarca = MarcaDAO.listarMarcaPorIdDAO(id_marca);

			String marca = listMarca.get(0).getMarca();

			txtNomeMarca.setText(marca);

		}

		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
	}

	public boolean verificaCampo() {

		if (txtNomeMarca.getText().isEmpty()) {

			return false;
		} else {
			return true;
		}

	}

}
