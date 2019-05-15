import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SomosSystigComponent } from './somos-systig.component';

describe('SomosSystigComponent', () => {
  let component: SomosSystigComponent;
  let fixture: ComponentFixture<SomosSystigComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SomosSystigComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SomosSystigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
