package Inheritance.Person; 
public class Person {

    private String name;
    private String address;
    /**
     * 
     * @param name คือชื่อของบุคคล
     * @param address คือที่อยู่ของบุคคล
     */
    public Person(String name, String address) {
    System.out.println("Invoke Person(String name, String address)");
         this.name = name;
         this.address = address;
    }
    /**
     * 
     * @return ชื่อของบุคคล
     */
    public String getName() {
        return name;
    }
    /**
     * 
     * @return ที่อยู่ของบุคคล
     */
    public String getAddress() {
        return address;
    }
    /**
     * 
     * @param address คือที่อยู่ของบุคคล
     */
    public void setAddress(String address) {
        this.address = address;
    }
    public String toString () {
    return String.format("Person[name=%s,address=%s]",name,address);
    }
}
