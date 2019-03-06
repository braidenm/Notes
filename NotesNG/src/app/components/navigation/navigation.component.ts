import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  //  F I E L D S
  loggedIn = false;
  isNavbarCollapsed = true;

  // C O N S T R U C T O R

  constructor(private auth: AuthService) { }

  // O N  I N I T

  ngOnInit() {
    this.loggedIn = this.isLoggedIn();
  }

  // P U B L I C  M E T H O D S

  isLoggedIn() {
    return this.auth.checkLogin();
  }

  changeLoggedIn(bool: boolean) {
    this.loggedIn = bool;
  }

}
