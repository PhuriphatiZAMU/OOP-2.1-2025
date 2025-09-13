package Inheritance.Footballer;

class Footballer extends Person {
    private String playerNumber;
    private String position;
    private int goals;
    private int assists;
    private int matchesPlayed;
    private int yellowCards;
    private int redCards;
    private int trophies;
    private int age;

    public Footballer(String name, String lastName, String playerNumber, String team, String position, int goals, int assists, int matchesPlayed, int yellowCards, int redCards , int trophies, int age) {
        super(name, lastName, team);
        this.playerNumber = playerNumber;
        this.position = position;
        this.goals = goals;
        this.assists = assists;
        this.matchesPlayed = matchesPlayed;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.trophies = trophies; 
        this.age = trophies;
    }
    public String getPlayerNumber() {
        return playerNumber;
    }
    public String getPosition() {
        return position;
    }
    public int getGoals() {
        return goals;
    }
    public int getAssists() {
        return assists;
    }
    public int getMatchesPlayed() {
        return matchesPlayed;
    }
    public int getYellowCards() {
        return yellowCards;
    }
    public int getRedCards() {
        return redCards;
    }
    public int getTrophies() {
        return trophies;
    }
    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return String.format("Footballer[%s, PlayerNumber=%s, Position=%s, Goals=%d, Assists=%d, MatchesPlayed=%d, YellowCards=%d, RedCards=%d, Trophies=%d, Age=%d]", 
            super.toString(), playerNumber, position, goals, assists, matchesPlayed, yellowCards, redCards, trophies, age);
}
}

