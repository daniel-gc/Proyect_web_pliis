import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CerrajeriaComponent } from './cerrajeria.component';

describe('CerrajeriaComponent', () => {
  let component: CerrajeriaComponent;
  let fixture: ComponentFixture<CerrajeriaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CerrajeriaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CerrajeriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
