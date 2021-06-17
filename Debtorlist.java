import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import library.*;
import library.ReaderBase.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Debtorlist extends JFrame implements ActionListener{

  Database database;
  JTable debtors;
  JPanel menu;
  JButton back;

  public Debtorlist(Database database){
    this.database = database;
    this.setTitle("List of debtors");
    this.setSize(1000, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    menu = new JPanel();

    debtors = new JTable();
    //JScrollPane pane = new JScrollPane(books);
    //pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    DefaultTableModel model = new DefaultTableModel(
      new String[] {
        "ID", "Name", "Surname", "Phone Number", "Number of current rents", 
      }, 1
    );
    debtors.setModel(model);
    for(Reader r: database.getReaders().getListOfDebtors()){
      model.addRow(new Object[]{
        r.getID(), r.getName(), r.getSurname(), r.getPhoneNumber(), r.getNumberOfRents(),
      });
    }
    menu.add(debtors);
    
    back = new JButton("Back");
    back.addActionListener(this);
    back.setBounds(0, 0, 20, 30);
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