package sample.decorator;

import sample.IVotingService;
import sample.VotingForm;
import sample.singleton.VotingService;

public abstract class VotingDecorator implements IVotingService {

    private VotingService votingService = VotingService.getInstance();

    @Override
    public VotingForm createVotingForm(VotingForm votingForm,String name1,String name2) {
                VotingForm votingFormSecond = votingService.createVotingForm(votingForm,name1,name2);
                votingFormSecond.setId(votingForm.getId());
                return votingFormSecond;
    }

}
