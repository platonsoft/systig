import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentoDlgEditComponent } from './documento-dlg-edit.component';

describe('DocumentoDlgEditComponent', () => {
  let component: DocumentoDlgEditComponent;
  let fixture: ComponentFixture<DocumentoDlgEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocumentoDlgEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentoDlgEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
