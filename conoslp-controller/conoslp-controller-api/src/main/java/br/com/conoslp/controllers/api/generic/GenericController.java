package br.com.conoslp.controllers.api.generic;

import java.util.List;

public interface GenericController<T> {

	void excluir(T t);

	void salvar(T t);

	void alterar(T t);

	List<T> buscaTodos();

	T pesquisarPorCodigo(Integer id);
}
