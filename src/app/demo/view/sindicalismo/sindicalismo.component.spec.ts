import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SindicalismoComponent } from './sindicalismo.component';

describe('SindicalismoComponent', () => {
  let component: SindicalismoComponent;
  let fixture: ComponentFixture<SindicalismoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SindicalismoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SindicalismoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
