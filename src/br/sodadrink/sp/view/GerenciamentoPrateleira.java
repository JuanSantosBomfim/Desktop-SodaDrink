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
import br.sodadrink.sp.DAO.EstantePrateleiraDAO;
import br.sodadrink.sp.DAO.EstoqueDAO;
import br.sodadrink.sp.DAO.PrateleiraDAO;
import br.sodadrink.sp.model.EstantePrateleira;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class GerenciamentoPrateleira extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	DefaultTableModel modelo;
	JTable tablePrateleira;
	JPanel panelTabela;

	public GerenciamentoPrateleira() {
		setClosable(true);
		setBounds(100, 100, 599, 418);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0, 102, 255));
		panel_1.setBounds(0, 0, 583, 66);
		panel.add(panel_1);

		JLabel lblGerenciamentoDePrateleiras = new JLabel("Gerenciamento de Prateleiras");
		lblGerenciamentoDePrateleiras.setForeground(Color.WHITE);
		lblGerenciamentoDePrateleiras.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblGerenciamentoDePrateleiras.setBounds(24, 21, 349, 23);
		panel_1.add(lblGerenciamentoDePrateleiras);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 322, 563, 55);
		panel.add(panel_2);

		JButton btnAdicionarPrateleira = new JButton("Adicionar Prateleira");
		btnAdicionarPrateleira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastrarPrateleira cadastrarPrateleira = new CadastrarPrateleira("Cadastrar Prateleira","Cadastrar",0);

			}
		});

		btnAdicionarPrateleira.setIcon(
				new ImageIcon(GerenciamentoPrateleira.class.getResource("/br/sodadrink/sp/imagens/ic_add_18pt.png")));
		btnAdicionarPrateleira.setBounds(10, 11, 159, 30);
		panel_2.add(btnAdicionarPrateleira);

		JButton button_1 = new JButton("Editar");
		button_1.setIcon(new ImageIcon(GerenciamentoPrateleira.class.getResource("/br/sodadrink/sp/imagens/ic_mode_edit_18pt.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int linha = tablePrateleira.getSelectedRow();
				int coluna = 0;

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione uma Prateleira!");

				} else {

					String id_prateleira = tablePrateleira.getModel().getValueAt(linha, coluna).toString();
					
					CadastrarPrateleira cadastrarPrateleira = new CadastrarPrateleira("Atualizar Prateleira", "Atualizar",
							Integer.parseInt(id_prateleira));

				}
				
			}
		});
		
		button_1.setBounds(185, 11, 100, 30);
		panel_2.add(button_1);

		JButton button_2 = new JButton("Excluir");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int linha = tablePrateleira.getSelectedRow();
				int coluna = 0;
				int colunaNome = 1;
				
				if(linha == -1){
					
					JOptionPane.showMessageDialog(null, "Selecione uma Prateleira!");
					
				}else{
					
					String nome_prateleira = tablePrateleira.getModel().getValueAt(linha, colunaNome).toString();
					
					int continua = JOptionPane.showConfirmDialog(null, "Deseja Excluir a prateleira "+nome_prateleira,"Atenção!",JOptionPane.YES_NO_OPTION);
					
					if(continua == 0){
						
						String id_prateleira = tablePrateleira.getModel().getValueAt(linha, coluna).toString();
						int id_prateleira_int = Integer.parseInt(id_prateleira);
						
						boolean resultado = EstantePrateleiraDAO.excluirEstantePrateleiraDAO(id_prateleira_int);

						boolean resultado2 = PrateleiraDAO.excluirPrateleiraDAO(id_prateleira_int);
						
						boolean resultado3 = EstoqueDAO.excluirEstoquePelaPrateleiraDAO(id_prateleira_int);
						
						if(resultado == true && resultado2 == true && resultado3 == true){
							
							JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
							recarregarTabela();
							
						}else{
							
							JOptionPane.showMessageDialog(null, "Erro ao Excluir, Essa prateleira está sendo utilizada!");
							
						}
						
					}				
					
				}	
				
			}
		});
		button_2.setIcon(new ImageIcon(GerenciamentoPrateleira.class.getResource("/br/sodadrink/sp/imagens/ic_delete_forever_18pt.png")));
		button_2.setBounds(298, 11, 100, 30);
		panel_2.add(button_2);

		JButton button_3 = new JButton("Atualizar Tabela");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				recarregarTabela();
				
			}
		});
		button_3.setIcon(new ImageIcon(GerenciamentoPrateleira.class.getResource("/br/sodadrink/sp/imagens/ic_autorenew_18pt.png")));
		button_3.setBounds(408, 11, 145, 30);
		panel_2.add(button_3);

		panelTabela = new JPanel();
		panelTabela.setBounds(10, 77, 563, 235);
		panel.add(panelTabela);

		exibirPrateleiras();

		this.setVisible(true);

	}

	public void exibirPrateleiras() {

		modelo = new DefaultTableModel();
		tablePrateleira = new JTable();
		tablePrateleira.setModel(modelo);

		modelo.addColumn("ID");
		modelo.addColumn("NOME PRATELEIRA");
		modelo.addColumn("NOME ESTANTE");

		tablePrateleira.getColumnModel().getColumn(0).setPreferredWidth(80);
		tablePrateleira.getColumnModel().getColumn(1).setPreferredWidth(233);
		tablePrateleira.getColumnModel().getColumn(2).setPreferredWidth(233);
		//tablePrateleira.getColumnModel().getColumn(3).setPreferredWidth(233);
		
		tablePrateleira.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				
				tablePrateleira.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
		});

		listarItensTabela();

		panelTabela.setLayout(null);

		JScrollPane scrollPanel = new JScrollPane(tablePrateleira);
		scrollPanel.setBounds(0, 0, 563, 237);
		panelTabela.add(scrollPanel);

	}

	public void listarItensTabela() {

		List<EstantePrateleira> listEstantePrateleira = new ArrayList<>();

		listEstantePrateleira = EstantePrateleiraDAO.listarEstantePrateleiraDAO();

		for (int i = 0; listEstantePrateleira.size() > i; i++) {

			modelo.addRow(new Object[] { listEstantePrateleira.get(i).getId_estante_prateleira(),
					listEstantePrateleira.get(i).getNomePrateleira(), listEstantePrateleira.get(i).getNomeEstante() });

		}

	}

	public void recarregarTabela() {

		DefaultTableModel tm = (DefaultTableModel) tablePrateleira.getModel();

		while (tablePrateleira.getRowCount() > 0) {
			((DefaultTableModel) tablePrateleira.getModel()).removeRow(0);
		}

		listarItensTabela();

	}

}
