package engii.lab4.engiilab4.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import engii.lab4.engiilab4.exception.CidadeException;
import engii.lab4.engiilab4.exception.ClienteException;
import engii.lab4.engiilab4.model.Cidade;
import engii.lab4.engiilab4.model.Cliente;
import engii.lab4.engiilab4.model.Frete;
import engii.lab4.engiilab4.repository.CidadeRepository;
import engii.lab4.engiilab4.repository.ClienteRepository;
import engii.lab4.engiilab4.repository.FreteRepository;

@SpringBootTest
public class FreteServiceTest {
	
	@Autowired
	private FreteService freteService;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private FreteRepository freteRepository;
	
	private Frete frete;
	private Cidade cidade;
	private Cliente cliente;
	
	@BeforeEach
	public void start() {
		cidade = new Cidade();
		cidade.setNome("cidade01");
		cidade.setTaxa(20);
		cidade.setUf("MA");
//		cidadeRepository.save(cidade);

		cliente = new Cliente();
		cliente.setEndereco("endereco01");
		cliente.setNome("cliente01");
		cliente.setTelefone("0000-0000");
//		clienteRepository.save(cliente);

		frete = new Frete();
		frete.setCidade(cidade);
		frete.setCliente(cliente);
		frete.setDescricao("frete01");
		frete.setPeso(0);
		frete.setValor(0);
	}
	
	@AfterEach
	public void reset() {
		freteRepository.deleteAll();
		cidadeRepository.deleteAll();
		clienteRepository.deleteAll();
	}
	
	@Test
	public void deveSalvar() throws Exception{
		cidadeRepository.save(cidade);
		clienteRepository.save(cliente);
		
		freteService.inserir(frete);
		List<Frete> fretes = freteService.todos();
		
		assertEquals(1, fretes.size());
		
	}
	@Test 
	public void deveTerValorFreteCorreto() throws Exception {
		cidadeRepository.save(cidade);
		clienteRepository.save(cliente);
		
		frete.setPeso(20);
		
		Frete freteSalvo = freteService.inserir(frete);
		
		assertNotNull(freteSalvo);
		assertEquals(220, frete.getValor());
	}
	@Test
	public void deveLancarExcecaoCidadeNaoEncontrada() throws Exception{
		clienteRepository.save(cliente);
		
		CidadeException exception = assertThrows(CidadeException.class, () -> freteService.inserir(frete));
		assertEquals("Cidade não encontrada", exception.getMessage());
		
	}
	@Test
	public void deveLancarExcecaoClienteNaoCadastrado() throws Exception{
		cidadeRepository.save(cidade);
		
		ClienteException exception = assertThrows(ClienteException.class, () -> freteService.inserir(frete));
		assertEquals("Cliente não cadastrado", exception.getMessage());
		
	}
	@Test
	public void deveAcharOFreteDeMaiorValor() throws Exception {
		cidadeRepository.save(cidade);
		clienteRepository.save(cliente);
		
		Frete frete2 = new Frete();
		frete2.setCidade(cidade);
		frete2.setCliente(cliente);
		frete2.setDescricao("frete02");
		frete2.setPeso(50);
		
		frete = freteService.inserir(frete);
		frete2 = freteService.inserir(frete2);		
		List<Frete> achados = freteService.freteComMaiorValor();
	
		assertEquals(1, achados.size());
		assertEquals(frete2, achados.get(0));
	}
	@Test
	public void deveAcharACidadeComMaiorQntFretes() throws Exception {
		// setup
		clienteRepository.save(cliente);
		
		Cidade cidade01 = new Cidade();
		cidade01.setNome("cidade01");
		cidade01.setTaxa(20);
		cidade01.setUf("MA");
		cidadeRepository.save(cidade01);
		
		Cidade cidade02 = new Cidade();
		cidade02.setNome("cidade02");
		cidade02.setTaxa(20);
		cidade02.setUf("MA");
		cidadeRepository.save(cidade02);
		
		Frete frete01 = new Frete();
		frete01.setCidade(cidade01);
		frete01.setCliente(cliente);
		frete01.setDescricao("frete01");
		frete01.setPeso(0);
		frete01.setValor(0);
		freteService.inserir(frete01);
		
		Frete frete02 = new Frete();
		frete02.setCidade(cidade01);
		frete02.setCliente(cliente);
		frete02.setDescricao("frete02");
		frete02.setPeso(0);
		frete02.setValor(0);
		freteService.inserir(frete02);

		Frete frete03 = new Frete();
		frete03.setCidade(cidade02);
		frete03.setCliente(cliente);
		frete03.setDescricao("frete03");
		frete03.setPeso(0);
		frete03.setValor(0);
		freteService.inserir(frete03);
		
		//test
		
		Cidade cidadeEncontrada = freteService.cidadesComMaiorQntFrete();
		
//		assertEquals(1, cidades.size());
//		assertEquals(cidade01, cidades.get(0));
		
		assertNotNull(cidadeEncontrada);
		assertEquals(cidade01, cidadeEncontrada);
		
	}
	
	
}
