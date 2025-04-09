import { isPlatformBrowser } from '@angular/common';
import { inject, Injectable, PLATFORM_ID } from '@angular/core';
import * as CryptoJS from 'crypto-js';
import { jwtDecode } from 'jwt-decode';
import { RegisteredUserDTO } from '../app/models/RegisteredUserDTO';
import { userData } from '../app/models/userData';
import { RoleDTO } from '../app/models/RoleDTO';
import { ClinicRole } from '../app/models/ClinicRole';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {
  private readonly secretKey: string = 'key-de-prueba';
  private platformId = inject(PLATFORM_ID); // Inject PLATFORM_ID

  private selectedGlobalClinic: ClinicRole | null = null;
  private selectedGlobalRole: RoleDTO | null = null;

  constructor() { }

  public setTokenInLocalStorage(jwtToken: string, refreshToken: string): void {
    let userData = this.parseJwtToUserDTO(jwtToken);

    const encryptedToken: string = CryptoJS.AES.encrypt(jwtToken, this.secretKey).toString();
    localStorage.setItem('accessToken', encryptedToken);
    localStorage.setItem('apiToken', jwtToken);
    localStorage.setItem('refreshToken', refreshToken);
    localStorage.setItem('userData', JSON.stringify(userData));
  }

  parseJwtToUserDTO(jwtToken: string): RegisteredUserDTO | null {
    if (!jwtToken) return null;
  
    try {
      const payloadBase64 = jwtToken.split('.')[1];
      const binaryString = atob(payloadBase64);
      const bytes = new Uint8Array([...binaryString].map(c => c.charCodeAt(0)));
      // Decode with UTF-8 - Evitar que se rompa el texto con acentos y 'ñ'.
      const payloadJson = new TextDecoder('utf-8').decode(bytes);
      const payload = JSON.parse(payloadJson);
      
      return {
        id: payload.id || 0,
        username: payload.username || '',
        email: payload.email || '',
        firstName: payload.firstName || '',
        lastName1: payload.lastName1 || '',
        lastName2: payload.lastName2 || '',
        dni: payload.dni || '',
        roles: payload.roles || null,
        jwtToken: '',
        refreshToken: ''
      };
    } catch (error) {
      console.error('Error parseando JWT:', error);
      return null;
    }
  }

  public getUserData(): userData {
    const userData = localStorage.getItem('userData');
    return JSON.parse(userData!);
  }

  public getTokenInLocalStorage(): string {
    const encryptedToken: string = localStorage.getItem('accessToken') ?? 'noToken';
    const decryptedToken: string = CryptoJS.AES.decrypt(encryptedToken, this.secretKey).toString(CryptoJS.enc.Utf8);

    return decryptedToken;
  }

  public existsToken(): boolean {
    if (!isPlatformBrowser(this.platformId)) {
      return false; // Prevents `localStorage` errors in SSR
    }
    return localStorage.getItem('accessToken') ? true : false;
  }

  async checkAuthStatus(): Promise<boolean> {
    return new Promise((resolve) => {
      setTimeout(() => { 
        resolve(this.existsToken());
      }, 100);
    });
  }

  public getDecodedToken(): string {
    const token = this.getTokenInLocalStorage();
    return this.decodeToken(token);
  }

  private decodeToken(decryptedToken: string): string {
    try {
      const decoded: any = jwtDecode(decryptedToken);
      return decoded; 
    } catch (error) {
      console.error('Error decoding JWT:', error);
    }
    return '';
  }

  public deleteTokens(): void{
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    localStorage.removeItem('userData');
  }

  public getSelectedGlobalClinic(){
    return this.selectedGlobalClinic;
  }

  public getSelectedGlobalRole(){
    return this.selectedGlobalRole;
  }

  public setSelectedGlobalClinic(clinicRole: ClinicRole): void{
    this.selectedGlobalClinic = clinicRole;
  }

  public setSelectedGlobalRole(globalRole: any): void{
    this.selectedGlobalRole = globalRole;
  }



}
