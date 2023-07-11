package br.com.biblioteca.service;

import java.util.List;
import java.util.Optional;

import br.com.biblioteca.model.entity.Livro;

public interface LivroService {
	
public List<Livro> obterTodos(Livro livroParamFiltro);
	
	public Optional<Livro> obterPorId(Integer id);
	
	public Livro salvar(Livro livro);
	
	public Livro atualizar(Livro livro);
	
	public void deletar(Livro livro);

}
