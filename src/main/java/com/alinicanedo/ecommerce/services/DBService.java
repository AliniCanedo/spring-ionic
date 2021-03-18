package com.alinicanedo.ecommerce.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alinicanedo.ecommerce.domain.Categoria;
import com.alinicanedo.ecommerce.domain.Cidade;
import com.alinicanedo.ecommerce.domain.Cliente;
import com.alinicanedo.ecommerce.domain.Endereco;
import com.alinicanedo.ecommerce.domain.Estado;
import com.alinicanedo.ecommerce.domain.ItemPedido;
import com.alinicanedo.ecommerce.domain.Pagamento;
import com.alinicanedo.ecommerce.domain.PagamentoComCartao;
import com.alinicanedo.ecommerce.domain.Pedido;
import com.alinicanedo.ecommerce.domain.Produto;
import com.alinicanedo.ecommerce.domain.enums.EstadoPagamento;
import com.alinicanedo.ecommerce.domain.enums.Perfil;
import com.alinicanedo.ecommerce.domain.enums.TipoCliente;
import com.alinicanedo.ecommerce.repositories.CategoriaRepository;
import com.alinicanedo.ecommerce.repositories.CidadeRepository;
import com.alinicanedo.ecommerce.repositories.ClienteRepository;
import com.alinicanedo.ecommerce.repositories.EnderecoRepository;
import com.alinicanedo.ecommerce.repositories.EstadoRepository;
import com.alinicanedo.ecommerce.repositories.ItemPedidoRepository;
import com.alinicanedo.ecommerce.repositories.PagamentoRepository;
import com.alinicanedo.ecommerce.repositories.PedidoRepository;
import com.alinicanedo.ecommerce.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria categoria1 = new Categoria(null, "Cama mesa e banho");
		Categoria categoria2 = new Categoria(null, "Roupa de bebê");
		
		Produto toalha = new Produto(null, "Toalha", 50.00);
		Produto touca = new Produto(null, "Touca", 50.00);
		
		categoria1.getProdutos().addAll(Arrays.asList(toalha));
		categoria2.getProdutos().addAll(Arrays.asList(touca));
		
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(toalha,touca));
		
		Estado est1 = new Estado(null, "Paraná");
		Cidade c1 = new Cidade(null, "Curitiba", est1);

		est1.getCidades().addAll(Arrays.asList(c1));

		estadoRepository.saveAll(Arrays.asList(est1));
		cidadeRepository.saveAll(Arrays.asList(c1));
		
		Cliente cli1 = new Cliente(null, "Alini Canedo", "alini.canedo@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, pe.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		cli1.addPerfil(Perfil.ADMIN);
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);

		cli1.getEnderecos().addAll(Arrays.asList(e1));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);

		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1));
		
		pedidoRepository.saveAll(Arrays.asList(ped1));
		pagamentoRepository.saveAll(Arrays.asList(pagto1));
		
		ItemPedido ip1 = new ItemPedido(ped1, toalha, 0.00, 1, 2000.00);
		toalha.getItens().addAll(Arrays.asList(ip1));

		itemPedidoRepository.saveAll(Arrays.asList(ip1));		
	}
}
