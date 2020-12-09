import { HomeComponent } from './components/manager/home/home.component';
import { ShowUsersComponent } from './components/manager/show-users/show-users.component';
import { SignupComponent } from './components/signup/signup.component';
import { SigninComponent } from './components/signin/signin.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [

  {path: '', redirectTo: '/api/signin', pathMatch: 'full'},
  {path: 'api/signin', component: SigninComponent},
  {path: 'api/signup', component: SignupComponent},
  {path: 'api/mngHome', component: HomeComponent},
  {path: 'api/users', component: ShowUsersComponent},
  {path: 'api/users', component: ShowUsersComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
