import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  // C O N S T R U C T O R

  constructor(private auth: AuthService, private router: Router) { }

  // O N  I N I T
  ngOnInit() {
  }

  // P U B L I C  M E T H O D S

  logout() {
    this.auth.logout();
    this.router.navigateByUrl('home');
  }
}
