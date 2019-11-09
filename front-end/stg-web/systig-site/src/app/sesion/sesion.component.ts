import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DesconectadosService } from '../shared/desconectados';
import { ConectadosService } from '../shared/conectados';
import { first } from 'rxjs/operators';

@Component({
  selector: 'stg-sesion',
  templateUrl: './sesion.component.html',
  styleUrls: ['./sesion.component.css']
})
export class SesionComponent implements OnInit {

  options: FormGroup;
  selected = 'option2';
  listaPaises: PaisDisponible[];
  hidePass = true;
  submitted = false;

  constructor(private servicios: ConectadosService) {
    this.options = new FormGroup({
      emailFormControl: new FormControl('', [
        Validators.required,
        Validators.email,
      ]),
      passwordFormControl: new FormControl('', [
        Validators.required
      ]),
      nombreFormControl: new FormControl('', [
        Validators.required
      ]),
      movilFormControl: new FormControl('', [
        Validators.required
      ]),
      codPostalFormControl: new FormControl('', [
        Validators.required
      ]),
      paisFormControl: new FormControl('', [
        Validators.required
      ])
    });

  }

  ngOnInit() {
  }

  get f() { return this.options.controls; }

  onSubmit() {
      this.submitted = true;
      console.log("Enviando...");


      if (this.f.emailFormControl.invalid) {
        console.log("Invalidado");
          return;
      }

      this.servicios.login(this.f.emailFormControl.value, this.f.passwordFormControl.value)
          .pipe(first())
          .subscribe(
              data => {
                  // this.router.navigate([this.returnUrl]);
                  console.log("Resultado ---> " + JSON.stringify(data));
              },
              error => {
                  // this.error = error;
                  // this.loading = false;
              });
  }

}
