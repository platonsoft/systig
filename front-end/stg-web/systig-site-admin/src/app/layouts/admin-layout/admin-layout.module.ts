import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from 'src/app/app.component';
import { AdminLayoutComponent } from './admin-layout.component';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTooltipModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { ComponentsModule } from 'src/app/components/components.module';
import { DashboardComponent } from 'src/app/dashboard/dashboard.component';
import { ProductosDlgEditComponent } from 'src/app/stg-productos/productos-dlg-edit/productos-dlg-edit.component';
import { ProductosDlgImpExpComponent } from 'src/app/stg-productos/productos-dlg-imp-exp/productos-dlg-imp-exp.component';
import { ClienteDlgEditComponent } from 'src/app/stg-clientes/cliente-dlg-edit/cliente-dlg-edit.component';
import { StgClientesComponent } from 'src/app/stg-clientes/stg-clientes.component';
import { StgContabilidadComponent } from 'src/app/stg-contabilidad/stg-contabilidad.component';
import { StgProductosComponent } from 'src/app/stg-productos/stg-productos.component';
import { StgProveedoresComponent } from 'src/app/stg-proveedores/stg-proveedores.component';
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
export class AdminLayoutModule { }
