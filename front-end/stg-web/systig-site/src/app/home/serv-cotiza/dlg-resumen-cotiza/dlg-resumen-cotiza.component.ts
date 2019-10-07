import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';
import { MyErrorStateMatcher } from 'src/app/models/MyErrorStateMatcher';
import { DesconectadosService } from 'src/app/shared/desconectados';

@Component({
  selector: 'stg-dlg-resumen-cotiza',
  templateUrl: './dlg-resumen-cotiza.component.html',
  styleUrls: ['./dlg-resumen-cotiza.component.css']
})
export class DlgResumenCotizaComponent implements OnInit {
  selected = 'option2';
  itemsResumen = [];
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  myGroup: FormGroup;
  listaPaises: PaisDisponible[];

  matcher = new MyErrorStateMatcher();
  step = 0;
  constructor(
    public dialogRef: MatDialogRef<DlgResumenCotizaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: CotizacionGeneral,
    private servicios: DesconectadosService
    ) {
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
        ])
     });
     }

  ngOnInit() {
    this.getPaises();
    this.getResumen();

  }

  getPaises() {
    this.servicios.getPaisesDisponibles().subscribe(result=>{
      this.listaPaises = result;
    });
  }

  getResumen() {
    this.data.etapas.forEach(element => {
      element.items.forEach(subelement => {
        if (subelement.seleccionado) {
          this.itemsResumen.push({
            etapaTitulo: element.titulo,
            etapaSeleccion: subelement.titulo
          });
        }
      });
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }

  onFileChange(event) {
    let reader = new FileReader();
    if(event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        console.log(file.name);
      };
    }
  }
}
