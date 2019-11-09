import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StgProveedoresComponent } from './stg-proveedores.component';

describe('StgProveedoresComponent', () => {
  let component: StgProveedoresComponent;
  let fixture: ComponentFixture<StgProveedoresComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StgProveedoresComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StgProveedoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
