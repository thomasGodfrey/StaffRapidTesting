package EDLanguage.sandbox;

/*Generated by MPS */

import edHello.Signals.Signal;

public class NewPatientNeedMedicineSignal extends Signal {

  public NewPatientNeedMedicineSignal() {
    this.setName("NewPatientNeedMedicine");
    this.setDescription("a patient need medicine");
    this.AddActor("Nurse");
  }
}