package simcore.diagnosis;

import EDLanguage.sandbox.Staff;
import repast.simphony.random.RandomHelper;

public class InfectionStateType {

	private Integer minDurationOfState; // What is minimum time an agent can spend in this state
	private Integer maxDurationOfState; // What is maximum time an agent can spend in this state
	private Double probabilityMoveToNextState;
	private InfectionStatus infectionStatus; //The actual infection status this state represents
	private InfectionStateType nextState; // What is the next state after this one
	
	public InfectionStateType(InfectionStatus pinfectionStatus, Integer pintMinDurationOfState, Integer pintMaxDurationOfState) {
		this.infectionStatus = pinfectionStatus;
		this.setMinDurationOfState(pintMinDurationOfState);
		this.setMaxDurationOfState(pintMaxDurationOfState);
	}
	
	public InfectionStateType(InfectionStatus pinfectionStatus, Double pdblProbabilityMoveToNextState) {
		this.infectionStatus = pinfectionStatus;
		this.probabilityMoveToNextState = pdblProbabilityMoveToNextState;
	}
	
	public InfectionState generateStateForMe(Staff pStaff) {   //<---------------------------------------- later could consider agent properties to determine duration etc
		
		if(probabilityMoveToNextState != null) {
			return new InfectionState(this, probabilityMoveToNextState);
		} else {
			int pintRandDuration = RandomHelper.nextIntFromTo(minDurationOfState, maxDurationOfState);
			return new InfectionState(this, pintRandDuration);
		}
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