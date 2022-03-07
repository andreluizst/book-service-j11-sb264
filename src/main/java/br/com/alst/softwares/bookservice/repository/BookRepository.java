package br.com.alst.softwares.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alst.softwares.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
