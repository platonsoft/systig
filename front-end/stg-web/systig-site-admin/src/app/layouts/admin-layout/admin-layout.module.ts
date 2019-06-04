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
  MatIconModule
} from '@angular/material';
import { UserProfileComponent } from 'src/app/user-profile/user-profile.component';
import { ProfileDlgEditComponent } from '../../user-profile/profile-dlg-edit/profile-dlg-edit.component';
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
    MatCheckboxModule,
    MatTableModule,
    MatDialogModule,
    MatIconModule,
    ChartistModule
  ],
  declarations: [
    DashboardComponent,
    UserProfileComponent,
    ProfileDlgEditComponent
  ],
  entryComponents: [
    ProfileDlgEditComponent
  ]
})

export class AdminLayoutModule {}
