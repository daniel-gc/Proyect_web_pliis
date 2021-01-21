import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DescuentoMedicoComponent } from './descuento-medico.component';

describe('DescuentoMedicoComponent', () => {
  let component: DescuentoMedicoComponent;
  let fixture: ComponentFixture<DescuentoMedicoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DescuentoMedicoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DescuentoMedicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
