import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileDlgEditComponent } from './profile-dlg-edit.component';

describe('ProfileDlgEditComponent', () => {
  let component: ProfileDlgEditComponent;
  let fixture: ComponentFixture<ProfileDlgEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfileDlgEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileDlgEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
