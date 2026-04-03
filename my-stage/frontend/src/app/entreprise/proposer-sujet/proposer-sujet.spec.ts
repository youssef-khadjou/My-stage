import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProposerSujet } from './proposer-sujet';

describe('ProposerSujet', () => {
  let component: ProposerSujet;
  let fixture: ComponentFixture<ProposerSujet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProposerSujet],
    }).compileComponents();

    fixture = TestBed.createComponent(ProposerSujet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
