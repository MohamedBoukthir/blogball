import {Component, OnInit} from '@angular/core';
import {StorageService} from "../../authentication/storage.service";
import {AuthenticationService} from "../../authentication/authentication.service";
import {NavigationEnd, Router} from "@angular/router";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent implements OnInit{

  isUserLoggedIn : boolean = false;
  isAdminLoggedIn : boolean = false;

  isOpen = false;


  toggle = () => {
    this.isOpen = !this.isOpen;
  };

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

  logOut(): void {
    this.authenticationService.logOut().subscribe({
      next: () => {
        this.router.navigate(['/sign-in']);
      },
      error: (err) => {
        console.error('Logout failed', err);
        // Optionally, show an error message to the user
      },
    });
  }

}
