package simcore.diagnosis;
import EDLanguage.sandbox.Staff;
import repast.simphony.random.RandomHelper;

public class AsymptomaticInfectedInfectionState extends InfectionStateType{
	protected Double probabilityMoveToNextState;

	public AsymptomaticInfectedInfectionState(int pintminDurationOfState, int pintmaxDurationOfState) {
		super();
		minDurationOfState = pintminDurationOfState;
		maxDurationOfState = pintmaxDurationOfState;
		this.infectionStatus = InfectionStatus.Infected_Asymptomatic;
	}
	
	public InfectionState generateStateForMe(Staff pStaff) {   //<---------------------------------------- later could consider agent properties to determine duration etc
		int pintRandDuration = RandomHelper.nextIntFromTo(minDurationOfState, maxDurationOfState);
		return new InfectionState(this, pintRandDuration);	
	}
}



