import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {CategoryManagementComponent} from "./pages/category-management/category-management.component";
import {authGuard} from "../../authentication/auth.guard";

const routes: Routes = [
  { path: 'dashboard' , component: DashboardComponent, canActivate: [authGuard] },
  { path: 'categories-management', component: CategoryManagementComponent, canActivate: [authGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
