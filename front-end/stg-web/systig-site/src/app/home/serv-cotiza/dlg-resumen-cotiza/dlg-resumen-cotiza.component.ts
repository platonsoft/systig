import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'stg-dlg-resumen-cotiza',
  templateUrl: './dlg-resumen-cotiza.component.html',
  styleUrls: ['./dlg-resumen-cotiza.component.css']
})
export class DlgResumenCotizaComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DlgResumenCotizaComponent>, @Inject(MAT_DIALOG_DATA) public data: CotizacionGeneral) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
