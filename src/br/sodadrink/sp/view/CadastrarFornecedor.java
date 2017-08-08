package br.sodadrink.sp.view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import br.sodadrink.sp.DAO.EstadoDAO;
import br.sodadrink.sp.DAO.FornecedorDAO;
import br.sodadrink.sp.controller.ComboboxFuncionalidade;
import br.sodadrink.sp.controller.TamanhoCampo;
import br.sodadrink.sp.controller.WebServiceCep;
import br.sodadrink.sp.model.Estado;
import br.sodadrink.sp.model.Fornecedor;
import br.sodadrink.sp.model.ItemComboBox;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastrarFornecedor extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel panelPrincipal = new JPanel();
	private JTextField txtRazaoSocial;
	private JTextField txtNomeFantasia;
	private JTextField txtInscricaoEstadual;
	private JTextField txtEmail;
	private JTextField txtCEP;
	private JTextField txtCidade;
	private JTextField txtLogradouro;
	private JTextField txtBairro;
	private JTextField txtNumero;
	private JTextField txtCNPJ;
	private JTextField txtTelefone;
	private JComboBox<ItemComboBox> cbEstado;

	public CadastrarFornecedor(String titulo, String opcao, int id_fornecedor) {

		setBounds(100, 100, 729, 472);
		getContentPane().setLayout(new BorderLayout());
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 713, 66);
		panelPrincipal.add(panelSuperior);

		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblTitulo.setBounds(24, 21, 295, 23);
		panelSuperior.add(lblTitulo);

		JPanel panelDadosFornecedor = new JPanel();
		panelDadosFornecedor.setLayout(null);
		panelDadosFornecedor.setBounds(10, 77, 693, 280);
		TitledBorder titledBorderFornecedor = new TitledBorder("Dados do Fornecedor");
		panelDadosFornecedor.setBorder(titledBorderFornecedor);
		panelPrincipal.add(panelDadosFornecedor);

		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setDocument(new TamanhoCampo(30));
		txtRazaoSocial.setColumns(10);
		txtRazaoSocial.setBounds(10, 50, 239, 31);
		panelDadosFornecedor.add(txtRazaoSocial);

		txtNomeFantasia = new JTextField();
		txtNomeFantasia.setDocument(new TamanhoCampo(30));
		txtNomeFantasia.setColumns(10);
		txtNomeFantasia.setBounds(259, 50, 234, 31);
		panelDadosFornecedor.add(txtNomeFantasia);

		txtInscricaoEstadual = new JTextField();
		txtInscricaoEstadual.setDocument(new TamanhoCampo(9));

		//Mascara InscricaoEstadual
	     try{
	           javax.swing.text.MaskFormatter format_textField3 = new javax.swing.text.MaskFormatter("###.###.###.###");
	           txtInscricaoEstadual = new javax.swing.JFormattedTextField(format_textField3);
	           
	        }catch (Exception e){}
	    //Fim mascara
		
		txtInscricaoEstadual.setBounds(10, 108, 217, 31);

		camposNumericos(txtInscricaoEstadual);

		panelDadosFornecedor.add(txtInscricaoEstadual);

		txtEmail = new JTextField();
		txtEmail.setDocument(new TamanhoCampo(30));
		txtEmail.setColumns(10);
		txtEmail.setBounds(237, 109, 217, 31);
		panelDadosFornecedor.add(txtEmail);

		JLabel lblRazaoSocial = new JLabel("Raz\u00E3o Social");
		lblRazaoSocial.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblRazaoSocial.setBounds(10, 32, 102, 14);
		panelDadosFornecedor.add(lblRazaoSocial);

		JLabel lblNomeFantasia = new JLabel("Nome Fantasia");
		lblNomeFantasia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNomeFantasia.setBounds(259, 34, 124, 14);
		panelDadosFornecedor.add(lblNomeFantasia);

		JLabel lblInscrioEstadual = new JLabel("Inscri\u00E7\u00E3o Estadual");
		lblInscrioEstadual.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblInscrioEstadual.setBounds(10, 92, 131, 14);
		panelDadosFornecedor.add(lblInscrioEstadual);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEmail.setBounds(237, 92, 68, 14);
		panelDadosFornecedor.add(lblEmail);

		txtCEP = new JTextField();
		txtCEP.setDocument(new TamanhoCampo(8));
		
		txtCEP.setBounds(10, 168, 230, 31);

		camposNumericos(txtCEP);

		txtCEP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				if (txtCEP.getText().toString().length() == 8) {

					buscaCep();

				}

			}
		});

		panelDadosFornecedor.add(txtCEP);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCep.setBounds(10, 150, 63, 14);
		panelDadosFornecedor.add(lblCep);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEstado.setBounds(250, 151, 82, 14);
		panelDadosFornecedor.add(lblEstado);

		txtCidade = new JTextField();
		txtCidade.setDocument(new TamanhoCampo(30));
		txtCidade.setColumns(10);
		txtCidade.setBounds(399, 169, 284, 31);
		panelDadosFornecedor.add(txtCidade);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCidade.setBounds(399, 151, 284, 14);
		panelDadosFornecedor.add(lblCidade);

		txtLogradouro = new JTextField();
		// TODO :MUDAR NO BANCO O TOTAL DE CARACTERS
		txtLogradouro.setDocument(new TamanhoCampo(100));
		txtLogradouro.setColumns(10);
		txtLogradouro.setBounds(258, 229, 340, 31);
		panelDadosFornecedor.add(txtLogradouro);

		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblLogradouro.setBounds(259, 206, 150, 24);
		panelDadosFornecedor.add(lblLogradouro);

		txtBairro = new JTextField();
		txtBairro.setDocument(new TamanhoCampo(30));
		txtBairro.setColumns(10);
		txtBairro.setBounds(10, 229, 238, 31);
		panelDadosFornecedor.add(txtBairro);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblBairro.setBounds(10, 211, 63, 14);
		panelDadosFornecedor.add(lblBairro);

		txtNumero = new JTextField();
		txtNumero.setDocument(new TamanhoCampo(10));
		txtNumero.setColumns(10);
		txtNumero.setBounds(608, 229, 75, 31);

		camposNumericos(txtNumero);

		panelDadosFornecedor.add(txtNumero);

		JLabel lblNumero = new JLabel("N\u00B0");
		lblNumero.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNumero.setBounds(608, 211, 63, 14);
		panelDadosFornecedor.add(lblNumero);

		cbEstado = new JComboBox<ItemComboBox>();
		cbEstado.setBounds(250, 169, 139, 31);

		List<Estado> listEstado = new ArrayList<>();

		listEstado = EstadoDAO.listarEstados();

		for (int i = 0; listEstado.size() > i; i++) {

			int id = listEstado.get(i).getId_estado();
			String nome = listEstado.get(i).getEstado();

			cbEstado.addItem(new ItemComboBox(id, nome, i));

		}

		panelDadosFornecedor.add(cbEstado);

		txtCNPJ = new JTextField();
		txtCNPJ.setDocument(new TamanhoCampo(14));
		//Mascara cnpj
	     try{
	           javax.swing.text.MaskFormatter format_textField3 = new javax.swing.text.MaskFormatter("##.###.###/####-##");
	           txtCNPJ = new javax.swing.JFormattedTextField(format_textField3);
	           
	        }catch (Exception e){}
	    //Fim mascara
		txtCNPJ.setBounds(503, 50, 178, 31);

		camposNumericos(txtCNPJ);

		panelDadosFornecedor.add(txtCNPJ);

		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCnpj.setBounds(503, 33, 178, 14);
		panelDadosFornecedor.add(lblCnpj);
		
		txtTelefone = new JTextField();
		txtTelefone.setDocument(new TamanhoCampo(14));

		//Mascara telefone
	     try{
	           javax.swing.text.MaskFormatter format_textField3 = new javax.swing.text.MaskFormatter("(##)####-####");
	           txtTelefone = new javax.swing.JFormattedTextField(format_textField3);
	           
	        }catch (Exception e){}
	    //Fim mascara
		
		txtTelefone.setBounds(464, 109, 217, 31);

		camposNumericos(txtTelefone);

		panelDadosFornecedor.add(txtTelefone);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTelefone.setBounds(464, 92, 75, 14);
		panelDadosFornecedor.add(lblTelefone);

		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setLayout(null);
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(10, 368, 693, 55);
		panelPrincipal.add(panelOpcoes);

		JButton btnEvento = new JButton(opcao);
		btnEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean continua = verificarCampos();

				if (continua == true) {

					Fornecedor fornecedor = new Fornecedor();

					ItemComboBox idestado = (ItemComboBox) cbEstado.getSelectedItem();
					int estadoCombobox = idestado.getId_item();

					fornecedor.setIdEstado(estadoCombobox);
					fornecedor.setCep(txtCEP.getText().toString());
					fornecedor.setCidade(txtCidade.getText().toString());
					fornecedor.setLogradouro(txtLogradouro.getText().toString());
					fornecedor.setBairro(txtBairro.getText().toString());
					fornecedor.setNumero(Integer.parseInt(txtNumero.getText().toString()));
					fornecedor.setEmail(txtTelefone.getText().toString());
					fornecedor.setTelefone(txtTelefone.getText().toString());
					fornecedor.setRazaoSocial(txtRazaoSocial.getText().toString());
					fornecedor.setNomeFantasia(txtNomeFantasia.getText().toString());
					fornecedor.setCnpj(txtCNPJ.getText().toString());
					fornecedor.setInscricaoEstadual(txtInscricaoEstadual.getText().toString());

					if (opcao.equals("Cadastrar")) {

						boolean resultadoCadastro = FornecedorDAO.cadastrarFornecedorDAO(fornecedor);

						if (resultadoCadastro == true) {

							JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao Cadastrar!");
						}

					} else if (opcao.equals("Atualizar")) {

						fornecedor.setIdFornecedor(id_fornecedor);

						boolean resultadoEditar = FornecedorDAO.editarFornecedorDAO(fornecedor);

						if (resultadoEditar == true) {

							JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao Atualizar!");
						}

					}

				} else {

					JOptionPane.showMessageDialog(null, "Preencha Todos os Campos!");

				}

			}
		});

		btnEvento.setIcon(new ImageIcon(CadastrarFornecedor.class.getResource("/br/sodadrink/sp/imagens/ic_check_18pt.png")));
		btnEvento.setBounds(10, 11, 132, 30);
		panelOpcoes.add(btnEvento);

		JButton btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtRazaoSocial.setText("");
				txtNomeFantasia.setText("");
				txtInscricaoEstadual.setText("");
				txtEmail.setText("");
				txtCEP.setText("");
				txtCidade.setText("");
				txtLogradouro.setText("");
				txtBairro.setText("");
				txtNumero.setText("");
				txtCNPJ.setText("");
				txtTelefone.setText("");
				
				cbEstado.setSelectedIndex(0);
				
			}
		});
		
		btnLimparCampos.setBounds(152, 11, 132, 30);
		panelOpcoes.add(btnLimparCampos);

		if (opcao.equals("Atualizar")) {

			preencheOsCampos(id_fornecedor);

		}

		this.setLocationRelativeTo(null);
		this.setModal(true);
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

	public void preencheOsCampos(int id_fornecedor) {

		List<Fornecedor> listFornecedor = new ArrayList<>();
		listFornecedor = FornecedorDAO.listarFornecedorPorId(id_fornecedor);

		listFornecedor.get(0).getIdFornecedor();
		int estado = listFornecedor.get(0).getIdEstado();
		String cep = listFornecedor.get(0).getCep();
		String cidade = listFornecedor.get(0).getCidade();
		String logradouro = listFornecedor.get(0).getLogradouro();
		String bairro = listFornecedor.get(0).getBairro();
		int numero = listFornecedor.get(0).getNumero();
		String email = listFornecedor.get(0).getEmail();
		String telefone = listFornecedor.get(0).getTelefone();
		String razaoSocial = listFornecedor.get(0).getRazaoSocial();
		String nomeFantasia = listFornecedor.get(0).getNomeFantasia();
		String cnpj = listFornecedor.get(0).getCnpj();
		String inscricaoEstadual = listFornecedor.get(0).getInscricaoEstadual();

		ComboboxFuncionalidade.selecionaComboboxUsandoValorDoBanco(cbEstado, estado);

		txtCEP.setText(cep);
		txtCidade.setText(cidade);
		txtLogradouro.setText(logradouro);
		txtBairro.setText(bairro);
		txtNumero.setText("" + numero);
		txtEmail.setText(email);
		txtTelefone.setText(telefone);
		txtRazaoSocial.setText(razaoSocial);
		txtNomeFantasia.setText(nomeFantasia);
		txtCNPJ.setText(cnpj);
		txtInscricaoEstadual.setText(inscricaoEstadual);

	}

	public boolean verificarCampos() {

		if (txtCEP.getText().isEmpty() || txtCidade.getText().isEmpty() || txtLogradouro.getText().isEmpty()
				|| txtBairro.getText().isEmpty() || txtNumero.getText().isEmpty() || txtTelefone.getText().isEmpty()
				|| txtRazaoSocial.getText().isEmpty() || txtNomeFantasia.getText().isEmpty()
				|| txtCNPJ.getText().isEmpty() || txtInscricaoEstadual.getText().isEmpty()
				|| txtEmail.getText().isEmpty()) {

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
