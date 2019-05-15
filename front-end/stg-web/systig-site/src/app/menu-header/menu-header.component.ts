import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'stg-menu-header',
  templateUrl: './menu-header.component.html',
  styleUrls: ['./menu-header.component.css']
})
export class MenuHeaderComponent implements OnInit {

  menuLat = false;
  @Input() isHeader: boolean;
  constructor() { }

  ngOnInit() {
  }

  ToggleMenuLat() {
    this.menuLat = !this.menuLat;
  }
}
