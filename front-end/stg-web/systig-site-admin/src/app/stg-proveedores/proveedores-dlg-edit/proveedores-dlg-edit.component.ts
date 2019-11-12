import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, ValidationErrors } from '@angular/forms';
import { MyErrorStateMatcher } from 'src/app/objetos/MyErrorStateMatcher';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ProfileDlgEditComponent } from 'src/app/user-profile/profile-dlg-edit/profile-dlg-edit.component';
import { Pais, Currencies, Proveedor } from 'src/app/objetos/Objetos';
import { ProveedoresService } from '../proveedores.service';

@Component({
  selector: 'stg-proveedores-dlg-edit',
  templateUrl: './proveedores-dlg-edit.component.html',
  styleUrls: ['./proveedores-dlg-edit.component.scss']
})
export class ProveedoresDlgEditComponent implements OnInit {

  myGroup: FormGroup;
  matcher = new MyErrorStateMatcher();
  listaPaises: Pais[];
  listaMonedas: Currencies[];

  selPais: Pais = {numericCode: '0', currencies: [{code: '0', name: null}]};
  selMoneda: Currencies = {code: '0', name: null};

  constructor(public dialogRef: MatDialogRef<ProveedoresDlgEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public proveedoresService: ProveedoresService) {

    this.myGroup = new FormGroup({
      tipoIdentificacion: new FormControl('', [
        Validators.required
      ]),
      numeroIdentificacion: new FormControl('', [
        Validators.required
      ]),
      nombres: new FormControl(),
      apellidos: new FormControl(),
      razonSocial: new FormControl('', [
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
      direccionFiscal: new FormControl('', [
        Validators.required
      ]),
      codigoPostal: new FormControl('', [
        Validators.required
      ]),
      ciudad: new FormControl('', [
        Validators.required
      ]),
      provincia: new FormControl('', [
        Validators.required
      ]),
      pais: new FormControl('', [
        Validators.required
      ]),
      moneda: new FormControl('', [
        Validators.required
      ]),
      etapa: new FormControl(),
      campanaPublicidad: new FormControl(),
      ranking: new FormControl(),
      isCursoControl: new FormControl(),
    });

    this.proveedoresService.getListaPaises().subscribe(result => {
      if (result) {
          this.listaPaises = result;
          if(this.data.item) {
            this.listarMonedas(this.data.item.pais);
          }
    }});
  }

  listarMonedas(codePais: string) {
    let paisSel: Pais;

    this.listaPaises.forEach( function(value) {
        if (value.numericCode === codePais) {
          paisSel = value;
        }
    });
    this.selPais = paisSel;
    this.listaMonedas = paisSel.currencies;
    if (this.data.item.moneda) {
      const monedaSel = this.data.item.moneda;
      let moneda1: Currencies = this.listaMonedas[0];
      this.listaMonedas.forEach( function(param) {
        if (param.code === monedaSel) {
          moneda1 = param;
        }
      });
      this.selMoneda = moneda1;
    } else {
      this.selMoneda = this.listaMonedas[0];
    }
  }

  seleccionaPais(event) {
    if(event.isUserInput) {
      this.listarMonedas(event.source.value);
    }
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
      this.getFormValidationErrors();
      return;
    }

    const proveedorItem: Proveedor = this.myGroup.value;
    this.data.item = proveedorItem;

    this.data.item.pais = this.selPais.numericCode;
    this.data.item.moneda = this.selMoneda.code;
    console.log('Antes:  ' + JSON.stringify(proveedorItem));
  }

  getFormValidationErrors() {
    Object.keys(this.myGroup.controls).forEach(key => {

    const controlErrors: ValidationErrors = this.myGroup.get(key).errors;
    if (controlErrors != null) {
          Object.keys(controlErrors).forEach(keyError => {
            console.log('Key control: ' + key + ', keyError: ' + keyError + ', err value: ', controlErrors[keyError]);
          });
        }
      });
    }
}
