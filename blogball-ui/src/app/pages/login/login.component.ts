import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CustomValidators} from "../../utils/customValidator";
import {AuthenticationService} from "../../authentication/authentication.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {StorageService} from "../../authentication/storage.service";
import {LoginRequest, User} from "../../../../types/types";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private toastrService: ToastrService,
    private storageService: StorageService,
    private router: Router
  ) {
  }

  get username() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ["", [Validators.required, CustomValidators.usernameStrength()]],
      password: ["", [Validators.required, CustomValidators.passwordStrength()]],
    })
  }

  login() {
    if (this.loginForm.invalid) {
      return;
    }
    this.authenticationService.login(this.loginForm.value as LoginRequest).subscribe({
      next: (response) => {
        console.log(response); // For debugging
        if (response.error) {
          this.toastrService.error(response.error, '');
        }
        const user: User = {
          id: response.userDto.id,
          firstName: response.userDto.firstName,
          lastName: response.userDto.lastName,
          username: response.userDto.username,
          email: response.userDto.email,
          roleName: response.userDto.roleName,
          imgUrl: response.userDto.imgUrl || null
        };
        this.storageService.saveUser(user);
        this.authenticationService.setUser(response.userDto);
        if (this.storageService.isUserLoggedIn()) {
          this.router.navigateByUrl("/user/feed"); // navigate the user to the feed
        } else if (this.storageService.isAdminLoggedIn()) {
          this.router.navigateByUrl("/");
        }
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

}
