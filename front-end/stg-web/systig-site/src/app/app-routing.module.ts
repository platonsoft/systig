import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './public/home/home.component';
import { ProductosComponent } from './public/productos/productos/productos.component';
import { ServiciosComponent } from './public/productos/servicios/servicios.component';
import { SomosComponent } from './public/somos/somos.component';
import { ContactoComponent } from './public/contacto/contacto.component';
import { PageNotFoundComponent } from './shared/page-not-found/page-not-found.component';


const routes: Routes =  [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'productos/productos', component: ProductosComponent },
  { path: 'productos/servicios', component: ServiciosComponent },
  { path: 'somos', component: SomosComponent },
  { path: 'contacto', component: ContactoComponent },
  { path: '**', component: HomeComponent },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
