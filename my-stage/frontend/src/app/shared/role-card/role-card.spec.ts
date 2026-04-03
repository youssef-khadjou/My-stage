import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoleCard } from './role-card';

describe('RoleCard', () => {
  let component: RoleCard;
  let fixture: ComponentFixture<RoleCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoleCard],
    }).compileComponents();

    fixture = TestBed.createComponent(RoleCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
