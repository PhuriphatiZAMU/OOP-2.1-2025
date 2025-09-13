package Inheritance.Author;

public class BookTest {
public static void main (String [] args) {
    Author a = new Author("CK","oakabc@gmail.com",'M');
    System.out.println(a);
    Book b = new Book ("My name is CK", a, 100);
    System.out.println(b);    
    
    Book anotherBook = new Book("more Java", new Author("Paul Tan", "paul@somewhere.com", 'm'), 29.95);
    System.out.println(anotherBook);

    Book chulaBook1 = new Book("HARRY POTTER AND THE PHILOSOPHER'S STONE (MINALIMA EDITION) (VERSION) (HC)", new Author(" J. K. ROWLING", "info@jkrowling.com", 'f'), 1_044.00, 65);
    System.out.println(chulaBook1);
    Book chulaBook2 = new Book( "ความฉิบหายชั่วชีวิต", new Author(" เรวัตร์ พันธุ์พิพัฒน์", "yorewat@yahoo.com", 'm'), 175.00, 476);
    System.out.println(chulaBook2);
    Book chulaBook3 = new Book("พจนานุกรมความกลัวและความบ้า", new Author(" KATE SUMMERSCALE", "jonny.coward@bloomsbury.com", 'f'), 418.00, 8);
    System.out.println(chulaBook3);

    }
}

