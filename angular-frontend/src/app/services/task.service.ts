import { AuthenticationService } from './authentication.service';
import { tap, catchError } from 'rxjs/operators';
import { BASE_URL } from './../../environments/environment';
import { Observable, of } from 'rxjs';
import { Task } from './../models/task';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TaskService {


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



  constructor(private http: HttpClient, private authenticationService: AuthenticationService) { }

  addTask(task: Task): Observable<any> {
    console.log(task);
    return this.http.post<any>(`${BASE_URL}tasks/add`, task, this.httpOptions)
      .pipe(
        tap((newTask: Task) => console.log(newTask)),
        catchError(this.handleError<any>('addUser'))
      )
  }

  // getAllTasks(): Observable<Task[]> {

  //   return this.http.get<Task[]>(`${BASE_URL}tasks/all`)
  //     .pipe(
  //       catchError(this.handleError<Task[]>('getAllTasks', []))
  //     );
      
  // }

  getAllTasks(): Observable<Task[]> {
    let userId = this.authenticationService.userId;
    return this.http.post<Task[]>(`${BASE_URL}tasks/all`, {userId : userId}, this.httpOptions)
      .pipe(
        catchError(this.handleError<Task[]>('getAllTasks', []))
      );
      
  }



  getTask(taskId: number): Observable<Task> {
    console.log(taskId);
    return this.http.post<Task>(`${BASE_URL}tasks/getTask`, {taskId: taskId}, this.httpOptions)
      .pipe(
        catchError(this.handleError<Task>('getTask'))
      );
      
  }


  updateTask(task: Task): Observable<any> {
    return this.http.put(`${BASE_URL}tasks/update`, task, this.httpOptions)
      .pipe(
        catchError(this.handleError<any>('updateTask'))
      );
  }


  // getTask(taskId: number): Observable<Task> {

  //    const url = `${BASE_URL}/${taskId}`; // here we are constructing a URL with the desired cat's ID
  //    return this.http.get<Task>(url)
  //    .pipe(
      
  //      catchError(this.handleError<Task>(`getCat id=${taskId}`))
  //    );
  // }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error)
      return of(result as T);
    }
  }

}
