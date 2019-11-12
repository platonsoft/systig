import { Routes } from '@angular/router';

import { DashboardComponent } from '../../dashboard/dashboard.component';
import { UserProfileComponent } from 'src/app/user-profile/user-profile.component';
import { StgProductosComponent } from 'src/app/stg-productos/stg-productos.component';
import { StgClientesComponent } from 'src/app/stg-clientes/stg-clientes.component';
import { StgContabilidadComponent } from 'src/app/stg-contabilidad/stg-contabilidad.component';
import { AuthGuard } from 'src/app/objetos/auth-guard';
import { AccesoGuard } from 'src/app/objetos/acceso-guard';
import { StgProveedoresComponent } from 'src/app/stg-proveedores/stg-proveedores.component';

export const AdminLayoutRoutes: Routes = [
    // {
    //   path: '',
    //   children: [ {
    //     path: 'dashboard',
    //     component: DashboardComponent
    // }]}, {
    // path: '',
    // children: [ {
    //   path: 'userprofile',
    //   component: UserProfileComponent
    // }]
    // }, {
    //   path: '',
    //   children: [ {
    //     path: 'icons',
    //     component: IconsComponent
    //     }]
    // }, {
    //     path: '',
    //     children: [ {
    //         path: 'notifications',
    //         component: NotificationsComponent
    //     }]
    // }, {
    //     path: '',
    //     children: [ {
    //         path: 'maps',
    //         component: MapsComponent
    //     }]
    // }, {
    //     path: '',
    //     children: [ {
    //         path: 'typography',
    //         component: TypographyComponent
    //     }]
    // }, {
    //     path: '',
    //     children: [ {
    //         path: 'upgrade',
    //         component: UpgradeComponent
    //     }]
    // }
    { path: 'inicio/:token', canActivate: [AuthGuard] },
    { path: 'profile',      component: UserProfileComponent, canActivate: [AccesoGuard] },
    { path: 'principal',      component: DashboardComponent, canActivate: [AccesoGuard] },
    { path: 'productos',      component: StgProductosComponent, canActivate: [AccesoGuard] },
    { path: 'clientes',      component: StgClientesComponent, canActivate: [AccesoGuard] },
    { path: 'contable',      component: StgContabilidadComponent, canActivate: [AccesoGuard] },
    { path: 'proveedores',      component: StgProveedoresComponent, canActivate: [AccesoGuard] },
];
