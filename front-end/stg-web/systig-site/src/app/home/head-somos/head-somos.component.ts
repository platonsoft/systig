import { Component, OnInit } from '@angular/core';
import { DesconectadosService } from 'src/app/shared/desconectados';
import { DlgCurriculumComponent } from './dlg-curriculum/dlg-curriculum.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'stg-head-somos',
  templateUrl: './head-somos.component.html',
  styleUrls: ['./head-somos.component.css']
})
export class HeadSomosComponent implements OnInit {

  empleadosSystig: EmpleadosSystig[];
  constructor(private servicios: DesconectadosService, public dialog: MatDialog) { }

  ngOnInit() {
    this.EmpleadosSystigSplash();
  }

  EmpleadosSystigSplash() {
    this.servicios.getEmpleadosSystig().subscribe(result => {
      this.empleadosSystig = result;
    });
  }

  OpenCurriculum(itemId: EmpleadosSystig) {
    this.dialog.open(DlgCurriculumComponent, {
      width: '250px',
      data: itemId
    });
  }

}
