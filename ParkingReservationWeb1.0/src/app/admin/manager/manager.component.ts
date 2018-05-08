import { OnInit } from '@angular/core';
import { Owner } from '../owner';
import { OwnerService } from '../owner.service';
import { Router } from '@angular/router';
import {Component, ViewChild} from '@angular/core';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import {MatIconModule} from '@angular/material';
import {MatPaginatorModule} from '@angular/material/paginator';
import { User } from '../../_models/user';
import { UserService } from '../../_services/user.service';


@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css'],
  providers: [OwnerService, UserService]
})
export class ManagerComponent implements OnInit {


  public owners : Owner[];
  public displayedColumns = ['fullName', 'email', 'phoneNumber', 'address', 'numStations', 'status','actions'];
  public dataSource;
  private user : User;

  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(private router: Router, private ownerService: OwnerService,private userService: UserService) { }

  ngOnInit() {
      this.user = this.userService.getSavedUser();
      console.log(this.user);
      if(this.user == null || this.user.userType!= 'ADMIN'){
          this.router.navigate(['/']);
      }
  }

  ngAfterViewInit() {
    this.getAllOwner();
  }
  getPageListOwner(page: number){
    this.ownerService.findPageList(page).subscribe(
      owners =>{
        this.owners = owners;
      },
      err => {
        console.log(err);
      }
    )
  }


  getAllOwner(){
    this.ownerService.findAllOwners().subscribe(
      owners =>{
        this.owners = owners;
        this.dataSource = new MatTableDataSource(this.owners);
        this.dataSource.paginator = this.paginator;
      },
      err => {
        console.log(err);
      }
    )
  }
}
