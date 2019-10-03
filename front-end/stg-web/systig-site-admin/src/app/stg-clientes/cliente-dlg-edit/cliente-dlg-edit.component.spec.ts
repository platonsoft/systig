import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClienteDlgEditComponent } from './cliente-dlg-edit.component';

describe('ClienteDlgEditComponent', () => {
  let component: ClienteDlgEditComponent;
  let fixture: ComponentFixture<ClienteDlgEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClienteDlgEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClienteDlgEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
