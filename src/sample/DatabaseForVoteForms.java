package sample;

import java.util.HashMap;

public class DatabaseForVoteForms implements Database<String, VotingForm> {

    private HashMap<String , VotingForm> databaseForVoteFormNerds = new HashMap<>();

    public HashMap<String, VotingForm> getDatabaseForVoteFormNerds() {
        return databaseForVoteFormNerds;
    }

    @Override
    public void put(String type,VotingForm votingForm) {
        databaseForVoteFormNerds.put(type,votingForm);
    }

}
