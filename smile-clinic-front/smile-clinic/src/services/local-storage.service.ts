import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {
  private readonly secretKey: string = 'key-de-prueba';

  constructor() { }

  public setTokenInLocalStorage(jwtToken: string): void {
    const encryptedToken: string = CryptoJS.AES.encrypt(jwtToken, this.secretKey).toString();
    localStorage.setItem('token', encryptedToken);
  }

  public getTokenInLocalStorage(): string {
    const encryptedToken:string = localStorage.getItem('token') ?? 'noToken';
    const decryptedToken: string = CryptoJS.AES.decrypt(encryptedToken, this.secretKey).toString(CryptoJS.enc.Utf8);

    const decodedToken: string = this.decodeToken(decryptedToken);
    return decodedToken;
  }

  private decodeToken(decryptedToken: string): string {
    try {
      const decoded: any = jwtDecode(decryptedToken);
      console.log(decoded);
      return decoded; // You can return this data for use in your application
    } catch (error) {
      console.error('Error decoding JWT:', error);
    }
    return '';
  }


}
