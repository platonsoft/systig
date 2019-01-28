import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemCotComponent } from './item-cot.component';

describe('ItemCotComponent', () => {
  let component: ItemCotComponent;
  let fixture: ComponentFixture<ItemCotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemCotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemCotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
