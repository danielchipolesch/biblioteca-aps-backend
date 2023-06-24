package br.com.biblioteca.service.implementation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.biblioteca.model.entity.Livro;
import br.com.biblioteca.model.reposity.LivroRepository;
import br.com.biblioteca.service.LivroService;
import jakarta.transaction.Transactional;

@Service
public class LivroServiceImpl implements LivroService{

	@Autowired
	private LivroRepository livroRepository;
	
	public LivroServiceImpl(LivroRepository livroRepository) {
		this.livroRepository=livroRepository;
	}
	
	@Override
	@Transactional
	public List<Livro> obterTodos(Livro livroParamFiltro) {
		Example<Livro> example = Example.of(livroParamFiltro);
		return livroRepository.findAll(example);
	}

	@Override
	@Transactional
	public Optional<Livro> obterPorId(Integer id) {
		return livroRepository.findById(id);
	}

	@Override
	@Transactional
	public Livro salvar(Livro livro) {
		return livroRepository.save(livro);
	}

	@Override
	public Livro atualizar(Livro livro) {
		Objects.requireNonNull(livro.getId());
		return livroRepository.save(livro);
	}

	@Override
	public void deletar(Livro livro) {
		Objects.requireNonNull(livro.getId());
		livroRepository.delete(livro);
	}
	
	

}
