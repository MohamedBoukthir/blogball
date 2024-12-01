import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FooterComponent} from './layout/footer/footer.component';
import {HeaderComponent} from './layout/header/header.component';
import {HomeComponent} from './pages/home/home.component';
import {SingleCategoryComponent} from './category/components/single-category/single-category.component';
import {SinglePostComponent} from './posts/single-post/single-post.component';
import {TermsAndConditionComponent} from './pages/terms-and-condition/terms-and-condition.component';
import {ContactUsComponent} from './pages/contact-us/contact-us.component';
import {CommentFormComponent} from './comments/comment-form/comment-form.component';
import {CommentListComponent} from './comments/comment-list/comment-list.component';
import {AboutComponent} from './pages/about/about.component';
import {PostCardComponent} from './posts/post-card/post-card.component';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {ThemeSwitcherComponent} from './layout/theme-switcher/theme-switcher.component';
import {LoginComponent} from './pages/login/login.component';
import {RegisterComponent} from './pages/register/register.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {httpInterceptorProviders} from "./helpers/http.interceptor";
import {ToastrModule} from "ngx-toastr";
import {HeroComponent} from './layout/hero/hero.component';
import { LatestPostsComponent } from './posts/latest-posts/latest-posts.component';
import {NgOptimizedImage, provideImgixLoader} from '@angular/common';
import { SliderComponent } from './layout/slider/slider.component';
import { CategoriesComponent } from './category/components/categories/categories.component';


@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    HomeComponent,
    SingleCategoryComponent,
    SinglePostComponent,
    TermsAndConditionComponent,
    ContactUsComponent,
    CommentFormComponent,
    CommentListComponent,
    AboutComponent,
    PostCardComponent,
    ThemeSwitcherComponent,
    LoginComponent,
    RegisterComponent,
    HeroComponent,
    LatestPostsComponent,
    SliderComponent,
    CategoriesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FaIconComponent,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 2000,
      progressBar: true,
      progressAnimation: 'decreasing',
    }),
    NgOptimizedImage
  ],
  providers: [
    httpInterceptorProviders,
    provideImgixLoader('https://cdn.dribbble.com/users/494229/screenshots/1601132/media/cce678fcc99418b1421798e4c470c483.gif')
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
