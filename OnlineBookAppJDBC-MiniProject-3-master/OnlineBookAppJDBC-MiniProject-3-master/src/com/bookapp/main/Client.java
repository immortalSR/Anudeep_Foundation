package com.bookapp.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bookapp.bean.Book;
import com.bookapp.dao.BookInter;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;
import com.bookapp.dao.BookImpl;

public class Client {

	public static void main(String[] args) {
	
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter 1 Add Book Details \n 2-for Delete Book \n "
			+ "3-Get Book By ID \n 4- Update Book with ID \n 5-Get All books Details"
			+ "\n 6-Get All books By Author \n 7-Get All books By Category");
	int num;
	BookInter bookService=new BookImpl();
	do {
		num=sc.nextInt();
		switch(num) {
		case 1:
			System.out.println("Enter value for add: Title");
			String title=sc.next();
			System.out.println("Enter Author name");
			String author=sc.next();
			System.out.println("Enter Category");
			String category=sc.next();
			System.out.println("Enter price");
			int price=sc.nextInt();
			System.out.println("Enter BookId");
			int bookid=sc.nextInt();
			Book book1=new Book(title,author,category, price,bookid);
			book1.setTitle(title);
			book1.setAuthor(author);
			book1.setCategory(category);
			book1.setPrice(price);
			book1.setBookId(bookid);
			bookService.addBook(book1);
			break;
		case 2:
			System.out.println("Enter BookId for Delete");
			int bookId=sc.nextInt();
			try {
				boolean val=bookService.deleteBook(bookId);
				if(val) {
					System.out.println("Value  deleted ");
				}
				
			} catch (BookNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			System.out.println("Enter Book Id");
			int bookId1=sc.nextInt();
			Book book=new Book();
			try {
				book=bookService.getBookById(bookId1);
				System.out.println("Book-Title: "+book.getTitle()+"\nAuthor-Name: "+book.getAuthor()+
						"\nBook Category-Name: "+book.getCategory()+"\nBook-price: "+book.getPrice()+
						"\nBook Id: "+book.getBookId());
			} catch (BookNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 4:
			System.out.println("Enter BookId");
			int bookid1=sc.nextInt();
			System.out.println("Enter Price");
			int price1=sc.nextInt();
			try {
				boolean val=bookService.updateBook(bookid1, price1);
				if(!val) {
					System.out.println("Book Id Wrong");
				}
			} catch (BookNotFoundException e1) {
				System.out.println(e1.getMessage());
			}
			break;
		case 5:
			System.out.println("Details of All Books");
			List<Book>allBooks=bookService.getAllBooks();
			for(Book books:allBooks) {
				System.out.println(books.getTitle()+" "+books.getAuthor()+" "+
			books.getCategory()+" "+(int)books.getPrice()+" "+(int)books.getBookId());
			}
			break;
		case 6:
			System.out.println("Enter Author Name");
			String author1=sc.next();
			List<Book>allBooks1=new ArrayList<>();
			try {
				allBooks1 = bookService.getBookByAuthor(author1);
			} catch (AuthorNotFoundException e) {
				System.out.println(e.getMessage());
			}
			for(Book books:allBooks1) {
				System.out.println(books.getTitle()+" "+books.getAuthor()+" "+
			books.getCategory()+" "+(int)books.getPrice()+" "+(int)books.getBookId());
			}
			break;
		case 7:
			System.out.println("Enter category Name");
			String category1=sc.next();
			List<Book>allBooks2=new ArrayList<>();
			try {
				allBooks2 = bookService.getBookByCategory(category1);
			} catch (CategoryNotFoundException e) {
				System.out.println(e.getMessage());
			}
			for(Book books:allBooks2) {
				System.out.println(books.getTitle()+" "+books.getAuthor()+" "+
			books.getCategory()+" "+(int)books.getPrice()+" "+(int)books.getBookId());
			}
			break;
			
		case 0:
			System.exit(0);
		default:
			System.out.println("wrong choice");
		}
	} while(num!=0);
	sc.close();
	}
	
}
