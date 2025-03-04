import { TreatmentDTO } from "./TreatmentDTO";

export interface ClinicDTO {
  name: string;
  postalCode: string;
  address: string;
  phoneNumber: string;
  email: string;
  img: string;
  treatments: TreatmentDTO[];
}
