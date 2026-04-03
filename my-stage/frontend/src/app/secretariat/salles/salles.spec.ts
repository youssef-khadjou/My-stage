import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Salles } from './salles';

describe('Salles', () => {
  let component: Salles;
  let fixture: ComponentFixture<Salles>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Salles],
    }).compileComponents();

    fixture = TestBed.createComponent(Salles);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
