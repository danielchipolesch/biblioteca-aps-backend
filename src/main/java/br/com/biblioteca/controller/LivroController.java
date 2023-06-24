package br.com.biblioteca.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	LivroService service;
	
	public LivroController(LivroService service) {
		this.service = service;
	}
	
	private Livro converter(LivroDTO dto) {
		Livro livro = new Livro();
		
		livro.setTitulo(dto.getTitulo());
		livro.setAutor(dto.getAutor());
		livro.setEditora(dto.getEditora());
		
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
			Livro entidadeLivro = converter(dto);
			
			entidadeLivro = service.salvar(entidadeLivro);
			return ResponseEntity.ok(entidadeLivro);
			
		} catch (RegraNegocioException regraNegocioException) {
			return ResponseEntity.badRequest().body(regraNegocioException.getMessage());
		}
		
	}
	
}
