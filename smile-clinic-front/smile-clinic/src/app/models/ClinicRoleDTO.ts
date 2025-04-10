import { RoleDTO } from "./RoleDTO";

export interface ClinicRoleDTO {
    clinicId: string,
    clinic: string,
    roles: RoleDTO[]
}