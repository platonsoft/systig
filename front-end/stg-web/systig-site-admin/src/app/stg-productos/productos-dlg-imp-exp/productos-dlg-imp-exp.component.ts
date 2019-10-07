import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'stg-productos-dlg-imp-exp',
  templateUrl: './productos-dlg-imp-exp.component.html',
  styleUrls: ['./productos-dlg-imp-exp.component.scss']
})
export class ProductosDlgImpExpComponent implements OnInit {

  step = 0;

  constructor(public dialogRef: MatDialogRef<ProductosDlgImpExpComponent>) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  setStep(index: number) {
    this.step = index;
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
