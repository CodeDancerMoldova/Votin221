package sample.proxy;

import sample.Database;
import sample.DatabaseForVoteForms;
import sample.VotingForm;
import sample.singleton.VotingService;

public class DatabaseProxy implements Database<String, VotingForm> {

    private static DatabaseForVoteForms databaseForVoteForms;
    private static DatabaseProxy DatabaseProxy;

    public static DatabaseProxy getInstance() {

        if(DatabaseProxy == null) {
            DatabaseProxy = new DatabaseProxy();
        }

        return DatabaseProxy;
    }


    @Override
    public void put(String s, VotingForm votingForm) {
        if (databaseForVoteForms==null){
            databaseForVoteForms = new DatabaseForVoteForms();
        }
        if (s.isEmpty() ||  votingForm==null){
            throw new RuntimeException("Null objects on input");
        }
        else {
            databaseForVoteForms.put(s,votingForm);
        }
    }

    public  DatabaseForVoteForms getDatabaseForVoteForms() {
        return databaseForVoteForms;
    }
}
