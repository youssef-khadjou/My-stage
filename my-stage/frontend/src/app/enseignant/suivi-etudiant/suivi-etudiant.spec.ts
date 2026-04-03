import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuiviEtudiant } from './suivi-etudiant';

describe('SuiviEtudiant', () => {
  let component: SuiviEtudiant;
  let fixture: ComponentFixture<SuiviEtudiant>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SuiviEtudiant],
    }).compileComponents();

    fixture = TestBed.createComponent(SuiviEtudiant);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
