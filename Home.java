import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import library.Database;

public class Home extends JFrame implements ActionListener{
  JFrame frame;
  JButton booklist, readerlist, bookRent, bookReturn, debtors, removeBook, addBook, addReader, bookLookup;
  Database database;
  public Home(Database database){
    this.database = database;
    frame = new JFrame("Library management system");
    frame.setSize(500, 300);
    frame.setResizable(false);
    frame.setLayout(new GridLayout(2, 3, 10, 10));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    booklist = new JButton("Booklist");
    booklist.addActionListener(this);
    frame.add(booklist);

    readerlist = new JButton("List of readers");
    readerlist.addActionListener(this);
    frame.add(readerlist);

    bookRent = new JButton("Rent a book");
    bookRent.addActionListener(this);
    frame.add(bookRent);

    bookReturn = new JButton("Return the book");
    bookReturn.addActionListener(this);
    frame.add(bookReturn);

    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e){
    if(e.getSource() == booklist){
      System.out.println("GIT");
    }
    //if(e.getSource() == readerlist)
  }
  public static void main(String[] args){
    Database database = new Database();
    Home window = new Home(database);
  }
}