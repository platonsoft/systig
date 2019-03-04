import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeSiteComponent } from './home-site/home-site.component';
import { PorqueComponent } from './home-site/pages/porque/porque.component';
import { ProductosComponent } from './home-site/pages/productos/productos.component';
import { ServiciosComponent } from './home-site/pages/servicios/servicios.component';
import { QuienesSomosComponent } from './home-site/pages/quienes-somos/quienes-somos.component';

const routes: Routes = [
  {
    path:'',
    redirectTo:'home',
    pathMatch:'full'
  },
  {
    path:'porque',
    component:PorqueComponent
  },
  {
    path:'productos',
    component:ProductosComponent
  },
  {
    path:'servicios',
    component:ServiciosComponent
  },
  {
    path:'contacto',
    component:PorqueComponent
  },
  {
    path:'nosotros',
    component:QuienesSomosComponent
  },
  {
    path:'home',
    component:HomeSiteComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
