package br.sodadrink.sp.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import br.sodadrink.sp.DAO.NivelDAO;
import br.sodadrink.sp.DAO.UsuarioDAO;
import br.sodadrink.sp.controller.HoraAtual;
import br.sodadrink.sp.model.Nivel;
import br.sodadrink.sp.model.Usuario;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JDesktopPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	public Principal(int id_usuario) {

		List<Usuario> listaDadosUsuarioLogado = new ArrayList<>();
		listaDadosUsuarioLogado = UsuarioDAO.listarUsuarioPorIdDAO(id_usuario);
		String nome = listaDadosUsuarioLogado.get(0).getNome();

		int id_nivel = listaDadosUsuarioLogado.get(0).getId_nivel();

		List<Nivel> listaNivelUsuario = new ArrayList<>();
		listaNivelUsuario = NivelDAO.listarNivelPorIdDAO(id_nivel);
		String nomeNivel = listaNivelUsuario.get(0).getDescricao();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 800);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBorder(null);
		contentPane.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		JPanel panelSuperior = new JPanel();
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new BorderLayout(0, 0));

		JLabel lblImagem = new JLabel("");
		lblImagem.setIcon(new ImageIcon(Principal.class.getResource("/br/sodadrink/sp/imagens/logoPrincipal.png")));
		panelSuperior.add(lblImagem, BorderLayout.WEST);

		JPanel panelTitulo = new JPanel();
		panelSuperior.add(panelTitulo, BorderLayout.CENTER);
		panelTitulo.setLayout(new BorderLayout(0, 0));

		JLabel lblTitulo = new JLabel("Sistema de Gerenciamento Desktop");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
		panelTitulo.add(lblTitulo, BorderLayout.CENTER);

		JPanel panelSuperiorDireito = new JPanel();
		panelSuperior.add(panelSuperiorDireito, BorderLayout.EAST);
		panelSuperiorDireito.setLayout(new BorderLayout(0, 0));

		JLabel lblEspaco = new JLabel(
				"                                                                                               ");
		panelSuperiorDireito.add(lblEspaco, BorderLayout.SOUTH);

		JPanel panelUsuario = new JPanel();
		panelSuperiorDireito.add(panelUsuario, BorderLayout.CENTER);
		panelUsuario.setLayout(null);

		JLabel lblNomeUsuario = new JLabel(nome);
		lblNomeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeUsuario.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNomeUsuario.setBounds(0, 29, 184, 25);
		panelUsuario.add(lblNomeUsuario);

		JLabel lblNivelUsuario = new JLabel(nomeNivel);
		lblNivelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNivelUsuario.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblNivelUsuario.setBounds(12, 62, 161, 20);
		panelUsuario.add(lblNivelUsuario);

		JButton btnLogout = new JButton("");
		btnLogout.setIcon(
				new ImageIcon(Principal.class.getResource("/br/sodadrink/sp/imagens/ic_exit_to_app_18pt.png")));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int opcao = JOptionPane.showConfirmDialog(null, "Deseja Fazer Logout?", "Atenção!",
						JOptionPane.YES_NO_OPTION);

				if (opcao == 0) {
					
					Login login = new Login();
					dispose();
					
				}

			}
		});
		btnLogout.setBounds(218, 29, 33, 33);
		panelUsuario.add(btnLogout);

		JPanel panelCentral = new JPanel();
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));

		JPanel panelConteudo = new JPanel();
		panelCentral.add(panelConteudo, BorderLayout.CENTER);
		panelConteudo.setLayout(new BorderLayout(0, 0));

		JDesktopPane desktopPane = new JDesktopPane();
		panelConteudo.add(desktopPane, BorderLayout.CENTER);

		JPanel panelMenu = new JPanel();
		panelCentral.add(panelMenu, BorderLayout.WEST);
		panelMenu.setLayout(new BorderLayout(0, 0));

		JTree treeMenu = new JTree();
		treeMenu.setFont(new Font("SansSerif", Font.PLAIN, 13));
		treeMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				String menuSelecionado = treeMenu.getLastSelectedPathComponent().toString();

				List<Nivel> listaNivelUsuario = new ArrayList<>();
				listaNivelUsuario = NivelDAO.listarNivelPorIdDAO(id_nivel);

				int categoria = listaNivelUsuario.get(0).getCategoria();
				int estoque = listaNivelUsuario.get(0).getEstoque();
				int fornecedor = listaNivelUsuario.get(0).getFornecedor();
				int marca = listaNivelUsuario.get(0).getMarca();

				int nivel = listaNivelUsuario.get(0).getNivel();
				int produto = listaNivelUsuario.get(0).getProduto();
				int usuario = listaNivelUsuario.get(0).getUsuario();
				int relatorio = listaNivelUsuario.get(0).getRelatorio();

				if (menuSelecionado.equals("Vendas")) {

					if (relatorio == 1) {

						VerificarVenda tela = new VerificarVenda();
						desktopPane.add(tela);
						tela.setVisible(true);

					} else {

						JOptionPane.showMessageDialog(null, "Acesso Negado!");

					}

				} else if (menuSelecionado.equals("Pedidos")) {

					if (relatorio == 1) {

						VerificarPedido tela = new VerificarPedido();
						desktopPane.add(tela);
						tela.setVisible(true);

					} else {

						JOptionPane.showMessageDialog(null, "Acesso Negado!");

					}

				} else if (menuSelecionado.equals("Gerenciamento de Fornecedores")) {

					if (fornecedor == 1) {

						GerenciamentoFornecedor tela = new GerenciamentoFornecedor();
						desktopPane.add(tela);
						tela.setVisible(true);

					} else {

						JOptionPane.showMessageDialog(null, "Acesso Negado!");

					}

				} else if (menuSelecionado.equals("Gerenciamento de Categorias")) {

					if (categoria == 1) {

						GerenciamentoCategoria tela = new GerenciamentoCategoria();
						desktopPane.add(tela);
						tela.setVisible(true);

					} else {

						JOptionPane.showMessageDialog(null, "Acesso Negado!");

					}

				} else if (menuSelecionado.equals("Gerenciamento de Marcas")) {

					if (marca == 1) {

						GerenciamentoMarca tela = new GerenciamentoMarca();
						desktopPane.add(tela);
						tela.setVisible(true);

					} else {

						JOptionPane.showMessageDialog(null, "Acesso Negado!");

					}

				} else if (menuSelecionado.equals("Gerenciamento de Produtos")) {

					if (produto == 1) {

						GerenciamentoProduto tela = new GerenciamentoProduto(id_usuario);
						desktopPane.add(tela);
						tela.setVisible(true);

					} else {

						JOptionPane.showMessageDialog(null, "Acesso Negado!");

					}

				} else if (menuSelecionado.equals("Historico de Compras")) {

					if (produto == 1) {

						HistoricoCompra tela = new HistoricoCompra();
						desktopPane.add(tela);
						tela.setVisible(true);

					} else {

						JOptionPane.showMessageDialog(null, "Acesso Negado!");

					}

				} else if (menuSelecionado.equals("Gerenciar Corredores")) {

					if (estoque == 1) {

						GerenciamentoCorredor tela = new GerenciamentoCorredor();
						desktopPane.add(tela);
						tela.setVisible(true);

					} else {

						JOptionPane.showMessageDialog(null, "Acesso Negado!");

					}

				} else if (menuSelecionado.equals("Gerenciar Estantes")) {

					if (estoque == 1) {

						GerenciamentoEstante tela = new GerenciamentoEstante();
						desktopPane.add(tela);
						tela.setVisible(true);

					} else {

						JOptionPane.showMessageDialog(null, "Acesso Negado!");

					}

				} else if (menuSelecionado.equals("Gerenciar Prateleiras")) {

					if (estoque == 1) {

						GerenciamentoPrateleira tela = new GerenciamentoPrateleira();
						desktopPane.add(tela);
						tela.setVisible(true);

					} else {

						JOptionPane.showMessageDialog(null, "Acesso Negado!");

					}

				} else if (menuSelecionado.equals("Gerenciar Produtos em Estoque")) {

					if (estoque == 1) {

						GerenciamentoEstoque tela = new GerenciamentoEstoque();
						desktopPane.add(tela);
						tela.setVisible(true);

					} else {

						JOptionPane.showMessageDialog(null, "Acesso Negado!");

					}

				} else if (menuSelecionado.equals("Gerenciamento de Níveis")) {

					if (nivel == 1) {

						GerenciamentoNivel tela = new GerenciamentoNivel(id_nivel);
						desktopPane.add(tela);
						tela.setVisible(true);

					} else {

						JOptionPane.showMessageDialog(null, "Acesso Negado!");

					}

				} else if (menuSelecionado.equals("Gerenciamento de Usuarios")) {

					if (usuario == 1) {

						GerenciamentoUsuario tela = new GerenciamentoUsuario(id_usuario);
						desktopPane.add(tela);
						tela.setVisible(true);

					} else {

						JOptionPane.showMessageDialog(null, "Acesso Negado!");

					}

				} else if (menuSelecionado.equals("Verificar Clientes")) {

					VerificarCliente tela = new VerificarCliente();
					desktopPane.add(tela);
					tela.setVisible(true);

				}

			}
		});

		treeMenu.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("root") {

			private static final long serialVersionUID = 1L;

			{
				DefaultMutableTreeNode node_1;
				DefaultMutableTreeNode node_2;
				node_1 = new DefaultMutableTreeNode("Relatorios");
				node_1.add(new DefaultMutableTreeNode("Vendas"));
				node_1.add(new DefaultMutableTreeNode("Pedidos"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Fornecedores");
				node_1.add(new DefaultMutableTreeNode("Gerenciamento de Fornecedores"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Categorias");
				node_1.add(new DefaultMutableTreeNode("Gerenciamento de Categorias"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Marcas");
				node_1.add(new DefaultMutableTreeNode("Gerenciamento de Marcas"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Produtos");
				node_1.add(new DefaultMutableTreeNode("Gerenciamento de Produtos"));
				node_1.add(new DefaultMutableTreeNode("Historico de Compras"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Estoque");
				node_2 = new DefaultMutableTreeNode("Corredores");
				node_2.add(new DefaultMutableTreeNode("Gerenciar Corredores"));
				node_1.add(node_2);
				node_2 = new DefaultMutableTreeNode("Estantes");
				node_2.add(new DefaultMutableTreeNode("Gerenciar Estantes"));
				node_1.add(node_2);
				node_2 = new DefaultMutableTreeNode("Prateleiras");
				node_2.add(new DefaultMutableTreeNode("Gerenciar Prateleiras"));
				node_1.add(node_2);
				node_1.add(new DefaultMutableTreeNode("Gerenciar Produtos em Estoque"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Niveis");
				node_1.add(new DefaultMutableTreeNode("Gerenciamento de N\u00EDveis"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Usuarios");
				node_1.add(new DefaultMutableTreeNode("Gerenciamento de Usuarios"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Clientes");
				node_1.add(new DefaultMutableTreeNode("Verificar Clientes"));
				add(node_1);
			}
		}));

		treeMenu.setRootVisible(false);
		panelMenu.add(treeMenu, BorderLayout.CENTER);

		JLabel lblEspacoMenu = new JLabel();
		lblEspacoMenu.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panelMenu.add(lblEspacoMenu, BorderLayout.SOUTH);
		
		HoraAtual.HoraAtualSistema(lblEspacoMenu);

		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

}
