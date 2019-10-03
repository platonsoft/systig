import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StgClientesComponent } from './stg-clientes.component';

describe('StgClientesComponent', () => {
  let component: StgClientesComponent;
  let fixture: ComponentFixture<StgClientesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StgClientesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StgClientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
