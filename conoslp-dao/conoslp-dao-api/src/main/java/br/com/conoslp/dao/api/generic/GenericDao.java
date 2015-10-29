package br.com.conoslp.dao.api.generic;

import java.util.List;

public interface GenericDao<T> {

	T salvar(T t);

	void alterar(T t);

	void deletar(Integer id);

	T pesquisarPorCodigo(Integer id);

	List<T> buscaTodos();
}