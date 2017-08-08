package br.sodadrink.sp.view;

import java.awt.BorderLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import br.sodadrink.sp.DAO.EstadoDAO;
import br.sodadrink.sp.DAO.NivelDAO;
import br.sodadrink.sp.DAO.UsuarioDAO;
import br.sodadrink.sp.controller.ComboboxFuncionalidade;
import br.sodadrink.sp.controller.TamanhoCampo;
import br.sodadrink.sp.controller.WebServiceCep;
import br.sodadrink.sp.model.ItemComboBox;
import br.sodadrink.sp.model.Estado;
import br.sodadrink.sp.model.Nivel;
import br.sodadrink.sp.model.Usuario;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;

public class CadastrarUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel panelPrincipal = new JPanel();
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtTelefone;
	private JTextField txtCEP;
	private JTextField txtCidade;
	private JTextField txtLogin;
	private JPasswordField pswfSenha;
	private JPasswordField pswfComfirmaSenha;
	private JTextField txtLogradouro;
	private JTextField txtBairro;
	private JTextField txtNumero;

	JRadioButton rdoMulher;
	JRadioButton rdoHomen;

	JDateChooser datechooser;

	String data = "";
	String sexo = "";
	int id_nivel_do_combobox = 0;

	JComboBox<ItemComboBox> cbNivel;
	JComboBox<ItemComboBox> cbEstado;
	private JTextField txtEmail;

	public CadastrarUsuario(String titulo, String opcao, int id_usuario) {

		setTitle(titulo);
		setBounds(100, 100, 729, 640);
		getContentPane().setLayout(new BorderLayout());
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 713, 66);
		panelPrincipal.add(panelSuperior);

		JLabel lblCadastroDeUsuarios = new JLabel(titulo);
		lblCadastroDeUsuarios.setForeground(Color.WHITE);
		lblCadastroDeUsuarios.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblCadastroDeUsuarios.setBounds(24, 21, 246, 23);
		panelSuperior.add(lblCadastroDeUsuarios);

		JPanel panelDadosPessoais = new JPanel();
		panelDadosPessoais.setBounds(10, 77, 693, 280);

		TitledBorder titledBorderDadosPessoais = new TitledBorder("Dados Pessoais");
		panelDadosPessoais.setBorder(titledBorderDadosPessoais);

		panelPrincipal.add(panelDadosPessoais);
		panelDadosPessoais.setLayout(null);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setDocument(new TamanhoCampo(30));
		txtNome.setBounds(10, 50, 217, 31);
		panelDadosPessoais.add(txtNome);

		txtCPF = new JTextField();
		txtCPF.setDocument(new TamanhoCampo(11));
 
		//Mascara cpf
	     try{
	           javax.swing.text.MaskFormatter format_textField3 = new javax.swing.text.MaskFormatter("###.###.###-##");
	           txtCPF = new javax.swing.JFormattedTextField(format_textField3);
	           
	        }catch (Exception e){}
	    //Fim mascara

		txtCPF.setColumns(10);
		txtCPF.setBounds(237, 50, 217, 31);

		camposNumericos(txtCPF);

		panelDadosPessoais.add(txtCPF);

		txtTelefone = new JTextField();
		txtTelefone.setDocument(new TamanhoCampo(14));

		//Mascara telefone
	     try{
	           javax.swing.text.MaskFormatter format_textField3 = new javax.swing.text.MaskFormatter("(##) ####-####");
	           txtTelefone = new javax.swing.JFormattedTextField(format_textField3);
	           
	        }catch (Exception e){}
	    //Fim mascara
		
		txtTelefone.setBounds(464, 50, 217, 31);
		camposNumericos(txtTelefone);
		panelDadosPessoais.add(txtTelefone);

		datechooser = new JDateChooser();			
		datechooser.setBounds(10, 109, 217, 31);			
		panelDadosPessoais.add(datechooser);

		JLabel lblNewLabel = new JLabel("Nome Completo");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 32, 126, 14);
		panelDadosPessoais.add(lblNewLabel);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCpf.setBounds(237, 34, 53, 14);
		panelDadosPessoais.add(lblCpf);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTelefone.setBounds(464, 34, 75, 14);
		panelDadosPessoais.add(lblTelefone);

		JLabel lblDataDeNacimento = new JLabel("Data de Nacimento");
		lblDataDeNacimento.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDataDeNacimento.setBounds(10, 92, 147, 14);
		panelDadosPessoais.add(lblDataDeNacimento);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSexo.setBounds(474, 98, 53, 14);
		panelDadosPessoais.add(lblSexo);

		rdoHomen = new JRadioButton("Homem");
		rdoHomen.setBounds(490, 117, 88, 23);
		panelDadosPessoais.add(rdoHomen);

		rdoMulher = new JRadioButton("Mulher");
		rdoMulher.setBounds(588, 117, 88, 23);
		panelDadosPessoais.add(rdoMulher);

		ButtonGroup buttonGroupSexo = new ButtonGroup();

		buttonGroupSexo.add(rdoHomen);
		buttonGroupSexo.add(rdoMulher);

		txtCEP = new JTextField();
		txtCEP.setDocument(new TamanhoCampo(8));
		
		txtCEP.setBounds(10, 169, 217, 31);

		camposNumericos(txtCEP);

		txtCEP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				if (txtCEP.getText().toString().length() == 8) {

					buscaCep();

				}
			}
		});

		panelDadosPessoais.add(txtCEP);

		JLabel lblCEP = new JLabel("CEP");
		lblCEP.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCEP.setBounds(10, 151, 63, 14);
		panelDadosPessoais.add(lblCEP);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEstado.setBounds(237, 151, 82, 14);
		panelDadosPessoais.add(lblEstado);

		txtCidade = new JTextField();
		txtCidade.setDocument(new TamanhoCampo(30));
		txtCidade.setColumns(10);
		txtCidade.setBounds(373, 169, 308, 31);
		panelDadosPessoais.add(txtCidade);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCidade.setBounds(373, 151, 310, 14);
		panelDadosPessoais.add(lblCidade);

		txtLogradouro = new JTextField();
		txtLogradouro.setDocument(new TamanhoCampo(100));
		txtLogradouro.setColumns(10);
		txtLogradouro.setBounds(237, 229, 359, 31);
		panelDadosPessoais.add(txtLogradouro);

		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblLogradouro.setBounds(237, 207, 88, 23);
		panelDadosPessoais.add(lblLogradouro);

		txtBairro = new JTextField();
		txtBairro.setDocument(new TamanhoCampo(30));
		txtBairro.setColumns(10);
		txtBairro.setBounds(10, 229, 217, 31);
		panelDadosPessoais.add(txtBairro);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblBairro.setBounds(10, 211, 63, 14);
		panelDadosPessoais.add(lblBairro);

		txtNumero = new JTextField();
		txtNumero.setDocument(new TamanhoCampo(10));
		txtNumero.setColumns(10);
		txtNumero.setBounds(606, 229, 75, 31);

		camposNumericos(txtNumero);

		panelDadosPessoais.add(txtNumero);

		JLabel lblNumero = new JLabel("N\u00B0");
		lblNumero.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNumero.setBounds(606, 211, 63, 14);
		panelDadosPessoais.add(lblNumero);

		cbEstado = new JComboBox<ItemComboBox>();
		cbEstado.setBounds(237, 169, 126, 31);
		panelDadosPessoais.add(cbEstado);

		txtEmail = new JTextField();
		txtEmail.setDocument(new TamanhoCampo(40));
		txtEmail.setColumns(10);
		txtEmail.setBounds(237, 108, 217, 31);
		panelDadosPessoais.add(txtEmail);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEmail.setBounds(237, 92, 53, 14);
		panelDadosPessoais.add(lblEmail);

		List<Estado> listEstado = new ArrayList<>();

		listEstado = EstadoDAO.listarEstados();

		for (int i = 0; listEstado.size() > i; i++) {

			int id = listEstado.get(i).getId_estado();
			String nome = listEstado.get(i).getEstado();

			cbEstado.addItem(new ItemComboBox(id, nome, i));

		}

		JPanel panelDadosProfissionais = new JPanel();
		panelDadosProfissionais.setBounds(10, 368, 693, 154);

		TitledBorder titledBorderDadosProfissionais = new TitledBorder("Dados Profissionais");
		panelDadosProfissionais.setBorder(titledBorderDadosProfissionais);

		panelPrincipal.add(panelDadosProfissionais);
		panelDadosProfissionais.setLayout(null);

		txtLogin = new JTextField();
		txtLogin.setDocument(new TamanhoCampo(30));
		txtLogin.setColumns(10);
		txtLogin.setBounds(10, 48, 217, 31);
		panelDadosProfissionais.add(txtLogin);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblLogin.setBounds(10, 25, 63, 25);
		panelDadosProfissionais.add(lblLogin);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSenha.setBounds(237, 30, 71, 14);
		panelDadosProfissionais.add(lblSenha);

		JLabel lblComfirmarSenha = new JLabel("Comfirmar Senha");
		lblComfirmarSenha.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblComfirmarSenha.setBounds(464, 30, 132, 14);
		panelDadosProfissionais.add(lblComfirmarSenha);

		cbNivel = new JComboBox<ItemComboBox>();
		cbNivel.setBounds(237, 106, 217, 31);
		panelDadosProfissionais.add(cbNivel);

		List<Nivel> listNivel = new ArrayList<>();
		listNivel = NivelDAO.listarNivelDAO();

		for (int i = 0; listNivel.size() > i; i++) {

			int id = listNivel.get(i).getId_nivel();
			String nome = listNivel.get(i).getDescricao();

			cbNivel.addItem(new ItemComboBox(id, nome, i));

		}

		pswfSenha = new JPasswordField();
		pswfSenha.setDocument(new TamanhoCampo(20));
		pswfSenha.setBounds(237, 48, 217, 31);
		panelDadosProfissionais.add(pswfSenha);

		pswfComfirmaSenha = new JPasswordField();
		pswfComfirmaSenha.setDocument(new TamanhoCampo(20));
		pswfComfirmaSenha.setBounds(464, 48, 217, 31);
		panelDadosProfissionais.add(pswfComfirmaSenha);

		JLabel lblNivel = new JLabel("Nivel");
		lblNivel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNivel.setBounds(237, 90, 71, 14);
		panelDadosProfissionais.add(lblNivel);

		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(10, 533, 693, 55);
		panelPrincipal.add(panelOpcoes);
		panelOpcoes.setLayout(null);

		// Cadastro de Usuarios
		JButton btnCadastrar = new JButton(opcao);
		btnCadastrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				boolean continua = verificarCampos();

				if (continua == true) {

					boolean retorna = verificarSenhas();

					if (retorna == true) {

						if (rdoHomen.isSelected()) {
							sexo = "M";
						} else if (rdoMulher.isSelected()) {
							sexo = "F";
						}

						ItemComboBox nivel = (ItemComboBox) cbNivel.getSelectedItem();
						id_nivel_do_combobox = nivel.getId_item();

						ItemComboBox estado = (ItemComboBox) cbEstado.getSelectedItem();
						int id_estado_do_combobox = estado.getId_item();

						Usuario usuario = new Usuario();
						usuario.setId_estado(id_estado_do_combobox);
						usuario.setId_nivel(id_nivel_do_combobox);
						usuario.setNome(txtNome.getText().toString());
						usuario.setCpf(txtCPF.getText().toString());

						// Pegando o Valor do DataChoose
						data = ((JTextField) datechooser.getDateEditor().getUiComponent()).getText().toString();
						usuario.setDtNascimento(data);

						usuario.setSexo(sexo);
						usuario.setLogin(txtLogin.getText().toString());
						usuario.setSenha(pswfSenha.getText().toString());
						usuario.setTelefone(txtTelefone.getText().toString());
						usuario.setCep(txtCEP.getText().toString());
						usuario.setCidade(txtCidade.getText().toString());
						usuario.setLogradouro(txtLogradouro.getText().toString());
						usuario.setBairro(txtBairro.getText().toString());
						usuario.setNumero(txtNumero.getText().toString());
						usuario.setEmail(txtEmail.getText().toString());

						if (opcao.equals("Cadastrar")) {

							boolean retorno = UsuarioDAO.cadastrarUsuarioDAO(usuario);

							if (retorno == true) {

								JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
								dispose();

							} else {
								JOptionPane.showMessageDialog(null, "Erro ao Cadastrar!");
							}

						} else if (opcao.equals("Atualizar")) {

							usuario.setId_usuario(id_usuario);

							boolean resultadoEditar = UsuarioDAO.editarUsuarioDAO(usuario);

							if (resultadoEditar == true) {

								JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
								dispose();

							} else {
								JOptionPane.showMessageDialog(null, "Erro ao Atualizar!");
							}

						}

					}

				} else {

					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");

				}

			}
		});

		btnCadastrar.setIcon(new ImageIcon(CadastrarUsuario.class.getResource("/br/sodadrink/sp/imagens/ic_check_18pt.png")));
		btnCadastrar.setBounds(10, 11, 132, 30);
		panelOpcoes.add(btnCadastrar);

		// Limpar Campos do Cadastro de Usuarios
		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cbEstado.setSelectedIndex(0);
				cbNivel.setSelectedIndex(0);
				
				txtNome.setText("");
				txtCPF.setText("");
				txtTelefone.setText("");
				txtCEP.setText("");
				txtCidade.setText("");
				txtEmail.setText("");
				txtLogin.setText("");
				pswfSenha.setText("");
				pswfComfirmaSenha.setText("");
				txtLogradouro.setText("");
				txtBairro.setText("");
				txtNumero.setText("");
				
				datechooser.setDate(null);
				
			}
		});
		
		btnLimparCampos.setBounds(152, 11, 132, 30);
		panelOpcoes.add(btnLimparCampos);

		if (opcao.equals("Atualizar")) {

			preencheOsCampos(id_usuario);

		}

		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public void buscaCep() {

		// Faz a busca para o cep 58043280
		WebServiceCep webServiceCep = WebServiceCep.searchCep(txtCEP.getText());
		// A ferramenta de busca ignora qualquer caracter que n?o seja n?mero.

		// caso a busca ocorra bem, imprime os resultados.
		if (webServiceCep.wasSuccessful()) {

			txtLogradouro.setText(webServiceCep.getLogradouroFull());
			txtCidade.setText(webServiceCep.getCidade());
			txtBairro.setText(webServiceCep.getBairro());

			List<Estado> listEstado = new ArrayList<>();

			listEstado = EstadoDAO.listarEstados();

			for (int i = 0; listEstado.size() > i; i++) {

				int id = listEstado.get(i).getId_estado();
				String ufCombobox = listEstado.get(i).getUf();

				if (webServiceCep.getUf().equalsIgnoreCase(ufCombobox)) {

					cbEstado.setSelectedIndex(id);

				}

			}

		} else {

			if (webServiceCep.getResulCode() == -14) {

				JOptionPane.showMessageDialog(null, "Erro : Sem acesso a Internet!");

			} else if (webServiceCep.getResulCode() == 0) {

				txtLogradouro.setText("");
				txtCidade.setText("");
				txtBairro.setText("");

				JOptionPane.showMessageDialog(null, "CEP invalido!");

			}

		}

	}

	public void preencheOsCampos(int id_usuario) {

		List<Usuario> listUsuario = new ArrayList<>();
		listUsuario = UsuarioDAO.listarUsuarioPorIdDAO(id_usuario);

		int id_estado = listUsuario.get(0).getId_estado();
		int id_nivel = listUsuario.get(0).getId_nivel();
		String nome = listUsuario.get(0).getNome();
		String cpf = listUsuario.get(0).getCpf();
		String dataNasc = listUsuario.get(0).getDtNascimento();
		String sexo = listUsuario.get(0).getSexo();
		String login = listUsuario.get(0).getLogin();
		String senha = listUsuario.get(0).getSenha();
		String telefone = listUsuario.get(0).getTelefone();
		String cep = listUsuario.get(0).getCep();
		String cidade = listUsuario.get(0).getCidade();
		String logradouro = listUsuario.get(0).getLogradouro();
		String bairro = listUsuario.get(0).getBairro();
		int numero = listUsuario.get(0).getNumero();
		String email = listUsuario.get(0).getEmail();

		if (sexo.equalsIgnoreCase("M")) {
			rdoHomen.setSelected(true);
		} else if (sexo.equalsIgnoreCase("F")) {
			rdoMulher.setSelected(true);
		}

		ComboboxFuncionalidade.selecionaComboboxUsandoValorDoBanco(cbEstado, id_estado);

		ComboboxFuncionalidade.selecionaComboboxUsandoValorDoBanco(cbNivel, id_nivel);

		txtNome.setText(nome);
		txtCPF.setText(cpf);

		((JTextField) datechooser.getDateEditor().getUiComponent()).setText(dataNasc);
		// txtDataNasc.setDate(new Date()); pega data atual

		txtLogin.setText(login);
		pswfSenha.setText(senha);
		pswfComfirmaSenha.setText(senha);
		txtTelefone.setText(telefone);
		txtCEP.setText(cep);
		txtCidade.setText(cidade);
		txtLogradouro.setText(logradouro);
		txtBairro.setText(bairro);
		txtNumero.setText("" + numero);
		txtEmail.setText(email);

	}

	@SuppressWarnings("deprecation")
	public boolean verificarSenhas() {

		if (pswfSenha.getText().toString().equals(pswfComfirmaSenha.getText().toString())) {

			return true;

		} else {

			JOptionPane.showMessageDialog(null, "As senhas não correspondem!");

			return false;

		}

	}

	@SuppressWarnings("deprecation")
	public boolean verificarCampos() {

		if (txtNome.getText().isEmpty() || txtCPF.getText().isEmpty() || txtLogin.getText().isEmpty()
				|| !rdoHomen.isSelected() && !rdoMulher.isSelected() || pswfSenha.getText().isEmpty()
				|| pswfComfirmaSenha.getText().isEmpty() || txtTelefone.getText().isEmpty()
				|| txtCEP.getText().isEmpty() || txtCidade.getText().isEmpty() || txtLogradouro.getText().isEmpty()
				|| txtBairro.getText().isEmpty() || txtNumero.getText().isEmpty() || txtEmail.getText().isEmpty()) {

			return false;

		} else {

			return true;

		}

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
	
}
