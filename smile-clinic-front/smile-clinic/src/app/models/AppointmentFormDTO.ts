export interface AppointmentFormDTO{
    id: string;
    duration: string;
    visitPurpose: string;
    date: string; //DateTime
    time: string; //DateTime
    userId: string;
    patientId: string;
}