package engii.lab4.engiilab4.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import engii.lab4.engiilab4.model.Cidade;
import engii.lab4.engiilab4.model.Cliente;
import engii.lab4.engiilab4.model.CountCidade;
import engii.lab4.engiilab4.model.Frete;

public interface FreteRepository extends JpaRepository<Frete, Integer>{
	List<Frete> findByClienteOrderByValorDesc(Cliente cliente );
	
	@Query(value = "SELECT f FROM Frete f WHERE f.valor = (SELECT MAX(ff.valor) FROM Frete ff  )")
	List<Frete> achaFreteComMaiorValor();

	@Query(value = "SELECT DISTINCT new  engii.lab4.engiilab4.model.CountCidade(f.cidade, count(*) as c) FROM Frete f GROUP BY f.cidade ORDER BY c DESC ")
	List<CountCidade> cidadeComMaiorQntFretes(Pageable page);
}

