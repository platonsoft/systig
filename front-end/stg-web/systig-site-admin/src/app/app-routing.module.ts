import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'stg-admin',
    pathMatch: 'full',
  }, {
    path: '',
    component: AdminLayoutComponent,
    children: [
        {
      path: '',
      loadChildren: './layouts/admin-layout/admin-layout.module#AdminLayoutModule'
  }]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
