package Inheritance.Footballer;

class Manager extends Person {
    private String Role;
    private int ExperienceYears;
    private int trophies;

    public Manager(String name ,String lastName ,String team ,String Role, int ExperienceYears, int trophies) {
    
    super(name, lastName, team);
    this.Role = Role;
    this.ExperienceYears = ExperienceYears;
    this.trophies = trophies;
    }
    public String getRole (){
        return Role;
    }
    public int getExperienceYears(){
        return ExperienceYears;
    }
    public int getTrophies(){
        return trophies;
    }
    @Override
    public String toString(){
        return String.format("Manager[%s Role = %s , ExperienceYears = %d ,  trophies = %d]",super.toString() , Role ,ExperienceYears,trophies);
    }
}
