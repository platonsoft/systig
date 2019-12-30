import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, ValidationErrors } from '@angular/forms';
import { MyErrorStateMatcher } from 'src/app/objetos/MyErrorStateMatcher';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ProfileDlgEditComponent } from 'src/app/user-profile/profile-dlg-edit/profile-dlg-edit.component';
import { Cliente, Pais, Etapa, CampanaPublicidad, Respuesta, Currencies } from 'src/app/objetos/Objetos';
import { ClientesService } from '../clientes.service';

@Component({
  selector: 'stg-cliente-dlg-edit',
  templateUrl: './cliente-dlg-edit.component.html',
  styleUrls: ['./cliente-dlg-edit.component.scss']
})
export class ClienteDlgEditComponent implements OnInit {

  myGroup: FormGroup;
  matcher = new MyErrorStateMatcher();
  listaPaises: Pais[];
  listaEtapas: Etapa[];
  listaCampanas: CampanaPublicidad[];
  listaMonedas: Currencies[];

  selPais: Pais = { numericCode: '0', currencies: [{ code: '0', name: null }] };
  selMoneda: Currencies = { code: '0', name: null };
  selEtapa: Etapa;
  selCampanaPublicidad: CampanaPublicidad;

  constructor(public dialogRef: MatDialogRef<ClienteDlgEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public clienteService: ClientesService) {

    this.myGroup = new FormGroup({
      tipoIdentificacion: new FormControl('', [
        Validators.required
      ]),
      numeroIdentificacion: new FormControl('', [
        Validators.required
      ]),
      nombres: new FormControl('', [
        Validators.required
      ]),
      apellidos: new FormControl('', [
        Validators.required
      ]),
      razonSocial: new FormControl('', [
        Validators.required
      ]),
      tipoCliente: new FormControl('', [
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
      isCursoControl: new FormControl('', []),
    });

    this.clienteService.getListaPaises().subscribe(result => {
      if (result) {
        this.listaPaises = result;
        if (this.data.item) {
          this.listarMonedas(this.data.item.pais);
        }
      }
    });
  }

  listarMonedas(codePais: string) {
    let paisSel: Pais;

    this.listaPaises.forEach(function (value) {
      if (value.numericCode === codePais) {
        paisSel = value;
      }
    });
    this.selPais = paisSel;
    this.listaMonedas = paisSel.currencies;
    if (this.data.item.moneda) {
      const monedaSel = this.data.item.moneda;
      let moneda1: Currencies = this.listaMonedas[0];
      this.listaMonedas.forEach(function (param) {
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
    if (event.isUserInput) {
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

    const clienteItem: Cliente = this.myGroup.value;
    this.data.item = clienteItem;

    this.data.item.pais = this.selPais.numericCode;
    this.data.item.moneda = this.selMoneda.code;
    // this.data.item.etapa = this.selEtapa;
    // this.data.item.campanaPublicidad = this.selCampanaPublicidad;

    console.log('Antes:  ' + JSON.stringify(clienteItem));

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
