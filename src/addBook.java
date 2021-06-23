import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import library.*;
import library.Bookset.*;

import javax.swing.*;

public class addBook extends JFrame implements ActionListener{
  
  Database database;
  JPanel menu;
  JButton add, back;
  JLabel title, author, publisher, publicationYear, value;
  JTextField titleText, authorText, publisherText, publicationYearText, valueText;
  Booklist parent;

  public addBook(Database database, Booklist parent){
    this.parent = parent;
    this.database = database;
    this.setTitle("Adding book");
    this.setResizable(false);
    this.setSize(250, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    menu = new JPanel();

    title = new JLabel("Title");
    titleText = new JTextField("");
    title.setBounds(10, 10, 15, 10);
    menu.add(title);
    titleText.setBounds(30, 10, 30, 10);
    titleText.setColumns(20);
    menu.add(titleText);

    author = new JLabel("Author");
    authorText = new JTextField("");
    author.setBounds(10, 25, 15, 10);
    menu.add(author);
    authorText.setBounds(30, 25, 30, 10);
    authorText.setColumns(20);
    menu.add(authorText);

    publisher = new JLabel("Publisher");
    publisherText = new JTextField("");
    publisher.setBounds(10, 40, 15, 10);
    menu.add(publisher);
    publisherText.setBounds(30, 40, 30, 10);
    publisherText.setColumns(20);
    menu.add(publisherText);

    publicationYear = new JLabel("Publication year");
    publicationYearText = new JTextField();
    publicationYear.setBounds(10, 55, 15, 10);
    menu.add(publicationYear);
    publicationYearText.setBounds(30, 55, 30, 10);
    publicationYearText.setColumns(20);
    menu.add(publicationYearText);

    value = new JLabel("Value");
    valueText = new JTextField();
    value.setBounds(10, 70, 15, 10);
    menu.add(value);
    valueText.setBounds(30, 70, 30, 10);
    valueText.setColumns(20);
    menu.add(valueText);

    add = new JButton("Add");
    add.setBounds(20, 75, 20, 30);
    add.addActionListener(this);
    menu.add(add);

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

    if(e.getSource() == add){
        int bookNumber = database.getBooks().addToBookset(titleText.getText(), authorText.getText(), publisherText.getText(), Integer.valueOf(publicationYearText.getText()), Integer.valueOf(valueText.getText()));
        JOptionPane.showMessageDialog(this, "Book successfully added.\n" + bookNumber + " books currently in bookset.", "INFO", JOptionPane.INFORMATION_MESSAGE);
        parent.getBookEntries();
        this.dispose();
    }
  }
}