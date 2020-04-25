import { Component } from '@angular/core';

@Component({
  selector: 'stg-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  isCargando = true;
  title = 'systig-site';

  constructor(){
    document.addEventListener('readystatechange', () => {
      this.cambiaCargando();
    });
  }

  cambiaCargando() {
    while ( document.readyState !== 'complete') {
      this.isCargando = true;
      console.log('completo');

    }
    this.isCargando = false;
    console.log(document.readyState);
  }
}
