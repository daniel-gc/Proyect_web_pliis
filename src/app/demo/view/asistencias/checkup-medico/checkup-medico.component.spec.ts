import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckupMedicoComponent } from './checkup-medico.component';

describe('CheckupMedicoComponent', () => {
  let component: CheckupMedicoComponent;
  let fixture: ComponentFixture<CheckupMedicoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckupMedicoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckupMedicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
