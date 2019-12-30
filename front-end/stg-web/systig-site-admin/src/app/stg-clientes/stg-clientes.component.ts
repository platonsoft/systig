import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import {Cliente, ClientesDataSource, Respuesta
} from '../objetos/Objetos';
import { ClienteDlgEditComponent } from './cliente-dlg-edit/cliente-dlg-edit.component';
import { ClientesService } from './clientes.service';

@Component({
  selector: 'stg-stg-clientes',
  templateUrl: './stg-clientes.component.html',
  styleUrls: ['./stg-clientes.component.scss']
})
export class StgClientesComponent implements OnInit {

  displayedColumns: string[] = ['identificacion', 'tipoCliente', 'razonSocial', 'edicion'];
  dataSource = new ClientesDataSource(this.clienteService);
  constructor(public dialog: MatDialog, public clienteService: ClientesService) { }

  ngOnInit() {
  }

  openDialogNuevoCliente(): void {
    const clienteNuevo: Cliente = {
      idComprador: 0,
      pais: '170'
    };

    const dialogRef = this.dialog.open(ClienteDlgEditComponent, {
      width: '80vw',
      data: { item: clienteNuevo, sentencia: 'nuevo' }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.clienteService.insertarCliente(result.item).subscribe(resultado => {
          if (result) {
            this.dataSource = new ClientesDataSource(this.clienteService);
            console.log('Despues:  ' + JSON.stringify(resultado));
          }
        });
      }
      console.log('The dialog was closed');
      console.log('Resukt: ' + JSON.stringify(result));
    });
  }

  openDialogEditCliente(itemSelect: Cliente, idCliente: number): void {
    const dialogRef = this.dialog.open(ClienteDlgEditComponent, {
      width: '80vw',
      data: { item: itemSelect, sentencia: 'editar' }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.clienteService.actualizarCliente(result.item, idCliente)
          .subscribe((resultado: Respuesta) => {
            if (result) {
              this.dataSource = new ClientesDataSource(this.clienteService);
              console.log('Despues:  ' + JSON.stringify(resultado));
            }
          });
      }
      console.log('The dialog was closed');
      console.log('Resukt: ' + JSON.stringify(result));
    });
  }

  openDialogBorrarCliente(idCliente: number): void {
    const dialogRef = this.dialog.open(ClienteDlgEditComponent, {
      width: '80vw',
      data: { item: null, sentencia: 'borrar' }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.clienteService.eliminarCliente(idCliente).subscribe(resultado => {
          if (resultado) {
            this.dataSource = new ClientesDataSource(this.clienteService);
            console.log('Despues:  ' + JSON.stringify(resultado));
          }
        });
      }
      console.log('The dialog was closed');
      console.log('Resukt: ' + JSON.stringify(result));
    });
  }

  dlgFuncionDesarrollo() {
    alert('La opcion que ha seleccionado esta en desarrollo, en cualquier momento sera activado');
  }
}
