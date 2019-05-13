import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServCotizaComponent } from './serv-cotiza.component';

describe('ServCotizaComponent', () => {
  let component: ServCotizaComponent;
  let fixture: ComponentFixture<ServCotizaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServCotizaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServCotizaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
