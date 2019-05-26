import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProductosComponent } from './productos/productos.component';
import { ServiciosComponent } from './servicios/servicios.component';
import { SomosSystigComponent } from './somos-systig/somos-systig.component';
import { ContactoSystigComponent } from './contacto-systig/contacto-systig.component';

const routes: Routes = [
  { path: '', redirectTo: '/casa', pathMatch: 'full' },
  { path: 'casa', component: HomeComponent },
  { path: 'productos', component: ProductosComponent },
  { path: 'servicios', component: HomeComponent },
  { path: 'somos', component: SomosSystigComponent },
  { path: 'contacto', component: ContactoSystigComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
