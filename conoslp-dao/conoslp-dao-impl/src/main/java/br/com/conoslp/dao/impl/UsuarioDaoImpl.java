package br.com.conoslp.dao.impl;

import javax.ejb.Stateless;

import br.com.conoslp.dao.api.UsuarioDao;
import br.com.conoslp.dao.impl.generic.GenericDaoImpl;
import br.com.conoslp.entidades.Usuario;

@Stateless(name = "usuarioDao")
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario> implements UsuarioDao {

}
