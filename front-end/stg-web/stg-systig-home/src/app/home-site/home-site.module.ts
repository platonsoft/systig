import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeSiteComponent } from './home-site.component';
import { HomeComponent } from './pages/home/home.component';
import { PorqueComponent } from './pages/porque/porque.component';
import { ProductosComponent } from './pages/productos/productos.component';
import { ServiciosComponent } from './pages/servicios/servicios.component';
import { QuienesSomosComponent } from './pages/quienes-somos/quienes-somos.component';
import { ContactoComponent } from './pages/contacto/contacto.component';
import { FooterComponent } from './footer/footer.component';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule } from '@angular/material';
import {NgbModule, NgbPaginationModule, NgbAlertModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [HomeSiteComponent, HomeComponent, PorqueComponent, ProductosComponent, ServiciosComponent, QuienesSomosComponent, ContactoComponent, FooterComponent, MainMenuComponent],
  imports: [
    CommonModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    NgbModule,
    NgbPaginationModule,
    NgbAlertModule
  ]
})
export class HomeSiteModule { }
