package br.sodadrink.sp.view;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import br.sodadrink.sp.DAO.ClienteJuridicoDAO;
import br.sodadrink.sp.model.ClienteJuridico;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerificarCliente extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtRazaoSocial;
	private JTextField txtNomeFantasia;
	private JTextField txtCPNJ;

	JTable tableClientes;
	JPanel panelTabela;
	
	DefaultTableModel modelo;

	public VerificarCliente() {
		setClosable(true);
		setTitle("Verificar Clientes");
		setBounds(100, 100, 816, 548);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 800, 66);
		panelPrincipal.add(panelSuperior);

		JLabel lblTitulo = new JLabel("Clientes Cadastrados");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblTitulo.setBounds(24, 21, 313, 23);
		panelSuperior.add(lblTitulo);

		JPanel panelBusca = new JPanel();
		panelBusca.setLayout(null);
		panelBusca.setBounds(10, 77, 780, 96);
		TitledBorder titledBorderBusca = new TitledBorder("Buscar Por:");
		panelBusca.setBorder(titledBorderBusca);
		panelPrincipal.add(panelBusca);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String razao = txtRazaoSocial.getText().toString();
				String nomeFantasia = txtNomeFantasia.getText().toString();
				String cnpj = txtCPNJ.getText().toString();
				
				List<ClienteJuridico> listClientesJuridico = new ArrayList<>();
				listClientesJuridico = ClienteJuridicoDAO.listarClientesPorTodosDadosDAO("razaoSocial", razao, "nomeFantasia", nomeFantasia, "cnpj", cnpj);
						
				recarregarTabela(listClientesJuridico);
				
			}
		});
		
		btnBuscar.setBounds(638, 46, 103, 30);
		panelBusca.add(btnBuscar);

		JLabel lblNomeFantasia = new JLabel("Nome Fantasia");
		lblNomeFantasia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNomeFantasia.setBounds(228, 18, 109, 30);
		panelBusca.add(lblNomeFantasia);

		txtRazaoSocial = new JTextField();
		txtRazaoSocial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				List<ClienteJuridico> listClientesJuridico = new ArrayList<>();
				listClientesJuridico = ClienteJuridicoDAO.listarClientesPorDadosDAO("razaoSocial", txtRazaoSocial.getText().toString());
						
				recarregarTabela(listClientesJuridico);
			}
		});
		txtRazaoSocial.setToolTipText("");
		txtRazaoSocial.setText("");
		txtRazaoSocial.setColumns(10);
		txtRazaoSocial.setBounds(39, 44, 179, 31);
		panelBusca.add(txtRazaoSocial);

		JLabel lblRazaoSocial = new JLabel("Raz\u00E3o Social");
		lblRazaoSocial.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblRazaoSocial.setBounds(39, 26, 89, 14);
		panelBusca.add(lblRazaoSocial);

		txtNomeFantasia = new JTextField();
		txtNomeFantasia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				List<ClienteJuridico> listClientesJuridico = new ArrayList<>();
				listClientesJuridico = ClienteJuridicoDAO.listarClientesPorDadosDAO("nomeFantasia", txtNomeFantasia.getText().toString());
						
				recarregarTabela(listClientesJuridico);
				
			}
		});
		
		txtNomeFantasia.setToolTipText("");
		txtNomeFantasia.setText("");
		txtNomeFantasia.setColumns(10);
		txtNomeFantasia.setBounds(228, 45, 179, 31);
		panelBusca.add(txtNomeFantasia);

		JLabel lblCPNJ = new JLabel("CPNJ");
		lblCPNJ.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCPNJ.setBounds(417, 18, 89, 30);
		panelBusca.add(lblCPNJ);

		txtCPNJ = new JTextField();
		txtCPNJ.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				List<ClienteJuridico> listClientesJuridico = new ArrayList<>();
				listClientesJuridico = ClienteJuridicoDAO.listarClientesPorDadosDAO("cnpj", txtCPNJ.getText().toString());
						
				recarregarTabela(listClientesJuridico);
				
			}
		});
		txtCPNJ.setToolTipText("");
		txtCPNJ.setText("");
		txtCPNJ.setColumns(10);
		txtCPNJ.setBounds(417, 45, 179, 31);
		panelBusca.add(txtCPNJ);

		panelTabela = new JPanel();
		panelTabela.setBounds(10, 184, 780, 321);
		TitledBorder titledBorderTabela = new TitledBorder("Lista de Clientes:");
		panelTabela.setBorder(titledBorderTabela);
		panelPrincipal.add(panelTabela);

		// Tabela de Clientes
		exibirTabela();

		this.setVisible(true);

	}
	
	public void exibirTabela(){
		
		modelo = new DefaultTableModel();

		modelo.addColumn("ID");
		modelo.addColumn("RAZAO SOCIAL");
		modelo.addColumn("NOME FANTASIA");
		modelo.addColumn("E-MAIL");
		modelo.addColumn("TELEFONE");
		modelo.addColumn("CPNJ");

		tableClientes = new JTable();
		tableClientes.setModel(modelo);

		tableClientes.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableClientes.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableClientes.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableClientes.getColumnModel().getColumn(3).setPreferredWidth(140);
		tableClientes.getColumnModel().getColumn(4).setPreferredWidth(130);
		tableClientes.getColumnModel().getColumn(5).setPreferredWidth(130);
		
		tableClientes.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				
				tableClientes.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
		});
		
		List<ClienteJuridico> listClientesJuridico = new ArrayList<>();

		listClientesJuridico = ClienteJuridicoDAO.listarClientesDAO();
		
		exibirItensTabela(listClientesJuridico);

		panelTabela.setLayout(null);

		JScrollPane scrollPanel = new JScrollPane(tableClientes);
		scrollPanel.setBounds(10, 23, 760, 287);
		panelTabela.add(scrollPanel);
		
	}

	public void exibirItensTabela(List<ClienteJuridico> listClientesJuridico) {

		for (int i = 0; listClientesJuridico.size() > i; i++) {

			modelo.addRow(new Object[] { listClientesJuridico.get(i).getId_cliente(),
					listClientesJuridico.get(i).getRazaoSocial(), listClientesJuridico.get(i).getNomeFantasia(),
					listClientesJuridico.get(i).getEmail(), listClientesJuridico.get(i).getTelefone(),
					listClientesJuridico.get(i).getCnpj() });

		}

	}
	
	public void recarregarTabela(List<ClienteJuridico> listClientesJuridico) {

		DefaultTableModel tm = (DefaultTableModel) tableClientes.getModel();

		while (tableClientes.getRowCount() > 0) {
			((DefaultTableModel) tableClientes.getModel()).removeRow(0);
		}

		exibirItensTabela(listClientesJuridico);

	}

}
