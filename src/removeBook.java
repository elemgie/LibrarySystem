import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import library.*;

import javax.swing.*;

public class removeBook extends JFrame implements ActionListener{
  
  Database database;
  JPanel menu;
  JButton remove, back;
  JLabel ID;
  JTextField IDText;
  Booklist parent;

  public removeBook(Database database, Booklist parent){
    this.parent = parent;
    this.database = database;
    this.setTitle("Remove a book");
    this.setResizable(false);
    this.setSize(250, 120);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    menu = new JPanel();

    ID = new JLabel("Book's ID");
    IDText = new JTextField();
    ID.setBounds(10, 70, 15, 10);
    menu.add(ID);
    IDText.setBounds(30, 70, 30, 10);
    IDText.setColumns(20);
    menu.add(IDText);

    remove = new JButton("Remove");
    remove.setBounds(20, 75, 20, 30);
    remove.addActionListener(this);
    menu.add(remove);

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

    if(e.getSource() == remove){
      try{
        database.getBooks().removeFromBookset(Integer.parseInt(IDText.getText()));
        JOptionPane.showMessageDialog(this, "Book successfully removed.", "INFO", JOptionPane.INFORMATION_MESSAGE);
      }
      catch(Exception exc){
        JOptionPane.showMessageDialog(this, "This book is currently rented or doesn't exist!", "ERROR", JOptionPane.ERROR_MESSAGE);
      }
        parent.getBookEntries();
        this.dispose();
    }
  }
}