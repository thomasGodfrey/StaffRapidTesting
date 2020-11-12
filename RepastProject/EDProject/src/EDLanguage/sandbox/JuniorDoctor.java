package EDLanguage.sandbox;

/*Generated by MPS */

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import edHello.Signals.Signal;
import edHello.action.Action;
import edHello.action.ActionStep;
import edHello.action.basicAction.MoveAction;
import edHello.action.basicAction.StayForConditionAction;
import edHello.action.basicAction.conditions.SpaceatCondition;
import edHello.action.basicAction.StayForTimeAction;
import edHello.action.basicAction.conditions.PossibilityCondition;
import edHello.action.basicAction.SendSignalAction;
import edHello.action.basicAction.OrderAction;
import edHello.agents.Patient;
import edHello.Signals.Orders.MoveToOrder;
import edHello.action.ConsequenceStep;
import edHello.action.Consequence;
import edHello.action.basicAction.conditions.StateCondition;

public class JuniorDoctor extends Doctor {

  public double stress = Double.parseDouble("" + "1");
  public double mistakes = Double.parseDouble("" + "0");
  public double correctWork = Double.parseDouble("" + "0");
  public double groupStress = Double.parseDouble("" + "0");

  public JuniorDoctor(ContinuousSpace<Object> space, Grid<Object> grid) {
    super(space, grid);
  }


  public void SetMission(Signal s) {
    switch (s.getName()) {
      case "":
        break;
      case "NewPatientGotoOffice":
        curMission = new Action("Diagnose");
        this.InitDiagnose(s);
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

    curMission.WithStep(new ActionStep().WithName("").WithAction(new MoveAction().WithTarget(this)));

  }
  public void InitDoOtherThings(Signal s) {
    System.out.println("DoOtherThings" + " function called");

    Signal sendSignalTemp = new Signal();


  }
  public void InitDiagnose(Signal s) {
    System.out.println("Diagnose" + " function called");

    Signal sendSignalTemp = new Signal();

    curMission.WithStep(new ActionStep().WithName("move to diagnostic room").WithAction(new MoveAction().WithTarget(s.GetData("destination"))));
    StayForConditionAction sa = new StayForConditionAction();
    sa.WithCondition(new SpaceatCondition().WithSubject(s.GetData("patient")).WithTarget(s.GetData("destination")));
    curMission.WithStep(new ActionStep().WithName("wait until patient arrive").WithAction(sa));
    curMission.WithStep(new ActionStep().WithName("inspect the patient").WithAction(new StayForTimeAction().WithTimeSpan(10)));
    if (CheckCondition(new PossibilityCondition().WithPossibility(2000 / (this.stress - 100) + 108))) {
      this.InitMakeCorrectDiagnose(s);
    } else {
      this.InitMakeMistake(s);
    }
    this.InitRest(s);
    curMission.WithStep(new ActionStep().WithName("go back to office").WithAction(new MoveAction().WithTarget(ReadMap().FindPlace("office1"))));

  }
  public void InitXRay(Signal s) {
    System.out.println("XRay" + " function called");

    Signal sendSignalTemp = new Signal();

    sendSignalTemp = new XRaySignal();
    sendSignalTemp.AddData("patient", s.GetData("patient"));
    curMission.WithStep(new ActionStep().WithName("").WithAction(new SendSignalAction().WithSignal(sendSignalTemp)));

  }
  public void InitTakeMedicine(Signal s) {
    System.out.println("TakeMedicine" + " function called");

    Signal sendSignalTemp = new Signal();

    sendSignalTemp = new NewPatientNeedMedicineSignal();
    sendSignalTemp.AddData("patient", s.GetData("patient"));
    curMission.WithStep(new ActionStep().WithName("tell nurse to take medicine for patient").WithAction(new SendSignalAction().WithSignal(sendSignalTemp)));

  }
  public void InitLetPatientGo(Signal s) {
    System.out.println("LetPatientGo" + " function called");

    Signal sendSignalTemp = new Signal();

    curMission.WithStep(new ActionStep().WithName("").WithAction(new OrderAction().WithPatient(((Patient) s.GetData("patient"))).WithOrder(new MoveToOrder().WithDestination(ReadMap().FindPlace("exit")))));

  }
  public void InitMakeMistake(Signal s) {
    System.out.println("MakeMistake" + " function called");

    Signal sendSignalTemp = new Signal();

    if (CheckCondition(new PossibilityCondition().WithPossibility(70))) {
      if (CheckCondition(new PossibilityCondition().WithPossibility(50))) {
        curMission.WithStep(new ActionStep().WithName("").WithAction(new OrderAction().WithPatient(((Patient) s.GetData("patient"))).WithOrder(new MoveToOrder().WithDestination(ReadMap().FindPlace("waitingArea")))));
        this.InitTakeMedicine(s);
      } else {
        this.InitLetPatientGo(s);
      }
    } else {
      this.InitXRay(s);
    }

    curMission.WithStep(new ConsequenceStep().WithOrder(new Consequence().WithContent("mistakes", "+=", 1)));
    curMission.WithStep(new ConsequenceStep().WithOrder(new Consequence().WithContent("stress", "+=", 8)));
  }
  public void InitMakeCorrectDiagnose(Signal s) {
    System.out.println("MakeCorrectDiagnose" + " function called");

    Signal sendSignalTemp = new Signal();

    if (CheckCondition(new PossibilityCondition().WithPossibility(70))) {
      if (CheckCondition(new PossibilityCondition().WithPossibility(50))) {
        curMission.WithStep(new ActionStep().WithName("").WithAction(new OrderAction().WithPatient(((Patient) s.GetData("patient"))).WithOrder(new MoveToOrder().WithDestination(ReadMap().FindPlace("waitingArea")))));
        this.InitTakeMedicine(s);
      } else {
        this.InitLetPatientGo(s);
      }
    } else {
      this.InitXRay(s);
    }

    curMission.WithStep(new ConsequenceStep().WithOrder(new Consequence().WithContent("correctWork", "+=", 1)));
    curMission.WithStep(new ConsequenceStep().WithOrder(new Consequence().WithContent("stress", "+=", 1)));
  }
  public void InitRest(Signal s) {
    System.out.println("Rest" + " function called");

    Signal sendSignalTemp = new Signal();

    if (CheckCondition(new StateCondition().WithContent("stress", ">=", 90))) {
      curMission.WithStep(new ActionStep().WithName("").WithAction(new MoveAction().WithTarget(ReadMap().FindPlace("restRoom1"))));
      StayForConditionAction sa = new StayForConditionAction();
      sa.WithCondition(new StateCondition().WithContent("stress", "<=", 10));
      sa.WithConsequence(new Consequence().WithContent("stress", "-=", 10));
      curMission.WithStep(new ActionStep().WithName("").WithAction(sa));
    } else {
    }

  }

}
