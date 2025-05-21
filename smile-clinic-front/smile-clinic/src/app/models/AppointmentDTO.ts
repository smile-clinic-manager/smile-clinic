import { PatientDTO } from "./PatientDTO";
import { RegisteredUserDTO } from "./RegisteredUserDTO";

export interface AppointmentDTO {
  id: string;
  duration: number;
  visitPurpose: string;
  appointmentState: string;
  dateTime: string; //DateTime
  user: RegisteredUserDTO;
  patient: PatientDTO;
}