package br.sodadrink.sp.view;

import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.awt.BorderLayout;
import com.toedter.calendar.JDateChooser;
import br.sodadrink.sp.DAO.FormaPagamentoDAO;
import br.sodadrink.sp.DAO.PedidoDAO;
import br.sodadrink.sp.model.FormaPagamento;
import br.sodadrink.sp.model.ItemComboBox;
import br.sodadrink.sp.model.Venda;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VerificarPedido extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel panelPrincipal;
	private JTextField txtNomeFantasia;

	DefaultTableModel modelo;
	JTable tablePedido;
	JPanel panelTabela;

	JDateChooser dateChooserDe;
	JDateChooser dateChooserAte;

	JComboBox<ItemComboBox> cbOrdenar;

	public VerificarPedido() {

		setTitle("Acompanhamento de Pedidos");
		setClosable(true);
		setBounds(100, 100, 1002, 599);
		getContentPane().setLayout(new BorderLayout(0, 0));

		panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 998, 66);
		panelPrincipal.add(panelSuperior);

		JLabel lblTitulo = new JLabel("Acompanhamento de Pedidos");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTitulo.setBounds(24, 21, 297, 23);
		panelSuperior.add(lblTitulo);

		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setLayout(null);
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(10, 503, 966, 55);
		panelPrincipal.add(panelOpcoes);

		JButton btnDetalhesPedido = new JButton("Detalhes Pedido");
		btnDetalhesPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int coluna = 0;
				int linha = tablePedido.getSelectedRow();

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione um pedido!!");

				} else {

					String id_pedido = tablePedido.getModel().getValueAt(linha, coluna).toString();

					RelatorioVenda relatorioVendas = new RelatorioVenda(Integer.parseInt(id_pedido));

				}

			}
		});

		btnDetalhesPedido.setIcon(new ImageIcon(
				VerificarPedido.class.getResource("/br/sodadrink/sp/imagens/ic_chrome_reader_mode_18pt.png")));
		btnDetalhesPedido.setBounds(10, 11, 148, 30);
		panelOpcoes.add(btnDetalhesPedido);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int coluna = 0;
				int linha = tablePedido.getSelectedRow();

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione um pedido!");

				} else {

					String id_pedido = tablePedido.getModel().getValueAt(linha, coluna).toString();

					RelatorioPedido relatorioPedido = new RelatorioPedido(Integer.parseInt(id_pedido));

				}

			}
		});

		btnEditar.setIcon(
				new ImageIcon(VerificarPedido.class.getResource("/br/sodadrink/sp/imagens/ic_mode_edit_18pt.png")));
		btnEditar.setBounds(168, 11, 100, 30);
		panelOpcoes.add(btnEditar);

		JButton btnRecarre = new JButton("Atualizar Tabela");
		btnRecarre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				List<Venda> listVenda = new ArrayList<>();

				listVenda = PedidoDAO.listarPedidoDAO();

				recarregarTabela(listVenda);

			}
		});

		btnRecarre.setIcon(
				new ImageIcon(VerificarPedido.class.getResource("/br/sodadrink/sp/imagens/ic_autorenew_18pt.png")));
		btnRecarre.setBounds(278, 11, 155, 30);
		panelOpcoes.add(btnRecarre);

		panelTabela = new JPanel();
		panelTabela.setBounds(10, 192, 966, 300);
		TitledBorder titledBorderTabela = new TitledBorder("Lista de Pedidos");
		panelTabela.setBorder(titledBorderTabela);
		panelPrincipal.add(panelTabela);

		// Tabela de Usuarios

		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("ID");
		modelo.addColumn("RAZAO SOCIAL");
		modelo.addColumn("NOME FANTASIA");
		modelo.addColumn("STATUS");
		modelo.addColumn("PRODUTO COMPRADO");
		modelo.addColumn("QTD");
		modelo.addColumn("VALOR");
		panelTabela.setLayout(null);

		JPanel panelBusca = new JPanel();
		panelBusca.setLayout(null);
		panelBusca.setBounds(10, 77, 966, 104);
		TitledBorder titledBorderBusca = new TitledBorder("Buscar Por");
		panelBusca.setBorder(titledBorderBusca);
		panelPrincipal.add(panelBusca);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String data1 = ((JTextField) dateChooserDe.getDateEditor().getUiComponent()).getText().toString();
				String data2 = ((JTextField) dateChooserAte.getDateEditor().getUiComponent()).getText().toString();

				DateFormat formato = new SimpleDateFormat("dd/mm/yyyy");

				try {

					Date dataFormatada = formato.parse(data1);
					Date dataFormatada2 = formato.parse(data2);

					String novaData1 = new SimpleDateFormat("yyyy-mm-dd").format(dataFormatada);
					String novaData2 = new SimpleDateFormat("yyyy-mm-dd").format(dataFormatada2);

					List<Venda> listaVenda = new ArrayList<>();

					ItemComboBox comboboxFormaPagamento = (ItemComboBox) cbOrdenar.getSelectedItem();
					String nomeFormaPagamento = comboboxFormaPagamento.getDescricao();

					listaVenda = PedidoDAO.listarPedidoPorBuscaTodasDAO(txtNomeFantasia.getText().toString(),
							nomeFormaPagamento, novaData1, novaData2);

					recarregarTabela(listaVenda);

				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		});
		btnBuscar.setBounds(823, 48, 112, 30);
		panelBusca.add(btnBuscar);

		cbOrdenar = new JComboBox<ItemComboBox>();

		// AQUI
		List<FormaPagamento> listFormaPagamento = new ArrayList<>();
		listFormaPagamento = FormaPagamentoDAO.listarFormaPagamentoDAO();

		for (int i = 0; listFormaPagamento.size() > i; i++) {

			int idFormaPagamento = listFormaPagamento.get(i).getId_forma_pagamento();
			String nome = listFormaPagamento.get(i).getFormaPagamento();

			cbOrdenar.addItem(new ItemComboBox(idFormaPagamento, nome, i));

		}

		cbOrdenar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				List<Venda> listVenda = new ArrayList<>();

				ItemComboBox comboboxFormaPagamento = (ItemComboBox) cbOrdenar.getSelectedItem();
				String nomeFormaPagamento = comboboxFormaPagamento.getDescricao();

				listVenda = PedidoDAO.listarPedidoPorBuscaDAO("f.formaPagamento", nomeFormaPagamento);

				recarregarTabela(listVenda);

			}
		});

		cbOrdenar.setBounds(232, 48, 187, 31);
		panelBusca.add(cbOrdenar);

		JLabel lblNomeFantasia = new JLabel("Nome Fantasia");
		lblNomeFantasia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNomeFantasia.setBounds(35, 21, 153, 30);
		panelBusca.add(lblNomeFantasia);

		txtNomeFantasia = new JTextField();
		txtNomeFantasia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				List<Venda> listaVenda = new ArrayList<>();
				listaVenda = PedidoDAO.listarPedidoPorBuscaDAO("nomeFantasia", txtNomeFantasia.getText().toString());
				recarregarTabela(listaVenda);
			}
		});
		txtNomeFantasia.setToolTipText("");
		txtNomeFantasia.setText("");
		txtNomeFantasia.setColumns(10);
		txtNomeFantasia.setBounds(35, 48, 187, 31);
		panelBusca.add(txtNomeFantasia);

		JLabel lblOrdenar = new JLabel("Forma de pagamento");
		lblOrdenar.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblOrdenar.setBounds(232, 19, 153, 27);
		panelBusca.add(lblOrdenar);

		dateChooserDe = new JDateChooser();
		dateChooserDe.setBounds(429, 48, 187, 31);
		panelBusca.add(dateChooserDe);

		JLabel lblDe = new JLabel("Pedidos De:");
		lblDe.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDe.setBounds(429, 31, 97, 14);
		panelBusca.add(lblDe);

		JLabel lblAte = new JLabel("At\u00E9:");
		lblAte.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAte.setBounds(626, 31, 97, 14);
		panelBusca.add(lblAte);

		dateChooserAte = new JDateChooser();
		dateChooserAte.setBounds(626, 48, 187, 31);
		panelBusca.add(dateChooserAte);

		exibirTabelaVendas();

		this.setVisible(true);

	}

	public void exibirTabelaVendas() {

		// Tabela de Usuarios

		modelo = new DefaultTableModel();

		modelo.addColumn("ID");
		modelo.addColumn("NOME FANTASIA");
		modelo.addColumn("FORMA DE PAGAMENTO");
		modelo.addColumn("ID STATUS");
		modelo.addColumn("DATA PEDIDO");
		modelo.addColumn("DATA ENVIO");
		modelo.addColumn("VALOR");

		tablePedido = new JTable();
		tablePedido.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {

				tablePedido.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}
		});

		tablePedido.setModel(modelo);

		tablePedido.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablePedido.getColumnModel().getColumn(1).setPreferredWidth(200);
		tablePedido.getColumnModel().getColumn(2).setPreferredWidth(215);
		tablePedido.getColumnModel().getColumn(3).setPreferredWidth(170);
		tablePedido.getColumnModel().getColumn(4).setPreferredWidth(150);
		tablePedido.getColumnModel().getColumn(5).setPreferredWidth(140);
		tablePedido.getColumnModel().getColumn(6).setPreferredWidth(100);

		List<Venda> listVenda = new ArrayList<>();

		listVenda = PedidoDAO.listarPedidoDAO();

		exibirItensTabela(listVenda);

		panelTabela.setLayout(null);

		JScrollPane scrollPanel = new JScrollPane(tablePedido);
		scrollPanel.setBounds(10, 21, 946, 268);
		panelTabela.add(scrollPanel);

	}

	public void exibirItensTabela(List<Venda> listVenda) {

		for (int i = 0; listVenda.size() > i; i++) {

			Locale ptBr = new Locale("pt", "BR");
			NumberFormat formato = NumberFormat.getCurrencyInstance(ptBr);
			
			// *****************FORMATANDO AS DATAS*****************	
			DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
			
			if(listVenda.get(i).getDtEnvio() != null && !listVenda.get(i).getDtEnvio().isEmpty() && !listVenda.get(i).getDtEnvio().equals("")){
				
				try {

					Date dataPedidoFormatada = formatoData.parse(listVenda.get(i).getDtPedido());
					Date dataEnvioFormatada = formatoData.parse(listVenda.get(i).getDtEnvio());

					String novaDataPedido= new SimpleDateFormat("dd/MM/yyyy").format(dataPedidoFormatada);
					String novaDataEnvio = new SimpleDateFormat("dd/MM/yyyy").format(dataEnvioFormatada);

					modelo.addRow(new Object[] { listVenda.get(i).getId_pedido(), listVenda.get(i).getNomeCliente(),
							listVenda.get(i).getNomeFormaPagamento(), listVenda.get(i).getNomeStatus(),
							novaDataPedido, novaDataEnvio, formato.format(listVenda.get(i).getValorTotal()) });
					

				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}else{
				
				try {

					Date dataPedidoFormatada = formatoData.parse(listVenda.get(i).getDtPedido());

					String novaDataPedido= new SimpleDateFormat("dd/MM/yyyy").format(dataPedidoFormatada);

					modelo.addRow(new Object[] { listVenda.get(i).getId_pedido(), listVenda.get(i).getNomeCliente(),
							listVenda.get(i).getNomeFormaPagamento(), listVenda.get(i).getNomeStatus(),
							novaDataPedido, listVenda.get(i).getDtEnvio(), formato.format(listVenda.get(i).getValorTotal()) });
					

				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
			
		}
	}

	public void recarregarTabela(List<Venda> listVenda) {

		DefaultTableModel tm = (DefaultTableModel) tablePedido.getModel();

		while (tablePedido.getRowCount() > 0) {
			((DefaultTableModel) tablePedido.getModel()).removeRow(0);
		}

		exibirItensTabela(listVenda);

	}
}
