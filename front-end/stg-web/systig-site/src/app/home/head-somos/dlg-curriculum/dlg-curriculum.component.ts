import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'stg-dlg-curriculum',
  templateUrl: './dlg-curriculum.component.html',
  styleUrls: ['./dlg-curriculum.component.css']
})
export class DlgCurriculumComponent implements OnInit {

  step = 0;
  constructor(public dialogRef: MatDialogRef<DlgCurriculumComponent>, @Inject(MAT_DIALOG_DATA) public data: EmpleadosSystig) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
  setStep(index: number) {
    this.step = index;
  }
}
