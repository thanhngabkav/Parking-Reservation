import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { TicketService } from '../../service/ticket.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TicketType} from '../../model/ticket.model';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css'],
  providers: [TicketService]
})
export class TicketListComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;

  displayedColumns = ['stt', 'type', 'price', 'service', 'description', 'xxxx'];
  dataSource =  new MatTableDataSource();
  stationID: number;
  constructor(
    private serviceTicket: TicketService
    , private activate: ActivatedRoute
    , private router: Router
  ) { }

  ngOnInit() {
    this.activate.queryParams.subscribe(p => {
      const stationID = p['stationID'];
      const serviceID = p['serviceID'];
      this.stationID = stationID;
      this.serviceTicket.getTicketType(stationID, serviceID).subscribe(types => {
        this.dataSource.data = types;
      });
    });
  }

  public addTicketType() {
    this.router.navigate(['owner/ticket-type/create']);
  }

  public edit(id: TicketType) {
    this.router.navigate(['owner/ticket-type/edit'], {queryParams: {id: this.stationID, typeID: id.stationVehicleTypeID}});
  }

}



