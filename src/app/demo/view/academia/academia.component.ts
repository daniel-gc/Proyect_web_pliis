import { Component, OnInit } from '@angular/core';
import {BreadcrumbService} from '../../../breadcrumb.service';

@Component({
  selector: 'app-academia',
  templateUrl: './academia.component.html',
  styleUrls: ['./academia.component.css']
})
export class AcademiaComponent implements OnInit {
    ngOnInit(): void {

    }

  constructor(private breadcrumbService: BreadcrumbService) {
      this.breadcrumbService.setItems([
          {label: 'Academia', routerLink: ['/academia']}
      ]);
  }

}
