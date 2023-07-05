package br.com.biblioteca.model.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name="t_livro", schema = "public")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_livro")
	private Integer id;

	@Column(name="nm_titulo", nullable = false)
	private String titulo;
	
	@Column(name="nm_autor", nullable = false)
	private String autor;
	
	@Column(name="nm_editora", nullable = false)
	private String editora;
	
	@Column(name="dt_publicacao", nullable = false)
	private Date publicacao;
	
	@Column(name="nr_isbn", nullable = false)
	private String isbn;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Livro livro = (Livro) o;
		return Objects.equals(id, livro.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
