import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LoginRequest, RegisterRequest, User} from "../../../types/types";
import {catchError, Observable, tap, throwError} from "rxjs";
import {StorageService} from "./storage.service";


const BASE_URL = 'http://localhost:8080/api/v1/auth/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
};

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private user: User | null = this.storageService.getUser();

  constructor(
    private http: HttpClient,
    private storageService: StorageService
  ) {
  }

  getUser(): User | null {
    return this.user;
  }

  setUser(user: User): void {
    this.user = user;
  }

  register(registerRequest: RegisterRequest): Observable<any> {
    return this.http.post(BASE_URL + 'sign-up', registerRequest, httpOptions);
  }

  login(loginRequest: LoginRequest): Observable<any> {
    return this.http.post(BASE_URL + 'sign-in', loginRequest, httpOptions);
  }

  logOut(): Observable<any> {
    return this.http.post(`${BASE_URL}logout`, {}, httpOptions).pipe(
      tap(() => {
        this.storageService.clean();
      }),
      catchError((error) => {
        console.error('Logout failed', error);
        return throwError(() => new Error('Logout failed. Please try again.'));
      })
    );
  }
}
