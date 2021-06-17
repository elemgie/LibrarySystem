import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import library.*;
import library.Bookset.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class addBook extends JFrame implements ActionListener{

  Database database;
  JTable books;
  JPanel menu;
  JButton back;

  public addBook(Database database){
    this.database = database;
    this.setTitle("List of books in the library");
    this.setSize(1000, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    menu = new JPanel();

    books = new JTable();
    JScrollPane pane = new JScrollPane(books);
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    DefaultTableModel model = new DefaultTableModel(
      new String[] {
        "ID", "Title", "Author", "Publisher", "Publication Year", "Value", "Current renter ID" 
      }, 0
    );
    books.setModel(model);
    for(Book b: database.getBooks().bookLookupByTitle("")){
      String rentid;
      if(b.isAvailableToRent())
        rentid = "Not rented";
      else
        rentid = "" + b.getRent().getRenter().getID();
      model.addRow(new Object[]{
        b.getID(), b.getTitle(), b.getAuthor(), b.getPublisher(), b.getPublicationYear(), b.getValue(), rentid
      });
    }
    menu.add(books);
    
    back = new JButton("Back");
    back.addActionListener(this);
    menu.add(back);

    this.add(menu); 
  }

  @Override
  public void actionPerformed(ActionEvent e){
     if(e.getSource() == back){
    this.setVisible(false);
    Home home = new Home(database);
     }
  }
}