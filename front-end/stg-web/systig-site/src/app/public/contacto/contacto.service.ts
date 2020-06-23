import { Injectable } from '@angular/core';
import { PaisDisponible } from 'src/app/shared/objetos';
import { of, Observable } from 'rxjs';
import { AngularFirestore, AngularFirestoreCollection } from '@angular/fire/firestore';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ContactoService {

  private paisesCollection: AngularFirestoreCollection<PaisDisponible[]>;

  constructor(private afs: AngularFirestore) { }

  getPaisesDisponibles(): Observable<any> {
    this.paisesCollection = this.afs.collection<PaisDisponible[]>('paises');
    return this.paisesCollection.valueChanges();
  }

  registrarMensajes(contactForm: FormGroup){
    if (!contactForm.invalid) {
      const shirtsCollection = this.afs.collection<any>('mensajes');
      shirtsCollection.add({
        nombre: contactForm.get('nombreFormControl').value,
        email: contactForm.get('emailFormControl').value,
        telefono: contactForm.get('movilFormControl').value,
        pais: contactForm.get('paisFormControl').value,
        tema: contactForm.get('asuntoFormControl').value,
        mensaje: contactForm.get('mensajeFormControl').value,
      });
      alert('Muchas gracias, nos estaremos comunicando con usted a la brevedad...');
    } else {
      alert('Hubo un error');
    }
  }
}
