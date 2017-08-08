package br.sodadrink.sp.view;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import br.sodadrink.sp.DAO.FormaPagamentoDAO;
import br.sodadrink.sp.DAO.ItensPedidoDAO;
import br.sodadrink.sp.DAO.ProdutoDAO;
import br.sodadrink.sp.DAO.VendaDAO;
import br.sodadrink.sp.model.FormaPagamento;
import br.sodadrink.sp.model.ItemComboBox;
import br.sodadrink.sp.model.ItensPedido;
import br.sodadrink.sp.model.Produto;
import br.sodadrink.sp.model.Venda;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.MouseEvent;

public class VerificarVenda extends JInternalFrame {
	private JPanel panelPrincipal;
	private JTextField txtNomeFantasia;

	DefaultTableModel modelo;
	JTable tableVenda;
	JPanel panelTabela;

	JDateChooser dateChooserDe;
	JDateChooser dateChooserAte;

	JComboBox<ItemComboBox> cbOrdenar;

	public VerificarVenda() {

		setTitle("Relatório de Vendas");
		setClosable(true);
		setBounds(100, 100, 1002, 599);
		getContentPane().setLayout(new BorderLayout(0, 0));

		panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 999, 66);
		panelPrincipal.add(panelSuperior);

		JLabel lblVendas = new JLabel("Relat\u00F3rio de Vendas");
		lblVendas.setForeground(Color.WHITE);
		lblVendas.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblVendas.setBounds(24, 21, 246, 23);
		panelSuperior.add(lblVendas);

		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setLayout(null);
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(10, 503, 966, 55);
		panelPrincipal.add(panelOpcoes);

		JButton btnDetalhesVenda = new JButton("Detalhes Venda");
		btnDetalhesVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int coluna = 0;
				int linha = tableVenda.getSelectedRow();

				if (linha == -1) {

					JOptionPane.showMessageDialog(null, "Selecione uma venda!");

				} else {

					String id_pedido = tableVenda.getModel().getValueAt(linha, coluna).toString();

					RelatorioVenda relatorioVendas = new RelatorioVenda(Integer.parseInt(id_pedido));

				}

			}
		});

		btnDetalhesVenda.setIcon(new ImageIcon(
				VerificarVenda.class.getResource("/br/sodadrink/sp/imagens/ic_chrome_reader_mode_18pt.png")));
		btnDetalhesVenda.setBounds(10, 11, 148, 30);
		panelOpcoes.add(btnDetalhesVenda);

		JButton btnGrafico = new JButton("Gr\u00E1fico de Venda Mensais");
		btnGrafico.setIcon(
				new ImageIcon(VerificarVenda.class.getResource("/br/sodadrink/sp/imagens/ic_insert_chart_18pt.png")));
		btnGrafico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DefaultCategoryDataset dataset = new DefaultCategoryDataset();

				List<Venda> listVendaJaneiro = new ArrayList<>();
				listVendaJaneiro = VendaDAO.listarVendaPorMes("2017-01");
				int janeiro = listVendaJaneiro.size();

				List<Venda> listVendaFevereiro = new ArrayList<>();
				listVendaFevereiro = VendaDAO.listarVendaPorMes("2017-02");
				int fevereiro = listVendaFevereiro.size();

				List<Venda> listVendaMarco = new ArrayList<>();
				listVendaMarco = VendaDAO.listarVendaPorMes("2017-03");
				int Marco = listVendaMarco.size();

				List<Venda> listVendaAbril = new ArrayList<>();
				listVendaAbril = VendaDAO.listarVendaPorMes("2017-04");
				int Abril = listVendaAbril.size();

				List<Venda> listVendaMaio = new ArrayList<>();
				listVendaMaio = VendaDAO.listarVendaPorMes("2017-05");
				int Maio = listVendaMaio.size();

				List<Venda> listVendaJunho = new ArrayList<>();
				listVendaJunho = VendaDAO.listarVendaPorMes("2017-06");
				int Junho = listVendaJunho.size();

				List<Venda> listVendaJulho = new ArrayList<>();
				listVendaJulho = VendaDAO.listarVendaPorMes("2017-07");
				int Julho = listVendaJulho.size();

				List<Venda> listVendaAgosto = new ArrayList<>();
				listVendaAgosto = VendaDAO.listarVendaPorMes("2017-08");
				int Agosto = listVendaAgosto.size();

				List<Venda> listVendaSetembro = new ArrayList<>();
				listVendaSetembro = VendaDAO.listarVendaPorMes("2017-09");
				int Setembro = listVendaSetembro.size();

				List<Venda> listVendaOutubro = new ArrayList<>();
				listVendaOutubro = VendaDAO.listarVendaPorMes("2017-10");
				int Outubro = listVendaOutubro.size();

				List<Venda> listVendaNovenbro = new ArrayList<>();
				listVendaNovenbro = VendaDAO.listarVendaPorMes("2017-11");
				int Novenbro = listVendaNovenbro.size();

				List<Venda> listVendaDezembro = new ArrayList<>();
				listVendaDezembro = VendaDAO.listarVendaPorMes("2017-12");
				int Dezembro = listVendaDezembro.size();

				dataset.setValue(janeiro, "Total", "Janeiro");
				dataset.setValue(fevereiro, "Total", "Fevereiro");
				dataset.setValue(Marco, "Total", "Março");
				dataset.setValue(Abril, "Total", "Abril");
				dataset.setValue(Maio, "Total", "Maio");
				dataset.setValue(Junho, "Total", "Junho");
				dataset.setValue(Julho, "Total", "Julho");
				dataset.setValue(Agosto, "Total", "Agosto");
				dataset.setValue(Setembro, "Total", "Setembro");
				dataset.setValue(Outubro, "Total", "Outubro");
				dataset.setValue(Novenbro, "Total", "Novembro");
				dataset.setValue(Dezembro, "Total", "Dezembro");

				JFreeChart chart = ChartFactory.createBarChart("Grafico de Vendas Mensais", "Mês", "Vendas Mensais",
						dataset, PlotOrientation.VERTICAL, false, true, false);
				CategoryPlot p = chart.getCategoryPlot();
				p.setRangeGridlinePaint(Color.BLACK);
				ChartFrame frame = new ChartFrame("Grafico de Vendas", chart);

				frame.setSize(1200, 500);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

			}
		});
		btnGrafico.setBounds(168, 11, 206, 30);
		panelOpcoes.add(btnGrafico);

		JButton btnGraficoDeVenda = new JButton("Gr\u00E1fico de Venda Produtos");
		btnGraficoDeVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DefaultPieDataset pieDataset = new DefaultPieDataset();

				List<Produto> listVendaProdutos = new ArrayList<>();
				listVendaProdutos = ProdutoDAO.listarProdutoDAO();
				
				List<ItensPedido> listVendaProdutosTotalVenda = new ArrayList<>();
			
				for (int i = 0; listVendaProdutos.size() > i; i++) {
					
					listVendaProdutosTotalVenda = ItensPedidoDAO.listarTotalVendasDoProdutoDAO(listVendaProdutos.get(i).getId_produto());
					
					if(listVendaProdutosTotalVenda.get(0).getQuantidadeTotalVenda() != 0){
						
						pieDataset.setValue(listVendaProdutos.get(i).getNome(), listVendaProdutosTotalVenda.get(0).getQuantidadeTotalVenda());

						
					}
					
				}

				JFreeChart chart = ChartFactory.createPieChart("Grafico de Vendas de Produtos", pieDataset, true, true,
						true);

				PiePlot p = (PiePlot) chart.getPlot();

				ChartFrame chartFrame = new ChartFrame("Grafico de Vendas", chart);

				chartFrame.setSize(600, 500);
				chartFrame.setLocationRelativeTo(null);
				chartFrame.setVisible(true);

			}
		});
		btnGraficoDeVenda.setIcon(
				new ImageIcon(VerificarVenda.class.getResource("/br/sodadrink/sp/imagens/ic_pie_chart_18pt.png")));
		btnGraficoDeVenda.setBounds(384, 11, 206, 30);
		panelOpcoes.add(btnGraficoDeVenda);

		JPanel panelBusca = new JPanel();
		panelBusca.setLayout(null);
		panelBusca.setBounds(10, 77, 966, 104);
		TitledBorder titledBorderBusca = new TitledBorder("Buscar Por:");
		panelBusca.setBorder(titledBorderBusca);
		panelPrincipal.add(panelBusca);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String data1 = ((JTextField) dateChooserDe.getDateEditor().getUiComponent()).getText().toString();
				String data2 = ((JTextField) dateChooserAte.getDateEditor().getUiComponent()).getText().toString();

				if(data1.isEmpty() || data2.isEmpty()){
					
					
					
				}else{
					
					DateFormat formato = new SimpleDateFormat("dd/mm/yyyy");

					try {

						Date dataFormatada = formato.parse(data1);
						Date dataFormatada2 = formato.parse(data2);

						String novaData1 = new SimpleDateFormat("yyyy-mm-dd").format(dataFormatada);
						String novaData2 = new SimpleDateFormat("yyyy-mm-dd").format(dataFormatada2);

						List<Venda> listaVenda = new ArrayList<>();

						ItemComboBox comboboxFormaPagamento = (ItemComboBox) cbOrdenar.getSelectedItem();
						String nomeFormaPagamento = comboboxFormaPagamento.getDescricao();

						listaVenda = VendaDAO.listarVendaPorBuscaTodasDAO(txtNomeFantasia.getText().toString(),
								nomeFormaPagamento, novaData1, novaData2);

						recarregarTabela(listaVenda);

					} catch (ParseException e) {
						e.printStackTrace();
					}
					
				}			

			}
		});
		btnBuscar.setBounds(825, 49, 112, 30);
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

				listVenda = VendaDAO.listarVendaPorBuscaDAO("f.formaPagamento", nomeFormaPagamento);

				recarregarTabela(listVenda);

			}
		});

		cbOrdenar.setBounds(235, 49, 187, 31);
		panelBusca.add(cbOrdenar);

		JLabel lblNomeFantasia = new JLabel("Nome Fantasia");
		lblNomeFantasia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNomeFantasia.setBounds(36, 20, 153, 30);
		panelBusca.add(lblNomeFantasia);

		txtNomeFantasia = new JTextField();
		txtNomeFantasia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				List<Venda> listaVenda = new ArrayList<>();
				listaVenda = VendaDAO.listarVendaPorBuscaDAO("nomeFantasia", txtNomeFantasia.getText().toString());
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
		lblOrdenar.setBounds(235, 27, 153, 25);
		panelBusca.add(lblOrdenar);

		dateChooserDe = new JDateChooser();
		dateChooserDe.setBounds(431, 49, 187, 31);
		panelBusca.add(dateChooserDe);

		JLabel lblVendaEntregueDe = new JLabel("Vendas entregues De:");
		lblVendaEntregueDe.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblVendaEntregueDe.setBounds(433, 29, 146, 18);
		panelBusca.add(lblVendaEntregueDe);

		JLabel label_4 = new JLabel("At\u00E9:");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_4.setBounds(628, 31, 97, 14);
		panelBusca.add(label_4);

		dateChooserAte = new JDateChooser();
		dateChooserAte.setBounds(628, 48, 187, 31);
		panelBusca.add(dateChooserAte);

		panelTabela = new JPanel();
		panelTabela.setBounds(10, 192, 966, 300);
		TitledBorder titledBorderUsuarios = new TitledBorder("Relatorio de Vendas");
		panelTabela.setBorder(titledBorderUsuarios);
		panelPrincipal.add(panelTabela);

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
		modelo.addColumn("DATA ENTREGUE");
		modelo.addColumn("VALOR");

		tableVenda = new JTable();
		tableVenda.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {

				tableVenda.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}
		});

		tableVenda.setModel(modelo);

		tableVenda.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableVenda.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableVenda.getColumnModel().getColumn(2).setPreferredWidth(215);
		tableVenda.getColumnModel().getColumn(3).setPreferredWidth(170);
		tableVenda.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableVenda.getColumnModel().getColumn(5).setPreferredWidth(140);
		tableVenda.getColumnModel().getColumn(6).setPreferredWidth(100);

		List<Venda> listVenda = new ArrayList<>();

		listVenda = VendaDAO.listarVendaDAO();

		exibirItensTabela(listVenda);

		panelTabela.setLayout(null);

		JScrollPane scrollPanel = new JScrollPane(tableVenda);
		scrollPanel.setBounds(10, 21, 946, 268);
		panelTabela.add(scrollPanel);

	}

	public void exibirItensTabela(List<Venda> listVenda) {

		for (int i = 0; listVenda.size() > i; i++) {

			Locale ptBr = new Locale("pt", "BR");
			NumberFormat formato = NumberFormat.getCurrencyInstance(ptBr);

			// *****************FORMATANDO AS DATAS*****************
			DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
			
			if (listVenda.get(i).getDtEntrega().isEmpty()) {

				try {

					Date dataPedidoFormatada = formatoData.parse(listVenda.get(i).getDtPedido());
					Date dataEntregaFormatada = formatoData.parse(listVenda.get(i).getDtEntrega());

					String novaDataPedido = new SimpleDateFormat("dd/MM/yyyy").format(dataPedidoFormatada);
					String novaDataEntrega = new SimpleDateFormat("dd/MM/yyyy").format(dataEntregaFormatada);

					modelo.addRow(new Object[] { listVenda.get(i).getId_pedido(), listVenda.get(i).getNomeCliente(),
							listVenda.get(i).getNomeFormaPagamento(), listVenda.get(i).getNomeStatus(), 
							novaDataPedido,listVenda.get(i).getDtEntrega(), formato.format(listVenda.get(i).getValorTotal()) });

				} catch (ParseException e) {
					e.printStackTrace();
				}

			}else{
				
				try {

					Date dataPedidoFormatada = formatoData.parse(listVenda.get(i).getDtPedido());
					Date dataEntregaFormatada = formatoData.parse(listVenda.get(i).getDtEntrega());

					String novaDataPedido = new SimpleDateFormat("dd/MM/yyyy").format(dataPedidoFormatada);
					String novaDataEntrega = new SimpleDateFormat("dd/MM/yyyy").format(dataEntregaFormatada);

					modelo.addRow(new Object[] { listVenda.get(i).getId_pedido(), listVenda.get(i).getNomeCliente(),
							listVenda.get(i).getNomeFormaPagamento(), listVenda.get(i).getNomeStatus(), 
							novaDataPedido,novaDataEntrega, formato.format(listVenda.get(i).getValorTotal()) });

				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}

		}
	}

	public void recarregarTabela(List<Venda> listVenda) {

		DefaultTableModel tm = (DefaultTableModel) tableVenda.getModel();

		while (tableVenda.getRowCount() > 0) {
			((DefaultTableModel) tableVenda.getModel()).removeRow(0);
		}

		exibirItensTabela(listVenda);

	}
}
