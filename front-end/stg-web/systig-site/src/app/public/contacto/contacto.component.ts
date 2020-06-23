import { Component, OnInit } from '@angular/core';

import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ContactoService } from './contacto.service';
import { PaisDisponible } from 'src/app/shared/objetos';
import { MyErrorStateMatcher } from 'src/app/shared/MyErrorStateMatcher';

@Component({
  selector: 'stg-contacto',
  templateUrl: './contacto.component.html',
  styleUrls: ['./contacto.component.scss']
})
export class ContactoComponent implements OnInit {

  contactForm: FormGroup;
  listaPaises: PaisDisponible[];
  selected = 'option2';
  matcher = new MyErrorStateMatcher();


  constructor(private ctcServ: ContactoService) {
    this.contactForm = new FormGroup({
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
    this.ctcServ.getPaisesDisponibles().subscribe(result => {
      this.listaPaises = result;
    });
  }

  onSubmit() {
    this.ctcServ.registrarMensajes(this.contactForm);
  }

}
