import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductoGeneral } from 'src/app/shared/objetos';
import { AngularFirestore, AngularFirestoreCollection } from '@angular/fire/firestore';


@Injectable({
  providedIn: 'root'
})
export class ProductosService {
  private productosCollection: AngularFirestoreCollection<ProductoGeneral[]>;
  private votacionesCollection: AngularFirestoreCollection<ProductoGeneral[]>;

  constructor(private afs: AngularFirestore) {
  }

  getProductosGenerales(): Observable<any> {
    this.productosCollection = this.afs.collection<ProductoGeneral[]>('productos');
    return this.productosCollection.valueChanges();
  }

  getVotaciones(): Observable<any> {
    this.votacionesCollection = this.afs.collection<ProductoGeneral[]>('votaciones');
    return this.votacionesCollection.valueChanges();
  }
}
