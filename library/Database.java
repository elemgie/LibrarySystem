package library;

import java.io.*;

public class Database{
  ReaderBase readers;
  Bookset books;

  public ReaderBase getReaders(){
    return readers;
  }

  public Bookset getBooks(){
    return books;
  }
  
  public Database(){
    readData();
    if(readers == null){
      //System.out.println("coś nie ten");
      readers = new ReaderBase();
    }
    if(books == null){
      //System.out.println("coś nie ten ale z książkami");
      books = new Bookset();
    }
  }

  public void writeData(){
    try{
      ObjectOutputStream readersOut = new ObjectOutputStream(new FileOutputStream("readers.ser"));
      readersOut.writeObject(readers);
      readersOut.close();
      ObjectOutputStream booksOut = new ObjectOutputStream(new FileOutputStream("books.ser"));
      booksOut.writeObject(books);
      booksOut.close();
      System.out.println("Database saved without interruption.");
    }
    catch(IOException e){
      System.out.println("Problem with saving occured. Check integrity of database.");
    }
  }
  public void readData(){
    try{
      ObjectInputStream readersIn = new ObjectInputStream(new FileInputStream("readers.ser"));
      readers = (ReaderBase)readersIn.readObject();
      readersIn.close();
      ObjectInputStream booksIn = new ObjectInputStream(new FileInputStream("books.ser"));
      books = (Bookset)booksIn.readObject();
      booksIn.close();
      System.out.println("Database read successfully.");
    }
    catch(Exception e){
      System.out.println("Database loading failed. Check database files.");
    }
  }
}
