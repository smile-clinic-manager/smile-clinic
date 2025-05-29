import { DentistDataDTO } from "./DentistDataDTO";
import { PatientDTO } from "./PatientDTO";
import { RegisteredUserDTO } from "./RegisteredUserDTO";
import { userData } from "./userData";

export interface AppointmentDTO {
  id: string;
  duration: number;
  visitPurpose: string;
  appointmentState: string;
  dateTime: string; //DateTime
  user: DentistDataDTO;
  patient: PatientDTO;
}