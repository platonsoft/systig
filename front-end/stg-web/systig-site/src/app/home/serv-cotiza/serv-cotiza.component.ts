import { Component, OnInit, ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DlgCaracteristicaComponent } from './dlg-caracteristica/dlg-caracteristica.component';
import { DesconectadosService } from 'src/app/servicios/desconectados';
import { DlgResumenCotizaComponent } from './dlg-resumen-cotiza/dlg-resumen-cotiza.component';

@Component({
  selector: 'stg-serv-cotiza',
  templateUrl: './serv-cotiza.component.html',
  styleUrls: ['./serv-cotiza.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ServCotizaComponent implements OnInit {
  cotizacionCompleta: CotizacionGeneral;
  constructor(private ref: ChangeDetectorRef, public dialog: MatDialog, private servicio: DesconectadosService) { }

  ngOnInit() {
    this.servicio.getCotizacion().subscribe((res: CotizacionGeneral) => {
      this.cotizacionCompleta = res;
    });
  }

  SeleccionUnica($event) {
    this.ref.markForCheck();
    this.cotizacionCompleta.etapas[this.cotizacionCompleta.etapaActual].items.forEach( function(element) {
      if (element.id === $event.source.value) {
        element.seleccionado = true;
      } else {
        element.seleccionado = false;
      }
    });
  }

  OpenCaracteristicas(itemId: ItemCotizacion) {
    this.dialog.open(DlgCaracteristicaComponent, {
      width: '250px',
      data: itemId
    });
  }

  OpenResumenCotizacion(itemId: CotizacionGeneral) {
    this.dialog.open(DlgResumenCotizaComponent, {
      width: '250px',
      data: itemId
    });
  }

  SiguienteEtapa() {
    this.ref.markForCheck();
    this.servicio.SiguienteEtapaCotizacion(this.cotizacionCompleta.etapas[this.cotizacionCompleta.etapaActual])
    .subscribe((res: EtapaCotizacion) => {
      if ((this.cotizacionCompleta.etapaActual + 1) < this.cotizacionCompleta.etapas.length) {
        this.cotizacionCompleta.etapaActual += 1;
      } else {
        this.cotizacionCompleta.etapas.push(res);
        this.cotizacionCompleta.etapaActual += 1;
      }
    });
    console.log(JSON.stringify(this.cotizacionCompleta.etapas[this.cotizacionCompleta.etapaActual]));
  }

  PreviaEtapa() {
    this.ref.markForCheck();
    this.cotizacionCompleta.etapaActual -= 1;
    console.log(JSON.stringify(this.cotizacionCompleta.etapas[this.cotizacionCompleta.etapaActual]));
  }

  EtapaFinal() {

  }

}
