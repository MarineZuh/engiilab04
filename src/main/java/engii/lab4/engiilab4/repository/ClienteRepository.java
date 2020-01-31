package engii.lab4.engiilab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import engii.lab4.engiilab4.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	Cliente findByTelefone(String telefone);

}
