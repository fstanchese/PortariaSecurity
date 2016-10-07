package br.com.stanchese.portaria.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="funcionario")
public class Funcionario implements Serializable,UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;	
	
	@NotEmpty(message="O campo Nome é obrigatório.")
	@Size(max=100,message="Tamanho máximo 100 caracteres.")
	@Column(name="nome",length=100,nullable=false)
	private String nome;

	@Size(max=40,message="Tamanho máximo 40 caracteres.")
	@Column(name="login",length=40,nullable=true)
	private String login;

	@NotNull
	@NotEmpty
	@Column(name="senha",length=60,nullable=true)
	private String senha;
	
	@NotNull(message="O campo Funcao é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="funcao_id",nullable=false)
	private Funcao funcao;	

	@NotNull(message="O campo Condominio é obrigatório.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="condominio_id",nullable=true)
	@JsonManagedReference
	private Condominio condominio;	

	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Permissao> permissoes;
	
	@Transient
	private String confirmacaoSenha;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	
	public Condominio getCondominio() {
		return condominio;
	}

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}
	
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", funcao=" + funcao + ", login=" + login + ", senha="
				+ senha + "]";
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> autorizacoes = new ArrayList<GrantedAuthority>();

		for (Permissao permissao : getPermissoes()) {
			autorizacoes.add(new SimpleGrantedAuthority(permissao.getNome()));
		}
		return autorizacoes;
	}
	
	@Override
	public String getPassword() {
		return senha;
	}
	@Override
	public String getUsername() {
		return login;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
			
}
