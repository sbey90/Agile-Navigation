import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpShowTasksComponent } from './emp-show-tasks.component';

describe('EmpShowTasksComponent', () => {
  let component: EmpShowTasksComponent;
  let fixture: ComponentFixture<EmpShowTasksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmpShowTasksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmpShowTasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
