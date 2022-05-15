package sample;

public class Option {

    private int id;

    private String name;

    private Integer countNrOfVotes = 0;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void incrementCountNrOfVotes() {
         countNrOfVotes++;
    }

    @Override
    public String toString() {
        return name + ":" +countNrOfVotes;
    }
}
