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
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import br.sodadrink.sp.DAO.CorredorEstanteDAO;
import br.sodadrink.sp.DAO.EstantePrateleiraDAO;
import br.sodadrink.sp.DAO.EstoqueDAO;
import br.sodadrink.sp.model.EstanteCorredor;
import br.sodadrink.sp.model.EstantePrateleira;
import br.sodadrink.sp.model.Estoque;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class GerenciamentoEstoque extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	private JTextField textField_1;

	DefaultTableModel modelo;
	JTable tableEstoque;
	JPanel panelTabela;
	private JTextField textField_2;
	private JTextField textField_3;

	public GerenciamentoEstoque() {
		setClosable(true);
		setBounds(100, 100, 937, 641);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0, 102, 255));
		panel_1.setBounds(0, 0, 937, 66);
		panel.add(panel_1);

		JLabel lblGerenciamentoDoEstoque = new JLabel("Gerenciamento do Estoque");
		lblGerenciamentoDoEstoque.setForeground(Color.WHITE);
		lblGerenciamentoDoEstoque.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblGerenciamentoDoEstoque.setBounds(24, 21, 371, 23);
		panel_1.add(lblGerenciamentoDoEstoque);

		JPanel panel_2 = new JPanel();
		TitledBorder border2 = new TitledBorder("Buscar por");
		panel_2.setBorder(border2);
		panel_2.setLayout(null);
		panel_2.setBounds(10, 77, 901, 96);
		panel.add(panel_2);

		JButton button = new JButton("Buscar");
		button.setBounds(779, 48, 103, 30);
		panel_2.add(button);

		JLabel lblPrateleira = new JLabel("Prateleira");
		lblPrateleira.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPrateleira.setBounds(195, 20, 117, 30);
		panel_2.add(lblPrateleira);

		textField = new JTextField();
		textField.setToolTipText("");
		textField.setText("");
		textField.setColumns(10);
		textField.setBounds(10, 48, 175, 31);
		panel_2.add(textField);

		JLabel label_2 = new JLabel("Produto");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_2.setBounds(10, 30, 103, 14);
		panel_2.add(label_2);

		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setText("");
		textField_1.setColumns(10);
		textField_1.setBounds(195, 47, 175, 31);
		panel_2.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setToolTipText("");
		textField_2.setText("");
		textField_2.setColumns(10);
		textField_2.setBounds(380, 47, 175, 31);
		panel_2.add(textField_2);

		JLabel lblEstante = new JLabel("Estante");
		lblEstante.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEstante.setBounds(380, 20, 117, 30);
		panel_2.add(lblEstante);

		textField_3 = new JTextField();
		textField_3.setToolTipText("");
		textField_3.setText("");
		textField_3.setColumns(10);
		textField_3.setBounds(565, 47, 175, 31);
		panel_2.add(textField_3);

		JLabel lblCorredor = new JLabel("Corredor");
		lblCorredor.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCorredor.setBounds(565, 20, 117, 30);
		panel_2.add(lblCorredor);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 538, 901, 55);
		panel.add(panel_3);

		JButton btnAdicionarProdutoAo = new JButton("Adicionar Produto ao Estoque");
		btnAdicionarProdutoAo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastrarEstoque cadastrarEstoque = new CadastrarEstoque("Cadastrar Produto no Estoque", "Cadastrar",
						0);

			}
		});

		btnAdicionarProdutoAo.setIcon(
				new ImageIcon(GerenciamentoEstoque.class.getResource("/br/sodadrink/sp/imagens/ic_add_18pt.png")));
		btnAdicionarProdutoAo.setBounds(10, 11, 247, 30);
		panel_3.add(btnAdicionarProdutoAo);

		JButton button_2 = new JButton("Editar");
		button_2.setIcon(new ImageIcon(GerenciamentoEstoque.class.getResource("/br/sodadrink/sp/imagens/ic_mode_edit_18pt.png")));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int linha = tableEstoque.getSelectedRow();
				int coluna = 0;

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione um Produto no Estoque!");

				} else {

					String id_estoque = tableEstoque.getModel().getValueAt(linha, coluna).toString();

					CadastrarEstoque cadastrarEstoque = new CadastrarEstoque("Atualizar Estoque", "Atualizar", Integer.parseInt(id_estoque));

				}

			}
		});
		button_2.setBounds(267, 11, 100, 30);
		panel_3.add(button_2);

		JButton button_3 = new JButton("Excluir");
		button_3.setIcon(new ImageIcon(
				GerenciamentoEstoque.class.getResource("/br/sodadrink/sp/imagens/ic_delete_forever_18pt.png")));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int linha = tableEstoque.getSelectedRow();
				int coluna = 0;
				int colunaNome = 1;

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione um Produto no Estoque!");

				} else {

					String nome_produto = tableEstoque.getModel().getValueAt(linha, colunaNome).toString();

					int continua = JOptionPane.showConfirmDialog(null,
							"Deseja Excluir o produto: " + nome_produto + " do Estoque", "Atenção!",
							JOptionPane.YES_NO_OPTION);

					if (continua == 0) {

						String id_estoque = tableEstoque.getModel().getValueAt(linha, coluna).toString();

						boolean resultado = EstoqueDAO.excluirEstoqueDAO(Integer.parseInt(id_estoque));

						if (resultado == true) {

							JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
							recarregarTabela();

						} else {

							JOptionPane.showMessageDialog(null, "Erro ao Excluir!");

						}

					}

				}

			}
		});
		button_3.setBounds(380, 11, 100, 30);
		panel_3.add(button_3);

		JButton button_4 = new JButton("Atualizar Tabela");
		button_4.setIcon(new ImageIcon(
				GerenciamentoEstoque.class.getResource("/br/sodadrink/sp/imagens/ic_autorenew_18pt.png")));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				recarregarTabela();

			}
		});

		button_4.setBounds(490, 11, 160, 30);
		panel_3.add(button_4);

		panelTabela = new JPanel();
		TitledBorder border = new TitledBorder("Lista de Produtos em Estoque");
		panelTabela.setBorder(border);
		panelTabela.setBounds(10, 184, 901, 343);
		panel.add(panelTabela);

		exibirEstoque();

		this.setVisible(true);

	}

	public void exibirEstoque() {

		modelo = new DefaultTableModel();
		tableEstoque = new JTable();
		tableEstoque.setModel(modelo);

		modelo.addColumn("ID");
		modelo.addColumn("PRODUTO");
		modelo.addColumn("PRATELEIRA");
		modelo.addColumn("ESTANTE");
		modelo.addColumn("CORREDOR");

		tableEstoque.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableEstoque.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableEstoque.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableEstoque.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableEstoque.getColumnModel().getColumn(4).setPreferredWidth(200);

		tableEstoque.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {

				tableEstoque.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}
		});

		listarItensTabela();

		panelTabela.setLayout(null);

		JScrollPane scrollPanel = new JScrollPane(tableEstoque);
		scrollPanel.setBounds(10, 21, 881, 311);
		panelTabela.add(scrollPanel);

	}

	public void listarItensTabela() {

		List<Estoque> listEstoque = new ArrayList<>();
		listEstoque = EstoqueDAO.listarEstoqueDAO();	
		
		List<EstantePrateleira> listEstantePrateleiras = new ArrayList<>();
		String nomeEstante;	
		
		List<EstanteCorredor> listCorredorEstante = new ArrayList<>();
		String nomeCorredor;

		for (int i = 0; listEstoque.size() > i; i++) {
			
			int id_prateleira = listEstoque.get(i).getId_prateleira();	
					
			listEstantePrateleiras = EstantePrateleiraDAO.listarEstantePorIdPrateleiraDAO(id_prateleira);
			
			int id_estante = listEstantePrateleiras.get(0).getId_estante();
			nomeEstante = listEstantePrateleiras.get(0).getNomeEstante();
			
			listCorredorEstante = CorredorEstanteDAO.listarCorredorPorIdEstante(id_estante);
			nomeCorredor = listCorredorEstante.get(0).getNomeCorredor();
			
			modelo.addRow(new Object[] { listEstoque.get(i).getId_estoque(), listEstoque.get(i).getNomeProduto(),
					listEstoque.get(i).getNomePrateleira(),nomeEstante, nomeCorredor});

		}

	}

	public void recarregarTabela() {

		DefaultTableModel tm = (DefaultTableModel) tableEstoque.getModel();

		while (tableEstoque.getRowCount() > 0) {
			((DefaultTableModel) tableEstoque.getModel()).removeRow(0);
		}

		listarItensTabela();

	}
}
