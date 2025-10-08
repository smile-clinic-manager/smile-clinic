import { PreviousDiseaseDTO } from "./PreviousDiseaseDTO";

export interface MedicalHistoryDTO {
  id: string;
  allergies: string;
  previousDiseases: PreviousDiseaseDTO[];
}
