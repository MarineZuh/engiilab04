package engii.lab4.engiilab4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoCliente;
	
	@Column(length = 30, nullable = false)
	@Size(max = 30)
	@NotBlank(message = "Nome deve ser informado")
	private String nome;
	
	@Column(length = 30, nullable = false)
	@Size(max = 30)
	@NotBlank(message = "Endereco deve ser informado")
	private String endereco;
	
	@Column(length = 30, nullable = false)
	@Size(max = 30)
	@NotBlank(message = "Telefone deve ser informado")
	private String telefone;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	public int getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoCliente;
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
		Cliente other = (Cliente) obj;
		if (codigoCliente != other.codigoCliente)
			return false;
		return true;
	}
	
	
	
}
