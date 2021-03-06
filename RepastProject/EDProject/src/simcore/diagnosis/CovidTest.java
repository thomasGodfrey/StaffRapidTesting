package simcore.diagnosis;

import EDLanguage.sandbox.Staff;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;

public class CovidTest {

    private static CovidTest instance = null; 
	private Double sensitivity;
	private Double specificity;
	
	private CovidTest(Double pdblSensitivity, Double pdblSpecificity) {
		this.sensitivity = pdblSensitivity;
		this.specificity = pdblSpecificity;
	}
	
	private CovidTest() {
	    Parameters params = RunEnvironment.getInstance().getParameters();
		this.sensitivity = params.getDouble("covid_test_sensitivity");    
		this.specificity = params.getDouble("covid_test_specificity");    
	}
	
	public static CovidTest GetInstance() {
		if(instance == null) {
			instance = new CovidTest();
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
