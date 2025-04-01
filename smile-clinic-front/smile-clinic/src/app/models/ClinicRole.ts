import { RoleDTO } from "./RoleDTO";

export interface ClinicRole {
    clinicId: string,
    clinic: string,
    roles: RoleDTO[]
}
  