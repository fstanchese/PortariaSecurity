<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!-- Barra superior com os menus de navegação -->
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<button class="navbar-toggle" type="button" data-toggle="collapse"
			data-target=".bs-navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<ul class="nav navbar-nav navbar-left">
			<li>
				<form action="${path}/sair" method="post">
					<input type="hidden" name="_csrf" value="${_csrf.token}">
					<button id="btn-sair" type="submit" class="btn btn-default">
						Sair</button>
				</form>
			</li>
		</ul>
		<nav class="collapse navbar-collapse bs-navbar-collapse"
			role="navigation">
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Portaria<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="visitantes">Visitantes</a></li>
							<li><a href="correspondencias">Correspondências</a></li>
						</ul>
					</li>
				</sec:authorize>
				<sec:authorize access="hasRole('ADMIN')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Cadastros<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="unidades">Unidades</a></li>
							<li><a href="moradores">Moradores</a></li>
							<li><a href="veiculos">Veiculos</a></li>
						</ul></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ADMIN')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Tabelas<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="andares">Andares</a></li>
							<li><a href="blocos">Blocos</a></li>
							<li><a href="cores">Cores</a></li>
							<li><a href="marcas">Marcas</a></li>
							<li><a href="modelos">Modelos</a></li>
							<li><a href="moradortipos">Tipos de Morador</a></li>
							<li><a href="unidadetipos">Tipos de Unidade</a></li>
							<li><a href="visitantetipos">Tipos de Visitante</a></li>
						</ul></li>
				</sec:authorize>
				<sec:authorize access="hasRole('DBA')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Sistema<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="funcionarios">Usuários</a></li>
							<li><a href="funcoes">Funções</a></li>
						</ul></li>
				</sec:authorize>
			</ul>
		</nav>
	</div>
</nav>