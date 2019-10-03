import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FacturasDlgEditComponent } from './facturas-dlg-edit.component';

describe('FacturasDlgEditComponent', () => {
  let component: FacturasDlgEditComponent;
  let fixture: ComponentFixture<FacturasDlgEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FacturasDlgEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FacturasDlgEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
