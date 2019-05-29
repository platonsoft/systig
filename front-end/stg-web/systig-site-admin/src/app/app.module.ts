import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTooltipModule} from '@angular/material/tooltip';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ComponentsModule } from './components/components.module';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    AdminLayoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTooltipModule,
    FormsModule,
    HttpClientModule,
    ComponentsModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
