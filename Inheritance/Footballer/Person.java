package Inheritance.Footballer;

public class Person {
    private String Name;
    private String LastName;
    private String Team;

    public Person(String name, String lastName, String team) {
        this.Name = name;
        this.LastName = lastName;
        this.Team = team;
    }
    public String getName() {
        return Name;
    }
    public String getLastName() {
        return LastName;
    }

    public String getTeam() {
        return Team;
    }

    public String toString() {
        return String.format("Person[Name=%s , LastName=%s , Team=%s]", Name , LastName , Team);
    }
}