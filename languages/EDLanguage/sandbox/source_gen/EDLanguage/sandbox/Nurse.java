package EDLanguage.sandbox;

/*Generated by MPS */

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import edHello.Signals.Signal;
import edHello.action.Action;
import edHello.action.ActionStep;
import edHello.action.basicAction.MoveAction;
import edHello.action.basicAction.StayForTimeAction;
import edHello.action.basicAction.OrderAction;
import edHello.agents.Patient;
import edHello.Signals.Orders.MoveToOrder;
import edHello.action.basicAction.SendSignalAction;
import edHello.Signals.Orders.FollowOrder;
import edHello.action.basicAction.StayForConditionAction;
import edHello.action.basicAction.conditions.SpaceatCondition;

public class Nurse extends Staff {

  public double groupStress = Double.parseDouble("" + "0");

  public Nurse(ContinuousSpace<Object> space, Grid<Object> grid) {
    super(space, grid);
  }


  public void SetMission(Signal s) {
    switch (s.getName()) {
      case "":
        break;
      case "NewPatientArrive":
        curMission = new Action("DealNewPatient");
        this.InitDealNewPatient(s);
        break;
      case "NewPatientNeedMedicine":
        curMission = new Action("TakeMedicine");
        this.InitTakeMedicine(s);
        break;
      case "XRay":
        curMission = new Action("DoXRay");
        this.InitDoXRay(s);
        break;
      case "AllActorsCanReceive":
        curMission = new Action("test");
        this.Inittest(s);
        break;
      default:
        System.out.println("Set mission: " + s.getName() + " failed!");
        return;
    }
    curActionStep = 0;
  }

  public void InitDealNewPatient(Signal s) {
    System.out.println("DealNewPatient" + " function called");

    Signal sendSignalTemp = new Signal();

    curMission.WithStep(new ActionStep().WithName("").WithAction(new MoveAction().WithTarget(s.GetData("patient"))));
    curMission.WithStep(new ActionStep().WithName("talk with patient").WithAction(new StayForTimeAction().WithTimeSpan(3)));
    curMission.WithStep(new ActionStep().WithName("let patient go to see an EMP").WithAction(new OrderAction().WithPatient(((Patient) s.GetData("patient"))).WithOrder(new MoveToOrder().WithDestination(ReadMap().FindPlace("preDiagnosticArea")))));
    sendSignalTemp = new NewPatientGotoENPSignalSignal();
    sendSignalTemp.AddData("patient", s.GetData("patient"));
    curMission.WithStep(new ActionStep().WithName("tell ENP patient will arrive").WithAction(new SendSignalAction().WithSignal(sendSignalTemp)));

  }
  public void InitTakeMedicine(Signal s) {
    System.out.println("TakeMedicine" + " function called");

    Signal sendSignalTemp = new Signal();

    curMission.WithStep(new ActionStep().WithName("move to pharmacy").WithAction(new MoveAction().WithTarget(ReadMap().FindPlace("pharmacy"))));
    curMission.WithStep(new ActionStep().WithName("look for medicine").WithAction(new StayForTimeAction().WithTimeSpan(5)));
    curMission.WithStep(new ActionStep().WithName("move to patient").WithAction(new MoveAction().WithTarget(s.GetData("patient"))));
    curMission.WithStep(new ActionStep().WithName("give medicine").WithAction(new StayForTimeAction().WithTimeSpan(1)));
    curMission.WithStep(new ActionStep().WithName("let patient go out").WithAction(new OrderAction().WithPatient(((Patient) s.GetData("patient"))).WithOrder(new MoveToOrder().WithDestination(ReadMap().FindPlace("exit")))));

  }
  public void InitDoXRay(Signal s) {
    System.out.println("DoXRay" + " function called");

    Signal sendSignalTemp = new Signal();

    curMission.WithStep(new ActionStep().WithName("move to patient").WithAction(new MoveAction().WithTarget(s.GetData("patient"))));
    curMission.WithStep(new ActionStep().WithName("let patient to follow self").WithAction(new OrderAction().WithPatient(((Patient) s.GetData("patient"))).WithOrder(new FollowOrder().WithTarget(this))));
    curMission.WithStep(new ActionStep().WithName("go to x-ray room").WithAction(new MoveAction().WithTarget(ReadMap().FindPlace("XRayRoom1"))));
    curMission.WithStep(new ActionStep().WithName("let patient enter").WithAction(new OrderAction().WithPatient(((Patient) s.GetData("patient"))).WithOrder(new MoveToOrder().WithDestination(ReadMap().FindPlace("XRayRoom1")))));
    StayForConditionAction sa = new StayForConditionAction();
    sa.WithCondition(new SpaceatCondition().WithSubject(s.GetData("patient")).WithTarget(ReadMap().FindPlace("XRayRoom1")));
    curMission.WithStep(new ActionStep().WithName("wait until patient is inside").WithAction(sa));
    curMission.WithStep(new ActionStep().WithName("do x-ray").WithAction(new StayForTimeAction().WithTimeSpan(20)));
    curMission.WithStep(new ActionStep().WithName("let patient go").WithAction(new OrderAction().WithPatient(((Patient) s.GetData("patient"))).WithOrder(new MoveToOrder().WithDestination(ReadMap().FindPlace("exit")))));

  }
  public void Inittest(Signal s) {
    System.out.println("test" + " function called");

    Signal sendSignalTemp = new Signal();

    curMission.WithStep(new ActionStep().WithName("").WithAction(new MoveAction().WithTarget(s.GetData("patient"))));
    this.InitGoToPharmacy(s);

  }
  public void InitGoToPharmacy(Signal s) {
    System.out.println("GoToPharmacy" + " function called");

    Signal sendSignalTemp = new Signal();

    curMission.WithStep(new ActionStep().WithName("").WithAction(new MoveAction().WithTarget(ReadMap().FindPlace("XRayRoom1"))));

  }

}