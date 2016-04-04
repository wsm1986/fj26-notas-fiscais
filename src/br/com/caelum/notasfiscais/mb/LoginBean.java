package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDAO;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named
@javax.enterprise.context.RequestScoped
public class LoginBean implements Serializable {
	private Usuario usuario = new Usuario();

	@Inject
	UsuarioLogadoBean usuarioLogadoBean;
	@Inject
	UsuarioDAO dao;

	public String efetuarLogin() {

		Boolean loginValido = dao.existe(usuario);
		if (loginValido) {
			usuarioLogadoBean.setUsuario(usuario);
			return "produto?faces-redirect=true";
		} else {
			usuarioLogadoBean.setUsuario(null);
			return "login";
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean isLogado() {
		return usuarioLogadoBean.isLogado();
	}

	public String logOut() {
		usuario.setLogin(null);
		;
		return "login";
	}
}
