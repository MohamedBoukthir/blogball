import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FeedComponent} from "./pages/feed/feed.component";
import {ProfileComponent} from "./pages/profile/profile.component";
import {CategoriesComponent} from "../../category/components/categories/categories.component";
import {authGuard} from "../../authentication/auth.guard";

const routes: Routes = [
  { path: 'feed' , component: FeedComponent , canActivate: [authGuard] },
  { path: 'profile' , component: ProfileComponent, canActivate: [authGuard]  },
  { path: 'categories', component: CategoriesComponent, canActivate: [authGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
