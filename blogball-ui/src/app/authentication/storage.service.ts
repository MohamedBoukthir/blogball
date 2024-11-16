import {Injectable} from '@angular/core';
import {User} from "../../../types/types";

const USER = 'user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() {
  }

  clean() {
    window.localStorage.clear();
  }

  saveUser(user: User) {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  getUser(): User | null {
    const user = window.localStorage.getItem(USER);
    if (user) {
      return JSON.parse(user);
    }
    return null;
  }

  getUserId(): number {
    const user = this.getUser();
    if (!user) {
      throw new Error('User not logged in.');
    }
    return user.id;
  }

  isLoggedIn(): boolean {
    return window.localStorage.getItem(USER) !== null;
  }

  getUserRole(): string {
    const user = this.getUser();
    if (!user) {
      throw new Error('User not logged in. Please log in to perform this action.');
    }
    return user.roleName;
  }

// Check if the logged-in user is an admin
  isAdminLoggedIn(): boolean {
    return this.isLoggedIn() && this.getUserRole() === "ROLE_ADMIN";
  }

  // Check if the logged-in user is a regular user
  isUserLoggedIn(): boolean {
    return this.isLoggedIn() && this.getUserRole() === "ROLE_USER";
  }
}
