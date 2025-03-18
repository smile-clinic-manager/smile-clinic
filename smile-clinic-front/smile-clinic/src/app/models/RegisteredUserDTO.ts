import { RoleDTO } from "./RoleDTO";

export interface RegisteredUserDTO {
  id: number;
  username: string;
  email: string;
  firstName: string;
  lastName1: string;
  lastName2: string;
  dni: string;
  roles: RoleDTO[] | null;
  jwtToken: string;
  refreshToken: string;
}
