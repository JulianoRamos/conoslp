package br.com.conoslp.controllers.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

import br.com.conoslp.controllers.api.UsuarioController;
import br.com.conoslp.dao.api.GrupoDao;
import br.com.conoslp.dao.api.UsuarioDao;
import br.com.conoslp.entidades.Grupo;
import br.com.conoslp.entidades.Usuario;

@Stateless(name = "usuarioController")
@ManagedBean(name = "usuarioController")
public class UsuarioControllerImpl implements UsuarioController {

	@EJB
	private UsuarioDao usuarioDao;

	@EJB
	private GrupoDao grupoDao;

	private Usuario usuario = new Usuario();

	private List<String> nomesDosGrupos;

	private List<Usuario> usuarios;

	private List<Grupo> grupos;

	public void adiciona() throws NoSuchAlgorithmException {
		// Associando os Grupos ao novo Usuário
		for (String nomeDoGrupo : this.nomesDosGrupos) {
			Grupo g = new Grupo();
			g.setNome(nomeDoGrupo);
			this.usuario.getGrupos().add(g);
		}

		// Criptografando a senha do novo Usuário
		MessageDigest md = MessageDigest.getInstance("MD5"); // or "SHA -1"
		md.update(this.usuario.getSenha().getBytes());
		BigInteger hash = new BigInteger(1, md.digest());
		String senhaCriptografada = hash.toString(16);
		while (senhaCriptografada.length() < 32) { // 40 for SHA -1
			senhaCriptografada = "0" + senhaCriptografada;
		}
		this.usuario.setSenha(senhaCriptografada);

		// Salvando o usuário
		this.usuarioDao.salvar(this.usuario);
		this.usuario = new Usuario();
		this.usuarios = null;
	}

	public List<Grupo> getGrupos() {
		if (this.grupos == null) {
			this.grupos = this.grupoDao.buscaTodos();
		}

		return this.grupos;
	}

	public List<Usuario> getUsuarios() {
		if (this.usuarios == null) {
			this.usuarios = this.usuarioDao.buscaTodos();
		}

		return this.usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<String> getNomesDosGrupos() {
		return nomesDosGrupos;
	}

	public void setNomesDosGrupos(List<String> nomesDosGrupos) {
		this.nomesDosGrupos = nomesDosGrupos;
	}

	public void excluir(Usuario t) {
		this.excluir(t);
	}

	public void salvar(Usuario t) {
		this.salvar(t);
	}

	public void alterar(Usuario t) {
		this.alterar(t);
	}

	public List<Usuario> buscaTodos() {
		return this.buscaTodos();
	}

	public Usuario pesquisarPorCodigo(Integer id) {
		return this.pesquisarPorCodigo(id);
	}

}
