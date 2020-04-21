import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DlgCurriculumComponent } from './dlg-curriculum.component';

describe('DlgCurriculumComponent', () => {
  let component: DlgCurriculumComponent;
  let fixture: ComponentFixture<DlgCurriculumComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DlgCurriculumComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DlgCurriculumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
