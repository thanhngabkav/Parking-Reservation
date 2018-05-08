import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { AuthenticationService } from '../_services/authentication.service';
import { User } from '../_models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [UserService, AuthenticationService]
})
export class HomeComponent implements OnInit {

  constructor(private router: Router, private userService: UserService, private auth: AuthenticationService) { }

  ngOnInit() {
    
  }

 



}
