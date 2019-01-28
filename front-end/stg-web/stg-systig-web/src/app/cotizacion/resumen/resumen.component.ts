import { AppCotizacionService } from './../services/app-cotizacion.service';
import { Contrato } from './../../shared/model/contrato';
import { Component, OnInit, Input } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'stgw-resumen',
  templateUrl: './resumen.component.html',
  styleUrls: ['./resumen.component.css']
})
export class ResumenComponent implements OnInit {

  @Input("contrato") contrato: Contrato;
  public paisSelected:any;
  public paises:any=
    [
      {
        nombre:"Colombia",
        monedas:[
          {moneda:"Bitcoin"},
          {moneda:"Dolar EEUU"},
          {moneda:"Peso COL"}
        ]
      },
      {
        nombre:"Venezuela",
        monedas:[
          {moneda:"Bitcoin"},
          {moneda:"Dolar EEUU"},
          {moneda:"Bolivares Soberanos"}
        ]
      },
      {
        nombre:"Peru",
        monedas:[
          {moneda:"Bitcoin"},
          {moneda:"Dolar EEUU"},
          {moneda:"Soles"}
        ]
      }
    ];

    rif = new FormControl('', [Validators.required]);
    nombre = new FormControl('', [Validators.required]);
    email = new FormControl('', [Validators.required, Validators.email]);
    telefono = new FormControl('', [Validators.required]);
    direccion = new FormControl('', [Validators.required]);
    monedaSel = new FormControl('', [Validators.required]);

  getErrorMessage() {
    return this.email.hasError('required') ? 'Debe ingresar un valor valido' :
        this.email.hasError('email') ? 'Email invalido' :
            '';
  }

  constructor(private appCotizaService:AppCotizacionService) { }

  ngOnInit() {
  }

  procesar():void{
    this.contrato.cliente = {
      id:0,
      codigo:"",
      direccion:this.direccion.value,
      email:this.email.value,
      moneda:this.monedaSel.value,
      contratos:new Array(),
      nacionalidad:this.paisSelected.nombre,
      razonSocial: this.nombre.value,
      rif:this.rif.value,
      telMovil:this.telefono.value
    };

    this.appCotizaService.addContrato(this.contrato).subscribe(()=>{
      
    });
    console.log(this.contrato)
  }

}
