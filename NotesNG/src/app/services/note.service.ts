import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Note } from '../models/note';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  // F I E L D S

  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/notes';

  // C O N S T R U C T O R

  constructor(private http: HttpClient, private auth: AuthService, private router: Router) { }

  // P U B L I C  M E T H O D S

 index() {
    if (!this.auth.checkLogin) {
      this.router.navigateByUrl('login');
    }
    return this.http.get<Note[]>(this.url, this.getHttp())
    .pipe(
      catchError(this.handleError)
     );
  }

  show(id: number) {
    if (!this.auth.checkLogin) {
      this.router.navigateByUrl('login');
    }
    return this.http.get<Note[]>(this.url + '/' + id, this.getHttp())
    .pipe( catchError(this.handleError)
     );
  }
  search(search: string) {
    if (!this.auth.checkLogin) {
      this.router.navigateByUrl('login');
    }
    return this.http.get<Note[]>(this.url + '/search/' + search, this.getHttp())
    .pipe( catchError(this.handleError)
     );
  }

  addNote(note: Note) {
    if (!this.auth.checkLogin) {
      this.router.navigateByUrl('login');
    }
    return this.http.post<Note>(this.url, note, this.getHttp())
      .pipe(catchError(this.handleError)
       );

  }

  updateNote(note: Note) {
    if (!this.auth.checkLogin) {
      this.router.navigateByUrl('login');
    }
    return this.http.put<Note>(this.url + '/' + note.id, note, this.getHttp())
      .pipe(catchError(this.handleError)
       );
  }

  deleteNote(id: number) {
    if (!this.auth.checkLogin) {
      this.router.navigateByUrl('login');
    }
    return this.http.delete(this.url + '/' + id, this.getHttp())
    .pipe(catchError(this.handleError));

  }

  // P R I V A T E  M E T H O D S

  private handleError(error: any) {
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
