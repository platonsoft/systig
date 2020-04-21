import { Component, OnInit } from '@angular/core';
import { ProductoDestacado, PaisDisponible } from 'src/app/shared/objetos';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { MyErrorStateMatcher } from 'src/app/shared/MyErrorStateMatcher';
import { HomeService } from './home.service';

@Component({
  selector: 'stg-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
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

  constructor(private hmServ: HomeService, fb: FormBuilder) {
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

  ngOnInit(): void {
    this.ProductoDestacado();
  }

  ProductoDestacado() {
    this.hmServ.getProductoDestacado().subscribe(result => {
      this.productoDestacado = result;
    });
  }

  getPaises() {
    this.hmServ.getPaisesDisponibles().subscribe(result=>{
      this.listaPaises = result;
    });
  }
}
