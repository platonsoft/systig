import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { PaisDisponible } from '../objetos';
import { first } from 'rxjs/operators';
import { SesionService } from './sesion.service';

@Component({
  selector: 'stg-sesion',
  templateUrl: './sesion.component.html',
  styleUrls: ['./sesion.component.scss']
})
export class SesionComponent implements OnInit {

  options: FormGroup;
  registroGroup: FormGroup;
  selected = 'option2';
  listaPaises: PaisDisponible[];
  hidePass = true;
  submitted = false;

  constructor(private servicios: SesionService) {
    this.options = new FormGroup({
      emailFormControl: new FormControl('', [
        Validators.required,
        Validators.email,
      ]),
      passwordFormControl: new FormControl('', [
        Validators.required
      ])
    });

    this.registroGroup = new FormGroup({
      email: new FormControl('', [
        Validators.required,
        Validators.email,
      ]),
      razonSocial: new FormControl('', [
        Validators.required
      ]),
      telefonoMovil: new FormControl('', [
        Validators.required
      ]),
      codigoPostal: new FormControl('', [
        Validators.required
      ])
    });
  }

  ngOnInit(): void {
  }

  get f() { return this.options.controls; }
  get r() { return this.registroGroup.controls; }

  onSubmit() {
      if (this.f.emailFormControl.invalid) {
        return;
      }

      this.servicios.login(this.f.emailFormControl.value, this.f.passwordFormControl.value)
          .pipe(first())
          .subscribe(
            data => {
              console.log('Inicio de sesion Satisfactorio');
            },
            error => {
              alert('Acceso Denegado');
            }
          );
  }

  onSubmitRegistro() {
    if (this.r.invalid) {
      console.log('Invalidado');
      return;
    }

    this.servicios.registrar(this.registroGroup.value)
      .pipe(first())
      .subscribe(
        data => {
            console.log('Resultado --->' + JSON.stringify(data));
        },
        error => {
          console.log('Ocurrio un error --->' + JSON.stringify(error));
        }
      );
  }

}
