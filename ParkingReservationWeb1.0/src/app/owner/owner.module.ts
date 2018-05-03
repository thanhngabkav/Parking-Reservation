import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OwnerRoutingModule } from './owner-routing.module';
import { RegisterComponent } from './register/register.component';

@NgModule({
  imports: [
    CommonModule,
    OwnerRoutingModule
  ],
  declarations: [RegisterComponent]
})
export class OwnerModule { }
