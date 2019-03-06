import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  // F I E L D S

  user: User = new User();
  incomplete =  null;

  // C O N S T R U C T O R

  constructor(private auth: AuthService, private router: Router) { }

  // O N  I N I T

  ngOnInit() {
    this.incomplete = null;
  }

  // M E T H O D S

  register() {
   if (!this.user.username || !this.user.password || !this.user.email) {
       this.incomplete = 'Must Enter All Fields';
       return;
   }
   this.auth.register(this.user).subscribe(
    data => {
      if (data.username === 'notUnique') {
        this.incomplete = 'Username Already In Use';
        return;
      }
      this.incomplete = null;
      this.auth.login(this.user.username, this.user.password).subscribe(
        success => {
          this.router.navigateByUrl('/notes');
        },
      );
    },
    err => {
      this.incomplete = 'User has been deactivated';
      console.log('error in reggistering');
    }

  );

  }
}
