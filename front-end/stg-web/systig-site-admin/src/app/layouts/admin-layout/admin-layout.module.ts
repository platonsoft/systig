import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../dashboard/dashboard.component';
import { UserProfileComponent } from '../../user-profile/user-profile.component';
import { TableListComponent } from '../../table-list/table-list.component';
import { TypographyComponent } from '../../typography/typography.component';
import { IconsComponent } from '../../icons/icons.component';
import { MapsComponent } from '../../maps/maps.component';
import { NotificationsComponent } from '../../notifications/notifications.component';
import { UpgradeComponent } from '../../upgrade/upgrade.component';

import {
  MatButtonModule,
  MatInputModule,
  MatRippleModule,
  MatFormFieldModule,
  MatTooltipModule,
  MatSelectModule,
  MatIconModule,
  MatTableModule,
  MatDialogModule,
  MatCheckboxModule,
  MatAutocompleteModule
} from '@angular/material';
import { StgProveedoresComponent } from 'app/stg-proveedores/stg-proveedores.component';
import { StgClientesComponent } from 'app/stg-clientes/stg-clientes.component';
import { StgContabilidadComponent } from 'app/stg-contabilidad/stg-contabilidad.component';
import { StgProductosComponent } from 'app/stg-productos/stg-productos.component';
import { ProveedoresDlgEditComponent } from 'app/stg-proveedores/proveedores-dlg-edit/proveedores-dlg-edit.component';
import { ClienteDlgEditComponent } from 'app/stg-clientes/cliente-dlg-edit/cliente-dlg-edit.component';
import { ProductosDlgEditComponent } from 'app/stg-productos/productos-dlg-edit/productos-dlg-edit.component';
import { DocumentoDlgEditComponent } from 'app/stg-contabilidad/documento-dlg-edit/documento-dlg-edit.component';
import { ProductosService } from 'app/stg-productos/productos.service';
import { ProveedoresService } from 'app/stg-proveedores/proveedores.service';
import { ClientesService } from 'app/stg-clientes/clientes.service';
import { ContabilidadService } from 'app/stg-contabilidad/contabilidad.service';
import { HttpClientModule } from '@angular/common/http';
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatTableModule,
    MatCheckboxModule,
    MatAutocompleteModule,
    MatSelectModule,
    MatDialogModule,
    MatIconModule,
    MatRippleModule,
    MatFormFieldModule,
    MatInputModule,
    MatTooltipModule,
  ],
  declarations: [
    DashboardComponent,
    StgProveedoresComponent,
    StgClientesComponent,
    StgContabilidadComponent,
    StgProductosComponent,
    ProveedoresDlgEditComponent,
    ClienteDlgEditComponent,
    ProductosDlgEditComponent,
    DocumentoDlgEditComponent,
    UserProfileComponent,
    TableListComponent,
    TypographyComponent,
    IconsComponent,
    MapsComponent,
    NotificationsComponent,
    UpgradeComponent,
  ],
  entryComponents: [
    ProveedoresDlgEditComponent,
    ClienteDlgEditComponent,
    ProductosDlgEditComponent,
    DocumentoDlgEditComponent
  ],
  providers: [
    ProductosService,
    ProveedoresService,
    ClientesService,
    ContabilidadService
  ]
})

export class AdminLayoutModule {}
