class Dog {
    int size; // ขนาด
String breed; // พันธุ์
String name;

void bark(){
	System.out.println("โฮ่ง โฮ่ง");
}
}

public class DogTestDrive { // class เรียกใช้
    public static void main (String [] args) {
	Dog d = new Dog (); 
	d.breed = "อลาสกันมาลามิล";
	d.name = "มะแขว้น";
	
	Dog jack = new Dog();
	jack.breed = "หมาวัด";
	jack.name = "แจ็ค";

	jack.bark();

}
}

     
