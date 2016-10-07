package br.com.stanchese.portaria.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="modelo")
public class Modelo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@NotEmpty(message="O campo Modelo é obrigatório.")
	@Size(max=45,message="Tamanho máximo 45 caracteres.")
	@Column(name="descricao",length=45,nullable=false,unique=true)
	private String descricao;
	
	@NotNull(message="O campo Marca � obrigat�rio.")
	@ManyToOne(fetch=FetchType.LAZY)
	private Marca marca;
	
//	@OneToMany(mappedBy = "modelo",targetEntity = Veiculo.class)
//	private List<Veiculo> veiculos;
//
//	@OneToMany(mappedBy = "modelo",targetEntity = Visitante.class)
//	private List<Veiculo> visitantes;		

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Marca getMarca() {
		return this.marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
//	public List<Veiculo> getVeiculos() {
//		return veiculos;
//	}
//	public void setVeiculos(List<Veiculo> veiculos) {
//		this.veiculos = veiculos;
//	}
//	public void setVisitantes(List<Veiculo> visitantes) {
//		this.visitantes = visitantes;
//	}
//	public List<Veiculo> getVisitantes() {
//		return visitantes;
//	}

	@Override
	public String toString() {
		return "Modelo [id=" + id + ", descricao=" + descricao + ", marca=" + marca + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Modelo other = (Modelo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
