import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { NgbdCarouselBasic } from './components/landing-page/carousel/carousel.component';
import {  NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { RouterModule, Routes } from '@angular/router';
import { UserInformationComponent } from './components/user-information/user-information.component';
import { HttpClientModule } from '@angular/common/http';
import { AdminComponent } from './components/admin/admin-prod.component';
import { ModalComponent } from './components/admin/modal/modal-prod.component';
import { CustomerComponent } from './components/navbar/navbar-customer/navbar-customer.component';
import { NavbarAdminComponent } from './components/navbar/navbar-admin/navbar-admin.component';
import { AdminUserComponent } from './components/admin-user/admin-user.component';
import { ModalUserComponent } from './components/admin-user/modal-user/modal-user.component';

const appRoutes: Routes = [
  { path: '', component:  LandingPageComponent},
  { path: 'login', component:  LoginComponent},
  { path: 'registration', component:  RegisterComponent},
  { path: 'userInfo', component: UserInformationComponent},
  { path: 'adminProds', component: AdminComponent},
  { path: 'adminUsers', component: AdminUserComponent}


];

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LandingPageComponent,
    NgbdCarouselBasic,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    UserInformationComponent,
    AdminComponent,
    ModalComponent,
    CustomerComponent,
    NavbarAdminComponent,
    AdminUserComponent,
    ModalUserComponent,
  ],
  imports: [
    BrowserModule,
    NgbModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
