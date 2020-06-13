import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl, EmailValidator } from '@angular/forms';
import { DatosUsuario, TipoDocumento, EmpresasDataSource, Empresa } from 'app/shared/objetos';
import { MatDatepickerInputEvent } from '@angular/material';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
 
  displayedColumns: string[] = ['tipoIdentificacion', 'razonSocial', 'edicion'];
  dataSource = new EmpresasDataSource();
  empresaSelect: Empresa = {
    tipoIdentificacion: {
      abrev: 'RIF',
      nombre: 'Registro Informacion Fiscal'
    }
  }

  myPerfilGroup: FormGroup;
  myEmpresaGroup: FormGroup;
  datos: DatosUsuario = {
    tipoIdentificacion: {
      abrev: 'RIF',
      nombre: 'Registro Informacion Fiscal'
    }
  };

  constructor() {
    this.myPerfilGroup = new FormGroup(
      {
      tipoIdentificacion: new FormControl('', [
        Validators.required
      ]),
      numeroIdentificacion: new FormControl('', [
        Validators.required
      ]),
      fechaNacimiento: new FormControl('', [
        Validators.required
      ]),
      email: new FormControl('', [
        Validators.required
      ]),
      nombres: new FormControl('', [
        Validators.required
      ]),
      apellidos: new FormControl('', [
        Validators.required
      ]),
      direccion: new FormControl('', [
        Validators.required
      ]),
      telefMovil: new FormControl('', [
        Validators.required
      ]),
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

  addEvent(event: MatDatepickerInputEvent<Date>) {
     this.datos.fechaNacimiento = event.value;
  }

  ngOnInit() {
  }

}
