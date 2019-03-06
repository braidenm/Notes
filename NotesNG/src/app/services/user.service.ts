import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { User } from '../models/note';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  // F I E L D S
  private user: User = null;
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/users';

  // C O N S T R U C T O R

  constructor(private http: HttpClient, private auth: AuthService, private router: Router) { }

  // P U B L I C  M E T H O D S

 index() {
    if (!this.auth.checkLogin) {
      this.router.navigateByUrl('login');
    }
    return this.http.get<User[]>(this.url, this.getHttp())
    .pipe(
      catchError(this.handleError)
     );
  }

  show(id: number) {
    if (!this.auth.checkLogin) {
      this.router.navigateByUrl('login');
    }
    return this.http.get<User[]>(this.url + '/' + id, this.getHttp())
    .pipe( catchError(this.handleError)
     );
  }
  // may use this later
  //
  // search(search: string) {
  //   if (!this.auth.checkLogin) {
  //     this.router.navigateByUrl('login');
  //   }
  //   return this.http.get<User[]>(this.url + '/search/' + search, this.getHttp())
  //   .pipe( catchError(this.handleError)
  //    );
  // }
  //
  // addUser(note: User) {
  //   if (!this.auth.checkLogin) {
  //     this.router.navigateByUrl('login');
  //   }
  //   return this.http.post<User>(this.url, note, this.getHttp())
  //     .pipe(catchError(this.handleError)
  //      );
  //
  // }

  updateUser(user: User) {
    if (!this.auth.checkLogin) {
      this.router.navigateByUrl('login');
    }
    return this.http.put<User>(this.url + '/' + user.id, user, this.getHttp())
      .pipe(catchError(this.handleError)
       );
  }

  deleteUser(id: number) {
    if (!this.auth.checkLogin) {
      this.router.navigateByUrl('login');
    }
    return this.http.delete(this.url + '/' + id, this.getHttp())
    .pipe(catchError(this.handleError));

  }



  getLoggedInUser() {
    if (!this.auth.checkLogin) {
      this.router.navigateByUrl('login');
    }
    return this.http.get<User>(this.url + '/loggedIn', this.getHttp())
      .pipe( catchError(this.handleError));
  }

  // P R I V A T E  M E T H O D S

  private handleError(error?: any) {
    console.error('Something Broke');
    console.log(error);
    return throwError('Server Error');
  }

  private getHttp() {
    const credentials = this.auth.getCredentials();
    return {
      headers: {
        Authorization: `Basic ${credentials}`,
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      }
    };
   }
}
