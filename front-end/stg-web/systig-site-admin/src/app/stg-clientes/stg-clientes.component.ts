import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ClienteDlgEditComponent } from './cliente-dlg-edit/cliente-dlg-edit.component';
import { ClientesService } from './clientes.service';
import { ClientesDataSource, Cliente, Respuesta } from 'app/shared/objetos';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-clientes',
  templateUrl: './stg-clientes.component.html',
  styleUrls: ['./stg-clientes.component.scss']
})
export class StgClientesComponent implements OnInit {

  displayedColumns: string[] = ['identificacion', 'tipoCliente', 'razonSocial', 'edicion'];
  dataSource = new ClientesDataSource(this.clienteService);
  inedit = false;

  myClienteGroup: FormGroup;

  clienteSel: Cliente = {
    tipoIdentificacion: {
      abrev: 'RIF',
      nombre: 'Registro Informacion Fiscal'
    }
  };

  constructor(public dialog: MatDialog, public clienteService: ClientesService) {
    this.myClienteGroup = new FormGroup(
      {
      tipoIdentificacion: new FormControl('', [
        Validators.required
      ]),
      numeroIdentificacion: new FormControl('', [
        Validators.required
      ]),
      email: new FormControl('', [
        Validators.required
      ]),
      razonSocial: new FormControl('', [
        Validators.required
      ]),
      direccion: new FormControl('', [
        Validators.required
      ]),
      telefFijo: new FormControl('', [
        Validators.required
      ]),
      disponible: new FormControl(),
      ciudad: new FormControl('', [
        Validators.required
      ]),
      pais: new FormControl('', [
        Validators.required
      ]),
      codigoPostal: new FormControl('', [
        Validators.required
      ])
    });
  }

  ngOnInit() {

  }

  onNuevoCliente() {
    this.inedit = !this.inedit;
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
