import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CentralServiceService {

  user: string | null = '';

  constructor(private http: HttpClient) { }

  postLogin(cred:{username:string, password:string}): Observable<any>{
   return this.http.post('http://localhost:8081/rest/utente/login', cred, {observe: 'response'}).pipe(
     tap((data:HttpResponse<any>) => {
      this.user = data.headers.get('Auth');
     })
   )
  }

  postRegistrazione(cred:{ nome:string, cognome:string, email:string, username:string, password:string}):Observable<any>{
    return this.http.post("http://localhost:8081/rest/utente/registrazione",cred, {observe:'response'}).pipe(
      tap((data:HttpResponse<any>) => {
        this.user = data.headers.get('Auth');
      })
    )
  }


}
