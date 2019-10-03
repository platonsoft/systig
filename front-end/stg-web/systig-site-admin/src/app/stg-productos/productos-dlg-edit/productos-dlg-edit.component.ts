import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { MyErrorStateMatcher } from 'src/app/objetos/MyErrorStateMatcher';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ProfileDlgEditComponent } from 'src/app/user-profile/profile-dlg-edit/profile-dlg-edit.component';

@Component({
  selector: 'stg-productos-dlg-edit',
  templateUrl: './productos-dlg-edit.component.html',
  styleUrls: ['./productos-dlg-edit.component.scss']
})
export class ProductosDlgEditComponent implements OnInit {

  myGroup: FormGroup;
  matcher = new MyErrorStateMatcher();

  constructor(public dialogRef: MatDialogRef<ProductosDlgEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private fb: FormBuilder) {
    this.myGroup = new FormGroup({
      emailInstitucionFormControl: new FormControl('', [
        Validators.required,
        Validators.email,
      ]),
      tituloFormControl: new FormControl('', [
        Validators.required
      ]),
      descripcionFormControl: new FormControl('', [
        Validators.required
      ]),
      institucionFormControl: new FormControl('', [
        Validators.required
      ]),
      telefonoInstitucionFormControl: new FormControl('', [
        Validators.required
      ]),
      fechaInicioFormControl: new FormControl('', [
        Validators.required
      ]),
      fechaFinalFormControl: new FormControl('', [
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

      alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.myGroup.value))
  }

}
