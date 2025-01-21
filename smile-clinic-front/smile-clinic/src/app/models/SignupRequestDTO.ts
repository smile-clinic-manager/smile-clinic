export class SignupRequestDTO {
  private firstName!: string;
  private surname!: string;
  private username!: string;
  private dni!: string;
  private email!: string;
  private confirmEmail!: string;
  private password!: string;
  private confirmPassword!: string;

  constructor(
    firstName: string,
    surname: string,
    username: string,
    dni: string,
    email: string,
    confirmEmail: string,
    password: string,
    confirmPassword: string
  ) {
    this.firstName = firstName;
    this.surname = surname;
    this.username = username;
    this.dni = dni;
    this.email = email;
    this.confirmEmail = confirmEmail;
    this.password = password;
    this.confirmPassword = confirmPassword;
  }

  getFirstName(): string {
    return this.firstName;
  }

  setFirstName(firstName: string): void {
    this.firstName = firstName;
  }

  getSurname(): string {
    return this.surname;
  }

  setSurname(surname: string): void {
    this.surname = surname;
  }

  getUsername(): string {
    return this.username;
  }

  setUsername(username: string): void {
    this.username = username;
  }

  getDni(): string {
    return this.dni;
  }

  setDni(dni: string): void {
    this.dni = dni;
  }

  getEmail(): string {
    return this.email;
  }

  setEmail(email: string): void {
    this.email = email;
  }

  getConfirmEmail(): string {
    return this.confirmEmail;
  }

  setConfirmEmail(confirmEmail: string): void {
    this.confirmEmail = confirmEmail;
  }

  getPassword(): string {
    return this.password;
  }

  setPassword(password: string): void {
    this.password = password;
  }

  getConfirmPassword(): string {
    return this.confirmPassword;
  }

  setConfirmPassword(confirmPassword: string): void {
    this.confirmPassword = confirmPassword;
  }
}
