package simcore.diagnosis;

import EDLanguage.sandbox.Staff;

public class SusceptibleInfectionState extends InfectionStateType{
	protected Double probabilityMoveToNextState;

	public SusceptibleInfectionState(Double pdblProbabilityMoveToNextState) {
		super();
		this.probabilityMoveToNextState = pdblProbabilityMoveToNextState;
		this.infectionStatus = InfectionStatus.Susceptible;
	}
	
	public InfectionState generateStateForMe(Staff pStaff) {   //<---------------------------------------- later could consider agent properties to determine duration etc
		return new InfectionState(this, probabilityMoveToNextState);
	}
}


