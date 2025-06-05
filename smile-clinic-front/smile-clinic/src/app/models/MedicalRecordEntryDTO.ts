
import { DentistDataDTO } from "./DentistDataDTO";
import { TreatmentInstanceDTO } from "./TreatmentInstanceDTO";
import { userData } from "./userData";

export interface MedicalRecordEntryDTO{
    id: string;
    dateTime: string;
    observations: string;
    treatmentInstance: TreatmentInstanceDTO;
    user: DentistDataDTO;
}