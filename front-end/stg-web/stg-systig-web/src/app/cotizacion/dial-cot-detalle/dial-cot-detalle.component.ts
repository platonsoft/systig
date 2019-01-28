import { ItemCotiza } from './../../shared/model/item-cotiza';
import { MAT_DIALOG_DATA } from '@angular/material';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'stgw-dial-cot-detalle',
  templateUrl: './dial-cot-detalle.component.html',
  styleUrls: ['./dial-cot-detalle.component.css']
})
export class DialCotDetalleComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public itemData: ItemCotiza) { }

  ngOnInit() {
  }

}
