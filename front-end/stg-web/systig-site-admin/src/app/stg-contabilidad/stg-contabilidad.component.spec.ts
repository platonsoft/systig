import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StgContabilidadComponent } from './stg-contabilidad.component';

describe('StgContabilidadComponent', () => {
  let component: StgContabilidadComponent;
  let fixture: ComponentFixture<StgContabilidadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StgContabilidadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StgContabilidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
