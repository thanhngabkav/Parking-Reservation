import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OwnerRoutingModule } from './owner-routing.module';
import { RegisterComponent } from './register/register.component';
import { MatButtonModule
  , MatCheckboxModule
  , MatCardModule
  , MatStepperModule
  , MatFormFieldModule
  , MatListModule
  , MatIconModule
  , MatInput
  , MatInputModule,
  MatNativeDateModule,
  MatSlideToggleModule,
  MatDatepickerModule,
  MatOptionModule,
  MatSelectModule,
  MatTableModule,
  MatPaginatorModule,
  MatSortModule,
  MatSnackBarModule,
  MatCommonModule} from '@angular/material';
import { StationComponent } from './station/station.component';
import { ChartsModule } from 'ng2-charts';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ManagerComponent } from './manager/manager.component';
import { MapComponent } from './station/map/map.component';
import { AgmCoreModule } from '@agm/core';
import { TicketTypeComponent } from './ticket-type/ticket-type.component';
import { TicketListComponent } from './ticket-type/ticket-list/ticket-list.component';
import { CdkTableModule } from '@angular/cdk/table';
import { CdkStepperModule } from '@angular/cdk/stepper';
import { HttpModule } from '@angular/http';
import { AccountComponent } from './account/account.component';
@NgModule({
  imports: [
    CommonModule,
    OwnerRoutingModule,

    HttpModule,
    FormsModule,
    BrowserModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,

    // mat
    MatListModule,
    MatSortModule,
    MatIconModule,
    MatCardModule,
    MatInputModule,
    CdkTableModule,
    MatTableModule,
    MatCommonModule,
    MatButtonModule,
    MatOptionModule,
    MatSelectModule,
    MatStepperModule,
    CdkStepperModule,
    MatSnackBarModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatPaginatorModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatSlideToggleModule,
    BrowserAnimationsModule,

    CdkTableModule,
    
    // chart
    ChartsModule,

    // map
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAOet-_egIrSp-vfWNH11JTKE4xi98NUfs'
    })
  ],
  declarations: [
    RegisterComponent,
    StationComponent,
    ManagerComponent,
    MapComponent,
    TicketTypeComponent,
    TicketListComponent,
    AccountComponent
  ]
})
export class OwnerModule { }
