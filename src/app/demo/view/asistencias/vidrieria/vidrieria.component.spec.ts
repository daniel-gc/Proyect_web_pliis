import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VidrieriaComponent } from './vidrieria.component';

describe('VidrieriaComponent', () => {
  let component: VidrieriaComponent;
  let fixture: ComponentFixture<VidrieriaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VidrieriaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VidrieriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
