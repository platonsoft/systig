import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialCotDetalleComponent } from './dial-cot-detalle.component';

describe('DialCotDetalleComponent', () => {
  let component: DialCotDetalleComponent;
  let fixture: ComponentFixture<DialCotDetalleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialCotDetalleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialCotDetalleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
