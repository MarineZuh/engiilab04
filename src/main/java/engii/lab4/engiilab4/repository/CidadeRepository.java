package engii.lab4.engiilab4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import engii.lab4.engiilab4.model.Cidade;
import engii.lab4.engiilab4.model.Cliente;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
	Cidade findByNome(String nome);
	
	
}
