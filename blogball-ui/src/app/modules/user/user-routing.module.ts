import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FeedComponent} from "./pages/feed/feed.component";
import {ProfileComponent} from "./pages/profile/profile.component";
import {CategoriesComponent} from "../../category/categories/categories.component";

const routes: Routes = [
  { path: 'feed' , component: FeedComponent },
  { path: 'profile' , component: ProfileComponent },
  { path: 'categories', component: CategoriesComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
