import { MedicalRecordEntryDTO } from "./MedicalRecordEntryDTO";
import { TreatmentInstanceDTO } from "./TreatmentInstanceDTO";
import { userData } from "./userData";

export interface TeethDTO {
     id: string;
     code: string;
     name: string;
     medicalRecords: MedicalRecordEntryDTO[];
     user: userData;
}