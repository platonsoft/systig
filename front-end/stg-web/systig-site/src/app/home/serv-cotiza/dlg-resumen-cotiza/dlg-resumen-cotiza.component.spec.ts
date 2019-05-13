import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DlgResumenCotizaComponent } from './dlg-resumen-cotiza.component';

describe('DlgResumenCotizaComponent', () => {
  let component: DlgResumenCotizaComponent;
  let fixture: ComponentFixture<DlgResumenCotizaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DlgResumenCotizaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DlgResumenCotizaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
