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
import br.sodadrink.sp.DAO.CategoriaDAO;
import br.sodadrink.sp.model.Categoria;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class GerenciamentoCategoria extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	DefaultTableModel modelo;
	JTable tableCategoria;
	JPanel panelTabela;
	
	public GerenciamentoCategoria() {
		setClosable(true);
		setBounds(100, 100, 599, 418);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 596, 66);
		panel.add(panelSuperior);
		
		JLabel lblGerenciamentoDeCategoria = new JLabel("Gerenciamento de Categoria");
		lblGerenciamentoDeCategoria.setForeground(Color.WHITE);
		lblGerenciamentoDeCategoria.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblGerenciamentoDeCategoria.setBounds(24, 21, 301, 23);
		panelSuperior.add(lblGerenciamentoDeCategoria);
		
		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setLayout(null);
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(10, 322, 563, 55);
		panel.add(panelOpcoes);
		
		JButton btnAdicionarCategoria = new JButton("Adicionar Categoria");
		btnAdicionarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CadastrarCategoria cadastrarCategoria = new CadastrarCategoria("Cadastrar Categoria","Cadastrar",0);
				
			}
		});
		
		btnAdicionarCategoria.setIcon(new ImageIcon(GerenciamentoCategoria.class.getResource("/br/sodadrink/sp/imagens/ic_add_18pt.png")));
		btnAdicionarCategoria.setBounds(10, 11, 165, 30);
		panelOpcoes.add(btnAdicionarCategoria);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int coluna = 0;
				int linha = tableCategoria.getSelectedRow();
				
				if(linha == -1){
					
					JOptionPane.showMessageDialog(null,"Selecione uma categoria!");
					
				}else{
					
					String id_categoria = tableCategoria.getModel().getValueAt(linha, coluna).toString();

					CadastrarCategoria cadastrarCategoria = new CadastrarCategoria("Atualizar Categoria","Atualizar",Integer.parseInt(id_categoria));
					
					
				}		
				
			}
		});
		btnEditar.setIcon(new ImageIcon(GerenciamentoCategoria.class.getResource("/br/sodadrink/sp/imagens/ic_mode_edit_18pt.png")));
		btnEditar.setBounds(185, 11, 100, 30);
		panelOpcoes.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int colunaNome = 1;
				int coluna = 0;
				int linha = tableCategoria.getSelectedRow();
				
				if(linha == -1){
					
					JOptionPane.showMessageDialog(null,"Selecione uma categoria!");
					
				}else{
					
					String nome = tableCategoria.getModel().getValueAt(linha, colunaNome).toString();

					int continua = JOptionPane.showConfirmDialog(null,"Deseja excluir a categoria "+nome, "Atenção!",JOptionPane.YES_NO_OPTION);
					
					
					if(continua == 0){
						
						String id_categoria = tableCategoria.getModel().getValueAt(linha, coluna).toString();
						
						boolean resultado = CategoriaDAO.excluirCategoriaDAO(Integer.parseInt(id_categoria));
						
						if (resultado == true) {

							JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
							recarregarTabela();

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao excluir, essa categoria está sendo utilizada!");
						}
						
					}
					
					
				}
				
			}
		});
		btnExcluir.setIcon(new ImageIcon(GerenciamentoCategoria.class.getResource("/br/sodadrink/sp/imagens/ic_delete_forever_18pt.png")));
		btnExcluir.setBounds(298, 11, 100, 30);
		panelOpcoes.add(btnExcluir);
		
		JButton btnAdicionarTabela = new JButton("Atualizar Tabela");
		btnAdicionarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				recarregarTabela();
				
			}
		});
		
		btnAdicionarTabela.setIcon(new ImageIcon(GerenciamentoCategoria.class.getResource("/br/sodadrink/sp/imagens/ic_autorenew_18pt.png")));
		btnAdicionarTabela.setBounds(408, 11, 145, 30);
		panelOpcoes.add(btnAdicionarTabela);
		
		panelTabela = new JPanel();
		panelTabela.setBounds(10, 77, 563, 234);
		panel.add(panelTabela);
		
		exibirCategoria();
		
		this.setVisible(true);

	}
	
	public void exibirCategoria() {

		modelo = new DefaultTableModel();
		tableCategoria = new JTable();
		tableCategoria.setModel(modelo);

		modelo.addColumn("ID");
		modelo.addColumn("CATEGORIA");

		tableCategoria.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableCategoria.getColumnModel().getColumn(1).setPreferredWidth(466);
		
		tableCategoria.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				
				tableCategoria.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
		});

		listarIntensCategoria();

		panelTabela.setLayout(null);

		JScrollPane scrollPanel = new JScrollPane(tableCategoria);
		scrollPanel.setBounds(0, 0, 563, 232);
		panelTabela.add(scrollPanel);

	}
	
	public void listarIntensCategoria(){
		
		List<Categoria> listCategoria = new ArrayList<>();

		listCategoria = CategoriaDAO.listarCategoriaDAO();

		for (int i = 0; listCategoria.size() > i; i++) {

			modelo.addRow(new Object[] { listCategoria.get(i).getId_categoria(), listCategoria.get(i).getDescricao() });

		}
		
	}

	public void recarregarTabela() {

		DefaultTableModel tm = (DefaultTableModel) tableCategoria.getModel();

		while (tableCategoria.getRowCount() > 0) {
			((DefaultTableModel) tableCategoria.getModel()).removeRow(0);
		}

		listarIntensCategoria();

	}
}
