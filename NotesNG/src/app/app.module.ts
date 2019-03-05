import { AboutComponent } from './components/about/about.component';
import { AdminComponent } from './components/admin/admin.component';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NgModule } from '@angular/core';
import { NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { NavigationComponent } from './components/navigation/navigation.component';
import { NotesComponent } from './components/notes/notes.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RegisterComponent } from './components/register/register.component';

@NgModule({
  declarations: [
    AboutComponent,
    AppComponent,
    AdminComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent,
    NavigationComponent,
    NotesComponent,
    NotFoundComponent,
    RegisterComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    NgbModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
