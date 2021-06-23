import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import library.*;

import javax.swing.*;

public class addReader extends JFrame implements ActionListener{
  
  Database database;
  JPanel menu;
  JButton add, back;
  JLabel name, surname, phoneNumber;
  JTextField nameText, surnameText, phoneNumberText;
  Readerlist parent;

  public addReader(Database database, Readerlist parent){
    this.parent = parent;
    this.database = database;
    this.setTitle("Adding reader");
    this.setResizable(false);
    this.setSize(250, 350);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    menu = new JPanel();

    name = new JLabel("Name");
    nameText = new JTextField("");
    name.setBounds(10, 10, 15, 10);
    menu.add(name);
    nameText.setBounds(30, 10, 30, 10);
    nameText.setColumns(20);
    menu.add(nameText);

    surname = new JLabel("Surname");
    surnameText = new JTextField("");
    surname.setBounds(10, 25, 15, 10);
    menu.add(surname);
    surnameText.setBounds(30, 25, 30, 10);
    surnameText.setColumns(20);
    menu.add(surnameText);

    phoneNumber = new JLabel("Phone number");
    phoneNumberText = new JTextField("");
    phoneNumber.setBounds(10, 40, 15, 10);
    menu.add(phoneNumber);
    phoneNumberText.setBounds(30, 40, 30, 10);
    phoneNumberText.setColumns(20);
    menu.add(phoneNumberText);

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
        database.getReaders().addReader(nameText.getText(), surnameText.getText(), Integer.parseInt(phoneNumberText.getText()));
        JOptionPane.showMessageDialog(this, "Reader successfully added.\n There are " + database.getReaders().getSize() + " readers.", "INFO", JOptionPane.INFORMATION_MESSAGE);
        parent.getReaderEntries();
        this.dispose();
    }
  }
}