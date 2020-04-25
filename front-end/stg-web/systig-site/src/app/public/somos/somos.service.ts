import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { EmpleadosSystig, ItemSplash } from 'src/app/shared/objetos';
import { AngularFirestore, AngularFirestoreCollection } from '@angular/fire/firestore';

@Injectable({
  providedIn: 'root'
})
export class SomosService {

  private equipoCollection: AngularFirestoreCollection<EmpleadosSystig[]>;
  private experienciasCollection: AngularFirestoreCollection<ItemSplash[]>;

  constructor(private afs: AngularFirestore) { }

  getEmpleadosSystig(): Observable<any> {
    this.equipoCollection = this.afs.collection<EmpleadosSystig[]>('equipo');
    return this.equipoCollection.valueChanges();
  }

  getItemsSplash(): Observable<any> {
    this.experienciasCollection = this.afs.collection<ItemSplash[]>('experiencias');
    return this.experienciasCollection.valueChanges();
  }
}
