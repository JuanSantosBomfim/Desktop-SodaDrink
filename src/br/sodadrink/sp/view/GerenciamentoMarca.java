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
import br.sodadrink.sp.DAO.MarcaDAO;
import br.sodadrink.sp.model.Marca;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class GerenciamentoMarca extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	DefaultTableModel modelo;
	JTable tableMarcas;
	JPanel panelTabela;

	public GerenciamentoMarca() {
		setClosable(true);
		setBounds(100, 100, 584, 418);

		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 583, 66);
		panelPrincipal.add(panelSuperior);

		JLabel lblGerenciamentoDeMarcas = new JLabel("Gerenciamento de Marcas");
		lblGerenciamentoDeMarcas.setForeground(Color.WHITE);
		lblGerenciamentoDeMarcas.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblGerenciamentoDeMarcas.setBounds(24, 21, 301, 23);
		panelSuperior.add(lblGerenciamentoDeMarcas);

		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setLayout(null);
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(10, 322, 546, 55);
		panelPrincipal.add(panelOpcoes);

		JButton btnAdicionarMarca = new JButton("Adicionar Marca");
		btnAdicionarMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CadastrarMarca cadastrarMarca = new CadastrarMarca("Cadastrar Marca", "Cadastrar", 0, 0);

			}
		});

		btnAdicionarMarca
				.setIcon(new ImageIcon(GerenciamentoMarca.class.getResource("/br/sodadrink/sp/imagens/ic_add_18pt.png")));
		btnAdicionarMarca.setBounds(10, 11, 145, 30);
		panelOpcoes.add(btnAdicionarMarca);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int coluna = 0;
				int linha = tableMarcas.getSelectedRow();

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione uma Marca!");

				} else {

					String id_marca_tabela = tableMarcas.getModel().getValueAt(linha, coluna).toString();

					CadastrarMarca cadastrarMarca = new CadastrarMarca("Atualizar Marca", "Atualizar",
							Integer.parseInt(id_marca_tabela), linha);

				}

			}
		});

		btnEditar
				.setIcon(new ImageIcon(GerenciamentoMarca.class.getResource("/br/sodadrink/sp/imagens/ic_mode_edit_18pt.png")));
		btnEditar.setBounds(165, 11, 100, 30);
		panelOpcoes.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int colunaId = 0;
				int linha = tableMarcas.getSelectedRow();

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione uma Marca!");

				} else {

					int colunaNome = 1;
					String nome_marca = tableMarcas.getModel().getValueAt(linha, colunaNome).toString();

					int continuar = JOptionPane.showConfirmDialog(null,
							"Tem certeza que deseja excluir a Marca " + nome_marca, "Atenção!",
							JOptionPane.YES_NO_OPTION);

					if (continuar == 0) {

						String id_marca_tabela = tableMarcas.getModel().getValueAt(linha, colunaId).toString();

						boolean resultado = MarcaDAO.excluirMarcaDAO(Integer.parseInt(id_marca_tabela));

						if (resultado == true) {

							JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
							recarregarTabela();

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao excluir, essa marca está sendo utilizada!");
						}
					}

				}

			}
		});
		btnExcluir
				.setIcon(new ImageIcon(GerenciamentoMarca.class.getResource("/br/sodadrink/sp/imagens/ic_delete_forever_18pt.png")));
		btnExcluir.setBounds(278, 11, 100, 30);
		panelOpcoes.add(btnExcluir);

		JButton btnAtualizarTabela = new JButton("Atualizar Tabela");
		btnAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				recarregarTabela();

			}
		});
		btnAtualizarTabela.setIcon(
				new ImageIcon(GerenciamentoMarca.class.getResource("/br/sodadrink/sp/imagens/ic_autorenew_18pt.png")));
		btnAtualizarTabela.setBounds(388, 11, 145, 30);
		panelOpcoes.add(btnAtualizarTabela);

		panelTabela = new JPanel();
		panelTabela.setBounds(10, 77, 546, 232);
		panelPrincipal.add(panelTabela);

		exibirMarcas();

		this.setVisible(true);
	}

	public void exibirMarcas() {

		modelo = new DefaultTableModel();
		tableMarcas = new JTable();
		tableMarcas.setModel(modelo);

		modelo.addColumn("ID");
		modelo.addColumn("NOME MARCA");

		tableMarcas.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableMarcas.getColumnModel().getColumn(1).setPreferredWidth(466);
		
		tableMarcas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				
				tableMarcas.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
		});

		listarItensTabela();

		panelTabela.setLayout(null);

		JScrollPane scrollPanel = new JScrollPane(tableMarcas);
		scrollPanel.setBounds(0, 0, 546, 232);
		panelTabela.add(scrollPanel);

	}

	public void listarItensTabela() {

		List<Marca> listMarca = new ArrayList<>();

		listMarca = MarcaDAO.listarMarcaDAO();

		for (int i = 0; listMarca.size() > i; i++) {

			modelo.addRow(new Object[] { listMarca.get(i).getId_marca(), listMarca.get(i).getMarca() });

		}

	}

	public void recarregarTabela() {

		DefaultTableModel tm = (DefaultTableModel) tableMarcas.getModel();

		while (tableMarcas.getRowCount() > 0) {
			((DefaultTableModel) tableMarcas.getModel()).removeRow(0);
		}

		listarItensTabela();

	}

}
