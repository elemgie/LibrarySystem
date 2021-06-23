import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;

import library.*;
import library.Bookset.Book;

import javax.swing.*;

public class bookRentReturn extends JFrame implements ActionListener{
  
  Database database;
  JPanel menu;
  JButton help, rentBook, returnBook, back;
  JLabel bookID, readerID, startDate;
  JTextField bookIDText, readerIDText,startDateText;
  Booklist parent;

  public bookRentReturn(Database database, Booklist parent){
    this.parent = parent;
    this.database = database;
    this.setTitle("Renting/Returning the book");
    this.setSize(320, 150);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    menu = new JPanel();
    
    bookID = new JLabel("Book's ID");
    bookIDText = new JTextField("");
    bookID.setBounds(10, 35, 15, 10);
    menu.add(bookID);
    bookIDText.setBounds(30, 35, 30, 10);
    bookIDText.setColumns(20);
    menu.add(bookIDText);

    readerID = new JLabel("Reader's ID");
    readerIDText = new JTextField("");
    readerID.setBounds(10, 40, 15, 10);
    menu.add(readerID);
    readerIDText.setBounds(30, 40, 30, 10);
    readerIDText.setColumns(20);
    menu.add(readerIDText);

    startDate = new JLabel("Date");
    startDateText = new JTextField("DD/MM/YYYY");
    startDate.setBounds(10, 40, 15, 10);
    menu.add(startDate);
    startDateText.setBounds(30, 40, 30, 10);
    startDateText.setColumns(20);
    menu.add(startDateText);

    help = new JButton("Help");
    help.setBounds(0, 75, 20, 30);
    help.addActionListener(this);
    menu.add(help);

    rentBook = new JButton("Rent");
    rentBook.setBounds(20, 75, 20, 30);
    rentBook.addActionListener(this);
    menu.add(rentBook);

    returnBook = new JButton("Return");
    returnBook.setBounds(20, 75, 20, 30);
    returnBook.addActionListener(this);
    menu.add(returnBook);

    back = new JButton("Back");
    back.setBounds(20, 75, 20, 30);
    back.addActionListener(this);
    menu.add(back);

    this.add(menu);
  }

  @Override
  public void actionPerformed(ActionEvent e){
    if(e.getSource() == back){
      this.dispose();
    }

    if(e.getSource() == help)
      JOptionPane.showMessageDialog(this, "If you want to rent a book, provide book's, reader's ID and start date.\n In order to return a book only the book's and renter's IDs are required.\n If the date is today you can type \"today\"", "Help", JOptionPane.INFORMATION_MESSAGE);

    if(e.getSource() == rentBook){
      try{
        LocalDate start;
        String dateInput = startDateText.getText();
        if(dateInput.equals("today"))
          start = LocalDate.now();
        else
          start = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        database.getReaders().getReader(Integer.parseInt(readerIDText.getText())).rentBook(database.getBooks().getBook(Integer.parseInt(bookIDText.getText())), start);
        JOptionPane.showMessageDialog(this, "Book successfully rented.\n", "INFO", JOptionPane.INFORMATION_MESSAGE);
        parent.getBookEntries();
        this.dispose();
      }
      catch(DateTimeParseException exc){
        JOptionPane.showMessageDialog(this, "Wrong format date!", "Date format error", JOptionPane.WARNING_MESSAGE);
        startDateText.setText("DD/MM/YYYY");
      }
      catch(Exception exc){
        JOptionPane.showMessageDialog(this, "Book is currently rented or reader has already rented 6 books.", "Creating rent error", JOptionPane.WARNING_MESSAGE);
      }
    }

    if(e.getSource() == returnBook){
      try{
        Book b = database.getBooks().getBook(Integer.parseInt(bookIDText.getText()));
        database.getReaders().getReader(Integer.parseInt(readerIDText.getText())).returnBook(b);
        JOptionPane.showMessageDialog(this, "Book successfully returned.\n", "INFO", JOptionPane.INFORMATION_MESSAGE);
        parent.getBookEntries();
        this.dispose();
      }
      catch(Exception exc){
        JOptionPane.showMessageDialog(this, "Book hasn't been rented! Check given IDs.", "Returning error", JOptionPane.WARNING_MESSAGE);
      }
    }
  }
}