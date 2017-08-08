package br.sodadrink.sp.view;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import br.sodadrink.sp.DAO.CategoriaDAO;
import br.sodadrink.sp.DAO.EstoqueDAO;
import br.sodadrink.sp.DAO.FornecedorDAO;
import br.sodadrink.sp.DAO.MarcaDAO;
import br.sodadrink.sp.DAO.ProdutoDAO;
import br.sodadrink.sp.model.Categoria;
import br.sodadrink.sp.model.Fornecedor;
import br.sodadrink.sp.model.ItemComboBox;
import br.sodadrink.sp.model.Marca;
import br.sodadrink.sp.model.Produto;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.NumberFormat;

public class GerenciamentoProduto extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtProduto;
	private JTextField txtCodigoBarra;
	JPanel panelTabela;
	JTable tableProdutos;
	DefaultTableModel modelo;

	JComboBox<ItemComboBox> cbCategoria;
	JComboBox<ItemComboBox> cbMarca;
	JComboBox<ItemComboBox> cbFornecedor;

	public GerenciamentoProduto(int id_usuario_logado) {
		setClosable(true);
		setBounds(100, 100, 1002, 641);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 997, 66);
		panelPrincipal.add(panelSuperior);

		JLabel lblTitulo = new JLabel("Gerenciamento de Produtos");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblTitulo.setBounds(24, 21, 371, 23);
		panelSuperior.add(lblTitulo);

		JPanel panelBusca = new JPanel();
		panelBusca.setLayout(null);
		panelBusca.setBounds(10, 77, 966, 96);
		TitledBorder titledBorder = new TitledBorder("Buscar Por");
		panelBusca.setBorder(titledBorder);
		panelPrincipal.add(panelBusca);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Produto> listProduto = new ArrayList<>();

				String nomeProduto = txtProduto.getText().toString();
				String codBarra = txtCodigoBarra.getText().toString();

				ItemComboBox comboboxCategoria = (ItemComboBox) cbCategoria.getSelectedItem();
				String nomeCategoria = comboboxCategoria.getDescricao();

				ItemComboBox comboboxMarca = (ItemComboBox) cbMarca.getSelectedItem();
				String nomeMarca = comboboxMarca.getDescricao();

				ItemComboBox comboboxFornecedor = (ItemComboBox) cbFornecedor.getSelectedItem();
				String nomeFornecedor = comboboxFornecedor.getDescricao();

				listProduto = ProdutoDAO.listarProdutoPorTodosDadosDAO("nome", nomeProduto, "codBarra", codBarra,
						"descricao", nomeCategoria, "marca", nomeMarca, "razaoSocial", nomeFornecedor);

				recarregarTabela(listProduto);

			}
		});

		btnBuscar.setBounds(839, 48, 103, 30);
		panelBusca.add(btnBuscar);

		JLabel lblCodigoDeBarra = new JLabel("C\u00F3digo de Barra");
		lblCodigoDeBarra.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCodigoDeBarra.setBounds(195, 20, 117, 30);
		panelBusca.add(lblCodigoDeBarra);

		txtProduto = new JTextField();
		txtProduto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				List<Produto> listProduto = new ArrayList<>();

				listProduto = ProdutoDAO.listarProdutoPorDadosDAO("nome", txtProduto.getText().toString());

				recarregarTabela(listProduto);

			}
		});
		txtProduto.setToolTipText("");
		txtProduto.setText("");
		txtProduto.setColumns(10);
		txtProduto.setBounds(10, 48, 175, 31);
		panelBusca.add(txtProduto);

		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblProduto.setBounds(10, 30, 103, 14);
		panelBusca.add(lblProduto);

		txtCodigoBarra = new JTextField();
		txtCodigoBarra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				List<Produto> listProduto = new ArrayList<>();

				listProduto = ProdutoDAO.listarProdutoPorDadosDAO("codBarra", txtCodigoBarra.getText().toString());

				recarregarTabela(listProduto);

			}
		});

		txtCodigoBarra.setToolTipText("");
		txtCodigoBarra.setText("");
		txtCodigoBarra.setColumns(10);
		txtCodigoBarra.setBounds(195, 47, 175, 31);
		panelBusca.add(txtCodigoBarra);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCategoria.setBounds(380, 20, 89, 30);
		panelBusca.add(lblCategoria);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMarca.setBounds(525, 20, 128, 30);
		panelBusca.add(lblMarca);

		cbCategoria = new JComboBox<ItemComboBox>();

		List<Categoria> listCategoria = new ArrayList<>();
		listCategoria = CategoriaDAO.listarCategoriaDAO();

		for (int i = 0; listCategoria.size() > i; i++) {

			int idCategoria = listCategoria.get(i).getId_categoria();
			String nome = listCategoria.get(i).getDescricao();

			cbCategoria.addItem(new ItemComboBox(idCategoria, nome, i));

		}

		cbCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Produto> listProduto = new ArrayList<>();

				ItemComboBox comboboxCategoria = (ItemComboBox) cbCategoria.getSelectedItem();
				String nomeCategoria = comboboxCategoria.getDescricao();

				listProduto = ProdutoDAO.listarProdutoPorDadosDAO("c.descricao", nomeCategoria);

				recarregarTabela(listProduto);

			}
		});

		cbCategoria.setBounds(380, 47, 135, 31);
		panelBusca.add(cbCategoria);

		cbMarca = new JComboBox<ItemComboBox>();

		List<Marca> listMarca = new ArrayList<>();
		listMarca = MarcaDAO.listarMarcaDAO();

		for (int i = 0; listMarca.size() > i; i++) {

			int idmarca = listMarca.get(i).getId_marca();
			String nome = listMarca.get(i).getMarca();

			cbMarca.addItem(new ItemComboBox(idmarca, nome, i));

		}

		cbMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				List<Produto> listProduto = new ArrayList<>();

				ItemComboBox comboboxMarca = (ItemComboBox) cbMarca.getSelectedItem();
				String nomeMarca = comboboxMarca.getDescricao();

				listProduto = ProdutoDAO.listarProdutoPorDadosDAO("m.marca", nomeMarca);

				recarregarTabela(listProduto);

			}
		});

		cbMarca.setBounds(525, 47, 135, 31);
		panelBusca.add(cbMarca);

		cbFornecedor = new JComboBox<ItemComboBox>();

		List<Fornecedor> listFornecedor = new ArrayList<>();
		listFornecedor = FornecedorDAO.listarFornecedorDAO();

		for (int i = 0; listFornecedor.size() > i; i++) {

			int idFornecedor = listFornecedor.get(i).getIdFornecedor();
			String nome = listFornecedor.get(i).getRazaoSocial();

			cbFornecedor.addItem(new ItemComboBox(idFornecedor, nome, i));

		}

		cbFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Produto> listProduto = new ArrayList<>();

				ItemComboBox comboboxFornecedor = (ItemComboBox) cbFornecedor.getSelectedItem();
				String nomeFornecedor = comboboxFornecedor.getDescricao();

				listProduto = ProdutoDAO.listarProdutoPorDadosDAO("f.razaoSocial", nomeFornecedor);

				recarregarTabela(listProduto);

			}
		});
		cbFornecedor.setBounds(670, 47, 135, 31);
		panelBusca.add(cbFornecedor);

		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblFornecedor.setBounds(670, 20, 128, 30);
		panelBusca.add(lblFornecedor);

		JPanel panelOpcao = new JPanel();
		panelOpcao.setLayout(null);
		panelOpcao.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcao.setBounds(10, 538, 966, 55);
		panelPrincipal.add(panelOpcao);

		JButton btnComprarProduto = new JButton("Comprar Produto");
		btnComprarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CadastrarProduto comprarProdutos = new CadastrarProduto("Comprar Produtos", "Comprar", 0,
						id_usuario_logado);

			}
		});

		btnComprarProduto.setIcon(
				new ImageIcon(GerenciamentoProduto.class.getResource("/br/sodadrink/sp/imagens/ic_add_18pt.png")));
		btnComprarProduto.setBounds(10, 11, 180, 30);
		panelOpcao.add(btnComprarProduto);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linha = tableProdutos.getSelectedRow();
				int coluna = 0;

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione um Produto!");

				} else {

					String id_produto_linha = tableProdutos.getModel().getValueAt(linha, coluna).toString();

					CadastrarProduto comprarProdutos = new CadastrarProduto("Atualizar Produtos", "Atualizar",
							Integer.parseInt(id_produto_linha), id_usuario_logado);

				}

			}
		});
		btnEditar.setIcon(new ImageIcon(
				GerenciamentoProduto.class.getResource("/br/sodadrink/sp/imagens/ic_mode_edit_18pt.png")));
		btnEditar.setBounds(200, 11, 100, 30);
		panelOpcao.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int colunaId = 0;
				int linha = tableProdutos.getSelectedRow();

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione um Produto!");

				} else {

					int colunaNome = 1;
					String nome_produto = tableProdutos.getModel().getValueAt(linha, colunaNome).toString();

					int continuar = JOptionPane.showConfirmDialog(null,
							"Tem certeza que deseja excluir esse Produto " + nome_produto, "Atenção!",
							JOptionPane.YES_NO_OPTION);

					if (continuar == 0) {

						String id_produto_tabela = tableProdutos.getModel().getValueAt(linha, colunaId).toString();
						int id_produto = Integer.parseInt(id_produto_tabela);
						
						
						boolean resultado = EstoqueDAO.excluirEstoquePeloProdutoDAO(id_produto);
						
						boolean resultado1 = ProdutoDAO.excluirProdutoDAO(id_produto);

						if (resultado == true && resultado1 == true) {

							JOptionPane.showMessageDialog(null, "Excluido com sucesso!");

							List<Produto> listProduto = new ArrayList<>();
							listProduto = ProdutoDAO.listarProdutoDAO();
							recarregarTabela(listProduto);

						} else {
							JOptionPane.showMessageDialog(null, "Não foi possivel Excluir!");
						}
					}

				}

			}
		});

		btnExcluir.setIcon(new ImageIcon(
				GerenciamentoProduto.class.getResource("/br/sodadrink/sp/imagens/ic_delete_forever_18pt.png")));
		btnExcluir.setBounds(313, 11, 100, 30);
		panelOpcao.add(btnExcluir);

		JButton btnAtualizarTabela = new JButton("Atualizar Tabela");
		btnAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Produto> listProduto = new ArrayList<>();

				listProduto = ProdutoDAO.listarProdutoDAO();

				recarregarTabela(listProduto);

			}
		});
		btnAtualizarTabela.setIcon(new ImageIcon(
				GerenciamentoProduto.class.getResource("/br/sodadrink/sp/imagens/ic_autorenew_18pt.png")));
		btnAtualizarTabela.setBounds(423, 11, 160, 30);
		panelOpcao.add(btnAtualizarTabela);

		panelTabela = new JPanel();
		panelTabela.setBounds(10, 184, 966, 343);
		TitledBorder titledBorderTabela = new TitledBorder("Buscar Por");
		panelTabela.setBorder(titledBorderTabela);
		panelPrincipal.add(panelTabela);

		exibirTabela();

		this.setVisible(true);

	}

	public void exibirTabela() {

		// Adicionar Tabela de Produtos

		modelo = new DefaultTableModel();

		modelo.addColumn("ID");
		modelo.addColumn("CODIGO DE BARRA");
		modelo.addColumn("PRODUTO");
		modelo.addColumn("CATEGORIA");
		modelo.addColumn("MARCA");
		modelo.addColumn("FORNECEDOR");
		modelo.addColumn("VALOR COMPRA");
		modelo.addColumn("VALOR VENDA");
		modelo.addColumn("QTD");

		tableProdutos = new JTable();
		tableProdutos.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {

				tableProdutos.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}
		});

		tableProdutos.setModel(modelo);

		tableProdutos.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableProdutos.getColumnModel().getColumn(1).setPreferredWidth(130);
		tableProdutos.getColumnModel().getColumn(2).setPreferredWidth(130);
		tableProdutos.getColumnModel().getColumn(3).setPreferredWidth(120);
		tableProdutos.getColumnModel().getColumn(4).setPreferredWidth(120);
		tableProdutos.getColumnModel().getColumn(5).setPreferredWidth(120);
		tableProdutos.getColumnModel().getColumn(6).setPreferredWidth(90);
		tableProdutos.getColumnModel().getColumn(7).setPreferredWidth(90);
		tableProdutos.getColumnModel().getColumn(8).setPreferredWidth(70);

		panelTabela.setLayout(null);

		List<Produto> listProduto = new ArrayList<>();

		listProduto = ProdutoDAO.listarProdutoDAO();

		exibirItensTabela(listProduto);

		JScrollPane scrollPanel = new JScrollPane(tableProdutos);
		scrollPanel.setBounds(10, 21, 946, 310);
		panelTabela.add(scrollPanel);

	}

	public void exibirItensTabela(List<Produto> listProduto) {

		Locale ptBr = new Locale("pt", "BR");
		NumberFormat formato = NumberFormat.getCurrencyInstance(ptBr);

		for (int i = 0; listProduto.size() > i; i++) {

			modelo.addRow(new Object[] { listProduto.get(i).getId_produto(), listProduto.get(i).getCodBarra(),
					listProduto.get(i).getNome(), listProduto.get(i).getNomeCategoria(),
					listProduto.get(i).getNomeMarca(), listProduto.get(i).getNomeFornecedor(),
					formato.format(listProduto.get(i).getValorCompra()),
					formato.format(listProduto.get(i).getValorVenda()), listProduto.get(i).getQuantidadeEstoque() });

		}
	}

	public void recarregarTabela(List<Produto> listProduto) {

		DefaultTableModel tm = (DefaultTableModel) tableProdutos.getModel();

		while (tableProdutos.getRowCount() > 0) {
			((DefaultTableModel) tableProdutos.getModel()).removeRow(0);
		}

		exibirItensTabela(listProduto);

	}

}
