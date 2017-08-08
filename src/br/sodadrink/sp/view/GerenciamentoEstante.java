package br.sodadrink.sp.view;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import br.sodadrink.sp.DAO.CorredorEstanteDAO;
import br.sodadrink.sp.DAO.EstanteDAO;
import br.sodadrink.sp.model.EstanteCorredor;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class GerenciamentoEstante extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	DefaultTableModel modelo;
	JTable tableEstante;
	JPanel panelTabela;

	public GerenciamentoEstante() {
		setClosable(true);
		setBounds(100, 100, 599, 418);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0, 102, 255));
		panel_1.setBounds(0, 0, 583, 66);
		panel.add(panel_1);

		JLabel lblGerenciamentoDeEstantes = new JLabel("Gerenciamento de Estantes");
		lblGerenciamentoDeEstantes.setForeground(Color.WHITE);
		lblGerenciamentoDeEstantes.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblGerenciamentoDeEstantes.setBounds(24, 21, 349, 23);
		panel_1.add(lblGerenciamentoDeEstantes);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 322, 563, 55);
		panel.add(panel_2);

		JButton btnAdicionarEstante = new JButton("Adicionar Estante");
		btnAdicionarEstante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastrarEstante cadastrarEstante = new CadastrarEstante("Cadastrar Estante","Cadastrar",0);

			}
		});

		btnAdicionarEstante.setIcon(
				new ImageIcon(GerenciamentoEstante.class.getResource("/br/sodadrink/sp/imagens/ic_add_18pt.png")));
		btnAdicionarEstante.setBounds(10, 11, 159, 30);
		panel_2.add(btnAdicionarEstante);

		JButton button_1 = new JButton("Editar");
		button_1.setIcon(new ImageIcon(GerenciamentoEstante.class.getResource("/br/sodadrink/sp/imagens/ic_mode_edit_18pt.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int linha = tableEstante.getSelectedRow();
				int coluna = 0;

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione uma Estante!");

				} else {

					String id_estante = tableEstante.getModel().getValueAt(linha, coluna).toString();
					
					CadastrarEstante cadastrarEstante = new CadastrarEstante("Atualizar Estante", "Atualizar",
							Integer.parseInt(id_estante));

				}

			}
		});

		button_1.setBounds(185, 11, 100, 30);
		panel_2.add(button_1);

		JButton button_2 = new JButton("Excluir");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int linha = tableEstante.getSelectedRow();
				int coluna = 0;
				int colunaNome = 1;

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione uma Estante!");

				} else {

					String nome_estante = tableEstante.getModel().getValueAt(linha, colunaNome).toString();

					int continua = JOptionPane.showConfirmDialog(null, "Deseja Excluir a estante " + nome_estante,
							"Atenção!", JOptionPane.YES_NO_OPTION);

					if (continua == 0) {

						String id_estante = tableEstante.getModel().getValueAt(linha, coluna).toString();
						int id_estante_int = Integer.parseInt(id_estante);
						
						boolean resultado = CorredorEstanteDAO.excluirCorredorEstanteDAO(id_estante_int);
						
						boolean resultado2 = EstanteDAO.excluirEstanteDAO(id_estante_int);		
						
						if (resultado == true && resultado2 == true) {

							JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
							recarregarTabela();

						} else {

							JOptionPane.showMessageDialog(null, "Erro ao Excluir, Essa estante está sendo utilizada!");

						}

					}

				}

			}
		});
		button_2.setIcon(new ImageIcon(
				GerenciamentoEstante.class.getResource("/br/sodadrink/sp/imagens/ic_delete_forever_18pt.png")));
		button_2.setBounds(298, 11, 100, 30);
		panel_2.add(button_2);

		JButton button_3 = new JButton("Atualizar Tabela");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				recarregarTabela();

			}
		});
		button_3.setIcon(new ImageIcon(
				GerenciamentoEstante.class.getResource("/br/sodadrink/sp/imagens/ic_autorenew_18pt.png")));
		button_3.setBounds(408, 11, 145, 30);
		panel_2.add(button_3);

		panelTabela = new JPanel();
		panelTabela.setBounds(10, 77, 563, 237);
		panel.add(panelTabela);

		exibirEstantes();

		this.setVisible(true);
	}

	public void exibirEstantes() {

		modelo = new DefaultTableModel();
		tableEstante = new JTable();
		tableEstante.setModel(modelo);

		modelo.addColumn("ID");
		modelo.addColumn("NOME ESTANTE");
		modelo.addColumn("NOME CORREDOR");

		tableEstante.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableEstante.getColumnModel().getColumn(1).setPreferredWidth(233);
		tableEstante.getColumnModel().getColumn(1).setPreferredWidth(233);

		tableEstante.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {

				tableEstante.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}
		});

		listarItensTabela();

		panelTabela.setLayout(null);

		JScrollPane scrollPanel = new JScrollPane(tableEstante);
		scrollPanel.setBounds(0, 0, 563, 237);
		panelTabela.add(scrollPanel);

	}

	public void listarItensTabela() {

		List<EstanteCorredor> listEstanteCorredor = new ArrayList<>();

		listEstanteCorredor = CorredorEstanteDAO.listarEstanteCorredorDAO();

		for (int i = 0; listEstanteCorredor.size() > i; i++) {

			modelo.addRow(new Object[] { listEstanteCorredor.get(i).getId_estante(),
					listEstanteCorredor.get(i).getNomeEstante(), listEstanteCorredor.get(i).getNomeCorredor() });

		}

	}

	public void recarregarTabela() {

		DefaultTableModel tm = (DefaultTableModel) tableEstante.getModel();

		while (tableEstante.getRowCount() > 0) {
			((DefaultTableModel) tableEstante.getModel()).removeRow(0);
		}

		listarItensTabela();

	}

}
