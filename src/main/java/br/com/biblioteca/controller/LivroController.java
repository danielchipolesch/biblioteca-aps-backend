package br.com.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.DTO.LivroDTO;
import br.com.biblioteca.ExceptionHandler.RegraNegocioException;
import br.com.biblioteca.model.entity.Livro;
import br.com.biblioteca.service.LivroService;

@RestController
@RequestMapping("/api/v1/livros")
public class LivroController {
	
	private LivroService service;
	
	public LivroController(LivroService service) {
		this.service = service;
	}
	
	private Livro converter(LivroDTO dto) {
		Livro livro = new Livro();
		
		livro.setTitulo(dto.getTitulo());
		livro.setAutor(dto.getAutor());
		livro.setEditora(dto.getEditora());
		livro.setPublicacao(dto.getPublicacao());
		livro.setIsbn(dto.getIsbn());
		
		return livro;
	}
	
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
	public ResponseEntity<Object> salvar(@RequestBody LivroDTO dto) {
		
		try {
			Livro livro = converter(dto);			
			livro = service.salvar(livro);
			return ResponseEntity.ok(livro);
		} catch (RegraNegocioException regraNegocioException) {
			return ResponseEntity.badRequest().body(regraNegocioException.getMessage());
		}
		
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<? extends Object> atualizar(@PathVariable("id") Integer id, @RequestBody LivroDTO dto) {
		return service.obterPorId(id).map(entity -> {
			try {
				Livro livro = converter(dto);
				livro.setId(entity.getId());
				service.atualizar(livro);
				return ResponseEntity.ok(livro);
			} catch (RegraNegocioException regraNegocioException) {
				return ResponseEntity.badRequest().body(regraNegocioException.getMessage());
			}
		}).orElseGet(() -> ResponseEntity.badRequest().body("O livro não foi encontrado na base de dados"));
	}
	
	@DeleteMapping("/deletar/{id}")
	public Optional<Object> deletar(@PathVariable("id") Integer id) {
		return service.obterPorId(id).map(entity -> {
			service.deletar(entity);
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		});
	}
}
