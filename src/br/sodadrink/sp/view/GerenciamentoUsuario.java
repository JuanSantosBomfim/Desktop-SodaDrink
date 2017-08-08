package br.sodadrink.sp.view;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import br.sodadrink.sp.DAO.NivelDAO;
import br.sodadrink.sp.DAO.UsuarioDAO;
import br.sodadrink.sp.model.ItemComboBox;
import br.sodadrink.sp.model.Nivel;
import br.sodadrink.sp.model.Usuario;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GerenciamentoUsuario extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtLogin;
	private JTextField txtCPF;

	DefaultTableModel modelo;
	JTable tableUsuario;
	JPanel panelTabela;
	JComboBox<ItemComboBox> cbNivel;

	public GerenciamentoUsuario(int id_usuario) {

		setTitle("Gerenciamento de Usuarios");
		setClosable(true);
		setBounds(100, 100, 1002, 641);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setLayout(null);
		panelSuperior.setBounds(0, 0, 998, 66);
		panelPrincipal.add(panelSuperior);

		JLabel lblTitulo = new JLabel("Gerenciamento de Us\u00FAarios");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblTitulo.setBounds(24, 21, 313, 23);
		panelSuperior.add(lblTitulo);

		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(10, 538, 966, 55);
		panelPrincipal.add(panelOpcoes);
		panelOpcoes.setLayout(null);

		JButton btnAdicionarUsuario = new JButton("Adicionar Us\u00FAario");
		btnAdicionarUsuario.setIcon(
				new ImageIcon(GerenciamentoUsuario.class.getResource("/br/sodadrink/sp/imagens/ic_add_18pt.png")));
		btnAdicionarUsuario.setBounds(10, 11, 148, 30);
		panelOpcoes.add(btnAdicionarUsuario);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int coluna = 0;
				int linha = tableUsuario.getSelectedRow();

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione um Usuario!");

				} else {

					String id_usuario_linha = tableUsuario.getModel().getValueAt(linha, coluna).toString();

					CadastrarUsuario cadastrarUsuario = new CadastrarUsuario("Atualizar Usuario", "Atualizar",
							Integer.parseInt(id_usuario_linha));

				}

			}
		});

		btnEditar.setIcon(new ImageIcon(
				GerenciamentoUsuario.class.getResource("/br/sodadrink/sp/imagens/ic_mode_edit_18pt.png")));
		btnEditar.setBounds(168, 11, 100, 30);
		panelOpcoes.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int colunaNomeUsuario = 1;
				int linhaNomeUsuario = tableUsuario.getSelectedRow();

				if (linhaNomeUsuario == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um Usuario!");
				} else {

					String nome_usuario = tableUsuario.getModel().getValueAt(linhaNomeUsuario, colunaNomeUsuario)
							.toString();
								
					int coluna = 0;
					int linha = tableUsuario.getSelectedRow();
					
					String id_usuario_selecionado = tableUsuario.getModel().getValueAt(linha, coluna).toString();
					int id_usuario_selecionado_int = Integer.parseInt(id_usuario_selecionado);
					
					if(id_usuario == id_usuario_selecionado_int){
						
						JOptionPane.showMessageDialog(null, "Este usuario esta sendo utilizado!");
						
					}else{
						
						int continuar = JOptionPane.showConfirmDialog(null, "Deseja excluir o Usuario " + nome_usuario,
								"Atenção!", JOptionPane.YES_NO_OPTION);

						if (continuar == 0) {

							boolean retorno = UsuarioDAO.excluirUsuarioDAO(id_usuario_selecionado_int);

							if (retorno == true) {
								JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

								List<Usuario> listUsuario = new ArrayList<>();

								listUsuario = UsuarioDAO.listarUsuarioDAO();

								recarregarTabela(listUsuario);

							} else {
								JOptionPane.showMessageDialog(null, "Erro ao Excluir!");
							}

						}
						
					}

				}

			}
		});

		btnExcluir.setIcon(new ImageIcon(
				GerenciamentoUsuario.class.getResource("/br/sodadrink/sp/imagens/ic_delete_forever_18pt.png")));
		btnExcluir.setBounds(281, 11, 100, 30);
		panelOpcoes.add(btnExcluir);

		JButton button = new JButton("Atualizar Tabela");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Usuario> listUsuario = new ArrayList<>();

				listUsuario = UsuarioDAO.listarUsuarioDAO();

				recarregarTabela(listUsuario);

			}
		});

		button.setIcon(new ImageIcon(
				GerenciamentoUsuario.class.getResource("/br/sodadrink/sp/imagens/ic_autorenew_18pt.png")));
		button.setBounds(391, 11, 160, 30);
		panelOpcoes.add(button);

		JPanel panelBusca = new JPanel();
		panelBusca.setBounds(10, 77, 966, 96);
		panelPrincipal.add(panelBusca);
		panelBusca.setLayout(null);
		TitledBorder titledBorderBusca = new TitledBorder("Buscar Por");
		panelBusca.setBorder(titledBorderBusca);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Usuario> listUsuario = new ArrayList<>();

				ItemComboBox comboboxNivel = (ItemComboBox) cbNivel.getSelectedItem();
				String nomeNivel = comboboxNivel.getDescricao();

				String nome = txtNome.getText().toString();
				String login = txtLogin.getText().toString();
				String cpf = txtCPF.getText().toString();
				String email = txtEmail.getText().toString();

				listUsuario = UsuarioDAO.listarUsuarioPorTodosDadosDAO("nome", nome, "login", login, "cpf", cpf,
						"email", email, "descricao", nomeNivel);

				recarregarTabela(listUsuario);

			}
		});

		btnBuscar.setBounds(829, 48, 103, 30);
		panelBusca.add(btnBuscar);

		cbNivel = new JComboBox<ItemComboBox>();

		List<Nivel> listNivel = new ArrayList<>();
		listNivel = NivelDAO.listarNivelDAO();

		for (int i = 0; listNivel.size() > i; i++) {

			int idNivel = listNivel.get(i).getId_nivel();
			String nome = listNivel.get(i).getDescricao();

			cbNivel.addItem(new ItemComboBox(idNivel, nome, i));

		}

		cbNivel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				List<Usuario> listUsuario = new ArrayList<>();

				ItemComboBox comboboxNivel = (ItemComboBox) cbNivel.getSelectedItem();
				String nomeNivel = comboboxNivel.getDescricao();

				listUsuario = UsuarioDAO.listarUsuarioPorDadosDAO("n.descricao", nomeNivel);

				recarregarTabela(listUsuario);

			}
		});
		cbNivel.setBounds(650, 47, 140, 31);
		panelBusca.add(cbNivel);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEmail.setBounds(490, 21, 89, 30);
		panelBusca.add(lblEmail);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				List<Usuario> listUsuario = new ArrayList<>();

				listUsuario = UsuarioDAO.listarUsuarioPorDadosDAO("nome", txtNome.getText().toString());

				recarregarTabela(listUsuario);

			}
		});

		txtNome.setToolTipText("");
		txtNome.setText("");
		txtNome.setColumns(10);
		txtNome.setBounds(10, 48, 150, 31);
		panelBusca.add(txtNome);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNome.setBounds(10, 30, 89, 14);
		panelBusca.add(lblNome);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				List<Usuario> listUsuario = new ArrayList<>();

				listUsuario = UsuarioDAO.listarUsuarioPorDadosDAO("email", txtEmail.getText().toString());

				recarregarTabela(listUsuario);

			}
		});
		txtEmail.setToolTipText("");
		txtEmail.setText("");
		txtEmail.setColumns(10);
		txtEmail.setBounds(490, 48, 150, 31);
		panelBusca.add(txtEmail);

		JLabel lblNivel = new JLabel("N\u00EDvel");
		lblNivel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNivel.setBounds(650, 20, 89, 30);
		panelBusca.add(lblNivel);

		txtLogin = new JTextField();
		txtLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				List<Usuario> listUsuario = new ArrayList<>();

				listUsuario = UsuarioDAO.listarUsuarioPorDadosDAO("login", txtLogin.getText().toString());

				recarregarTabela(listUsuario);

			}
		});
		txtLogin.setToolTipText("");
		txtLogin.setText("");
		txtLogin.setColumns(10);
		txtLogin.setBounds(170, 48, 150, 31);
		panelBusca.add(txtLogin);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblLogin.setBounds(170, 21, 89, 30);
		panelBusca.add(lblLogin);

		txtCPF = new JTextField();
		txtCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				List<Usuario> listUsuario = new ArrayList<>();

				listUsuario = UsuarioDAO.listarUsuarioPorDadosDAO("cpf", txtCPF.getText().toString());

				recarregarTabela(listUsuario);

			}
		});
		txtCPF.setToolTipText("");
		txtCPF.setText("");
		txtCPF.setColumns(10);
		txtCPF.setBounds(330, 48, 150, 31);
		panelBusca.add(txtCPF);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCpf.setBounds(330, 21, 89, 30);
		panelBusca.add(lblCpf);

		btnAdicionarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastrarUsuario cadastrarUsuario = new CadastrarUsuario("Cadastrar Usuario", "Cadastrar", 0);

			}
		});

		panelTabela = new JPanel();
		panelTabela.setBounds(10, 184, 966, 342);
		TitledBorder titledBorderUsuarios = new TitledBorder("Lista de Usuarios");
		panelTabela.setBorder(titledBorderUsuarios);
		panelPrincipal.add(panelTabela);

		exibirTabelaUsuarios();

		this.setVisible(true);

	}

	public void exibirTabelaUsuarios() {

		modelo = new DefaultTableModel();
		tableUsuario = new JTable();
		tableUsuario.setModel(modelo);

		modelo.addColumn("ID");
		modelo.addColumn("NOME");
		modelo.addColumn("LOGIN");
		modelo.addColumn("E-MAIL");
		modelo.addColumn("TELEFONE");
		modelo.addColumn("CPF");
		modelo.addColumn("NIVEL");

		tableUsuario.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableUsuario.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableUsuario.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableUsuario.getColumnModel().getColumn(3).setPreferredWidth(150);// email
		tableUsuario.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableUsuario.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableUsuario.getColumnModel().getColumn(6).setPreferredWidth(150);

		tableUsuario.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {

				tableUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}
		});

		List<Usuario> listUsuario = new ArrayList<>();

		listUsuario = UsuarioDAO.listarUsuarioDAO();

		exibirItensTabela(listUsuario);

		panelTabela.setLayout(null);

		JScrollPane scrollPanel = new JScrollPane(tableUsuario);
		scrollPanel.setBounds(10, 21, 946, 310);
		panelTabela.add(scrollPanel);

	}

	public void exibirItensTabela(List<Usuario> listUsuario) {

		for (int i = 0; listUsuario.size() > i; i++) {

			modelo.addRow(new Object[] { listUsuario.get(i).getId_usuario(), listUsuario.get(i).getNome(),
					listUsuario.get(i).getLogin(), listUsuario.get(i).getEmail(), listUsuario.get(i).getTelefone(),
					listUsuario.get(i).getCpf(), listUsuario.get(i).getNomeNivel() });

		}
	}

	public void recarregarTabela(List<Usuario> listUsuario) {

		DefaultTableModel tm = (DefaultTableModel) tableUsuario.getModel();

		while (tableUsuario.getRowCount() > 0) {
			((DefaultTableModel) tableUsuario.getModel()).removeRow(0);
		}

		exibirItensTabela(listUsuario);

	}
}
