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
    if(readers == null)
      readers = new ReaderBase();
    if(books == null)
      books = new Bookset();
  }

  public void writeData(){
    try{
      ObjectOutputStream readersOut = new ObjectOutputStream(new FileOutputStream("data/readers.ser"));
      readersOut.writeObject(readers);
      readersOut.close();
      ObjectOutputStream booksOut = new ObjectOutputStream(new FileOutputStream("data/books.ser"));
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
      ObjectInputStream readersIn = new ObjectInputStream(new FileInputStream("data/readers.ser"));
      readers = (ReaderBase)readersIn.readObject();
      readersIn.close();
      ObjectInputStream booksIn = new ObjectInputStream(new FileInputStream("data/books.ser"));
      books = (Bookset)booksIn.readObject();
      booksIn.close();
      System.out.println("Database read successfully.");
    }
    catch(Exception e){
      System.out.println("Database loading failed. Check database files.");
    }
  }
}
