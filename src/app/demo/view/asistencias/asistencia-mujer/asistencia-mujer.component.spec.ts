import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AsistenciaMujerComponent } from './asistencia-mujer.component';

describe('AsistenciaMujerComponent', () => {
  let component: AsistenciaMujerComponent;
  let fixture: ComponentFixture<AsistenciaMujerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AsistenciaMujerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AsistenciaMujerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
