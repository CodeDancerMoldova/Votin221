package sample.singleton;


import sample.IVotingService;
import sample.Option;
import sample.VotingForm;
import sample.proxy.DatabaseProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public final class VotingService extends VotingServiceTemplate {

    private DatabaseProxy databaseProxy = DatabaseProxy.getInstance();
    private static int count = 0;
    private static int option_id = 0;
    private HashMap<Integer , Option> databaseForOptionNerds = new HashMap<>();
    private static VotingService INSTANCE;

    public static VotingService getInstance() {

        if(INSTANCE == null) {
            INSTANCE = new VotingService();
        }

        return INSTANCE;
    }

/*    public VotingForm createVotingForm(VotingForm votingForm,String name1,String name2) {

        if(votingForm.getName().isEmpty() && votingForm.getOptionList().size() == 0)
        {
            throw new RuntimeException("Mandatory attributes are required!");
        }

        List<Option> optionList = new ArrayList<>();
        optionList.add(createOption(name1));
        optionList.add(createOption(name2));

        VotingForm build = new VotingForm.VotingFormBuilder()
                .optionList(optionList)
                .description(votingForm.getDescription())
                .name(votingForm.getName())
                .build();

        databaseProxy.put(votingForm.getName(), build);
        count++;
        showVotingList();

        return build;

    }*/

/*    public List<VotingForm> showVotingList() {


    }*/

    public HashMap<Integer, Option> getDatabaseForOptionNerds() {
        return databaseForOptionNerds;
    }

    public  DatabaseProxy getDatabaseProxy() {
        return databaseProxy;
    }

    @Override
    public Option createOption(String name) {

        if(name.isEmpty()){
            throw new RuntimeException("Name is required!");
        }

        Option newOption = new Option();
        newOption.setName(name);
        newOption.setId(option_id);

        databaseForOptionNerds.put(count , newOption);
        option_id++;

        return newOption;
    }


    @Override
    public List<VotingForm> showVotingList() {
        List<VotingForm> votingFormList = new ArrayList<>();

        databaseProxy.getDatabaseForVoteForms().getDatabaseForVoteFormNerds().forEach((key,value) -> {
            votingFormList.add(value);
        });

        return votingFormList;
    }
}
