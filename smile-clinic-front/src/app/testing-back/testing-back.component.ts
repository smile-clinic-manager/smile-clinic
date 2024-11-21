import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../service/api.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-testing-back',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './testing-back.component.html',
  styleUrl: './testing-back.component.scss',
  providers: [ApiService],
})
export class TestingBackComponent implements OnInit {
  data: any[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.fillData();
  }

  fillData() {
    this.apiService.getData().subscribe((data) => {
      this.data = data;
      console.log(this.data);
    });
  }
}
