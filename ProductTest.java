class Product
{
    String productName;
    String type;
    int rating;
    
    void rateIt(int rating) {
        
        System.out.println("This product has rating: " + rating); 
        
    }
}







public class ProductTest {

    public static void main (String [] args) {
    
         // Object 1
        Product asahiBeer = new Product ();
        asahiBeer.productName = "Asahi Beer";
        asahiBeer.type = "Beer";
        asahiBeer.rating = 5;
        asahiBeer.rateIt(asahiBeer.rating);
        
        // Object 2

        Product iPhone16 = new Product ();
        iPhone16.productName = "iPhone16";
        iPhone16.type = "Smart Phone";
        iPhone16.rating = 3;
        iPhone16.rateIt(iPhone16.rating);

        // Object 3
        
        Product poysian = new Product();
        poysian.productName = "ยาดมโป้ยเซียน";
        poysian.type = "ยาดม";
        poysian.rating = 10;
        poysian.rateIt(poysian.rating);
        
        
        // 1.1 จากโจทย์ จงสร้าง object 4 ตามข้อกำหนด โดยการเขียนโค้ดเอง

        // Object 4
        
        Product doiKham= new Product();
        doiKham.productName = "น้ำมะเขือเทศดอยคำ";
        doiKham.type = "น้ำผลไม้";
        doiKham.rating = 10;
        doiKham.rateIt(doiKham.rating);

        



        
        // 1.2 ลองคิดผลิตภัณฑ์ขึ้นมา 1 อย่าง ระบุชื่อ ชนิด เรตติ้ง และ สั่งให้ทำการแสดงค่าเรตติ้งออกมา
        
        // Object 5
        
        Product SamsungS25= new Product();
        SamsungS25.productName = "Samsung S25";
        SamsungS25.type = "Smart Phone";
        SamsungS25.rating = 9;
        SamsungS25.rateIt(SamsungS25.rating);

    }
}

