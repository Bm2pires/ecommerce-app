import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { NgbdCarouselBasic } from './components/landing-page/carousel/carousel.component';
import { NgbCarouselModule, NgbDropdown, NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';


@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    NgbdCarouselBasic,
    NavbarComponent,
    FooterComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    NgbCarouselModule,
    NgbDropdownModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
