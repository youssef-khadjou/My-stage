import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Propositions } from './propositions';

describe('Propositions', () => {
  let component: Propositions;
  let fixture: ComponentFixture<Propositions>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Propositions],
    }).compileComponents();

    fixture = TestBed.createComponent(Propositions);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
