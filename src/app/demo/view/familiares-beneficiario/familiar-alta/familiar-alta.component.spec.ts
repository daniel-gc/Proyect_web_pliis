import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FamiliarAltaComponent } from './familiar-alta.component';

describe('FamiliarAltaComponent', () => {
  let component: FamiliarAltaComponent;
  let fixture: ComponentFixture<FamiliarAltaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FamiliarAltaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FamiliarAltaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
