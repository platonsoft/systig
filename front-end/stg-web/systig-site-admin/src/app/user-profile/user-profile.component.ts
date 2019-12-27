import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MyErrorStateMatcher } from '../objetos/MyErrorStateMatcher';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { FORMACION_DATA, FormacionItem, ExperienciAItem } from '../objetos/Objetos';
import { ProfileDlgEditComponent } from './profile-dlg-edit/profile-dlg-edit.component';

@Component({
  selector: 'stg-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  frmPropietario: FormGroup;
  matcher = new MyErrorStateMatcher();
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);
  displayedColumns: string[] = [ 'email', 'tipo', 'edicion'];
  dataSource = new MatTableDataSource<FormacionItem>(FORMACION_DATA);

  constructor(public dialog: MatDialog) {
    this.frmPropietario = new FormGroup({
      tipo: new FormControl('', [
        Validators.required
      ]),
      tipoIdentificacion: new FormControl('', [
        Validators.required
      ]),
      nroIdentificacion: new FormControl('', [
        Validators.required
      ]),
      razonSocial: new FormControl('', [
        Validators.required
      ]),
      direccionFiscal: new FormControl('', [
        Validators.required
      ]),
      telefonoLocal: new FormControl('', [
        Validators.required
      ]),
      telefonoMovil: new FormControl('', [
        Validators.required
      ]),
      email: new FormControl('', [
        Validators.required
      ]),
      sitioWeb: new FormControl('', [
        Validators.required
      ]),
      codigoPostal: new FormControl('', [
        Validators.required
      ])
   });

  }
  ngOnInit() {}

  openDialogEditFormacion(itemSelect: FormacionItem, tipoSentencia: string): void {
      const dialogRef = this.dialog.open(ProfileDlgEditComponent, {
        width: '80wh',
        data: {item: itemSelect != null ? itemSelect : new FormacionItem(), sentencia: tipoSentencia}
      });
      dialogRef.afterClosed().subscribe(result => {
        if (result) {
          if (result.sentencia === 'nuevo') {
            FORMACION_DATA.push(result.item);
            this.dataSource = new MatTableDataSource<FormacionItem>(FORMACION_DATA);
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
