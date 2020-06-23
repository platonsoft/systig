import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HomeComponent } from './public/home/home.component';
import { ProductosComponent } from './public/productos/productos/productos.component';
import { ServiciosComponent } from './public/productos/servicios/servicios.component';
import { SomosComponent } from './public/somos/somos.component';
import { ContactoComponent } from './public/contacto/contacto.component';
import { PageNotFoundComponent } from './shared/page-not-found/page-not-found.component';
import { HeaderService } from './shared/header/header.service';
import { MainMenuComponent } from './shared/header/main-menu/main-menu.component';
import { CotizacionComponent } from './shared/cotizacion/cotizacion.component';
import {MatRadioModule} from '@angular/material/radio';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatDialogModule} from '@angular/material/dialog';
import { DlgCaracteristicasComponent } from './shared/cotizacion/dlg-caracteristicas/dlg-caracteristicas.component';
import { CotizacionService } from './shared/cotizacion/cotizacion.service';
import {MatExpansionModule} from '@angular/material/expansion';
import { DlgResumenComponent } from './shared/cotizacion/dlg-resumen/dlg-resumen.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import { HomeService } from './public/home/home.service';
import { SomosService } from './public/somos/somos.service';
import { DlgCurriculumComponent } from './public/somos/dlg-curriculum/dlg-curriculum.component';
import { ProductosService } from './public/productos/productos/productos.service';
import { SesionComponent } from './shared/sesion/sesion.component';
import {MatTabsModule} from '@angular/material/tabs';
import { AngularFireModule } from '@angular/fire';
import { AngularFireAnalyticsModule } from '@angular/fire/analytics';
import { AngularFirestoreModule } from '@angular/fire/firestore';
import { SesionService } from './shared/sesion/sesion.service';
import { environment } from 'src/environments/environment';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    ProductosComponent,
    ServiciosComponent,
    SomosComponent,
    ContactoComponent,
    PageNotFoundComponent,
    MainMenuComponent,
    CotizacionComponent,
    DlgCaracteristicasComponent,
    DlgResumenComponent,
    DlgCurriculumComponent,
    SesionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatRadioModule,
    MatDialogModule,
    MatSlideToggleModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatTabsModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireAnalyticsModule,
    AngularFirestoreModule,
    HttpClientModule
  ],
  providers: [HeaderService, CotizacionService, HomeService, SomosService, ProductosService, SesionService],
  bootstrap: [AppComponent],
  entryComponents: [DlgCaracteristicasComponent, DlgResumenComponent]
})
export class AppModule { }
