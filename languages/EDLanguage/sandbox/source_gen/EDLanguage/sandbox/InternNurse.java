package EDLanguage.sandbox;

/*Generated by MPS */

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import edHello.Signals.Signal;
import edHello.action.Action;
import edHello.action.ActionStep;
import edHello.action.basicAction.MoveAction;
import edHello.action.ConsequenceStep;
import edHello.action.Consequence;

public class InternNurse extends Staff {

  public double stress = Double.parseDouble("" + "20.0");
  public double trust = Double.parseDouble("" + "40");
  public double groupStress = Double.parseDouble("" + "0");

  public InternNurse(ContinuousSpace<Object> space, Grid<Object> grid) {
    super(space, grid);
  }


  public void SetMission(Signal s) {
    switch (s.getName()) {
      case "":
        break;
      case "NewPatientArrive":
        curMission = new Action("DoSomething");
        this.InitDoSomething(s);
        break;
      default:
        System.out.println("Set mission: " + s.getName() + " failed!");
        return;
    }
    curActionStep = 0;
  }

  public void InitDoSomething(Signal s) {
    System.out.println("DoSomething" + " function called");

    Signal sendSignalTemp = new Signal();

    curMission.WithStep(new ActionStep().WithName("").WithAction(new MoveAction().WithTarget(s.GetData("patient"))));

    curMission.WithStep(new ConsequenceStep().WithOrder(new Consequence().WithContent("stress", "+=", 1.4)));
  }

}
