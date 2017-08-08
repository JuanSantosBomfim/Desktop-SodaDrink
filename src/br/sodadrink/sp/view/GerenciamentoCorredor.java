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
import br.sodadrink.sp.DAO.CorredorDAO;
import br.sodadrink.sp.model.Corredor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class GerenciamentoCorredor extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	DefaultTableModel modelo;
	JTable tableCorredor;
	JPanel panelTabela;

	public GerenciamentoCorredor() {
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

		JLabel lblGerenciamentoDeCorredores = new JLabel("Gerenciamento de Corredores");
		lblGerenciamentoDeCorredores.setForeground(Color.WHITE);
		lblGerenciamentoDeCorredores.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblGerenciamentoDeCorredores.setBounds(24, 21, 349, 23);
		panel_1.add(lblGerenciamentoDeCorredores);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 322, 563, 55);
		panel.add(panel_2);

		JButton btnAdicionarCorredor = new JButton("Adicionar Corredor");
		btnAdicionarCorredor.setIcon(
				new ImageIcon(GerenciamentoCorredor.class.getResource("/br/sodadrink/sp/imagens/ic_add_18pt.png")));
		btnAdicionarCorredor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastrarCorredor cadastrarCorredor = new CadastrarCorredor("Cadastrar Corredor", "Cadastrar", 0);

			}
		});

		btnAdicionarCorredor.setBounds(10, 11, 159, 30);
		panel_2.add(btnAdicionarCorredor);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int linha = tableCorredor.getSelectedRow();
				int coluna = 0;

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione um corredor!");

				} else {

					String id_corredor = tableCorredor.getModel().getValueAt(linha, coluna).toString();

					CadastrarCorredor cadastrarCorredor = new CadastrarCorredor("Atualizar Corredor", "Atualizar",
							Integer.parseInt(id_corredor));

				}

			}
		});
		btnEditar.setIcon(new ImageIcon(
				GerenciamentoCorredor.class.getResource("/br/sodadrink/sp/imagens/ic_mode_edit_18pt.png")));
		btnEditar.setBounds(185, 11, 100, 30);
		panel_2.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int linha = tableCorredor.getSelectedRow();
				int coluna = 0;
				int colunaNome = 1;

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione um corredor!");

				} else {

					String nome_corredor = tableCorredor.getModel().getValueAt(linha, colunaNome).toString();

					int continua = JOptionPane.showConfirmDialog(null, "Deseja Excluir o corredor " + nome_corredor,
							"Atenção!", JOptionPane.YES_NO_OPTION);

					if (continua == 0) {

						String id_corredor = tableCorredor.getModel().getValueAt(linha, coluna).toString();

						boolean resultado = CorredorDAO.excluirCorredorDAO(Integer.parseInt(id_corredor));

						if (resultado == true) {

							JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
							recarregarTabela();

						} else {

							JOptionPane.showMessageDialog(null, "Erro ao Excluir, Este corredor está sendo utilizado!");

						}

					}

				}

			}
		});
		btnExcluir.setIcon(new ImageIcon(
				GerenciamentoCorredor.class.getResource("/br/sodadrink/sp/imagens/ic_delete_forever_18pt.png")));
		btnExcluir.setBounds(298, 11, 100, 30);
		panel_2.add(btnExcluir);

		JButton btnAtualizarTabela = new JButton("Atualizar Tabela");
		btnAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				recarregarTabela();

			}
		});
		btnAtualizarTabela.setIcon(new ImageIcon(
				GerenciamentoCorredor.class.getResource("/br/sodadrink/sp/imagens/ic_autorenew_18pt.png")));
		btnAtualizarTabela.setBounds(408, 11, 145, 30);
		panel_2.add(btnAtualizarTabela);

		panelTabela = new JPanel();
		panelTabela.setBounds(10, 77, 563, 237);
		panel.add(panelTabela);

		exibirCorredores();

		this.setVisible(true);

	}

	public void exibirCorredores() {

		modelo = new DefaultTableModel();
		tableCorredor = new JTable();
		tableCorredor.setModel(modelo);

		modelo.addColumn("ID");
		modelo.addColumn("NOME CORREDOR");

		tableCorredor.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableCorredor.getColumnModel().getColumn(1).setPreferredWidth(466);

		tableCorredor.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {

				tableCorredor.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}
		});

		listarItensTabela();

		panelTabela.setLayout(null);

		JScrollPane scrollPanel = new JScrollPane(tableCorredor);
		scrollPanel.setBounds(0, 0, 563, 237);
		panelTabela.add(scrollPanel);

	}

	public void listarItensTabela() {

		List<Corredor> listCorredor = new ArrayList<>();

		listCorredor = CorredorDAO.listarCorredorDAO();

		for (int i = 0; listCorredor.size() > i; i++) {

			modelo.addRow(new Object[] { listCorredor.get(i).getId_corredor(), listCorredor.get(i).getNome() });

		}

	}

	public void recarregarTabela() {

		DefaultTableModel tm = (DefaultTableModel) tableCorredor.getModel();

		while (tableCorredor.getRowCount() > 0) {
			((DefaultTableModel) tableCorredor.getModel()).removeRow(0);
		}

		listarItensTabela();

	}

}
