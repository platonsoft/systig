import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeadSomosComponent } from './head-somos.component';

describe('HeadSomosComponent', () => {
  let component: HeadSomosComponent;
  let fixture: ComponentFixture<HeadSomosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeadSomosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeadSomosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
