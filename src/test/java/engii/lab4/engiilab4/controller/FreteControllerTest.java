//package engii.lab4.engiilab4.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.List;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//
//import engii.lab4.engiilab4.model.Cidade;
//import engii.lab4.engiilab4.model.Cliente;
//import engii.lab4.engiilab4.model.Frete;
//import engii.lab4.engiilab4.repository.CidadeRepository;
//import engii.lab4.engiilab4.repository.ClienteRepository;
//import engii.lab4.engiilab4.repository.FreteRepository;
//
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//public class FreteControllerTest {
//	@Autowired
//	private TestRestTemplate testRestTemplate;
//
//	@Autowired
//	private ClienteRepository clienteRepository;
//
//	@Autowired
//	private CidadeRepository cidadeRepository;
//
//	@Autowired
//	private FreteRepository freteRepository;
//
//	private Frete frete;
//	private Cidade cidade;
//	private Cliente cliente;
//
//	@BeforeEach
//	public void start() {
//
//		cidade = new Cidade();
//		cidade.setNome("cidade01");
//		cidade.setTaxa(20);
//		cidade.setUf("MA");
//		cidade = cidadeRepository.save(cidade);
//
//		cliente = new Cliente();
//		cliente.setEndereco("endereco01");
//		cliente.setNome("cliente01");
//		cliente.setTelefone("0000-0000");
//		cliente = clienteRepository.save(cliente);
//
//		frete = new Frete();
//		frete.setCidade(cidade);
//		frete.setCliente(cliente);
//		frete.setDescricao("frete01");
//		frete.setPeso(0);
//		frete.setValor(0);
//		frete = freteRepository.save(frete);
//	}
//
//	@AfterEach
//	public void end() {
//		freteRepository.deleteAll();
//		cidadeRepository.deleteAll();
//		clienteRepository.deleteAll();
//	}
//
//	@Test
//	public void deveAcharTodosOsFretes() {
//		ResponseEntity<String> resposta = testRestTemplate.exchange("/frete/",
//				HttpMethod.GET, null, String.class);
//
//		System.err.println(resposta.getBody());
//		assertEquals(HttpStatus.OK, resposta.getStatusCode());
//	}
//
//	@Test
//	public void deveAcharTodosOsFretesUsandoList() {
//		ParameterizedTypeReference<List<Frete>> tipoRetorno = new ParameterizedTypeReference<List<Frete>>() {
//		};
//
//		ResponseEntity<List<Frete>> resposta = testRestTemplate
//				.exchange("/frete/", HttpMethod.GET, null, tipoRetorno);
//		System.err.println(resposta.getHeaders().getContentType());
//		
//		assertEquals(HttpStatus.OK, resposta.getStatusCode());
//		assertTrue(resposta.getHeaders().getContentType()
//				.equals(MediaType.parseMediaType("application/json")));
//		assertEquals(1, resposta.getBody().size());
//		assertEquals(frete, resposta.getBody().get(0));
//	}
//
//	@Test
//	public void deveAcharUmFretePorId() {
//		ResponseEntity<Frete> resposta = testRestTemplate.exchange(
//				"/frete/{id}", HttpMethod.GET, null, Frete.class,
//				frete.getCodigoFrete());
//
//		assertEquals(HttpStatus.OK, resposta.getStatusCode());
//		assertEquals(frete, resposta.getBody());
//	}
//	
//	@Test
//	public void deveRetornarErroAoBuscarFreteNaoExistente() {
//
//		ResponseEntity<Frete> resposta =
//				testRestTemplate.exchange("/frete/{id}",HttpMethod.GET,null, Frete.class,100 );
//
//		assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
//		assertNull(resposta.getBody());
//	}
//	
//	@Test
//	public void deveRetornarErroAoSalvar() {
//		Frete frete01 = new Frete();
//		HttpEntity<Frete> httpEntity = new HttpEntity<>(frete01);
//
//		ResponseEntity<List<String>> resposta =
//				testRestTemplate.exchange("/frete/",
//						                  HttpMethod.POST,httpEntity,
//						                  new ParameterizedTypeReference<List<String>>() {});
//		System.err.println(resposta.getBody());
//		assertEquals(HttpStatus.BAD_REQUEST,resposta.getStatusCode());
//		assertTrue(resposta.getBody().contains("Descricao deve ser informada"));
//		assertTrue(resposta.getBody().contains("Cidade deve ser informada"));
//		assertTrue(resposta.getBody().contains("Cliente deve ser informado"));
//	}
//	@Test
//	public void deveLancarCidadeException() {
//		//setup
//		freteRepository.deleteAll();
//		cidadeRepository.deleteAll();
//		
//		//test
//		HttpEntity<Frete> httpEntity = new HttpEntity<>(frete);
//
//		ResponseEntity<String> resposta =
//				testRestTemplate.exchange("/frete/",
//						                  HttpMethod.POST,httpEntity,
//						                  new ParameterizedTypeReference<String>() {});
//		System.err.println(resposta.getBody());
//		assertEquals(HttpStatus.BAD_REQUEST,resposta.getStatusCode());
//		assertTrue(resposta.getBody().contains("Cidade não encontrada"));
//		
//	}
//	@Test
//	public void deveLancarClienteException() {
//		//setup
//		freteRepository.deleteAll();
//		clienteRepository.deleteAll();
//		
//		//test
//		HttpEntity<Frete> httpEntity = new HttpEntity<>(frete);
//
//		ResponseEntity<String> resposta =
//				testRestTemplate.exchange("/frete/",
//						                  HttpMethod.POST,httpEntity,
//						                  new ParameterizedTypeReference<String>() {});
//		System.err.println(resposta.getBody());
//		assertEquals(HttpStatus.BAD_REQUEST,resposta.getStatusCode());
//		assertTrue(resposta.getBody().contains("Cliente não cadastrado"));		
//	}
//	
//	@Test
//	public void deveSalvarOFrete() {
//		//setup
//		Frete freteASerSalvo = new Frete();
//		freteASerSalvo.setCidade(cidade);
//		freteASerSalvo.setCliente(cliente);
//		freteASerSalvo.setDescricao("frete");
//		freteASerSalvo.setPeso(0);
//		freteASerSalvo.setValor(0);
//		
//		//test
//		HttpEntity<Frete> httpEntity = new HttpEntity<>(freteASerSalvo);
//
//		ResponseEntity<Frete> resposta =
//				testRestTemplate.exchange("/frete/",HttpMethod.POST,httpEntity, Frete.class);
//
//		assertEquals(HttpStatus.CREATED,resposta.getStatusCode());
//
//		Frete resultado = resposta.getBody();
//
//		assertNotNull(resultado.getCodigoFrete());
//		assertEquals(freteASerSalvo.getCidade().getCodigoCidade(), resultado.getCidade().getCodigoCidade());
//		assertEquals(freteASerSalvo.getCliente().getCodigoCliente(), resultado.getCliente().getCodigoCliente());
//		assertEquals(freteASerSalvo.getDescricao(), resultado.getDescricao());
//		assertEquals(freteASerSalvo.getPeso(), resultado.getPeso());
//
//	}
//	
//	@Test
//	public void deveRetornarErroAoAlterar() {
//		Frete frete01 = new Frete();
//		HttpEntity<Frete> httpEntity = new HttpEntity<>(frete01);
//		ResponseEntity<List<String>> resposta = 
//				testRestTemplate.exchange("/frete/{id}",HttpMethod.PUT
//						,httpEntity, new ParameterizedTypeReference<List<String>>() {},frete.getCodigoFrete());
//		
//		assertEquals(HttpStatus.BAD_REQUEST,resposta.getStatusCode());
//		assertTrue(resposta.getBody().contains("Descricao deve ser informada"));
//		assertTrue(resposta.getBody().contains("Cidade deve ser informada"));
//		assertTrue(resposta.getBody().contains("Cliente deve ser informado"));
//	}
//	
//	@Test
//	public void alterarDeveAlterarContato() {
//		frete.setDescricao("Frete atualizado");
//		HttpEntity<Frete> httpEntity = new HttpEntity<>(frete);
//		ResponseEntity<Frete> resposta = 
//				testRestTemplate.exchange("/frete/{id}",HttpMethod.PUT,httpEntity
//						, Frete.class,frete.getCodigoFrete());
//		
//		assertEquals(HttpStatus.CREATED,resposta.getStatusCode());
//		Frete resultado = resposta.getBody(); 
//		assertEquals(frete.getCidade().getCodigoCidade(), resultado.getCidade().getCodigoCidade());
//		assertEquals(frete.getCliente().getCodigoCliente(), resultado.getCliente().getCodigoCliente());
//		assertEquals(frete.getDescricao(), resultado.getDescricao());
//		assertEquals(frete.getPeso(), resultado.getPeso());
//
//	}
//	
//	@Test
//	public void deveRemoverUmFretePorId() {
//		ResponseEntity<Frete> resposta = 
//				testRestTemplate.exchange("/frete/{id}",HttpMethod.DELETE,null
//						, Frete.class, frete.getCodigoFrete());
//		
//		assertEquals(HttpStatus.NO_CONTENT,resposta.getStatusCode());
//		assertNull(resposta.getBody());
//	}
//
//}
