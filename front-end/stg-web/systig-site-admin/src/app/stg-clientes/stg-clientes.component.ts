import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { ClienteItem, CLIENTES_DATA, ExperienciAItem, FORMACION_DATA, FormacionItem } from '../objetos/Objetos';
import { ProfileDlgEditComponent } from '../user-profile/profile-dlg-edit/profile-dlg-edit.component';

@Component({
  selector: 'stg-stg-clientes',
  templateUrl: './stg-clientes.component.html',
  styleUrls: ['./stg-clientes.component.scss']
})
export class StgClientesComponent implements OnInit {

  displayedColumns: string[] = [ 'identificacion', 'tipoCliente', 'razonSocial', 'edicion'];
  dataSource = new MatTableDataSource<ClienteItem>(CLIENTES_DATA);
  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }

  openDialogEditFormacion(itemSelect: FormacionItem, tipoSentencia: string): void {
    const dialogRef = this.dialog.open(ProfileDlgEditComponent, {
      width: '80wh',
      data: {item: itemSelect != null ? itemSelect : new FormacionItem(), sentencia: tipoSentencia}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (result.sentencia === 'nuevo') {
          FORMACION_DATA.push(result.item);
          this.dataSource = new MatTableDataSource<ClienteItem>(CLIENTES_DATA);
        } else if (result.sentencia === 'borrar') {
          FORMACION_DATA.forEach(element => {
            if (element.id === itemSelect.id) {
              FORMACION_DATA.splice(element.id, 1);
            }
          });
        }
      }
      console.log('The dialog was closed');
      console.log('Resukt: ' + JSON.stringify(result));
    });
}

openDialogEditExperiencia(itemSelect: ExperienciAItem, tipoSentencia: string): void {
  const dialogRef = this.dialog.open(ProfileDlgEditComponent, {
    width: '80wh',
    data: {item: itemSelect != null ? itemSelect : new ExperienciAItem(), sentencia: tipoSentencia}
  });
  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');
    console.log('Resukt: ' + JSON.stringify(result));
  });
}

}
