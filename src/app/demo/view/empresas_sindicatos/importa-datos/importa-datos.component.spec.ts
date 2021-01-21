import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImportaDatosComponent } from './importa-datos.component';

describe('ImportaDatosComponent', () => {
  let component: ImportaDatosComponent;
  let fixture: ComponentFixture<ImportaDatosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImportaDatosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImportaDatosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
