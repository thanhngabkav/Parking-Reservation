import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { TicketService } from '../../service/ticket.service';
import { ActivatedRoute } from '@angular/router';

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
  constructor(
    private serviceTicket: TicketService
    , private activate: ActivatedRoute
  ) { }

  ngOnInit() {
    this.activate.queryParams.subscribe(p => {
      let stationID = p['stationID'];
      this.serviceTicket.getTicketType(stationID).subscribe(types => {
        this.dataSource.data = types
      })
    })
  
  }

  ngAfterViewInit() {
    
  }

}

export interface xxx {
  stt: number;
  type: string;
  service: string;
  price: number;
  description: string;
}



