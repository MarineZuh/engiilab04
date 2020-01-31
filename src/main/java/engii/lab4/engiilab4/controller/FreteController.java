package engii.lab4.engiilab4.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import engii.lab4.engiilab4.exception.CidadeException;
import engii.lab4.engiilab4.exception.ClienteException;
import engii.lab4.engiilab4.model.Frete;
import engii.lab4.engiilab4.service.FreteService;

@RestController
@RequestMapping("/frete")
public class FreteController {
	
	@Autowired
	private FreteService freteService;
	
	@GetMapping()
	public ResponseEntity<List<Frete>> fretes(){
		List<Frete> fretes = freteService.todos();
		return ResponseEntity.ok(fretes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Frete> frete(@PathVariable Integer id){
		Frete frete = freteService.acharPorId(id);
		if(frete == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(frete);
	}
	
	@PostMapping()
	public ResponseEntity<Frete> inserir(@RequestBody @Valid Frete frete) throws URISyntaxException, CidadeException, ClienteException, Exception{
		try {
			Frete freteSalvo = freteService.inserir(frete);
			return new ResponseEntity<>(freteSalvo, HttpStatus.CREATED);
		} catch (CidadeException e) {
			throw e;
		} catch (ClienteException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}	
	}
	
	@PutMapping("/{id}") 
	public ResponseEntity<Frete> alterar(@PathVariable Integer id, @RequestBody @Valid Frete frete)  throws URISyntaxException, CidadeException, ClienteException, Exception{
		try {
			Frete freteSalvo = freteService.inserir(frete);
			return new ResponseEntity<>(freteSalvo, HttpStatus.CREATED);
		} catch (CidadeException e) {
			throw e;
		} catch (ClienteException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Frete> remover(@PathVariable Integer id) {
		freteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
