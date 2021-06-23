package library;

import java.util.ArrayList;
import java.time.LocalDate;
import java.io.Serializable;
import library.Bookset.*;

/** Exception informing that issued book is already rented by someone */
 class CurrentlyRentedException extends Exception{
  public CurrentlyRentedException(){}
}

/** Exception informing that reader has already rented maximal agreed number of books */
class RentCapacityAchieved extends Exception{
  public RentCapacityAchieved(){}
}

/** Exception informing that issued book is not rented by specified person */
class BookNotRented extends Exception{
  public BookNotRented(){}
}

/** Redaers' database */
public class ReaderBase implements Serializable{
  /** Instance of rent of a book */
  public class Rent implements Serializable{
    Book rentedBook;
    LocalDate dateOfRent;
    LocalDate dateOfReturn;
    Reader rentingPerson;

    public Rent(Book rentedBook, Reader renter, LocalDate dateOfRent)
    {
      this.dateOfRent = dateOfRent;
      this.rentedBook = rentedBook;
      this.rentingPerson = renter;
      this.dateOfReturn = dateOfRent.plusDays(28);
      rentedBook.setRent(this);
    }

    public Reader getRenter(){
      return this.rentingPerson;
    }

    /**Sets further date of return for a rent */
    public void prolongRent(int prolongationDays){
      dateOfReturn = dateOfReturn.plusDays(prolongationDays);
    }

    /** Edits rent entry enabling book to be rent */
    private void removeRentEntry(){
      this.rentedBook.setRent(null);
    }
  }
  public class Reader implements Serializable{
    private int id;
    private String name;
    private String surname;
    private int phoneNumber;
    private ArrayList<Rent> currentRents;

    public Reader(int id, String name, String surname, int phoneNumber)
    {
      this.id = id;
      this.name = name;
      this.surname = surname;
      this.phoneNumber = phoneNumber;
      this.currentRents = new ArrayList<Rent>();
    }

    public int getID(){
      return this.id;
    }

    public void setName(String name){
      this.name = name;
    }

    public String getName()
    {
      return this.name;
    }

    public String getSurname()
    {
      return this.surname;
    }

    public int getPhoneNumber(){
      return this.phoneNumber;
    }

    public int getNumberOfRents()
    {
      return this.currentRents.size();
    }

    public void setSurname(String surname){
      this.surname = surname;
    }

    public void setPhoneNumber(int phoneNumber){
      this.phoneNumber = phoneNumber;
    }

    /** Does a lookup through specified reader's rents in order to find there specific book */
    public Rent findBookInRents(Book book) throws BookNotRented{
      for(Rent r: currentRents){
        System.out.println(r.rentedBook.getTitle());
        if(r.rentedBook == book)
          return r;
      }
      throw new BookNotRented();
    }

    /** Begins rent and sets the book status as unavailable
     * @see setRent
     */
    public void rentBook(Book bookToRent, LocalDate startDate) throws CurrentlyRentedException, RentCapacityAchieved{
      if(!bookToRent.isAvailableToRent())
        throw new CurrentlyRentedException();
      if(currentRents.size() == 6)
        throw new RentCapacityAchieved();
      Rent act = new Rent(bookToRent, this, startDate);
      currentRents.add(act);
      bookToRent.setRent(act);
    }

    /** Ends rent and enables the book to be rent */
    public void returnBook(Book returnedBook) throws BookNotRented{
      Rent finishedRent;
      try{
        finishedRent = findBookInRents(returnedBook);
      }
      catch(BookNotRented e){
        throw e;
      }
      finishedRent.removeRentEntry();
      this.currentRents.remove(finishedRent);
      System.out.println("Book successfully returned");
    }
  }

  private ArrayList<Reader> readerSet;

  public ReaderBase(){
    readerSet = new ArrayList<Reader>();
  }

  /** Allows to add a reader into the database */
  public void addReader(String name, String surname, int phoneNumber){
    readerSet.add(new Reader(readerSet.size() + 1, name, surname, phoneNumber));
  }

  /** Creates list of people who missed the deadline to return the book they rented */
  public ArrayList<Reader> getListOfDebtors(){
    ArrayList<Reader> debtors = new ArrayList<Reader>();
    for(Reader reader: this.readerSet)
      for(Rent rent: reader.currentRents)
        if(LocalDate.now().isAfter(rent.dateOfReturn)){
          debtors.add(reader);
          break;
        }
    return debtors;
  }

  /** Searches for readers with surnames starting with given prefix
   * @return ArrayList<Book> of matching books
   */
  public ArrayList<Reader> readerLookupBySurname(String surnamePrefix){
    ArrayList<Reader> res = new ArrayList<Reader>();
    for(Reader r: readerSet)
      if(r.surname.startsWith(surnamePrefix))
        res.add(r);
    return res;
  }

  public int getSize(){
    return this.readerSet.size();
  }

  /** Returns reader specified by an ID (counting from 1) */
  public Reader getReader(int id){
    return this.readerSet.get(id - 1);
  }
  
}
