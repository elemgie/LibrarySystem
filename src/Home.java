import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Random;

import javax.swing.*;
import library.*;

public class Home extends JFrame implements ActionListener{
  Database database;
  JButton booklist, readerlist, debtors, exit;
  JPanel menu;

  public Home(Database database){
    this.database = database;
    this.setTitle("Library management system");
    this.setSize(400, 200);
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    menu = new JPanel();
    menu.setLayout(new GridLayout(2, 5, 10, 10));
    booklist = new JButton("Books");
    booklist.addActionListener(this);
    menu.add(booklist);

    readerlist = new JButton("Readers");
    readerlist.addActionListener(this);
    menu.add(readerlist);

    debtors = new JButton("Debtors list");
    debtors.addActionListener(this);
    menu.add(debtors);

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


  /** Allows to generate random database for developer tests
   */
  public static void generateRandomDatabase(Database database, int howManyBooks, int howManyReaders){
    Random gen = new Random();
    String titles[] = {"Alice's adventures in wonderland", "Romeo and Juliet", "Makbet", "Catcher in the rye", "The Great Gatsby", "Anna Karenina"};
    String authors[] = {"Lewis Carroll", "William Shakespeare", "William Shakespeare", "J.D. Sallinger", "F. Scott Fitzgerald", "Leo Tolstoy"};
    String publishers[] = {"Penguin", "HarperCollins", "Macmillan", "Simon&Schuster"};
    for(int i = 0; i < howManyBooks; i++){
      int nr = Math.abs(gen.nextInt()) % titles.length;
      int pub = Math.abs(gen.nextInt()) % publishers.length;
      database.getBooks().addToBookset(titles[nr], authors[nr], publishers[pub], 1900 + Math.abs(gen.nextInt())%122, 1 + Math.abs(gen.nextInt())%1000);
    }
    String names[] = {"John", "Henry", "Michael", "James", "Arthur", "Andrew", "Mark", "Yvonne", "Claire", "Hannah"};
    String surnames[] = {"Smith", "Jones", "Blake", "Clark", "Rumsfeld", "Namara", "Krinsky"};
    for(int i = 0; i < howManyReaders; i++){
      int namenr = Math.abs(gen.nextInt())%names.length;
      int surnamenr = Math.abs(gen.nextInt())%surnames.length;
      database.getReaders().addReader(names[namenr], surnames[surnamenr], 511111111 + Math.abs(gen.nextInt())%400000000);
    }
    for(int i = 1; i <= database.getBooks().getSize(); i++)
      if(gen.nextBoolean()){
        int renterNo = 1 + Math.abs(gen.nextInt()) % howManyReaders;
        try{
          database.getReaders().getReader(renterNo).rentBook(database.getBooks().getBook(i), LocalDate.now());
        }
        catch(Exception e){
          System.out.println("Exception, happens");
        }
      }
  }
  public static void main(String[] args){
    Database database = new Database();
    System.out.println(database.getBooks().getSize());
    System.out.println(database.getReaders().getSize());
    Home window = new Home(database);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run(){
        database.writeData();
      }
    });
  }
}
