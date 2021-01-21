import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServFunerariosComponent } from './serv-funerarios.component';

describe('ServFunerariosComponent', () => {
  let component: ServFunerariosComponent;
  let fixture: ComponentFixture<ServFunerariosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServFunerariosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServFunerariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
