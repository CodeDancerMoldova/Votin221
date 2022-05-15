package sample.singleton;

import sample.IVotingService;
import sample.Option;
import sample.VotingForm;
import sample.proxy.DatabaseProxy;

import java.util.ArrayList;
import java.util.List;

public abstract class VotingServiceTemplate implements IVotingService {

    private DatabaseProxy databaseProxy = DatabaseProxy.getInstance();
    private static int count = 0;

    @Override
    public final VotingForm createVotingForm(VotingForm votingForm, String name1, String name2) {

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

    }

    public abstract Option createOption(String name);
    public abstract List<VotingForm> showVotingList();

}
