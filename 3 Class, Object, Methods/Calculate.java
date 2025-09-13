class Calculate {

public double calculateAverage (int arr []) {

        double avg = 0;
        double sum = 0;
        for (int i = 0; i < arr.length; i++){
        sum+=arr[i];
        }
        avg = sum/arr.length;



        return avg;
}
public  int add (int a, int b){
    
    int result = a + b;
    
    return result;
}
public  int subtract(int a, int b){
    
    int result = a - b;
    
    return result;
}
public  int multiply(int a, int b){
    
    int result = a * b;
    
    return result;
}
public  double divide(int a, int b){
    
    if (b == 0) {
        return -1;
    }else return a / b;
    
    
    
}



}

