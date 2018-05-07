import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';


import {OwnerModule} from './owner/owner.module'
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { RegisterComponent } from './owner/register/register.component';
import { HomeComponent } from './home/home.component';
import { AdminModule } from './admin/admin.module';
import { HttpClientModule } from '@angular/common/http';
import { AlertComponent } from './_directives/alert/alert.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AlertComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    OwnerModule,
    AdminModule,
    HttpModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
