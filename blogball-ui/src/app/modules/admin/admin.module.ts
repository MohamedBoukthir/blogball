import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CategoryManagementComponent } from './pages/category-management/category-management.component';
import {FormsModule} from "@angular/forms";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";


@NgModule({
  declarations: [
    DashboardComponent,
    CategoryManagementComponent
  ],
    imports: [
        CommonModule,
        AdminRoutingModule,
        FormsModule,
        FaIconComponent
    ]
})
export class AdminModule { }
