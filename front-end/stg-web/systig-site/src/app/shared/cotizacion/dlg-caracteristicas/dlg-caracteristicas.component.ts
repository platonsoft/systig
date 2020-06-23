import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ItemCotizacion } from '../../objetos';

@Component({
  selector: 'stg-dlg-caracteristicas',
  templateUrl: './dlg-caracteristicas.component.html',
  styleUrls: ['./dlg-caracteristicas.component.scss']
})
export class DlgCaracteristicasComponent implements OnInit {

  step = 0;
  constructor(
    public dialogRef: MatDialogRef<DlgCaracteristicasComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ItemCotizacion
    ) { }

  ngOnInit(): void {
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

}
