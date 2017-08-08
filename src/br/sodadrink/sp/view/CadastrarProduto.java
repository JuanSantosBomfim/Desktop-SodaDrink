package br.sodadrink.sp.view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import br.sodadrink.sp.DAO.CategoriaDAO;
import br.sodadrink.sp.DAO.FornecedorDAO;
import br.sodadrink.sp.DAO.HistoricoCompraDAO;
import br.sodadrink.sp.DAO.MarcaDAO;
import br.sodadrink.sp.DAO.ProdutoDAO;
import br.sodadrink.sp.DAO.UsuarioDAO;
import br.sodadrink.sp.controller.ComboboxFuncionalidade;
import br.sodadrink.sp.controller.TamanhoCampo;
import br.sodadrink.sp.model.Categoria;
import br.sodadrink.sp.model.Compra;
import br.sodadrink.sp.model.Fornecedor;
import br.sodadrink.sp.model.ItemComboBox;
import br.sodadrink.sp.model.Marca;
import br.sodadrink.sp.model.Produto;
import br.sodadrink.sp.model.Usuario;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastrarProduto extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel panelPrincipal = new JPanel();
	private JTextField txtNomeProduto;
	private JTextField txtCodigoBarra;
	private JTextField txtQtd;
	private JTextField txtPeso;
	private JTextField txtValorCompra;
	// JLabel lblImagemProduto = new JLabel("");
	JComboBox<ItemComboBox> cbFornecedor;
	JComboBox<ItemComboBox> cbMarca;
	JComboBox<ItemComboBox> cbCategoria;

	String caminhoDoArquivo = "";
	JFileChooser escolherArquivo;
	List<Fornecedor> listFornecedor = new ArrayList<>();
	List<Categoria> listCategoria = new ArrayList<>();

	public CadastrarProduto(String titulo, String opcao, int id_produto, int id_usuario) {
		
		List<Usuario> listaUsuario = new ArrayList<>();
		listaUsuario = UsuarioDAO.listarUsuarioPorIdDAO(id_usuario);
		String nomeUsuario = listaUsuario.get(0).getNome();

		setBounds(100, 100, 517, 426);
		getContentPane().setLayout(new BorderLayout());
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 514, 66);
		panelPrincipal.add(panelSuperior);

		JLabel lblComprarProduto = new JLabel(titulo);
		lblComprarProduto.setForeground(Color.WHITE);
		lblComprarProduto.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblComprarProduto.setBounds(24, 21, 246, 23);
		panelSuperior.add(lblComprarProduto);

		JPanel panelDadosProduto = new JPanel();
		panelDadosProduto.setLayout(null);
		panelDadosProduto.setBounds(10, 77, 482, 232);
		TitledBorder titledBorderProduto = new TitledBorder("Dados Produto");
		panelDadosProduto.setBorder(titledBorderProduto);
		panelPrincipal.add(panelDadosProduto);

		txtNomeProduto = new JTextField();
		txtNomeProduto.setDocument(new TamanhoCampo(40));
		txtNomeProduto.setColumns(10);
		txtNomeProduto.setBounds(20, 51, 217, 31);
		panelDadosProduto.add(txtNomeProduto);

		txtCodigoBarra = new JTextField();
		txtCodigoBarra.setDocument(new TamanhoCampo(12));
		txtCodigoBarra.setColumns(10);
		txtCodigoBarra.setBounds(247, 51, 217, 31);

		camposNumericos(txtCodigoBarra);

		panelDadosProduto.add(txtCodigoBarra);

		txtQtd = new JTextField();
		txtQtd.setDocument(new TamanhoCampo(10));
		txtQtd.setColumns(10);
		txtQtd.setBounds(247, 110, 104, 31);

		camposNumericos(txtQtd);

		panelDadosProduto.add(txtQtd);

		txtPeso = new JTextField();
		txtPeso.setDocument(new TamanhoCampo(8));
		txtPeso.setColumns(10);
		txtPeso.setBounds(361, 110, 103, 31);

		camposNumericos(txtPeso);

		panelDadosProduto.add(txtPeso);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNome.setBounds(20, 33, 63, 14);
		panelDadosProduto.add(lblNome);

		JLabel lblCodigoDeBarra = new JLabel("C\u00F3digo de Barra");
		lblCodigoDeBarra.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCodigoDeBarra.setBounds(247, 28, 132, 25);
		panelDadosProduto.add(lblCodigoDeBarra);

		JLabel lblQtd = new JLabel("Qtd");
		lblQtd.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblQtd.setBounds(247, 94, 75, 14);
		panelDadosProduto.add(lblQtd);

		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPeso.setBounds(361, 93, 53, 14);
		panelDadosProduto.add(lblPeso);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMarca.setBounds(20, 162, 82, 14);
		panelDadosProduto.add(lblMarca);

		cbMarca = new JComboBox<ItemComboBox>();
		cbMarca.setBounds(20, 180, 140, 31);
		panelDadosProduto.add(cbMarca);

		List<Marca> listMarca = new ArrayList<>();
		listMarca = MarcaDAO.listarMarcaDAO();

		for (int i = 0; listMarca.size() > i; i++) {

			int idmarca = listMarca.get(i).getId_marca();
			String nome = listMarca.get(i).getMarca();

			cbMarca.addItem(new ItemComboBox(idmarca, nome, i));

		}

		JLabel lblFornecedor;
		lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblFornecedor.setBounds(170, 162, 96, 14);
		panelDadosProduto.add(lblFornecedor);

		cbFornecedor = new JComboBox<ItemComboBox>();
		cbFornecedor.setBounds(170, 180, 140, 31);
		panelDadosProduto.add(cbFornecedor);

		listFornecedor = new ArrayList<>();
		listFornecedor = FornecedorDAO.listarFornecedorDAO();

		for (int i = 0; listFornecedor.size() > i; i++) {

			int idFornecedor = listFornecedor.get(i).getIdFornecedor();
			String nome = listFornecedor.get(i).getNomeFantasia();

			cbFornecedor.addItem(new ItemComboBox(idFornecedor, nome, i));

		}

		txtValorCompra = new JTextField();
		txtValorCompra.setDocument(new TamanhoCampo(8));
		txtValorCompra.setColumns(10);
		txtValorCompra.setBounds(20, 110, 217, 31);

		camposNumericos(txtValorCompra);

		panelDadosProduto.add(txtValorCompra);

		JLabel lblValorCompra = new JLabel("Valor de Compra");
		lblValorCompra.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblValorCompra.setBounds(20, 93, 125, 14);
		panelDadosProduto.add(lblValorCompra);

		/*
		 * JButton btnEscolherImagem = new JButton("Escolher Imagem");
		 * btnEscolherImagem.addActionListener(new ActionListener() { public
		 * void actionPerformed(ActionEvent arg0) {
		 * 
		 * escolherArquivo = new JFileChooser();
		 * 
		 * escolherArquivo.setCurrentDirectory(new
		 * File(System.getProperty("user.home"))); FileNameExtensionFilter
		 * filtro = new FileNameExtensionFilter("*.Images", "jpg", "png");
		 * escolherArquivo.addChoosableFileFilter(filtro);
		 * 
		 * int resultado = escolherArquivo.showSaveDialog(null);
		 * 
		 * if (resultado == JFileChooser.APPROVE_OPTION) {
		 * 
		 * File arquivoEscolhido = escolherArquivo.getSelectedFile();
		 * caminhoDoArquivo = arquivoEscolhido.getAbsolutePath();
		 * lblImagemProduto.setIcon(ResizeImage(caminhoDoArquivo));
		 * 
		 * // Mostra o caminho do arquivo //JOptionPane.showMessageDialog(null,
		 * caminhoDoArquivo);
		 * 
		 * } else if (resultado == JFileChooser.CANCEL_OPTION) {
		 * JOptionPane.showMessageDialog(null, "Selecione um arquivo!"); }
		 * 
		 * } });
		 * 
		 * btnEscolherImagem.setBounds(503, 188, 230, 23);
		 * panelDadosProduto.add(btnEscolherImagem);
		 */

		/*
		 * lblImagemProduto.setBackground(new Color(255, 255, 255));
		 * lblImagemProduto.setBounds(503, 33, 230, 149);
		 * panelDadosProduto.add(lblImagemProduto);
		 */

		cbCategoria = new JComboBox<ItemComboBox>();
		cbCategoria.setBounds(320, 180, 144, 31);
		panelDadosProduto.add(cbCategoria);

		listCategoria = new ArrayList<>();
		listCategoria = CategoriaDAO.listarCategoriaDAO();

		for (int i = 0; listCategoria.size() > i; i++) {

			int idCategoria = listCategoria.get(i).getId_categoria();
			String nome = listCategoria.get(i).getDescricao();

			cbCategoria.addItem(new ItemComboBox(idCategoria, nome, i));

		}

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCategoria.setBounds(320, 160, 96, 19);
		panelDadosProduto.add(lblCategoria);

		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setLayout(null);
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(10, 320, 482, 55);
		panelPrincipal.add(panelOpcoes);

		JButton btnEvento = new JButton(opcao);
		btnEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean continua = verificarCampos();

				if (continua == true) {

					/* if(!caminhoDoArquivo.equals("")){ */

					ItemComboBox comoboxFornecedor = (ItemComboBox) cbFornecedor.getSelectedItem();
					int idfornecedor = comoboxFornecedor.getId_item();
					
					List<Fornecedor> listaFornecedor = new ArrayList<>();
					listaFornecedor = FornecedorDAO.listarFornecedorPorId(idfornecedor);
					String nomeFornecedor = listaFornecedor.get(0).getRazaoSocial();

					ItemComboBox comboboxCategoria = (ItemComboBox) cbCategoria.getSelectedItem();
					int idcategoria = comboboxCategoria.getId_item();

					ItemComboBox comboboxMarca = (ItemComboBox) cbMarca.getSelectedItem();
					int idmarca = comboboxMarca.getId_item();

					Produto produto = new Produto();

					produto.setId_categoria(idcategoria);
					produto.setId_fornecedor(idfornecedor);
					produto.setId_marca(idmarca);
					produto.setNome(txtNomeProduto.getText().toString());
					produto.setValorCompra(txtValorCompra.getText().toString());
					//produto.setImagem(caminhoDoArquivo);
					produto.setPeso(txtPeso.getText().toString());
					produto.setCodBarra(txtCodigoBarra.getText().toString());
					produto.setQuantidadeEstoque(txtQtd.getText().toString());
					produto.setPorcDesconto(0);
					produto.setAprovado(0);

					if (opcao.equals("Comprar")) {

						boolean resultado = ProdutoDAO.cadastrarProdutoDAO(produto);

						/*
						 * if (listFornecedor.get(0).getIdFornecedor() == 0) {
						 * JOptionPane.showMessageDialog(null,
						 * "Cadastre um Fornecedor Primeiro!"); }
						 */

						if (resultado == true) {

							JOptionPane.showMessageDialog(null, "Casdastrado com Sucesso!");

							// Cria uma lista de produtos para pegar o id do
							// produto
							List<Produto> listProduto = new ArrayList<>();
							listProduto = ProdutoDAO.listarProdutoDAO();

							int id_produto = 0;
							int id_ultimo_produto_vem_do_banco = 0;

							// Pega o id do ultimo produto cadastrado
							for (int i = 0; listProduto.size() > i; i++) {

								id_ultimo_produto_vem_do_banco = listProduto.get(i).getId_produto();

								if (id_produto <= id_ultimo_produto_vem_do_banco) {

									id_produto = id_ultimo_produto_vem_do_banco;

								}
							}
							
							List<Produto> lstProduto = new ArrayList<>();
							lstProduto = ProdutoDAO.listarProdutoPorIdDAO(id_produto);
							String nomeProduto = lstProduto.get(0).getNome();

							Compra compra = new Compra();

							compra.setFornecedor(nomeFornecedor);
							compra.setProduto(nomeProduto);
							compra.setUsuario(nomeUsuario);

							boolean resultadoHistorico = HistoricoCompraDAO.cadastrarHistoricoCompraDAO(compra);

							if (resultadoHistorico == true) {

								//JOptionPane.showMessageDialog(null, "Casdastrado no Historico com Sucesso!");

							} else {

								//JOptionPane.showMessageDialog(null, "Erro ao Casdastrar Historico!");

							}

							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao Casdastrar!");
						}

					} else {

						produto.setId_produto(id_produto);

						boolean resultado = ProdutoDAO.editarProdutoDAO(produto);

						if (resultado == true) {

							JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");
							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao Atualizar!");
						}

					}

					/*
					 * }else{
					 * 
					 * JOptionPane.showMessageDialog(null,
					 * "Selecione uma imagem!");
					 * 
					 * }
					 */

				} else {

					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");

				}

			}
		});

		btnEvento.setIcon(new ImageIcon(CadastrarProduto.class.getResource("/br/sodadrink/sp/imagens/ic_check_18pt.png")));
		btnEvento.setBounds(10, 11, 132, 30);
		panelOpcoes.add(btnEvento);

		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				txtNomeProduto.setText("");
				txtCodigoBarra.setText("");
				txtQtd.setText("");
				txtPeso.setText("");
				txtValorCompra.setText("");
				
				cbFornecedor.setSelectedIndex(0);
				cbMarca.setSelectedIndex(0);
				cbCategoria.setSelectedIndex(0);
				
			}
		});
		
		btnLimparCampos.setBounds(152, 11, 132, 30);
		panelOpcoes.add(btnLimparCampos);

		if (opcao.equals("Atualizar")) {

			preencherCampos(id_produto);

		}

		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/*
	 * public ImageIcon ResizeImage(String caminhoImagem) {
	 * 
	 * ImageIcon minhaImagem = new ImageIcon(caminhoImagem); Image img =
	 * minhaImagem.getImage(); Image novaImagem =
	 * img.getScaledInstance(lblImagemProduto.getWidth(),
	 * lblImagemProduto.getHeight(), Image.SCALE_SMOOTH); ImageIcon imagem = new
	 * ImageIcon(novaImagem); return imagem; }
	 */

	public void preencherCampos(int id_produto) {

		List<Produto> listProduto = new ArrayList<>();
		listProduto = ProdutoDAO.listarProdutoPorIdDAO(id_produto);

		int fornecedor = listProduto.get(0).getId_fornecedor();
		int marca = listProduto.get(0).getId_marca();
		int categoria = listProduto.get(0).getId_categoria();
		String nome = listProduto.get(0).getNome();
		float valor = listProduto.get(0).getValorCompra();
		//String img = listProduto.get(0).getImagem();
		float peso = listProduto.get(0).getPeso();
		int codBarra = listProduto.get(0).getCodBarra();
		int qtd = listProduto.get(0).getQuantidadeEstoque();

		ComboboxFuncionalidade.selecionaComboboxUsandoValorDoBanco(cbFornecedor, fornecedor);
		ComboboxFuncionalidade.selecionaComboboxUsandoValorDoBanco(cbMarca, marca);
		ComboboxFuncionalidade.selecionaComboboxUsandoValorDoBanco(cbCategoria, categoria);

		//caminhoDoArquivo = img;
		//lblImagemProduto.setIcon(ResizeImage(img));
		txtNomeProduto.setText(nome);
		txtValorCompra.setText("" + valor);
		txtPeso.setText("" + peso);
		txtCodigoBarra.setText("" + codBarra);
		txtQtd.setText("" + qtd);

	}

	public void camposNumericos(JTextField campo) {

		campo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();

				if ((input < '0' || input > '9') && input != '\b') {
					e.consume();
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

		});

	}

	public boolean verificarCampos() {

		if (txtNomeProduto.getText().isEmpty() || txtCodigoBarra.getText().isEmpty() || txtPeso.getText().isEmpty()
				|| txtQtd.getText().isEmpty() || txtValorCompra.getText().isEmpty()) {

			return false;

		} else {

			return true;

		}

	}
}
