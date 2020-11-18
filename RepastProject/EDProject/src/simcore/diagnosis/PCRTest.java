package simcore.diagnosis;

import EDLanguage.sandbox.Staff;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;

public class PCRTest {

    private static PCRTest instance = null; 
	private Double sensitivity;
	private Double specificity;
	
	private PCRTest(Double pdblSensitivity, Double pdblSpecificity) {
		this.sensitivity = pdblSensitivity;
		this.specificity = pdblSpecificity;
	}
	
	private PCRTest() {
	    Parameters params = RunEnvironment.getInstance().getParameters();
		this.sensitivity = params.getDouble("pcr_test_sensitivity");    
		this.specificity = params.getDouble("pcr_test_specificity");    
	}
	
	public static PCRTest GetInstance() {
		if(instance == null) {
			instance = new PCRTest();
		}
		
		return instance;
	}
	
	public TestResult Test(Staff pstaff, Double pdblCurrentTimestamp) {
		boolean staffIsInfected = pstaff.getActualInfectionState().stateType.getInfectionStatus() == InfectionStatus.Infected_Asymptomatic;
		
		Double pdblRand = RandomHelper.nextDouble();
		if (staffIsInfected) { // Sensitivity value used
			if (pdblRand < sensitivity) {
				return new TestResult(true, pdblCurrentTimestamp);
			}
			return new TestResult(false, pdblCurrentTimestamp);

		} else { // Specificity value used
			if (pdblRand < specificity) {
				return new TestResult(false, pdblCurrentTimestamp);
			}
			return new TestResult(true, pdblCurrentTimestamp);
		} 
	}
}
