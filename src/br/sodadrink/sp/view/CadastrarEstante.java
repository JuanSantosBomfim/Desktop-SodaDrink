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
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.TitledBorder;
import br.sodadrink.sp.DAO.CorredorDAO;
import br.sodadrink.sp.DAO.CorredorEstanteDAO;
import br.sodadrink.sp.DAO.EstanteDAO;
import br.sodadrink.sp.controller.ComboboxFuncionalidade;
import br.sodadrink.sp.model.Corredor;
import br.sodadrink.sp.model.Estante;
import br.sodadrink.sp.model.EstanteCorredor;
import br.sodadrink.sp.model.ItemComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarEstante extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtEstante;

	JComboBox<ItemComboBox> cbCorredor;

	public CadastrarEstante(String titulo, String opcao, int id_estante) {
		setBounds(100, 100, 371, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 102, 255));
		panel.setBounds(0, 0, 372, 66);
		contentPanel.add(panel);

		JLabel label = new JLabel(titulo);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.BOLD, 22));
		label.setBounds(24, 21, 246, 23);
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 265, 335, 55);
		contentPanel.add(panel_1);

		JButton btnCadastrar = new JButton(opcao);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// **************CADASTRO DA ESTANTE****************
				Estante estante = new Estante();

				estante.setNome(txtEstante.getText().toString());

				ItemComboBox corredor_verifica = (ItemComboBox) cbCorredor.getSelectedItem();
				int id_corredor_do_combobox_verifica = corredor_verifica.getId_item();

				if (id_corredor_do_combobox_verifica != 0) {

					if (!txtEstante.getText().toString().isEmpty()) {

						if (opcao.equals("Cadastrar")) {

							boolean resultado = EstanteDAO.cadastrarEstanteDAO(estante);

							// ***********FIM CADASTRO ESTANTE**************

							if (resultado == true) {

								// **************CADASTRO DA ESTANTE NO
								// CORREDOR**************

								ItemComboBox corredor = (ItemComboBox) cbCorredor.getSelectedItem();
								int id_corredor_do_combobox = corredor.getId_item();

								// Cria uma lista de estante para pegar o id da
								// estante
								List<Estante> listEstante = new ArrayList<>();
								listEstante = EstanteDAO.listarEstanteDAO();

								int id_estante = 0;
								int id_estante_banco = 0;

								// Pega o id da ultima estante cadastrada
								for (int i = 0; listEstante.size() > i; i++) {

									id_estante_banco = listEstante.get(i).getId_estante();

									if (id_estante <= id_estante_banco) {

										id_estante = id_estante_banco;

									}
								}

								EstanteCorredor estanteCorredor = new EstanteCorredor();

								estanteCorredor.setId_corredor(id_corredor_do_combobox);
								estanteCorredor.setId_estante(id_estante);

								boolean resultadoEstanteCorredor = CorredorEstanteDAO
										.cadastrarCorredorEstanteDAO(estanteCorredor);

								// **************FIM CADASTRO ESTANTE NO
								// CORREDOR**********

								if (resultadoEstanteCorredor == true) {

									JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
									dispose();

								} else {

									JOptionPane.showMessageDialog(null, "Erro ao Casdastrar!");

								}

							} else {

								JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");

							}

						} else {

							estante.setId_estante(id_estante);

							boolean resultado = EstanteDAO.editarEstanteDAO(estante);

							if (resultado == true) {

								ItemComboBox corredor = (ItemComboBox) cbCorredor.getSelectedItem();
								int id_corredor_do_combobox = corredor.getId_item();

								EstanteCorredor estanteCorredor = new EstanteCorredor();

								estanteCorredor.setId_corredor(id_corredor_do_combobox);
								estanteCorredor.setId_estante(id_estante);

								boolean resultadoEstanteCorredor = CorredorEstanteDAO
										.editarCorredorDaEstanteDAO(estanteCorredor);

								if (resultadoEstanteCorredor == true) {

									JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
									dispose();

								} else {

									JOptionPane.showMessageDialog(null, "Erro ao Atualizar!");

								}

							} else {

								JOptionPane.showMessageDialog(null, "Erro ao Atualizar");

							}

						}

					} else {

						JOptionPane.showMessageDialog(null, "Preencha o campo!");

					}

				} else {

					JOptionPane.showMessageDialog(null, "Não há nenhum corredor selecionado!");

				}

			}
		});

		btnCadastrar.setIcon(
				new ImageIcon(CadastrarEstante.class.getResource("/br/sodadrink/sp/imagens/ic_check_18pt.png")));
		btnCadastrar.setBounds(10, 11, 132, 30);
		panel_1.add(btnCadastrar);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 73, 335, 181);
		contentPanel.add(panel_2);

		txtEstante = new JTextField();
		txtEstante.setToolTipText("");
		txtEstante.setText("");
		txtEstante.setColumns(10);
		txtEstante.setBounds(10, 29, 317, 31);
		panel_2.add(txtEstante);

		JLabel lblNomeDaEstante = new JLabel("Nome da Estante");
		lblNomeDaEstante.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNomeDaEstante.setBounds(10, 11, 122, 14);
		panel_2.add(lblNomeDaEstante);

		cbCorredor = new JComboBox<ItemComboBox>();
		cbCorredor.setBounds(10, 112, 317, 31);
		panel_2.add(cbCorredor);

		List<Corredor> listCorredor = new ArrayList<>();

		listCorredor = CorredorDAO.listarCorredorDAO();

		if (listCorredor.size() != 0) {

			for (int i = 0; listCorredor.size() > i; i++) {

				int id = listCorredor.get(i).getId_corredor();
				String nome = listCorredor.get(i).getNome();

				cbCorredor.addItem(new ItemComboBox(id, nome, i));

			}

		} else {

			cbCorredor.addItem(new ItemComboBox(0, "Nenhum Corredor Encontrado!", 0));

		}

		JLabel lblPertenceAoCorredor = new JLabel("Pertence ao corredor:");
		lblPertenceAoCorredor.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPertenceAoCorredor.setBounds(10, 87, 140, 14);
		panel_2.add(lblPertenceAoCorredor);

		if (opcao.equals("Atualizar")) {

			preencherCampos(id_estante);

		}

		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void preencherCampos(int id_estante) {

		List<Estante> listaEstante = new ArrayList<>();
		listaEstante = EstanteDAO.listarEstantePorIdDAO(id_estante);

		txtEstante.setText(listaEstante.get(0).getNome());

		List<EstanteCorredor> listaEstanteCorredor = new ArrayList<>();
		listaEstanteCorredor = CorredorEstanteDAO.listarCorredorPorIdEstante(id_estante);

		ComboboxFuncionalidade.selecionaComboboxUsandoValorDoBanco(cbCorredor,
				listaEstanteCorredor.get(0).getId_corredor());

	}

}
