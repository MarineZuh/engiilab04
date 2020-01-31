package engii.lab4.engiilab4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoCidade;
	
	@Column(length = 30, nullable = false)
	@Size(max = 30)
	@NotBlank(message = "Nome deve ser informado")
	private String nome;
	
	@Column(length = 30, nullable = false)
	@Size(max = 30)
	@NotBlank(message = "UF deve ser informado")
	private String uf;
	
	@Column(precision = 4, scale = 2, nullable = false)
	@DecimalMin(value = "0.0", message = "Taxa deve ser maior ou igual a 0.0")
	private double taxa;
	
	public Cidade() {
		// TODO Auto-generated constructor stub
	}	
	
	public int getCodigoCidade() {
		return codigoCidade;
	}
	public void setCodigoCidade(int codigoCidade) {
		this.codigoCidade = codigoCidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public double getTaxa() {
		return taxa;
	}
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoCidade;
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
		Cidade other = (Cidade) obj;
		if (codigoCidade != other.codigoCidade)
			return false;
		return true;
	}
	
	
	
}
