import { TreatmentDTO } from "./TreatmentDTO";

export interface ClinicDTO {
  id: string;
  name: string;
  postalCode: string;
  address: string;
  phoneNumber: string;
  email: string;
  img: string;
  treatments: TreatmentDTO[];
}
