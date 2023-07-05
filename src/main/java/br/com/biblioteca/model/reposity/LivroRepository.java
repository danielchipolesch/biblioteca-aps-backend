package br.com.biblioteca.model.reposity;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.model.entity.Livro;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface LivroRepository extends JpaRepository<Livro, Integer>{
	
}
