export interface SignupResponseDTO {
  id: number;
  username: string;
  email: string;
  firstName: string;
  lastName1: string;
  lastName2: string;
  dni: string;
  role: string | null;
  jwtToken: string;
}
