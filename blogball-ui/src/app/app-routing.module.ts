import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {SingleCategoryComponent} from "./pages/single-category/single-category.component";
import {SinglePostComponent} from "./pages/single-post/single-post.component";
import {AboutComponent} from "./pages/about/about.component";
import {TermsAndConditionComponent} from "./pages/terms-and-condition/terms-and-condition.component";
import {ContactUsComponent} from "./pages/contact-us/contact-us.component";
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";

const routes: Routes = [
  { path:'', component: HomeComponent },
  { path:'login' , component: LoginComponent},
  { path:'register' , component: RegisterComponent},

  { path: 'category', component: SingleCategoryComponent },
  { path: 'post', component: SinglePostComponent },

  { path: 'about', component: AboutComponent },
  { path: 'terms-and-conditions' , component: TermsAndConditionComponent },
  { path: 'contact' , component: ContactUsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
