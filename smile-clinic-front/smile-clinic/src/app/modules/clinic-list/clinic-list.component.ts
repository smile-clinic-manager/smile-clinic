import { Component } from "@angular/core";
import { MatTableModule } from "@angular/material/table";
import { ClinicDTO } from "../../models/ClinicDTO";

@Component(
  {
    selector: "app-clinic-list",
    imports: [MatTableModule],
    templateUrl: "./clinic-list.component.html",
    styleUrls: ["./clinic-list.component.scss"],
  }
) export class ClinicListComponent {
  constructor() {
  }
  displayedColumns: string[] = ["name", "postalCode", "address", "phoneNumber", "email"];
  dataSource: ClinicDTO[] = [
    {
      name: "Smile Clinic",
      postalCode: "12345",
      address: "1234 Smile St",
      phoneNumber: "123-456-7890",
      email: "a@b.es",
      img: "smile.jpg",
      invitations: ["12345", "67890"],
      treatments: ["cleaning", "whitening"]
    }
  ];
};
