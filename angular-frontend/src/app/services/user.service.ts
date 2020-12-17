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


  getUser(userId: number): Observable<User> {

    return this.http.post<User>(`${BASE_URL}getUser`, {userId: userId}, this.httpOptions)
      .pipe(
        catchError(this.handleError<User>('fgetUser'))
      );
      
  }


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

  updateUser(user: User): Observable<any> {
    return this.http.put(`${BASE_URL}updateUser`, user, this.httpOptions)
      .pipe(
        catchError(this.handleError<any>('updateUser'))
      );
  }

  updateCareer(user: User): Observable<any> {
    return this.http.put(`${BASE_URL}updateCareer`, user, this.httpOptions)
      .pipe(
        catchError(this.handleError<any>('updateCareer'))
      );
  }



   searchUsers(term: string): Observable<User[]> {
    if (!term.trim()) {
      return of([]);
    }

    return  this.http.post<any>(`${BASE_URL}searchUser`, {username: term}, this.httpOptions)
    .pipe(
      tap(x => x.length ? 
       console.log(`found users matching ${term}`) :
       console.log(`no users foudn matching ${term}`)),
       catchError(this.handleError<any>('searchUsers', [])) 
    );
  }


  forgotPass(emailData : any): Observable<any> {

    return this.http.post<any>(`${BASE_URL}forgotPass`, emailData, this.httpOptions)
      .pipe(
        catchError(this.handleError<any>('forgotPass'))
      );
      
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error)
      return of(result as T);
    }
  }

}
