import { PromoteUserComponent } from './components/manager/promote-user/promote-user.component';
import { ShowTasksComponent } from './components/manager/show-tasks/show-tasks.component';
import { EmpUpdateTaskComponent } from './components/employee/emp-update-task/emp-update-task.component';
import { DashboardComponent } from './components/manager/dashboard/dashboard.component';
import { EmpShowTasksComponent } from './components/employee/emp-show-tasks/emp-show-tasks.component';
import { EmpHomeComponent } from './components/employee/emp-home/emp-home.component';

import { ForgotpasswordComponent } from './components/forgotpassword/forgotpassword.component';
import { CreateTaskComponent } from './components/manager/create-task/create-task.component';
import { AuthGuard } from './guards/auth.guard';
import { HomeComponent } from './components/manager/home/home.component';
import { ShowUsersComponent } from './components/manager/show-users/show-users.component';
import { SignupComponent } from './components/signup/signup.component';
import { SigninComponent } from './components/signin/signin.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShowProfileComponent } from './components/manager/show-profile/show-profile.component';
import { EmpDashboardComponent } from './components/employee/emp-dashboard/emp-dashboard.component';

const routes: Routes = [

  {path: '', redirectTo: '/api/signin', pathMatch: 'full'},
  {path: 'api/signin', component: SigninComponent},
  {path: 'api/signup', component: SignupComponent},
  {path: 'api/forgotPass', component: ForgotpasswordComponent},

  {path: 'api/mngHome', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'api/createTask', component: CreateTaskComponent, canActivate: [AuthGuard]},
  {path: 'api/showTasks', component: ShowTasksComponent, canActivate: [AuthGuard]}, 
  {path: 'api/dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  {path: 'api/users', component: ShowUsersComponent, canActivate: [AuthGuard]},
  {path: 'api/getUser', component: ShowProfileComponent, canActivate: [AuthGuard]},
  {path: 'api/promoteUser/:id', component: PromoteUserComponent, canActivate: [AuthGuard]},

  {path: 'api/empHome', component: EmpHomeComponent, canActivate: [AuthGuard]},
  {path: 'api/empShowTasks', component: EmpShowTasksComponent, canActivate: [AuthGuard]},
  {path: 'api/empDashboard', component: EmpDashboardComponent, canActivate: [AuthGuard]},
  {path: 'api/updateTask/:id', component: EmpUpdateTaskComponent, canActivate: [AuthGuard]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
