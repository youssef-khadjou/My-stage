import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SallesPlanning } from './salles-planning';

describe('SallesPlanning', () => {
  let component: SallesPlanning;
  let fixture: ComponentFixture<SallesPlanning>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SallesPlanning],
    }).compileComponents();

    fixture = TestBed.createComponent(SallesPlanning);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
