package br.com.biblioteca.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.biblioteca.ExceptionHandler.RegraNegocioException;
import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.model.entity.Livro;
import br.com.biblioteca.service.LivroService;



@RestController
@RequestMapping("/api/v1/livros")
public class LivroController {
	
	LivroService service;
	
	public LivroController(LivroService service) {
		this.service = service;
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
	public ResponseEntity salvar(@RequestBody LivroDTO dto) {

		try {
			Livro entidadeLivro = converter(dto);

			entidadeLivro = service.salvar(entidadeLivro);
			return ResponseEntity.ok(entidadeLivro);

		} catch (RegraNegocioException regraNegocioException) {
			return ResponseEntity.badRequest().body(regraNegocioException.getMessage());
		}
	}

	@PutMapping(value = "/atualizarLivro/")
	public ResponseEntity atualizaLivro(@RequestBody LivroDTO livroDTO) {

		return service.obterPorId(livroDTO.getId()).map(entity -> {
			try {
				Livro livro = preencherLivroUpdate(livroDTO);
				service.atualizar(livro);
				return ResponseEntity.ok(livro);
			} catch (RegraNegocioException regraNegocioException) {
				return ResponseEntity.badRequest().body(regraNegocioException.getMessage());
			}
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

}
