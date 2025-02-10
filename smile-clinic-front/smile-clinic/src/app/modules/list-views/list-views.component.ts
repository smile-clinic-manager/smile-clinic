import { Component } from "@angular/core";

@Component({
  selector: "app-list-views",
  templateUrl: "./list-views.component.html",
  styleUrls: ["./list-views.component.scss"],
})
export class ListViewsComponent {
  items: any;
  constructor(
    public title: string,
    ) {}
}