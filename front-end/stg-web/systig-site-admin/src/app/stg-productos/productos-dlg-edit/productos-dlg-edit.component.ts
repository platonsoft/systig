import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { MyErrorStateMatcher } from 'src/app/objetos/MyErrorStateMatcher';
import { MatDialogRef, MAT_DIALOG_DATA, MatTableDataSource } from '@angular/material';
import { Historial, PRODUCTOS_HISTORIAL_DATA } from 'src/app/objetos/Objetos';

@Component({
  selector: 'stg-productos-dlg-edit',
  templateUrl: './productos-dlg-edit.component.html',
  styleUrls: ['./productos-dlg-edit.component.scss']
})
export class ProductosDlgEditComponent implements OnInit {

  myGroup: FormGroup;
  matcher = new MyErrorStateMatcher();

  displayedColumns: string[] = ['descripcion', 'accion', 'fecha'];
  dataSourceHistorico = new MatTableDataSource<Historial>(PRODUCTOS_HISTORIAL_DATA);

  constructor(public dialogRef: MatDialogRef<ProductosDlgEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.myGroup = new FormGroup({
      nombreFormControl: new FormControl('', [
        Validators.required,
      ]),
      cantidadMinimaFormControl: new FormControl('', [
        Validators.required
      ]),
      descripcionFormControl: new FormControl('', [
        Validators.required
      ]),
      cantidadExistenteFormControl: new FormControl('', [
        Validators.required
      ]),
      descuentoFormControl: new FormControl('', [
        Validators.required
      ]),
      impuestoFormControl: new FormControl('', [
        Validators.required
      ]),
      categoriaFormControl: new FormControl('', [
        Validators.required
      ]),
      almacenFormControl: new FormControl('', [
        Validators.required
      ]),
      proveedorFormControl: new FormControl('', [
        Validators.required
      ]),
      unidadFormControl: new FormControl('', [
        Validators.required
      ]),
      montoFormControl: new FormControl('', [
        Validators.required
      ]),
      isCursoControl: new FormControl('', []),
    });
  }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  // convenience getter for easy access to form fields
  get f() { return this.myGroup.controls; }

  onSubmit() {

      // stop here if form is invalid
      if (this.myGroup.invalid) {
          return;
      }

      alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.myGroup.value));
  }

}
