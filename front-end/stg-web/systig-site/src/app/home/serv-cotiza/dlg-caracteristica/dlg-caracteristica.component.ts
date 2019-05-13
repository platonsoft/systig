import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'stg-dlg-caracteristica',
  templateUrl: './dlg-caracteristica.component.html',
  styleUrls: ['./dlg-caracteristica.component.css']
})
export class DlgCaracteristicaComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DlgCaracteristicaComponent>, @Inject(MAT_DIALOG_DATA) public data: ItemCotizacion) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
