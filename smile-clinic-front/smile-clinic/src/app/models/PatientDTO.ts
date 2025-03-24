import { ClinicDTO } from "./ClinicDTO";
import { DiseaseDTO } from "./DiseaseDTO";

export interface PatientDTO {
  id: string;
  firstName: string;
  lastName1: string;
  lastName2: string;
  dni: string;
  email: string;
  phoneNumber: string;
  allergies: string; //Why aren't we making an enum or something?
  diseases: DiseaseDTO[];
  clinic: ClinicDTO;
}