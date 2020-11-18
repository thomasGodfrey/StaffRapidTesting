package simcore.diagnosis;

import java.util.ArrayList;
import java.util.List;
import EDLanguage.sandbox.Staff;
import repast.simphony.data2.NonAggregateDataSource;
import simcore.logNote.LogNote;

public class TrackAndTraceApp{
	private static TrackAndTraceApp instance = new TrackAndTraceApp();
	private List<ResultLog> resultLogs = new ArrayList<ResultLog>();
	
	private TrackAndTraceApp() {}
	
	public static TrackAndTraceApp GetInstance() {
		if(instance != null) {
			return instance;
		}else {
			instance = new TrackAndTraceApp();
			return instance;
		}
	}
	
	public void LogResult(Staff pStaff, TestResult pTestResult) {
		resultLogs.add(new ResultLog(pStaff, pTestResult));
	}
	
	public double GetFalsePositives() {
//		int pintNumFalsePositives = 0;
//		for (ResultLog resultLog : resultLogs) {
//			if(resultLog.testResult.isInfected() 
//					&& ((resultLog.actualInfectionState.stateType.infectionStatus == InfectionStatus.Recovered
//							|| resultLog.actualInfectionState.stateType.infectionStatus == InfectionStatus.Susceptible))) {
//				pintNumFalsePositives++;
//			}
//		}
		return 100.0;
//		return pintNumFalsePositives;
	}
	
	public double GetFalseNegatives() {
//		int pintNumFalseNegatives = 0;
//		for (ResultLog resultLog : resultLogs) {
//			if(!resultLog.testResult.isInfected() 
//					&& ((resultLog.actualInfectionState.stateType.infectionStatus != InfectionStatus.Recovered
//							&& resultLog.actualInfectionState.stateType.infectionStatus != InfectionStatus.Susceptible))) {
//				pintNumFalseNegatives++;
//			}
//		}
//		
		return 2.0;
	}
	
	public class ResultLog{
		public Staff staff;
		public TestResult testResult;
		public InfectionState actualInfectionState;
		
		public ResultLog(Staff pStaff, TestResult pTestResult) {
			staff = pStaff;
			testResult = pTestResult;
			actualInfectionState = pStaff.getActualInfectionState();
		}
	}	
}
