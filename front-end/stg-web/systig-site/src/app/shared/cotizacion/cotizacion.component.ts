import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CotizacionGeneral, ItemCotizacion, EtapaCotizacion } from '../objetos';
import { MatDialog } from '@angular/material/dialog';
import { CotizacionService } from './cotizacion.service';
import { DlgCaracteristicasComponent } from './dlg-caracteristicas/dlg-caracteristicas.component';
import { DlgResumenComponent } from './dlg-resumen/dlg-resumen.component';

@Component({
  selector: 'stg-cotizacion',
  templateUrl: './cotizacion.component.html',
  styleUrls: ['./cotizacion.component.scss']
})
export class CotizacionComponent implements OnInit {

  cotizacionCompleta: CotizacionGeneral;
  constructor(private ref: ChangeDetectorRef, public dialog: MatDialog, private cotServ: CotizacionService) { }

  ngOnInit(): void {
    this.cotServ.getCotizacion().subscribe((res: CotizacionGeneral) => {
      this.cotizacionCompleta = res;
    });
  }

  SeleccionUnica($event) {
    this.ref.markForCheck();
    this.cotizacionCompleta.etapas[this.cotizacionCompleta.etapaActual].items.forEach(function (element) {
      if (element.id === $event.source.value) {
        element.seleccionado = true;
      } else {
        element.seleccionado = false;
      }
    });
  }

  OpenCaracteristicas(itemId: ItemCotizacion) {
    this.dialog.open(DlgCaracteristicasComponent, {
      width: '400px',
      data: itemId
    });

  }

  OpenResumenCotizacion(itemId: CotizacionGeneral) {
    this.dialog.open(DlgResumenComponent, {
      width: '400px',
      data: itemId
    });
  }

  SiguienteEtapa() {
    this.ref.markForCheck();
    let seleccion = 0;
    this.cotizacionCompleta.etapas[this.cotizacionCompleta.etapaActual].items.forEach(element => {
      if (element.seleccionado) {
        seleccion += 1;
      }
    });
    if (seleccion > 0) {
      this.cotServ.SiguienteEtapaCotizacion(this.cotizacionCompleta.etapas[this.cotizacionCompleta.etapaActual])
        .subscribe((res: EtapaCotizacion) => {
          if ((this.cotizacionCompleta.etapaActual + 1) < this.cotizacionCompleta.etapas.length) {
            this.cotizacionCompleta.etapaActual += 1;
          } else {
            this.cotizacionCompleta.etapas.push(res);
            this.cotizacionCompleta.etapaActual += 1;
          }
        });
    } else {
      alert('Debe seleccionar alguna de las opciones para continuar');
    }
    console.log(JSON.stringify(this.cotizacionCompleta.etapas[this.cotizacionCompleta.etapaActual]));
  }

  PreviaEtapa() {
    this.ref.markForCheck();
    this.cotizacionCompleta.etapas.pop();
    this.cotizacionCompleta.etapaActual -= 1;
    console.log(JSON.stringify(this.cotizacionCompleta.etapas[this.cotizacionCompleta.etapaActual]));
  }

}
