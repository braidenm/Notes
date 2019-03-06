import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  // F I E L D S

  // C O N S T R U C T O R

  constructor(private auth: AuthService, private router: Router) { }

  // O N  I N I T

  ngOnInit() {
  }

  // P U B L I C  M E T H O D S

  isLoggedIn() {
    return this.auth.checkLogin();
  }


}
