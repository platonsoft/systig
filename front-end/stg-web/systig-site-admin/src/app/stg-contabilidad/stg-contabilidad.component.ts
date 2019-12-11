import { Component, OnInit } from '@angular/core';
import { Documento, DocumentosDataSource, Respuesta } from '../objetos/Objetos';
import { ContabilidadService } from './contabilidad.service';
import { MatDialog } from '@angular/material';
import { DocumentoDlgEditComponent } from './documento-dlg-edit/documento-dlg-edit.component';

@Component({
  selector: 'stg-stg-contabilidad',
  templateUrl: './stg-contabilidad.component.html',
  styleUrls: ['./stg-contabilidad.component.scss']
})
export class StgContabilidadComponent implements OnInit {

  displayedColumns: string[] = [ 'descripcion', 'cantidad', 'edicion'];
  PRODUCTOS_DATA: Documento[];
  dataSource = new DocumentosDataSource(this.contabilidadService);

  tipoDocumento = 'TODOS';

  constructor(public dialog: MatDialog, public contabilidadService: ContabilidadService) {
  }

  ngOnInit() {

  }

  openDialogNuevoDocumento(tipoDoc: string): void {
    const documentoNuevo: Documento = {
      idDocumento: 0
    };

    const dialogRef = this.dialog.open(DocumentoDlgEditComponent, {
      width: '80vw',
      data: {item: documentoNuevo, sentencia: 'NUEVO', tipoDocumento: tipoDoc}
    });
    dialogRef.afterClosed().subscribe(result => {
      this.contabilidadService.crearPedido(result.documento, result.productos).subscribe(resultado=>{
        if (result) {
          console.log('Pedido:  ' + JSON.stringify(resultado));
        }
      });
      console.log('Agregando Documento: ' + JSON.stringify(result.documento));
    });
}

openDialogEditDocumento(itemSelect: Documento, idDocumento: number): void {
  const dialogRef = this.dialog.open(DocumentoDlgEditComponent, {
    width: '80vw',
    data: {item: itemSelect, sentencia: 'editar'}
  });
  dialogRef.afterClosed().subscribe(result => {
    if (result) {
        this.contabilidadService.actualizarDocumento(result.item, idDocumento)
                              .subscribe(( resultado: Respuesta) => {
          if (result) {
            this.dataSource = new DocumentosDataSource(this.contabilidadService);
            console.log('Despues:  ' + JSON.stringify(resultado));
          }
        });
      }
    console.log('The dialog was closed');
    console.log('Resukt: ' + JSON.stringify(result));
    });
}

openDialogBorrarDocumento(idDocumento: number): void {
  const dialogRef = this.dialog.open(DocumentoDlgEditComponent, {
    width: '80vw',
    data: {item: null, sentencia: 'borrar'}
  });
  dialogRef.afterClosed().subscribe(result => {
    if (result) {
      this.contabilidadService.eliminarDocumento(idDocumento).subscribe(resultado => {
        if (resultado) {
            this.dataSource = new DocumentosDataSource(this.contabilidadService);
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

openDialogImportarExportarDocumentos(): void {
  const dialogRef = this.dialog.open(DocumentoDlgEditComponent, {
    width: '80vw'
  });
  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');
    console.log('Resukt: ' + JSON.stringify(result));
  });
}
}
