import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { NavigationComponent } from '../navigation/navigation.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  // F I E L D S

  user: User = new User();
  invalid: string;

  // C O N S T R U C T O R

  constructor(private auth: AuthService, private router: Router, private navBar: NavigationComponent) { }

  // O N  I N I T

  ngOnInit() {
    this.invalid = null;
  }

  // P U B L I C  M E T H O D S
  login() {
    this.auth.login(this.user.username, this.user.password).subscribe(
      data => {
        this.invalid = null;
        this.navBar.setIsAdmin();
        this.router.navigateByUrl('notes');
      },
      err => this.invalid = 'Invalid Login'

    );

  }
}
