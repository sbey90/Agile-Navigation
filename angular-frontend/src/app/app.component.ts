import { AuthorizationService } from './services/authorization.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
 
  title = 'angular-frontend';

  constructor(public authorizationService: AuthorizationService){

  }
}
