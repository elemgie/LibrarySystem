package library;

import java.util.ArrayList;
import java.time.LocalDate;
import java.io.Serializable;
import library.Bookset.*;


///Exception informing that issued book is already rented by someone
class CurrentlyRentedException extends Exception{
  public CurrentlyRentedException(){}
}

///Exception informing that reader has already rented maximal agreed number of books
class RentCapacityAchieved extends Exception{
  public RentCapacityAchieved(){}
}

public class ReaderBase implements Serializable{
  class Rent implements Serializable{
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

    /**Sets further date of return for a rent */
    public void prolongRent(int prolongationDays){
      dateOfReturn = dateOfReturn.plusDays(prolongationDays);
    }

    /** Edits rent entry enabling book to be rent */
    void removeRentEntry(){
      this.rentedBook.setRent(null);
    }
  }
  class Reader implements Serializable{
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

    /** Setter for reader's name */
    public void setName(String name){
      this.name = name;
    }

    /** Setter for reader's surname */
    public void setSurname(String surname){
      this.surname = surname;
    }

    /** Setter for reader's phoneNumber */
    public void setPhoneNumber(int phoneNumber){
      this.phoneNumber = phoneNumber;
    }

    /** Begins rent and sets the book status as unavailable
     * @see setRent
     */
    void rentBook(Book bookToRent, LocalDate startDate) throws CurrentlyRentedException, RentCapacityAchieved{
      if(!bookToRent.isAvailableToRent())
        throw new CurrentlyRentedException();
      if(currentRents.size() == 6)
        throw new RentCapacityAchieved();
      Rent act = new Rent(bookToRent, this, startDate);
      currentRents.add(act);
      bookToRent.setRent(act);
    }

    /**Ends rent and enables the book to be rent */
    void returnBook(Rent finishedRent){
      finishedRent.removeRentEntry();
      this.currentRents.remove(finishedRent);
    }
  }

  private ArrayList<Reader> readerSet;

  void addReader(String name, String surname, int phoneNumber){
    readerSet.add(new Reader(readerSet.size(), name, surname, phoneNumber));
  }

  /** Creates list of people who missed the deadline to return the book they rented */
  ArrayList<Reader> getListOfDebtors(){
    ArrayList<Reader> debtors = new ArrayList<Reader>();
    for(Reader reader: this.readerSet)
      for(Rent rent: reader.currentRents)
        if(LocalDate.now().isAfter(rent.dateOfReturn))
          debtors.add(reader);
    return debtors;
  }

  /** Generates list of all registered readers */
  ArrayList<Reader> getListOfReaders(){
    return this.readerSet;
  }
  
}
