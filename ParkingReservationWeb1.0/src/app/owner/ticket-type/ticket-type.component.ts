import { Component, OnInit } from '@angular/core';
import { TicketService } from '../service/ticket.service';
import { FormGroup, FormControl } from '@angular/forms';
import { TicketType, VehicleType, Service } from '../model/ticket.model';
import { ArrayType } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-ticket-type',
  templateUrl: './ticket-type.component.html',
  styleUrls: ['./ticket-type.component.css'],
  providers: [TicketService]
})
export class TicketTypeComponent implements OnInit {

  constructor(
    private ticketService: TicketService
    ,
  ) { }

  formGroup: FormGroup;
  types: VehicleType[] = [];
  services: Service[] = [];
  
  ngOnInit() {

    this.formGroup = new FormGroup({
      vehicleTypes: new FormControl(''),
      services: new FormControl(''),
      price: new FormControl(''),
      description: new FormControl(''),
      types: new FormControl('')
    });

    this.types.push({ id: 1, name: 'Xe Đạp'});
    this.types.push({ id: 2, name: 'Xe Máy'});
    this.types.push({ id: 3, name: 'Ô Tô'});
    this.types.push({ id: 4, name: 'Xe Khách'});
    this.types.push({ id: 5, name: 'Xe Tải'});

    this.services.push({ id: 1, name: 'Giữ Xe'});
    this.services.push({ id: 2, name: 'Sửa Xe'});
    this.services.push({ id: 3, name: 'Đổ Xăng'});
    this.services.push({ id: 4, name: 'Rửa Xe'});
  }

  public onSubmit() {
    let ticket: TicketType = {
      price: this.formGroup.controls['price'].value,
      name: this.formGroup.controls['description'].value,
      serviceID: this.formGroup.controls['services'].value,
      holdingTime: '11:01:01',
      vehicleTypeName: this.formGroup.controls['types'].value,
    }
    this.ticketService.postTicketType(ticket).subscribe(val => {
        console.log(val);
    })
  }

}
