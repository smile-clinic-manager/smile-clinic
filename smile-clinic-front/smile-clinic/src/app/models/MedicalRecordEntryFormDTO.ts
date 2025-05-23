import { TeethDTO } from "./TeethDTO";
import { TreatmentDTO } from "./TreatmentDTO";
import { userData } from "./userData";

export interface MedicalRecordEntryFormDTO{
    date: string,
    time: string,
    treatmentId: string,
    userId: string,
    observations: string,
    teethListId: string[]
}