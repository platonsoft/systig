import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutModule } from '@angular/cdk/layout';
import { 
          MatToolbarModule, 
          MatButtonModule, 
          MatSidenavModule, 
          MatIconModule, 
          MatListModule, 
          MatButtonToggleModule, 
          MatMenuModule,
          MatCardModule,
          MatSlideToggleModule,
          MatDialogModule,
          MatRadioModule,
          MatFormFieldModule,
          MatOptionModule,
          MatSelectModule,
          MatInputModule,
          MatStepperModule
        } from '@angular/material';
import {NgbModule, NgbCarouselModule} from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './home/home.component';
import { CotizacionComponent } from './cotizacion/cotizacion.component';
import { DialCotDetalleComponent } from './cotizacion/dial-cot-detalle/dial-cot-detalle.component';
import { ItemCotComponent } from './cotizacion/item-cot/item-cot.component';
import { HttpClientModule } from '@angular/common/http';
import { ResumenComponent } from './cotizacion/resumen/resumen.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CotizacionComponent,
    DialCotDetalleComponent,
    ItemCotComponent,
    ResumenComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatButtonToggleModule,
    MatMenuModule,
    MatRadioModule,
    MatFormFieldModule,
    MatOptionModule,
    MatSelectModule,
    MatInputModule,
    MatStepperModule,
    NgbModule,
    NgbCarouselModule,
    MatCardModule,
    MatSlideToggleModule,
    FormsModule, 
    ReactiveFormsModule,
    MatDialogModule,
    HttpClientModule
  ],
  entryComponents: [
    DialCotDetalleComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
