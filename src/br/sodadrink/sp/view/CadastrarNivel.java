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
import javax.swing.border.TitledBorder;
import br.sodadrink.sp.DAO.NivelDAO;
import br.sodadrink.sp.model.Nivel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class CadastrarNivel extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNomeNivel;

	public CadastrarNivel(String titulo, String opcao, int id_nivel) {

		setBounds(100, 100, 511, 451);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 507, 66);
		contentPanel.add(panelSuperior);

		JLabel label = new JLabel(titulo);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.BOLD, 22));
		label.setBounds(24, 21, 246, 23);
		panelSuperior.add(label);

		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setLayout(null);
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(10, 347, 475, 55);
		contentPanel.add(panelOpcoes);

		JPanel panelDadosNivel = new JPanel();
		panelDadosNivel.setLayout(null);
		TitledBorder border = new TitledBorder("Permissões do Nível");
		panelDadosNivel.setBorder(border);
		panelDadosNivel.setBounds(10, 167, 475, 174);
		contentPanel.add(panelDadosNivel);

		JCheckBox ckbUsuarios = new JCheckBox("Gerenciamento de Usuarios");
		ckbUsuarios.setBounds(16, 51, 220, 23);
		panelDadosNivel.add(ckbUsuarios);

		JCheckBox ckbProdutos = new JCheckBox("Gerenciamento de Produtos");
		ckbProdutos.setBounds(16, 77, 220, 23);
		panelDadosNivel.add(ckbProdutos);

		JCheckBox ckbNiveis = new JCheckBox("Gerenciamento de Níveis");
		ckbNiveis.setBounds(238, 77, 220, 23);
		panelDadosNivel.add(ckbNiveis);

		JCheckBox ckbEstoque = new JCheckBox("Gerenciamento de Estoque");
		ckbEstoque.setBounds(16, 103, 220, 23);
		panelDadosNivel.add(ckbEstoque);

		JCheckBox ckbCategorias = new JCheckBox("Gerenciamento de Categorias");
		ckbCategorias.setBounds(238, 25, 220, 23);
		panelDadosNivel.add(ckbCategorias);

		JCheckBox ckbMarca = new JCheckBox("Gerenciamento de Marca");
		ckbMarca.setBounds(238, 51, 220, 23);
		panelDadosNivel.add(ckbMarca);

		JCheckBox ckbFornecedor = new JCheckBox("Gerenciamento de Fornecedor");
		ckbFornecedor.setBounds(16, 25, 220, 23);
		panelDadosNivel.add(ckbFornecedor);

		JCheckBox ckbRelatorios = new JCheckBox("Gerenciamento de Relatórios");
		ckbRelatorios.setBounds(238, 103, 220, 23);
		panelDadosNivel.add(ckbRelatorios);

		JButton btnMarcarTodos = new JButton("Marcar Todos");
		btnMarcarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ckbCategorias.setSelected(true);
				ckbEstoque.setSelected(true);
				ckbFornecedor.setSelected(true);
				ckbMarca.setSelected(true);

				ckbNiveis.setSelected(true);
				ckbProdutos.setSelected(true);
				ckbUsuarios.setSelected(true);
				ckbRelatorios.setSelected(true);

			}
		});

		btnMarcarTodos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnMarcarTodos.setBounds(215, 137, 120, 26);
		panelDadosNivel.add(btnMarcarTodos);

		JButton btnDesmarcartodos = new JButton("Desmarcar Todos");
		btnDesmarcartodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ckbCategorias.setSelected(false);
				ckbEstoque.setSelected(false);
				ckbFornecedor.setSelected(false);
				ckbMarca.setSelected(false);

				ckbNiveis.setSelected(false);
				ckbProdutos.setSelected(false);
				ckbUsuarios.setSelected(false);
				ckbRelatorios.setSelected(false);

			}
		});

		btnDesmarcartodos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnDesmarcartodos.setBounds(345, 137, 120, 26);
		panelDadosNivel.add(btnDesmarcartodos);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 77, 475, 79);
		contentPanel.add(panel);
		panel.setLayout(null);

		txtNomeNivel = new JTextField();
		txtNomeNivel.setBounds(20, 37, 280, 31);
		panel.add(txtNomeNivel);
		txtNomeNivel.setToolTipText("");
		txtNomeNivel.setText("");
		txtNomeNivel.setColumns(10);

		JLabel lblNomeDoNivel = new JLabel("Nome do N\u00EDvel");
		lblNomeDoNivel.setBounds(10, 12, 122, 14);
		panel.add(lblNomeDoNivel);
		lblNomeDoNivel.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		JButton btnEvento = new JButton(opcao);
		btnEvento
				.setIcon(new ImageIcon(CadastrarNivel.class.getResource("/br/sodadrink/sp/imagens/ic_check_18pt.png")));
		btnEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean continua = verificaCampo();

				if (continua == true) {

					Nivel nivel = new Nivel();

					nivel.setDescricao(txtNomeNivel.getText().toString());

					//Niveis Marcados
					if(ckbCategorias.isSelected()){
						
						nivel.setCategoria(1);
						
					}
					if(ckbEstoque.isSelected()){
						
						nivel.setEstoque(1);
						
					}
					if(ckbFornecedor.isSelected()){
						
						nivel.setFornecedor(1);
						
					}
					if(ckbMarca.isSelected()){
						
						nivel.setMarca(1);
						
					}
					if(ckbNiveis.isSelected()){
						
						nivel.setNivel(1);
						
					}
					if(ckbProdutos.isSelected()){
						
						nivel.setProduto(1);
						
					}
					if(ckbUsuarios.isSelected()){
						
						nivel.setUsuario(1);
						
					}
					if(ckbRelatorios.isSelected()){
						
						nivel.setRelatorio(1);
						
					}
					
					//Niveis vazios
					if(!ckbCategorias.isSelected()){
						
						nivel.setCategoria(0);
						
					}
					if(!ckbEstoque.isSelected()){
						
						nivel.setEstoque(0);
						
					}
					if(!ckbFornecedor.isSelected()){
						
						nivel.setFornecedor(0);
						
					}
					if(!ckbMarca.isSelected()){
						
						nivel.setMarca(0);
						
					}
					if(!ckbNiveis.isSelected()){
						
						nivel.setNivel(0);
						
					}
					if(!ckbProdutos.isSelected()){
						
						nivel.setProduto(0);
						
					}
					if(!ckbUsuarios.isSelected()){
						
						nivel.setUsuario(0);
						
					}
					if(!ckbRelatorios.isSelected()){
						
						nivel.setRelatorio(0);
						
					}
					
					//Cadastrar Nivel
					if (opcao.equals("Cadastrar")) {

						boolean retorno = NivelDAO.cadastrarNivelDAO(nivel);

						if (retorno == true) {

							JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");
						}

					//Atualizar Nivel
					} else if (opcao.equals("Atualizar")) {

						nivel.setId_nivel(id_nivel);

						boolean retorno = NivelDAO.editarNivelDAO(nivel);

						if (retorno == true) {

							JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao Atualizar!");
						}

					}

				} else {

					JOptionPane.showMessageDialog(null, "Preencha o Campo Nome Nivel!");

				}

			}
		});

		btnEvento.setBounds(10, 11, 132, 30);
		panelOpcoes.add(btnEvento);

		if (opcao.equals("Atualizar")) {

			List<Nivel> listNivel = new ArrayList<>();

			listNivel = NivelDAO.listarNivelPorIdDAO(id_nivel);

			String descricao = listNivel.get(0).getDescricao();

			txtNomeNivel.setText(descricao);
			
			int categoria = listNivel.get(0).getCategoria();
			int estoque = listNivel.get(0).getEstoque();
			int fornecedor = listNivel.get(0).getFornecedor();
			int marca = listNivel.get(0).getMarca();

			int nivel = listNivel.get(0).getNivel();
			int produto = listNivel.get(0).getProduto();
			int usuario = listNivel.get(0).getUsuario();
			int relatorio = listNivel.get(0).getRelatorio();

			if(categoria == 1){
				
				ckbCategorias.setSelected(true);
				
			}
			if(estoque == 1){
				
				ckbEstoque.setSelected(true);
				
			}
			if(fornecedor == 1){
				
				ckbFornecedor.setSelected(true);
				
			}
			if(marca == 1){
				
				ckbMarca.setSelected(true);
				
			}
			if(nivel == 1){
				
				ckbNiveis.setSelected(true);
				
			}
			if(produto == 1){
				
				ckbProdutos.setSelected(true);
				
			}
			if(usuario == 1){
				
				ckbUsuarios.setSelected(true);
				
			}
			if(relatorio == 1){
				
				ckbRelatorios.setSelected(true);
				
			}
			
		}

		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
	}

	public boolean verificaCampo() {

		if (txtNomeNivel.getText().isEmpty()) {

			return false;
		} else {
			return true;
		}

	}
}
