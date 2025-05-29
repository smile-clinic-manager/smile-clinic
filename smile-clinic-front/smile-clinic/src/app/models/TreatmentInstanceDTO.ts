import { PatientDTO } from "./PatientDTO";

export interface TreatmentInstanceDTO{
    id: string;
    name: string;
    price: number;
    notes: string;
    patient: PatientDTO;
}