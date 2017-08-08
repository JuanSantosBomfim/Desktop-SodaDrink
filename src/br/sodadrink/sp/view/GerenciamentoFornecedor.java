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
import br.sodadrink.sp.DAO.FornecedorDAO;
import br.sodadrink.sp.model.Fornecedor;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GerenciamentoFornecedor extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtRazaoSocial;
	private JTextField txtNomeFantasia;
	private JTextField txtCNPJ;
	private JTextField txtInscricaoEstadual;
	private JPanel panelTabela;

	JTable tableFornecedor;
	DefaultTableModel modelo;

	public GerenciamentoFornecedor() {
		setClosable(true);
		setBounds(100, 100, 1002, 641);

		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 999, 66);
		panelPrincipal.add(panelSuperior);

		JLabel lblTitulo = new JLabel("Gerenciamento de Fornecedores");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblTitulo.setBounds(24, 21, 371, 23);
		panelSuperior.add(lblTitulo);

		JPanel panelBusca = new JPanel();
		panelBusca.setLayout(null);
		panelBusca.setBounds(10, 77, 966, 96);
		TitledBorder titledBorderBusca = new TitledBorder("Buscar Por");
		panelBusca.setBorder(titledBorderBusca);
		panelPrincipal.add(panelBusca);

		JLabel lblCnpj = new JLabel("Nome Fantasia");
		lblCnpj.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCnpj.setBounds(238, 21, 117, 30);
		panelBusca.add(lblCnpj);

		txtRazaoSocial = new JTextField();
		txtRazaoSocial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				List<Fornecedor> listFornecedor = new ArrayList<>();
				listFornecedor = FornecedorDAO.listarFornecedorPorDadosDAO("razaoSocial",
						txtRazaoSocial.getText().toString());

				recarregarTabela(listFornecedor);

			}
		});

		txtRazaoSocial.setToolTipText("");
		txtRazaoSocial.setText("");
		txtRazaoSocial.setColumns(10);
		txtRazaoSocial.setBounds(10, 48, 218, 31);
		panelBusca.add(txtRazaoSocial);

		JLabel lblRazao = new JLabel("Raz\u00E3o Social");
		lblRazao.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblRazao.setBounds(10, 30, 103, 14);
		panelBusca.add(lblRazao);

		txtNomeFantasia = new JTextField();
		txtNomeFantasia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				List<Fornecedor> listFornecedor = new ArrayList<>();
				listFornecedor = FornecedorDAO.listarFornecedorPorDadosDAO("nomeFantasia", txtNomeFantasia.getText());

				recarregarTabela(listFornecedor);

			}
		});

		txtNomeFantasia.setToolTipText("");
		txtNomeFantasia.setText("");
		txtNomeFantasia.setColumns(10);
		txtNomeFantasia.setBounds(238, 48, 218, 31);
		panelBusca.add(txtNomeFantasia);

		txtCNPJ = new JTextField();
		txtCNPJ.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				List<Fornecedor> listFornecedor = new ArrayList<>();
				listFornecedor = FornecedorDAO.listarFornecedorPorDadosDAO("cnpj", txtCNPJ.getText().toString());

				recarregarTabela(listFornecedor);

			}
		});
		txtCNPJ.setToolTipText("");
		txtCNPJ.setText("");
		txtCNPJ.setColumns(10);
		txtCNPJ.setBounds(466, 48, 172, 31);
		panelBusca.add(txtCNPJ);

		JLabel lblCNPJ = new JLabel("CNPJ");
		lblCNPJ.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCNPJ.setBounds(466, 21, 89, 30);
		panelBusca.add(lblCNPJ);

		txtInscricaoEstadual = new JTextField();
		txtInscricaoEstadual.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				List<Fornecedor> listFornecedor = new ArrayList<>();
				listFornecedor = FornecedorDAO.listarFornecedorPorDadosDAO("inscricaoEstadual",
						txtInscricaoEstadual.getText().toString());

				recarregarTabela(listFornecedor);

			}
		});
		txtInscricaoEstadual.setToolTipText("");
		txtInscricaoEstadual.setText("");
		txtInscricaoEstadual.setColumns(10);
		txtInscricaoEstadual.setBounds(648, 48, 195, 31);
		panelBusca.add(txtInscricaoEstadual);

		JLabel lblInscrioEstadual = new JLabel("Inscri\u00E7\u00E3o Estadual");
		lblInscrioEstadual.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblInscrioEstadual.setBounds(648, 21, 128, 30);
		panelBusca.add(lblInscrioEstadual);

		JButton button = new JButton("Buscar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				List<Fornecedor> listFornecedor = new ArrayList<>();

				String razao = txtRazaoSocial.getText().toString();
				String nome = txtNomeFantasia.getText().toString();
				String cnpj = txtCNPJ.getText().toString();
				String inscricao = txtInscricaoEstadual.getText().toString();

				listFornecedor = FornecedorDAO.listarFornecedorPorTodosDadosDAO("razaoSocial", razao, "nomeFantasia",
						nome, "cnpj", cnpj, "inscricaoEstadual", inscricao);

				recarregarTabela(listFornecedor);

			}
		});

		button.setBounds(853, 48, 103, 30);
		panelBusca.add(button);

		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setLayout(null);
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(10, 538, 966, 55);
		panelPrincipal.add(panelOpcoes);

		JButton btnAdicionarFornecedor = new JButton("Adicionar Fornecedor");
		btnAdicionarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CadastrarFornecedor cadastroFornecedor = new CadastrarFornecedor("Cadastrar Fornecedor", "Cadastrar",
						0);

			}
		});

		btnAdicionarFornecedor.setIcon(
				new ImageIcon(GerenciamentoFornecedor.class.getResource("/br/sodadrink/sp/imagens/ic_add_18pt.png")));
		btnAdicionarFornecedor.setBounds(10, 11, 180, 30);
		panelOpcoes.add(btnAdicionarFornecedor);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int coluna = 0;
				int linha = tableFornecedor.getSelectedRow();

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione um Fornecedor!");

				} else {

					String id_fornecedor_tabela = tableFornecedor.getModel().getValueAt(linha, coluna).toString();

					CadastrarFornecedor cadastroFornecedor = new CadastrarFornecedor("Atualizar Fornecedor",
							"Atualizar", Integer.parseInt(id_fornecedor_tabela));

				}

			}
		});
		btnEditar.setIcon(new ImageIcon(
				GerenciamentoFornecedor.class.getResource("/br/sodadrink/sp/imagens/ic_mode_edit_18pt.png")));
		btnEditar.setBounds(200, 11, 100, 30);
		panelOpcoes.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int colunaNomeFornecedor = 1;
				int linhaNomeFornecedor = tableFornecedor.getSelectedRow();

				if (linhaNomeFornecedor == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um Fornecedor!");
				} else {

					String nome_fornecedor = tableFornecedor.getModel()
							.getValueAt(linhaNomeFornecedor, colunaNomeFornecedor).toString();

					int continuar = JOptionPane.showConfirmDialog(null,
							"Tem certeza que deseja excluir o Fornecedor " + nome_fornecedor, "Atenção!",
							JOptionPane.YES_NO_OPTION);

					if (continuar == 0) {

						int coluna = 0;
						int linha = tableFornecedor.getSelectedRow();
						String id_fornecedor_tabela = tableFornecedor.getModel().getValueAt(linha, coluna).toString();

						boolean retorno = FornecedorDAO.excluirFornecedorDAO(Integer.parseInt(id_fornecedor_tabela));

						if (retorno == true) {
							JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

							List<Fornecedor> listFornecedor = new ArrayList<>();

							listFornecedor = FornecedorDAO.listarFornecedorDAO();

							recarregarTabela(listFornecedor);

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao Excluir, esse Fornecedor está sendo utilizado!");
						}

					}
				}

			}
		});

		btnExcluir.setIcon(new ImageIcon(
				GerenciamentoFornecedor.class.getResource("/br/sodadrink/sp/imagens/ic_delete_forever_18pt.png")));
		btnExcluir.setBounds(313, 11, 100, 30);
		panelOpcoes.add(btnExcluir);

		JButton btnAtualizarTabela = new JButton("Atualizar Tabela");
		btnAtualizarTabela.setIcon(new ImageIcon(
				GerenciamentoFornecedor.class.getResource("/br/sodadrink/sp/imagens/ic_autorenew_18pt.png")));
		btnAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				List<Fornecedor> listFornecedor = new ArrayList<>();

				listFornecedor = FornecedorDAO.listarFornecedorDAO();

				recarregarTabela(listFornecedor);

			}
		});

		btnAtualizarTabela.setBounds(423, 11, 160, 30);
		panelOpcoes.add(btnAtualizarTabela);

		// Adicionar Tabela de fornecedores

		panelTabela = new JPanel();
		panelTabela.setBounds(10, 184, 966, 343);
		TitledBorder titledBorderTabela = new TitledBorder("Lista de Fornecedores");
		panelTabela.setBorder(titledBorderTabela);
		panelPrincipal.add(panelTabela);
		panelTabela.setLayout(null);

		exibirTabelaFornecedores();

		this.setVisible(true);

	}

	public void exibirTabelaFornecedores() {

		modelo = new DefaultTableModel();
		tableFornecedor = new JTable();
		tableFornecedor.setModel(modelo);

		modelo.addColumn("ID");
		modelo.addColumn("RAZÃO SOCIAL");
		modelo.addColumn("NOME FANTASIA");
		modelo.addColumn("E-MAIL");
		modelo.addColumn("TELEFONE");
		modelo.addColumn("CNPJ");
		modelo.addColumn("INSCRIÇÃO ESTADUAL");

		tableFornecedor.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableFornecedor.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableFornecedor.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableFornecedor.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableFornecedor.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableFornecedor.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableFornecedor.getColumnModel().getColumn(6).setPreferredWidth(150);

		tableFornecedor.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {

				tableFornecedor.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}
		});

		List<Fornecedor> listFornecedor = new ArrayList<>();

		listFornecedor = FornecedorDAO.listarFornecedorDAO();

		listarFornecedorTabela(listFornecedor);

		JScrollPane scrollPanel = new JScrollPane(tableFornecedor);
		scrollPanel.setBounds(10, 21, 946, 310);
		panelTabela.add(scrollPanel);

	}

	public void listarFornecedorTabela(List<Fornecedor> listFornecedor) {

		for (int i = 0; listFornecedor.size() > i; i++) {

			modelo.addRow(new Object[] { listFornecedor.get(i).getIdFornecedor(),
					listFornecedor.get(i).getRazaoSocial(), listFornecedor.get(i).getNomeFantasia(),
					listFornecedor.get(i).getEmail(), listFornecedor.get(i).getTelefone(),
					listFornecedor.get(i).getCnpj(), listFornecedor.get(i).getInscricaoEstadual() });

		}

	}

	public void recarregarTabela(List<Fornecedor> listFornecedor) {

		DefaultTableModel tm = (DefaultTableModel) tableFornecedor.getModel();

		while (tableFornecedor.getRowCount() > 0) {
			((DefaultTableModel) tableFornecedor.getModel()).removeRow(0);
		}

		listarFornecedorTabela(listFornecedor);

	}

}
