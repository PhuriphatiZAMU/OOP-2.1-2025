package Polymorphism;

interface Ability  {
        void SpecailSkill();
}
class Animal {
    protected String name;

    public Animal(String name){
            this.name = name;
    }
    public void MakeSound(){
        System.out.printf("%s make a sound",name);
    }

    
}
class Dog extends Animal implements Ability{
    public Dog(String name){
        super(name);
    }
    @Override
    public void MakeSound(){
        System.out.printf("%s bark!\n",name);
    }
    @Override
    public void SpecailSkill(){
        System.out.printf("%s loyalty\n",name);
    }
}    
class Tiger extends Animal implements Ability {
    public Tiger(String name){
        super(name);
    }
    @Override
    public void MakeSound(){
        System.out.printf("%s Hokpeeb\n",name);
    }
    @Override
    public void SpecailSkill(){
        System.out.printf("%s will eat you\n",name);
    }
    
}

public class Zoo {
    public static void main(String[] args) {
        Animal dog = new Dog("Boo");
        dog.MakeSound();
        dog.SpecailSkill();


    }
}
