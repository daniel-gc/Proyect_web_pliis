import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CuidadosEnfermeriaComponent } from './cuidados-enfermeria.component';

describe('CuidadosEnfermeriaComponent', () => {
  let component: CuidadosEnfermeriaComponent;
  let fixture: ComponentFixture<CuidadosEnfermeriaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CuidadosEnfermeriaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CuidadosEnfermeriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
