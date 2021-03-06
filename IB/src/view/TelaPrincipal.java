package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;

import controller.ControleIB;
import controller.lstExtrato;
import model.Conta;
import model.Transferencia;

public class TelaPrincipal implements ActionListener, ItemListener {
	private static TelaPrincipal tela = new TelaPrincipal();
	private static JFrame janela;
	private static JPanel telaLogin;
	private JPanel telaCadastro;
	private JPanel telaEscolha;
	private JPanel telaTransferencia;
	private JPanel telaPagamento;
	private JPanel telaRecarga;
	private JPanel telaExtrato;
	private JPanel painelSuperior;

	private ControleIB controle = new ControleIB();
	private lstExtrato listaExtrato = new lstExtrato();
	private JTextField login;
	private JTextField nome;
	private JTextField cpf;
	private JTextField nconta;
	private JTextField agencia;

	private JTextField saldo;
	private JPasswordField senha;

	private JTextField bancoReceb;
	private JTextField agenciaReceb;
	private JTextField contaReceb;
	private JTextField nomeReceb;
	private JTextField cpfReceb;

	private JTextField valorReceb;
	private JComboBox cbxFinalidade;

	JTable tabelaExtrato = new JTable();
	private JTextArea identificacao;
	Conta conta = new Conta();

	private JTextField valorPagamento;
	private JTextArea identificacaoPagamento;

	private int clkConta;
	private JComboBox operadora;
	private JComboBox valorRecarga;

	private boolean recarga = false;

	//adiciona icone do programa
	public void icon() {
		URL link = getClass().getResource("./coin.png");
		ImageIcon icn = new ImageIcon(link);
		janela.setIconImage(icn.getImage());
	}

	//modulo de inicializa��o com configura��es da janela
	public static void main(String[] args) {
		janela = new JFrame("Banco do HUE");
		tela.icon();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		tela.telaLogin();
		janela.setContentPane(telaLogin);
		janela.setVisible(true);
		janela.setResizable(false);
		janela.setSize(900, 550);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//tela de login
	public void telaLogin() {
		telaLogin = new JPanel(new GridLayout(3, 1));

		JLabel imagem = new JLabel(new ImageIcon(adjustaImagem(200, 200)));

		Font fonte = new Font("Segoe UI", Font.PLAIN, 12);

		JLabel lbLogin = new JLabel("Login:");
		lbLogin.setFont(fonte);
		login = new JTextField(20);
		login.setFont(fonte);
		JPanel painelLogin = new JPanel();
		painelLogin.add(lbLogin);
		painelLogin.add(login);

		PlainDocument ajLogin = (PlainDocument) login.getDocument();
		ajLogin.setDocumentFilter(new ajuste(50, "Text"));

		JLabel lbSenha = new JLabel("Senha:");
		lbSenha.setFont(fonte);
		senha = new JPasswordField(20);
		senha.setFont(fonte);
		JPanel painelSenha = new JPanel();
		painelSenha.add(lbSenha);
		painelSenha.add(senha);

		PlainDocument ajSenha = (PlainDocument) senha.getDocument();
		ajSenha.setDocumentFilter(new ajuste(30, "Text"));

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(fonte);
		JButton btnCadastro = new JButton("Primeiro Acesso");
		btnCadastro.setFont(fonte);
		JPanel painelBotao = new JPanel();
		painelBotao.add(btnLogin);
		painelBotao.add(btnCadastro);

		JPanel dadosLogin = new JPanel(new GridLayout(3, 1));
		dadosLogin.add(painelLogin);
		dadosLogin.add(painelSenha);
		dadosLogin.add(painelBotao);

		telaLogin.add(imagem);
		telaLogin.add(dadosLogin);
		telaLogin.add(new JPanel());

		btnLogin.addActionListener(this);
		btnCadastro.addActionListener(this);
	}

	//tela de cadastro do cliente
	public void telaCadastro() {
		telaCadastro = new JPanel();

		JLabel lbNome = new JLabel("Nome Completo:");
		nome = new JTextField(20);
		JPanel painelNome = new JPanel();
		painelNome.add(lbNome);
		painelNome.add(nome);

		PlainDocument ajNome = (PlainDocument) nome.getDocument();
		ajNome.setDocumentFilter(new ajuste(200, "Text"));

		JLabel lbLogin = new JLabel("Login:");
		login = new JTextField(20);
		JPanel painelLogin = new JPanel();
		painelLogin.add(lbLogin);
		painelLogin.add(login);

		PlainDocument ajLogin = (PlainDocument) login.getDocument();
		ajLogin.setDocumentFilter(new ajuste(50, "Text"));

		JLabel lbSenha = new JLabel("Senha:");
		senha = new JPasswordField(20);
		JPanel painelSenha = new JPanel();
		painelSenha.add(lbSenha);
		painelSenha.add(senha);

		PlainDocument ajSenha = (PlainDocument) senha.getDocument();
		ajSenha.setDocumentFilter(new ajuste(30, "Text"));

		JLabel lbCpf = new JLabel("CPF:");
		cpf = new JTextField(16);
		JPanel painelCpf = new JPanel();
		painelCpf.add(lbCpf);
		painelCpf.add(cpf);

		PlainDocument ajCpf = (PlainDocument) cpf.getDocument();
		ajCpf.setDocumentFilter(new ajuste(11, "Int"));

		JPanel painelBanco = new JPanel(new GridLayout(1, 2));

		JLabel lbConta = new JLabel("Conta:");
		nconta = new JTextField(11);
		JPanel painelConta = new JPanel();
		painelConta.add(lbConta);
		painelConta.add(nconta);

		PlainDocument ajConta = (PlainDocument) nconta.getDocument();
		ajConta.setDocumentFilter(new ajuste(10, "Text"));

		JLabel lbAgencia = new JLabel("Agencia:");
		agencia = new JTextField(5);
		JPanel painelAgencia = new JPanel();
		painelAgencia.add(lbAgencia);
		painelAgencia.add(agencia);

		PlainDocument ajAgencia = (PlainDocument) agencia.getDocument();
		ajAgencia.setDocumentFilter(new ajuste(4, "Int"));

		painelBanco.add(painelAgencia);
		painelBanco.add(painelConta);

		JLabel lbValor = new JLabel("Saldo da Conta:");
		saldo = new JTextField();
		saldo.setColumns(10);
		JPanel painelValor = new JPanel();
		painelValor.add(lbValor);
		painelValor.add(saldo);

		PlainDocument ajSaldo = (PlainDocument) saldo.getDocument();
		ajSaldo.setDocumentFilter(new ajuste(12, "Float"));

		JButton btnCadastro = new JButton("Cadastrar");
		JButton btnVoltar = new JButton("Voltar");
		JPanel painelBotao = new JPanel(new GridLayout(1, 2));
		painelBotao.add(btnCadastro);
		painelBotao.add(btnVoltar);

		JPanel dadosLogin = new JPanel(new GridLayout(10, 1));
		dadosLogin.add(painelNome);
		dadosLogin.add(painelLogin);
		dadosLogin.add(painelSenha);
		dadosLogin.add(painelCpf);
		dadosLogin.add(painelBanco);
		dadosLogin.add(painelValor);
		dadosLogin.add(painelBotao);

		telaCadastro.add(new JPanel());
		telaCadastro.add(dadosLogin);
		telaCadastro.add(new JPanel());

		btnCadastro.addActionListener(this);
		btnVoltar.addActionListener(this);
	}

	//tela principal com op��es de opera��es
	public void telaEscolha() {
		telaEscolha = new JPanel(new BorderLayout());

		int btnX = 130;
		int btnY = 40;

		JLabel titulo = new JLabel("Bem vindo ao banco HUE");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
		JPanel painelTitulo = new JPanel();
		painelTitulo.add(titulo);

		Font fonteTexto = new Font("Segoe UI", Font.PLAIN, 12);

		JPanel painelGeral = new JPanel(new GridLayout(1, 4));

		JPanel painelTransferencia = new JPanel(new GridLayout(9, 1));
		JButton btnTranferencia = new JButton("Transferencia");
		btnTranferencia.setPreferredSize(new Dimension(btnX, btnY));
		btnTranferencia.setFont(fonteTexto);
		JPanel botao1 = new JPanel();
		botao1.add(btnTranferencia);
		JLabel lbTransferencia = new JLabel();
		lbTransferencia.setText("<html><center>Transferencia DOC ou TED entre contas HUE ou outros bancos</html>");
		lbTransferencia.setFont(fonteTexto);
		painelTransferencia.add(new JPanel());
		painelTransferencia.add(new JPanel());
		painelTransferencia.add(new JPanel());
		painelTransferencia.add(botao1);
		painelTransferencia.add(lbTransferencia);

		JPanel painelExtrato = new JPanel(new GridLayout(9, 1));
		JButton btnExtrato = new JButton("Extrato");
		btnExtrato.setPreferredSize(new Dimension(btnX, btnY));
		btnExtrato.setFont(fonteTexto);
		JPanel botao2 = new JPanel();
		botao2.add(btnExtrato);
		JLabel lbExtrato = new JLabel();
		lbExtrato.setText("<html><center>Extrato de suas movimenta��es da conta</html>");
		lbExtrato.setFont(fonteTexto);
		painelExtrato.add(new JPanel());
		painelExtrato.add(new JPanel());
		painelExtrato.add(new JPanel());
		painelExtrato.add(botao2);
		painelExtrato.add(lbExtrato);

		JPanel painelPagamento = new JPanel(new GridLayout(9, 1));
		JButton btnPagamento = new JButton("Pagamento");
		btnPagamento.setPreferredSize(new Dimension(btnX, btnY));
		btnPagamento.setFont(fonteTexto);
		JPanel botao3 = new JPanel();
		botao3.add(btnPagamento);
		JLabel lbPagamento = new JLabel();
		lbPagamento.setText("<html><center>Pagamentos por codigo de barra</html>");
		lbPagamento.setFont(fonteTexto);
		painelPagamento.add(new JPanel());
		painelPagamento.add(new JPanel());
		painelPagamento.add(new JPanel());
		painelPagamento.add(botao3);
		painelPagamento.add(lbPagamento);

		JPanel painelRecarga = new JPanel(new GridLayout(9, 1));
		JButton btnRecarga = new JButton("Recarga");
		btnRecarga.setPreferredSize(new Dimension(btnX, btnY));
		btnRecarga.setFont(fonteTexto);
		JPanel botao4 = new JPanel();
		botao4.add(btnRecarga);
		JLabel lbRecarga = new JLabel();
		lbRecarga.setText("<html><center>Recarga pr� pago</html>");
		lbRecarga.setFont(fonteTexto);
		painelRecarga.add(new JPanel());
		painelRecarga.add(new JPanel());
		painelRecarga.add(new JPanel());
		painelRecarga.add(botao4);
		painelRecarga.add(lbRecarga);

		btnTranferencia.addActionListener(this);
		btnPagamento.addActionListener(this);
		btnRecarga.addActionListener(this);
		btnExtrato.addActionListener(this);

		painelGeral.add(painelTransferencia);
		painelGeral.add(painelExtrato);
		painelGeral.add(painelPagamento);
		painelGeral.add(painelRecarga);

		telaEscolha.add(painelSuperior(), BorderLayout.NORTH);
		telaEscolha.add(painelGeral, BorderLayout.CENTER);
	}

	//tela de transferencia entre contas
	public void telaTransferencia() {
		telaTransferencia = new JPanel(new BorderLayout());

		JPanel dadosTransferencia = new JPanel(new GridLayout(8, 1));

		JLabel lbBanco = new JLabel("Banco: ");
		bancoReceb = new JTextField(10);
		JPanel painelBanco = new JPanel();
		painelBanco.add(lbBanco);
		painelBanco.add(bancoReceb);

		PlainDocument ajbanco = (PlainDocument) bancoReceb.getDocument();
		ajbanco.setDocumentFilter(new ajuste(3, "Int"));

		JLabel lbAgencia = new JLabel("Agencia e Conta: ");
		agenciaReceb = new JTextField(4);
		contaReceb = new JTextField(10);
		JPanel painelAgenciaConta = new JPanel();
		painelAgenciaConta.add(lbAgencia);
		painelAgenciaConta.add(agenciaReceb);
		painelAgenciaConta.add(contaReceb);

		PlainDocument ajAje = (PlainDocument) agenciaReceb.getDocument();
		ajAje.setDocumentFilter(new ajuste(4, "Int"));

		PlainDocument ajCont = (PlainDocument) contaReceb.getDocument();
		ajCont.setDocumentFilter(new ajuste(10, "Text"));

		JLabel lbNomeBeneficiario = new JLabel("Nome: ");
		nomeReceb = new JTextField(20);
		nomeReceb.setEditable(false);
		JPanel painelNomeBeneficiario = new JPanel();
		painelNomeBeneficiario.add(lbNomeBeneficiario);
		painelNomeBeneficiario.add(nomeReceb);

		PlainDocument ajNome = (PlainDocument) nomeReceb.getDocument();
		ajNome.setDocumentFilter(new ajuste(200, "Text"));

		JLabel lbCpf = new JLabel("CPF: ");
		cpfReceb = new JTextField(10);
		JPanel painelCpfTransf = new JPanel();
		painelCpfTransf.add(lbCpf);
		painelCpfTransf.add(cpfReceb);

		PlainDocument ajCpf = (PlainDocument) cpfReceb.getDocument();
		ajCpf.setDocumentFilter(new ajuste(11, "Int"));

		JLabel lbFinalidade = new JLabel("Finalidade: ");
		cbxFinalidade = new JComboBox<String>();
		cbxFinalidade.addItem("01 - Credito em conta corrente");
		cbxFinalidade.addItem("07 - Pagamentos em geral");
		cbxFinalidade.addItem("12 - Transferencia Internacional");
		cbxFinalidade.addItem("22 - DOC para Poupan�a");
		cbxFinalidade.addItem("50 - Outros");
		JPanel painelFinalidade = new JPanel();
		painelFinalidade.add(lbFinalidade);
		painelFinalidade.add(cbxFinalidade);

		JLabel lbIdenfificacao = new JLabel("Idenfica��o: ");
		identificacao = new JTextArea(4, 20);
		JPanel painelIdentificacao = new JPanel();
		painelIdentificacao.add(lbIdenfificacao);
		painelIdentificacao.add(identificacao);

		PlainDocument ajId = (PlainDocument) identificacao.getDocument();
		ajId.setDocumentFilter(new ajuste(200, "Text"));

		JLabel lbValor = new JLabel("Valor: ");
		valorReceb = new JTextField(10);
		JPanel painelValor = new JPanel();
		painelValor.add(lbValor);
		painelValor.add(valorReceb);

		PlainDocument ajSaldo = (PlainDocument) valorReceb.getDocument();
		ajSaldo.setDocumentFilter(new ajuste(12, "Float"));

		JButton btnTransferir = new JButton("Transferir");
		JButton btnCancelar = new JButton("Cancelar e Voltar ao Menu");
		JPanel painelBotao = new JPanel();
		painelBotao.add(btnTransferir);
		painelBotao.add(btnCancelar);

		btnCancelar.addActionListener(this);
		btnTransferir.addActionListener(this);

		dadosTransferencia.add(painelBanco);
		dadosTransferencia.add(painelAgenciaConta);
		dadosTransferencia.add(painelNomeBeneficiario);
		dadosTransferencia.add(painelCpfTransf);
		dadosTransferencia.add(painelFinalidade);
		dadosTransferencia.add(painelIdentificacao);
		dadosTransferencia.add(painelValor);
		dadosTransferencia.add(painelBotao);

		JScrollPane scroll = new JScrollPane(dadosTransferencia);

		telaTransferencia.add(painelSuperior(), BorderLayout.NORTH);
		telaTransferencia.add(dadosTransferencia, BorderLayout.CENTER);
	}

	//tela de pagamento de contas
	public void telaPagamento() {
		telaPagamento = new JPanel(new BorderLayout());

		JPanel paineis = new JPanel(new GridLayout(4, 1));
		JLabel lbCodigo = new JLabel("Codigo de barras: ");
		JTextField codigoBarras = new JTextField(30);
		JPanel painelCodigo = new JPanel();
		painelCodigo.add(lbCodigo);
		painelCodigo.add(codigoBarras);

		PlainDocument ajCod = (PlainDocument) codigoBarras.getDocument();
		ajCod.setDocumentFilter(new ajuste(47, "Text"));

		JLabel lbPagamento = new JLabel("Valor do Pagamento: ");
		valorPagamento = new JTextField(10);
		JPanel painelPagamento = new JPanel();
		painelPagamento.add(lbPagamento);
		painelPagamento.add(valorPagamento);

		PlainDocument ajPag = (PlainDocument) valorPagamento.getDocument();
		ajPag.setDocumentFilter(new ajuste(12, "Float"));

		JLabel lbIdent = new JLabel("Identifica��o: ");
		identificacaoPagamento = new JTextArea(4, 20);
		JPanel painelIden = new JPanel();
		painelIden.add(lbIdent);
		painelIden.add(identificacaoPagamento);

		PlainDocument ajId = (PlainDocument) identificacaoPagamento.getDocument();
		ajId.setDocumentFilter(new ajuste(200, "Text"));

		JButton btnPagar = new JButton("Pagar");
		JButton btnCancelar = new JButton("Cancelar e Voltar ao Menu");
		JPanel painelBotao = new JPanel();
		painelBotao.add(btnPagar);
		painelBotao.add(btnCancelar);

		paineis.add(painelCodigo);
		paineis.add(painelPagamento);
		paineis.add(painelIden);
		paineis.add(painelBotao);

		btnPagar.addActionListener(this);
		btnCancelar.addActionListener(this);

		telaPagamento.add(painelSuperior(), BorderLayout.NORTH);
		telaPagamento.add(paineis, BorderLayout.CENTER);
	}

	//tela de recarga de celulares
	public void telaRecarga() {
		telaRecarga = new JPanel(new BorderLayout());

		JPanel paineis = new JPanel(new GridLayout(4, 1));

		JPanel painelTel = new JPanel();
		JLabel lbTelefone = new JLabel("DDD e Telefone: ");
		JTextField ddd = new JTextField(2);
		JTextField telefone = new JTextField(8);
		painelTel.add(lbTelefone);
		painelTel.add(ddd);
		painelTel.add(telefone);

		PlainDocument ajDdd = (PlainDocument) ddd.getDocument();
		ajDdd.setDocumentFilter(new ajuste(2, "Int"));

		PlainDocument ajTel = (PlainDocument) telefone.getDocument();
		ajTel.setDocumentFilter(new ajuste(9, "Int"));

		JLabel lbOperadora = new JLabel("Operadora: ");
		operadora = new JComboBox<String>();
		operadora.addItem("TIM");
		operadora.addItem("Vivo");
		operadora.addItem("Claro");
		operadora.addItemListener(this);
		JPanel painelOperadora = new JPanel();
		painelOperadora.add(lbOperadora);
		painelOperadora.add(operadora);

		JLabel lbPagamento = new JLabel("Valor da Recarga: ");
		valorRecarga = new JComboBox<String>();

		valorRecarga.addActionListener(this);
		JPanel painelPagamento = new JPanel();
		painelPagamento.add(lbPagamento);
		painelPagamento.add(valorRecarga);

		recargaValor(operadora.getSelectedItem().toString());

		JButton btnRecaregar = new JButton("Realizar Recarga");
		JButton btnCancelar = new JButton("Cancelar e Voltar ao Menu");
		JPanel painelBotao = new JPanel();
		painelBotao.add(btnRecaregar);
		painelBotao.add(btnCancelar);

		paineis.add(painelTel);
		paineis.add(painelOperadora);
		paineis.add(painelPagamento);
		paineis.add(painelBotao);

		btnRecaregar.addActionListener(this);
		btnCancelar.addActionListener(this);

		telaRecarga.add(painelSuperior(), BorderLayout.NORTH);
		telaRecarga.add(paineis, BorderLayout.CENTER);
	}

	//valores de recarga por operadora
	public void recargaValor(String op) {
		valorRecarga.removeAllItems();
		if ("TIM".equals(op)) {
			valorRecarga.addItem("R$ 15");
			valorRecarga.addItem("R$ 18");
			valorRecarga.addItem("R$ 20");
			valorRecarga.addItem("R$ 30");
			valorRecarga.addItem("R$ 50");
			valorRecarga.addItem("R$ 100");
		} else if ("Vivo".equals(op)) {
			valorRecarga.addItem("R$ 15");
			valorRecarga.addItem("R$ 20");
			valorRecarga.addItem("R$ 30");
			valorRecarga.addItem("R$ 50");
			valorRecarga.addItem("R$ 100");
		} else if ("Claro".equals(op)) {
			valorRecarga.addItem("R$ 12");
			valorRecarga.addItem("R$ 20");
			valorRecarga.addItem("R$ 25");
			valorRecarga.addItem("R$ 40");
			valorRecarga.addItem("R$ 50");
			valorRecarga.addItem("R$ 100");
		}
		valorRecarga.invalidate();
		valorRecarga.repaint();
		valorRecarga.repaint();
	}

	//tela de extrato
	public void telaExtrato() {
		telaExtrato = new JPanel(new BorderLayout());

		JLabel lbExtrato = new JLabel("<html><center>Extrato atual</html>");
		lbExtrato.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tabelaExtrato = new JTable(listaExtrato);
		tabelaExtrato.setModel(listaExtrato);
		tabelaExtrato.setOpaque(true);

		JButton btnVoltar = new JButton("Voltar ao Menu");
		btnVoltar.setPreferredSize(new Dimension(150, 40));
		JPanel painelBtn = new JPanel();
		painelBtn.add(btnVoltar);
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().add(tabelaExtrato);

		JPanel painelExtrato = new JPanel(new BorderLayout());

		painelExtrato.add(lbExtrato, BorderLayout.NORTH);
		painelExtrato.add(scroll, BorderLayout.CENTER);
		painelExtrato.add(painelBtn, BorderLayout.SOUTH);

		btnVoltar.addActionListener(this);

		telaExtrato.add(painelSuperior(), BorderLayout.NORTH);
		telaExtrato.add(painelExtrato, BorderLayout.CENTER);
	}

	//painel superior da tela com dados do usuario logado
	public JPanel painelSuperior() {
		JLabel imagem = new JLabel(new ImageIcon(adjustaImagem(100, 100)));

		painelSuperior = new JPanel(new BorderLayout());

		JPanel conteudo = new JPanel(new GridLayout(3, 1));
		JLabel user = new JLabel("Ola, " + conta.getNome());
		JLabel saldo = new JLabel("Saldo: " + String.valueOf(conta.getSaldo()));
		JLabel acc = new JLabel("Agencia: " + conta.getAgencia() + " Conta: " + conta.getConta());
		conteudo.add(user);
		conteudo.add(acc);
		conteudo.add(saldo);

		JPanel painelSair = new JPanel();
		JButton btnSair = new JButton("Sair");
		btnSair.setPreferredSize(new Dimension(150, 40));
		painelSair.add(btnSair);
		btnSair.addActionListener(this);

		painelSuperior.add(imagem, BorderLayout.WEST);
		painelSuperior.add(conteudo, BorderLayout.CENTER);
		painelSuperior.add(painelSair, BorderLayout.EAST);
		painelSuperior.add(new JSeparator(), BorderLayout.SOUTH);

		return painelSuperior;
	}

	//procedimento de login
	public boolean fazerLogin() {
		conta.setLogin(login.getText());
		String pass = new String(senha.getPassword());
		conta.setSenha(pass);
		return controle.acessoConta(conta);
	}

	//procedimento de cria��o de conta
	public void adicionarConta() {
		if (nome.getText().isEmpty() || login.getText().isEmpty() || cpf.getText().isEmpty()
				|| agencia.getText().isEmpty() || nconta.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "POR FAVOR, PREENCHA TODOS OS CAMPOS");
		} else if (Float.parseFloat(saldo.getText()) < 0 || Float.parseFloat(saldo.getText()) > 10000000
				|| saldo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "VALOR DE SALDO DEVE SER ENTRE 0 E 10000000");
		} else {
			Conta acc = new Conta();
			acc.setNome(nome.getText());
			acc.setLogin(login.getText());
			acc.setCpf(cpf.getText());
			acc.setAgencia(agencia.getText());
			acc.setConta(nconta.getText());
			acc.setSaldo(Float.parseFloat(saldo.getText()));
			String pass = new String(senha.getPassword());
			acc.setSenha(pass);
			controle.criarConta(acc);
		}
	}

	//procedimento de transferencia entre contas
	public void transferenciaConta() {
		Transferencia destino = new Transferencia();
		if (agenciaReceb.getText().isEmpty() || contaReceb.getText().isEmpty() || cpfReceb.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "POR FAVOR, PREENCHA TODOS OS CAMPOS");
		} else if (valorReceb.getText().isEmpty() || Float.parseFloat(valorReceb.getText()) < 1) {
			JOptionPane.showMessageDialog(null, "O VALOR DA TRANSFERENCIA DEVE SER MAIOR QUE 1");
		} else {

			if (conta.modificaSaldo(Float.parseFloat(valorReceb.getText())) == true) {
				destino.setAgencia(agenciaReceb.getText());
				destino.setConta(contaReceb.getText());
				destino.setCpf(cpfReceb.getText());
				destino.setValor(Float.parseFloat(valorReceb.getText()));
				String ocorrencia = "Transferencia - [" + cbxFinalidade.getSelectedItem().toString() + "]";
				clkConta++;
				if (clkConta == 1) {
					String nomeDest = controle.verificaConta(destino);
					System.out.println("nome recebido: " + nomeDest);
					if (nomeDest == null) {
						JOptionPane.showMessageDialog(null, "CONTA DESTINO N�O ENCONTRADA");
						clkConta = 0;
					} else {
						nomeReceb.setText(nomeDest);
					}
				} else if (clkConta == 2) {
					int ok = JOptionPane.showConfirmDialog(null, "DESEJA REALIZAR ESTA TRANSFERENCIA?");
					System.out.println(ok);
					if (ok == 0) {
						conta.setSaldo(conta.getSaldo() - Float.parseFloat(valorReceb.getText()));
						painelSuperior.invalidate();
						painelSuperior.revalidate();
						painelSuperior.repaint();
						controle.transferirValor(conta, destino, ocorrencia, identificacao.getText());
						JOptionPane.showMessageDialog(null, "TRANSFERENCIA REALIZADA COM SUCESSO!");
					} else {
						painelSuperior.invalidate();
						painelSuperior.revalidate();
						painelSuperior.repaint();
					}
					clkConta = 0;
				}

			} else {
				JOptionPane.showMessageDialog(null, "SALDO INSUFICIENTE PARA TRANSFERENCIA");
			}
		}
	}

	//procedimento de pagamento por codigo
	public void pagamentoCodigo() {
		if (Float.parseFloat(valorPagamento.getText()) <= 0) {
			JOptionPane.showMessageDialog(null, "VALOR DE PAGAMENTO INVALIDO");
		} else if (conta.getSaldo() - (Float.parseFloat(valorPagamento.getText())) < 0) {
			JOptionPane.showMessageDialog(null, "SALDO NA CONTA INSUFICIENTE");
		} else {
			int ok = JOptionPane.showConfirmDialog(null, "DESEJA REALIZAR ESTE PAGAMENTO?");
			if (ok == 0) {
				Float valor = Float.parseFloat(valorPagamento.getText());
				conta.setSaldo(conta.getSaldo() - Float.parseFloat(valorPagamento.getText()));
				controle.pagarConta(valor, conta, identificacaoPagamento.getText());
				JOptionPane.showMessageDialog(null, "PAGAMENTO REALIZADO COM SUCESSO!");
			}
		}
	}

	//procedimento para gerar imagem de logo
	public BufferedImage adjustaImagem(int w, int h) {
		BufferedImage bufImagem = null;
		try {
			bufImagem = ImageIO.read(getClass().getResource("./coin.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage imgSize = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = imgSize.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(bufImagem, 0, 0, w, h, null);
		g2.dispose();
		return imgSize;
	}

	//procedimento de a��o em bot�es
	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		if ("Login".equals(cmd)) {
			boolean aprovado = fazerLogin();
			if (aprovado == false) {
				JOptionPane.showMessageDialog(null, "LOGIN OU SENHA INVALIDO");
			} else {
				telaEscolha();
				telaLogin.setVisible(false);
				janela.setContentPane(telaEscolha);
				telaEscolha.setVisible(true);
				janela.invalidate();
				janela.revalidate();
				janela.repaint();
			}
		} else if ("Primeiro Acesso".equals(cmd)) {
			telaCadastro();
			telaLogin.setVisible(false);
			janela.setContentPane(telaCadastro);
			telaCadastro.setVisible(true);
			janela.invalidate();
			janela.revalidate();
			janela.repaint();
		} else if ("Cadastrar".equals(cmd)) {
			adicionarConta();
		}

		if ("Sair".equals(cmd)) {
			telaLogin();
			telaEscolha.setVisible(false);
			janela.setContentPane(telaLogin);
			telaLogin.setVisible(true);
			janela.invalidate();
			janela.revalidate();
			janela.repaint();
		} else if ("Transferencia".equals(cmd)) {
			telaTransferencia();
			telaEscolha.setVisible(false);
			janela.setContentPane(telaTransferencia);
			telaTransferencia.setVisible(true);
		} else if ("Pagamento".equals(cmd)) {
			telaPagamento();
			telaEscolha.setVisible(false);
			janela.setContentPane(telaPagamento);
			telaPagamento.setVisible(true);

		} else if ("Recarga".equals(cmd)) {
			telaRecarga();
			telaEscolha.setVisible(false);
			janela.setContentPane(telaRecarga);
			telaRecarga.setVisible(true);
			recarga = true;
		} else if ("Extrato".equals(cmd)) {
			listaExtrato.verExtrato(conta);
			telaExtrato();
			telaEscolha.setVisible(false);
			janela.setContentPane(telaExtrato);
			telaExtrato.setVisible(true);
			tabelaExtrato.invalidate();
			tabelaExtrato.revalidate();
			tabelaExtrato.repaint();
		} else if ("Cancelar e Voltar ao Menu".equals(cmd) || "Voltar ao Menu".equals(cmd)) {
			telaEscolha();
			janela.setContentPane(telaEscolha);
			telaEscolha.setVisible(true);
			janela.invalidate();
			janela.revalidate();
			janela.repaint();
		} else if ("Voltar".equals(cmd)) {
			telaLogin();
			telaCadastro.setVisible(false);
			janela.setContentPane(telaLogin);
			telaLogin.setVisible(true);
			recarga = false;
		}
		if ("Transferir".equals(cmd)) {
			transferenciaConta();
		}
		if ("Pagar".equals(cmd)) {
			pagamentoCodigo();
		}
		if ("Realizar Recarga".equals(cmd)) {
			String rec = valorRecarga.getSelectedItem().toString();
			String numberOnly = rec.replaceAll("[^0-9]", "");

			Float valor = Float.parseFloat(numberOnly);
			if (conta.getSaldo() - valor >= 0) {
				int op = JOptionPane.showConfirmDialog(null, "DESEJA REALIZAR ESTA RECARGA?");
				if (op == 0) {
					
					String ocorrencia;
					ocorrencia = "Recarga " + operadora.getSelectedItem().toString() + " - "
							+ valorRecarga.getSelectedItem().toString();

					conta.setSaldo(conta.getSaldo() - valor);

					controle.realizarRecarga(conta, ocorrencia, valor);
					JOptionPane.showMessageDialog(null, "RECARGA REALIZADA COM SUCESSO!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "SALDO INSUFICIENTE PARA ESTA OPERA��O");
			}
		}
	}

	//procedimento de altera��o de valores na combobox de recarga
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String cbxItem = (String) operadora.getSelectedItem();
			switch (cbxItem) {
			case "TIM":
				recargaValor(cbxItem);
				break;
			case "Vivo":
				recargaValor(cbxItem);
				break;
			case "Claro":
				recargaValor(cbxItem);
				break;
			}
		}
	}

	// AJUSTA E LIMITA TEXTO DOS TEXTFIELD

	class ajuste extends DocumentFilter {

		private int limite;
		private String tipo;

		public ajuste(int limite, String tipo) {
			this.limite = limite;
			this.tipo = tipo;
		}

		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {

			Document doc = fb.getDocument();
			StringBuilder sb = new StringBuilder();
			sb.append(doc.getText(0, doc.getLength()));
			sb.insert(offset, string);

			if (test(sb.toString())) {
				super.insertString(fb, offset, string, attr);
			} else {
				// warn the user and don't allow the insert
			}
		}

		private boolean test(String text) {
			if ("Text".equals(tipo)) {
				if (text.length() <= limite) {
					return true;
				} else {
					return false;
				}
			} else if ("Int".equals(tipo)) {
				if (text.length() <= limite) {
					try {
						Long.parseLong(text);
						return true;
					} catch (NumberFormatException e) {
						return false;
					}
				} else {
					return false;
				}
			} else if ("Float".equals(tipo)) {
				if (text.length() <= limite) {
					try {
						Float.parseFloat(text);
						return true;
					} catch (NumberFormatException e) {
						return false;
					}
				} else {
					return false;
				}
			}
			return false;
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			Document doc = fb.getDocument();
			StringBuilder sb = new StringBuilder();
			sb.append(doc.getText(0, doc.getLength()));
			sb.replace(offset, offset + length, text);

			if (test(sb.toString())) {
				super.replace(fb, offset, length, text, attrs);
			} else {
				// warn the user and don't allow the insert
			}
		}

		@Override
		public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
			Document doc = fb.getDocument();
			StringBuilder sb = new StringBuilder();
			sb.append(doc.getText(0, doc.getLength()));
			sb.delete(offset, offset + length);

			if (test(sb.toString())) {
				super.remove(fb, offset, length);
			} else {
				// warn the user and don't allow the insert
			}
		}
	}
}
