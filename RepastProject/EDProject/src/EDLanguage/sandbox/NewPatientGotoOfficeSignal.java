package EDLanguage.sandbox;

/*Generated by MPS */

import edHello.Signals.Signal;

public class NewPatientGotoOfficeSignal extends Signal {

  public NewPatientGotoOfficeSignal() {
    this.setName("NewPatientGotoOffice");
    this.setDescription("a new patient has been sent to office");
    this.AddActor("Doctor");
  }
}
