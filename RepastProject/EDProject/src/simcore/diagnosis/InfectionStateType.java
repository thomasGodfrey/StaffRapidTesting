package simcore.diagnosis;

import EDLanguage.sandbox.Staff;
import repast.simphony.random.RandomHelper;

public class InfectionStateType {

	protected Integer minDurationOfState; // What is minimum time an agent can spend in this state
	protected Integer maxDurationOfState; // What is maximum time an agent can spend in this state
	protected InfectionStatus infectionStatus; //The actual infection status this state represents
	protected InfectionStateType nextState; // What is the next state after this one
	
	public InfectionStateType() {
	}
	
	public InfectionState generateStateForMe(Staff pStaff) {   //<---------------------------------------- later could consider agent properties to determine duration etc
		
		int pintRandDuration = RandomHelper.nextIntFromTo(minDurationOfState, maxDurationOfState);
		return new InfectionState(this, pintRandDuration);
	}

	public InfectionStateType getNextState() {
		return nextState;
	}

	public void setNextState(InfectionStateType nextState) {
		this.nextState = nextState;
	}

	public Integer getMinDurationOfState() {
		return minDurationOfState;
	}
	
	public Integer getMaxDurationOfState() {
		return maxDurationOfState;
	}

	public void setMinDurationOfState(Integer durationOfState) {
		minDurationOfState = durationOfState;
	}
	
	public void setMaxDurationOfState(Integer durationOfState) {
		maxDurationOfState = durationOfState;
	}
	
	public InfectionStatus getInfectionStatus() {
		return infectionStatus;
	}
}