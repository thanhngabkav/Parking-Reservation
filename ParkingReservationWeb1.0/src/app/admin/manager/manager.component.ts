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
import { FormsModule } from '@angular/forms';


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
  public filterText : string;

  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(private router: Router, private ownerService: OwnerService,private userService: UserService) { }

  ngOnInit() {
      this.user = this.userService.getSavedUser();
      console.log(this.user);
      if(this.user == null || this.user.userType!= 'ADMIN'){
          this.router.navigate(['/Error']);
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

  filterData(){
    console.log("Do filter: " + this.filterText)
    if(this.filterText!= ""){
      let filterOwners = this.owners.filter(
        owner => owner.address.includes(this.filterText)
        ||  owner.fullName.includes(this.filterText)
        ||  owner.email.includes(this.filterText)
        ||  owner.phoneNumber.includes(this.filterText)
        ||  owner.status.includes(this.filterText)
      );
      this.dataSource = new MatTableDataSource(filterOwners);
      this.dataSource.paginator = this.paginator;
    }else{
      this.dataSource = new MatTableDataSource(this.owners);
      this.dataSource.paginator = this.paginator;
    }
    
  }

  viewOwnerDetail(ownerID: string){
      this.router.navigate(['owner/'+ownerID])
  }

}
