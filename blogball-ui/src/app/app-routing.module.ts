import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {AboutComponent} from "./pages/about/about.component";
import {TermsAndConditionComponent} from "./pages/terms-and-condition/terms-and-condition.component";
import {ContactUsComponent} from "./pages/contact-us/contact-us.component";
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'sign-in', component: LoginComponent},
  {path: 'sign-up', component: RegisterComponent},

  // user module
  { path: 'user' , loadChildren: () => import('./modules/user/user.module').then(m => m.UserModule) },
  // admin module
  { path: 'admin' , loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule) },


  {path: 'about', component: AboutComponent},
  {path: 'terms-and-conditions', component: TermsAndConditionComponent},
  {path: 'contact', component: ContactUsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
