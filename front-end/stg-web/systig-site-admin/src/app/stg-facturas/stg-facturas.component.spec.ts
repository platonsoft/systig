import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StgFacturasComponent } from './stg-facturas.component';

describe('StgFacturasComponent', () => {
  let component: StgFacturasComponent;
  let fixture: ComponentFixture<StgFacturasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StgFacturasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StgFacturasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
