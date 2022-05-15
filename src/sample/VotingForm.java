package sample;


import java.util.ArrayList;
import java.util.List;

public class VotingForm {

    private int id;
    private String name;
    private String description;
    private List<Option> optionList = new ArrayList<>();
    private boolean IsVoted = false;

    public void setVoted(boolean voted) {
        IsVoted = voted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVoted() {
        return IsVoted;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    private VotingForm(VotingFormBuilder votingFormBuilder){
        this.id = votingFormBuilder.id;
        this.name = votingFormBuilder.name;
        this.description = votingFormBuilder.description;
        this.optionList = votingFormBuilder.optionList;
    }

    @Override
    public String toString() {
        return  name  + "\n" + description + '\'' + "\n" + optionList + "\n";
    }

    public static class VotingFormBuilder
    {
        private int id;
        private String name;
        private String description;
        private List<Option> optionList = new ArrayList<>();

        public VotingFormBuilder name(String name){
            this.name = name;
            return this;
        }
        public VotingFormBuilder id(int id){
            this.id = id;
            return this;
        }
        public VotingFormBuilder description(String description){
            this.description = description;
            return this;
        }
        public VotingFormBuilder optionList(List<Option> optionList){
            this.optionList = optionList;
            return this;
        }
        public VotingForm build() {
            VotingForm votingForm =  new VotingForm(this);
            return votingForm;
        }

    }

}
