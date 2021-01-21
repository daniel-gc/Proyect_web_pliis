import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CambioPasswComponent } from './cambio-passw.component';

describe('CambioPasswComponent', () => {
  let component: CambioPasswComponent;
  let fixture: ComponentFixture<CambioPasswComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CambioPasswComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CambioPasswComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
