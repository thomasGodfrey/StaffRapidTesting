package EDLanguage.sandbox;

/*Generated by MPS */

import edHello.Signals.Signal;

public class NewPatientGotoENPSignalSignal extends Signal {

  public NewPatientGotoENPSignalSignal() {
    this.setName("NewPatientGotoENPSignal");
    this.setDescription("a new patient has been sent to waiting area");
    this.AddActor("ENP");
  }
}
