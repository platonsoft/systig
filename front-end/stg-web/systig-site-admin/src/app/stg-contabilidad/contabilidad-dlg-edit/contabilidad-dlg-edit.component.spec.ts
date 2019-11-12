import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContabilidadDlgEditComponent } from './contabilidad-dlg-edit.component';

describe('ContabilidadDlgEditComponent', () => {
  let component: ContabilidadDlgEditComponent;
  let fixture: ComponentFixture<ContabilidadDlgEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContabilidadDlgEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContabilidadDlgEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
