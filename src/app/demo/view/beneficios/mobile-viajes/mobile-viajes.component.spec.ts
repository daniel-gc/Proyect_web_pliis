import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MobileViajesComponent } from './mobile-viajes.component';

describe('MobileViajesComponent', () => {
  let component: MobileViajesComponent;
  let fixture: ComponentFixture<MobileViajesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MobileViajesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MobileViajesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
