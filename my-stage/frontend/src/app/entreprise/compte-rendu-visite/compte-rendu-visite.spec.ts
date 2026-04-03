import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompteRenduVisite } from './compte-rendu-visite';

describe('CompteRenduVisite', () => {
  let component: CompteRenduVisite;
  let fixture: ComponentFixture<CompteRenduVisite>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompteRenduVisite],
    }).compileComponents();

    fixture = TestBed.createComponent(CompteRenduVisite);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
