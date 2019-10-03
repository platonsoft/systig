import { Routes } from '@angular/router';

import { DashboardComponent } from '../../dashboard/dashboard.component';
import { UserProfileComponent } from 'src/app/user-profile/user-profile.component';
import { StgProductosComponent } from 'src/app/stg-productos/stg-productos.component';
import { StgClientesComponent } from 'src/app/stg-clientes/stg-clientes.component';
import { StgFacturasComponent } from 'src/app/stg-facturas/stg-facturas.component';
import { StgContabilidadComponent } from 'src/app/stg-contabilidad/stg-contabilidad.component';

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
    { path: 'stg-admin',      component: DashboardComponent },
    { path: 'profile',      component: UserProfileComponent },
    { path: 'productos',      component: StgProductosComponent },
    { path: 'clientes',      component: StgClientesComponent },
    { path: 'facturas',      component: StgFacturasComponent },
    { path: 'contable',      component: StgContabilidadComponent },
];
