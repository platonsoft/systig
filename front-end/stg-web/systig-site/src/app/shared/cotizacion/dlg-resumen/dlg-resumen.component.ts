import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { PaisDisponible, CotizacionGeneral } from '../../objetos';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CotizacionService } from '../cotizacion.service';
import { MyErrorStateMatcher } from '../../MyErrorStateMatcher';
import { AngularFirestore } from '@angular/fire/firestore';

@Component({
  selector: 'stg-dlg-resumen',
  templateUrl: './dlg-resumen.component.html',
  styleUrls: ['./dlg-resumen.component.scss']
})
export class DlgResumenComponent implements OnInit {

  selected = 'option2';
  itemsResumen = [];
  myGroup: FormGroup;
  listaPaises: PaisDisponible[];
  matcher = new MyErrorStateMatcher();
  step = 0;

  constructor(
    public dialogRef: MatDialogRef<DlgResumenComponent>,
    @Inject(MAT_DIALOG_DATA) public data: CotizacionGeneral,
    private ctzServ: CotizacionService,
    private afs: AngularFirestore
  ) {
    this.myGroup = new FormGroup({
      emailFormControl: new FormControl('', [
        Validators.required,
        Validators.email,
      ]),
      nombreProyFormControl: new FormControl('', [
        Validators.required
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

  onSubmit(){
    if (!this.myGroup.invalid) {
      const shirtsCollection = this.afs.collection<any>('cotizaciones');
      shirtsCollection.add({
        nombreProyecto: this.myGroup.get('nombreProyFormControl').value,
        nombre: this.myGroup.get('nombreFormControl').value,
        email: this.myGroup.get('emailFormControl').value,
        movil: this.myGroup.get('movilFormControl').value,
        pais: this.myGroup.get('paisFormControl').value,
        cotizacion: this.data,
      });
      alert('Muchas gracias, nos estaremos comunicando con usted a la brevedad...');
    } else {
      alert('Hubo un error');
    }
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
