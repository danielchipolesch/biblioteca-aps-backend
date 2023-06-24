package br.com.biblioteca.model.reposity;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.model.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer>{
	
}
