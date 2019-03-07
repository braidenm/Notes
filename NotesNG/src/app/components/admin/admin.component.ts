import { UserService } from './../../services/user.service';
import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { NoteService } from 'src/app/services/note.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  //  F I E L D S
  users: User[] = [];
  selectedUser: User = null;

  // C O N S T R U C T O R

  constructor(private auth: AuthService, private router: Router, private noteServ: NoteService, private useServ: UserService) { }

  // O N  I N I T

  ngOnInit() {
    this.useServ.getLoggedInUser().subscribe(
      data => {
        if (data.role !== 'admin') {
          this.router.navigateByUrl('/home');
        }
        this.useServ.index().subscribe(
          result => {
            this.users = result;
          }
        );
      }
    );
  }

  // M E T H O D S

  updateUser() {
    this.useServ.updateUser(this.selectedUser).subscribe();
  }

  cancelSelected() {
    this.selectedUser = null;
  }

  selectUser(user: User) {
    this.selectedUser = user;
  }

  changeActivated() {
    this.selectedUser.enabled = !this.selectedUser.enabled;
    this.updateUser();
  }
  changeRole() {
    this.selectedUser.role = this.selectedUser.role === 'standard' ? 'admin' : 'standard';
    this.updateUser();
  }

}

