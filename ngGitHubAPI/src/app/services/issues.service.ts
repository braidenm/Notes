import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Issue } from '../models/issue';
import { from } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IssuesService {
// F I E L D S

private baseUrl = environment.baseUrl;
private url = this.baseUrl + 'repos/angular/angular/issues';

// C O N S T R U C T O R

constructor(private http: HttpClient) { }

// M E T H O D S

getAll() {
  const since = new Date(Date.now());
  since.setDate(since.getDate() - 7);
  console.log(since.toISOString());
  console.log(this.url + '?since=' + since.toISOString());


  return this.http.get<Issue[]>(this.url + '?since=' + since.toISOString());

}


}
