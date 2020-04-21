import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { PaisDisponible, CotizacionGeneral } from '../../objetos';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CotizacionService } from '../cotizacion.service';
import { MyErrorStateMatcher } from '../../MyErrorStateMatcher';

@Component({
  selector: 'stg-dlg-resumen',
  templateUrl: './dlg-resumen.component.html',
  styleUrls: ['./dlg-resumen.component.scss']
})
export class DlgResumenComponent implements OnInit {

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
    public dialogRef: MatDialogRef<DlgResumenComponent>,
    @Inject(MAT_DIALOG_DATA) public data: CotizacionGeneral,
    private ctzServ: CotizacionService
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

  ngOnInit(): void {
    this.getPaises();
    this.getResumen();
  }

  getPaises() {
    this.ctzServ.getPaisesDisponibles().subscribe(result=>{
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
