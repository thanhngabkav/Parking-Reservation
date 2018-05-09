import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { StationComponent } from './station/station.component';
import { ManagerComponent } from './manager/manager.component';
import { MapComponent } from './station/map/map.component';
import { TicketListComponent } from './ticket-type/ticket-list/ticket-list.component';
import { TicketTypeComponent } from './ticket-type/ticket-type.component';

const routes: Routes = [

  { path: 'owner/register', component: RegisterComponent },
  { path: 'owner/station', component: StationComponent },
  { path: 'owner/manager', component: ManagerComponent },
  { path: 'owner/map', component: MapComponent },
  { path: 'owner/ticket-type', component: TicketListComponent },
  { path: 'owner/ticket-type/create', component: TicketTypeComponent },
  { path: 'owner/ticket-type/edit', component: TicketTypeComponent },
  // { path: 'owner/:id', component: AccountComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OwnerRoutingModule { }
