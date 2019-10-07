import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductosDlgImpExpComponent } from './productos-dlg-imp-exp.component';

describe('ProductosDlgImpExpComponent', () => {
  let component: ProductosDlgImpExpComponent;
  let fixture: ComponentFixture<ProductosDlgImpExpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductosDlgImpExpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductosDlgImpExpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
