import { Routes } from '@angular/router';

import { DashboardComponent } from '../../dashboard/dashboard.component';
import { UserProfileComponent } from '../../user-profile/user-profile.component';
import { TableListComponent } from '../../table-list/table-list.component';
import { TypographyComponent } from '../../typography/typography.component';
import { IconsComponent } from '../../icons/icons.component';
import { MapsComponent } from '../../maps/maps.component';
import { NotificationsComponent } from '../../notifications/notifications.component';
import { UpgradeComponent } from '../../upgrade/upgrade.component';
import { AuthGuard } from 'app/shared/auth-guard';
import { AccesoGuard } from 'app/shared/acceso-guard';
import { StgProductosComponent } from 'app/stg-productos/stg-productos.component';
import { StgClientesComponent } from 'app/stg-clientes/stg-clientes.component';
import { StgContabilidadComponent } from 'app/stg-contabilidad/stg-contabilidad.component';
import { StgProveedoresComponent } from 'app/stg-proveedores/stg-proveedores.component';

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
    { path: 'principal',      component: DashboardComponent, canActivate: [AccesoGuard]},
    { path: 'productos',      component: StgProductosComponent, canActivate: [AccesoGuard] },
    { path: 'clientes',      component: StgClientesComponent, canActivate: [AccesoGuard] },
    { path: 'contable',      component: StgContabilidadComponent, canActivate: [AccesoGuard] },
    { path: 'proveedores',      component: StgProveedoresComponent, canActivate: [AccesoGuard] },
];
