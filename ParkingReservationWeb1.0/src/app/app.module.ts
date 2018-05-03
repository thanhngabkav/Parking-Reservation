import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';


import {OwnerModule} from './owner/owner.module'
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { RegisterComponent } from './owner/register/register.component';
import { HomeComponent } from './home/home.component';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    OwnerModule,
    HttpModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
