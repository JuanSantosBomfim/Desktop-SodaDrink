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
import br.sodadrink.sp.DAO.CategoriaDAO;
import br.sodadrink.sp.model.Categoria;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastrarCategoria extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	JTextField txtCategoria;

	public CadastrarCategoria(String titulo, String opcao, int id_categoria) {
		
		setBounds(100, 100, 450, 261);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(new Color(0, 102, 255));
		panelSuperior.setBounds(0, 0, 434, 66);
		contentPanel.add(panelSuperior);

		JLabel label = new JLabel(titulo);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.BOLD, 22));
		label.setBounds(24, 21, 246, 23);
		panelSuperior.add(label);

		JPanel panelDadosCategoria = new JPanel();
		panelDadosCategoria.setLayout(null);
		panelDadosCategoria.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDadosCategoria.setBounds(10, 73, 414, 80);
		contentPanel.add(panelDadosCategoria);

		txtCategoria = new JTextField();
		txtCategoria.setToolTipText("");
		txtCategoria.setText("");
		txtCategoria.setColumns(10);
		txtCategoria.setBounds(10, 29, 394, 31);
		panelDadosCategoria.add(txtCategoria);

		JLabel lblNomeDaCategoria = new JLabel("Nome da Categoria");
		lblNomeDaCategoria.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNomeDaCategoria.setBounds(10, 11, 139, 19);
		panelDadosCategoria.add(lblNomeDaCategoria);

		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setLayout(null);
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(10, 164, 414, 55);
		contentPanel.add(panelOpcoes);

		JButton btnEvento = new JButton(opcao);
		btnEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean continua = verificaCampo();
				
				if(continua == true){
					
					Categoria categoria = new Categoria();

					categoria.setDescricao(txtCategoria.getText().toString());
					
					if(opcao.equals("Cadastrar")){
						
						boolean retorno = CategoriaDAO.cadastrarCategoriaDAO(categoria);

						if (retorno == true) {

							JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");
						}
						
					}else if(opcao.equals("Atualizar")){
						
						categoria.setId_categoria(id_categoria);
						
						boolean retorno = CategoriaDAO.editarCategoriaDAO(categoria);

						if (retorno == true) {

							JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Erro ao Atualizar!");
						}
						
					}		
					
				}else{
					
					JOptionPane.showMessageDialog(null,"Preencha o Campo!");
					
				}
									

			}
		});

		btnEvento.setIcon(new ImageIcon(CadastrarCategoria.class.getResource("/br/sodadrink/sp/imagens/ic_check_18pt.png")));
		btnEvento.setBounds(10, 11, 132, 30);
		panelOpcoes.add(btnEvento);
		
		if(opcao.equals("Atualizar")){
			
			List<Categoria> listCategoria = new ArrayList<>();
			
			listCategoria = CategoriaDAO.listarCategoriaPorIdDAO(id_categoria);
			
			String descricao = listCategoria.get(0).getDescricao();
			
			txtCategoria.setText(descricao);
			
			
		}

		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
	}
	
	public boolean verificaCampo(){
		
		if(txtCategoria.getText().isEmpty()){
			
			return false;		
		}else{			
			return true;
		}
		
	}


}
