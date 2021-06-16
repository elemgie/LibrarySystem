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
  
  public String writeData(){
    try{
      ObjectOutputStream readersOut = new ObjectOutputStream(new FileOutputStream("readers.ser"));
      readersOut.writeObject(readers);
      readersOut.close();
      ObjectOutputStream booksOut = new ObjectOutputStream(new FileOutputStream("books.ser"));
      booksOut.writeObject(books);
      booksOut.close();
      return "Database saved without interruption.";
    }
    catch(IOException e){
      return "Problem with saving occured. Check integrity of database.";
    }
  }
  public String readData(){
    try{
      ObjectInputStream readersIn = new ObjectInputStream(new FileInputStream("readers.ser"));
      readers = (ReaderBase)readersIn.readObject();
      readersIn.close();
      ObjectInputStream booksIn = new ObjectInputStream(new FileInputStream("books.ser"));
      books = (Bookset)booksIn.readObject();
      booksIn.close();
      return "Database read successfully.";
    }
    catch(Exception e){
      return "Database loading failed. Check database files and restart the program.";
    }
  }
}
