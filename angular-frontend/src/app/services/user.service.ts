import { User } from './../models/user';
import { BASE_URL } from './../../environments/environment.prod';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

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

  constructor(private http: HttpClient) { }


  getAllUsers(): Observable<User[]> {

    return this.http.get<User[]>(`${BASE_URL}getAllUsers`)
      .pipe(
        catchError(this.handleError<User[]>('getAllUsers', []))
      );
      
  }


  addUser(user: User): Observable<any> {
    console.log(user);
    return this.http.post<any>(`${BASE_URL}signup`, user, this.httpOptions)
      .pipe(
        tap((newUser: User) => console.log(newUser)),
        catchError(this.handleError<any>('addUser'))
      )
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error)
      return of(result as T);
    }
  }

}
