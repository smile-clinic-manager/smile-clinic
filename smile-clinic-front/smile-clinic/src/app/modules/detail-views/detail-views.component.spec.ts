import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DetailViewsComponent } from './detail-views.component';

describe('DetailViewsComponent', () => {
  let component: DetailViewsComponent;
  let fixture: ComponentFixture<DetailViewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailViewsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailViewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});