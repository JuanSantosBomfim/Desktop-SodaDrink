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
import javax.swing.JComboBox;
import br.sodadrink.sp.DAO.EstoqueDAO;
import br.sodadrink.sp.DAO.PrateleiraDAO;
import br.sodadrink.sp.DAO.ProdutoDAO;
import br.sodadrink.sp.controller.ComboboxFuncionalidade;
import br.sodadrink.sp.model.Estoque;
import br.sodadrink.sp.model.ItemComboBox;
import br.sodadrink.sp.model.Prateleira;
import br.sodadrink.sp.model.Produto;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastrarEstoque extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	JComboBox<ItemComboBox> cbPrateleira;
	JComboBox<ItemComboBox> cbProduto;

	public CadastrarEstoque(String titulo, String opcao, int id_estoque) {
		setBounds(100, 100, 475, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 255));
		panel.setLayout(null);
		setBounds(100, 100, 395, 369);
		panel.setBounds(0, 0, 379, 66);
		contentPanel.add(panel);

		JLabel label = new JLabel(titulo);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.BOLD, 22));
		label.setBounds(24, 21, 425, 23);
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 73, 359, 181);
		contentPanel.add(panel_1);

		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblProduto.setBounds(10, 11, 140, 14);
		panel_1.add(lblProduto);

		cbProduto = new JComboBox<ItemComboBox>();
		cbProduto.setBounds(10, 36, 339, 31);
		
		List<Produto> listProdutoVerificacao = new ArrayList<>();
		listProdutoVerificacao = ProdutoDAO.listarProdutoForaDeEstoqueDAO();
		
		if(listProdutoVerificacao.size() == 0){
			
			cbProduto.addItem(new ItemComboBox(0, "Não há nenhum produto fora de estoque!", 0));
			
		}else{
			
			if(opcao.equals("Cadastrar")){
				
				List<Produto> listProduto = new ArrayList<>();
				listProduto = ProdutoDAO.listarProdutoForaDeEstoqueDAO();

				for (int i = 0; listProduto.size() > i; i++) {

					int id_produto = listProduto.get(i).getId_produto();
					String nome = listProduto.get(i).getNome();

					cbProduto.addItem(new ItemComboBox(id_produto, nome, i));

				}
				
			}else{
				
				List<Estoque> listEstoque = new ArrayList<>();
				listEstoque = EstoqueDAO.listarEstoquePorIdDAO(id_estoque);
				
				List<Produto> listProduto = new ArrayList<>();
				listProduto = ProdutoDAO.listarProdutoPorIdDAO(listEstoque.get(0).getId_produto());

				for (int i = 0; listProduto.size() > i; i++) {

					int id_produto = listProduto.get(i).getId_produto();
					String nome = listProduto.get(i).getNome();

					cbProduto.addItem(new ItemComboBox(id_produto, nome, i));

				}
				
			}
			
		}	

		panel_1.add(cbProduto);

		cbPrateleira = new JComboBox<ItemComboBox>();
		cbPrateleira.setBounds(10, 112, 339, 31);

		List<Prateleira> listPrateleira = new ArrayList<>();
		listPrateleira = PrateleiraDAO.listarPrateleiraDAO();
		
		if(listPrateleira.size() == 0){
			
			cbPrateleira.addItem(new ItemComboBox(0, "Nenhuma prateleira encontrada!", 0));
			
		}else{
			
			for (int i = 0; listPrateleira.size() > i; i++) {

				int id_prateleira = listPrateleira.get(i).getId_prateleira();
				String nome = listPrateleira.get(i).getNome();

				cbPrateleira.addItem(new ItemComboBox(id_prateleira, nome, i));

			}
			
		}

		panel_1.add(cbPrateleira);

		JLabel lblPertenceAPrateleira = new JLabel("Pertence a Prateleira:");
		lblPertenceAPrateleira.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPertenceAPrateleira.setBounds(10, 87, 140, 14);
		panel_1.add(lblPertenceAPrateleira);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 265, 359, 55);
		contentPanel.add(panel_2);

		JButton btnCadastrar = new JButton(opcao);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ItemComboBox comoboxPrateleira = (ItemComboBox) cbPrateleira.getSelectedItem();
				int id_prateleira = comoboxPrateleira.getId_item();

				ItemComboBox comoboxProduto = (ItemComboBox) cbProduto.getSelectedItem();
				int id_produto = comoboxProduto.getId_item();

				Estoque estoque = new Estoque();
				estoque.setId_prateleira(id_prateleira);
				estoque.setId_produto(id_produto);
				estoque.setId_estoque(id_estoque);

				if (id_prateleira == 0 || id_produto == 0) {

					JOptionPane.showMessageDialog(null, "Não é possivel cadastrar!");

				} else {
					
					if(opcao.equals("Cadastrar")){
						
						boolean resultado = EstoqueDAO.cadastrarEstoqueDAO(estoque);

						if (resultado == true) {

							JOptionPane.showMessageDialog(null, "Casdastrado com Sucesso!");
							dispose();

						} else {

							JOptionPane.showMessageDialog(null, "Erro ao Casdastrar!");

						}
						
					}else{
						
						boolean resultado = EstoqueDAO.editarEstoqueDAO(estoque);

						if (resultado == true) {

							JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!");
							dispose();

						} else {

							JOptionPane.showMessageDialog(null, "Erro ao Atualizar!");

						}
						
					}			

				}

			}
		});

		btnCadastrar.setIcon(
				new ImageIcon(CadastrarEstoque.class.getResource("/br/sodadrink/sp/imagens/ic_check_18pt.png")));
		btnCadastrar.setBounds(10, 11, 132, 30);
		panel_2.add(btnCadastrar);

		if (opcao.equals("Atualizar")) {

			preencherCampos(id_estoque);

		}

		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void preencherCampos(int id_estoque) {

		List<Estoque> listaEstoque = new ArrayList<>();

		listaEstoque = EstoqueDAO.listarEstoquePorIdDAO(id_estoque);

		int id_produto = listaEstoque.get(0).getId_produto();
		int id_prateleira = listaEstoque.get(0).getId_prateleira();

		ComboboxFuncionalidade.selecionaComboboxUsandoValorDoBanco(cbProduto, id_produto);
		ComboboxFuncionalidade.selecionaComboboxUsandoValorDoBanco(cbPrateleira, id_prateleira);

	}

}
