package br.com.stanchese.portaria.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "visitante")
public class Visitante implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@NotNull(message="O campo Unidade é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="unidade_id",nullable=false)
	@JsonManagedReference
	private Unidade unidade;
	
	@NotNull(message = "O campo Tipo de Visitante é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "visitantetipo_id")
	@JsonManagedReference
	private VisitanteTipo visitanteTipo;	
	
	@NotEmpty(message = "O campo Nome é obrigatório.")
	@Size(max = 100, message = "Tamanho máximo 100 caracteres.")
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Column(name="documento",length = 20, nullable = true)
	private String documento;
	
	@Column(name="placa", length=8 ,nullable=true)
	private String placa;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "modelo_id")
	@JsonManagedReference
	private Modelo modelo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cor_id")
	@JsonManagedReference
	private Cor cor;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="entrada", nullable = true)
	private Date entrada;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="saida", nullable = true)
	private Date saida;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
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
	public Date getEntrada() {
		return entrada;
	}
	public void setEntrada(Date entrada2) {
		this.entrada = entrada2;
	}
	public Date getSaida() {
		return saida;
	}
	public void setSaida(Date saida) {
		this.saida = saida;
	}
	public void setVisitanteTipo(VisitanteTipo visitanteTipo) {
		this.visitanteTipo = visitanteTipo;
	}
	public VisitanteTipo getVisitanteTipo() {
		return visitanteTipo;
	}
	
	@Override
	public String toString() {
		return "Visitante [id=" + id + ", nome=" + nome + ", documento=" + documento + ", placa=" + placa + ", modelo="
				+ modelo + ", cor=" + cor + ", entrada=" + entrada + ", saida=" + saida + "]";
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
		Visitante other = (Visitante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
