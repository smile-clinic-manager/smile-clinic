import { ClinicRole } from "./ClinicRole";

export interface userData {
  id: number;
  username: string;
  email: string;
  firstName: string;
  lastName1: string;
  lastName2: string;
  dni: string;
  roles: ClinicRole[];
}
