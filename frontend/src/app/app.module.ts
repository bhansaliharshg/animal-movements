import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { MovementComponent } from './movement/movement.component';
import { FarmComponent } from './farm/farm.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { DatePipe } from '@angular/common';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    MovementComponent,
    FarmComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxPaginationModule,
    FormsModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
