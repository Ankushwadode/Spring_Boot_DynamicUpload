package com.api.main.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Pid;
	private String Ptitle;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private Author Pauthor;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int pid, String ptitle, Author pauthor) {
		super();
		Pid = pid;
		Ptitle = ptitle;
		Pauthor = pauthor;
	}

	public int getPid() {
		return Pid;
	}

	public void setPid(int pid) {
		Pid = pid;
	}

	public String getPtitle() {
		return Ptitle;
	}

	public void setPtitle(String ptitle) {
		Ptitle = ptitle;
	}

	public Author getPauthor() {
		return Pauthor;
	}

	public void setPauthor(Author pauthor) {
		Pauthor = pauthor;
	}

	@Override
	public String toString() {
		return "Book [Pid=" + Pid + ", Ptitle=" + Ptitle + ", Pauthor=" + Pauthor + "]";
	}
	
}
