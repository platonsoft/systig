import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductosDlgEditComponent } from './productos-dlg-edit.component';

describe('ProductosDlgEditComponent', () => {
  let component: ProductosDlgEditComponent;
  let fixture: ComponentFixture<ProductosDlgEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductosDlgEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductosDlgEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
