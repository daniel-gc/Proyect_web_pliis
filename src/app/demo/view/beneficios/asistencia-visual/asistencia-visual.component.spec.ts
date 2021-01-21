import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AsistenciaVisualComponent } from './asistencia-visual.component';

describe('AsistenciaVisualComponent', () => {
  let component: AsistenciaVisualComponent;
  let fixture: ComponentFixture<AsistenciaVisualComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AsistenciaVisualComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AsistenciaVisualComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
