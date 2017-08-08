package br.sodadrink.sp.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import br.sodadrink.sp.DAO.LoginDAO;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Usuario;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	ImageIcon imagem = new ImageIcon();
	private JPanel panelPrincipal;
	private JButton btnEntrar;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JLabel lblImagem;
	private JPasswordField pswfSenha;

	JLabel lblCarregamento;

	public Login() {

		setTitle("Login SodaDrink");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 528);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(255, 255, 255));
		contentPane.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				if (!txtUsuario.getText().toString().isEmpty() && !pswfSenha.getText().toString().isEmpty()) {

					new SwingWorker<Void, String>() {

						@Override
						protected Void doInBackground() throws Exception {

							lblCarregamento.setVisible(true);

							Thread.sleep(1000);

							return null;
						}

						@Override
						protected void done() {

							if (ConnectDataBase.openConection() != null) {

								List<Usuario> listUsuario = new ArrayList<>();

								listUsuario = LoginDAO.fazerLogin(txtUsuario.getText().toString(),
										pswfSenha.getText().toString());

								if (listUsuario.get(0).isConseguiuLogar() == true) {

									Principal principal = new Principal(listUsuario.get(0).getId_usuario());
									dispose();

								} else {

									lblCarregamento.setVisible(false);
									JOptionPane.showMessageDialog(null, "Usuario ou Senha Incorreto!");

								}

							} else {

								lblCarregamento.setVisible(false);
								JOptionPane.showMessageDialog(null, "Não foi possivel conectar ao Banco de dados!");

							}

						}

					}.execute();
					
				} else {

					JOptionPane.showMessageDialog(null, "Preencha os campos!");

				}
			}
		});

		lblCarregamento = new JLabel();
		lblCarregamento.setIcon(new ImageIcon(Login.class.getResource("/br/sodadrink/sp/imagens/loading.gif")));
		lblCarregamento.setVisible(false);
		lblCarregamento.setBounds(357, 413, 34, 30);
		panelPrincipal.add(lblCarregamento);
		btnEntrar.setForeground(Color.BLACK);
		btnEntrar.setBackground(Color.WHITE);
		btnEntrar.setBounds(400, 413, 100, 30);
		panelPrincipal.add(btnEntrar);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(230, 285, 270, 30);
		panelPrincipal.add(txtUsuario);

		lblUsuario = new JLabel("Us\u00FAario");
		lblUsuario.setBounds(230, 260, 46, 14);
		panelPrincipal.add(lblUsuario);

		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(230, 336, 46, 14);
		panelPrincipal.add(lblSenha);

		lblImagem = new JLabel();
		lblImagem.setIcon(new ImageIcon(Login.class.getResource("/br/sodadrink/sp/imagens/logo.png")));
		lblImagem.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImagem.setBounds(73, 49, 583, 173);
		panelPrincipal.add(lblImagem);

		pswfSenha = new JPasswordField();
		pswfSenha.setBounds(230, 361, 270, 30);
		panelPrincipal.add(pswfSenha);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
