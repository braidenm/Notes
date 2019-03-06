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

  // C O N S T R U C T O R

  constructor(private auth: AuthService, private router: Router) { }

  // O N  I N I T

  ngOnInit() {
  }

  // M E T H O D S

  register() {
   console.log(this.user);
   this.auth.register(this.user).subscribe(
    data => {
      this.auth.login(this.user.username, this.user.password).subscribe(
        success => {
          this.router.navigateByUrl('/notes');
        },
      );
    },
    err => console.log('error in reggistering')

  );

  }
}
