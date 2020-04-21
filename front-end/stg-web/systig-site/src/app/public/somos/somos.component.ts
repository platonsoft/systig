import { Component, OnInit, Input } from '@angular/core';
import { EmpleadosSystig, ItemSplash } from 'src/app/shared/objetos';
import { SomosService } from './somos.service';
import { MatDialog } from '@angular/material/dialog';
import { DlgCurriculumComponent } from './dlg-curriculum/dlg-curriculum.component';

@Component({
  selector: 'stg-somos',
  templateUrl: './somos.component.html',
  styleUrls: ['./somos.component.scss']
})
export class SomosComponent implements OnInit {

  empleadosSystig: EmpleadosSystig[];
  onExpandSomos: boolean;
  itemExperiencia: ItemSplash[];
  itemExperienciaActivo: ItemSplash;
  idItemActivo = 0;
  interval: any;
  @Input() colorTexto: string;

  constructor(private smsServ: SomosService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.EmpleadosSystigSplash();
    this.ItemsExperiencia();
    this.itemExperienciaActivo = this.itemExperiencia[this.idItemActivo];
    this.IntervalChangeExperiencia();
  }

  EmpleadosSystigSplash() {
    this.smsServ.getEmpleadosSystig().subscribe(result => {
      this.empleadosSystig = result;
    });
  }

  OpenCurriculum(itemId: EmpleadosSystig) {
    this.dialog.open(DlgCurriculumComponent, {
      width: '400px',
      data: itemId
    });
  }

  ToggleSomosDetail() {
    this.onExpandSomos = !this.onExpandSomos;
  }

  IntervalChangeExperiencia() {
    this.interval = setInterval(() => {
      this.idItemActivo += 1;
      if (this.idItemActivo >= 0 && this.idItemActivo <= 2) {
        this.itemExperienciaActivo = this.itemExperiencia[this.idItemActivo];
      } else {
        this.idItemActivo = 0;
        this.itemExperienciaActivo = this.itemExperiencia[this.idItemActivo];
      }
    }, 5000);
  }

  NextNavegadorExperiencia() {
    if (this.idItemActivo >= 0 && this.idItemActivo <= 2) {
      clearInterval(this.interval);
      this.idItemActivo = this.idItemActivo + 1;
      this.itemExperienciaActivo = this.itemExperiencia[this.idItemActivo];
    }
  }

  PrevNavegadorExperiencia() {
    if (this.idItemActivo >= 0 && this.idItemActivo <= 2) {
      clearInterval(this.interval);
      this.idItemActivo = this.idItemActivo - 1;
      this.itemExperienciaActivo = this.itemExperiencia[this.idItemActivo];
    } else if (this.idItemActivo === 3) {
      clearInterval(this.interval);
      this.idItemActivo = 0;
      this.itemExperienciaActivo = this.itemExperiencia[this.idItemActivo];
    }
  }

  ItemsExperiencia() {
    this.smsServ.getItemsSplash('experiencia').subscribe(result => {
      this.itemExperiencia = result;
    });
  }

}
