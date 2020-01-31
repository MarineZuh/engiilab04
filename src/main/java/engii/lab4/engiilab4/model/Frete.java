package engii.lab4.engiilab4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Frete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoFrete;
	
	@ManyToOne
	@JoinColumn(name = "codigo_cidade", nullable = false)
	@NotNull(message = "Cidade deve ser informada")
	private Cidade cidade;
	
	@ManyToOne
	@JoinColumn(name = "codigo_cliente", nullable = false)
	@NotNull(message = "Cliente deve ser informado")
	private Cliente cliente;
	
	@Column(length = 30, nullable = false)
	@Size(max = 30)
	@NotNull(message = "Descricao deve ser informada")
	private String descricao;
	
	@Column(precision = 4, scale = 2, nullable = false)
	@DecimalMin(value = "0.0", message = "Peso deve ser maior ou igual a 0.0")
	private double peso;
	
	@Column(precision = 4, scale = 2, nullable = false)
	@DecimalMin(value = "0.0", message = "Valor deve ser maior ou igual a 0.0")
	private double valor;
	
	public Frete() {
		// TODO Auto-generated constructor stub
	}
	
	public int getCodigoFrete() {
		return codigoFrete;
	}
	public void setCodigoFrete(int codigoFrete) {
		this.codigoFrete = codigoFrete;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoFrete;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Frete other = (Frete) obj;
		if (codigoFrete != other.codigoFrete)
			return false;
		return true;
	}
	
	
	
	
}
