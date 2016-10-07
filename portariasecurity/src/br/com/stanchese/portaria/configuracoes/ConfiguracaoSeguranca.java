package br.com.stanchese.portaria.configuracoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.stanchese.portaria.modelo.servicos.AutenticacaoServico;

@Configuration 
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	private AutenticacaoServico servicoAutenticacao;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(servicoAutenticacao)
		.passwordEncoder(encoder());		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/andares","/blocos","/cores","/funcionarios","/funcoes","/marcas",
					"/modelos","/moradores","/moradortipos","/unidades","/unidadetipos","/visitantetipos","/veiculos")
			.hasAnyRole("ADMIN","DBA","USER")
			.anyRequest().permitAll()
		.and()
			.formLogin()
			.loginPage("/login.jsp")
			.loginProcessingUrl("/autenticar")
			.defaultSuccessUrl("/menuprincipal.jsp")
			.failureUrl("/login.jsp?semacesso=true")
			.usernameParameter("usuario")
			.passwordParameter("senha")
		.and()
			.logout()
			.logoutUrl("/sair")
			.logoutSuccessUrl("/login.jsp?saiu=true");
	}
	
	@Bean
	public BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("123456"));
	}
}
