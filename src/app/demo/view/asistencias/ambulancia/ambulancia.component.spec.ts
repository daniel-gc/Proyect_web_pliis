import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AmbulanciaComponent } from './ambulancia.component';

describe('AmbulanciaComponent', () => {
  let component: AmbulanciaComponent;
  let fixture: ComponentFixture<AmbulanciaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AmbulanciaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AmbulanciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
