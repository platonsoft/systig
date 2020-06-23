import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DlgCaracteristicasComponent } from './dlg-caracteristicas.component';

describe('DlgCaracteristicasComponent', () => {
  let component: DlgCaracteristicasComponent;
  let fixture: ComponentFixture<DlgCaracteristicasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DlgCaracteristicasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DlgCaracteristicasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
