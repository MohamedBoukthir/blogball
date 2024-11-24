import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FeedComponent} from "./pages/feed/feed.component";
import {ProfileComponent} from "./pages/profile/profile.component";

const routes: Routes = [
  { path: 'feed' , component: FeedComponent },
  { path: 'profile' , component: ProfileComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
