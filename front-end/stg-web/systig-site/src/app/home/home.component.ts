import { Component, OnInit } from '@angular/core';
import { DesconectadosService } from '../shared/desconectados';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { MyErrorStateMatcher } from '../models/MyErrorStateMatcher';

@Component({
  selector: 'stg-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  productoDestacado: ProductoDestacado;
  myGroup: FormGroup;
  matcher = new MyErrorStateMatcher();
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);
  selected = 'option2';
  listaPaises: PaisDisponible[];

  constructor(private servicios: DesconectadosService, fb: FormBuilder) {
    this.myGroup = new FormGroup({
      emailFormControl: new FormControl('', [
        Validators.required,
        Validators.email,
      ]),
      nombreFormControl: new FormControl('', [
        Validators.required
      ]),
      movilFormControl: new FormControl('', [
        Validators.required
      ]),
      paisFormControl: new FormControl('', [
        Validators.required
      ]),
      asuntoFormControl: new FormControl('', [
        Validators.required
      ]),
      mensajeFormControl: new FormControl('', [
        Validators.required
      ])
   });
  }

  ngOnInit() {
    this.ProductoDestacado();
  }

  ProductoDestacado() {
    this.servicios.getProductoDestacado().subscribe(result => {
      this.productoDestacado = result;
    });
  }

  getPaises() {
    this.servicios.getPaisesDisponibles().subscribe(result=>{
      this.listaPaises = result;
    });
  }
}
