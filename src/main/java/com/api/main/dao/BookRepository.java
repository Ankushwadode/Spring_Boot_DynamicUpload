package com.api.main.dao;

import org.springframework.data.repository.CrudRepository;

import com.api.main.entities.Book;

public interface BookRepository  extends CrudRepository<Book,Integer>{
	public Book findById(int id);
}
