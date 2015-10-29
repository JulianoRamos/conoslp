package br.com.conoslp.dao.impl;

import javax.ejb.Stateless;

import br.com.conoslp.dao.api.GrupoDao;
import br.com.conoslp.dao.impl.generic.GenericDaoImpl;
import br.com.conoslp.entidades.Grupo;

@Stateless(name = "grupoDao")
public class GrupoDaoImpl extends GenericDaoImpl<Grupo> implements GrupoDao {

}
