package br.com.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> refs/remotes/origin/master

import br.com.biblioteca.ExceptionHandler.RegraNegocioException;
import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.model.entity.Livro;
import br.com.biblioteca.service.LivroService;

<<<<<<< HEAD

=======
>>>>>>> refs/remotes/origin/master
@RestController
@RequestMapping("/api/v1/livros")
public class LivroController {
	
	private LivroService service;
	
	public LivroController(LivroService service) {
		this.service = service;
	}
	
<<<<<<< HEAD

=======
	private Livro converter(LivroDTO dto) {
		Livro livro = new Livro();
		
		livro.setTitulo(dto.getTitulo());
		livro.setAutor(dto.getAutor());
		livro.setEditora(dto.getEditora());
		livro.setPublicacao(dto.getPublicacao());
		livro.setIsbn(dto.getIsbn());
		
		return livro;
	}
	
>>>>>>> refs/remotes/origin/master
	@GetMapping("/buscar")
	public ResponseEntity<Object> buscar(
			@RequestParam(value="titulo", required=false) String titulo,
			@RequestParam(value="autor", required=false) String autor,
			@RequestParam(value="editora", required=false) String editora
			) {
		Livro livroFiltro = new Livro();
		livroFiltro.setTitulo(titulo);
		livroFiltro.setAutor(autor);
		livroFiltro.setEditora(editora);
		List<Livro> livros = service.obterTodos(livroFiltro);
		return ResponseEntity.ok(livros);
	}

	@PostMapping("/salvar")
	public ResponseEntity salvar(@RequestBody LivroDTO dto) {

		try {
<<<<<<< HEAD
			Livro entidadeLivro = converter(dto);

			entidadeLivro = service.salvar(entidadeLivro);
			return ResponseEntity.ok(entidadeLivro);

=======
			Livro livro = converter(dto);			
			livro = service.salvar(livro);
			return ResponseEntity.ok(livro);
>>>>>>> refs/remotes/origin/master
		} catch (RegraNegocioException regraNegocioException) {
			return ResponseEntity.badRequest().body(regraNegocioException.getMessage());
		}
	}
<<<<<<< HEAD

	@PutMapping(value = "/atualizarLivro/")
	public ResponseEntity atualizaLivro(@RequestBody LivroDTO livroDTO) {

		return service.obterPorId(livroDTO.getId()).map(entity -> {
			try {
				Livro livro = preencherLivroUpdate(livroDTO);
=======
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<? extends Object> atualizar(@PathVariable("id") Integer id, @RequestBody LivroDTO dto) {
		return service.obterPorId(id).map(entity -> {
			try {
				Livro livro = converter(dto);
				livro.setId(entity.getId());
>>>>>>> refs/remotes/origin/master
				service.atualizar(livro);
				return ResponseEntity.ok(livro);
			} catch (RegraNegocioException regraNegocioException) {
				return ResponseEntity.badRequest().body(regraNegocioException.getMessage());
			}
<<<<<<< HEAD
		}).orElseGet(() -> ResponseEntity.badRequest().body("Falha ao atualizar o livro."));
	}

	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity deletar(@PathVariable("id") Integer id) {
		return service.obterPorId(id).map(entity -> {
			service.deletar(entity);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> ResponseEntity.badRequest().body("O id do produto informado não pode ser deletado."));
	}


	private Livro converter(LivroDTO dto) {
		Livro livro = new Livro();

		livro.setTitulo(dto.getTitulo());
		livro.setAutor(dto.getAutor());
		livro.setEditora(dto.getEditora());
		livro.setIsbn(dto.getIsbn());
		livro.setPublicacao(dto.getPublicacao());

		return livro;
	}

	private Livro preencherLivroUpdate(LivroDTO livroDTO) {
		Livro livro = service.obterPorId(livroDTO.getId()).get();

		if (livroDTO != null) {
			if (livroDTO.getTitulo() != null) {
				livro.setTitulo(livroDTO.getTitulo());
			}
			if (livroDTO.getAutor() != null) {
				livro.setAutor(livroDTO.getAutor());
			}
			if (livroDTO.getEditora() != null) {
				livro.setEditora(livroDTO.getEditora());
			}
			if (livroDTO.getPublicacao() != null) {
				livro.setPublicacao(livroDTO.getPublicacao());
			}
			if (livroDTO.getIsbn() != null) {
				livro.setIsbn(livroDTO.getIsbn());
			}
		}
		return livro;
	}

=======
		}).orElseGet(() -> ResponseEntity.badRequest().body("O livro não foi encontrado na base de dados"));
	}
	
	@DeleteMapping("/deletar/{id}")
	public Optional<Object> deletar(@PathVariable("id") Integer id) {
		return service.obterPorId(id).map(entity -> {
			service.deletar(entity);
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		});
	}
>>>>>>> refs/remotes/origin/master
}
