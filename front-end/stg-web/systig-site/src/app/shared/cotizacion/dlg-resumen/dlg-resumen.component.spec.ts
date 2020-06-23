import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DlgResumenComponent } from './dlg-resumen.component';

describe('DlgResumenComponent', () => {
  let component: DlgResumenComponent;
  let fixture: ComponentFixture<DlgResumenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DlgResumenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DlgResumenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
