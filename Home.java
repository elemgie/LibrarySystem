import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.*;
import library.*;

public class Home extends JFrame implements ActionListener{
  Database database;
  JButton booklist, readerlist, bookRent, bookReturn, debtors, removeBook, addBook, addReader, exit;
  JPanel menu;

  public Home(Database database){
    this.database = database;
    this.setTitle("Library management system");
    this.setSize(1000, 200);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    menu = new JPanel();
    menu.setLayout(new GridLayout(2, 5, 10, 10));
    booklist = new JButton("Search for book");
    booklist.addActionListener(this);
    menu.add(booklist);

    readerlist = new JButton("Search for reader");
    readerlist.addActionListener(this);
    menu.add(readerlist);

    bookRent = new JButton("Rent a book");
    bookRent.addActionListener(this);
    menu.add(bookRent);

    bookReturn = new JButton("Return the book");
    bookReturn.addActionListener(this);
    menu.add(bookReturn);

    debtors = new JButton("Debtors list");
    debtors.addActionListener(this);
    menu.add(debtors);

    addBook = new JButton("Add a book");
    addBook.addActionListener(this);
    menu.add(addBook);
    
    removeBook = new JButton("Remove the book");
    removeBook.addActionListener(this);
    menu.add(removeBook);

    addReader = new JButton("Add a reader");
    addReader.addActionListener(this);
    menu.add(addReader);

    exit = new JButton("Exit");
    exit.addActionListener(this);
    menu.add(exit);

    this.add(menu); 
  }

  @Override
  public void actionPerformed(ActionEvent e){
    if(e.getSource() == booklist){
      this.setVisible(false);
      new Booklist(database).setVisible(true);
     }

    if(e.getSource() == addBook){
       this.setVisible(false);
       new addBook(database).setVisible(true);
     }

    if(e.getSource() == readerlist){
      this.setVisible(false);
      new Readerlist(database).setVisible(true);
    }

    if(e.getSource() == debtors){
      this.setVisible(false);
      new Debtorlist(database).setVisible(true);
    }
    if(e.getSource() == exit)
     System.exit(0);
  }
  public static void main(String[] args){
    Database database = new Database();
    System.out.println(database.getBooks().getSize());
    System.out.println(database.getReaders().getSize());
    Home window = new Home(database);
    try{
    database.getReaders().addReader("John", "Smith", 987654321);
    for(int i = 0; i < 10; i++)
    database.getBooks().addToBookset("Catcher in the rye", "J.D. Sallinger", "Collins", 2010, 50);
    database.getBooks().removeFromBookset(2);
    database.getReaders().getReader(1).rentBook(database.getBooks().getBook(5), LocalDate.of(2020, 10, 21));
    database.getReaders().getReader(1).returnBook(database.getBooks().getBook(5));
    database.getReaders().getReader(1).rentBook(database.getBooks().getBook(15), LocalDate.of(2020, 10, 21));
    database.getBooks().removeFromBookset(24);
    }
    catch(Exception e){
      System.out.println("sth is wrong");
    }
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run(){
        database.writeData();
      }
    });
  }
}
