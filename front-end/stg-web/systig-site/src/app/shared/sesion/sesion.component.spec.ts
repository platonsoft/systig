import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SesionComponent } from './sesion.component';

describe('SesionComponent', () => {
  let component: SesionComponent;
  let fixture: ComponentFixture<SesionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SesionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SesionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
