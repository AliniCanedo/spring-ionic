package com.alinicanedo.ecommerce.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alinicanedo.ecommerce.domain.Categoria;
import com.alinicanedo.ecommerce.domain.Cidade;
import com.alinicanedo.ecommerce.domain.Cliente;
import com.alinicanedo.ecommerce.domain.Endereco;
import com.alinicanedo.ecommerce.domain.Estado;
import com.alinicanedo.ecommerce.domain.ItemPedido;
import com.alinicanedo.ecommerce.domain.Pagamento;
import com.alinicanedo.ecommerce.domain.PagamentoComBoleto;
import com.alinicanedo.ecommerce.domain.PagamentoComCartao;
import com.alinicanedo.ecommerce.domain.Pedido;
import com.alinicanedo.ecommerce.domain.Produto;
import com.alinicanedo.ecommerce.domain.enums.EstadoPagamento;
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
	private CategoriaRepository categoriaRepository;

	@Autowired
	public ProdutoRepository produtoRepository;

	@Autowired
	public CidadeRepository cidadeRepository;

	@Autowired
	public EstadoRepository estadoRepository;

	@Autowired
	public ClienteRepository clienteRepository;

	@Autowired
	public EnderecoRepository enderecoRepository;

	@Autowired
	public PedidoRepository pedidoRepository;

	@Autowired
	public PagamentoRepository pagamentoRepository;

	@Autowired
	public ItemPedidoRepository itemPedidoRepository;

	public void instantiateTestDatabase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("30/09/2017 10:32"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
