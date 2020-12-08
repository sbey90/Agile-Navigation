import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SigninComponent } from './components/signin/signin.component';
import { HomeComponent } from './components/manager/home/home.component';
import { SignupComponent } from './components/signup/signup.component';
import { CreateTaskComponent } from './components/manager/create-task/create-task.component';
import { ShowTasksComponent } from './components/manager/show-tasks/show-tasks.component';
import { AssignTaskComponent } from './components/manager/assign-task/assign-task.component';
import { ShowUsersComponent } from './components/manager/show-users/show-users.component';
import { ShowProfileComponent } from './components/manager/show-profile/show-profile.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    HomeComponent,
    SignupComponent,
    CreateTaskComponent,
    ShowTasksComponent,
    AssignTaskComponent,
    ShowUsersComponent,
    ShowProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
