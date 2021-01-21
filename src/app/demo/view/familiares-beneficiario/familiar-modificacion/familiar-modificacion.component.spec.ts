import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FamiliarModificacionComponent } from './familiar-modificacion.component';

describe('FamiliarModificacionComponent', () => {
  let component: FamiliarModificacionComponent;
  let fixture: ComponentFixture<FamiliarModificacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FamiliarModificacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FamiliarModificacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
