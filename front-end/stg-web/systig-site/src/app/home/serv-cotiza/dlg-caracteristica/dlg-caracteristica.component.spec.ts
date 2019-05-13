import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DlgCaracteristicaComponent } from './dlg-caracteristica.component';

describe('DlgCaracteristicaComponent', () => {
  let component: DlgCaracteristicaComponent;
  let fixture: ComponentFixture<DlgCaracteristicaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DlgCaracteristicaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DlgCaracteristicaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
