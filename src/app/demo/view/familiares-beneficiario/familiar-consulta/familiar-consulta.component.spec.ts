import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FamiliarConsultaComponent } from './familiar-consulta.component';

describe('FamiliarConsultaComponent', () => {
  let component: FamiliarConsultaComponent;
  let fixture: ComponentFixture<FamiliarConsultaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FamiliarConsultaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FamiliarConsultaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
