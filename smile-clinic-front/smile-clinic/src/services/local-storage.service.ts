import { isPlatformBrowser } from '@angular/common';
import { inject, Injectable, PLATFORM_ID } from '@angular/core';
import * as CryptoJS from 'crypto-js';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {
  private readonly secretKey: string = 'key-de-prueba';
  private platformId = inject(PLATFORM_ID); // Inject PLATFORM_ID

  constructor() { }

  public setTokenInLocalStorage(jwtToken: string, refreshToken: string): void {
    const encryptedToken: string = CryptoJS.AES.encrypt(jwtToken, this.secretKey).toString();
    const encryptedRefreshToken: string = CryptoJS.AES.encrypt(jwtToken, this.secretKey).toString();
    localStorage.setItem('authToken', jwtToken);
    localStorage.setItem('token', encryptedToken);
    localStorage.setItem('refreshToken', encryptedRefreshToken);
  }

  public getTokenInLocalStorage(): string {
    const encryptedToken: string = localStorage.getItem('token') ?? 'noToken';
    const decryptedToken: string = CryptoJS.AES.decrypt(encryptedToken, this.secretKey).toString(CryptoJS.enc.Utf8);

    return decryptedToken;
  }

  public existsToken(): boolean {
    if (!isPlatformBrowser(this.platformId)) {
      return false; // Prevents `localStorage` errors in SSR
    }
    return localStorage.getItem('token') ? true : false;
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
      console.log(decoded);
      return decoded; // You can return this data for use in your application
    } catch (error) {
      console.error('Error decoding JWT:', error);
    }
    return '';
  }

  public deleteTokens(): void{
    localStorage.removeItem('token');
    localStorage.removeItem('refreshToken');
  }


}
