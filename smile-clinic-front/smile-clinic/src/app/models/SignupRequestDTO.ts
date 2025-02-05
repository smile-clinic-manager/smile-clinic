export class SignupRequestDTO {
  constructor(
    public firstName: string | null,
    public lastName1: string | null,
    public lastName2: string | null,
    public username: string | null,
    public dni: string | null,
    public email: string | null,
    public confirmEmail: string | null,
    public password: string | null,
    public confirmPassword: string | null
  ) {}
}
