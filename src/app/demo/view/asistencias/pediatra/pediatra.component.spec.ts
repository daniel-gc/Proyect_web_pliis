import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PediatraComponent } from './pediatra.component';

describe('PediatraComponent', () => {
  let component: PediatraComponent;
  let fixture: ComponentFixture<PediatraComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PediatraComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PediatraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
