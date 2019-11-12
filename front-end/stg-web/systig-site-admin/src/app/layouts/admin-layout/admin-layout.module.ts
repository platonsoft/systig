import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../dashboard/dashboard.component';
import { ChartistModule } from 'ng-chartist';


import {
  MatButtonModule,
  MatInputModule,
  MatRippleModule,
  MatFormFieldModule,
  MatTooltipModule,
  MatSelectModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatSlideToggleModule,
  MatCheckboxModule,
  MatTableModule,
  MatDialogModule,
  MatIconModule,
  MatExpansionModule
} from '@angular/material';
import { UserProfileComponent } from 'src/app/user-profile/user-profile.component';
import { ProfileDlgEditComponent } from '../../user-profile/profile-dlg-edit/profile-dlg-edit.component';
import { StgProductosComponent } from '../../stg-productos/stg-productos.component';
import { StgClientesComponent } from '../../stg-clientes/stg-clientes.component';
import { StgContabilidadComponent } from '../../stg-contabilidad/stg-contabilidad.component';
import { ClienteDlgEditComponent } from '../../stg-clientes/cliente-dlg-edit/cliente-dlg-edit.component';
import { ProductosDlgEditComponent } from '../../stg-productos/productos-dlg-edit/productos-dlg-edit.component';
import { PesocolDirective } from '../../objetos/pesocol.directive';
import { CurrencyMaskService } from 'src/app/objetos/currency-mask.service';
import { PorcentajeundDirective } from '../../objetos/porcentajeund.directive';
import { PercentMaskService } from 'src/app/objetos/percent-mask.service';
import { ProductosDlgImpExpComponent } from '../../stg-productos/productos-dlg-imp-exp/productos-dlg-imp-exp.component';
import { MatFileUploadModule } from 'angular-material-fileupload';
import { StgProveedoresComponent } from '../../stg-proveedores/stg-proveedores.component';
import { ContabilidadDlgEditComponent } from '../../stg-contabilidad/contabilidad-dlg-edit/contabilidad-dlg-edit.component';
import { ProveedoresDlgEditComponent } from '../../stg-proveedores/proveedores-dlg-edit/proveedores-dlg-edit.component';
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    ReactiveFormsModule,
    ReactiveFormsModule.withConfig({warnOnNgModelWithFormControl: 'never'}),
    MatButtonModule,
    MatRippleModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatTooltipModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSlideToggleModule,
    MatExpansionModule,
    MatFileUploadModule,
    MatCheckboxModule,
    MatTableModule,
    MatDialogModule,
    MatIconModule,
    ChartistModule,
  ],
  declarations: [
    DashboardComponent,
    UserProfileComponent,
    ProfileDlgEditComponent,
    StgProductosComponent,
    StgClientesComponent,
    StgContabilidadComponent,
    ClienteDlgEditComponent,
    ProductosDlgEditComponent,
    PesocolDirective,
    PorcentajeundDirective,
    ProductosDlgImpExpComponent,
    StgProveedoresComponent,
    ContabilidadDlgEditComponent,
    ProveedoresDlgEditComponent
  ],
  entryComponents: [
    ProfileDlgEditComponent,
    ProductosDlgEditComponent,
    ClienteDlgEditComponent,
    ProductosDlgImpExpComponent,
    ProveedoresDlgEditComponent
  ],
  providers: [CurrencyMaskService,
              PercentMaskService,
  ]
})

export class AdminLayoutModule {}
