import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClinicPersonalComponent } from './clinic-personal.component';

describe('ClinicPersonalComponent', () => {
  let component: ClinicPersonalComponent;
  let fixture: ComponentFixture<ClinicPersonalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClinicPersonalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClinicPersonalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
