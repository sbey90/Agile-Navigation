import { WeatherForecastComponent } from './components/weather/weather-forecast/weather-forecast.component';
import { WeatherCurrentComponent } from './components/weather/weather-current/weather-current.component';
import { WeatherContainerComponent } from './components/weather/weather-container/weather-container.component';
import { UpdateProfileComponent } from './components/manager/update-profile/update-profile.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SigninComponent } from './components/signin/signin.component';
import { HomeComponent } from './components/manager/home/home.component';
import { SignupComponent } from './components/signup/signup.component';
import { CreateTaskComponent } from './components/manager/create-task/create-task.component';
import { ShowUsersComponent } from './components/manager/show-users/show-users.component';
import { ShowProfileComponent } from './components/manager/show-profile/show-profile.component';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ForgotpasswordComponent } from './components/forgotpassword/forgotpassword.component';
import { EmpHomeComponent } from './components/employee/emp-home/emp-home.component';
import { EmpShowTasksComponent } from './components/employee/emp-show-tasks/emp-show-tasks.component';
import { EmpDashboardComponent } from './components/employee/emp-dashboard/emp-dashboard.component';
import { DashboardComponent } from './components/manager/dashboard/dashboard.component';
import { EmpUpdateTaskComponent } from './components/employee/emp-update-task/emp-update-task.component';
import { ShowTasksComponent } from './components/manager/show-tasks/show-tasks.component';


import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatInputModule} from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PromoteUserComponent } from './components/manager/promote-user/promote-user.component';


@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    HomeComponent,
    SignupComponent,
    CreateTaskComponent,
    ShowUsersComponent,
    ShowProfileComponent,
    ForgotpasswordComponent,
    EmpHomeComponent,
    EmpShowTasksComponent,
    EmpDashboardComponent,
    DashboardComponent,
    UpdateProfileComponent,
    EmpUpdateTaskComponent,
    ShowTasksComponent,
    WeatherContainerComponent,
    WeatherCurrentComponent,
    WeatherForecastComponent,
    PromoteUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatAutocompleteModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatCheckboxModule,
    MatInputModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,



  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
