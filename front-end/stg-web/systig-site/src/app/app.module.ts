import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import { HomeComponent } from './home/home.component';
import { ServCotizaComponent } from './home/serv-cotiza/serv-cotiza.component';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatRadioModule} from '@angular/material/radio';
import {MatDialogModule} from '@angular/material/dialog';
import { DlgCaracteristicaComponent } from './home/serv-cotiza/dlg-caracteristica/dlg-caracteristica.component';
import { DesconectadosService } from './shared/desconectados';
import { HeadSomosComponent } from './home/head-somos/head-somos.component';
import { DlgCurriculumComponent } from './home/head-somos/dlg-curriculum/dlg-curriculum.component';
import { DlgResumenCotizaComponent } from './home/serv-cotiza/dlg-resumen-cotiza/dlg-resumen-cotiza.component';
import { MenuHeaderComponent } from './menu-header/menu-header.component';
import { ProductosComponent } from './productos/productos.component';
import { GlobalHeaderComponent } from './global-header/global-header.component';
import { ServiciosComponent } from './servicios/servicios.component';
import { SomosSystigComponent } from './somos-systig/somos-systig.component';
import { ContactoSystigComponent } from './contacto-systig/contacto-systig.component';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import {MatSelectModule} from '@angular/material/select';
import {MatListModule} from '@angular/material/list';
import { MatFileUploadModule } from 'angular-material-fileupload';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ServCotizaComponent,
    DlgCaracteristicaComponent,
    HeadSomosComponent,
    DlgCurriculumComponent,
    DlgResumenCotizaComponent,
    MenuHeaderComponent,
    ProductosComponent,
    GlobalHeaderComponent,
    ServiciosComponent,
    SomosSystigComponent,
    ContactoSystigComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatSlideToggleModule,
    MatDialogModule,
    MatRadioModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatListModule,
    MatFileUploadModule
  ],
  providers: [DesconectadosService],
  bootstrap: [AppComponent],
  entryComponents: [DlgCaracteristicaComponent, DlgCurriculumComponent, DlgResumenCotizaComponent]
})
export class AppModule { }
