package simcore.diagnosis;

import java.util.ArrayList;
import java.util.List;

import EDLanguage.sandbox.Staff;
import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import repast.simphony.util.ContextUtils;
import simcore.basicStructures.Board;
import simcore.basicStructures.ToolBox;

public class StaffRotaAdder {
	
	private int count = 0;
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private List<Staff> completeStaffList = new ArrayList<Staff>();
	private ArrayList<ArrayList<Staff>> staffTeamList = new ArrayList<ArrayList<Staff>>();
	private int daysOn;
	private int currentTeamIndex = 0;

	/**
	 * 
	 * @param space
	 * @param grid
	 * @param plstAllStaff
	 * @param pintTeamSize
	 */
	public StaffRotaAdder(ContinuousSpace<Object> space, Grid<Object> grid, List<Staff> plstAllStaff, int pintTeamSize) {
		this.space = space;
		this.grid = grid;
		completeStaffList = plstAllStaff;
		createStaffTeams(pintTeamSize);
	}
	
	/**
	 * 
	 * @param pintDaysOn The number of days each staff team works in order before a new team replaces them
	 * @return
	 */
	public StaffRotaAdder WithDaysOn(int pintDaysOn) {
		daysOn = pintDaysOn;
		return this;
	}
	
	private void createStaffTeams(int pintGroupSize) {		
		int i = 0;
		
		System.out.println("PintGroupSize = " + pintGroupSize);
		System.out.println("Num staff = " + completeStaffList.size());
		ArrayList<Staff> plstNewTeam = new ArrayList<>();
		for (Staff staff : completeStaffList) {
			if(i < pintGroupSize) {
				i++;
			} else {
				staffTeamList.add(plstNewTeam);
				plstNewTeam = new ArrayList<>();
				i = 0;
			}
			plstNewTeam.add(staff);
		}
		staffTeamList.add(plstNewTeam);
		
		System.out.println("Team size = " + staffTeamList.size());
		int j = 0;
		for (List<Staff> plstTeam : staffTeamList) {
			System.out.println("Team " + (j+1) + " has " + plstTeam.size() + " people");
			j++;
		}

	}

	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		Ticktock();
	}
	
	/*
	 * Split staff into equal groups of x 
	 * 
	 */
	
	public void Ticktock() {
		if(count == 0) {
			count = daysOn;
	    	Context context = ContextUtils.getContext (this);
	    	ArrayList<Staff> plstCurrentTeam = staffTeamList.get(currentTeamIndex);
	    	
	    	// Remove the current team from the simulation
			// Tell these staff they are off and dont need to test themselves
//	    	context.removeAll(plstCurrentTeam);
	    	for (Staff staff : plstCurrentTeam) {
	    		staff.isOnRota = false;
			}
	    	
	    	// Iterate the team number (needs to be circular)
			// Tell these staff they are on and need to test themselves
	    	currentTeamIndex = getNextTeamIndex();
	    	ArrayList<Staff> plstNewTeam = staffTeamList.get(currentTeamIndex);
	    	String pstrOnStaff = "";
	    	for (Staff staff : plstNewTeam) {
				// Tell these staff they are off and dont need to test themselves
	    		staff.isOnRota = true;
	    		pstrOnStaff += staff + ",";
			}
	    	
	    	// Add the new team to the simulation
//	    	context.addAll(plstNewTeam);
	    	System.out.println("new team on:");
	    	System.out.println(pstrOnStaff);
		}else {
			count --;
		}
	}
	
    public Board ReadBoard() {
    	return new ToolBox(this).ReadBoard();
    }
    
    private int getNextTeamIndex() {
    	return (currentTeamIndex + 1) % staffTeamList.size();
    }

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	

}
