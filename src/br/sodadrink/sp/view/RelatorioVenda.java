package br.sodadrink.sp.view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import br.sodadrink.sp.DAO.ClienteJuridicoDAO;
import br.sodadrink.sp.DAO.VendaDAO;
import br.sodadrink.sp.model.ClienteJuridico;
import br.sodadrink.sp.model.Venda;
import javax.swing.border.TitledBorder;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class RelatorioVenda extends JDialog {
	
	private static final long serialVersionUID = 1L;

	private final JPanel panelPrincipal = new JPanel();

	JLabel lblIdPedido;
	JLabel lblFormaDePagamento;
	JLabel lblDataDeEntrega;
	JLabel lblStatus;
	JLabel lblDataDeEnvio;
	JLabel lblDataDePedido;
	JLabel lblValor;

	JLabel lblRazaoSocial, lblNomeFantasia, lblInscricaoEstadual, lblCnpj, lblEmail, lblTelefone, lblCep, lblCidade,
			lblBairro, lblLogradouro, lblNumero;

	public RelatorioVenda(int id_pedido) {

		setBounds(100, 100, 643, 637);
		getContentPane().setLayout(new BorderLayout());
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 641, 66);
		panelPrincipal.add(panelSuperior);

		JLabel lblTitulo = new JLabel("Relat\u00F3rio de Venda");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTitulo.setBounds(24, 21, 246, 23);
		panelSuperior.add(lblTitulo);

		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setLayout(null);
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(10, 538, 607, 50);
		panelPrincipal.add(panelOpcoes);

		JButton btnImprimir = new JButton("Gerar PDF");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			//****************************** PDF COM ITEXT *******************************************************
				Document document = new Document();
				
				try {
					
					PdfWriter.getInstance(document, new FileOutputStream("relatorio.pdf"));
					
					document.open();				
					document.setPageSize(PageSize.A4);
					
					Paragraph paragraph = new Paragraph("Relatorio de Venda",FontFactory.getFont(FontFactory.TIMES_BOLD,25,BaseColor.BLUE));
					paragraph.setAlignment(Element.ALIGN_CENTER);				
					document.add(paragraph);
					
					Paragraph paragraph3 = new Paragraph("SodaDrink",FontFactory.getFont(FontFactory.TIMES_BOLD,18,BaseColor.ORANGE));
					paragraph3.setAlignment(Element.ALIGN_CENTER);				
					document.add(paragraph3);	
					
					document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
					
					document.add(new Paragraph(" "));
					document.add(new Paragraph(" "));
					
					PdfPCell celulaVazia = new PdfPCell(new Paragraph(" "));
					celulaVazia.setBorderColor(BaseColor.WHITE);
					
					//Cria tabela
					PdfPTable table = new PdfPTable(2);
					
					//Cria os itens
					PdfPCell cell = new PdfPCell(new Paragraph("Detalhes do Cliente"));							
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBorderColor(BaseColor.WHITE);
					cell.setColspan(4);
					table.addCell(cell);
										
					table.addCell(celulaVazia);
					table.addCell(celulaVazia);
					
					table.addCell("Nome Fantasia");
					table.addCell(lblNomeFantasia.getText());
					table.addCell("Razao Social");
					table.addCell(lblRazaoSocial.getText());
					table.addCell("CNPJ");
					table.addCell(lblCnpj.getText());
					table.addCell("E-mail");
					table.addCell(lblEmail.getText());
					table.addCell("Telefone");
					table.addCell(lblTelefone.getText());
					table.addCell("CEP");
					table.addCell(lblCep.getText());
					table.addCell("Cidade");
					table.addCell(lblCidade.getText());
					table.addCell("Bairro");
					table.addCell(lblBairro.getText());
					table.addCell("Logradouro");
					table.addCell(lblLogradouro.getText());
					table.addCell("Numero");
					table.addCell(lblNumero.getText());
					
					document.add(table);
					
					document.add(new Paragraph(" "));
					
					//Cria tabela
					PdfPTable table2 = new PdfPTable(2);
					
					table2.addCell(celulaVazia);
					table2.addCell(celulaVazia);
					
					//Cria os itens
					PdfPCell cell2 = new PdfPCell(new Paragraph("Detalhes da Venda"));							
					cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell2.setBorderColor(BaseColor.WHITE);
					cell2.setColspan(4);
					table2.addCell(cell2);
					
					table2.addCell(celulaVazia);
					table2.addCell(celulaVazia);
					
					table2.addCell("Forma de Pagamento");
					table2.addCell(lblFormaDePagamento.getText());
					table2.addCell("Status do Pedido");
					table2.addCell(lblStatus.getText());
					table2.addCell("Data do Pedido");
					table2.addCell(lblDataDePedido.getText());
					table2.addCell("Data de Envio");
					table2.addCell(lblDataDeEnvio.getText());
					table2.addCell("Data de Entrega");
					table2.addCell(lblDataDeEntrega.getText());
					table2.addCell("Valor");
					table2.addCell(lblValor.getText());
			
					document.add(table2);
					
					document.add(new Paragraph(" "));
					document.add(new Paragraph(" "));
					
					Paragraph paragraph2 = new Paragraph(new Date().toString());				
					paragraph2.setAlignment(Element.ALIGN_RIGHT);				
					document.add(paragraph2);
					
					
				} catch (FileNotFoundException e) {					
					e.printStackTrace();			
				} catch (DocumentException e) {					
					e.printStackTrace();		
				}finally{
					
					document.close();
					
				}
				
				try {
					Desktop.getDesktop().open(new File("relatorio.pdf"));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		
		btnImprimir.setIcon(new ImageIcon(RelatorioVenda.class.getResource("/br/sodadrink/sp/imagens/Save16.png")));
		btnImprimir.setBounds(10, 11, 148, 30);
		panelOpcoes.add(btnImprimir);

		JPanel panelDadosVenda = new JPanel();
		panelDadosVenda.setBounds(10, 347, 607, 180);
		TitledBorder titledBorder = new TitledBorder("Dados da Venda");
		panelDadosVenda.setBorder(titledBorder);
		panelPrincipal.add(panelDadosVenda);
		panelDadosVenda.setLayout(null);

		lblIdPedido = new JLabel("");
		lblIdPedido.setBounds(30, 44, 166, 26);
		panelDadosVenda.add(lblIdPedido);

		lblFormaDePagamento = new JLabel("");
		lblFormaDePagamento.setBounds(30, 128, 149, 26);
		panelDadosVenda.add(lblFormaDePagamento);

		lblStatus = new JLabel("");
		lblStatus.setBounds(236, 44, 149, 26);
		panelDadosVenda.add(lblStatus);

		lblDataDeEnvio = new JLabel("");
		lblDataDeEnvio.setBounds(331, 128, 113, 26);
		panelDadosVenda.add(lblDataDeEnvio);

		lblDataDeEntrega = new JLabel("");
		lblDataDeEntrega.setBounds(464, 128, 133, 26);
		panelDadosVenda.add(lblDataDeEntrega);

		lblDataDePedido = new JLabel("");
		lblDataDePedido.setBounds(197, 128, 113, 26);
		panelDadosVenda.add(lblDataDePedido);

		lblValor = new JLabel("");
		lblValor.setBounds(464, 44, 113, 26);
		panelDadosVenda.add(lblValor);

		JLabel lblinfonome = new JLabel("N\u00FAmero do Pedido");
		lblinfonome.setBounds(30, 25, 113, 20);
		panelDadosVenda.add(lblinfonome);

		JLabel lblinfoFormaDePagamento_1 = new JLabel("Forma de Pagamento");
		lblinfoFormaDePagamento_1.setBounds(30, 109, 132, 20);
		panelDadosVenda.add(lblinfoFormaDePagamento_1);

		JLabel lblinfoValorDaVenda = new JLabel("Valor Total");
		lblinfoValorDaVenda.setBounds(464, 25, 113, 20);
		panelDadosVenda.add(lblinfoValorDaVenda);

		JLabel lblinfoStatusDaVenda = new JLabel("Status do Pedido");
		lblinfoStatusDaVenda.setBounds(236, 25, 113, 20);
		panelDadosVenda.add(lblinfoStatusDaVenda);

		JLabel lblinfodataenvio = new JLabel("Data de Envio");
		lblinfodataenvio.setBounds(331, 109, 95, 20);
		panelDadosVenda.add(lblinfodataenvio);

		JLabel lblinfoDataDeEntrega_1 = new JLabel("Data de Entrega");
		lblinfoDataDeEntrega_1.setBounds(462, 109, 95, 20);
		panelDadosVenda.add(lblinfoDataDeEntrega_1);

		JLabel lblinfoDataDoPedido = new JLabel("Data do Pedido");
		lblinfoDataDoPedido.setBounds(197, 109, 95, 20);
		panelDadosVenda.add(lblinfoDataDoPedido);

		JPanel panelDadosCliente = new JPanel();
		panelDadosCliente.setBounds(10, 77, 607, 259);

		TitledBorder titledBorderCliente = new TitledBorder("Dados Cliente");
		panelDadosCliente.setBorder(titledBorderCliente);
		panelPrincipal.add(panelDadosCliente);
		panelDadosCliente.setLayout(null);

		lblRazaoSocial = new JLabel("");
		lblRazaoSocial.setBounds(39, 46, 154, 28);
		panelDadosCliente.add(lblRazaoSocial);

		lblNomeFantasia = new JLabel();
		lblNomeFantasia.setBounds(232, 46, 154, 28);
		panelDadosCliente.add(lblNomeFantasia);

		lblInscricaoEstadual = new JLabel("");
		lblInscricaoEstadual.setBounds(430, 46, 154, 28);
		panelDadosCliente.add(lblInscricaoEstadual);

		lblCnpj = new JLabel("");
		lblCnpj.setBounds(39, 104, 154, 28);
		panelDadosCliente.add(lblCnpj);

		lblEmail = new JLabel("");
		lblEmail.setBounds(232, 104, 154, 28);
		panelDadosCliente.add(lblEmail);

		lblTelefone = new JLabel("");
		lblTelefone.setBounds(430, 104, 154, 28);
		panelDadosCliente.add(lblTelefone);

		lblCep = new JLabel("");
		lblCep.setBounds(39, 158, 154, 28);
		panelDadosCliente.add(lblCep);

		lblCidade = new JLabel("");
		lblCidade.setBounds(232, 158, 154, 28);
		panelDadosCliente.add(lblCidade);

		lblBairro = new JLabel("");
		lblBairro.setBounds(430, 158, 154, 28);
		panelDadosCliente.add(lblBairro);

		lblLogradouro = new JLabel("");
		lblLogradouro.setBounds(39, 207, 154, 28);
		panelDadosCliente.add(lblLogradouro);

		lblNumero = new JLabel("");
		lblNumero.setBounds(232, 207, 154, 28);
		panelDadosCliente.add(lblNumero);

		JLabel lblinfoRazaosocial = new JLabel("Raz\u00E3oSocial");
		lblinfoRazaosocial.setBounds(39, 31, 95, 20);
		panelDadosCliente.add(lblinfoRazaosocial);

		JLabel lblinfonomefantasia = new JLabel("Nome Fantasia");
		lblinfonomefantasia.setBounds(231, 31, 95, 20);
		panelDadosCliente.add(lblinfonomefantasia);

		JLabel lblinfoInscrioEstadual = new JLabel("Inscri\u00E7\u00E3o Estadual");
		lblinfoInscrioEstadual.setBounds(430, 31, 124, 20);
		panelDadosCliente.add(lblinfoInscrioEstadual);

		JLabel lblinfoCnpj = new JLabel("CNPJ");
		lblinfoCnpj.setBounds(39, 85, 95, 20);
		panelDadosCliente.add(lblinfoCnpj);

		JLabel lblinfoEmail_1 = new JLabel("E-mail");
		lblinfoEmail_1.setBounds(232, 88, 95, 20);
		panelDadosCliente.add(lblinfoEmail_1);

		JLabel lblinfoTelefone_1 = new JLabel("Telefone");
		lblinfoTelefone_1.setBounds(430, 88, 95, 20);
		panelDadosCliente.add(lblinfoTelefone_1);

		JLabel lblinfoBairro_1 = new JLabel("Bairro");
		lblinfoBairro_1.setBounds(430, 143, 95, 20);
		panelDadosCliente.add(lblinfoBairro_1);

		JLabel lblinfoCidade_1 = new JLabel("Cidade");
		lblinfoCidade_1.setBounds(232, 143, 95, 20);
		panelDadosCliente.add(lblinfoCidade_1);

		JLabel lblinfoCep_1 = new JLabel("CEP");
		lblinfoCep_1.setBounds(39, 143, 95, 20);
		panelDadosCliente.add(lblinfoCep_1);

		JLabel lblinfoLogradouro_1 = new JLabel("Logradouro");
		lblinfoLogradouro_1.setBounds(39, 189, 95, 20);
		panelDadosCliente.add(lblinfoLogradouro_1);

		JLabel lblinfoN = new JLabel("N\u00B0");
		lblinfoN.setBounds(231, 192, 95, 20);
		panelDadosCliente.add(lblinfoN);
		
		preencherCampos(id_pedido);

		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	public void preencherCampos(int id_pedido) {

		List<Venda> listVenda = new ArrayList<>();
		listVenda = VendaDAO.listarVendaPorId(id_pedido);

		int idpedido = listVenda.get(0).getId_pedido();
		int idcliente = listVenda.get(0).getId_cliente();
		int idpagamento = listVenda.get(0).getId_forma_pagamento();
		int idstatus = listVenda.get(0).getId_status();
		String dtenvio = listVenda.get(0).getDtEnvio();
		String dtentrega = listVenda.get(0).getDtEntrega();
		String dtpedido = listVenda.get(0).getDtPedido();
		float valor = listVenda.get(0).getValorTotal();
		
		String nomeStatus = listVenda.get(0).getNomeStatus();
		String nomeFormaPagamento = listVenda.get(0).getNomeFormaPagamento();

		lblIdPedido.setText("" + idpedido);
		lblFormaDePagamento.setText("" + nomeFormaPagamento);
		lblStatus.setText("" + nomeStatus);
		lblDataDeEnvio.setText(dtenvio);
		lblDataDeEntrega.setText(dtentrega);
		lblDataDePedido.setText(dtpedido);
		
		Locale ptBr = new Locale("pt", "BR");
		NumberFormat formato = NumberFormat.getCurrencyInstance(ptBr);
		
		lblValor.setText("" + formato.format(valor));
		
		List<ClienteJuridico> ListCliente = new ArrayList<>();
		ListCliente = ClienteJuridicoDAO.listarClientesPorIdDAO(idcliente);
		
		DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
		
		// *****************FORMATA A DATA ENTREGA*****************
		
				if (dtentrega == "" || dtentrega == null || dtentrega.isEmpty())  {	
					
					lblDataDeEntrega.setText("Aguardando Entrega!");

				}else{
					
					try {

						Date dataEntregaFormatada = formatoData.parse(dtentrega);

						String novaDataEntrega = new SimpleDateFormat("dd/MM/yyyy").format(dataEntregaFormatada);

						lblDataDeEntrega.setText(novaDataEntrega);

					} catch (ParseException e) {
						e.printStackTrace();
					}
					
				}

		// *****************FORMATA A DATA ENVIO*****************
		
		if (dtenvio == "" || dtenvio == null || dtenvio.isEmpty())  {
			
			lblDataDeEnvio.setText("Aguardando data de Envio!");

		}else{
			
			try {

				Date dataEnvioFormatada = formatoData.parse(dtenvio);

				String novaDataEnvio = new SimpleDateFormat("dd/MM/yyyy").format(dataEnvioFormatada);

				lblDataDeEnvio.setText(novaDataEnvio);

			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}

		// ***************FORMATA A DATA PEDIDO*****************

		if (dtpedido == "" || dtpedido == null || dtpedido.isEmpty())  {	

		}else{

			try {

				Date dataPedidoFormatada = formatoData.parse(dtpedido);

				String novaDataPedido = new SimpleDateFormat("dd/MM/yyyy").format(dataPedidoFormatada);

				lblDataDePedido.setText(novaDataPedido);

			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		
		lblRazaoSocial.setText(ListCliente.get(0).getRazaoSocial());
		lblNomeFantasia.setText(ListCliente.get(0).getNomeFantasia());
		lblInscricaoEstadual.setText(ListCliente.get(0).getInscricaoEstadual());
		lblCnpj.setText(ListCliente.get(0).getCnpj());
		lblEmail.setText(ListCliente.get(0).getEmail());
		lblTelefone.setText(ListCliente.get(0).getTelefone());
		lblCep.setText(ListCliente.get(0).getCep());
		lblCidade.setText(ListCliente.get(0).getCidade());
		lblBairro.setText(ListCliente.get(0).getBairro());
		lblLogradouro.setText(ListCliente.get(0).getLogradouro());
		lblNumero.setText(""+ListCliente.get(0).getNumero());
		
	}
}
