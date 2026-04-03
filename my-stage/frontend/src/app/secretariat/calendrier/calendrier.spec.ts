import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Calendrier } from './calendrier';

describe('Calendrier', () => {
  let component: Calendrier;
  let fixture: ComponentFixture<Calendrier>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Calendrier],
    }).compileComponents();

    fixture = TestBed.createComponent(Calendrier);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
