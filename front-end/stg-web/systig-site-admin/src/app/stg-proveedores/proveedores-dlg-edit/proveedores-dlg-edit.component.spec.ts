import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProveedoresDlgEditComponent } from './proveedores-dlg-edit.component';

describe('ProveedoresDlgEditComponent', () => {
  let component: ProveedoresDlgEditComponent;
  let fixture: ComponentFixture<ProveedoresDlgEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProveedoresDlgEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProveedoresDlgEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
