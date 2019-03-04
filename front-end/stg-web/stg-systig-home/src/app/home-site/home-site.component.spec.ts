import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeSiteComponent } from './home-site.component';

describe('HomeSiteComponent', () => {
  let component: HomeSiteComponent;
  let fixture: ComponentFixture<HomeSiteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeSiteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeSiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
