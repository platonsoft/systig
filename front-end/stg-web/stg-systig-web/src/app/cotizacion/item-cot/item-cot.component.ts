import { Contrato } from './../../shared/model/contrato';
import { AppCotizacionService } from './../services/app-cotizacion.service';
import { DialCotDetalleComponent } from './../dial-cot-detalle/dial-cot-detalle.component';
import { MatDialog } from '@angular/material';
import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
import { Pregunta } from '../../shared/model/pregunta';
import { Servicio } from '../../shared/model/servicio';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'stgw-item-cot',
  templateUrl: './item-cot.component.html',
  styleUrls: ['./item-cot.component.css']
})
export class ItemCotComponent implements OnInit {

  public listaPreguntas:Pregunta[] = new Array();


  public preguntaActiva:Pregunta;

  public preguntasPresupuesto:Pregunta[] = new Array();

  public presupuesto:Contrato;

  public preguntasPendientes:Pregunta[] = new Array();

  public resumenActivo = false;

  isLinear = true;
  frmGroup: FormGroup[] = new Array(0);
  frmResumen: FormGroup;

  constructor(private _formBuilder: FormBuilder, public dialog: MatDialog, private appCotizaService:AppCotizacionService) {}

  ngOnInit() {
    this.getInitItems();
  }
  
  getInitItems(){
    this.presupuesto= Object.assign({},this.presupuesto);
    this.presupuesto.preguntas = new Array();
    this.appCotizaService.getServiciosById(0).subscribe((data:Pregunta)=>{
      this.preguntaActiva=data;
      this.preguntasPresupuesto.push(Object.assign({}, this.preguntaActiva));
    });

    this.getPreguntasList();
  }

  getPregunta(item:number):Pregunta{
    this.appCotizaService.getServiciosById(item).subscribe((data:Pregunta)=>{
      return item;
    });
    return null;
  }

  getPreguntasList(){
    this.appCotizaService.getPreguntasList().subscribe((data:Pregunta[])=>{
      this.listaPreguntas= data;
      for (let frmIndex = 0; frmIndex < this.listaPreguntas.length; frmIndex++) {
        if (!this.listaPreguntas[frmIndex].multisel) {
          this.frmGroup[frmIndex] = this._formBuilder.group({
            Q1: ['', Validators.required]
          });
        }else{
          this.frmGroup[frmIndex] = this._formBuilder.group({
            Q1: ''
          });
        }
      }
      this.frmResumen = this._formBuilder.group({
        Q1: ''
      });
    });
    return null;
  }

  setSeleccion(preg:Pregunta, servi:Servicio){
    if (servi.tipoElemento==0) {
      preg.serviciosCollection.forEach(item => {
        if (item.id== servi.id) {
          item.valor=true;
        }else{
          item.valor=false;
        }
      });
    }
    //servi.valor=(servi.valor)?false:true;
  }

  openCaracteristicas(itemSel:Servicio):void{
    this.dialog.open(DialCotDetalleComponent, {data: [itemSel]});
  }

  resultado(){
    console.log(this.listaPreguntas);
  }
}
