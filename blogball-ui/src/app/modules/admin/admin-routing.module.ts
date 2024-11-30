import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {CategoryManagementComponent} from "./pages/category-management/category-management.component";

const routes: Routes = [
  { path: 'dashboard' , component: DashboardComponent},
  { path: 'categories-management', component: CategoryManagementComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
