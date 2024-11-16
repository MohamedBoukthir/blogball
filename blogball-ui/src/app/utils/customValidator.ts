import {AbstractControl, ValidationErrors} from '@angular/forms';

export class CustomValidators {

  // PASSWORD VALIDATOR
  static passwordStrength() {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      if (!value) {
        return null; // No value, so no error
      }

      // Define validation checks
      const hasUpperCase = /[A-Z]+/.test(value);
      const hasLowerCase = /[a-z]+/.test(value);
      const hasNumeric = /[0-9]+/.test(value);
      const hasSpecial = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/.test(value);
      const isValidLength = value.length >= 8;

      // Return specific error messages for failed validation checks
      if (!hasUpperCase) {
        return {password: 'Password must contain at least one uppercase letter.'};
      }
      if (!hasLowerCase) {
        return {password: 'Password must contain at least one lowercase letter.'};
      }
      if (!hasNumeric) {
        return {password: 'Password must contain at least one number.'};
      }
      if (!hasSpecial) {
        return {password: 'Password must contain at least one special character.'};
      }
      if (!isValidLength) {
        return {password: 'Password must be at least 8 characters long.'};
      }

      return null; // If all checks pass, return null (valid password)
    };
  }


  // USERNAME VALIDATOR
  static usernameStrength() {
    return (control: AbstractControl): ValidationErrors | null => {
      // Retrieve the value of the form control that the validator is applied to.
      const value = control.value;

      // If the value is empty, return null (no error) because it hasn't been entered yet.
      if (!value) {
        return null;
      }

      // Check if the username is within the valid length range (3-30 characters).
      const isValidLength = value.length >= 3 && value.length <= 30;

      // Check if the username contains only letters (a-z, A-Z), numbers (0-9), and underscores (_).
      const hasNoSpecialChars = /^[a-zA-Z0-9_]+$/.test(value);

      // If the username is not within the valid length range, return an error.
      if (!isValidLength) {
        return {username: 'Username must be between 3-30 characters long.'};
      }

      // If the username contains special characters other than letters, numbers, or underscores, return an error.
      if (!hasNoSpecialChars) {
        return {username: 'Username can only contain letters, numbers, and underscores.'};
      }

      // If the username passes both checks, return null (indicating that it is valid).
      return null;
    };
  }

}
