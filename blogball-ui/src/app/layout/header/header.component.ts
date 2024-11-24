import {Component, OnInit} from '@angular/core';
import {StorageService} from "../../authentication/storage.service";
import {AuthenticationService} from "../../authentication/authentication.service";
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{

  isUserLoggedIn : boolean = false;
  isAdminLoggedIn : boolean = false;


  constructor(
    private authenticationService : AuthenticationService,
    private storageService : StorageService,
    private router : Router
  ) {}

  ngOnInit(): void {
        this.updateLoginState();
        this.router.events.subscribe((event) => {
          if (event instanceof NavigationEnd) {
            this.updateLoginState();
          }
        });
    }


  // Updates the login state by checking with the StorageService.
  private updateLoginState(): void {
    this.isAdminLoggedIn = this.storageService.isAdminLoggedIn();
    this.isUserLoggedIn = this.storageService.isUserLoggedIn();
  }

  logOut() {
    this.authenticationService.logOut();
    this.router.navigateByUrl('/sign-in')
  }

}
