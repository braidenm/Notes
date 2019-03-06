import { UserService } from './../../services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  //  F I E L D S
  user: User = new User();
  edit: User = null;

  // C O N S T R U C T O R
  constructor(private auth: AuthService, private router: Router, private userServe: UserService) { }

  // O N  I N I T

  ngOnInit() {
    if (this.auth.checkLogin()) {
      this.userServe.getLoggedInUser().subscribe(
        data => {
          this.user = data;
        }
      );
    } else {
      this.router.navigateByUrl('/register');
    }
  }

  // M E T H O D S

  setEdit() {
    this.edit = this.user;
  }

  cancelEdit() {
    this.edit = null;
  }

  saveEdit() {
    this.user = this.edit;
    this.userServe.updateUser(this.user).subscribe(
      data => {
        this.edit = null;
      },
      err => {
        console.log('error updating user');

      }
      );
    }

    deactivate() {
      this.user.enabled = false;

      this.userServe.updateUser(this.user).subscribe(
        data => {
          this.auth.logout();
          this.router.navigateByUrl('/home');
        },
        err => {
          console.log('error updating user');
        }
    );
  }



}
