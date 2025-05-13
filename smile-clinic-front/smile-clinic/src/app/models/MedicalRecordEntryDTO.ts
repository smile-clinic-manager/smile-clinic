import { TreatmentInstanceDTO } from "./TreatmentInstanceDTO";

export interface MedicalRecordEntryDTO{
    id: string;
    dateTime: string;
    observations: string;
    treatmentInstance: TreatmentInstanceDTO;
}