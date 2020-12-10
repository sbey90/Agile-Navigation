import { SignInData } from './../models/signindata';
import { tap, catchError } from 'rxjs/operators';
import { BASE_URL } from './../../environments/environment';
import { Observable, of } from 'rxjs';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {


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
  userRole: string = "";
  userId : number = 0;
  //data : any;

  authenticate(signInData : SignInData): Observable<any> {
    
    console.log(signInData);
    return this.http.post<any>(`${BASE_URL}signin`, signInData, this.httpOptions)
      .pipe(
        tap((data:any) => {
        
        if(data.status == "signin success"){
            this.isAuthenticated = true;

            if(data.role == 'Software Manager'){
              this.userRole = data.role;
              this.userId = data.userId;
              this.router.navigate(['api/mngHome'])
            } else {
              this.userRole = data.role;
              this.userId = data.userId;
              this.router.navigate(['api/empHome'])
            }
            
        } else if(data.status == "signin failed"){
          console.log(data.status);
        }
        

        console.log("new data" + data.userId)}),       
        catchError(this.handleError<any>('addUser'))
      )
  }

  signOut(){
    this.isAuthenticated = false;
    this.router.navigate(['']);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error)
      return of(result as T);
    }
  }


}