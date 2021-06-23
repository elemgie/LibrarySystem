import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.PatternSyntaxException;
import java.awt.*;

import library.*;
import library.ReaderBase.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Debtorlist extends JFrame implements ActionListener{

  Database database;
  JTable debtors;
  JPanel menu;
  JButton back, filter;
  JScrollPane scrollbar;
  DefaultTableModel model;
  TableRowSorter<DefaultTableModel> sorter;
  JTextField filterText;

  public Debtorlist(Database database){
    this.database = database;
    this.setResizable(false);
    this.setTitle("List of debtors");
    this.setSize(500, 550);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    menu = new JPanel();
    //Creating table view
    debtors = new JTable();
    model = new DefaultTableModel();

    //Creating filter
    sorter = new TableRowSorter<DefaultTableModel>(model);
    debtors.setRowSorter(sorter);
    filterText = new JTextField("");
    filterText.setBounds(5, 5, 400, 30);
    filterText.setColumns(30);
    menu.add(filterText);
    filter = new JButton("Filter");
    filter.addActionListener(this);
    filter.setBounds(405, 5, 20, 30);
    menu.add(filter);

    String[] headers = new String[] {
      "ID", "Name", "Surname", "Phone number", "No. of rents", 
    };
    for(String s: headers)
      model.addColumn(s);
    debtors.setModel(model);

    //Generating table entries
    getReaderEntries();

    //Inserting table into ScrollPane
    scrollbar = new JScrollPane(debtors);
    scrollbar.setBounds(35, 30, 650, 450);
    scrollbar.setVisible(true);
    menu.add(scrollbar);
    
    //Buttons

    back = new JButton("Back");
    back.addActionListener(this);
    back.setBounds(80, 490, 20, 30);
    menu.add(back);

    this.add(menu); 
  }

  public void getReaderEntries(){
    model.setRowCount(0);
    for(Reader r: database.getReaders().getListOfDebtors()){
      model.addRow(new Object[]{
        r.getID(), r.getName(), r.getSurname(), r.getPhoneNumber(), r.getNumberOfRents(),
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
  }
}