import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactoSystigComponent } from './contacto-systig.component';

describe('ContactoSystigComponent', () => {
  let component: ContactoSystigComponent;
  let fixture: ComponentFixture<ContactoSystigComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContactoSystigComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContactoSystigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
