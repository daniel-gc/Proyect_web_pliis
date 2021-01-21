import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../breadcrumb.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private breadcrumbService: BreadcrumbService) {
      this.breadcrumbService.setItems([
          {label: 'Dashboard', routerLink: ['/']}
      ]);
  }

  ngOnInit(): void {
  }

}
