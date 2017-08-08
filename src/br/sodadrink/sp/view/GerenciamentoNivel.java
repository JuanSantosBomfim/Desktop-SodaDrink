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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import br.sodadrink.sp.DAO.NivelDAO;
import br.sodadrink.sp.model.Nivel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class GerenciamentoNivel extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	DefaultTableModel modelo;
	JTable tableNivel;
	JPanel panelTabela;

	public GerenciamentoNivel(int id_nivel) {
		
		setClosable(true);
		setBounds(100, 100, 1100, 418);

		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 1103, 66);
		panelPrincipal.add(panelSuperior);

		JLabel lblGerenciamentoDeNivel = new JLabel("Gerenciamento de N\u00EDvel e Permiss\u00F5es de acesso");
		lblGerenciamentoDeNivel.setForeground(Color.WHITE);
		lblGerenciamentoDeNivel.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblGerenciamentoDeNivel.setBounds(24, 21, 575, 23);
		panelSuperior.add(lblGerenciamentoDeNivel);

		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setLayout(null);
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(10, 322, 1064, 55);
		panelPrincipal.add(panelOpcoes);

		JButton btnCadastrar = new JButton("Adicionar N\u00EDvel");
		btnCadastrar.setIcon(new ImageIcon(GerenciamentoNivel.class.getResource("/br/sodadrink/sp/imagens/ic_add_18pt.png")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastrarNivel cadastrarNivel = new CadastrarNivel("Cadastrar Nivel", "Cadastrar", 0);

			}
		});

		btnCadastrar.setBounds(10, 11, 145, 30);
		panelOpcoes.add(btnCadastrar);

		JButton button_1 = new JButton("Editar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int coluna = 0;
				int linha = tableNivel.getSelectedRow();

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione um nivel!");

				} else {

					String id_nivel = tableNivel.getModel().getValueAt(linha, coluna).toString();

					CadastrarNivel cadastrarNivel = new CadastrarNivel("Atualizar Nivel", "Atualizar",
							Integer.parseInt(id_nivel));

				}

			}
		});
		button_1.setIcon(new ImageIcon(GerenciamentoNivel.class.getResource("/br/sodadrink/sp/imagens/ic_mode_edit_18pt.png")));
		button_1.setBounds(165, 11, 100, 30);
		panelOpcoes.add(button_1);

		JButton button_2 = new JButton("Excluir");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int colunaNome = 1;
				int coluna = 0;
				int linha = tableNivel.getSelectedRow();

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione um nivel!");

				} else {

					String nome = tableNivel.getModel().getValueAt(linha, colunaNome).toString();
					
					String id_nivel_selecionado = tableNivel.getModel().getValueAt(linha, coluna).toString();			
					int id_nivel_selecionado_int = Integer.parseInt(id_nivel_selecionado);
					
					if(id_nivel == id_nivel_selecionado_int){
						
						JOptionPane.showMessageDialog(null, "Este nivel está sendo utilizado pelo atual usuario!");
						
					}else{
						
						int continua = JOptionPane.showConfirmDialog(null, "Deseja excluir o nivel " + nome, "Atenção!",
								JOptionPane.YES_NO_OPTION);

						if (continua == 0) {

							boolean resultado = NivelDAO.excluirNivelDAO(id_nivel_selecionado_int);

							if (resultado == true) {

								JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
								recarregarTabela();

							} else {

								JOptionPane.showMessageDialog(null, "Erro ao excluir, verifique se esse nivel está sendo utilizado!");

							}

						}
						
					}

				}

			}
		});
		button_2.setIcon(new ImageIcon(GerenciamentoNivel.class.getResource("/br/sodadrink/sp/imagens/ic_delete_forever_18pt.png")));
		button_2.setBounds(278, 11, 100, 30);
		panelOpcoes.add(button_2);

		JButton button_3 = new JButton("Atualizar Tabela");
		button_3.setIcon(
				new ImageIcon(GerenciamentoNivel.class.getResource("/br/sodadrink/sp/imagens/ic_autorenew_18pt.png")));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				recarregarTabela();

			}
		});

		button_3.setBounds(388, 11, 145, 30);
		panelOpcoes.add(button_3);

		panelTabela = new JPanel();
		panelTabela.setBounds(10, 77, 1064, 234);
		panelPrincipal.add(panelTabela);

		exibirNivel();

		this.setVisible(true);

	}

	public void exibirNivel() {

		modelo = new DefaultTableModel();
		tableNivel = new JTable();
		tableNivel.setModel(modelo);

		modelo.addColumn("ID");
		modelo.addColumn("NOME NIVEL");
		
		modelo.addColumn("CATEGORIA");
		modelo.addColumn("ESTOQUE");
		modelo.addColumn("FORNECEDOR");
		modelo.addColumn("MARCA");

		modelo.addColumn("NIVEL");
		modelo.addColumn("PRODUTO");
		modelo.addColumn("USUARIO");
		modelo.addColumn("RELATORIO");
		
		tableNivel.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableNivel.getColumnModel().getColumn(1).setPreferredWidth(130);
		
		tableNivel.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableNivel.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableNivel.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableNivel.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		tableNivel.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableNivel.getColumnModel().getColumn(7).setPreferredWidth(100);
		tableNivel.getColumnModel().getColumn(8).setPreferredWidth(100);
		tableNivel.getColumnModel().getColumn(9).setPreferredWidth(100);
		
		tableNivel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				
				tableNivel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
		});

		exibirItensTabela();

		panelTabela.setLayout(null);

		JScrollPane scrollPanel = new JScrollPane(tableNivel);
		scrollPanel.setBounds(0, 0, 1064, 232);
		panelTabela.add(scrollPanel);

	}

	public void exibirItensTabela() {

		List<Nivel> listNivel = new ArrayList<>();

		listNivel = NivelDAO.listarNivelDAO();
		
		for (int i = 0; listNivel.size() > i; i++) {
			
			String categoria = "Não";
			String estoque = "Não";
			String fornecedor = "Não";
			String marca = "Não";
			
			if(listNivel.get(i).getCategoria() == 1){
				categoria = "Sim";
			}
			if(listNivel.get(i).getEstoque() == 1){
				estoque = "Sim";
			}
			if(listNivel.get(i).getFornecedor() == 1){
				fornecedor = "Sim";
			}
			if(listNivel.get(i).getMarca() == 1){
				marca = "Sim";
			}
			
			String nivel = "Não";
			String produto = "Não";
			String usuario = "Não";
			String descricao = "Não";
			
			if(listNivel.get(i).getNivel() == 1){
				nivel = "Sim";
			}
			if(listNivel.get(i).getProduto() == 1){
				produto = "Sim";
			}
			if(listNivel.get(i).getUsuario() == 1){
				usuario = "Sim";
			}
			if(listNivel.get(i).getRelatorio() == 1){
				descricao = "Sim";
			}

			modelo.addRow(new Object[] { listNivel.get(i).getId_nivel(), listNivel.get(i).getDescricao(),
											
					categoria, estoque, fornecedor, marca, 
					
					nivel, produto, usuario, descricao});

		}
	}

	public void recarregarTabela() {

		DefaultTableModel tm = (DefaultTableModel) tableNivel.getModel();

		while (tableNivel.getRowCount() > 0) {
			((DefaultTableModel) tableNivel.getModel()).removeRow(0);
		}

		exibirItensTabela();

	}

}
