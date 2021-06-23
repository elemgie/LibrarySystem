import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.PatternSyntaxException;
import java.awt.*;

import library.*;
import library.Bookset.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Booklist extends JFrame implements ActionListener{

  Database database;
  JTable books;
  JPanel menu;
  JButton back, add, remove, filter, bookRentReturn;
  JScrollPane scrollbar;
  DefaultTableModel model;
  TableRowSorter<DefaultTableModel> sorter;
  JTextField filterText;

  public Booklist(Database database){
    this.database = database;
    this.setTitle("List of books in the library");
    this.setResizable(false);
    this.setSize(500, 550);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    menu = new JPanel();
    //Creating table view
    books = new JTable();
    model = new DefaultTableModel();

    //Creating filter
    sorter = new TableRowSorter<DefaultTableModel>(model);
    books.setRowSorter(sorter);
    filterText = new JTextField("");
    filterText.setBounds(5, 5, 400, 30);
    filterText.setColumns(30);
    menu.add(filterText);
    filter = new JButton("Filter");
    filter.addActionListener(this);
    filter.setBounds(405, 5, 20, 30);
    menu.add(filter);

    String[] headers = new String[] {
      "ID", "Title", "Author", "Publisher", "Publication Year", "Value", "Current renter ID" 
    };
    for(String s: headers)
      model.addColumn(s);
    books.setModel(model);

    //Generating table entries
    getBookEntries();

    //Inserting table into ScrollPane
    scrollbar = new JScrollPane(books);
    scrollbar.setBounds(35, 30, 650, 450);
    scrollbar.setVisible(true);
    menu.add(scrollbar);
    
    //Buttons
    add = new JButton("Add");
    add.addActionListener(this);
    add.setBounds(20, 490, 20, 30);
    menu.add(add);

    remove = new JButton("Remove");
    remove.addActionListener(this);
    remove.setBounds(50, 490, 20, 30);
    menu.add(remove);

    bookRentReturn = new JButton("Rent/Return");
    bookRentReturn.addActionListener(this);
    bookRentReturn.setBounds(80, 490, 20, 30);
    menu.add(bookRentReturn);

    back = new JButton("Back");
    back.addActionListener(this);
    back.setBounds(110, 490, 20, 30);
    menu.add(back);


    this.add(menu); 
  }

  public void getBookEntries(){
    model.setRowCount(0);
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
  }

  @Override
  public void actionPerformed(ActionEvent e){
    if(e.getSource() == back){
      this.setVisible(false);
      Home home = new Home(database);
    }

    if(e.getSource() == filter){
      String input = filterText.getText();
      if(input.length() == 0)
        sorter.setRowFilter(null);
      else{
        try{
          sorter.setRowFilter(RowFilter.regexFilter(input));
        }
        catch(PatternSyntaxException exc){
          JOptionPane.showMessageDialog(null, "Invalid filter input!", "Filter error", JOptionPane.ERROR_MESSAGE);
        }
      }
    }

    if(e.getSource() == bookRentReturn){
      bookRentReturn window = new bookRentReturn(database, this);
    }

    if(e.getSource() == add){
      addBook window = new addBook(database, this);
    }
    if(e.getSource() == remove){
      removeBook window = new removeBook(database, this);
    }
  }
}