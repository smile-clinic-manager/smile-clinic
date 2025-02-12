import { Component } from "@angular/core";
import { DetailViewsComponent } from "../detail-views/detail-views.component";
import { ListViewsComponent } from "../list-views/list-views.component";

@Component(
  {
    selector: "app-clinic-list",
    imports: [DetailViewsComponent, ListViewsComponent],
    templateUrl: "./clinic-list.component.html",
    styleUrls: ["./clinic-list.component.scss"],
  }
) export class ClinicListComponent {
  constructor(
    public title: string,
    public items: any,
    ) {
      this.title = "Clinic List";
      this.items = [
        {
          name: "Smile Clinic",
          location: "1234 Main St",
          phone: "555-555-5555",
          email: "b@a.es"
        },
        {
          name: "Smile Clinic 2",
          location: "3434 Main St",
          phone: "111-555-5555",
          email: "a@b.es"
        }
      ];
      new ListViewsComponent(title, items);
    }
};