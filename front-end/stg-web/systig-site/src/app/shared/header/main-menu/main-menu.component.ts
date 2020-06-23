import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'stg-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.scss']
})
export class MainMenuComponent implements OnInit {

  menuLat = false;
  @Input() isHeader: boolean;

  constructor() { }

  ngOnInit(): void {
  }

  ToggleMenuLat() {
    this.menuLat = !this.menuLat;
  }
}
