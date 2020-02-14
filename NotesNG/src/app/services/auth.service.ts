import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // F I E L D S

  private baseUrl = environment.baseUrl;

  // C O N S T R U C T O R

  constructor(private http: HttpClient) {}

  // P U B L I C  M E T H O D S

  login(username, password) {
      // Make credentials
      const credentials = this.generateBasicAuthCredentials(username, password);
      // Send credentials as Authorization header (this is spring security convention for basic auth)
      const httpOptions = {
        headers: new HttpHeaders({
          Authorization: `Basic ${credentials}`,
          'X-Requested-With': 'XMLHttpRequest'
        })
      };
     // create request to authenticate credentials
      return this.http.get<User>(this.baseUrl + 'authenticate', httpOptions)
     .pipe( tap((res) => {
         localStorage.setItem('credentials', credentials);
         console.log(res);

         return res;
       }),
       catchError((err: any) => {
         console.log(err);
         return throwError('AuthService.login(): Error logging in.');
       })
     );
  }

  updateCredentials(username: string, password: string) {
    const credentials = this.generateBasicAuthCredentials(username, password);
    localStorage.setItem('credentials', credentials);
  }

  register(user) {
    // create request to register a new account
    return this.http.post<User>(this.baseUrl + 'register', user)
    .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('AuthService.register(): error registering user.');
        })
      );
  }

  logout() {
    localStorage.removeItem('credentials');
  }

  checkLogin() {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

  getCredentials() {
    return localStorage.getItem('credentials');
  }

  // P R I V A T E  M E T H O D S

  private generateBasicAuthCredentials(username, password) {
    return btoa(`${username}:${password}`);
  }
}
