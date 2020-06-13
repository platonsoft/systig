import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ProveedoresService } from './proveedores.service';
import { ProveedoresDlgEditComponent } from './proveedores-dlg-edit/proveedores-dlg-edit.component';
import { ProveedoresDataSource, Proveedor, Respuesta, Empresa } from 'app/shared/objetos';
import { FormGroup, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'app-proveedores',
  templateUrl: './stg-proveedores.component.html',
  styleUrls: ['./stg-proveedores.component.scss']
})
export class StgProveedoresComponent implements OnInit {

  displayedColumns: string[] = ['identificacion', 'razonSocial', 'edicion'];
  dataSource = new ProveedoresDataSource(this.proveedoresService);
  empresaSelect: Empresa = {
    tipoIdentificacion: {
      abrev: 'RIF',
      nombre: 'Registro Informacion Fiscal'
    }
  }

  inedit = false;

  myEmpresaGroup: FormGroup;
  
  constructor(public dialog: MatDialog, public proveedoresService: ProveedoresService) {
    this.myEmpresaGroup = new FormGroup(
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
      ciudad: new FormControl('', [
        Validators.required
      ]),
      pais: new FormControl('', [
        Validators.required
      ]),
      disponible: new FormControl(),
      codigoPostal: new FormControl('', [
        Validators.required
      ])
    });
  }

  ngOnInit() {
  }

  onNuevoProveedor(){
    this.inedit = !this.inedit;
  }

  openDialogNuevoProveedores(): void {
    const proveedorNuevo: Proveedor = {
      idProveedor: 0,
      pais: '170',
      envios: {idEmpresaEnvios: 0}
    };

    const dialogRef = this.dialog.open(ProveedoresDlgEditComponent, {
      width: '80vw',
      data: { item: proveedorNuevo, sentencia: 'nuevo' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.proveedoresService.insertarProveedor(result.item).subscribe(resultado => {
          if (result) {
            this.dataSource = new ProveedoresDataSource(this.proveedoresService);
            console.log('Despues:  ' + JSON.stringify(resultado));
          }
        });
      }
      console.log('The dialog was closed');
      console.log('Resukt: ' + JSON.stringify(result));
    });
  }

  openDialogEditProveedores(itemSelect: Proveedor, idProveedor: number): void {
    const dialogRef = this.dialog.open(ProveedoresDlgEditComponent, {
      width: '80vw',
      data: { item: itemSelect, sentencia: 'editar' }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.proveedoresService.actualizarProveedor(result.item, idProveedor)
          .subscribe((resultado: Respuesta) => {
            if (result) {
              this.dataSource = new ProveedoresDataSource(this.proveedoresService);
              console.log('Despues:  ' + JSON.stringify(resultado));
            }
          });
      }
      console.log('The dialog was closed');
      console.log('Resukt: ' + JSON.stringify(result));
    });
  }

  openDialogBorrarProveedores(idProveedor: number): void {
    const dialogRef = this.dialog.open(ProveedoresDlgEditComponent, {
      width: '80vw',
      data: { item: null, sentencia: 'borrar' }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.proveedoresService.eliminarProveedor(idProveedor).subscribe(resultado => {
          if (resultado) {
            this.dataSource = new ProveedoresDataSource(this.proveedoresService);
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
