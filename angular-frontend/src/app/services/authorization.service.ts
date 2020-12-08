import { BASE_URL } from './../../environments/environment.prod';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {


  httpOptions = {
    headers: new HttpHeaders(
      {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': 'http://localhost:4200',
        'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, DELETE, PUT',
        'Access-Control-Allow-Headers': 'X-Requested-With, Content-Type, Origin, Authorization, Accept, Client-Security-Token, Accept-Encoding, X-Auth-Token, content-type'
      }
    )
  }

  constructor(private http: HttpClient, private router: Router) { }

  isAuthenticated = false;
  data : any;

  signin(username: string, password: string): boolean {

    let loginTemplate = {
      username: username,
      password: password   
    }
    
    this.data = this.http.post<any>(`${BASE_URL}signin`, loginTemplate, this.httpOptions)

    this.data.pipe(
      catchError(this.handleError<any>('Signin failed'))
    )

    if(this.data.status == "process failed"){
      this.isAuthenticated = false;
      return false;
    } else {
      this.isAuthenticated = true;
      this.router.navigate(['api/mngHome']);
      return true;
    }
    
  }



  // public signin(username: string, password: string) {

  //   let loginTemplate = {
  //     username: username,
  //     password: password   
  //   }
    
  //   return this.http.post<any>(`${BASE_URL}signin`, loginTemplate, this.httpOptions)
  //     .pipe(
  //       catchError(this.handleError<any>('Signin failed'))
  //     )
  // }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error)
      return of(result as T);
    }
  }


}
