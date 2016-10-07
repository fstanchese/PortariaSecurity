package br.com.stanchese.portaria.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@NotEmpty(message = "O campo Placa é obrigatório.")
	@Column(name="placa", length=8 ,nullable=false)
	private String placa;

	@NotNull(message = "O campo Ano de Fabricação é obrigatório.")
	@Column(name="ano", length=4 ,nullable=false)
	@Min(value=1900)
	private Integer ano;
	
	@NotNull(message = "O campo Morador é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "morador_id")
	@JsonManagedReference
	private Morador morador;
	
	@NotNull(message = "O campo Modelo é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "modelo_id")
	@JsonManagedReference
	private Modelo modelo;
	
	@NotNull(message = "O campo Cor é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cor_id")
	@JsonManagedReference
	private Cor cor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getAno() {
		return ano;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Morador getMorador() {
		return morador;
	}
	public void setMorador(Morador morador) {
		this.morador = morador;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public Cor getCor() {
		return cor;
	}
	public void setCor(Cor cor) {
		this.cor = cor;
	}
	
	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", placa=" + placa + ", morador=" + morador + ", modelo=" + modelo + ", cor=" + cor
				+ "]";
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
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
