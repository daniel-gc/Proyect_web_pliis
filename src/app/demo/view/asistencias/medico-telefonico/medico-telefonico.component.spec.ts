import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicoTelefonicoComponent } from './medico-telefonico.component';

describe('MedicoTelefonicoComponent', () => {
  let component: MedicoTelefonicoComponent;
  let fixture: ComponentFixture<MedicoTelefonicoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedicoTelefonicoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicoTelefonicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
