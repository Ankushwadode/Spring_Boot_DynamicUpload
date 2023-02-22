package com.api.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.main.dao.BookRepository;
import com.api.main.entities.Book;

@Component
public class BookService {
	
	@Autowired
	private BookRepository br;
	
//	getting all books 
	public List<Book> getAll(){
		List<Book> list =(List<Book>) this.br.findAll();
		return list;
	}
	
//	getting book by id
	public Book getById(int id) {
		Book book=null;
		try {
			book = this.br.findById(id);		
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		return book;
	}
	
//	adding book
	public Book addBook(Book book) {
		Book result=br.save(book);
		return result;
	}

//	delete book
	public void deleteBook(int bid) {
		br.deleteById(bid);
	}

//	update book
	public void updateBook(Book book, int bid) {
		book.setPid(bid);
		br.save(book);
		}
	}
	
