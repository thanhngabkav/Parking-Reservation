import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { VehicleType, Service } from '../model/ticket.model';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {

  constructor() { }

  formGroup: FormGroup;

  types: VehicleType[] = [];
  services: Service[] = [];


  ngOnInit() {
    this.initFormGroup();

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
  

  private initFormGroup() {
    this.formGroup = new FormGroup({
      name: new FormControl("", Validators.required),
      slots: new FormControl(""),
      status: new FormControl(""),
    })
  }

  public location(e: any) {
    console.log(e);
  }

}
