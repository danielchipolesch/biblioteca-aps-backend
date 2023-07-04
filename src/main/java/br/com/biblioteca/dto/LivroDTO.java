package br.com.biblioteca.dto;

import java.sql.Date;

public class LivroDTO {
	
<<<<<<< HEAD:src/main/java/br/com/biblioteca/dto/LivroDTO.java
	private Integer id;
=======

	private Long id;
>>>>>>> refs/remotes/origin/master:src/main/java/br/com/biblioteca/DTO/LivroDTO.java
	
	private String titulo;
	
	private String autor;
	
	private String editora;
	
	private Date publicacao;
	
	private String isbn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Date getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Date publicacao) {
		this.publicacao = publicacao;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
}
