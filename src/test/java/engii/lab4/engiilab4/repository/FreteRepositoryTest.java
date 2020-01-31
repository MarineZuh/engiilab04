package engii.lab4.engiilab4.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import engii.lab4.engiilab4.model.Cidade;
import engii.lab4.engiilab4.model.Cliente;
import engii.lab4.engiilab4.model.CountCidade;
import engii.lab4.engiilab4.model.Frete;

@DataJpaTest
public class FreteRepositoryTest {

	@Autowired
	private FreteRepository freteRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	private Frete frete;
	private Cidade cidade;
	private Cliente cliente;


	@BeforeEach
	public void start() {

		cidade = new Cidade();
		cidade.setNome("cidade01");
		cidade.setTaxa(20);
		cidade.setUf("MA");
		cidadeRepository.save(cidade);

		cliente = new Cliente();
		cliente.setEndereco("endereco01");
		cliente.setNome("cliente01");
		cliente.setTelefone("0000-0000");
		clienteRepository.save(cliente);

		frete = new Frete();
		frete.setCidade(cidade);
		frete.setCliente(cliente);
		frete.setDescricao("frete01");
		frete.setPeso(0);
		frete.setValor(0);
	}
	
//	@AfterEach
//	public void reset() {
//		freteRepository.deleteAll();
//		cidadeRepository.deleteAll();
//		clienteRepository.deleteAll();
//	}

	@Test
	public void saveComCidadeNullDeveLancarException() {
		frete.setCidade(null);

		ConstraintViolationException exception = assertThrows(
				ConstraintViolationException.class,
				() -> freteRepository.save(frete));
		ConstraintViolation c = (ConstraintViolation) exception
				.getConstraintViolations().toArray()[0];

		assertEquals("Cidade deve ser informada", c.getMessage());
	}
	@Test
	public void saveComClienteNullDeveLancarException() {
		frete.setCliente(null);

		ConstraintViolationException exception = assertThrows(
				ConstraintViolationException.class,
				() -> freteRepository.save(frete));
		ConstraintViolation c = (ConstraintViolation) exception
				.getConstraintViolations().toArray()[0];

		assertEquals("Cliente deve ser informado", c.getMessage());
	}
	@Test
	public void saveComDescricaoNullDeveLancarException() {
		frete.setDescricao(null);

		ConstraintViolationException exception = assertThrows(
				ConstraintViolationException.class,
				() -> freteRepository.save(frete));
		ConstraintViolation c = (ConstraintViolation) exception
				.getConstraintViolations().toArray()[0];

		assertEquals("Descricao deve ser informada", c.getMessage());
	}
	@Test
	public void saveComPesoNegativoDeveLancarException() {
		frete.setPeso(-1);

		ConstraintViolationException exception = assertThrows(
				ConstraintViolationException.class,
				() -> freteRepository.save(frete));
		ConstraintViolation c = (ConstraintViolation) exception
				.getConstraintViolations().toArray()[0];

		assertEquals("Peso deve ser maior ou igual a 0.0", c.getMessage());
	}
	@Test
	public void saveComValorNegativoDeveLancarException() {
		frete.setValor(-1);

		ConstraintViolationException exception = assertThrows(
				ConstraintViolationException.class,
				() -> freteRepository.save(frete));
		ConstraintViolation c = (ConstraintViolation) exception
				.getConstraintViolations().toArray()[0];

		assertEquals("Valor deve ser maior ou igual a 0.0", c.getMessage());
	}
	@Test
	public void deveSalvar() {
		freteRepository.save(frete);

		List<Frete> fretes = freteRepository.findAll();

		assertEquals(1, fretes.size());
	}

	@Test
	public void deveAcharUmFretePorNomeDoCliente() {
		freteRepository.save(frete);

		List<Frete> fretes = freteRepository
				.findByClienteOrderByValorDesc(cliente);

		assertEquals(1, fretes.size());

	}
	@Test
	public void deveAcharOFreteDeMaiorValor() {

		Frete frete2 = new Frete();
		frete2.setCidade(cidade);
		frete2.setCliente(cliente);
		frete2.setDescricao("frete01");
		frete2.setPeso(0);
		frete2.setValor(10);
		
		frete = freteRepository.save(frete);
		frete2 = freteRepository.save(frete2);
		
		
		List<Frete> achados = freteRepository.achaFreteComMaiorValor();
		
		assertEquals(1, achados.size());
		assertEquals(frete2, achados.get(0));
	}
	@Test
	public void deveAcharCidadeComMaioQntFretes() {
		freteRepository.save(frete);
		
		Frete frete2 = new Frete();
		frete2.setCidade(cidade);
		frete2.setCliente(cliente);
		frete2.setDescricao("frete02");
		frete2.setPeso(0);
		frete2.setValor(10);
		freteRepository.save(frete2);
		
		Cidade cidade3 = new Cidade();
		cidade3.setNome("cidade01");
		cidade3.setTaxa(20);
		cidade3.setUf("MA");
		cidadeRepository.save(cidade3);
		
		Frete frete3 = new Frete();
		frete3.setCidade(cidade3);
		frete3.setCliente(cliente);
		frete3.setDescricao("frete03");
		frete3.setPeso(0);
		frete3.setValor(10);
		freteRepository.save(frete3);
		
		List<CountCidade> cidadeAchada = freteRepository.cidadeComMaiorQntFretes(PageRequest.of(0, 1));
		
//		System.err.println(cidadeAchada.get(0).cidade.getNome());
		
		assertEquals(1, cidadeAchada.size());
		
	}

}
