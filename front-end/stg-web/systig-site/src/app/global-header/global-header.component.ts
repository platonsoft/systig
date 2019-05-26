import { Component, OnInit, Input, Inject, HostListener } from '@angular/core';
import { DesconectadosService } from '../shared/desconectados';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'stg-global-header',
  templateUrl: './global-header.component.html',
  styleUrls: ['./global-header.component.css'],
  animations: [
      trigger('fade',
      [
        state('void', style({ opacity : 0})),
        transition(':enter', [ animate(300)]),
        transition(':leave', [ animate(500)]),
      ]
  )]
})
export class GlobalHeaderComponent implements OnInit {

  itemSplash: ItemSplash[];
  itemSplasActivo: ItemSplash;
  idItemActivo = 0;
  interval: any;
  hidePass = true;
  options: FormGroup;
  switchLogin = false;
  @Input() paginaActiva = 'home';
  @Input() conNavegador = true;

  constructor(private servicios: DesconectadosService, @Inject(DOCUMENT) document) {
    this.options = new FormGroup({
      emailFormControl: new FormControl('', [
        Validators.required,
        Validators.email,
      ]),
      passwordFormControl: new FormControl('', [
        Validators.required
      ])
   });
  }

  ngOnInit() {
    switch (this.paginaActiva) {
      case 'home':
        this.ItemsSplash();
        this.itemSplasActivo = this.itemSplash[this.idItemActivo];
        this.conNavegador = true;
        this.IntervalChangeSplash();
        break;
      case 'productos':
        this.ItemsSplash();
        this.itemSplasActivo = this.itemSplash[1];
        this.conNavegador = false;
        clearInterval(this.interval);
        break;
      case 'servicios':
        this.ItemsSplash();
        this.itemSplasActivo = this.itemSplash[0];
        this.conNavegador = false;
        clearInterval(this.interval);
        break;
      case 'somos':
        this.ItemsSplash();
        this.itemSplasActivo = this.itemSplash[2];
        this.conNavegador = false;
        clearInterval(this.interval);
        break;
      case 'contacto':
        this.ItemsSplash();
        this.itemSplasActivo = {
          id: 0,
          titulo: '',
          subTitulo: '',
          imagen: 'none',
          linkMas: ''
        };
        this.conNavegador = false;
        clearInterval(this.interval);
        break;
      default:
        break;
    }
  }

  IntervalChangeSplash() {
    this.interval = setInterval(() => {
      this.idItemActivo += 1;
      if (this.idItemActivo >= 0 && this.idItemActivo <= 2) {
        this.itemSplasActivo = this.itemSplash[this.idItemActivo];
      } else {
        this.idItemActivo = 0;
        this.itemSplasActivo = this.itemSplash[this.idItemActivo];
      }
    }, 5000);
  }

  NextNavegador() {
    if (this.idItemActivo >= 0 && this.idItemActivo <= 2) {
      clearInterval(this.interval);
      this.idItemActivo = this.idItemActivo + 1;
      this.itemSplasActivo = this.itemSplash[this.idItemActivo];
    }
  }

  PrevNavegador() {
    if (this.idItemActivo >= 0 && this.idItemActivo <= 2) {
      clearInterval(this.interval);
      this.idItemActivo = this.idItemActivo - 1;
      this.itemSplasActivo = this.itemSplash[this.idItemActivo];
    } else if (this.idItemActivo === 3) {
      clearInterval(this.interval);
      this.idItemActivo = 0;
      this.itemSplasActivo = this.itemSplash[this.idItemActivo];
    }
  }

  ItemsSplash() {
    this.servicios.getItemsSplash('splash').subscribe(result => {
      this.itemSplash = result;
    });
  }

  ToggleLogin() {
    this.switchLogin = !this.switchLogin;
  }

  @HostListener('window:scroll', ['$event'])
  onWindowScroll(e) {
     if (window.pageYOffset > 550) {
       document.getElementById('navbar-menu').classList.add('sticky');
     } else {
      document.getElementById('navbar-menu').classList.remove('sticky');
     }
  }
}
