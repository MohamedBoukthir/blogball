import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CustomValidators} from "../../utils/customValidator";
import {AuthenticationService} from "../../authentication/authentication.service";
import {RegisterRequest} from "../../../../types/types";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{

  registerForm! : FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService : AuthenticationService,
    private toastrService : ToastrService,
    private router : Router
  ) {}

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: ["", [Validators.required, Validators.maxLength(40)]] ,
      lastName: ["", [Validators.required, Validators.maxLength(40)]],
      username: ["", [Validators.required, CustomValidators.usernameStrength()]],
      email: ["", [Validators.required, Validators.email]],
      password: ["", [Validators.required, CustomValidators.passwordStrength()]],
      termsAndConditions: [false, Validators.requiredTrue]
    });
  }

  get firstName() {
    return this.registerForm.get('firstName')
  }

  get lastName() {
    return this.registerForm.get('lastName')
  }
  get username() {
    return this.registerForm.get('username')
  }
  get email() {
    return this.registerForm.get('email')
  }
  get password() {
    return this.registerForm.get('password')
  }
  get termsAndConditions() {
    return this.registerForm.get('termsAndConditions')
  }

  register() {
    if (this.registerForm.invalid) {
      return;
    }
    console.log(this.registerForm.value); // for debugging
    this.authenticationService.register(this.registerForm.value as RegisterRequest).subscribe({
      next: (response) => {
        console.log(response); // For debugging
        if (response.userDto?.id) {
          this.toastrService.success('You are Welcome', 'Registration successful');
          this.router.navigateByUrl('/sign-in'); // Navigate to the login page
        } else {
          this.toastrService.error('Something went wrong', 'Registration failed');
        }
      },
      error: (err) => {
        console.log(err);
        this.toastrService.error('An error occurred', 'Registration failed');
      }
    });
  }


}
