package br.sodadrink.sp.view;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import br.sodadrink.sp.DAO.HistoricoCompraDAO;
import br.sodadrink.sp.model.Compra;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class HistoricoCompra extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	DefaultTableModel modelo;
	JTable tableHistorico;
	JPanel panelTabela;

	public HistoricoCompra() {
		setClosable(true);
		setBounds(100, 100, 785, 447);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0, 102, 255));
		panel_1.setBounds(0, 0, 782, 66);
		panel.add(panel_1);

		JLabel lblHistoricoDeCompras = new JLabel("Hist\u00F3rico de Compras");
		lblHistoricoDeCompras.setForeground(Color.WHITE);
		lblHistoricoDeCompras.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblHistoricoDeCompras.setBounds(24, 21, 301, 23);
		panel_1.add(lblHistoricoDeCompras);

		panelTabela = new JPanel();
		panelTabela.setBounds(10, 77, 749, 330);
		panel.add(panelTabela);

		exibirTabela();

		this.setVisible(true);

	}

	public void exibirTabela() {

		modelo = new DefaultTableModel();
		tableHistorico = new JTable();
		tableHistorico.setModel(modelo);

		modelo.addColumn("ID");
		modelo.addColumn("FORNECEDOR");
		modelo.addColumn("PRODUTO");
		modelo.addColumn("USUARIO");
		modelo.addColumn("DATA PEDIDO");

		tableHistorico.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableHistorico.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableHistorico.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableHistorico.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableHistorico.getColumnModel().getColumn(4).setPreferredWidth(150);

		tableHistorico.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				
				tableHistorico.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
		});
		
		listarItensTabela(modelo);

		panelTabela.setLayout(null);

		JScrollPane scrollPanel = new JScrollPane(tableHistorico);
		scrollPanel.setBounds(10, 11, 729, 308);
		panelTabela.add(scrollPanel);

	}

	public static void listarItensTabela(DefaultTableModel modelo) {

		List<Compra> listCompra = new ArrayList<>();

		listCompra = HistoricoCompraDAO.listarHistoricoCompraDAO();

		for (int i = 0; listCompra.size() > i; i++) {

			modelo.addRow(new Object[] { listCompra.get(i).getId_pedido(), listCompra.get(i).getFornecedor(),
					listCompra.get(i).getProduto(), listCompra.get(i).getUsuario(),
					listCompra.get(i).getDtPedido() });

		}

	}

	public void recarregarTabela() {

		DefaultTableModel tm = (DefaultTableModel) tableHistorico.getModel();

		while (tableHistorico.getRowCount() > 0) {
			((DefaultTableModel) tableHistorico.getModel()).removeRow(0);
		}

		listarItensTabela(modelo);

	}
}
