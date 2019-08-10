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
  myGroup: FormGroup;
  matcher = new MyErrorStateMatcher();
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);
  displayedColumns: string[] = [ 'titulo', 'institucion', 'duracion', 'edicion'];
  dataSource = new MatTableDataSource<FormacionItem>(FORMACION_DATA);

  constructor(public dialog: MatDialog) {
    this.myGroup = new FormGroup({
      emailFormControl: new FormControl('', [
        Validators.required,
        Validators.email,
      ]),
      nombreFormControl: new FormControl('', [
        Validators.required
      ]),
      apellidoFormControl: new FormControl('', [
        Validators.required
      ]),
      sexoFormControl: new FormControl('', [
        Validators.required
      ]),
      tipoDocumentoFormControl: new FormControl('', [
        Validators.required
      ]),
      numeroDocumentoFormControl: new FormControl('', [
        Validators.required
      ]),
      movilFormControl: new FormControl('', [
        Validators.required
      ]),
      paisFormControl: new FormControl('', [
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
          }else if (result.sentencia === 'borrar') {
            alert('Borrando');
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
