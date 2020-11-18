package simcore.diagnosis;
import EDLanguage.sandbox.Staff;
import repast.simphony.random.RandomHelper;

public class ExposedInfectionState extends InfectionStateType{
	protected Double probabilityMoveToNextState;
	private InfectionStateType altNextState;
	
	public ExposedInfectionState(int pintminDurationOfState, int pintmaxDurationOfState) {
		super();
		minDurationOfState = pintminDurationOfState;
		maxDurationOfState = pintmaxDurationOfState;
		this.infectionStatus = InfectionStatus.Exposed;
	}
	
	public InfectionState generateStateForMe(Staff pStaff) {   //<---------------------------------------- later could consider agent properties to determine duration etc
		int pintRandDuration = RandomHelper.nextIntFromTo(minDurationOfState, maxDurationOfState);
		return new InfectionState(this, pintRandDuration);	
	}
	
	public void setNextState(InfectionStateType nextState, InfectionStateType altNextState) {
		this.nextState = nextState;
		this.altNextState = altNextState;
	}
	
	public InfectionStateType getNextState() {
		Double pdblDiceRoll = RandomHelper.nextDouble();
		if(pdblDiceRoll < 0.5) {
			return nextState;
		}
		return altNextState;
	}
}



