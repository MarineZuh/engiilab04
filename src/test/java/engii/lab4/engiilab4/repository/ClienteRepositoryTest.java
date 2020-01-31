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

import engii.lab4.engiilab4.model.Cliente;


@DataJpaTest
public class ClienteRepositoryTest {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	private Cliente cliente;
	
	@BeforeEach
	public void start() {
	  cliente = new Cliente();
	  cliente.setEndereco("endereco01");
	  cliente.setNome("cliente01");
	  cliente.setTelefone("0000-0000");
	}
	
	@Test
	public void saveComNomeNullDeveLancarException() {
		cliente.setNome(null);
		
		ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> clienteRepository.save(cliente));
		ConstraintViolation c = (ConstraintViolation) exception.getConstraintViolations().toArray()[0];
		
		assertEquals("Nome deve ser informado", c.getMessage());
	}
	@Test
	public void saveComEnderecoNullDeveLancarException() {
		cliente.setEndereco(null);
		
		ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> clienteRepository.save(cliente));
		ConstraintViolation c = (ConstraintViolation) exception.getConstraintViolations().toArray()[0];
		
		assertEquals("Endereco deve ser informado", c.getMessage());
	}
	@Test
	public void saveComTelefoneNullDeveLancarException() {
		cliente.setTelefone(null);
		
		ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> clienteRepository.save(cliente));
		ConstraintViolation c = (ConstraintViolation) exception.getConstraintViolations().toArray()[0];
		
		assertEquals("Telefone deve ser informado", c.getMessage());
	}
	@Test
	public void deveSalvar() {
		clienteRepository.save(cliente);
		List<Cliente> clientes = clienteRepository.findAll();
		
		assertEquals(1, clientes.size());
		
		clienteRepository.deleteAll();
	}
	
	@Test
	public void deveAcharPorTelefone() {
		clienteRepository.save(cliente);
		Cliente clienteBusca = clienteRepository.findByTelefone("0000-0000");
		
		assertEquals(cliente, clienteBusca);
		
		clienteRepository.deleteAll();
	}

}
