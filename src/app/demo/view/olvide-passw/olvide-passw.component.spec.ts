import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OlvidePasswComponent } from './olvide-passw.component';

describe('OlvidePasswComponent', () => {
  let component: OlvidePasswComponent;
  let fixture: ComponentFixture<OlvidePasswComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OlvidePasswComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OlvidePasswComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
