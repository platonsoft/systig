import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTooltipModule} from '@angular/material/tooltip';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { HttpClientModule } from '@angular/common/http';
import { ComponentsModule } from './components/components.module';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProductosDlgEditComponent } from './stg-productos/productos-dlg-edit/productos-dlg-edit.component';
import { ProductosDlgImpExpComponent } from './stg-productos/productos-dlg-imp-exp/productos-dlg-imp-exp.component';
import { ClienteDlgEditComponent } from './stg-clientes/cliente-dlg-edit/cliente-dlg-edit.component';
import { StgClientesComponent } from './stg-clientes/stg-clientes.component';
import { StgContabilidadComponent } from './stg-contabilidad/stg-contabilidad.component';
import { StgProductosComponent } from './stg-productos/stg-productos.component';
import { StgProveedoresComponent } from './stg-proveedores/stg-proveedores.component';
import { UserProfileComponent } from './user-profile/user-profile.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    UserProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTooltipModule,
    HttpClientModule,
    ComponentsModule,
    DashboardComponent,
    ProductosDlgEditComponent,
    ProductosDlgImpExpComponent,
    ClienteDlgEditComponent,
    StgClientesComponent,
    StgContabilidadComponent,
    StgProductosComponent,
    StgProveedoresComponent,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
