import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpShowProfileComponent } from './emp-show-profile.component';

describe('EmpShowProfileComponent', () => {
  let component: EmpShowProfileComponent;
  let fixture: ComponentFixture<EmpShowProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmpShowProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmpShowProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
