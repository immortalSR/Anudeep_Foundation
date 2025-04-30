package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookImpl implements BookInter {
	
	Connection connection= null;
	PreparedStatement ps= null;
	@Override
	public void addBook(Book book) {
		
		connection= ModelDAO.openConnection();
		String sql="insert into book value(?,?,?,?,?)";
		try {
			ps=connection.prepareStatement(sql);
			ps.setString(1,book.getTitle());
			ps.setString(2,book.getAuthor());
			ps.setString(3, book.getCategory());
			ps.setInt(4,(int) book.getPrice());
			ps.setInt(5,book.getBookId());
			boolean value=ps.execute();
			System.out.println("Value Inserted "+value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ModelDAO.closeConnection();
	}

	@Override
	public boolean deleteBook(int bookid) throws BookNotFoundException {
		int value = 0;
		boolean result= true;
		connection= ModelDAO.openConnection();
		String sql="delete from book where bookId=?";
		try {
			ps=connection.prepareStatement(sql);
			ps.setInt(1, bookid);
			value=ps.executeUpdate();
			if(value==0) {
				result= false;
				throw new BookNotFoundException("book cannot be deleted");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ModelDAO.closeConnection();
		return result;
	}

	@Override
	public Book getBookById(int bookid) throws BookNotFoundException {
		boolean value=false;
		Book book=new Book();
		connection= ModelDAO.openConnection();
		String sql="select * from book where bookId=?";
		try {
			ps=connection.prepareStatement(sql);
			ps.setInt(1,bookid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				value=true;
				book.setTitle(rs.getString(1));
				book.setAuthor(rs.getString(2));
				book.setCategory(rs.getString(3));
				book.setPrice(rs.getInt(4));
				book.setBookId(bookid);
			}
			if(!value) {
				throw new BookNotFoundException("Book Id not Found");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ModelDAO.closeConnection();
		return book;
	}

	@Override
	public boolean updateBook(int bookid, int price) throws BookNotFoundException{
		boolean value = true;
		connection= ModelDAO.openConnection();
		String sql="update book set price=? where bookId=?";
		try {
			ps=connection.prepareStatement(sql);
			ps.setInt(1, price);
			ps.setInt(2, bookid);
			value=ps.execute();
			if(value) {
				throw new BookNotFoundException("book Not Updated");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ModelDAO.closeConnection();
		return value;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book>book=new ArrayList<>();
		
		connection= ModelDAO.openConnection();
		String sql="select * from book";
		try {
			ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Book book1=new Book();
				book1.setTitle(rs.getString(1));
				book1.setAuthor(rs.getString(2));
				book1.setCategory(rs.getString(3));
				book1.setPrice(rs.getInt(4));
				book1.setBookId(rs.getInt(5));
				book.add(book1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ModelDAO.closeConnection();
		return book;
	}

	@Override
	public List<Book> getBookByAuthor(String author) throws AuthorNotFoundException {
		boolean value=false;
		List<Book>book=new ArrayList<>();
		
		connection= ModelDAO.openConnection();
		String sql="select * from book where author=?";
		try {
			ps=connection.prepareStatement(sql);
			ps.setString(1,author);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				value=true;
				Book book1=new Book();
				book1.setTitle(rs.getString(1));
				book1.setAuthor(rs.getString(2));
				book1.setCategory(rs.getString(3));
				book1.setPrice(rs.getInt(4));
				book1.setBookId(rs.getInt(5));
				book.add(book1);
			}
			if(!value) {
				throw new AuthorNotFoundException("Author not Found");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ModelDAO.closeConnection();
		return book;
	}

	@Override
	public List<Book> getBookByCategory(String category) throws CategoryNotFoundException {
		boolean value=false;
		List<Book>book=new ArrayList<>();
		
		connection= ModelDAO.openConnection();
		String sql="select * from book where category=?";
		try {
			ps=connection.prepareStatement(sql);
			ps.setString(1,category);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				value=true;
				Book book1=new Book();
				book1.setTitle(rs.getString(1));
				book1.setAuthor(rs.getString(2));
				book1.setCategory(rs.getString(3));
				book1.setPrice(rs.getInt(4));
				book1.setBookId(rs.getInt(5));
				book.add(book1);
			}
			if(!value) {
				throw new CategoryNotFoundException("Category Not Found");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ModelDAO.closeConnection();
		return book;
	}
	
}
