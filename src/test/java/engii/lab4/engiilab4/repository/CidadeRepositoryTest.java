package engii.lab4.engiilab4.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import engii.lab4.engiilab4.model.Cidade;
import engii.lab4.engiilab4.model.Cliente;

@DataJpaTest
public class CidadeRepositoryTest {

	@Autowired
	private CidadeRepository cidadeRepository;

	
	private Cidade cidade;
	
	@BeforeEach
	public void start() {
	 cidade = new Cidade();
	 cidade.setNome("cidade01");
	 cidade.setTaxa(20);
	 cidade.setUf("MA");
	 
	
	  
	}
	@Test
	public void saveComNomeNullDeveLancarException() {
		cidade.setNome(null);
		
		ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> cidadeRepository.save(cidade));
		ConstraintViolation c = (ConstraintViolation) exception.getConstraintViolations().toArray()[0];
		
		assertEquals("Nome deve ser informado", c.getMessage());
	}
	@Test
	public void saveComTaxaNegativaDeveLancarException() {
		cidade.setTaxa(-1);
		
		ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> cidadeRepository.save(cidade));
		ConstraintViolation c = (ConstraintViolation) exception.getConstraintViolations().toArray()[0];
		
		assertEquals("Taxa deve ser maior ou igual a 0.0", c.getMessage());
	}
	@Test
	public void saveComUfNullDeveLancarException() {
		cidade.setUf(null);
		
		ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> cidadeRepository.save(cidade));
		ConstraintViolation c = (ConstraintViolation) exception.getConstraintViolations().toArray()[0];
		
		assertEquals("UF deve ser informado", c.getMessage());
	}
	@Test
	public void deveSalvar() {
		cidadeRepository.save(cidade);
		List<Cidade> cidades = cidadeRepository.findAll();
		
		assertEquals(1, cidades.size());
		
		cidadeRepository.deleteAll();
	}
	
	@Test
	public void deveAcharPorTelefone() {
		cidadeRepository.save(cidade);
		Cidade cidadeBusca = cidadeRepository.findByNome("cidade01");
		
		assertEquals(cidade, cidadeBusca);
		
		cidadeRepository.deleteAll();
	}

}
