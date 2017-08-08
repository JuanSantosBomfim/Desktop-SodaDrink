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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import br.sodadrink.sp.DAO.EstanteDAO;
import br.sodadrink.sp.DAO.EstantePrateleiraDAO;
import br.sodadrink.sp.DAO.PrateleiraDAO;
import br.sodadrink.sp.controller.ComboboxFuncionalidade;
import br.sodadrink.sp.model.Estante;
import br.sodadrink.sp.model.EstantePrateleira;
import br.sodadrink.sp.model.ItemComboBox;
import br.sodadrink.sp.model.Prateleira;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastrarPrateleira extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtPrateleira;

	JComboBox<ItemComboBox> cbEstante;

	public CadastrarPrateleira(String titulo, String opcao, int id_prateleira) {
		setBounds(100, 100, 395, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 102, 255));
		panel.setBounds(0, 0, 382, 66);
		contentPanel.add(panel);

		JLabel label = new JLabel(titulo);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.BOLD, 22));
		label.setBounds(24, 21, 246, 23);
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 73, 359, 181);
		contentPanel.add(panel_1);

		txtPrateleira = new JTextField();
		txtPrateleira.setToolTipText("");
		txtPrateleira.setText("");
		txtPrateleira.setColumns(10);
		txtPrateleira.setBounds(10, 36, 335, 31);
		panel_1.add(txtPrateleira);

		JLabel lblNomeDaPrateleira = new JLabel("Nome da Prateleira");
		lblNomeDaPrateleira.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNomeDaPrateleira.setBounds(10, 11, 140, 14);
		panel_1.add(lblNomeDaPrateleira);

		cbEstante = new JComboBox<ItemComboBox>();
		cbEstante.setBounds(10, 112, 335, 31);
		panel_1.add(cbEstante);

		List<Estante> listEstante = new ArrayList<>();

		listEstante = EstanteDAO.listarEstanteDAO();

		if (listEstante.size() != 0) {

			for (int i = 0; listEstante.size() > i; i++) {

				int id = listEstante.get(i).getId_estante();
				String nome = listEstante.get(i).getNome();

				cbEstante.addItem(new ItemComboBox(id, nome, i));

			}

		} else {

			cbEstante.addItem(new ItemComboBox(0, "Nenhum Corredor Encontrado!", 0));

		}

		JLabel lblPertenceAEstante = new JLabel("Pertence a Estante:");
		lblPertenceAEstante.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPertenceAEstante.setBounds(10, 87, 140, 14);
		panel_1.add(lblPertenceAEstante);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 265, 358, 55);
		contentPanel.add(panel_2);

		JButton btnCadastrar = new JButton(opcao);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// **************CADASTRANDO A PRATELEIRA************
				Prateleira prateleira = new Prateleira();
				prateleira.setNome(txtPrateleira.getText().toString());

				ItemComboBox corredor_verifica = (ItemComboBox) cbEstante.getSelectedItem();
				int id_corredor_do_combobox_verifica = corredor_verifica.getId_item();

				if (id_corredor_do_combobox_verifica != 0) {

					if (!txtPrateleira.getText().toString().isEmpty()) {

						if (opcao.equals("Cadastrar")) {

							boolean resultado = PrateleiraDAO.cadastrarPrateleiraDAO(prateleira);

							// ********FIM DO CADASTRO DA
							// PRATELEIRA*************

							if (resultado == true) {

								ItemComboBox estante = (ItemComboBox) cbEstante.getSelectedItem();
								int id_estante_do_combobox = estante.getId_item();

								// Cria uma lista de estante para pegar o id da
								// estante
								List<Prateleira> listPrateleira = new ArrayList<>();
								listPrateleira = PrateleiraDAO.listarPrateleiraDAO();

								int id_prateleira = 0;
								int id_prateleira_banco = 0;

								// Pega o id da ultima estante cadastrada
								for (int i = 0; listPrateleira.size() > i; i++) {

									id_prateleira_banco = listPrateleira.get(i).getId_prateleira();

									if (id_prateleira <= id_prateleira_banco) {

										id_prateleira = id_prateleira_banco;

									}
								}

								EstantePrateleira estantePrateleira = new EstantePrateleira();
								estantePrateleira.setId_estante(id_estante_do_combobox);
								estantePrateleira.setId_prateleira(id_prateleira);

								boolean resultadoEstantePrateleira = EstantePrateleiraDAO
										.cadastrarEstantePrateleiraDAO(estantePrateleira);

								if (resultadoEstantePrateleira == true) {

									JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
									dispose();

								} else {

									JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");

								}

							} else {

								JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");

							}

						} else {

							prateleira.setId_prateleira(id_prateleira);

							boolean resultado = PrateleiraDAO.editarPrateleiraDAO(prateleira);

							if (resultado == true) {

								ItemComboBox estante = (ItemComboBox) cbEstante.getSelectedItem();
								int id_estante_do_combobox = estante.getId_item();

								EstantePrateleira estantePrateleira = new EstantePrateleira();
								estantePrateleira.setId_estante(id_estante_do_combobox);
								estantePrateleira.setId_prateleira(id_prateleira);

								boolean resultadoEstantePrateleira = EstantePrateleiraDAO
										.editarPrateleiraDaEstanteDAO(estantePrateleira);

								if (resultadoEstantePrateleira == true) {

									JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
									dispose();

								} else {

									JOptionPane.showMessageDialog(null, "Erro ao Atualizar!");

								}

							} else {

								JOptionPane.showMessageDialog(null, "Erro ao Atualizar!");

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
				new ImageIcon(CadastrarPrateleira.class.getResource("/br/sodadrink/sp/imagens/ic_check_18pt.png")));
		btnCadastrar.setBounds(10, 11, 132, 30);
		panel_2.add(btnCadastrar);

		if (opcao.equals("Atualizar")) {

			preencherCampos(id_prateleira);

		}

		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public void preencherCampos(int id_prateleira) {

		List<Prateleira> listPrateleira = new ArrayList<>();

		listPrateleira = PrateleiraDAO.listarPrateleiraPorIdDAO(id_prateleira);
		String nomePrateleria = listPrateleira.get(0).getNome();
		txtPrateleira.setText(nomePrateleria);

		List<EstantePrateleira> estantePrateleiras = new ArrayList<>();
		estantePrateleiras = EstantePrateleiraDAO.listarEstantePorIdPrateleiraDAO(id_prateleira);

		ComboboxFuncionalidade.selecionaComboboxUsandoValorDoBanco(cbEstante,
				estantePrateleiras.get(0).getId_estante());

	}

}
