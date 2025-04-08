import { PreviousDiseaseDTO } from "./PreviousDiseaseDTO";

export interface MedicalHistoryDTO {
  id: string;
  name: string;
  previousDisease: PreviousDiseaseDTO[];
}
