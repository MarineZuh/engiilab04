package engii.lab4.engiilab4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import engii.lab4.engiilab4.exception.CidadeException;
import engii.lab4.engiilab4.exception.ClienteException;
import engii.lab4.engiilab4.model.Cidade;
import engii.lab4.engiilab4.model.Cliente;
import engii.lab4.engiilab4.model.CountCidade;
import engii.lab4.engiilab4.model.Frete;
import engii.lab4.engiilab4.repository.CidadeRepository;
import engii.lab4.engiilab4.repository.ClienteRepository;
import engii.lab4.engiilab4.repository.FreteRepository;

@Service
public class FreteService {

	@Autowired
	private FreteRepository freteRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	public Frete inserir(Frete frete) throws Exception{
		Cidade cidade = null;
		Cliente cliente = null;
		
		if(frete.getCidade() != null){			
			cidade = cidadeRepository.findById(frete.getCidade().getCodigoCidade()).orElse(null);
			if(cidade == null) throw new CidadeException("Cidade não encontrada");
			frete.setCidade(cidade);
		}
		
		if(frete.getCliente() != null){			
			cliente = clienteRepository.findById(frete.getCliente().getCodigoCliente()).orElse(null);
			if(cliente == null) throw new ClienteException("Cliente não cadastrado");
			frete.setCliente(cliente);
		}
		
		frete.setValor(getValorFrete(frete));
		return freteRepository.save(frete);
		
	}
	
	public void deletar(int id) {
		this.freteRepository.deleteById(id);
	}
	
	public List<Frete> todos(){
		return this.freteRepository.findAll();
	}
	
	/**
	 *  peso multiplicado por um valor fixo (por exemplo R$ 10,00), 
	 *  acrescido da taxa de entrega associada a cada cidade de destino
	 */
	public double getValorFrete(Frete frete) {
		double taxaFixa = 10.0;
		
		double valor = frete.getPeso() * taxaFixa + frete.getCidade().getTaxa();
		return valor;
	}
	
	public List<Frete> freteComMaiorValor() {
		return freteRepository.achaFreteComMaiorValor();
	}
	
	public Cidade cidadesComMaiorQntFrete(){
//		List<Cidade> cidades = new ArrayList<Cidade>();
		List<CountCidade> cidadesCount = freteRepository.cidadeComMaiorQntFretes(PageRequest.of(0, 1));
		
//		for(CountCidade c : cidadesCount) {
//			cidades.add(c.cidade);
//		}
		if(cidadesCount.size() > 0) {
			return cidadesCount.get(0).cidade;
		}
		return null;
	}
	
	public Frete acharPorId(int id) {
		return freteRepository.findById(id).orElse(null);
	}
}
