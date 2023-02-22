package com.api.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.main.entities.Book;
import com.api.main.helper.FileUploadHelper;
import com.api.main.services.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private FileUploadHelper fuh;

//	gives all books 
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getbooks() {

		List<Book> list = bookService.getAll();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(list);
		}
	}

//	give book by id
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getbook(@PathVariable("id") int id) {
		Book book = bookService.getById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}

//	add the book
	@PostMapping("/books")
	public ResponseEntity<Book> addbook(@RequestBody Book book) {
		Book b = null;
		try {
			b = this.bookService.addBook(book);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	delete book
	@DeleteMapping("/books/{bid}")
	public ResponseEntity<?> deleteBook(@PathVariable("bid") int bid) {
		try {
			this.bookService.deleteBook(bid);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	update book
	@PutMapping("/books/{bid}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bid") int bid) {
		try {
			this.bookService.updateBook(book, bid);
			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	file upload
	@PostMapping("/uploadfile")
	public ResponseEntity<String> Uploadfile(@RequestParam("image") MultipartFile file) {

//		upload process
		try {
//			validation
			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please uploade the file.");
			} else if (!file.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please uploade only jpeg file format.");
			}
//			uploading
			boolean u=fuh.uploadfile(file);
			if (u) {
//				return ResponseEntity.ok("File upload successful.");
				
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/")
						.path(file.getOriginalFilename()).toUriString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong! Please try again later...");
	}
}
