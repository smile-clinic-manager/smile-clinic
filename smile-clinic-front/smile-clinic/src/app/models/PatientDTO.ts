import { ClinicDTO } from "./ClinicDTO";
import { DiseaseDTO } from "./DiseaseDTO";
import { MedicalHistoryDTO } from "./MedicalHistoryDTO";

export interface PatientDTO {
  id: string;
  firstName: string;
  lastName1: string;
  lastName2: string;
  dni: string;
  email: string;
  phoneNumber: string;
  clinic: ClinicDTO;
  medicalHistory: MedicalHistoryDTO;
}