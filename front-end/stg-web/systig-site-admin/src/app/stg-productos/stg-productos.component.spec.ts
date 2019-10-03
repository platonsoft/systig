import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StgProductosComponent } from './stg-productos.component';

describe('StgProductosComponent', () => {
  let component: StgProductosComponent;
  let fixture: ComponentFixture<StgProductosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StgProductosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StgProductosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
