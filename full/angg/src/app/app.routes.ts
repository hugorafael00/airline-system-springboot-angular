import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { FlightsComponent } from './flights/flights.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { AppComponent } from './app.component';
import { MyflightComponent } from './myflight/myflight.component';
import { HomepageComponent } from './homepage/homepage.component';
import { authGuard } from './auth/auth.guard';


export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },

    { path: '', redirectTo: 'home', pathMatch: 'full' },

    { path: 'home', component: HomepageComponent,
        children: [
          { path: 'flight', component: FlightsComponent },
          { path: 'admin', component: AdminComponent },
          { path: 'myflights', component: MyflightComponent }
      ],
      canActivate: [authGuard],
     },

    { path: '**', redirectTo: 'home'},

];

